
/**
 *
 * @author ivan0
 */
import java.util.Random;
import java.util.Scanner;

public class intParalelomultiCont implements Runnable {

    private double nPuntos, hits = 0;

    public intParalelomultiCont(int nPuntos) {
        this.nPuntos = nPuntos;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        double x, y;
        for (int i = 0; i < nPuntos; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (Math.sin(x) >= y) {
                hits++;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner S = new Scanner(System.in);
        double hitsTotal = 0;
        int nProc = Runtime.getRuntime().availableProcessors();
        System.out.print("Introduce el numero de puntos: ");
        int nPuntos = S.nextInt();
        
        Thread[] hilos = new Thread[nProc];
        intParalelomultiCont[] tareas = new intParalelomultiCont[hilos.length];
        
        double tiempoInicio = System.currentTimeMillis();
        
        for (int i = 0; i < hilos.length; i++) {
            tareas[i] = new intParalelomultiCont(nPuntos / hilos.length);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
            hitsTotal += tareas[i].hits;
        }
        
        hitsTotal /= nPuntos;
        
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo =  " + tiempoFin + " milisegundos.");
        System.out.println("Integral = " + hitsTotal);

    }
}
