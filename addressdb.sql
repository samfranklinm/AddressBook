CREATE DATABASE addressdb;

CREATE TABLE persons (
firstName VARCHAR(120), 
lastName VARCHAR(120),
phoneNumber VARCHAR(120),
address VARCHAR(120) NOT NULL
);

INSERT INTO persons (firstName, lastName, phoneNumber, address) VALUES ("Frank", "Mohan", "5046106472", "101 Maddox Hall, Mill Valley, CA 94941");

SELECT * from persons;