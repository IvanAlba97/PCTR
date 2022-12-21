import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase contiene los atributos y metodos para el control de los tenedores en el problema de los filósofos.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see usaFiloApiAn.java
 */
public class filoApiAN {

    private final int N = 5;
    private int fork[];
    private final ReentrantLock cerrojo;
    private Condition OKtoEat[];

    /**
     * Método constructor.
     */
    public filoApiAN() {
        this.fork = new int[N];
        this.cerrojo = new ReentrantLock();
        this.OKtoEat = new Condition[N];
        for (int i = 0; i < this.fork.length; i++) {
            this.fork[i] = 2;
        }
        for (int i = 0; i < this.OKtoEat.length; i++) {
            this.OKtoEat[i] = cerrojo.newCondition();
        }
    }

    /**
     * Este método se asigna los tenedores de sus lados cuando están disponibles.
     * @param hilo Identificador del hilo
     */
    public void takeForks(int hilo) {
        cerrojo.lock();
        try {
            while (fork[hilo % N] != 2) {
                this.OKtoEat[hilo % N].await();
            }
            this.fork[(hilo + 1) % N] = fork[(hilo + 1) % N] - 1;
            this.fork[(hilo - 1) % N] = fork[(hilo - 1) % N] - 1;
            System.out.println("El filosofo " + hilo + " ha cogido un tenedor.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Este método libera los tenedores de sus lados.
     * @param hilo Identificador del hilo
     */
    public void releaseForks(int hilo) {
        cerrojo.lock();
        try {
            fork[(hilo + 1) % N] = fork[(hilo + 1) % N] + 1;
            fork[(hilo - 1) % N] = fork[(hilo - 1) % N] + 1;
            if (fork[(hilo + 1) % N] == 2) {
                this.OKtoEat[(hilo + 1) % N].signalAll();
            }
            if (fork[(hilo - 1) % N] == 2) {
                this.OKtoEat[(hilo - 1) % N].signalAll();
            }
            System.out.println("El filosofo " + hilo + " ha soltado su tenedor.");
        } finally {
            cerrojo.unlock();
        }
    }
}