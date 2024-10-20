-- catalogs.sql
CREATE TABLE IF NOT EXISTS catalogs
(
    id          INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR                  NOT NULL,
    description VARCHAR,
    image       TEXT,
    disabled    BOOLEAN                  NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by  VARCHAR                  NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by  VARCHAR                  NOT NULL
);