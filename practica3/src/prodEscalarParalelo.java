package PCTR.practica3.src;

import java.util.Random;
import java.util.Scanner;

public class prodEscalarParalelo extends Thread {
    
    private static final int N = 1000000;
    private int idHebra, inicio, fin;
    private static int[] v1 = new int[N], v2 = new int[N];
    private static int prod = 0;
    
    
    public prodEscalarParalelo(int idHebra, int inicio, int fin, int[] v1, int[] v2) {
        this.idHebra = idHebra;
        this.inicio = inicio;
        this.fin = fin;
    }
    
    @Override
    public void run() {
        for(int i = inicio; i < fin; i++) {
            prod += v1[i] * v2[i];
        }
    }
    
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
        rango = (int) (N / nHebras);
        inicio = 0;
        fin = rango;
        double tiempoInicio = System.nanoTime();
        prodEscalarParalelo[] productoParcial = new prodEscalarParalelo[nHebras];
        for(int i = 0; i < nHebras; i++) {
            productoParcial[i] = new prodEscalarParalelo(i, inicio, fin, v1, v2);
            productoParcial[i].start();
            inicio = fin;
            if(fin + rango >= N) fin = N - 1;
            else fin += rango;
        }
        for(int i = 0; i < nHebras; i++) {
            productoParcial[i].join();
        }
        double tiempoFin = System.nanoTime() - tiempoInicio;
        System.out.println("Tiempo paralelo: " + tiempoFin/1000000 + " milisegundos.");
        S.close();
    }
    
}