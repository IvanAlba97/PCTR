package practica2.src;

import java.util.Scanner;

/**
 * Esta clase contiene el método principal para crear y ejecutar hebras
 * @author Iván Alba Gómez
 * @version 3.0
 * @see hebra
 */
public class Usa_hebra {

    /**
     * Método principal
     */
    public static void main(String[] args) {
        
        Scanner S = new Scanner(System.in);
        
        System.out.print("Introduce el numero de iteraciones: ");
        int nIter = S.nextInt();

        Thread hebra[] = new Thread[10];
        
        for(int i = 0; i < 5; i++) {
            hebra[i] = new hebra(0, nIter);
            hebra[i].start();
        }
        for(int i = 5; i < 10; i++) {
            hebra[i] = new hebra(1, nIter);
            hebra[i].start();
        }

        for(int i = 0; i < 10; i++) {
            try{
                hebra[i].join();
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        
        System.out.println("El valor final de n es " + ((hebra) hebra[0]).getN());

        S.close();
    }
}
