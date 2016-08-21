import java.util.*;

public class Main {
    private static char[] ops = new char[100];
    private static boolean[] visited = new boolean[400000];


    private static int perm2num(byte[] p) {
        int ans = 0;
        int t = 1;
        for (int i = 7; i >= 0; i--) {
            //int k = 0;
            for (int j = 8; j > i; j--) {
                if (p[i] > p[j])
                    ans += t;
            }
            t *= (9-i);
            //System.out.println(t);
        }

        return ans;
    }

    private static boolean isSuccess(byte[] num) {
        for (int i = 0; i < 8; i++) {
            if (num[i] - num[i+1] != -1)
                return false;
        }
        return true;
    }

    private static int h(byte[] nums) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (nums[i] == 9)
                continue;
            count += Math.abs(i/3 - (nums[i]-1)/3) + Math.abs(i%3 - (nums[i]-1)%3);
        }
        return count;
    }

    private static void swap(byte[] nums, int p1, int p2) {
        byte t = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = t;
    }

    private static boolean dfs(int pathLimit, int g, byte[] nums) {
        if (isSuccess(nums))
            return true;
        if (g >= pathLimit)
            return false;
        if (g + h(nums) <= pathLimit) {
            visited[perm2num(nums)] = true;
            int i = 0, j = 0;
            for(i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (nums[i*3+j] == 9)
                        break;
                }
                if (j < 3)
                    break;
            }
            if (i > 0) {
                swap(nums, (i-1)*3+j, i*3+j);
                ops[g] = 'u';
                if (!visited[perm2num(nums)] && dfs(pathLimit, g+1, nums))
                    return true;
                swap(nums, (i-1)*3+j, i*3+j);
            }
            if (i < 2) {
                swap(nums, (i+1)*3+j, i*3+j);
                ops[g] = 'd';
                if (!visited[perm2num(nums)] && dfs(pathLimit, g+1, nums))
                    return true;
                swap(nums, (i+1)*3+j, i*3+j);
            }
            if (j > 0) {
                swap(nums, i*3+j-1, i*3+j);
                ops[g] = 'l';
                if (!visited[perm2num(nums)] && dfs(pathLimit, g+1, nums))
                    return true;
                swap(nums, i*3+j-1, i*3+j);
            }
            if (j < 2) {
                swap(nums, i*3+j+1, i*3+j);
                ops [g] = 'r';
                if (!visited[perm2num(nums)] && dfs(pathLimit, g+1, nums))
                    return true;
                swap(nums, i*3+j+1, i*3+j);
            }
            visited[perm2num(nums)] = false;
        }

        return false;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        byte[] nums = new byte[9];
        for (int i = 0; i < 9; i++) {
            String s = scanner.next();
            //System.out.println(">"+s+"<");
            char c = s.charAt(0);
            if (c >= '0' && c <= '8') {
                nums[i] = (byte) (c - '0');
            } else {
                nums[i] = (byte) 9;
            }
        }

        int pathLimit = h(nums);
        boolean success = false;
        while (!success && pathLimit <= 50) {
            if (dfs(pathLimit, 0, nums)) {
                success = true;
                break;
            }
            pathLimit++;
        }
        if (success) {
            for (int i = 0; i < pathLimit; i++) {
                System.out.print(ops[i]);
            }
            System.out.println();
        } else {
            System.out.println("unsolvable");
        }



    }
}
