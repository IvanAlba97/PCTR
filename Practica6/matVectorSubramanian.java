
/**
 *
 * @author ivan0
 */
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class matVectorSubramanian implements Runnable {

    int[][] A;
    int[] B;
    int[] C;
    int n, m;

    public matVectorSubramanian(int[][] A, int[] B, int[] C, int n, int m) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.n = n;
        this.m = m;
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        int op, n, m;

        System.out.print("Introduce el numero de filas de la matriz A: ");
        n = S.nextInt();
        System.out.print("Introduce el numero de columnas de la matriz A: ");
        m = S.nextInt();

        int[][] A = new int[n][m];
        int[] B = new int[m];
        int[] C = new int[n];

        do {
            System.out.println("Elige una de las opciones: ");
            System.out.println("1. Introducir datos manualmente.");
            System.out.println("2. Rellenar aleatoriamente.");
            System.out.print("Opcion: ");
            op = S.nextInt();
        } while (op < 1 || op > 2);

        switch (op) {
            case 1:
                introducirDatos(A, B, C, n, m);
                break;
            case 2:
                rellenarAleatoriamente(A, B, C, n, m);
                break;
            default:
                System.err.println("ERROR.");
        }

        double tiempoInicio = System.currentTimeMillis();

        System.out.print("Introduce el coeficiente de bloqueo [0,1): ");
        double Cb = S.nextDouble();

        int nNuc = Runtime.getRuntime().availableProcessors();
        int tamPool = (int) (nNuc / (1 - Cb));

        ExecutorService pool = Executors.newFixedThreadPool(tamPool);
        for (int i = 0; i < n; i++) {
            Runnable runnable = new matVectorSubramanian(A, B, C, i, m);
            pool.execute(runnable);
        }
        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFinal = System.currentTimeMillis() - tiempoInicio;

        //mostrar(A, B, C, n, m);
        System.out.println("Tiempo = " + tiempoFinal + " milisegundos.");
    }

    public static void introducirDatos(int[][] A, int[] B, int[] C, int n, int m) {
        Scanner S = new Scanner(System.in);
        System.out.println("Rellene la matriz A: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print("A[" + i + "][" + j + "] = ");
                A[i][j] = S.nextInt();
            }
        }
        System.out.println("Rellene el vector B:");
        for (int i = 0; i < m; i++) {
            System.out.print("B[" + i + "] = ");
            B[i] = S.nextInt();
        }
    }

    public static void rellenarAleatoriamente(int[][] A, int[] B, int[] C, int n, int m) {
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = rand.nextInt(10);
            }
        }
        for (int i = 0; i < m; i++) {
            B[i] = rand.nextInt(10);
        }
    }

    public static void mostrar(int[][] A, int[] B, int[] C, int n, int m) {

        System.out.println("MATRIZ A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("VECTOR B:");
        for (int i = 0; i < m; i++) {
            System.out.print(B[i] + " ");
        }
        System.out.println();

        System.out.println("VECTOR C:");
        for (int i = 0; i < n; i++) {
            System.out.print(C[i] + " ");
        }
        System.out.println();
    }
}
