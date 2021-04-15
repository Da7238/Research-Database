/* Research Database Project 2021 ISTE 330
Darlene Ardila
Colton Bailiff
Weijie Chen
Ben Donahue
*/

DROP DATABASE IF EXISTS researchDatabase;
CREATE DATABASE researchDatabase;

USE researchDatabase;

DROP TABLE IF EXISTS interest;
CREATE TABLE interest (
    interestID INT NOT NULL AUTO_INCREMENT,
    interestName VARCHAR(255) NOT NULL,
    PRIMARY KEY (interestID)
)   ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO interest(interestID, interestName) VALUES (1, 'php');
INSERT INTO interest(interestID, interestName) VALUES (2, 'java');
commit;

CREATE TABLE public (
  publicID INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  userName VARCHAR(255) NOT NULL,
  pubEmail VARCHAR(255) NOT NULL,
  interestId INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (publicID),
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
			ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO public(publicID, firstName, lastName, userName, pubEmail, interestID, password) VALUES (1, 'pubF', 'pubL', 'pubU', 'pubE', 1, 'dsa123');
INSERT INTO public(publicID, firstName, lastName, userName, pubEmail, interestID, password) VALUES (2, 'pubF2', 'pubL2', 'pubU2', 'pubE2', 2, 'dsa123');

DROP TABLE IF EXISTS topic;
CREATE TABLE topic (
  topicID INT NOT NULL AUTO_INCREMENT,
  topicDescribtion VARCHAR(255),
  topicTag VARCHAR(20) NOT NULL,
  PRIMARY KEY (topicID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO topic(topicID, topicDescribtion, topicTag) VALUES (1, 'javaresearch', 'ja');
INSERT INTO topic(topicID, topicDescribtion, topicTag) VALUES (2, 'phpresearch', 'php');

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  studentID INT NOT NULL AUTO_INCREMENT,
  publicID INT NOT NULL,
  studentName VARCHAR(255) NOT NULL,
  interestId INT NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (studentID),
  FOREIGN KEY (publicID) REFERENCES public(publicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
			ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO student(studentID, publicID, studentName, interestId, email) VALUES (1, 2, 'jimm', 2, '123.edu');

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
  facultyID INT NOT NULL AUTO_INCREMENT,
  facultyName VARCHAR(255),
  department VARCHAR(255) NOT NULL,
  abstract VARCHAR(255) NOT NULL,
  publicID INT NOT NULL,
  topicID INT NOT NULL,
  PRIMARY KEY (facultyID),
  FOREIGN KEY (publicID) REFERENCES public(publicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (topicID) REFERENCES topic(topicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO faculty(facultyID, facultyName, department, abstract, publicID, topicID) VALUES (1, 'jimm', 'IS', 'aofdjaeoei', 1, 1);

DROP TABLE IF EXISTS author;
CREATE TABLE author (
	  authorID INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    articlesPublished INT,
    PRIMARY KEY (authorID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO author(authorID, firstName, lastName, articlesPublished) VALUES (1, 'kevin', 'll', '2');

DROP TABLE IF EXISTS article;
CREATE TABLE article (
	  articleID INT NOT NULL AUTO_INCREMENT,
    topicID INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    authorID INT NOT NULL,
    articleDescription VARCHAR(255),
    publishDate DATE,
    PRIMARY KEY (articleID),
    FOREIGN KEY (topicID) REFERENCES topic(topicID)
			      ON DELETE CASCADE
            ON UPDATE CASCADE,
	  FOREIGN KEY (authorID) REFERENCES author(authorID)
			      ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO article(articleID, topicID, title, authorID, articleDescription, publishDate) VALUES (1, 1, 'food', 1, 'foodw', '1991-01-01');

DROP TABLE IF EXISTS department;
CREATE TABLE department (
  departmentID INT NOT NULL AUTO_INCREMENT,
  facultyID INT NOT NULL,
  departmentName VARCHAR(255) NOT NULL,
  PRIMARY KEY (departmentID),
  FOREIGN KEY (facultyID) REFERENCES faculty(facultyID)
			      ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO department(departmentID, facultyID, departmentName) VALUES (1, 1, 'math');

DROP TABLE IF EXISTS major;
CREATE TABLE major (
	majorID INT NOT NULL AUTO_INCREMENT,
    studentID INT NOT NULL,
    majorName VARCHAR(100) NOT NULL,
    majorDescription VARCHAR(255),
    PRIMARY KEY (majorID),
    FOREIGN KEY (studentID) REFERENCES student(studentID)
			      ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO major(majorID, studentID, majorName, majorDescription) VALUES (1, 1,'math', '1+1');
