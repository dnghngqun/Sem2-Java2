CREATE DATABASE FPT_SESSION10;

USE FPT_SESSION10;

CREATE TABLE customers(
    id INT PRIMARY KEY,
    name varchar(255) not null,
    address varchar(255) not null,
    email varchar(255) not null
);

CREATE TABLE Order_table(
    id INT PRIMARY KEY,
    customerID INT NOT NULL ,
    orderDate DATE NOT NULL ,
    totalAmount DOUBLE NOT NULL ,
    CONSTRAINT cusID FOREIGN KEY (customerID) REFERENCES customers(id)
);


CREATE TABLE Product(
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(255) NOT NULL ,
    Price DOUBLE NOT NULL ,
    Description VARCHAR(255)
);

CREATE TABLE OrderDetail(
    id INT PRIMARY KEY,
    orderID INT NOT NULL ,
    productID INT NOT NULL ,
    quantity INT NOT NULL ,
    unitPrice DOUBLE NOT NULL ,
    discount DOUBLE DEFAULT 0,
    totalPrice DOUBLE NOT NULL ,
    CONSTRAINT orderID FOREIGN KEY (orderID) REFERENCES Order_table(id),
    CONSTRAINT productID FOREIGN KEY (productID) REFERENCES Product(ProductID)
);

DROP TABLE Order_table;
DROP TABLE OrderDetail;
DROP TABLE Product;

SELECT * FROM customers;
SELECT * FROM Product;