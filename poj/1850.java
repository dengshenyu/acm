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

    static int[][] f; //f[i][j]表示以字母i开头长度为j的单词个数

    static boolean isOk(String line) {
        for (int i = 0; i < line.length()-1; i++) {
            if (line.charAt(i) - line.charAt(i+1) >= 0)
                return false;
        }
        return true;
    }

    static void genData() {
        f = new int[26][11];
        for (int i = 0; i < 26; i++)
            f[i][1] = 1;
        for (int i = 2; i < 11; i++) {
            for (int j = 0; j < 26; j++) {
                int sum = 0;
                for (int k = j+1; k < 26; k++) {
                    sum += f[k][i-1];
                }
                f[j][i] = sum;
            }
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        if (!isOk(line)) {
            System.out.println(0);
        } else {
            genData();//打表
            int len = line.length();
            int pos = 0;
            for (int i = 1; i < len; i++)
                for (int j = 0; j < 26; j++)
                    pos += f[j][i];
            for (int i = 0; i < line.charAt(0)-'a'; i++)
                pos += f[i][len];
            for (int i = 1; i < len; i++) {
                for (int j = line.charAt(i-1)-'a'+1; j < line.charAt(i)-'a'; j++)
                    pos += f[j][len-i];
            }
            pos++;
            System.out.println(pos);
        }
    }

}
