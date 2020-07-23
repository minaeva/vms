CREATE TABLE IF NOT EXISTS machine
(
    id      SERIAL PRIMARY KEY,
    address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS drink
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(30) NOT NULL,
    volume     VARCHAR(10) NOT NULL,
    machine_id SERIAL REFERENCES machine (id)
);


