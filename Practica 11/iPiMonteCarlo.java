
/**
 *
 * @author ivan0
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iPiMonteCarlo extends Remote {

    public void anadirPuntos(int n) throws RemoteException;

    public void reset() throws RemoteException;

    public double PI() throws RemoteException;
}
