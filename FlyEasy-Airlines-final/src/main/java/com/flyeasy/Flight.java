package com.flyeasy;

import java.time.LocalDateTime;

/**
 * Flight model representing a single flight record from the database.
 */
public class Flight {
    private int flightId;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int seatsTotal;
    private int seatsAvailable;
    private double price;

    public Flight() {}

    public Flight(int flightId, String flightNumber, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  int seatsTotal, int seatsAvailable, double price) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
    }

    // Getters and setters...
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public java.time.LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(java.time.LocalDateTime departureTime) { this.departureTime = departureTime; }
    public java.time.LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(java.time.LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public int getSeatsTotal() { return seatsTotal; }
    public void setSeatsTotal(int seatsTotal) { this.seatsTotal = seatsTotal; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", seatsTotal=" + seatsTotal +
                ", seatsAvailable=" + seatsAvailable +
                ", price=" + price +
                '}';
    }
}
