
INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('peter', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('peter', 'ROLE_ADMIN');


INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES (1, 'Bart.graaf06@gmail.com', 'Bart', 'de Graaf', '0639783289');
INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES ((SELECT MAX(id)+1 FROM customer), 'Bart.graaf06+2@gmail.com', 'Jan', 'de Graaf', '06111222333');
INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES ((SELECT MAX(id)+1 FROM customer), 'Bart.graaf06+3@gmail.com', 'Henk', 'de Graaf', '06112222111');

INSERT INTO car (id, customer_id, license_plate, type) VALUES (1, 1, '8-XVT-33', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES ((SELECT MAX(id)+1 FROM car), 1, 'TN-326-G', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES ((SELECT MAX(id)+1 FROM car), 1, 'GB-399-J', 'car');