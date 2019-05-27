
/**
 *
 * @author ivan0
 */
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.concurrent.*;

public class usaMonitorImpresion implements Runnable {

    private final monitorImpresion M;
    private static final int nImpresoras = 3;
    private final int id;

    public usaMonitorImpresion(int id) {
        this.id = id;
        M = new monitorImpresion();
    }

    @Override
    public void run() {
        while (true) {
            try {
                M.takePrint(id);
                sleep(1000);
                M.dropPrint(id);
                sleep(2000);
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce el numero de procesos: ");
        int nProc = S.nextInt();
        int nHilos = (int)(Runtime.getRuntime().availableProcessors()/1-0.5);

        ExecutorService pool = Executors.newFixedThreadPool(nProc); //Aqui deberia de poner nHilos , pero no funciona asi
        for (int i = 0; i < nProc; i++) {
            Runnable runnable = new usaMonitorImpresion(i);
            pool.execute(runnable);
        }
        pool.shutdown();
        while (!pool.isTerminated());
    }
}
