import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

/**
 * Esta clase interpreta el servidor para el problema del cálculo de PI mediante el método de Monte Carlo.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see iPiMonteCarlo.java
 * @see cPiMonteCarlo.java
 */
public class sPiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo {

    ReentrantLock cerrojo = new ReentrantLock();
    private static double PI = 0;
    private static double puntosTotales = 0;
    private static double hits = 0;

    /**
     * Método constructo.
     */
    public sPiMonteCarlo() throws RemoteException {}

    /**
     * Este método envía puntos al servidor para aproximar a PI.
     */
    @Override
    public void masPuntos(int n) throws RemoteException {
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

    /**
     * Este método resetea el servidor. Establece los valores a 0.
     */
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

    /**
     * Método observador del valor de la aproximación actual de PI.
     * @return PI
     */
    @Override
    public double aproxActual() throws RemoteException {
        return PI;
    }

    /**
     * Método principal.
     */
    public static void main(String[] args) throws Exception {
        iPiMonteCarlo ORemoto = new sPiMonteCarlo();
        Naming.bind("Servidor", ORemoto);
        System.out.println("Servidor remoto preparado.");
    }
}