import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Scanner;

public class HRClient extends UnicastRemoteObject implements HRClientInterface {

    protected HRClient() throws RemoteException {
//        super();
    }

    private static void empty() throws RemoteException {
            System.out.println("Usage: java HRClient <list || book || guests || cancel>");
    }

    private static void list(String args[]) throws MalformedURLException, NotBoundException, RemoteException {
        if (args.length != 2) {
            System.out.println("Usage: java HRClient <hostname>");
            System.exit(1);
        }

        String host = args[1];

        HRInterface server = (HRInterface) Naming.lookup("rmi://" + host + ":1900" + "/hotel");

        var fullList = server.list();

        System.out.println(fullList);
    }

    private static void book(String args[]) throws MalformedURLException, NotBoundException, RemoteException {
        if (args.length != 5) {
            System.out.println("Usage: java HRClient <hostname> <type> <number> <name>");
            System.exit(1);
        }

        String host = args[1];
        String type = args[2];
        int number = Integer.parseInt(args[3]);
        String name = args[4];
        HRClientInterface client = new HRClient();


        HRInterface server = (HRInterface) Naming.lookup("rmi://" + host + ":1900" + "/hotel");
        var fullList = "";

        try {
            fullList = server.book(type, number, name, client);
            unexportObject(client, false);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

        System.out.println(fullList);
    }

    private static void guests(String args[]) throws RemoteException, MalformedURLException, NotBoundException {
        if (args.length != 2) {
            System.out.println("Usage: java HRClient <hostname>");
            System.exit(1);
        }

        String host = args[1];

        HRInterface server = (HRInterface) Naming.lookup("rmi://" + host + ":1900" + "/hotel");

        var fullList = server.guests();

        System.out.println(fullList);
    }

    private static void cancel(String args[]) throws RemoteException, MalformedURLException, NotBoundException {
        if (args.length != 5) {
            System.out.println("Usage: java HRClient <hostname> <type> <number> <name>");
            System.exit(1);
        }

        String host = args[1];
        String type = args[2];
        int number = Integer.parseInt(args[3]);
        String name = args[4];

        HRInterface server = (HRInterface) Naming.lookup("rmi://" + host + ":1900" + "/hotel");

        var fullList = server.cancel(type, number, name);

        System.out.println(fullList);
    }

    public static void main(String args[]) throws RemoteException {

        if (args.length == 0) {
            empty();
            return;
        }

        try {
            switch (args[0]) {
                case "list" -> list(args);
                case "book" -> book(args);
                case "guests" -> guests(args);
                case "cancel" -> cancel(args);
                default -> empty();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean confirmBook(int booked, int total) throws RemoteException {
        System.out.println("Only " + booked + " rooms were available and booked for a total cost of " + total + "€.\nDo you want to proceed with this booking? (yes/no)");
        var scanner = new Scanner(System.in);
        var answer = scanner.nextLine();

        while (!Objects.equals(answer, "yes") && !Objects.equals(answer, "y") && !Objects.equals(answer, "n") && !Objects.equals(answer, "no")) {
            System.out.println("Invalid answer. Try again.");
            answer = scanner.nextLine();
        }

        if (answer.equals("yes") || answer.equals("y")) {
            return true;
        } else if (answer.equals("no") || answer.equals("n")) {
            return false;
        }
        return false;
    }
}