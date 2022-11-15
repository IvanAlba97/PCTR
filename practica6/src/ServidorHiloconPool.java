import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Esta clase contiene los atributos y metodos para el manejo de clientes.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see clienteMultiple.java
 */
public class ServidorHiloconPool implements Runnable{
    Socket enchufe;
    int id;
    
    /**
     * Método constructor.
     * @param s
     * @param i
     */
    public ServidorHiloconPool(Socket s, int i){
        enchufe = s;
        id = i;
    }
    
    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    @Override
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.parseInt(datos);
            for(j = 1; j <= 20; j++){
                System.out.println("El hilo "+ id +" esta escribiendo el dato "+i);
            }
            enchufe.close();
            System.out.println("El hilo "+ id +" cierra su conexion");
        }catch(Exception e){System.out.println("Error");}
    }
    
    
    /** 
     * Método principal.
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        int puerto = 2001;
        double tiempoInicio = System.nanoTime();
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);
            while(true) {
                System.out.println("Esperando solicitud de conexión...");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexión...");
                pool.execute(new ServidorHiloconPool(cable, i));
                i++;
            }
        } catch(IOException e) { System.out.println("Error en los sockets..."); }
        double tiempoFin = System.nanoTime() - tiempoInicio;
        System.out.println("TIEMPO = " + tiempoFin/1000000 + " milisegundos.");
    }
}