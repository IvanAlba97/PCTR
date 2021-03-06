
/**
 *
 * @author ivan0
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class filoApiAN {

    private final int N = 5;
    private int fork[];
    private final ReentrantLock cerrojo;
    private Condition OKtoEat[];

    filoApiAN() {
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

    public void takeForks(int hilo) {
        cerrojo.lock();
        try {
            while (fork[hilo % N] != 2) {
                this.OKtoEat[hilo % N].await();
            }
            this.fork[(hilo + 1) % N] = fork[(hilo + 1) % N] - 1;
            this.fork[(hilo - 1) % N] = fork[(hilo - 1) % N] - 1;
            System.out.println("El filosofo " + hilo + " ha cogido un tenedor.");
        } catch (InterruptedException E) {
            System.err.println(E.getMessage());
        } finally {
            cerrojo.unlock();
        }
    }

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
