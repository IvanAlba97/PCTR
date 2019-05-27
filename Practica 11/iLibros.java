
/**
 *
 * @author ivan0
 */
import java.rmi.*;

public interface iLibros extends Remote {

    public String consultar(String t) throws RemoteException;

    public void insertar(String titulo, String autor, int anno) throws RemoteException;

    public Libro extraer(String t) throws RemoteException;
}
