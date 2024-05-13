CREATE DATABASE FPT_SESSION10;

USE FPT_SESSION10;

CREATE TABLE customers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) not null,
    address varchar(255) not null,
    email varchar(255) not null
);

CREATE TABLE Order_table(
    id INT PRIMARY KEY AUTO_INCREMENT,
    customerID INT NOT NULL ,
    orderDate DATE NOT NULL ,
    totalAmount DOUBLE ,
    status VARCHAR(255) NOT NULL ,
    CONSTRAINT cusID FOREIGN KEY (customerID) REFERENCES customers(id)
);

CREATE TABLE Product(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(255) NOT NULL ,
    Price DOUBLE NOT NULL ,
    description VARCHAR(255)
);

CREATE TABLE OrderDetail(
    id INT PRIMARY KEY AUTO_INCREMENT,
    orderID INT NOT NULL ,
    productID INT NOT NULL ,
    quantity INT NOT NULL ,
    unitPrice DOUBLE NOT NULL ,
    discount DOUBLE DEFAULT 0,
    totalPrice DOUBLE NOT NULL,
    CONSTRAINT orderID FOREIGN KEY (orderID) REFERENCES Order_table(id),
    CONSTRAINT productID FOREIGN KEY (productID) REFERENCES Product(id)
);

DROP TABLE customers;
DROP TABLE Order_table;
DROP TABLE OrderDetail;
DROP TABLE Product;

SELECT * FROM Product;
SELECT * FROM customers;
SELECT * FROM Order_table;
SELECT * FROM OrderDetail;

SELECT o.id AS OrderID,
       customerID,
       c.name AS CustomerName,
       o.orderDate,
       o.totalAmount,
       o.status
FROM Order_table o
INNER JOIN customers c on o.customerID = c.id;

SELECT o.id AS Order_Detail_ID
FROM OrderDetail o
INNER JOIN customers c on orderID;

SELECT od.id AS Order_Detail_ID,
       c.name AS CustomerName,
       p.ProductName,
       od.quantity,
       od.unitPrice,
       od.discount,
       od.totalPrice
FROM OrderDetail od
INNER JOIN Order_table o on od.orderID = o.id
INNER JOIN customers c on o.customerID = c.id
INNER JOIN Product p on od.productID = p.id
WHERE orderID = 1;

SELECT totalPrice FROM OrderDetail od
INNER JOIN Order_table Ot on od.orderID = Ot.id
WHERE customerID = 1;


INSERT INTO customers VALUES ('Quan', 'Han', 'Abc');

SELECT o.id AS OrderID,
            o.orderDate,
            o.totalAmount,
            o.status
            FROM Order_table o
            WHERE o.customerID = ?;

UPDATE Order_table SET status = '?' WHERE id = ?;
SELECT * FROM Order_table;