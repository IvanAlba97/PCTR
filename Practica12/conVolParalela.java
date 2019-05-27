
/**
 *
 * @author ivan0
 */
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class conVolParalela implements Runnable {

    private static final int N = 10000;
    private int[][] A, K;
    private static int[][] B = new int[N][N];
    int i, j;

    public conVolParalela(int[][] A, int[][] K, int i, int j) {
        this.A = A;
        this.K = K;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        if (i == 0 || j == 0 || i == A.length - 1 || j == A[0].length - 1) {
            B[i][j] = A[i][j];
        } else {
            B[i][j] = A[i - 1][j - 1] * K[0][0]
                    + A[i - 1][j] * K[0][1]
                    + A[i - 1][j + 1] * K[0][2]
                    + A[i][j - 1] * K[1][0]
                    + A[i][j] * K[1][1]
                    + A[i][j + 1] * K[1][2]
                    + A[i + 1][j - 1] * K[2][0]
                    + A[i + 1][j] * K[2][1]
                    + A[i + 1][j + 1] * K[2][2];
        }
    }

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());
        int op, nHilos;
        int[][] A = new int[N][N];
        int[][] K = new int[3][3];

        do {
            System.out.println("Selecciona la opcion que desee: ");
            System.out.println("1. Enfocar.");
            System.out.println("2. Realzar bordes.");
            System.out.println("3. Detectar bordes.");
            System.out.println("4. Filtro de Sobel.");
            System.out.println("5. Filtro de Sharpen.");
            System.out.print("Opcion: ");
            op = S.nextInt();
        } while (op < 1 || op > 5);

        switch (op) {
            case 1:
                K[0][0] = 0;
                K[0][1] = -1;
                K[0][2] = 0;
                K[1][0] = -1;
                K[1][1] = 5;
                K[1][2] = -1;
                K[2][0] = 0;
                K[2][1] = -1;
                K[2][2] = 0;
                break;
            case 2:
                K[0][0] = 0;
                K[0][1] = 0;
                K[0][2] = 0;
                K[1][0] = -1;
                K[1][1] = 1;
                K[1][2] = 0;
                K[2][0] = 0;
                K[2][1] = 0;
                K[2][2] = 0;
                break;
            case 3:
                K[0][0] = 0;
                K[0][1] = 1;
                K[0][2] = 0;
                K[1][0] = 1;
                K[1][1] = -4;
                K[1][2] = 1;
                K[2][0] = 0;
                K[2][1] = 1;
                K[2][2] = 0;
                break;
            case 4:
                K[0][0] = -1;
                K[0][1] = 0;
                K[0][2] = 1;
                K[1][0] = -2;
                K[1][1] = 0;
                K[1][2] = 2;
                K[2][0] = -1;
                K[2][1] = 0;
                K[2][2] = 1;
                break;
            case 5:
                K[0][0] = -1;
                K[0][1] = -2;
                K[0][2] = 1;
                K[1][0] = -2;
                K[1][1] = 5;
                K[1][2] = -2;
                K[2][0] = 1;
                K[2][1] = -2;
                K[2][2] = 1;
                break;
            default:
                System.out.println("Opcion no valida.");
        }

        //Rellenando la matriz con numeros de entre -20 y 20
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] = rand.nextInt(20 - (-20) + 1) - 20;
            }
        }

        do {
            System.out.print("Introduce el numero de hilos: ");
            nHilos = S.nextInt();
        } while (nHilos <= 0);

        double tiempoInicio = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(nHilos);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                Runnable runnable = new conVolParalela(A, K, i, j);
                pool.execute(runnable);
            }
        }
        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFin = System.currentTimeMillis();
        System.out.println("Tiempo: " + (tiempoFin - tiempoInicio) + " milisegundos.");
    }
}
