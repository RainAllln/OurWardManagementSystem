CREATE OR REPLACE FUNCTION verify_password_md5(username_input VARCHAR, encrypted_password_input VARCHAR)
RETURNS BOOLEAN AS $$
DECLARE
    stored_password VARCHAR;
BEGIN
    SELECT password INTO stored_password
    FROM Users
    WHERE username = username_input;
    IF stored_password = encrypted_password_input THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END;
$$ LANGUAGE plpgsql;
