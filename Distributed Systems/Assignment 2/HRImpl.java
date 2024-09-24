import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class HRImpl extends UnicastRemoteObject implements HRInterface {

    //    ROOMS
    String[] roomA = new String[40]; // 75
    String[] roomB = new String[35]; // 110
    String[] roomC = new String[25]; // 120
    String[] roomD = new String[30]; // 150
    String[] roomE = new String[20]; // 200
    private static final int[] ROOM_COUNTS = {40, 35, 25, 30, 20};
    private static final int[] ROOM_PRICES = {75, 110, 120, 150, 200};

    // Method to list the number of available rooms for each type
    public String list() throws java.rmi.RemoteException {
        String listString;

        int emptyA = 0;
        int emptyB = 0;
        int emptyC = 0;
        int emptyD = 0;
        int emptyE = 0;

        // Count empty rooms for each type
        for (int i = 0; i < 40; i++) {
            if (roomA[i].isEmpty()) {
                emptyA++;
            }
        }

        for (int i = 0; i < 35; i++) {
            if (roomB[i].isEmpty()) {
                emptyB++;
            }
        }

        for (int i = 0; i < 25; i++) {
            if (roomC[i].isEmpty()) {
                emptyC++;
            }
        }

        for (int i = 0; i < 30; i++) {
            if (roomD[i].isEmpty()) {
                emptyD++;
            }
        }

        for (int i = 0; i < 20; i++) {
            if (roomE[i].isEmpty()) {
                emptyE++;
            }
        }

        // Construct the list of available rooms
        listString = emptyA + " type A rooms. Price per room is 75€ per night\n";
        listString += emptyB + " type B rooms. Price per room is 110€ per night\n";
        listString += emptyC + " type C rooms. Price per room is 120€ per night\n";
        listString += emptyD + " type D rooms. Price per room is 150€ per night\n";
        listString += emptyE + " type E rooms. Price per room is 200€ per night\n";

        return listString;
    }

    // Method to book rooms of a specified type
    public String book(String type, int number, String name, HRClientInterface client) throws java.rmi.RemoteException {
        int total = 0;
        int temp = number; // Number given by guest

        if (name.isEmpty())
            return "Name cannot be empty\n";

        // Get the index corresponding to the room type
        int roomIndex = getRoomIndex(type);
        if (roomIndex == -1) {
            return "Type must be either: A, B, C, D or E\n";
        }

        // Get the array of rooms of the specified type
        String[] rooms = getRoomsByType(type);

        synchronized (rooms) {  // Synchronize on the rooms array to prevent concurrent modification issues
            // Loop through the rooms array
            for (int i = 0; i < rooms.length; i++) {
                if (temp == 0) break;
                // If a room is available book it for the guest
                if (rooms[i] == null || rooms[i].isEmpty()) {
                    temp--; // Decrement the number of rooms needed
                    rooms[i] = name; // Assign the guest's name to the room
                    total += ROOM_PRICES[roomIndex]; // Add the room price to the total cost
                }
            }
        }

        // Calculate the number of rooms successfully booked
        int booked = number - temp;
        int remove = 0;


        if (booked == 0) {
            return "No rooms available of type " + type + ".\n";
        } else if (booked < number) {
            // Ask the client to confirm the partial booking
            var answer = client.confirmBook(booked, total);

            if (answer) {
                return "Booked " + booked + " rooms of type " + type + ". Total cost: " + total + "€\n";
            } else {
                // Loop through the rooms to cancel the booking
                for (int i = 0; i < booked; i++) {
                    // If the room was booked by the current guest, cancel the booking
                    if (Objects.equals(rooms[i], name)) {
                        rooms[i] = null;
                        remove++;
                        if (remove == booked) {
                            break;
                        }
                    }
                }

                return "No booking made.\n";
            }
        } else {
            return "Booked " + booked + " type " + type + " rooms. The total cost is: " + total + "€\n";
        }
    }

    @Override
    public String cancel(String type, int number, String name) throws RemoteException {

        int temp = number;
        int remaining = 0;

        // Get the index corresponding to the room type
        int roomIndex = getRoomIndex(type);
        if (roomIndex == -1) {
            return "Type must be either: A, B, C, D or E\n";
        }

        // Get the array of rooms of the specified type
        String[] rooms = getRoomsByType(type);

        for (int i = 0; i < rooms.length; i++) {
            if (Objects.equals(rooms[i], name)) {
                if (temp == 0) {
                    remaining++;
                } else {
                    temp--;
                    rooms[i] = "";
                }
            }
        }

        return "Guest " + name + ". Type " + type + " rooms. Cancelled: " + (number - temp)
                + "\tRemaining: " + remaining + "\n";
    }

    // Method to show all guests and their bookings
    public String guests() {
        // Array lists to store names and rooms
        ArrayList<String> guestNames = new ArrayList<>();
        ArrayList<int[]> guestRooms = new ArrayList<>();

        // Update guest rooms for each type
        updateGuestRooms(roomA, guestNames, guestRooms, 0);
        updateGuestRooms(roomB, guestNames, guestRooms, 1);
        updateGuestRooms(roomC, guestNames, guestRooms, 2);
        updateGuestRooms(roomD, guestNames, guestRooms, 3);
        updateGuestRooms(roomE, guestNames, guestRooms, 4);

        // Final result with all guest info
        String guestsString = "";

        // Calculating total cost and number of rooms booked
        for (int i = 0; i < guestNames.size(); i++) {
            int totalCost = 0;

            // Calculating the total cost for the guest based on room prices
            totalCost += guestRooms.get(i)[0] * 75;
            totalCost += guestRooms.get(i)[1] * 110;
            totalCost += guestRooms.get(i)[2] * 120;
            totalCost += guestRooms.get(i)[3] * 150;
            totalCost += guestRooms.get(i)[4] * 200;

            // Concatenate the guest information to the result string
            guestsString += guestNames.get(i)
                    + " has booked: A = " + guestRooms.get(i)[0]
                    + ", B = " + guestRooms.get(i)[1]
                    + ", C = " + guestRooms.get(i)[2]
                    + ", D = " + guestRooms.get(i)[3]
                    + ", E = " + guestRooms.get(i)[4]
                    + " ... Total cost = " + totalCost + "€\n";
        }

        return guestsString;
    }



    private void updateGuestRooms(String[] roomType, ArrayList<String> guestNames, ArrayList<int[]> guestRooms, int roomIndex) {
        for (String guestName : roomType) {
            if (guestName.isEmpty()) {
                continue;
            }
            // If guest name is already in the list, update count
            if (guestNames.contains(guestName)) {
                guestRooms.get(guestNames.indexOf(guestName))[roomIndex] += 1;
            } else {
                int[] zeroArr = {0, 0, 0, 0, 0}; // Initialize an array with zeros for room counts
                guestNames.add(guestName); // Add the guest name to list
                guestRooms.add(zeroArr); // Add the zero-initialized array to list
                guestRooms.get(guestNames.indexOf(guestName))[roomIndex] += 1; // Update the room count for the current room type
            }
        }
    }

    private int getRoomIndex(String type) {
        return switch (type) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            case "E" -> 4;
            default -> -1;
        };
    }

    private String[] getRoomsByType(String type) {
        return switch (type) {
            case "A" -> roomA;
            case "B" -> roomB;
            case "C" -> roomC;
            case "D" -> roomD;
            case "E" -> roomE;
            default -> throw new IllegalArgumentException("Invalid room type: " + type + "\n");
        };
    }


    // Constructor to initialize rooms to be empty
    protected HRImpl() throws RemoteException {
        super();
        for (int i = 0; i < 40; i++) {
            roomA[i] = "";
        }

        for (int i = 0; i < 35; i++) {
            roomB[i] = "";
        }

        for (int i = 0; i < 25; i++) {
            roomC[i] = "";
        }

        for (int i = 0; i < 30; i++) {
            roomD[i] = "";
        }

        for (int i = 0; i < 20; i++) {
            roomE[i] = "";
        }

    }
}