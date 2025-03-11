CREATE TABLE orders
(
    id            SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
