package com.flyeasy;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * BookingService orchestrates a booking transaction:
 * 1) create passenger
 * 2) reduce seats
 * 3) create booking
 *
 * All steps are executed inside a single DB transaction to ensure atomicity.
 */
public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();
    private final FlightDAO flightDAO = new FlightDAO();

    /**
     * Attempt to book seats on a flight for a passenger. Returns true if booking succeeded.
     *
     * @param flightId flight id
     * @param fullName passenger name
     * @param email    passenger email
     * @param phone    passenger phone
     * @param seats    seats to book
     * @return true if booking successful
     */
    public boolean book(int flightId, String fullName, String email, String phone, int seats) {
        try (Connection conn = DBConnection.getConnection()) {
            try {
                conn.setAutoCommit(false); // begin transaction

                // 1) create passenger
                int passengerId = bookingDAO.createPassenger(conn, fullName, email, phone);
                if (passengerId <= 0) throw new SQLException("Failed to create passenger");

                // 2) reduce seats
                boolean seatsOk = flightDAO.reduceSeats(conn, flightId, seats);
                if (!seatsOk) throw new SQLException("Insufficient seats");

                // 3) create booking
                // quick price fetch (could be optimized)
                Flight flight = flightDAO.getFlightById(flightId);
                double totalPrice = flight.getPrice() * seats;
                bookingDAO.createBooking(conn, flightId, passengerId, seats, totalPrice);

                conn.commit();
                return true;
            } catch (Exception ex) {
                conn.rollback();
                System.err.println("Transaction failed and was rolled back: " + ex.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Database error in booking: " + e.getMessage());
            return false;
        }
    }
}
