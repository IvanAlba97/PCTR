package src;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

public class resImagenPar implements Runnable {

    public int h;
    public int[][] M, M2;
    private static final int N = 256;

    public resImagenPar(int[][] M, int[][] M2, int i) {
        this.M = M;
        this.M2 = M2;
        this.h = i;
    }

    @Override
    public void run() {
        for (int j = 1; j < N - 1; j++) {
            M2[h][j] = (4 * M[h][j] - M[h][j - 1] - M[h][j + 1] - M[h - 1][j] - M[h + 1][j]) / 8;
        }
    }

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);

        int nNuc = Runtime.getRuntime().availableProcessors();
        System.out.print("Introduce el coeficiente de bloqueo [0,1): ");
        double Cb = S.nextDouble();
        int nHilos = (int) (nNuc / (1 - Cb));

        int[][] M = new int[N+2][N+2];
        int[][] M2 = new int[N+2][N+2];

        rellenar(M);
        resaltar(M, M2, nHilos);
        //mostrar(M, M2);
        
        S.close();
    }

    public static void rellenar(int[][] M) {

        Random rand = new Random(System.nanoTime());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = 0;
            }
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                M[i][j] = rand.nextInt(20) + 1;
            }
        }
    }

    public static void resaltar(int[][] M, int[][] M2, int nHilos) {

        double tiempoInicio = System.nanoTime();

        ExecutorService pool = Executors.newFixedThreadPool(nHilos);

        for (int i = 1; i < N - 1; i++) {
            Runnable runnable = new resImagenPar(M, M2, i);
            pool.execute(runnable);
        }

        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFin = System.nanoTime() - tiempoInicio;

        System.out.println("Tiempo = " + tiempoFin/1000000 + " milisegundos.");
    }

    public static void mostrar(int[][] M, int[][] M2) {

        System.out.println("    .:MATRIZ NORMAL:.");
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j <N - 1; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("    .:MATRIZ RESALTADA:.");
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                System.out.print(M2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
