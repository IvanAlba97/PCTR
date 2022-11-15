/**
 * Esta clase contiene los atributos y metodos para la creación de hilos que ejecuten tareas de incremento.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see heterogenea.java
 */
public class usaHeterogenea extends Thread {
    
    public int tipo;
    public static heterogenea het = new heterogenea();

    /**
     * Método constructor.
     */
    public usaHeterogenea(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    public void run() {
        switch(tipo) {
            case 0: het.incN();
            case 1: het.incM();
        }
    }
    
    /**
     * Método principal.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int nHebras = 1000;
        Thread[] hilos = new Thread[nHebras];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new usaHeterogenea(i % 2));      // Par: tipo 0     Impar: tipo 1
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        System.out.println("n = " + het.getN());
        System.out.println("m = " + het.getM());
    }
}
