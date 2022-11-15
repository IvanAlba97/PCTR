import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para el producto de matrices de manera secuencial.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see prodMatricesParalelo.java
 */
public class prodMatricesSecuencial {

    private static final int N = 1000;
    
    /** 
     * Método principal.
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());

        double[][] A = new double[N][N];
        double[][] B = new double[N][N];
        double[][] C = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = rand.nextDouble();
                B[i][j] = rand.nextDouble();
            }
        }

        double tiempoInicio = System.nanoTime();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] + B[k][j];
                }
            }
        }

        double tiempoFin = System.nanoTime() - tiempoInicio;
        System.out.println("Tiempo: " + tiempoFin / 1000000 + " milisegundos.");
    }
}