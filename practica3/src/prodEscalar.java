package practica3.src;

import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para el producto escalar.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see prodEscalarParalelo.java
 */
public class prodEscalar {
    
    private static int N = 1000000;
    
    /** 
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        
        Random rand = new Random(System.nanoTime());
        
        int[] v1 = new int[N];
        int[] v2 = new int[N];
        
        for(int i = 0; i < N; i++) {
            v1[i] = rand.nextInt(10);
            v2[i] = rand.nextInt(10);
        }
        
        int prod = 0;
        
        double tiempoInicio = System.nanoTime();
        for(int i = 0; i < N; i++) {
            prod += v1[i] * v2[i];
        }
        double tiempoFin = System.nanoTime() - tiempoInicio;
        
        System.out.println("Producto escalar: " + prod);
        System.out.println("Tiempo secuencial: " + tiempoFin/1000000 + " milisegundos.");
    }
}