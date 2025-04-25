-- Table for storing bank accounts
CREATE TABLE if not exists bank_account (
    account_id SERIAL PRIMARY KEY,  -- Unique identifier for the account
    account_number VARCHAR(20) NOT NULL,  -- Unique account number
    customer_id BIGINT NOT NULL,  -- Foreign key to associate with the customer
    account_type VARCHAR(10) NOT NULL,  -- Type of account
    balance DECIMAL(15, 2),  -- Account balance, default to 0.00
    currency VARCHAR(10) DEFAULT 'USD' NOT NULL,  -- Currency type (USD, EUR, etc.)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) PARTITION BY HASH (account_id);

-- Create partitions
CREATE TABLE bank_account_partition_1 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 0);

CREATE TABLE bank_account_partition_2 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 1);

CREATE TABLE bank_account_partition_3 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 2);

CREATE TABLE bank_account_partition_4 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 3);

