CREATE TABLE IF NOT EXISTS orders
(
    id                       INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id              INTEGER                  NOT NULL,
    store_id                 INTEGER                  NOT NULL,
    status                   SMALLINT                 NOT NULL,
    order_type               SMALLINT                 NOT NULL,
    delivery_address         TEXT,
    delivery_instructions    TEXT,
    delivery_tracking_number TEXT,
    payment_method           SMALLINT                 NOT NULL,
    payment_instructions     TEXT,
    payment_tracking_number  TEXT,
    total_items              INTEGER                  NOT NULL,
    total_discounts          DECIMAL                  NOT NULL,
    total_price              DECIMAL                  NOT NULL,
    rating                   SMALLINT,
    review                   TEXT,
    created_at               TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by               VARCHAR                  NOT NULL,
    updated_at               TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by               VARCHAR                  NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (store_id) REFERENCES stores (id)
);

