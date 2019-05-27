
/**
 *
 * @author ivan0
 */
import java.util.concurrent.CyclicBarrier;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class triatlonBarreras implements Runnable{
    
    CyclicBarrier natacion = null;
    CyclicBarrier ciclismo = null;
    CyclicBarrier correr = null;
        
    public triatlonBarreras(CyclicBarrier natacion, CyclicBarrier ciclismo, CyclicBarrier correr){
        this.natacion = natacion;
        this.ciclismo = ciclismo;
        this.correr = correr;
    }
    
    @Override
    public void run(){
        Random rand = new Random(System.nanoTime());
        double tiempoInicio = System.currentTimeMillis();
        try{
            Thread.sleep(rand.nextInt(1000));
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de natacion.");
            natacion.await();
            Thread.sleep(rand.nextInt(1000));
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de ciclismo.");
            ciclismo.await();
            Thread.sleep(rand.nextInt(1000));
            System.out.println(Thread.currentThread().getName() + " esperando en la barrera de correr.");
            correr.await();
            System.out.println(Thread.currentThread().getName() + " ha terminado.");
        }catch(InterruptedException | BrokenBarrierException E){
            System.err.println(E.getMessage());
        }
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("TIEMPOdel *.class"
                + " " + Thread.currentThread().getName() + ": " + tiempoFin + " milisegundos.");
    }
    
    public static void main(String[] args){
        
        CyclicBarrier natacion = new CyclicBarrier(100);
        CyclicBarrier ciclismo = new CyclicBarrier(100);
        CyclicBarrier correr = new CyclicBarrier(100);
        
        for(int i=0;i<100;i++){
            new Thread(new triatlonBarreras(natacion, ciclismo, correr)).start();
        }
    }
}
