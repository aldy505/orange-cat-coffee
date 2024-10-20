CREATE TABLE IF NOT EXISTS auth_login_history
(
    id          INTEGER                  NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_id INTEGER                  NOT NULL,
    ip          VARCHAR                  NOT NULL,
    user_agent  VARCHAR                  NOT NULL,
    login_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);