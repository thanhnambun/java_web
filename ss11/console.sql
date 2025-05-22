create database bt10;
use bt10;

CREATE TABLE movie (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       director VARCHAR(50) NOT NULL,
                       release_date DATE NOT NULL,
                       genre VARCHAR(30) NOT NULL,
                       poster VARCHAR(255)
);

-- Thêm phim mới
DELIMITER $$
CREATE PROCEDURE insert_movie(
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_release_date DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(255)
)
BEGIN
    INSERT INTO movie (title, director, release_date, genre, poster)
    VALUES (p_title, p_director, p_release_date, p_genre, p_poster);
END$$
DELIMITER ;

-- Sửa phim
DELIMITER $$
CREATE PROCEDURE update_movie(
    IN p_id INT,
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_release_date DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(255)
)
BEGIN
    UPDATE movie
    SET title = p_title,
        director = p_director,
        release_date = p_release_date,
        genre = p_genre,
        poster = p_poster
    WHERE id = p_id;
END$$
DELIMITER ;

-- Xóa phim
DELIMITER $$
CREATE PROCEDURE delete_movie(IN p_id INT)
BEGIN
    DELETE FROM movie WHERE id = p_id;
END$$
DELIMITER ;

-- Lấy tất cả phim
DELIMITER $$
CREATE PROCEDURE get_all_movies()
BEGIN
    SELECT * FROM movie;
END$$
DELIMITER ;

-- Tìm phim theo id
DELIMITER $$
CREATE PROCEDURE find_movie_by_id(IN p_id INT)
BEGIN
    SELECT * FROM movie WHERE id = p_id;
END$$
DELIMITER ;


create database bt89;
use bt89;

CREATE TABLE category (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          category_name VARCHAR(50) NOT NULL UNIQUE,
                          status BIT DEFAULT 1
);
-- Lấy tất cả danh mục
DELIMITER $$
CREATE PROCEDURE get_all_categories()
BEGIN
    SELECT * FROM category;
END$$
DELIMITER ;

-- Tìm theo id
DELIMITER $$
CREATE PROCEDURE find_category_by_id(IN p_id INT)
BEGIN
    SELECT * FROM category WHERE id = p_id;
END$$
DELIMITER ;

-- Tìm theo tên
DELIMITER $$
CREATE PROCEDURE find_category_by_name(IN p_name VARCHAR(50))
BEGIN
    SELECT * FROM category WHERE category_name = p_name;
END$$
DELIMITER ;

-- Tìm theo tên và khác id
DELIMITER $$
CREATE PROCEDURE find_category_by_name_and_not_id(IN p_name VARCHAR(50), IN p_id INT)
BEGIN
    SELECT * FROM category WHERE category_name = p_name AND id <> p_id;
END$$
DELIMITER ;

-- Thêm mới
DELIMITER $$
CREATE PROCEDURE insert_category(IN p_name VARCHAR(50), IN p_status BIT)
BEGIN
    INSERT INTO category (category_name, status) VALUES (p_name, p_status);
END$$
DELIMITER ;

-- Sửa
DELIMITER $$
CREATE PROCEDURE update_category(IN p_id INT, IN p_name VARCHAR(50), IN p_status BIT)
BEGIN
    UPDATE category SET category_name = p_name, status = p_status WHERE id = p_id;
END$$
DELIMITER ;

-- Xóa
DELIMITER $$
CREATE PROCEDURE delete_category(IN p_id INT)
BEGIN
    DELETE FROM category WHERE id = p_id;
END$$
DELIMITER ;