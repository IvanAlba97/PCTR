
/**
 *
 * @author ivan0
 */

import java.net.*;
import java.io.*;

public class clienteMultiple {

    public static void main(String[] args) {
        int i = (int) (Math.random() * 10);
        int puerto = 2001;
        int iteraciones = 20;
        for (int j = 0; j < iteraciones; j++) {
            try {
                System.out.println("Realizando conexion");
                Socket cable = new Socket("localhost", 2001);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(i);
                salida.flush();
                System.out.println("Cerrando conexion");
                cable.close();
            } catch (IOException E) {
                System.err.println(E.getMessage());
            }
        }
    }
}
