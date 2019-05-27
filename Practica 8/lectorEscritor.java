
/**
 *
 * @author ivan0
 */
public class lectorEscritor {

    private int n;  //Numero de lectores leyendo
    private boolean escribiendo;

    public lectorEscritor() {
        n = 0;
        escribiendo = false;
    }

    public synchronized void iniciaLectura(int i) throws InterruptedException {
        while (escribiendo) {
            wait();
            System.out.println("Lector " + i + " esperando.");
            n++;
            System.out.println("Lector " + i + " leyendo.");
        }
    }

    public synchronized void finLectura(int i) {
        n--;
        System.out.println("Lector " + i + " deja de leer.");
        notifyAll();
    }

    public synchronized void iniciaEscritura(int i) throws InterruptedException {
        while (n > 0 || escribiendo) {  //Mientras haya algun lector o alguien este escribiendo
            wait();
            System.out.println("Escritor " + i + " esperando.");
        }
        escribiendo = true;
        System.out.println("Escritor " + i + " escribiendo.");
    }

    public synchronized void finEscritura(int i) {
        escribiendo = false;
        System.out.println("Escritor " + i + " deja de escribir.");
        notifyAll();
    }
}
