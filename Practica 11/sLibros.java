
/**
 *
 * @author ivan0
 */
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class sLibros extends UnicastRemoteObject implements iLibros {

    private ArrayList<Libro> libros = new ArrayList<Libro>();
    private static int nLibros;

    public sLibros() throws RemoteException {
        nLibros = 0;
    }

    @Override
    public synchronized String consultar(String titulo) throws RemoteException {
        for (int i = 0; i < nLibros; i++) {
            if (libros.get(i).getTitulo().equals(titulo)) {
                return libros.get(i).toString();
            }
        }
        return "El libro introducido no se encuentra en la Base de Datos.";
    }

    @Override
    public synchronized void insertar(String titulo, String autor, int anno) throws RemoteException {
        libros.add(new Libro(titulo, autor, anno));
        nLibros++;
        System.out.println("Libro insertado.");
    }

    @Override
    public synchronized Libro extraer(String titulo) throws RemoteException {
        Libro L = new Libro("No encontrado", "error", 0000);
        boolean encontrado = false;
        for (int i = 0; i < nLibros && !encontrado; i++) {
            if (libros.get(i).getTitulo().equals(titulo)) {
                L = libros.get(i);
                libros.remove(i);
                nLibros--;
                encontrado = true;
            }
        }
        if(encontrado){
            System.out.println("Libro extraido.");
        }
        return L;
    }

    public static void main(String[] args) throws Exception {
        iLibros ORemoto = new sLibros();
        Naming.bind("Servidor", ORemoto);
        System.out.println("Servidor remoto preparado.");
    }
}
