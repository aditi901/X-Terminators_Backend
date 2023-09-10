CREATE DATABASE Bug_Tracking_Software;

USE Bug_Tracking_Software;

CREATE TABLE Projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    projectId INT UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    startDate DATE,
    status VARCHAR(50)
);

INSERT INTO Projects (projectId, name, description, startDate, status)
VALUES (100, "Bug_tracking", "Software which will help to manage and fix bugs efficiently", "2023-08-12", "On Progress");
INSERT INTO Projects (projectId, name, description, startDate, status)
VALUES (102, "Meeting Room Booking", "Software which will help to book meeting rooms", "2023-08-20", "On Progress");
INSERT INTO Projects (projectId, name, description, startDate, status)
VALUES (103, "Attendance Marking System", "Software which will help to mark the attendance of the employees", "2023-08-23", "On Progress");

SELECT * FROM projects;

CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO Roles (name) VALUES
('Project Manager'),
('Developer'),
('Tester');

Select * from Roles;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    project_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Roles(id),
    FOREIGN KEY (project_id) REFERENCES Projects(projectId)
);

ALTER TABLE Users AUTO_INCREMENT=1000;

INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Aditi Mishra", "aditi@gmail.com", "", 1, 100);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Kaustav Ganguly", "kaustav@gmail.com", "", 2, 100);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Adit", "adit@gmail.com", "", 2, 100);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Vedika Hripathak", "vedika@gmail.com", "", 3, 100);

INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Aditi Mishra", "aditi@gmail.com", "", 1, 102);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Ananya Joshi", "ananya@gmail.com", "", 2, 102);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Payal", "payal@gmail.com", "", 2, 102);
INSERT INTO Users (name, email, password, role_id, project_id)
VALUES ("Fatehpreet Singh", "fatehpreet@gmail.com", "", 3, 102);

SELECT * FROM Users;

CREATE TABLE Bug (
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
	project_id INT NOT NULL,
    description VARCHAR(200) NOT NULL,
    createdBy INT NOT NULL,
    openDate DATE NOT NULL,
    closeDate DATE NOT NULL,
    assignedTo INT,
    marked_for_closing BOOLEAN,
    closedBy INT,
    closedOn DATE,
    status VARCHAR(50),
    severity_level VARCHAR(50),
    FOREIGN KEY (assignedTo) REFERENCES Users(id),
    FOREIGN KEY (createdBy) REFERENCES Users(id),
    FOREIGN KEY (closedBy) REFERENCES Users(id),
    FOREIGN KEY (project_id) REFERENCES Projects(id)
);

SELECT * from Bug;
