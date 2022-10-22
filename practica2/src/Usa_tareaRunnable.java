package practica2.src;

import java.util.Scanner;

/**
 * Esta clase contiene el método principal para crear objetos del tipo tareaRunnable
 * @author Iván Alba Gómez
 * @version 3.0
 * @see tareaRunnable
 */
public class Usa_tareaRunnable {
    
    /** 
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        
        Scanner S = new Scanner(System.in);
        
        System.out.print("Introduce el numero de iteraciones: ");
        int nIter = S.nextInt();
        
        Runnable r1 = new tareaRunnable(0, nIter);
        Thread h1 = new Thread(r1);
        Runnable r2 = new tareaRunnable(1, nIter);
        Thread h2 = new Thread(r2);
        
        h1.start();
        h2.start();

        try { 
            h1.join();
            h2.join();
        } catch(InterruptedException E) {
            System.err.println(E.getStackTrace());
        }

        System.out.println("El valor final de n es " + ((tareaRunnable) r1).getN());

        S.close();
    }
}