CREATE TABLE IF NOT EXISTS stores_products
(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    store_id   INTEGER  NOT NULL,
    product_id INTEGER  NOT NULL,
    UNIQUE (store_id, product_id),
    FOREIGN KEY (store_id) REFERENCES stores (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);