import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase utiliza objetos de la clase ReentrantLock para proveer de exclusión mútua a una variable compartida.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see ccSem.java
 */
public class cCRL {

    private ReentrantLock cerrojo = new ReentrantLock();
    private double saldo;

    /**
     * Método constructor.
     */
    public cCRL() { this.saldo = 0.0; }

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
        cerrojo.lock();
        try{
            saldo += cantidad;
            System.out.println("Se ha aumentado el saldo de la cuenta en " + cantidad + " unidades.");
            System.out.println("Saldo actual: " + saldo);
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método modificador.
     * @param cantidad Cantidad a decrementar en la variable saldo.
     */
    public void decrementar(double cantidad) {
        cerrojo.lock();
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
            cerrojo.unlock();
        }
    }
}
