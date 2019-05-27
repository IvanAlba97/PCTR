
/**
 *
 * @author ivan0
 */
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWMonitorAN {

    private final ReentrantLock cerrojo = new ReentrantLock();
    private final Condition escribir = cerrojo.newCondition();
    private final Condition leer = cerrojo.newCondition();
    private static boolean writing = false;
    private static int readers = 0;
    private RandomAccessFile file;

    public void startRead(int id) {
        cerrojo.lock();
        try {
            while (writing) {
                leer.await();
            }
            file = new RandomAccessFile("datos.dat", "r");
            readers++;
            System.out.println("Lector " + id + " inicia lectura.");
            file.read();
            file.close();
        } catch (InterruptedException | IOException E) {
            System.err.println(E.getMessage());
        } finally {
            cerrojo.unlock();
        }
    }

    public void endRead(int id) {
        cerrojo.lock();
        try {
            readers--;
            System.out.println("Lector " + id + " finaliza lectura.");
            leer.signalAll();
            if (readers == 0) {
                escribir.signal();
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void startWrite(int id) {
        cerrojo.lock();
        try {
            while (writing || readers > 0) {
                escribir.await();
            }
            writing = true;
            System.out.println("Escritor " + id + " inicia escritura.");
            file = new RandomAccessFile("daots.dat", "rw");
            file.writeBytes("Escritor " + id + " inicia escritura.");
            file.close();
        } catch (IOException | InterruptedException E) {
            System.err.println(E.getMessage());
        } finally {
            cerrojo.unlock();
        }
    }

    public void endWrite(int id) {
        cerrojo.lock();
        try {
            writing = false;
            System.out.println("Escritor " + id + " finaliza lectura.");
            leer.signalAll();
            escribir.signal();
        } finally {
            cerrojo.unlock();
        }
    }
}
