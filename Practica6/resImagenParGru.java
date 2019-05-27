
/**
 *
 * @author ivan0
 */
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

public class resImagenParGru implements Runnable {

    public int h, n;
    public int[][] M, M2;

    public resImagenParGru(int[][] M, int[][] M2, int i, int n) {
        this.M = M;
        this.M2 = M2;
        this.h = i;
        this.n = n;
    }

    @Override
    public void run() {
        for (int j = 1; j < n - 1; j++) {
            M2[h][j] = (4 * M[h][j] - M[h][j - 1] - M[h][j + 1] - M[h - 1][j] - M[h + 1][j]) / 8;
        }
    }

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);

        System.out.print("Introduce el orden de la matriz: ");
        int n = S.nextInt();
        n += 2;

        int nNuc = Runtime.getRuntime().availableProcessors();
        System.out.print("Introduce el coeficiente de bloqueo [0,1): ");
        double Cb = S.nextDouble();
        int nHilos = (int) (nNuc / (1 - Cb));

        int[][] M = new int[n][n];
        int[][] M2 = new int[n][n];

        rellenar(M, n);
        resaltar(M, M2, n, nHilos);
        mostrar(M, M2, n);
    }

    public static void rellenar(int[][] M, int n) {

        Random rand = new Random(System.nanoTime());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = 0;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                M[i][j] = rand.nextInt(20) + 1;
            }
        }
    }

    public static void resaltar(int[][] M, int[][] M2, int n, int nHilos) {

        double tiempoInicio = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(nHilos);

        for (int i = 1; i < n - 1; i++) {
            Runnable runnable = new resImagenParGru(M, M2, i, n);
            pool.execute(runnable);
        }

        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFin = System.currentTimeMillis() - tiempoInicio;

        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
    }

    public static void mostrar(int[][] M, int[][] M2, int n) {

        System.out.println("    .:MATRIZ NORMAL:.");
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("    .:MATRIZ RESALTADA:.");
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                System.out.print(M2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
