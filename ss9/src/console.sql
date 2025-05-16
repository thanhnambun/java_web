CREATE DATABASE IF NOT EXISTS cinema_booking;
USE cinema_booking;


CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    role     VARCHAR(20)  NOT NULL,
    enabled  BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE movies
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    director    VARCHAR(100),
    genre       VARCHAR(50),
    description TEXT,
    duration    INT          NOT NULL,
    language    VARCHAR(50)
);

CREATE TABLE screen_rooms
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    capacity INT          NOT NULL
);
CREATE TABLE seats
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    screen_room_id BIGINT      NOT NULL,
    seat_number    VARCHAR(10) NOT NULL,
    is_booked      BOOLEAN     NOT NULL DEFAULT FALSE,
    FOREIGN KEY (screen_room_id) REFERENCES screen_rooms (id)
);

CREATE TABLE schedules
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id        BIGINT   NOT NULL,
    screen_room_id  BIGINT   NOT NULL,
    show_time       DATETIME NOT NULL,
    available_seats INT      NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (screen_room_id) REFERENCES screen_rooms (id)
);

CREATE TABLE tickets
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    seat_id     BIGINT NOT NULL,
    price       DOUBLE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES users (id),
    FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    FOREIGN KEY (seat_id) REFERENCES seats (id)
);


DELIMITER //
CREATE PROCEDURE get_all_screen_rooms()
BEGIN
    SELECT id, name, capacity FROM screen_rooms;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_all_movies()
BEGIN
    SELECT id, title, director, genre, description, duration, language FROM movies;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_seats_by_screen_room(IN screen_room_id BIGINT)
BEGIN
    SELECT id, seat_number, is_booked
    FROM seats
    WHERE screen_room_id = screen_room_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_booked_seats_by_schedule(IN schedule_id BIGINT)
BEGIN
    SELECT seat_id
    FROM tickets
    WHERE schedule_id = schedule_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE find_user_login(IN p_username VARCHAR(50), IN p_password VARCHAR(255))
BEGIN
    SELECT id, username, email, role, enabled
    FROM users
    WHERE username = p_username
      AND password = p_password;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE book_ticket(
    IN p_customer_id BIGINT,
    IN p_schedule_id BIGINT,
    IN p_seat_id BIGINT,
    IN p_price DOUBLE
)
BEGIN
    INSERT INTO tickets (customer_id, schedule_id, seat_id, price)
    VALUES (p_customer_id, p_schedule_id, p_seat_id, p_price);

    UPDATE seats
    SET is_booked = TRUE
    WHERE id = p_seat_id;

    UPDATE schedules
    SET available_seats = available_seats - 1
    WHERE id = p_schedule_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_schedules_by_movie(IN movie_id BIGINT)
BEGIN
    SELECT s.id,
           m.title AS movieTitle,
           s.screen_room_id,
           s.show_time,
           s.available_seats
    FROM schedules s
             JOIN movies m ON s.movie_id = m.id
    WHERE s.movie_id = movie_id;
END //
DELIMITER ;


INSERT INTO users (username, password, email, role, enabled)
VALUES ('john_doe', 'password123', 'john@example.com', 'CUSTOMER', TRUE),
       ('admin', 'admin123', 'admin@example.com', 'ADMIN', TRUE);

INSERT INTO movies (title, director, genre, description, duration, language)
VALUES ('Inception', 'Christopher Nolan', 'Sci-Fi', 'A thief who steals corporate secrets through dream infiltration.',
        148, 'English'),
       ('The Matrix', 'Wachowski Sisters', 'Sci-Fi', 'A hacker discovers a mysterious reality.', 136, 'English');

INSERT INTO screen_rooms (name, capacity)
VALUES ('Room 1', 100),
       ('Room 2', 80);

INSERT INTO seats (screen_room_id, seat_number, is_booked)
VALUES (1, 'A1', FALSE),
       (1, 'A2', FALSE),
       (2, 'B1', FALSE),
       (2, 'B2', FALSE);

INSERT INTO schedules (movie_id, screen_room_id, show_time, available_seats)
VALUES (1, 1, '2025-05-17 19:00:00', 100),
       (2, 2, '2025-05-17 21:00:00', 80);