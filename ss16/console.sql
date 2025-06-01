CREATE DATABASE ss16;
USE ss16;

CREATE TABLE user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)           NOT NULL UNIQUE,
    password VARCHAR(100)          NOT NULL,
    email    VARCHAR(100)          NOT NULL UNIQUE,
    role     ENUM ('ADMIN','USER') NOT NULL,
    status   TINYINT DEFAULT 1
);

DELIMITER $$
CREATE PROCEDURE insert_user(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_role ENUM ('ADMIN','USER'),
    IN p_status TINYINT
)
BEGIN
    INSERT INTO user(username, password, email, role, status)
    VALUES (p_username, p_password, p_email, p_role, p_status);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_user_by_username(IN p_username VARCHAR(50))
BEGIN
    SELECT * FROM user WHERE username = p_username;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE exists_user_by_email(IN p_email VARCHAR(100))
BEGIN
    SELECT 1 FROM user WHERE email = p_email;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE exists_user_by_username(IN p_username VARCHAR(50))
BEGIN
    SELECT 1 FROM user WHERE username = p_username;
END$$
DELIMITER ;


CREATE TABLE bus
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(20)                    NOT NULL,
    bus_type      ENUM ('VIP','LUXURY','NORMAL') NOT NULL,
    row_seat      INT                            NOT NULL,
    col_seat      INT                            NOT NULL,
    total_seat    INT                            NOT NULL,
    image         VARCHAR(255)
);

DELIMITER $$
CREATE PROCEDURE insert_bus(
    IN p_license_plate VARCHAR(20),
    IN p_bus_type ENUM ('VIP','LUXURY','NORMAL'),
    IN p_row_seat INT,
    IN p_col_seat INT,
    IN p_total_seat INT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO bus(license_plate, bus_type, row_seat, col_seat, total_seat, image)
    VALUES (p_license_plate, p_bus_type, p_row_seat, p_col_seat, p_total_seat, p_image);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_all_bus()
BEGIN
    SELECT * FROM bus;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_bus_by_id(IN p_id INT)
BEGIN
    SELECT * FROM bus WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_bus(
    IN p_id INT,
    IN p_license_plate VARCHAR(20),
    IN p_bus_type ENUM ('VIP','LUXURY','NORMAL'),
    IN p_row_seat INT,
    IN p_col_seat INT,
    IN p_total_seat INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE bus
    SET license_plate = p_license_plate,
        bus_type      = p_bus_type,
        row_seat      = p_row_seat,
        col_seat      = p_col_seat,
        total_seat    = p_total_seat,
        image         = p_image
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_bus(IN p_id INT)
BEGIN
    DELETE FROM bus WHERE id = p_id;
END$$
DELIMITER ;

CREATE TABLE bus_trip
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    departure_point VARCHAR(100) NOT NULL,
    destination     VARCHAR(100) NOT NULL,
    departure_time  DATETIME     NOT NULL,
    arrival_time    DATETIME     NOT NULL,
    bus_id          INT          NOT NULL,
    seats_available INT          NOT NULL,
    image           VARCHAR(255),
    FOREIGN KEY (bus_id) REFERENCES bus (id)
);


DELIMITER $$
CREATE PROCEDURE insert_bustrip(
    IN p_departure_point VARCHAR(100),
    IN p_destination VARCHAR(100),
    IN p_departure_time DATETIME,
    IN p_arrival_time DATETIME,
    IN p_bus_id INT,
    IN p_seats_available INT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO bus_trip(departure_point, destination, departure_time, arrival_time, bus_id, seats_available, image)
    VALUES (p_departure_point, p_destination, p_departure_time, p_arrival_time, p_bus_id, p_seats_available, p_image);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_all_bustrip()
BEGIN
    SELECT * FROM bus_trip;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_bustrip_by_id(IN p_id INT)
BEGIN
    SELECT * FROM bus_trip WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_bustrip(
    IN p_id INT,
    IN p_departure_point VARCHAR(100),
    IN p_destination VARCHAR(100),
    IN p_departure_time DATETIME,
    IN p_arrival_time DATETIME,
    IN p_bus_id INT,
    IN p_seats_available INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE bus_trip
    SET departure_point = p_departure_point,
        destination     = p_destination,
        departure_time  = p_departure_time,
        arrival_time    = p_arrival_time,
        bus_id          = p_bus_id,
        seats_available = p_seats_available,
        image           = p_image
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_bustrip(IN p_id INT)
BEGIN
    DELETE FROM bus_trip WHERE id = p_id;
END$$
DELIMITER ;

CREATE TABLE ticket
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT            NOT NULL,
    trip_bus_id    INT            NOT NULL,
    list_seat      VARCHAR(255)   NOT NULL,
    total_money    DECIMAL(15, 2) NOT NULL,
    departure_date DATE           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (trip_bus_id) REFERENCES bus_trip (id)
);

DELIMITER $$
CREATE PROCEDURE insert_ticket(
    IN p_user_id INT,
    IN p_trip_bus_id INT,
    IN p_list_seat VARCHAR(255),
    IN p_total_money DECIMAL(15, 2),
    IN p_departure_date DATE
)
BEGIN
    INSERT INTO ticket(user_id, trip_bus_id, list_seat, total_money, departure_date)
    VALUES (p_user_id, p_trip_bus_id, p_list_seat, p_total_money, p_departure_date);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_tickets_by_user(IN p_user_id INT)
BEGIN
    SELECT * FROM ticket WHERE user_id = p_user_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_ticket_by_id(IN p_id INT)
BEGIN
    SELECT * FROM ticket WHERE id = p_id;
END$$
DELIMITER ;