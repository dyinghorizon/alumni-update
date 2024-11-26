-- V2__insert_data.sql
-- Insert some sample data for testing
INSERT INTO students (student_id, first_name, last_name, email, graduation_year) VALUES
('2020MT01', 'John', 'Doe', 'john.doe@example.com', 2024),
('2020MT02', 'Jane', 'Smith', 'jane.smith@example.com', 2024),
('2020MT03', 'Bob', 'Johnson', 'bob.johnson@example.com', 2023);

INSERT INTO organizations (name, address) VALUES
('Tech Corp', 'Bangalore, Karnataka'),
('Innovation Labs', 'Mumbai, Maharashtra'),
('Digital Solutions', 'Hyderabad, Telangana');