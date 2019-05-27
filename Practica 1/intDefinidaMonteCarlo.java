
/**
 * @author ivan
 */
import java.util.Scanner;
import java.util.Random;

public class intDefinidaMonteCarlo {

    public static void main(String[] args) {
        int nPuntos, op;
        double res = 0;
        Scanner S = new Scanner(System.in);
        System.out.print("Introduzca el numero de puntos: ");
        nPuntos = S.nextInt();
        do {
            System.out.println("Elige una de las dos funciones: ");
            System.out.println("1. f(x) = sen(x)");
            System.out.println("2. f(x) = x");
            op = S.nextInt();
        } while (op < 1 || op > 2);
        switch (op) {
            case 1:
                res = f1(nPuntos);
                break;
            case 2:
                res = f2(nPuntos);
                break;
        }
        System.out.println("Resultado: " + res);
    }

    public static double f1(int nPuntos) {
        int hits = 0;
        double x, y, res;
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < nPuntos; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (y < Math.sin(x)) {
                hits++;
            }
        }
        res = (double) hits / nPuntos;
        return res;
    }

    public static double f2(int nPuntos) {
        int hits = 0;
        double x, y, res;
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < nPuntos; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (y < x) {
                hits++;
            }
        }
        res = (double) hits / nPuntos;
        return res;
    }
}
