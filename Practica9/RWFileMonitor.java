
/**
 *
 * @author ivan0
 */
import java.io.IOException;
import java.io.RandomAccessFile;

public class RWFileMonitor {

    private int readers = 0;
    private boolean writing = false;
    RandomAccessFile file;

    public synchronized void startRead(int id) {
        while (writing) {
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        try {
            file = new RandomAccessFile("datos.txt", "rw");
            file.writeBytes("Lector " + id + " inicia lectura.");
            file.close();
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        readers++;
        System.out.println("Lector " + id + " inicia lectura.");
        notifyAll();
    }

    public synchronized void endRead(int id) {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
        try {
            file = new RandomAccessFile("datos.txt", "rw");
            file.writeBytes("Lector " + id + " finaliza lectura.");
            file.close();
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        System.out.println("Lector " + id + " finaliza lectura.");
        notifyAll();
    }

    public synchronized void startWrite(int id) {
        while (writing || readers != 0) {
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        try {
            file = new RandomAccessFile("datos.txt", "rw");
            file.writeBytes("Escritor " + id + " inicia escritura.");
            file.close();
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        writing = true;
        System.out.println("Escritor " + id + " inicia escritura.");
    }

    public synchronized void endWrite(int id) {
        try {
            file = new RandomAccessFile("datos.txt", "rw");
            file.writeBytes("Escritor " + id + " finaliza escritura.");
            file.close();
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        System.out.println("Escritor " + id + " finaliza escritura.");
        writing = false;
        notifyAll();
    }
}
