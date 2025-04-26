 CREATE TABLE IF NOT EXISTS receiver_account (
    receiver_id SERIAL PRIMARY KEY,
    bank_account_id BIGINT NOT NULL,
    receiver_name VARCHAR(100) NOT NULL,
    receiver_account_number VARCHAR(20) NOT NULL,
    receiver_bank_name VARCHAR(100) NOT NULL,
    receiver_currency VARCHAR(10) NOT NULL,
    status VARCHAR(20) DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (bank_account_id) REFERENCES bank_account(account_id) ON DELETE CASCADE
);

