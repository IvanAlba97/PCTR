 /**
     * Esta clase contiene los atributos y metodos para el manejo lectores y escritores.
     * @author Iván Alba Gómez
     * @version 3.0
     * @see recurso.java
     * @see usaLectorEscritor.java
     */
public class lectorEscritor {

    private int n;  // Numero de lectores leyendo concurrentemente
    private boolean escribiendo;    // Indica si hay un escritor activo

    /**
     * Constructor vacío
     */
    public lectorEscritor() {
        n = 0;
        escribiendo = false;
    }

    /**
     * Método para iniciar lectura
     */
    public synchronized void iniciaLectura(int i) throws InterruptedException {
        while (escribiendo) {
            wait();
            System.out.println("Lector " + i + " esperando.");
            n++;
            System.out.println("Lector " + i + " leyendo.");
        }
    }

    /**
     * Método para finalizar lectura
     */
    public synchronized void finLectura(int i) {
        n--;
        System.out.println("Lector " + i + " deja de leer.");
        notifyAll();
    }

    /**
     * Método para iniciar escritura
     */
    public synchronized void iniciaEscritura(int i) throws InterruptedException {
        while (n > 0 || escribiendo) {  // Mientras haya algun lector o alguien este escribiendo
            wait();
            System.out.println("Escritor " + i + " esperando.");
        }
        escribiendo = true;
        System.out.println("Escritor " + i + " escribiendo.");
    }

    /**
     * Método para finalizar escritura
     */
    public synchronized void finEscritura(int i) {
        escribiendo = false;
        System.out.println("Escritor " + i + " deja de escribir.");
        notifyAll();
    }
}