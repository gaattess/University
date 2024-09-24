import java.rmi.*;
import java.rmi.registry.*;

public class HRServer {
    public static void main(String args[])
    {
        try
        {
            // Create an object of the interface
            // implementation class
            HRImpl obj = new HRImpl();

            // rmiregistry within the server JVM with
            // port number 1900
            LocateRegistry.createRegistry(1900);

            // Binds the remote object by the name
            // hello
            Naming.rebind("rmi://localhost:1900"+
                    "/hotel", obj);
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }
}