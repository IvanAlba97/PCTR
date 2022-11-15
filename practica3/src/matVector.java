import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para el producto matriz vector.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see matVectorConcurrente.java
 */
public class matVector {    
    /** 
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        
        int N = 100000;
        int[][] M = new int[N][N];
        int[] V1 = new int[N];
        int[] V2 = new int[N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                M[i][j] = rand.nextInt(10);
            }
            V1[i] = rand.nextInt(10);
        }
        
        double tiempoInicio = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                V2[i] += M[i][j] * V1[j];
            }
        }
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
    }
}