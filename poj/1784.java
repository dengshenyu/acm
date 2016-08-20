import java.util.Scanner;

public class Main {

    static int n;
    static int[][] dp = new int[210][210];
    static int[] inner = new int[202];
    static int[] leaf = new int[202];
    static int[][] k = new int[202][202];
    static int[] sum = new int[202];

    private static void getMin() {

        for (int i = 0; i < n; i++) {
            dp[i][i] = leaf[i] + inner[i] + leaf[i+1];
            k[i][i] = i;
            if (i == 0)
                sum[i] = leaf[i] + inner[i] + leaf[i+1];
            else
                sum[i] = sum[i-1] + inner[i] + leaf[i+1];
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n-l; i++) {
                int j = i + l - 1;
                int ts = (i == 0) ? sum[j] : sum[j] - sum[i-1] + leaf[i];
                int min = Integer.MAX_VALUE;
                for (int m = k[i][j-1]; m <= k[i+1][j]; m++) {
                    int c = (m == 0) ? dp[m+1][j] + ts : dp[i][m-1] + dp[m+1][j] + ts;
                    if (c < min) {
                        min = c;
                        k[i][j] = m;
                    }
                }
                /*
                for (int m = i; m <= j; m++) {
                    int c = (m > 0) ? dp[i][m-1] + dp[m+1][j] + ts : dp[m+1][j] + ts;
                    if (c < min)
                        min = c;
                }
                */
                dp[i][j] = min;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            n = scanner.nextInt();
            if (n == 0)
                break;
            for (int i=0; i < n; i++) {
                inner[i] = scanner.nextInt();
            }
            for (int i=0; i < n+1; i++) {
                leaf[i] = scanner.nextInt();
            }
            getMin();
            System.out.println(dp[0][n-1]);
        }
    }
}
