package src;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class prodMatricesParalelo implements Runnable {

    private static final int N = 1000;
    private static double[][] A, B, C;
    private static final int nNuc = Runtime.getRuntime().availableProcessors();
    int n;

    public prodMatricesParalelo(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] + B[k][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());
        A = new double[N][N];
        B = new double[N][N];
        C = new double[N][N];

        System.out.print("Introduce el coeficiente de bloqueo (0,1]: ");
        int cB = S.nextInt();

        int Subramanian = (int) (nNuc / (1 - cB));
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = rand.nextDouble();
                B[i][j] = rand.nextDouble();
            }
        }

        double tiempoInicio = System.nanoTime();

        ExecutorService pool = Executors.newFixedThreadPool(Subramanian);
        for (int i = 0; i < N; i++) {
            Runnable runnable = new prodMatricesParalelo(i);
            pool.execute(runnable);
        }
        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFin = System.nanoTime() - tiempoInicio;
        System.out.println("Tiempo: " + tiempoFin / 1000000 + " milisegundos.");
        
        S.close();
    }
}