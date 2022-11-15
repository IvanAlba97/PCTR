import java.util.concurrent.locks.ReentrantLock;

public class cCRL {

    private ReentrantLock cerrojo = new ReentrantLock();
    private double saldo;

    public cCRL() { this.saldo = 0.0; }

    public double getSaldo() { return saldo; }

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
