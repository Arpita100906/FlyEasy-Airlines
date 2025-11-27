package com.flyeasy;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Console-based application entrypoint for FlyEasy-Airlines.
 * Demonstrates fetching flights and performing a booking transaction.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    // Simple email and phone regex for basic validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+\-\s]{7,20}$");

    public static void main(String[] args) {
        FlightDAO flightDAO = new FlightDAO();
        BookingService bookingService = new BookingService();

        System.out.println("Welcome to FlyEasy-Airlines (finalized for submission)");
        List<Flight> flights = flightDAO.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available. Please ensure the database is loaded.");
            return;
        }

        System.out.println("Available Flights:");
        for (Flight f : flights) {
            System.out.println(f.getFlightId() + " | " + f.getFlightNumber() + " | " + f.getOrigin() + " -> " + f.getDestination()
                    + " | Seats Available: " + f.getSeatsAvailable() + " | Price: " + f.getPrice());
        }

        System.out.print("\nEnter flight id to book: ");
        int flightId = readInt();
        Flight selected = flightDAO.getFlightById(flightId);
        if (selected == null) {
            System.out.println("That flight does not exist. Exiting.");
            return;
        }

        System.out.print("Enter your full name: ");
        String name = readNonEmpty();
        System.out.print("Enter your email: ");
        String email = readValidated(EMAIL_PATTERN, "Please enter a valid email address.");
        System.out.print("Enter your phone: ");
        String phone = readValidated(PHONE_PATTERN, "Please enter a valid phone number (digits, +, -, spaces).");
        System.out.print("Number of seats to book: ");
        int seats = readInt();

        if (seats <= 0) {
            System.out.println("Invalid seat count. Exiting.");
            return;
        }

        if (selected.getSeatsAvailable() < seats) {
            System.out.println("Not enough seats available. Exiting.");
            return;
        }

        System.out.println("Processing booking...");

        boolean result = bookingService.book(flightId, name, email, phone, seats);
        if (result) {
            System.out.println("Booking confirmed! Please check your bookings in the database.");
        } else {
            System.out.println("Booking failed. Please try again or contact support.");
        }
    }

    private static int readInt() {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    private static String readNonEmpty() {
        while (true) {
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.print("Input cannot be empty, please enter again: ");
        }
    }

    private static String readValidated(Pattern pattern, String errorMsg) {
        while (true) {
            String s = sc.nextLine().trim();
            if (pattern.matcher(s).matches()) return s;
            System.out.print(errorMsg + " Try again: ");
        }
    }
}
