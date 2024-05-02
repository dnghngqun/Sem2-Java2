USE ecommerceFPT;

CREATE TABLE users(
    user_id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users VALUES(1, 'admin', 'admin');

SELECT username FROM users WHERE username like 'admin' AND password like 'admin'