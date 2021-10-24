package PCTR.practica2.src;

import java.util.Scanner;
import java.util.Random;

public class escalaVector {
    
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
            V[i] *= escalar;
        }
        S.close();
    }
}