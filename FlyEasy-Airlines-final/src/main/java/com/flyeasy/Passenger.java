package com.flyeasy;

/**
 * Passenger model representing a passenger record.
 */
public class Passenger {
    private int passengerId;
    private String fullName;
    private String email;
    private String phone;

    public Passenger() {}

    public Passenger(int passengerId, String fullName, String email, String phone) {
        this.passengerId = passengerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
