import java.rmi.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase interpreta el cliente para el problema de BonoLoto.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see iBonoLoto.java
 * @see sBonoLoto.java
 */
public class cBonoLoto {

    /**
     * Método principal.
     */
    public static void main(String[] args) throws Exception {
        Random rand = new Random(System.nanoTime());
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce el numero de clientes: ");
        int nClientes = S.nextInt();

        for (int i = 0; i < nClientes; i++) {
            iBonoLoto RefORemoto = (iBonoLoto) Naming.lookup("//localhost/Servidor");
            int[] apuesta = new int[6];
            for (int j = 0; j < apuesta.length; j++) apuesta[j] = rand.nextInt(49) + 1;
            boolean resultado = RefORemoto.compApuesta(apuesta);
            for (int j = 0; j < apuesta.length; j++) System.out.print(apuesta[j] + " ");
            if (resultado) System.out.println("GANASTE");
            else System.out.println("PERDISTE");
        }

        S.close();
    }
}