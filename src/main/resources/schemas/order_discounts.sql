CREATE TABLE IF NOT EXISTS order_discounts
(
    id                  INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_id            INTEGER                  NOT NULL,
    product_id          INTEGER                  NOT NULL,
    voucher_code        VARCHAR,
    discount_amount     DECIMAL                  NOT NULL,
    discount_percentage DECIMAL                  NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by          VARCHAR                  NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
