
/**
 *
 * @author ivan0
 */

import java.util.Random;
import java.util.Scanner;

public class resImagenParFin implements Runnable {

    public int h, n;
    public int[][] M, M2;

    public resImagenParFin(int[][] M, int[][] M2, int i, int n) {
        this.M = M;
        this.M2 = M2;
        this.h = i;
        this.n = n;
    }

    @Override
    public void run() {
        /*if (h != 0 && h != n) {*/
            for (int j = 1; j < n - 1; j++) {
                M2[h][j] = (4 * M[h][j] - M[h][j - 1] - M[h][j + 1] - M[h - 1][j] - M[h + 1][j]) / 8;
            }
        /*}*/
    }

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);

        System.out.print("Introduce el orden de la matriz: ");
        int n = S.nextInt();
        n += 2;

        int[][] M = new int[n][n];
        int[][] M2 = new int[n][n];

        rellenar(M, n);
        resaltar(M, M2, n);
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

    public static void resaltar(int[][] M, int[][] M2, int n) {

        Thread[] hilos = new Thread[n-2];

        double tiempoInicio = System.currentTimeMillis();
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new resImagenParFin(M, M2, i+1, n));
            hilos[i].start();
        }

        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i].join();
            }
        } catch (InterruptedException E) {
            System.err.println(E.getMessage());
        }
        
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
