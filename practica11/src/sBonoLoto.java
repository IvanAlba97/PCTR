import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Random;

/**
 * Esta clase interpreta el servidor para el problema de BonoLoto.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see iBonoLoto.java
 * @see cBonoLoto.java
 */
public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {

    private final int[] bonoLoto = new int[6];

    /**
     * Método constructor.
     */
    public sBonoLoto() throws RemoteException {
        resetServidor();
    }

    /**
     * Método para resetear el servidor. Cambia el valor del vector bonoLoto[]
     */
    @Override
    public void resetServidor() throws RemoteException {
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < bonoLoto.length; i++) {
            bonoLoto[i] = rand.nextInt(49) + 1;
        }
    }

    /**
     * Este método comprueba si una apuesta dada ha acertado o no.
     * @return res Valor booleano
     */
    @Override
    public boolean compApuesta(int[] apuesta) throws RemoteException {
        boolean res = false;
        System.out.println("Apuesta: " + Arrays.toString(apuesta));
        if(Arrays.equals(bonoLoto, apuesta)) {
            res = true;
            System.out.println("ACIERTO");
        }
        return res;
    }

    /**
     * Método principal.
     */
    public static void main(String[] args) throws Exception {
        iBonoLoto ORemoto = new sBonoLoto();
        Naming.bind("//localhost/Servidor", ORemoto);
        System.out.println("Servidor remoto preparado.");
    }
}