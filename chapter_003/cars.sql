-- Creating tables
CREATE TABLE car_body (
    id serial PRIMARY KEY,
    body_name VARCHAR(100) NOT NULL
);

CREATE TABLE engine (
    id serial PRIMARY KEY,
    engine_name VARCHAR(100) NOT NULL
);

CREATE TABLE transmission (
    id serial PRIMARY KEY,
    transmission_name VARCHAR(100) NOT NULL
);

CREATE TABLE car (
    id serial PRIMARY KEY,
    car_name VARCHAR(100),
    body_id INT NOT NULL REFERENCES car_body (id),
    engine_id INT NOT NULL REFERENCES car_body (id),
    transmission_id INT NOT NULL REFERENCES transmission (id)
);

-- Inserting data
INSERT INTO car_body (body_name)
VALUES ('Sedan'),
       ('Cabriolet'),
       ('Wagon'),
       ('Hatchback');

INSERT INTO engine (engine_name)
VALUES ('1.5'),
       ('1.8'),
       ('2.0'),
       ('2.4');

INSERT INTO transmission (transmission_name)
VALUES ('automatic'),
       ('manual');

INSERT INTO car (car_name, body_id, engine_id, transmission_id)
VALUES ('VAZ-2106', 1, 1, 2),
       ('BMW Z4', 2, 3, 2);

-- Queries
-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT car_name, cb.body_name, e.engine_name, t.transmission_name
FROM car
    JOIN car_body cb on car.body_id = cb.id
    JOIN engine e on car.engine_id = e.id
    JOIN transmission t on car.transmission_id = t.id
ORDER BY car_name;

-- 2. Вывести отдельно детали, которые не используются в машине: кузова, двигатели, коробки передач.
-- Car body
SELECT cb.body_name "Body_name not used"
FROM car_body cb
    LEFT JOIN car c on cb.id = c.body_id
WHERE c.car_name IS NULL;

-- Engine
SELECT e.engine_name "Engine not used"
FROM engine e
    LEFT JOIN car c on e.id = c.engine_id
WHERE c.car_name IS NULL;

-- Transmission
SELECT t.transmission_name "Transmission not used"
FROM transmission t
    LEFT JOIN car c on t.id = c.transmission_id
WHERE c.car_name IS NULL;