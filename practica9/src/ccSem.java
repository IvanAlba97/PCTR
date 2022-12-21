import java.util.concurrent.Semaphore;

/**
 * Esta clase utiliza objetos de la clase Semaphore para proveer de exclusión mútua a una variable compartida.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see cCRL.java
 */
public class ccSem {

    private double saldo;
    public static long iterations = 1000000;
    public static Semaphore sem = new Semaphore(1);

    /**
     * Método constructor.
     */
    public ccSem() { this.saldo = 0.0; }

    /**
     * Método observador.
     * @return saldo Devuelve el saldo actual 
     */
    public double getSaldo() { return saldo; }

    /**
     * Método modificador.
     * @param cantidad Cantidad a aumentar en la variable saldo.
     */
    public void aumentar(double cantidad) {
        for(long i = 0; i < iterations; i++) {
            try{
                sem.acquire();
            } catch(InterruptedException E) {}
        }
        try{
            saldo += cantidad;
            System.out.println("Se ha aumentado el saldo de la cuenta en " + cantidad + " unidades.");
            System.out.println("Saldo actual: " + saldo);
        } finally {
            sem.release();
        }
    }

    /**
     * Método modificador.
     * @param cantidad Cantidad a decrementar en la variable saldo.
     */
    public void decrementar(double cantidad) {
        for(long i = 0; i < iterations; i++) {
            try{
                sem.acquire();
            } catch(InterruptedException E) {}
        }
        try {
            if (saldo >= cantidad) {
                saldo -= cantidad;
                System.out.println("Se ha decrementado el saldo de la cuenta en " + cantidad + " unidades.");
                System.out.println("Saldo actual: " + saldo);
            } else {
                System.out.println("No tiene saldo suficiente.");
                System.out.println("Saldo disponible: " + saldo);
            }
        } finally {
            sem.release();
        }
    }
}
