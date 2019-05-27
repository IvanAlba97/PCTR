
/**
 *
 * @author ivan0
 */
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

public class intParalelauniCont implements Runnable {

    private static int hits = 0;
    private static final Object cerrojo = new Object();

    public intParalelauniCont() {}

    @Override
    public void run() {
        double x, y;
        Random rand = new Random(System.nanoTime());
            x = rand.nextDouble();
            y = rand.nextDouble();
            if (y < Math.sin(x)) {
                synchronized (cerrojo) {
                    hits++;
                }
            }
    }

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce el numero de puntos: ");
        int nPuntos = S.nextInt();
        int nNuc = Runtime.getRuntime().availableProcessors();
        System.out.print("Introduce el coeficiente de bloqueo [0,1): ");
        double Cb = S.nextDouble();
        int nHilos = (int) (nNuc / (1 - Cb));

        double tiempoInicio = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(nHilos);

        for (int i = 0; i < nPuntos; i++) {
            Runnable runnable = new intParalelauniCont();
            pool.execute(runnable);
        }

        pool.shutdown();
        while (!pool.isTerminated());

        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
        System.out.println("Integral = " + ((double)hits/nPuntos));
    }
}
