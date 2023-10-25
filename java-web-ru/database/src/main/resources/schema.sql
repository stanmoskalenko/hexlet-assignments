drop table IF EXISTS product;

CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    price INT
    );

