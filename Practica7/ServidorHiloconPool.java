
/**
 * 
 * @author ivan0
 */

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ServidorHiloconPool implements Runnable{
    Socket enchufe;
    int id;

    public ServidorHiloconPool(Socket s, int i){
        enchufe = s;
        id = i;
    }

    @Override
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.parseInt(datos);
            for(j=1; j<=20; j++){
                System.out.println("El hilo "+ id +" esta escribiendo el dato "+i);
            }
            enchufe.close();
            System.out.println("El hilo "+ id +" cierra su conexion");
        }catch(Exception E){System.out.println("Error");}
    }

    public static void main(String[] args){
        int i = 0;
        int puerto = 2001;
        long tiempoInicio, tiempoFinal;
        tiempoInicio = System.nanoTime();
        ExecutorService pool = Executors.newCachedThreadPool();
        try{
            ServerSocket chuff = new ServerSocket (puerto, 3000);
            while (true){
                System.out.println("Esperando solicitud de conexion");
                Socket cable = chuff.accept();
                System.out.println("Recibida solicitud de conexion");
                pool.execute(new ServidorHiloconPool(cable,i));
                i++;
            }
        }catch(Exception E){System.out.println("Error en sockets");}
        tiempoFinal = System.nanoTime() - tiempoInicio;
        System.out.println("TIEMPO = "+tiempoFinal/1000000+" milisegundos.");
    }
}