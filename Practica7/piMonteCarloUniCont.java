
/**
 *
 * @author ivan0
 */
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class piMonteCarloUniCont implements Runnable {

    private static int hits = 0;
    private static final Object cerrojo = new Object();

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        double x, y;
        x = rand.nextDouble();
        y = rand.nextDouble();
        if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
            synchronized (cerrojo) {
                hits++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);

        System.out.print("Introduce el numero de intentos: ");
        int nVeces = S.nextInt();
        System.out.print("Introduce el coeficiente de bloqueo [0,1): ");
        double Cb = S.nextDouble();
        
        int nNuc = Runtime.getRuntime().availableProcessors();
        int nHilos = (int) (nNuc / (1 - Cb));
        
        double tiempoInicio = System.currentTimeMillis();
        
        ExecutorService pool = Executors.newFixedThreadPool(nHilos);
        for (int i = 0; i < nVeces; i++) {
            Runnable runnable = new piMonteCarloUniCont();
            pool.execute(runnable);
        }
        pool.shutdown();
        while (!pool.isTerminated());
        
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
        System.out.println("PI = " + (4.0 * hits / nVeces));
    }
}
