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


CREATE OR REPLACE FUNCTION check_user_id_exists()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (SELECT * FROM Patients WHERE patient_id = NEW.user_id) AND
       NOT EXISTS (SELECT * FROM Doctors WHERE doctor_id = NEW.user_id) AND
       NOT EXISTS (SELECT * FROM Nurses WHERE nurse_id = NEW.user_id) THEN
        RAISE EXCEPTION 'user_id % does not exist in Patients, Doctors, or Nurses', NEW.user_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_user_id_before_insert
BEFORE INSERT ON Users
FOR EACH ROW
EXECUTE PROCEDURE check_user_id_exists();