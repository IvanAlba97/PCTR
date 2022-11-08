package practica5.src;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Esta clase contiene los atributos y metodos para el cálculo de números perfectos de manera concurrente.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see numPerfectos.java
 */
public class numPerfectosParalelo implements Callable {

    private int lInf, lSup;

    public numPerfectosParalelo(int ini, int sup) {
        this.lInf = ini;
        this.lSup = sup;
    }

    
    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     * @return numPerfectos
     */
    public Integer call() {
        int numPerfectos = 0;
        for(int i = lInf; i <= lSup; i++) {
            int suma = 0;
            for(int j = 1; j < i; j++) if(i % j == 0) suma += j;
            if(i == suma) numPerfectos += 1;
        }
        return numPerfectos;
    }

    
    /** 
     * Método principal.
     * @param args
     */
    public static void main(String[] args) {
        int lInf, lSup, ventana, intervalo;
        Scanner S = new Scanner(System.in);

        int nNuc = Runtime.getRuntime().availableProcessors();
        int cont = 0;
        System.out.print("Introduce la longitud del intervalo: 1 - ");
        intervalo = S.nextInt();
        lInf = 1;
        ventana = intervalo / nNuc;
        lSup = ventana;

        Vector<Future<Integer>> vec = new Vector<Future<Integer>>();
        long inicTiempo = System.nanoTime();
        ExecutorService pool = Executors.newFixedThreadPool(nNuc);
        for (int i = 0; i < nNuc; i++) {
            Future<Integer> fut = pool.submit(new numPerfectosParalelo(lInf, lSup));
            lInf += lSup;
            lSup += ventana;
            if (lSup + ventana == intervalo) {
                lSup = intervalo;
            }
            vec.add(fut);
        }
        pool.shutdown();

        for (int i = 0; i < nNuc; i++) {
            Future<Integer> fut = vec.get(i);
            try {
                cont += fut.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e6;

        System.out.println("Numeros perfectos = " + cont);
        System.out.println("Tiempo: " + tiempoTotal + " milisegundos.");

        S.close();
    }
}
