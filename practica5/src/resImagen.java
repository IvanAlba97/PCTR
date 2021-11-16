package src;

import java.util.Random;

public class resImagen {

    private static final int N = 256;

    public static void main(String[] args) {

        int[][] M = new int[N + 2][N + 2];
        int[][] M2 = new int[N + 2][N + 2];

        rellenar(M);
        resaltar(M, M2);
        //mostrar(M, M2);
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

    public static void resaltar(int[][] M, int[][] M2) {

        double tiempoInicio = System.nanoTime();
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                M2[i][j] = (4 * M[i][j] - M[i][j - 1] - M[i][j + 1] - M[i - 1][j] - M[i + 1][j]) / 8;
            }
        }
        double tiempoFin = System.nanoTime() - tiempoInicio;
        System.out.println("Tiempo: " + tiempoFin / 1000000 + " milisegundos.");

    }

    public static void mostrar(int[][] M, int[][] M2) {

        System.out.println("    .:MATRIZ NORMAL:.");
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
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