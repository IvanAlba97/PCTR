
/**
 *
 * @author ivan0
 */
import java.util.Random;

public class usaConductores extends Thread {

    private static Conductores conductores;
    private int hilo, sumaPuntos, quitaPuntos;

    public usaConductores(int hilo, int sumaPuntos, int quitaPuntos) {
        this.hilo = hilo;
        this.sumaPuntos = sumaPuntos;
        this.quitaPuntos = quitaPuntos;
    }

    @Override
    public void run() {
        conductores.sumarPuntoConductor(conductores.obtenerConductor(0), sumaPuntos);
        conductores.sumarPuntoConductor(conductores.obtenerConductor(1), sumaPuntos);
        conductores.sumarPuntoConductor(conductores.obtenerConductor(2), sumaPuntos);
        conductores.quitarPuntosConductor(conductores.obtenerConductor(0), quitaPuntos);
        conductores.quitarPuntosConductor(conductores.obtenerConductor(1), quitaPuntos);
        conductores.quitarPuntosConductor(conductores.obtenerConductor(2), quitaPuntos);
    }

    public static void main(String[] args) {

        Random rand = new Random(System.nanoTime());
        conductores = new Conductores();
        Conductor ivan = new Conductor("12345678A", "Iván", "Alba Gómez", 15);
        Conductor diego = new Conductor("12345678B", "Diego", "Vela García", 12);
        Conductor juan = new Conductor("12345678C", "Juan", "Pallares Rojas", 10);

        int nHilos = 100;
        conductores.agregarConductor(ivan);
        conductores.agregarConductor(diego);
        conductores.agregarConductor(juan);

        usaConductores[] hilos = new usaConductores[nHilos];
        for (int i = 0; i < hilos.length; i++) {
            int addPuntos = rand.nextInt(5);
            int resPuntos = rand.nextInt(5);
            hilos[i] = new usaConductores(i, addPuntos, resPuntos);
            hilos[i].start();
        }
        for (int i = 0; i < hilos.length; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("Conductor: " + conductores.obtenerConductor(i));
        }
    }
}
