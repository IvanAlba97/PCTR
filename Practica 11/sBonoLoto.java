
/**
 *
 * @author ivan0
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Random;

public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {

    private final int[] bonoLoto = new int[6];

    public sBonoLoto() throws RemoteException {
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < bonoLoto.length; i++) {
            bonoLoto[i] = rand.nextInt(48) + 1;
        }
    }

    @Override
    public void resetServidor() throws RemoteException {
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < bonoLoto.length; i++) {
            bonoLoto[i] = rand.nextInt(48) + 1;
        }
    }

    @Override
    public boolean compApuesta(int[] apuesta) throws RemoteException {
        System.out.println("Apuesta: " + Arrays.toString(apuesta));
        return Arrays.equals(bonoLoto, apuesta);
    }

    public static void main(String[] args) throws Exception {
        iBonoLoto ORemoto = new sBonoLoto();
        Naming.bind("//localhost/Servidor", ORemoto);
        System.out.println("Servidor remoto preparado.");
    }
}
