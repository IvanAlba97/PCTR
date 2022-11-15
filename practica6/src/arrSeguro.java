/**
 * Esta clase contiene los atributos y metodos para el manejo de array seguro.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class arrSeguro extends Thread {

    private static final Object cerrojo = new Object();
    public int id;
    public static int[] v;

    /**
     * Método constructor.
     * @param i
     * @param v
     */
    public arrSeguro(int i, int[] v) {
        this.id = i;
        arrSeguro.v = v;
    }

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    public void run() {
        synchronized(cerrojo) {
            v[id] = id;
        }
    }

    
    /** 
     * Método principal
     * @param args
     * @throws Exception
     */
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