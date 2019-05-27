
/**
 *
 * @author ivan0
 */
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class intParaleloFutureCont implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int hits = 0;
        double x, y;
        Random rand = new Random(System.nanoTime());
        x = rand.nextDouble();
        y = rand.nextDouble();
        if (y < Math.sin(x)) {
            hits++;
        }
        return hits;
    }

    public static void main(String[] args) {
        int nPuntos, hits = 0;
        double res;
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce el numero de puntos: ");
        nPuntos = S.nextInt();
        int nNuc = Runtime.getRuntime().availableProcessors();

        double tiempoInicio = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(nNuc);
        Vector<Future<Integer>> vec = new Vector();

        for (int i = 0; i < nPuntos; i++) {
            Future<Integer> fut = executor.submit(new intParaleloFutureCont());
            vec.add(fut);
        }
        executor.shutdown();
        for (int i = 0; i < nPuntos; i++) {
            Future<Integer> fut = vec.get(i);
            try {
                hits += fut.get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(intParaleloFutureCont.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        double tiempoFin = System.currentTimeMillis() - tiempoInicio;

        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
        res = (double) hits / nPuntos;
        System.out.println("Integral = " + res);

    }
}
