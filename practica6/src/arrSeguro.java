public class arrSeguro extends Thread {

    private static final Object cerrojo = new Object();
    public int id;
    public static int[] v;

    public arrSeguro(int i, int[] v) {
        this.id = i;
        arrSeguro.v = v;
    }

    public void run() {
        synchronized(cerrojo) {
            v[id] = id;
        }
    }

    public static void main(String[] args) throws Exception {
        int nHebras = 10;
        int[] v = new int[nHebras];
        Thread[] hilos = new Thread[nHebras];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new arrSeguro(i, v));
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }
    }
}


/* 
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ficheroSeguro implements Runnable {

    private int id;
    private String[] frase;
    private static final Object cerrojo = new Object();

    public ficheroSeguro(int id, String[] frase) {
        this.id = id;
        this.frase = frase;
    }

    @Override
    public void run() {
        synchronized (cerrojo) {
            try {
                RandomAccessFile file;
                File f = new File("ficheroSeguro.txt");
                long fileLenght = f.length();
                file = new RandomAccessFile("ficheroSeguro.txt", "rw");
                file.seek(fileLenght);
                file.writeBytes(frase[id]);
                file.close();
            } catch (IOException E) {
                System.err.println(E.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String[] frase = new String[100];

        for (int i = 0; i < frase.length; i++) {
            frase[i] = "Soy el hilo " + i + ".\r\n";
        }

        Thread[] hilos = new Thread[frase.length];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new ficheroSeguro(i, frase));
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }
    }
} */