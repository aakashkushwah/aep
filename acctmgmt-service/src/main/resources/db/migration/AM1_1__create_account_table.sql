-- Table for storing customers
-- CREATE TABLE customers (
--                            customer_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for the customer
--                            first_name VARCHAR(50) NOT NULL,              -- First name of the customer
--                            last_name VARCHAR(50) NOT NULL,               -- Last name of the customer
--                            email VARCHAR(100) UNIQUE,                    -- Email address of the customer
--                            phone_number VARCHAR(15),                     -- Phone number of the customer
--                            address TEXT,                                 -- Address of the customer
--                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Record creation timestamp
-- );

-- Table for storing bank accounts
- Create the parent table
CREATE TABLE bank_account (
    account_id SERIAL PRIMARY KEY,  -- Unique identifier for the account
    account_number VARCHAR(20) NOT NULL,  -- Unique account number
    customer_id BIGINT NOT NULL,  -- Foreign key to associate with the customer
    account_type VARCHAR(10) NOT NULL,  -- Type of account
    balance DECIMAL(15, 2),  -- Account balance, default to 0.00
    currency VARCHAR(10) DEFAULT 'USD' NOT NULL,  -- Currency type (USD, EUR, etc.)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) PARTITION BY HASH (account_number);

-- Create partitions
CREATE TABLE bank_account_partition_1 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 0);

CREATE TABLE bank_account_partition_2 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 1);

CREATE TABLE bank_account_partition_3 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 2);

CREATE TABLE bank_account_partition_4 PARTITION OF bank_account
    FOR VALUES WITH (MODULUS 4, REMAINDER 3);
--
-- -- Table for recording account transactions
-- CREATE TABLE transactions (
--                               transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for the transaction
--                               account_id BIGINT NOT NULL,                       -- Foreign key to the bank account
--                               transaction_type ENUM('DEPOSIT', 'WITHDRAWAL', 'TRANSFER') NOT NULL, -- Type of transaction
--                               amount DECIMAL(15, 2) NOT NULL,                   -- Transaction amount
--                               transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Date and time of the transaction
--                               description VARCHAR(255),                         -- Optional description of the transaction
--                               FOREIGN KEY (account_id) REFERENCES bank_accounts(account_id) -- Link to account
-- );
