import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class usaFiloApiAn implements Runnable {

    static filoApiAN monitor = new filoApiAN();
    private int hilo;

    public usaFiloApiAn(int hilo) {
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
        usaFiloApiAn hilos[] = new usaFiloApiAn[5];
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 1; i < hilos.length; i++) {
            hilos[i] = new usaFiloApiAn(i);
            pool.execute(hilos[i]);
        }
    }
}