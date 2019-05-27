
/**
 *
 * @author ivan0
 */
import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

public class atomicServer implements Runnable {

    private static AtomicInteger contador = new AtomicInteger(0);
    Socket enchufe;

    public atomicServer(Socket enchufe) {
        this.enchufe = enchufe;
    }

    @Override
    public void run() {
        try {
            contador.incrementAndGet();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int i = Integer.parseInt(datos);
            for (int j = 1; j <= 20; j++) {
                System.out.println("El hilo " + new Thread(this).getName() + " esta escribiendo el dato " + i);
            }
            enchufe.close();
            System.out.println("El hilo " + new Thread(this).getName() + " cierra su conexion.");
        } catch (IOException E) {
            System.err.println(E.getMessage());
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
                pool.execute(new atomicServer(cable));
            }
        } catch (IOException E) {
            System.err.println(E.getMessage());
        }
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
    }
}
