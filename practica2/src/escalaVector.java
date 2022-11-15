import java.util.Scanner;
import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para escalar un vector de forma secuencial
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class escalaVector {
    
    /** 
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());

        System.out.print("Introduce el tamanno del vector: ");
        int tam = S.nextInt();
        System.out.print("Introduce el valor del escalar: ");
        int escalar = S.nextInt();
        
        int[] V = new int[tam];
        
        for(int i = 0; i < V.length; i++) {
            V[i] = rand.nextInt(10);
            System.out.print(V[i] + " ");
        }
        System.out.println();

        for(int i = 0; i < V.length; i++) {
            V[i] *= escalar;
        }

        for(int i = 0; i < V.length; i++) {
            System.out.print(V[i] + " ");
        }
        S.close();
    }
}