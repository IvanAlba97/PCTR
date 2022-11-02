package practica3.src;

import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos para el producto escalar paralelo.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see prodEscalar.java
 */
public class prodEscalarParalelo extends Thread {
    
    private static final int N = 1000000;
    private int idHebra, inicio, fin;
    private static int[] v1 = new int[N], v2 = new int[N];
    private static int[] productoParcial;
    
    /**
     * Método constructor
     * @param idHebra Identificador de la hebra.
     * @param inicio Variable que indica la primera posición del vector que la hebra debe ejecutar.
     * @param fin Variable que indica la última posición del vector que la hebra debe ejecutar.
     * @param v1 Vector 1
     * @param v2 Vector 2
     */
    public prodEscalarParalelo(int idHebra, int inicio, int fin, int[] v1, int[] v2) {
        this.idHebra = idHebra;
        this.inicio = inicio;
        this.fin = fin;
    }
    
    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    @Override
    public void run() {
        for(int i = inicio; i < fin; i++) {
            productoParcial[idHebra] = v1[i] * v2[i];
        }
    }
    
    /** 
     * Método principal
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random(System.nanoTime());
        Scanner S = new Scanner(System.in);
        
        int inicio, fin, rango;
        
        for(int i = 0; i < N; i++) {
            v1[i] = rand.nextInt(10);
            v2[i] = rand.nextInt(10);
        }
        
        System.out.print("Introduce el numero de hebras: ");
        int nHebras = S.nextInt();

        productoParcial = new int[nHebras];
        rango = (int) (N / nHebras);
        inicio = 0;
        fin = rango;
        double tiempoInicio = System.nanoTime();
        prodEscalarParalelo[] vector = new prodEscalarParalelo[nHebras];
        for(int i = 0; i < nHebras; i++) {
            vector[i] = new prodEscalarParalelo(i, inicio, fin, v1, v2);
            vector[i].start();
            inicio = fin;
            if(fin + rango >= N) fin = N - 1;
            else fin += rango;
        }
        for(int i = 0; i < nHebras; i++) vector[i].join();
        double tiempoFin = System.nanoTime() - tiempoInicio;
        
        int res = 0;
        for(int i = 0; i < productoParcial.length; i++) res += productoParcial[i];
        System.out.println("Producto escalar paralelo: " + res);
        System.out.println("Tiempo paralelo: " + tiempoFin/1000000 + " milisegundos.");
        S.close();
    }
    
}