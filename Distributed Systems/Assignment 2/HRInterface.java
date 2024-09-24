import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HRInterface extends Remote {
    public String list() throws RemoteException;

    public String book(String type, int number, String name, HRClientInterface client) throws RemoteException;

    public String guests() throws RemoteException;

    public String cancel(String type, int number, String name) throws RemoteException;
}
