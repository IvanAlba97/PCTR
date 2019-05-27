
/**
 *
 * @author ivan0
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;

public class piMonteCarloFutureCont implements Callable<Integer> {

    public piMonteCarloFutureCont() {
    }

    @Override
    public Integer call() throws Exception{
        Random rand = new Random(System.nanoTime());
        double x, y;
        int hits = 0;
        x = rand.nextDouble();
        y = rand.nextDouble();
        if(Math.pow(x, 2) + Math.pow(y, 2) <= 1){
            hits++;
        }
        return hits;
    }
    
    public static void main(String[] args) throws Exception{
        Scanner S = new Scanner(System.in);
        int hits = 0;

        System.out.print("Introduce el numero de intentos: ");
        int nVeces = S.nextInt();
        System.out.print("Introduce el coeficiente de bloqueo: ");
        double Cb = S.nextDouble();
        int nNuc = Runtime.getRuntime().availableProcessors();
        int nHilos = (int) (nNuc / (1 - Cb));
        
        double tiempoInicio = System.currentTimeMillis();
        
        ExecutorService pool = Executors.newFixedThreadPool(nHilos);
        Vector<Future<Integer>> vec = new Vector();
        for(int i=0;i<nVeces;i++){
            Future<Integer> fut = pool.submit(new piMonteCarloFutureCont());
            vec.add(fut);
        }
        pool.shutdown();
        for(int i=0;i<nVeces;i++){
            Future<Integer> fut = vec.get(i);
            hits += fut.get();
        }
        
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
        System.out.println("PI = " + (4.0*hits/nVeces));
    }
}
