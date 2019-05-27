
/**
 *
 * @author ivan0
 */
import java.net.*;
import java.io.*;

public class ServidorHilosinPool implements Runnable {

    Socket enchufe;
    int nombre;

    public ServidorHilosinPool(Socket s, int i) {
        enchufe = s;
        nombre = i;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; j++) {
                System.out.println("El hilo " + nombre + " esta escribiendo el dato " + i);
            }
            enchufe.close();
            System.out.println("El hilo " + nombre + " cierra su conexion");
        } catch (Exception E) {
            System.err.println(E.getMessage());
        }
    }

    public static void main(String[] args) {
        int i = 0;
        int puerto = 2001;
        //double tiempoInicio = System.currentTimeMillis();
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);
            while (true) {
                System.out.println("Esperando solicitud de conexion");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion");
                new Thread(new ServidorHilosinPool(cable, i)).start();
                i++;
            }
        } catch (IOException E) {
            System.out.println(E.getMessage());
        }
        //double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        //System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
    }
}
