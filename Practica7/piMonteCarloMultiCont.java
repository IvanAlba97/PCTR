
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author ivan0
 */
public class piMonteCarloMultiCont implements Runnable{
    private double nPuntos;
    private int hits = 0;

    public piMonteCarloMultiCont(int total) {
        this.nPuntos = total;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        double x, y;
        for (int i = 0; i < nPuntos; i++) {
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                hits++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner S = new Scanner(System.in);
        int nNuc = Runtime.getRuntime().availableProcessors();
        System.out.print("Introduce el numero de puntos: ");
        int nPuntos = S.nextInt();
        Thread[] hilos = new Thread[nNuc];
        piMonteCarloMultiCont[] tareas = new piMonteCarloMultiCont[hilos.length];
        double tiempoInicio = System.currentTimeMillis();
        for (int i = 0; i < hilos.length; i++) {
            tareas[i] = new piMonteCarloMultiCont(nPuntos / hilos.length);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }

        double hitsTotal = 0;

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
            hitsTotal += tareas[i].hits;
        }

        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("PI = " + 4 * (double) (hitsTotal) / nPuntos);
        System.out.println("Timepo trascurrido: " + tiempoFin + " milisegundos.");
    }
}
