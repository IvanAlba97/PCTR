package PCTR.practica3.src;

import java.util.Random;

public class prodEscalar {
    
    private static int N = 1000000;
    
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
        
        System.out.println("Tiempo secuencial: " + tiempoFin/1000000 + " milisegundos.");
    }
}