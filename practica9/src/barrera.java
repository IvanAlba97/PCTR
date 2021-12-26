import java.util.concurrent.CyclicBarrier;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class barrera implements Runnable {

    CyclicBarrier natacion = null;
    CyclicBarrier ciclismo = null;
    CyclicBarrier correr = null;
    private static double[] tiempos = new double[100];
    private int id;

    public barrera(CyclicBarrier natacion, CyclicBarrier ciclismo, CyclicBarrier correr, int i) {
        this.natacion = natacion;
        this.ciclismo = ciclismo;
        this.correr = correr;
        this.id = i;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        try {
            double tiempoInicio = System.nanoTime();
            Thread.sleep(rand.nextInt(1000));
            double tiempoFin = System.nanoTime() - tiempoInicio;
            tiempos[id] = tiempoFin;
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de natacion.");
            natacion.await();
            tiempoInicio = System.nanoTime();
            Thread.sleep(rand.nextInt(1000));
            tiempoFin = System.nanoTime() - tiempoInicio;
            tiempos[id] += tiempoFin;
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de ciclismo.");
            ciclismo.await();
            tiempoInicio = System.nanoTime();
            Thread.sleep(rand.nextInt(1000));
            tiempoFin = System.nanoTime() - tiempoInicio;
            tiempos[id] += tiempoFin;
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de correr.");
            correr.await();
            System.out.println(Thread.currentThread().getName() + " ha terminado.");
        } catch (InterruptedException | BrokenBarrierException E) {
            System.err.println(E.getMessage());
        }

    }

    public static void main(String[] args) {

        CyclicBarrier natacion = new CyclicBarrier(100);
        CyclicBarrier ciclismo = new CyclicBarrier(100);
        CyclicBarrier correr = new CyclicBarrier(100);
        Thread[] hilos = new Thread[100];
        for (int i = 0; i < 100; i++) {
            hilos[i] = new Thread(new barrera(natacion, ciclismo, correr, i));
            hilos[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
            }
        }

        for(int i=0;i<tiempos.length;i++) tiempos[i] /= 1000000;
        
        double t = 1000000;
        int aux = -1;
        for (int i = 0; i < tiempos.length; i++) {
            if (tiempos[i] < t) {
                t = tiempos[i];
                aux = i;
            }
        }
        System.out.println("Hilo ganador: " + aux);
        System.out.println("Tiempo: " + t + " milisegundos.");
    }
}
