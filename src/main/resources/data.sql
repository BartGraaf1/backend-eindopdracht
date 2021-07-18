INSERT INTO users (username, password, enabled) VALUES ('user_mechanic', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('user_administrative_employee', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user_mechanic', 'ROLE_MECHANIC');
INSERT INTO authorities (username, authority) VALUES ('user_administrative_employee', 'ROLE_MECHANIC');
INSERT INTO authorities (username, authority) VALUES ('user_administrative_employee', 'ROLE_ADMIN');

INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES (1, 'Bart.graaf06@gmail.com', 'Bart', 'de Graaf', '0639783289');
INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES (2, 'JanWillem@gmail.com', 'Jan-Willem', 'Koelewijn', '06546788990');
INSERT INTO customer (id, email_address, firstname, lastname, phone_number) VALUES (3, 'Henk@gmail.com', 'Henk', 'Bos', '06703561220');

INSERT INTO car (id, customer_id, license_plate, type) VALUES (1, 1, '8-XVT-33', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES (2, 2, 'TN-326-G', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES (3, 2, 'GB-399-J', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES (4, 3, '11-NP-XV', 'car');
INSERT INTO car (id, customer_id, license_plate, type) VALUES (5, 3, 'GH-SZ-95', 'car');

INSERT INTO car_part_stock (id, description, price, stock) VALUES (1, 'MICHELIN PRIMACY 4', 109.39, 50);
INSERT INTO car_part_stock (id, description, price, stock) VALUES (2, 'SACHS Clutch Kit', 1200.10, 5);
INSERT INTO car_part_stock (id, description, price, stock) VALUES (3, 'BOSCH Oil Filter', 12.16, 20);
INSERT INTO car_part_stock (id, description, price, stock) VALUES (4, 'BOSCH Air Filter', 11.97, 25);
INSERT INTO car_part_stock (id, description, price, stock) VALUES (5, 'Kroon-Oil 04214 Coolant SP 12 1L', 4.95, 30);
INSERT INTO car_part_stock (id, description, price, stock) VALUES (6, 'RADIATOR 76002002 International Radiators', 30.22, 10);

INSERT INTO car_issue (id, issue_description, car_id) VALUES (1, 'Oil filter needs to be replaced', 1);
INSERT INTO car_issue (id, issue_description, car_id) VALUES (2, 'The clutch plates are all the way worn down', 2);
INSERT INTO car_issue (id, issue_description, car_id) VALUES (3, 'Tires are all the way worn down', 4);
INSERT INTO car_issue (id, issue_description, car_id) VALUES (4, 'Engine coolant is to old', 4);

INSERT INTO repair_event (id, comment, date_of_event, is_event_approved, is_event_payed, is_routine_service, car_id) VALUES (1, 'This event is just a routine checkup', '2021-07-29 11:44:44.000000', '1', '0', '1', 1);
INSERT INTO repair_event (id, comment, date_of_event, is_event_approved, is_event_payed, is_routine_service, car_id) VALUES (2, 'The clutch need to be changed', '2021-08-19 15:21:12.000000', '1', '0', '1', 2);
INSERT INTO repair_event (id, comment, date_of_event, is_event_approved, is_event_payed, is_routine_service, car_id) VALUES (3, 'Tires need to be changed', '2021-07-21 08:39:44.000000', '1', '0', '1', 4);
INSERT INTO repair_event (id, comment, date_of_event, is_event_approved, is_event_payed, is_routine_service, car_id) VALUES (4, 'Car overheats after 30 minutes of driving', '2021-07-12 10:11:22.000000', '1', '0', '1', 4);

INSERT INTO repair_action (id, cost, description, repair_event_id) VALUES (1, 0.00, 'Just the regular checkup, replaced the oil filter.', 1);
INSERT INTO repair_action (id, cost, description, repair_event_id) VALUES (2, 240.00, 'Changed the clutch, took around 3 hours.', 2);
INSERT INTO repair_action (id, cost, description, repair_event_id) VALUES (3, 40.00, 'Changed the tires, took around 30 minutes', 3);
INSERT INTO repair_action (id, cost, description, repair_event_id) VALUES (4, 20.00, 'Air filter is replaced', 4);
INSERT INTO repair_action (id, cost, description, repair_event_id) VALUES (5, 40.00, 'Coolant is replaced', 4);

INSERT INTO car_part_used (id, amount_used, car_part_stock_id, repair_action_id) VALUES (1, 1, 3, 1);
INSERT INTO car_part_used (id, amount_used, car_part_stock_id, repair_action_id) VALUES (2, 1, 2, 2);
INSERT INTO car_part_used (id, amount_used, car_part_stock_id, repair_action_id) VALUES (3, 4, 1, 3);
INSERT INTO car_part_used (id, amount_used, car_part_stock_id, repair_action_id) VALUES (4, 1, 4, 4);
INSERT INTO car_part_used (id, amount_used, car_part_stock_id, repair_action_id) VALUES (5, 1, 5, 5);