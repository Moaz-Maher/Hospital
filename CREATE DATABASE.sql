-- 1.
CREATE DATABASE Hospital;

-- 2.
USE Hospital;

CREATE TABLE Doctor
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    degree VARCHAR(50),
    grad_year INT,
    specialization VARCHAR(50) NOT NULL,
    CHECK (degree IN ('Bachelor', 'Master', 'Doctoral')),
    CHECK (grad_year BETWEEN 1000 AND 3000)
);

CREATE TABLE Patient
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    gender VARCHAR(25),
    date_of_birth DATE,
    city VARCHAR(50),
    country VARCHAR(50),
    street_number INT,
    building_number INT,
    email VARCHAR(100) UNIQUE NOT NULL,
    CHECK (gender IN ('Male', 'Female'))
);

CREATE TABLE Examine
(
    doctor_id BIGINT,
    patient_id BIGINT,
    date DATETIME,
    diagnosis VARCHAR(255),
    PRIMARY KEY (doctor_id, patient_id, date),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

CREATE TABLE Patient_Phone
(
    patient_id BIGINT,
    phone VARCHAR(50),
    PRIMARY KEY (patient_id, phone),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

CREATE TABLE Specialization
(
    name VARCHAR(50) PRIMARY KEY,
    start_date DATE
);

CREATE TABLE Manage
(
    doctor_id BIGINT PRIMARY KEY,
    specialization VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
    FOREIGN KEY (specialization) REFERENCES Specialization(name)
);

CREATE TABLE Appointment
(
    doctor_id BIGINT,
    day VARCHAR(25),
    shift_number TINYINT,
    clinic_id INT NOT NULL,
    PRIMARY KEY (doctor_id, day, shift_number),
    FOREIGN KEY (doctor_id) REFERENCES  Doctor(id) ON DELETE CASCADE,
    -- *********
    CHECK (shift_number BETWEEN 1 AND 6),
    CHECK (day IN ('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'))
);

CREATE TABLE Clinic
(
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50),
    floor INT,
    specialization VARCHAR(50) NOT NULL,
    FOREIGN KEY (specialization) REFERENCES  Specialization(name) 
  ON UPDATE CASCADE
  ON DELETE CASCADE,
    CHECK (floor BETWEEN 0 AND 10)
);

CREATE TABLE Operation_details
(
    id INT PRIMARY KEY IDENTITY(1,1),
    description VARCHAR(1000),
    date DATETIME,
    clinic_id INT NOT NULL,
    FOREIGN KEY (clinic_id) REFERENCES Clinic(id),
);

CREATE TABLE Perform_operation
(
    doctor_id BIGINT,
    operation_id INT,
    patient_id BIGINT,
    PRIMARY Key(doctor_id, operation_id, patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
    FOREIGN KEY (operation_id) REFERENCES Operation_details(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id),
);

CREATE TABLE Nurse
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    supervizor_id BIGINT NOT NULL,
    FOREIGN KEY (supervizor_id) REFERENCES  Doctor(id),
);

CREATE TABLE Operation_help
(
    nurse_id BIGINT,
    operation_id INT,
    PRIMARY KEY (nurse_id, operation_id),
    FOREIGN KEY (nurse_id) REFERENCES Nurse(id),
    FOREIGN KEY (operation_id) REFERENCES Operation_details(id),
);

CREATE TABLE Room
(
    id INT PRIMARY KEY IDENTITY(1,1),
    floor INT,
    capacity INT DEFAULT 0,
    CHECK (floor BETWEEN 0 AND 10)
);

CREATE TABLE Patient_stay
(
    patient_id BIGINT,
    room_id INT,
    entry DATETIME,
    leave DATETIME,
    PRIMARY KEY (patient_id, room_id, entry),
    FOREIGN KEY (patient_id) REFERENCES Patient(id),
    FOREIGN KEY (room_id) REFERENCES Room(id),
);

CREATE TABLE Take_care
(
    nurse_id BIGINT,
    room_id INT,
    PRIMARY KEY (nurse_id, room_id),
    FOREIGN KEY (nurse_id) REFERENCES Nurse(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Room(id) ON DELETE CASCADE,
);

-- 3.
INSERT INTO Specialization
    (name, start_date)
VALUES
    ('Otolaryngology (ENT)', '2024-01-01'),
    ('General Medicine', '2024-01-01'),
    ('Ophthalmology', '2024-01-01'),
    ('Pediatrics', '2024-01-01'),
    ('Gastroenterology', '2024-01-01'),
    ('Orthopedics', '2024-01-01'),
    ('Dermatology', '2024-01-01');

-- 4.
ALTER TABLE Doctor 
ADD FOREIGN KEY (specialization) REFERENCES Specialization(name);

ALTER TABLE Appointment
ADD FOREIGN KEY (clinic_id) REFERENCES Clinic(id);
GO

CREATE TRIGGER trg_EnsureDoctorClinicSpecialization
ON Appointment
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1
    FROM Inserted i
        JOIN Doctor d ON i.doctor_id = d.id
        JOIN Clinic c ON i.clinic_id = c.id
    WHERE d.specialization != c.specialization
    )
    BEGIN
        RAISERROR ('Doctor''s specialization must match the clinic''s specialization.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO

CREATE TRIGGER trg_CheckRoomCapacity
ON Patient_stay
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    IF EXISTS (
        SELECT 1
    FROM Inserted i
        JOIN Room r ON i.room_id = r.id
        LEFT JOIN Patient_stay ps
        ON ps.room_id = r.id AND ps.leave IS NULL
    WHERE r.capacity <= (
            SELECT COUNT(*)-1
    FROM Patient_stay ps_active
    WHERE ps_active.room_id = r.id AND ps_active.leave IS NULL
        )
    )
    BEGIN
        RAISERROR ('Room has reached its maximum capacity.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO

CREATE TRIGGER trg_EnsureDoctorSpecializationMatch
ON Manage
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    IF EXISTS (
        SELECT 1
    FROM Inserted i
        JOIN Doctor d ON i.doctor_id = d.id
    WHERE d.specialization != i.specialization
    )
    BEGIN
        RAISERROR ('Doctor can only manage their own specialization.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO