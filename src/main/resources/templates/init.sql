INSERT INTO Parking_Garage (name, capacity, construction_year)
VALUES
    ('Garage A', 100, '2010-01-01'),
    ('Garage B', 75, '2015-05-15');
INSERT INTO Driver (first_name, last_name, driver_license, birthday, parking_debt, phone_number)
VALUES
    ('John', 'Doe', 'AB123456', '1990-01-15', 0.0, '123-456-7890'),
    ('Jane', 'Smith', 'CD789012', '1985-07-20', 25.5, '987-654-3210');
INSERT INTO Parking_Slot (occupied, slot_number, parking_garage_id)
VALUES (false, 1, 1);