
/**
 *
 * @author ivan0
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaFiloApiAN implements Runnable {

    static filoApiAN monitor = new filoApiAN();
    private int hilo;

    public usaFiloApiAN(int hilo) {
        this.hilo = hilo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                monitor.takeForks(hilo);
                Thread.sleep(2000);
                monitor.releaseForks(hilo);
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        usaFiloApiAN hilos[] = new usaFiloApiAN[5];
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 1; i < hilos.length; i++) {
            hilos[i] = new usaFiloApiAN(i);
            pool.execute(hilos[i]);
        }
    }
}
