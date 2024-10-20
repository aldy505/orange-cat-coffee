CREATE TABLE IF NOT EXISTS order_items
(
    id                  INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id            INTEGER                  NOT NULL,
    product_id          INTEGER                  NOT NULL,
    product_name        VARCHAR                  NOT NULL,
    product_description VARCHAR,
    product_image       TEXT,
    product_category    VARCHAR,
    quantity            SMALLINT                 NOT NULL,
    price               DECIMAL                  NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by          VARCHAR                  NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);