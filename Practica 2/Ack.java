
import java.util.Scanner;

public class Ack {

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int n, m, ackermann;
        System.out.print("Introduce el valor de n: ");
        n = S.nextInt();
        System.out.print("Introduce el valor de m: ");
        m = S.nextInt();
        ackermann = Ack(n, m);
        if (ackermann == 0) {
            System.out.println("Los valores han de ser positivos.");
        } else {
            System.out.println("Ackermann = " + ackermann);
        }
    }

    public static int Ack(int n, int m) {
        if (n == 0) {
            return m + 1;
        } else if (n > 0 && m == 0) {
            return Ack(n - 1, 1);
        } else if (n > 0 && m > 0) {
            return Ack(n - 1, Ack(n, m - 1));
        } else {
            return 0;
        }
    }
}
