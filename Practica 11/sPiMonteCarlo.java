
/**
 *
 * @author ivan0
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {

    ReentrantLock cerrojo = new ReentrantLock();
    private static double PI = 0;
    private static double puntosTotales = 0;
    private static double hits = 0;

    public sPiMonteCarlo() throws RemoteException {}

    @Override
    public void anadirPuntos(int n) throws RemoteException {
        Random rand = new Random(System.nanoTime());
        cerrojo.lock();
        try {
            for (int i = 0; i < n; i++) {
                double cx = rand.nextDouble();
                double cy = rand.nextDouble();
                if (Math.pow(cx, 2) + Math.pow(cy, 2) <= 1) {
                    hits++;
                }
            }
            puntosTotales += n;
            PI = 4.0 * hits / puntosTotales;
        } finally {
            cerrojo.unlock();
        }
        System.out.println("Se han aportado " + n + " puntos al calculo de PI.");
    }

    @Override
    public void reset() throws RemoteException {
        cerrojo.lock();
        try {
            PI = 0;
            puntosTotales = 0;
        } finally {
            cerrojo.unlock();
        }
        System.out.println("Se ha reseteado el calculo de PI.");
    }

    @Override
    public double PI() throws RemoteException {
        return PI;
    }

    public static void main(String[] args) throws Exception {
        iPiMonteCarlo ORemoto = new sPiMonteCarlo();
        Naming.bind("Servidor", ORemoto);
        System.out.println("Servidor remoto preparado.");
    }
}
