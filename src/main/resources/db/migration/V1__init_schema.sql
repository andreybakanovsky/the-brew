CREATE TABLE coffee
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)   NOT NULL,
    size INTEGER        NOT NULL,
    cost NUMERIC(10, 2) NOT NULL
);
