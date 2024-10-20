CREATE TABLE IF NOT EXISTS auth_session
(
    id          INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id INTEGER                  NOT NULL,
    token       VARCHAR                  NOT NULL,
    expires_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE INDEX IF NOT EXISTS index_auth_session_token ON auth_session (token);