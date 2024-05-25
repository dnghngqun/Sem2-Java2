CREATE DATABASE PracticalTestJSON;

USE PracticalTestJSON;

CREATE TABLE Student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    address varchar(100),
    email varchar(50),
    phone varchar(15),
    DOB DATE
);

SELECT * FROM Student;

INSERT INTO Student VALUES (1, 'Quan', 'Hanoi', 'abc@gmail.com','0987654321','2005/07/20');
INSERT INTO Student VALUES (2, 'Hoa', 'HCM', 'hoa@gmail.com','0981297326','2005/07/20');
INSERT INTO Student VALUES (3, 'Hai', 'Ninh Binh', 'hai@gmail.com','0929287333','2005/07/20');
INSERT INTO Student VALUES (4, 'Huong', 'Hanoi', 'huong@gmail.com','0982361233','2005/07/20');

