CREATE database ecommerceFPT;

USE ecommerceFPT;

CREATE TABLE customers(
	customer_id INT PRIMARY KEY,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	email varchar(255) not null
);

INSERT INTO customers VALUES(3,'TRUONG', 'GIA BINH', 'binh@fpt.edu.vn'),(4,'NGUYEN', 'KHACH THANH', 'thanh@fpt.edu.vn');

SELECT * FROM customers;

SELECT * FROM customers WHERE customer_id = 3 AND first_name = 'TRUONG';

# Create store procedure

CREATE PROCEDURE getCustomer(
    IN p_cus_id INT,
    IN p_first_name varchar(255),
    IN p_last_name varchar(255),
    IN p_email varchar(255)
    )

BEGIN

#     set p_first_name = 'Duong';
#     set p_last_name = 'Le Vinh';
#     set p_email = 'vinh@gmail.com';
    SELECT first_name, last_name, email
    INTO p_first_name, p_last_name, p_email
    FROM customers
    WHERE customer_id = p_cus_id;
END;

DROP PROCEDURE getCustomer;


SELECT * from customers;


