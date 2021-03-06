CREATE DATABASE agric_hub;
\c agric_hub;
CREATE TABLE farmers (id serial PRIMARY KEY, name VARCHAR, location VARCHAR, email VARCHAR);
CREATE TABLE customers (id serial PRIMARY KEY, name VARCHAR, location VARCHAR, email VARCHAR);
CREATE TABLE supplies (id serial PRIMARY KEY, farmerid INTEGER, farmername VARCHAR, productid INTEGER, productname VARCHAR, quantity INTEGER, price INTEGER);
CREATE TABLE orders (id serial PRIMARY KEY, customerid INTEGER, customername VARCHAR, productid INTEGER, productname VARCHAR, quantity INTEGER);
CREATE TABLE products (id serial PRIMARY KEY, name VARCHAR);
CREATE DATABASE agric_hub_test WITH TEMPLATE agric_hub;