INSERT INTO parking_garage (name, capacity, construction_date, street,
                            city, state, country)
VALUES ('Garage A', 100, '2020-01-01', '123 Main St', 'City A', 'State A', 'Country A'),
       ('Garage B', 150, '2019-05-15', '456 Elm St', 'City B', 'State B', 'Country B');
INSERT INTO parking_slot (occupied, slot_number, parking_garage_id)
VALUES (FALSE, 1, 1),
       (FALSE, 2, 1);