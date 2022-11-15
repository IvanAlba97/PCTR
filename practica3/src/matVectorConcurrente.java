import java.util.Scanner;
import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para el producto matriz vector concurrentemente.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see matVector.java
 */
public class matVectorConcurrente implements Runnable {

    private static final int N = 10000;
    public static int[][] M = new int[N][N];
    public static int[] V1 = new int[N];
    public static int[] V2 = new int[N];
    private int ini, fin;

    /**
     * Método constructor.
     * @param ini Indica la primera fila de la matriz que la hebra debe ejecutar.
     * @param fin Indica la última fila de la matriz que la hebra debe ejecutar.
     */
    public matVectorConcurrente(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    @Override
    public void run() {
        for (int i = ini; i < fin; i++) {
            for (int j = 0; j < N; j++) {
                V2[j] += M[i][j] * V1[j];
            }
        }
    }
    
    /** 
     * Método principal
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner S = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());
        int nHebras, op;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = rand.nextInt(10);
            }
            V1[i] = rand.nextInt(10);
        }
        
        do {
            System.out.print("Introduce un numero de hebras: ");
            nHebras = S.nextInt();

            double tiempoInicio = System.currentTimeMillis();
            Thread[] V = new Thread[nHebras];
            int rango = (int) (N / nHebras);
            int ini = 0;
            int fin = rango;

            for (int i = 0; i < nHebras; i++) {
                V[i] = new Thread(new matVectorConcurrente(ini, fin));
                ini = fin;
                if(fin + rango >= N) fin = N - 1;
                else fin += rango;
                V[i].start();
            }

            for (int i = 0; i < nHebras; i++) {
                V[i].join();
            }
            double tiempoFin = System.currentTimeMillis() - tiempoInicio;
            System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
            
            System.out.print("Desea realizar alguna otra accion (S/N)?");
            op = S.next().charAt(0);
        } while (op == 's' || op == 'S');
        S.close();
    }

}
