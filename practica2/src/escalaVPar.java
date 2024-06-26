import java.util.Scanner;
import java.util.Random;

/**
 * Esta clase contiene los atributos y metodos para escalar un vector de forma concurrente
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class escalaVPar implements Runnable {
    
    Random rand = new Random(System.nanoTime());
    private int[] V;
    private int escalar, tipoHilo;
    
    public escalaVPar(int tipoHilo, int tam, int escalar) {
        this.tipoHilo = tipoHilo;
        this.escalar = escalar;
        V = new int[tam];
    }
    
    @Override
    public void run() {
        switch(tipoHilo) {
            case 0:
                for(int i = 0; i < V.length/2; i++) {
                    V[i] = rand.nextInt(10);
                    V[i] *= escalar;
                }
                break;
            case 1:
                for(int i = V.length/2; i < V.length; i++) {
                    V[i] = rand.nextInt(10);
                    V[i] *= escalar;
                }
                break;
            default: System.err.println("Error en el switch.");
        }
        for(int i = 0; i < V.length; i++) {
            V[i] = rand.nextInt(10);
            V[i] *= escalar;
        }
    }
    
    
    /** 
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);

        System.out.print("Introduce el tamanno del vector: ");
        int tam = S.nextInt();
        System.out.print("Introduce el valor del escalar: ");
        int escalar = S.nextInt();
        
        Thread h1 = new Thread(new escalaVPar(0, tam, escalar));
        Thread h2 = new Thread(new escalaVPar(1, tam, escalar));
        h1.start(); h2.start();
        try { h1.join(); h2.join(); } catch(InterruptedException E) {
            System.err.println(E.getMessage());
        }
        S.close();
    }
}