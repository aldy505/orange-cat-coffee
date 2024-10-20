-- products.sql

CREATE TABLE IF NOT EXISTS products
(
    id            INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name          VARCHAR                  NOT NULL,
    price         DECIMAL                  NOT NULL,
    description   VARCHAR,
    image         TEXT,
    maximum_order SMALLINT,
    disabled      BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by    VARCHAR                  NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by    VARCHAR                  NOT NULL,
    catalog_id    INTEGER                  NOT NULL,
    FOREIGN KEY (catalog_id) REFERENCES catalogs (id)
);

CREATE INDEX IF NOT EXISTS index_products_name ON products (name);