import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        long[] s = new long[65537];
        long[] sum = new long[65537];
        long[] t = new long[65537];
        for (int i = 1; i < 65537; i++) {
            s[i] += s[i - 1] + (int) Math.log10(i) + 1;
            sum[i] = sum[i-1] + s[i];
        }
        for (int i = 1; i < 65537; i++) {
            t[i] += t[i-1] + (int) Math.log10(i) + 1;
        }


        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        while ((cases--)>0) {
            long n = scan.nextInt();
            int left = 1, right = 65536;
            while (right > left) {
                int middle = (left+right)/2;
                if (sum[middle] <= n)
                    left = middle+1;
                else
                    right = middle-1;
            }
            int k = left;
            if (sum[k] > n)
                k--;
            n -= sum[k];
            if (n == 0) {
                n = s[k];
            } else {
                k++;
            }
            left = 1; right = k;
            while (right > left) {
                int middle = (left+right)/2;
                if (t[middle] <= n)
                    left = middle+1;
                else
                    right = middle-1;
            }
            k = left;
            if (t[k] > n)
                k--;
            n -= t[k];
            if (n == 0) {
                System.out.println(k % 10);
            } else {
                k++;
                int c = 10;
                while (k / c > 0)
                    c *= 10;
                c /= 10;
                while (n > 1) {
                    k = k % c;
                    c /= 10;
                    n--;
                }
                System.out.println(k / c);
            }
        }

    }
}
