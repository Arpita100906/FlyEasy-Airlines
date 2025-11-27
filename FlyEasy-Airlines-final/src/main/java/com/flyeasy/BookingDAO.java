package com.flyeasy;

import java.sql.*;

/**
 * BookingDAO performs insert operations for passengers and bookings.
 * Note: for transactional booking, use BookingService which coordinates DAO calls inside a transaction.
 */
public class BookingDAO {

    /**
     * Insert a passenger and return generated id.
     *
     * @param conn     active DB connection (can be part of a transaction)
     * @param fullName passenger name
     * @param email    passenger email
     * @param phone    passenger phone
     * @return generated passenger id
     * @throws SQLException on DB error
     */
    public int createPassenger(Connection conn, String fullName, String email, String phone) throws SQLException {
        String sql = "INSERT INTO passengers (full_name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    /**
     * Create a booking record.
     *
     * @param conn        active connection
     * @param flightId
     * @param passengerId
     * @param seatsBooked
     * @param totalPrice
     * @throws SQLException on DB error
     */
    public void createBooking(Connection conn, int flightId, int passengerId, int seatsBooked, double totalPrice) throws SQLException {
        String sql = "INSERT INTO bookings (flight_id, passenger_id, seats_booked, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, flightId);
            ps.setInt(2, passengerId);
            ps.setInt(3, seatsBooked);
            ps.setDouble(4, totalPrice);
            ps.executeUpdate();
        }
    }
}
