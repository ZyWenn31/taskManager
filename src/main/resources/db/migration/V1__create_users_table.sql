CREATE TABLE users (
       user_id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
       username VARCHAR(50) UNIQUE NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       password varchar NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);