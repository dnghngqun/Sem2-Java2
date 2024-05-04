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
    customerID INT,
    orderDate DATE,
    totalAmount DOUBLE,
    CONSTRAINT cusID FOREIGN KEY (customerID) REFERENCES customers(id)
);

CREATE TABLE Product(
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(255),
    Price DOUBLE,
    Description VARCHAR(255)
);

CREATE TABLE OrderDetail(
    id INT PRIMARY KEY,
    orderID INT,
    productID INT,
    quantity INT,
    unitPrice DOUBLE,
    discount DOUBLE,
    totalPrice DOUBLE,
    CONSTRAINT orderID FOREIGN KEY (orderID) REFERENCES Order_table(id),
    CONSTRAINT productID FOREIGN KEY (productID) REFERENCES Product(ProductID)
);