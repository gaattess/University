import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HRClientInterface extends Remote {
    public boolean confirmBook(int booked, int total) throws RemoteException;
}
