--liquibase formatted sql

--changeset electron3d:1
CREATE TABLE parking_garage (
    capacity INTEGER,
    construction_date DATE,
    parking_rate INTEGER,
    id BIGINT NOT NULL AUTO_INCREMENT,
    building VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    name VARCHAR(255),
    state VARCHAR(255),
    street VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE parking_slot (
    occupied BIT NOT NULL,
    slot_number INTEGER,
    id BIGINT NOT NULL AUTO_INCREMENT,
    parking_garage_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE car (
    id BIGINT NOT NULL AUTO_INCREMENT,
    parking_slot_id BIGINT,
    parking_started DATETIME(6),
    brand VARCHAR(255),
    license_plate VARCHAR(255),
    model VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE driver (
    birthday DATE,
    id BIGINT NOT NULL AUTO_INCREMENT,
    driver_license VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE car_driver (
    car_id BIGINT NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY (car_id, driver_id)
);
ALTER TABLE car ADD CONSTRAINT UK_pao88ggwngqnq0r9cxehl3i79
    UNIQUE (parking_slot_id);
ALTER TABLE car ADD CONSTRAINT FK32u9f6dnu1ti5407ykprhnle4
    FOREIGN KEY (parking_slot_id) REFERENCES parking_slot (id);
ALTER TABLE car_driver ADD CONSTRAINT FKrnrbra75siihkc0pxstlyowgx
    FOREIGN KEY (car_id) REFERENCES car (id);
ALTER TABLE car_driver ADD CONSTRAINT FKqfgeoqa4bx2fhoa3bhwtijxb4
    FOREIGN KEY (driver_id) REFERENCES driver (id);
ALTER TABLE parking_slot ADD CONSTRAINT FKhbp9paljf4v8starvmf2996u6
    FOREIGN KEY (parking_garage_id) REFERENCES parking_garage (id);