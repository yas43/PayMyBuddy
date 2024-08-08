DROP DATABASE  IF EXISTS dev2payMyBuddy;

CREATE DATABASE dev2payMyBuddy;
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    role VARCHAR(255),
    balance FLOAT NOT NULL
);

CREATE TABLE friend (
    user_id INT,
    friend_id INT,
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (friend_id) REFERENCES User(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
CREATE TABLE Transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    description VARCHAR(255),
    amount FLOAT NOT NULL,
    fee FLOAT,
    date DATETIME,
    FOREIGN KEY (sender_id) REFERENCES User(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (receiver_id) REFERENCES User(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);