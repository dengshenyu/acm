import java.util.*;

public class Main {

    private static boolean win(int a, int b) {
        if (a == b)
            return true;
        double p = (1 + Math.sqrt(5)) / 2;
        double q = (3 + Math.sqrt(5)) / 2;
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (a == Math.floor(p * (b - a)))
            return false;
        else
            return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a < b) {
                int t = a;
                a = b;
                b = t;
            }
            if (win(a, b)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
