import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }
}


class Reservation {
    private Room room;
    private String guestName;
    private int days;
    private double totalCost;

    public Reservation(Room room, String guestName, int days) {
        this.room = room;
        this.guestName = guestName;
        this.days = days;
        this.totalCost = room.getPrice() * days;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCost() {
        return totalCost;
    }
}


public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Double", 150));
        rooms.add(new Room(103, "Suite", 250));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using my platform. Hope you will vist again !");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }

        scanner.close();
    }

    private static void viewAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory() + ", Price: $" + room.getPrice() + " per night");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        scanner.nextLine(); 
        String guestName = scanner.nextLine();

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter number of days: ");
        int days = scanner.nextInt();

        Room roomToReserve = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                roomToReserve = room;
                break;
            }
        }

        if (roomToReserve != null) {
            roomToReserve.reserve();
            Reservation reservation = new Reservation(roomToReserve, guestName, days);
            reservations.add(reservation);
            System.out.println("Reservation made successfully. Total cost: $" + reservation.getTotalCost());
        } else {
            System.out.println("Room not available or invalid room number.");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter your name to view booking details: ");
        scanner.nextLine(); 
        String guestName = scanner.nextLine();

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getGuestName().equalsIgnoreCase(guestName)) {
                System.out.println("Room Number: " + reservation.getRoom().getRoomNumber() + ", Category: " + reservation.getRoom().getCategory() + ", Days: " + reservation.getDays() + ", Total Cost: $" + reservation.getTotalCost());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservations found for " + guestName);
        }
    }
}
