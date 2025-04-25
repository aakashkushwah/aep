 CREATE TABLE if not exists customer (
                            customer_id SERIAL PRIMARY KEY, -- Unique identifier for the customer
                            first_name VARCHAR(50) NOT NULL,              -- First name of the customer
                            last_name VARCHAR(50) NOT NULL,               -- Last name of the customer
                            email VARCHAR(100) UNIQUE,                    -- Email address of the customer
                            phone_number VARCHAR(15),                     -- Phone number of the customer
                            address TEXT,                                 -- Address of the customer
                            status VARCHAR(20) DEFAULT 'active',          -- Status of the customer (e.g., active, inactive)
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Record creation timestamp
 );

