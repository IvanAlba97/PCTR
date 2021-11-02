package PCTR.practica3.src;

import java.util.Random;

public class matVector {    
   
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