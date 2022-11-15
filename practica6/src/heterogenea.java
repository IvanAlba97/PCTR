/**
 * Esta clase contiene los atributos y metodos para el manejo de array seguro.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class heterogenea extends Thread {

    public static int n = 0, m = 0;
    public int tipo;

    /**
     * Método constructor.
     * @param tipo
     */
    public heterogenea(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    public void run() {
        switch(tipo) {
            case 0: incN();
            case 1: incM();
        }
    }

    /**
     * Método modificador.
     */
    public synchronized void incN() { n++; }

    /**
     * Método modificador.
     */
    public void incM() { m++; }

    /**
     * Método principal.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int nHebras = 1000;
        Thread[] hilos = new Thread[nHebras];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new heterogenea(i % 2));      // Par: tipo 0     Impar: tipo 1
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        System.out.println("n = " + n);
        System.out.println("m = " + m);
    }
}
