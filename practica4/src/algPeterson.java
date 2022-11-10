package practica4.src;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Esta clase contiene los atributos y metodos del algoritmo de Peterson.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class algPeterson implements Runnable {
    private static boolean wantp = false, wantq = false;
    private static int last = 1;
    static volatile int enteroCompartido = 0;
    static final int iteraciones = 10000;

    /**
     * Método constructor.
     */
    public algPeterson() {}

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("pool-1-thread-1")) P();
        if(Thread.currentThread().getName().equals("pool-1-thread-2")) Q();
    }

    /**
     * Este método representa un proceso dentro del algoritmo de Peterson.
     */
    public void P() {
        for(int i = 0; i < iteraciones; i++) {
            wantp = true;
            last = 1;
            if(wantq = false || last == 2) Thread.yield();
            wantp = false;
            enteroCompartido++;
            System.out.println(enteroCompartido);
        }
    }

    /**
     * Este método representa un proceso dentro del algoritmo de Peterson.
     */
    public void Q() {
        for(int i = 0; i < iteraciones; i++) {
            wantq = true;
            last = 2;
            if(wantp = false || last == 1) Thread.yield();
            wantq = false;
            enteroCompartido--;
            System.out.println(enteroCompartido);
        }
    }

    
    /** 
     * Método principal.
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 2; i++) pool.execute(new algPeterson());
        pool.shutdown();
        while(!pool.isTerminated()) {}
        System.out.println("El valor del recurso compartido es " + enteroCompartido);
        System.out.println("Deberia ser 0");
    }
}

