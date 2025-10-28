-- schema.sql
DROP TABLE IF EXISTS Club_Members;
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
    description VARCHAR(200),
    status      ENUM('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE Club_Members
(
    club_member_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    join_date     DATE,
    student_id    BIGINT NOT NULL,
    club_id       BIGINT NOT NULL,
    CONSTRAINT fk_cm_student FOREIGN KEY (student_id) REFERENCES Students (student_id) ON DELETE CASCADE,
    CONSTRAINT fk_cm_club FOREIGN KEY (club_id) REFERENCES Clubs (club_id) ON DELETE CASCADE,
    CONSTRAINT ux_club_members UNIQUE (student_id, club_id)
);
