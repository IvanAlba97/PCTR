import java.util.concurrent.Semaphore;

public class ccSem {

    private double saldo;
    public static long iterations = 1000000;
    public static Semaphore sem = new Semaphore(1);

    public ccSem() { this.saldo = 0.0; }

    public double getSaldo() { return saldo; }

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
