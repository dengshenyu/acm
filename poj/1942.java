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

    static long C(long n, long k) {
        long ans = 1;
        for (long i = 1; i <= k; i++)
            ans = ans * (n-i+1) / i;
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            long n = scanner.nextLong();
            long m = scanner.nextLong();
            if (n == 0 && m == 0)
                break;
            if (m > n) {
                long t = m;
                m = n;
                n = t;
            }
            n += m;
            System.out.println(C(n, m));
        }
    }
}
