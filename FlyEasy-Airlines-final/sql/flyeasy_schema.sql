-- flyeasy_schema.sql
DROP DATABASE IF EXISTS flyeasy;
CREATE DATABASE flyeasy;
USE flyeasy;

CREATE TABLE flights (
  flight_id INT AUTO_INCREMENT PRIMARY KEY,
  flight_number VARCHAR(10) NOT NULL,
  origin VARCHAR(100) NOT NULL,
  destination VARCHAR(100) NOT NULL,
  departure_time DATETIME NOT NULL,
  arrival_time DATETIME NOT NULL,
  seats_total INT NOT NULL,
  seats_available INT NOT NULL,
  price DECIMAL(10,2) NOT NULL
);

CREATE TABLE passengers (
  passenger_id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(200) NOT NULL,
  email VARCHAR(150),
  phone VARCHAR(50)
);

CREATE TABLE bookings (
  booking_id INT AUTO_INCREMENT PRIMARY KEY,
  flight_id INT NOT NULL,
  passenger_id INT NOT NULL,
  seats_booked INT NOT NULL DEFAULT 1,
  booking_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  total_price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE,
  FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id) ON DELETE CASCADE
);

-- sample flights
INSERT INTO flights (flight_number, origin, destination, departure_time, arrival_time, seats_total, seats_available, price)
VALUES
  ('FE101','Mumbai','Delhi','2025-12-10 09:00:00','2025-12-10 11:00:00',180,180,5000.00),
  ('FE102','Delhi','Mumbai','2025-12-11 14:00:00','2025-12-11 16:00:00',180,180,5200.00),
  ('FE201','Bengaluru','Chennai','2025-12-12 07:30:00','2025-12-12 08:45:00',150,150,3500.00);

-- sample passengers
INSERT INTO passengers (full_name, email, phone)
VALUES
  ('John Doe','john@example.com','+919000000000'),
  ('Jane Smith','jane@example.com','+919111111111');
