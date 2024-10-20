CREATE TABLE IF NOT EXISTS customers
(
    id              INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    email           VARCHAR                  NOT NULL UNIQUE,
    password        TEXT                     NOT NULL,
    full_name       VARCHAR                  NOT NULL,
    gender          SMALLINT,
    birthday        DATE,
    phone_number    VARCHAR,
    profile_picture TEXT,
    disabled        BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by      VARCHAR                  NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by      VARCHAR                  NOT NULL,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    deleted_by      VARCHAR
);

CREATE INDEX IF NOT EXISTS index_customers_birthday ON customers (birthday);
