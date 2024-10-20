-- stores.sql
CREATE TABLE IF NOT EXISTS stores
(
    id                    INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                  VARCHAR                  NOT NULL,
    description           VARCHAR,
    image                 TEXT,
    address               TEXT                     NOT NULL,
    coordinates_latitude  DECIMAL                  NOT NULL,
    coordinates_longitude DECIMAL                  NOT NULL,
    disabled              BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by            VARCHAR                  NOT NULL,
    updated_at            TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by            VARCHAR                  NOT NULL
);

CREATE INDEX IF NOT EXISTS index_stores_name ON stores (name);