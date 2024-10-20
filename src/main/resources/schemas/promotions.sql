CREATE TABLE IF NOT EXISTS promotions
(
    id                  INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR                  NOT NULL,
    description         VARCHAR,
    image               TEXT,
    rule                JSONB                    NOT NULL,
    voucher_code        VARCHAR,
    discount_amount     DECIMAL                  NOT NULL,
    discount_percentage DECIMAL                  NOT NULL,
    starts_at           TIMESTAMP WITH TIME ZONE NOT NULL,
    ends_at             TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_by          VARCHAR                  NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_by          VARCHAR                  NOT NULL
);

CREATE INDEX IF NOT EXISTS index_promotions_voucher_code ON promotions (voucher_code);