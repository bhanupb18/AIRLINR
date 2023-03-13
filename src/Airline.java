import java.util.Scanner;

class Passenger {
    private String name;
    private int age;
    private String gender;
    private String contactNumber;
    private String email;

    // Constructor
    public Passenger(String name, int age, String gender, String contactNumber, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }
}

class Flight {
    private String flightNumber;
    private String airline;
    private String source;
    private String destination;
    private int capacity;
    private int seatsBooked;
    private double ticketPrice;

    // Constructor
    public Flight(String flightNumber, String airline, String source, String destination, int capacity, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
        this.seatsBooked = 0;
    }

    // Getters and Setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    // Other methods
    public boolean bookSeat() {
        if (seatsBooked < capacity) {
            seatsBooked++;
            return true;
        }
        return false;
    }

    public double calculateTotalPrice(int numSeats) {
        return numSeats * ticketPrice;
    }
}

class Reservation {
    private Flight flight;
    private Passenger passenger;
    private int numSeats;
    private double totalPrice;

    // Constructor
    public Reservation(Flight flight, Passenger passenger, int numSeats) {
        this.flight = flight;
        this.passenger = passenger;
        this.numSeats = numSeats;
        this.totalPrice = flight.calculateTotalPrice(numSeats);
    }

    // Getters
    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

public class Airline {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Flight[] flights = {
                new Flight("FL001", "Air India", "Mumbai", "Delhi", 100, 5000.00),
                new Flight("FL002", "Indigo", "Delhi", "Mumbai", 150, 4000.00),
                new Flight("FL003", "SpiceJet", "Mumbai", "Chennai", 75, 3500.00),
                new Flight("FL004", "GoAir", "Chennai", "Mumbai", 120, 4500.00)
        };

// Display available flights
        System.out.println("Available Flights:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s\n", "Flight No.", "Airline", "Source", "Destination", "Price", "Seats Available");
        System.out.println("------------------------------------------------------------------");
        for (Flight flight : flights) {
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s\n", flight.getFlightNumber(), flight.getAirline(),
                    flight.getSource(), flight.getDestination(), flight.getTicketPrice(),
                    flight.getCapacity() - flight.getSeatsBooked());
        }

// Get user input for flight booking
        System.out.print("Enter the flight number to book: ");
        String flightNumber = scanner.nextLine();
        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Invalid flight number.");
            return;
        }

// Get user input for passenger details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        Passenger passenger = new Passenger(name, age, gender, contactNumber, email);

// Get user input for number of seats
        System.out.print("Enter the number of seats to book: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

// Make reservation
        boolean success = selectedFlight.bookSeat();
        if (success) {
            Reservation reservation = new Reservation(selectedFlight, passenger, numSeats);
            System.out.println("\nReservation confirmed:");
            System.out.printf("Flight Number: %s\nAirline: %s\nSource: %s\nDestination: %s\nPassenger Name: %s\nNumber of Seats: %d\nTotal Price: Rs. %.2f\n",
                    reservation.getFlight().getFlightNumber(), reservation.getFlight().getAirline(),
                    reservation.getFlight().getSource(), reservation.getFlight().getDestination(),
                    reservation.getPassenger().getName(), reservation.getNumSeats(), reservation.getTotalPrice());
        } else {
            System.out.println("Sorry, seats are not available.");
        }
    }
}


