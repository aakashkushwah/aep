-- Table for storing bank accounts
CREATE TABLE if not exists notification (
    notification_id SERIAL PRIMARY KEY,
    user_email text NOT NULL,
    subject text NOT NULL,
    email_content text NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

