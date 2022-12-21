import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase contiene los atributos y metodos para la comparación de tiempos de las distintas formas de proveer exclusión mútua.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class tiempos  {

	/**
	 * Método constructor.
	 */
	public tiempos() {}
	
	/**
	 * Método principal.
	 */
	public static void main(String[] args) {

		long iteraciones = 100000000;
		
		// SYNCHRONIZED
        long n1 = 0;
		Object lock = new Object();
		double tiempoInicio = System.nanoTime();
        for(int i = 0; i < iteraciones; i++) synchronized(lock) { n1++; }
		double tiempoFin = System.nanoTime() - tiempoInicio;
		System.out.println("SYNCHRONIZED: " + tiempoFin / 1000000 + " milisegundos");
		
		// SEMAPHORE
		long n2 = 0;
		Semaphore sem = new Semaphore(1);
		tiempoInicio = System.nanoTime();
        for(int i = 0; i < iteraciones; i++) {
            try{ sem.acquire(); } catch (InterruptedException E) { E.printStackTrace(); }
            n2++;
            sem.release(); 
        }
		tiempoFin = System.nanoTime() - tiempoInicio;
		System.out.println("SEMAPHORE: " + tiempoFin / 1000000 + " milisegundos");
		
		// REENTRANTLOCK
		long n3 = 0;
		ReentrantLock cerrojo = new ReentrantLock();
		tiempoInicio = System.nanoTime();
        for(int i = 0; i < iteraciones; i++) {
            cerrojo.lock();
            n3++;
            cerrojo.unlock();
        }
		tiempoFin = System.nanoTime() - tiempoInicio;
		System.out.println("REENTRANTLOCK: " + tiempoFin / 1000000 + " milisegundos");
		
		// ATOMIC
		AtomicInteger n4 = new AtomicInteger(0);
		tiempoInicio = System.nanoTime();
        for(int i = 0; i < iteraciones; i++) n4.incrementAndGet();
		tiempoFin = System.nanoTime() - tiempoInicio;
		System.out.println("ATOMIC: " + tiempoFin / 1000000 + " milisegundos");
	}
}