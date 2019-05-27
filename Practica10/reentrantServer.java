
/**
 *
 * @author ivan0
 */
import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class reentrantServer implements Runnable {

    Socket enchufe;
    public static int contador;
    private static final ReentrantLock cerrojo = new ReentrantLock();

    public reentrantServer(Socket s) {
        this.enchufe = s;
    }

    @Override
    public void run() {
        cerrojo.lock();
        contador++;
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int i = Integer.parseInt(datos);
            for (int j = 1; j <= 20; j++) {
                System.out.println("El hilo " + new Thread(this).getName() + " esta escribiendo el dato " + i);
                enchufe.close();
                System.out.println("El hilo " + new Thread(this).getName() + " cierra su conexion.");
            }
        } catch (IOException E) {
            System.err.println(E.getMessage());
        } finally {
            cerrojo.unlock();
        }
    }

    public static void main(String[] args) {
        int puerto = 2001;
        double tiempoInicio = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);
            while (true) {
                System.out.println("Esperando solicitud de conexion.");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion.");
                pool.execute(new reentrantServer(cable));
            }
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo: " + tiempoFin + " milisegundos.");
    }
}
