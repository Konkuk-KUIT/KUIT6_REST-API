-- schema.sql
DROP TABLE IF EXISTS Student_Clubs;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Clubs;

CREATE TABLE Students
(
    student_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_number INTEGER     NOT NULL UNIQUE,
    name           VARCHAR(30) NOT NULL
);

CREATE TABLE Clubs
(
    club_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200)
);

CREATE TABLE Student_Clubs
(
    student_club_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    join_date       DATE,
    student_id      BIGINT NOT NULL,
    club_id         BIGINT NOT NULL,
    CONSTRAINT fk_sc_student FOREIGN KEY (student_id) REFERENCES Students (student_id),
    CONSTRAINT fk_sc_club FOREIGN KEY (club_id) REFERENCES Clubs (club_id),
    CONSTRAINT ux_student_club UNIQUE (student_id, club_id)
);
