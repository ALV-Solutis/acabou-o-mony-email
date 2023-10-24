CREATE TABLE emails(
    email_id uuid PRIMARY KEY,
    user_id uuid,
    email_from VARCHAR,
    email_to VARCHAR,
    subject VARCHAR,
    text VARCHAR,
    send_date_email TIMESTAMP,
    status_mail VARCHAR
);