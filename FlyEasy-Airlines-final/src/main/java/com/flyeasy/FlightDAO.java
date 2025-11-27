package com.flyeasy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FlightDAO contains methods for reading and updating flights table.
 */
public class FlightDAO {

    /**
     * Fetch all flights from DB.
     *
     * @return list of Flight objects
     */
    public List<Flight> getAllFlights() {
        List<Flight> list = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightId(rs.getInt("flight_id"));
                f.setFlightNumber(rs.getString("flight_number"));
                f.setOrigin(rs.getString("origin"));
                f.setDestination(rs.getString("destination"));

                Timestamp dep = rs.getTimestamp("departure_time");
                if (dep != null) f.setDepartureTime(dep.toLocalDateTime());
                Timestamp arr = rs.getTimestamp("arrival_time");
                if (arr != null) f.setArrivalTime(arr.toLocalDateTime());

                f.setSeatsTotal(rs.getInt("seats_total"));
                f.setSeatsAvailable(rs.getInt("seats_available"));
                f.setPrice(rs.getDouble("price"));

                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching flights: " + e.getMessage());
        }
        return list;
    }

    /**
     * Get a single flight by id.
     */
    public Flight getFlightById(int id) {
        String sql = "SELECT * FROM flights WHERE flight_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Flight f = new Flight();
                    f.setFlightId(rs.getInt("flight_id"));
                    f.setFlightNumber(rs.getString("flight_number"));
                    f.setOrigin(rs.getString("origin"));
                    f.setDestination(rs.getString("destination"));
                    Timestamp dep = rs.getTimestamp("departure_time");
                    if (dep != null) f.setDepartureTime(dep.toLocalDateTime());
                    Timestamp arr = rs.getTimestamp("arrival_time");
                    if (arr != null) f.setArrivalTime(arr.toLocalDateTime());
                    f.setSeatsTotal(rs.getInt("seats_total"));
                    f.setSeatsAvailable(rs.getInt("seats_available"));
                    f.setPrice(rs.getDouble("price"));
                    return f;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching flight by id: " + e.getMessage());
        }
        return null;
    }

    /**
     * Reduce seats for a flight (used during booking). This method expects to be called
     * within a transaction if atomicity is required.
     *
     * @param conn     active connection (transaction)
     * @param flightId id of flight
     * @param seats    number of seats to reduce
     * @return true if update succeeded
     * @throws SQLException on DB errors
     */
    public boolean reduceSeats(Connection conn, int flightId, int seats) throws SQLException {
        String sql = "UPDATE flights SET seats_available = seats_available - ? WHERE flight_id = ? AND seats_available >= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seats);
            ps.setInt(2, flightId);
            ps.setInt(3, seats);
            int updated = ps.executeUpdate();
            return updated > 0;
        }
    }
}
