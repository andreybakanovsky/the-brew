CREATE TABLE order_items
(
    id        SERIAL PRIMARY KEY,
    order_id  INT NOT NULL REFERENCES orders (id) ON DELETE CASCADE,
    coffee_id INT NOT NULL REFERENCES coffee (id),
    quantity  INT NOT NULL DEFAULT 1,
    UNIQUE (order_id, coffee_id)
);
