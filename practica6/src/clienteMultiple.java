import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos para la creación de clientes.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see ServidorHiloconPool.java
 */
public class clienteMultiple {

    /** 
     * Método principal.
     * @param args
     */
    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        //int i = (int) (Math.random() * 10);
        int puerto = 2001;
        char c;
        do {
            System.out.print("Introduce el número de clientes: ");
            int nClientes = S.nextInt();
            double tiempoInicio = System.nanoTime();
            for (int i = 0; i < nClientes; i++) {
                try {
                    System.out.println("Realizando conexion...");
                    Socket cable = new Socket("10.162.156.60", puerto);
                    System.out.println("Realizada conexion a " + cable);
                    PrintWriter salida = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            cable.getOutputStream())));
                    salida.println(i);
                    salida.flush();
                    System.out.println("Cerrando conexion...");
                    cable.close();

                } catch (IOException E) {
                    System.err.println(E.getMessage());
                }
            }
            double tiempoFin = System.nanoTime() - tiempoInicio;
            System.out.println("Tiempo: " + tiempoFin / 1000000 + " milisegundos.");

            System.out.print("Desea volver a lanzar los clientes (S/N)?: ");
            c = S.next().charAt(0);
        } while(c == 's' || c == 'S');
        S.close();
    }
}