# -- V1__create_tables.sql
#
# -- Create tables
# CREATE TABLE students (
#                           student_id VARCHAR(20) PRIMARY KEY,
#                           first_name VARCHAR(50) NOT NULL,
#                           last_name VARCHAR(50) NOT NULL,
#                           email VARCHAR(100) NOT NULL UNIQUE,
#                           photograph_path VARCHAR(255),
#                           cgpa DECIMAL(4,2),
#                           total_credits INTEGER,
#                           graduation_year INTEGER
# );
#
# CREATE TABLE organizations (
#                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                name VARCHAR(200) NOT NULL UNIQUE,
#                                address VARCHAR(255)
# );
#
# CREATE TABLE alumni (
#                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                         student_id VARCHAR(20) NOT NULL UNIQUE,
#                         email VARCHAR(100) NOT NULL UNIQUE,
#                         contact_number VARCHAR(20) NOT NULL,
#                         password VARCHAR(100) NOT NULL,
#                         FOREIGN KEY (student_id) REFERENCES students(student_id)
# );
#
# CREATE TABLE alumni_education (
#                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                   alumni_id BIGINT NOT NULL,
#                                   degree VARCHAR(100) NOT NULL,
#                                   college_name VARCHAR(200) NOT NULL,
#                                   address VARCHAR(500),
#                                   joining_year INTEGER NOT NULL,
#                                   passing_year INTEGER NOT NULL,
#                                   FOREIGN KEY (alumni_id) REFERENCES alumni(id)
# );
#
# CREATE TABLE alumni_organisation (
#                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                                      alumni_id BIGINT NOT NULL,
#                                      organisation_id BIGINT NOT NULL,
#                                      position VARCHAR(100) NOT NULL,
#                                      joining_date DATE NOT NULL,
#                                      leaving_date DATE,
#                                      FOREIGN KEY (alumni_id) REFERENCES alumni(id),
#                                      FOREIGN KEY (organisation_id) REFERENCES organizations(id)
# );

-- create.sql
CREATE TABLE students (
                          student_id VARCHAR(20) PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          photograph_path VARCHAR(255),
                          cgpa DECIMAL(4,2),
                          total_credits INTEGER,
                          graduation_year INTEGER
);

CREATE TABLE organizations (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(200) NOT NULL UNIQUE,
                               address VARCHAR(255)
);

CREATE TABLE alumni (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        student_id VARCHAR(20) NOT NULL UNIQUE,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        contact_number VARCHAR(20) NOT NULL,
                        password VARCHAR(100) NOT NULL
);

CREATE TABLE alumni_education (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  alumni_id BIGINT NOT NULL,
                                  degree VARCHAR(100) NOT NULL,
                                  college_name VARCHAR(200) NOT NULL,
                                  address VARCHAR(500),
                                  joining_year INTEGER NOT NULL,
                                  passing_year INTEGER NOT NULL
);

CREATE TABLE alumni_organisation (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     alumni_id BIGINT NOT NULL,
                                     organisation_id BIGINT NOT NULL,
                                     position VARCHAR(100) NOT NULL,
                                     joining_date DATE NOT NULL,
                                     leaving_date DATE
);