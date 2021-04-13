/* Research Database Project 2021 ISTE 330
Darlene Ardila
Colton Bailiff
Weijie Chen
Ben Donahue
*/

DROP DATABASE IF EXISTS researchDatabase;
CREATE DATABASE researchDatabase;

USE researchDatabase;

DROP TABLE IF EXISTS public;
CREATE TABLE public (
  publicID INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  userName VARCHAR(255) NOT NULL,
  pubEmail VARCHAR(255) NOT NULL,
  InterestId VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (publicID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS topic;
CREATE TABLE topic (
  topicID INT NOT NULL AUTO_INCREMENT,
  topicDescribtion VARCHAR(255),
  topicTag VARCHAR(20) NOT NULL,
  PRIMARY KEY (topicID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  studentID INT NOT NULL AUTO_INCREMENT,
  publicID INT NOT NULL,
  studentName VARCHAR(255) NOT NULL,
  interestID VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (studentID),
  FOREIGN KEY (publicID) REFERENCES public(publicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
	          ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
  facultyID INT NOT NULL AUTO_INCREMENT,
  facultyName VARCHAR(255),
  department VARCHAR(255) NOT NULL,
  abstract VARCHAR(255) NOT NULL,
  publicID INT NOT NULL,
  topicID INT NOT NULL,
  subjectID INT NOT NULL,
  interestID VARCHAR (255),
  PRIMARY KEY (facultyID),
  FOREIGN KEY (publicID) REFERENCES public(publicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (topicID) REFERENCES topic(topicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
	          ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS author;
CREATE TABLE author (
	  authorID INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    articlesPublished INT,
    PRIMARY KEY (authorID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS department;
CREATE TABLE department (
  departmentID INT NOT NULL AUTO_INCREMENT,
  facultyID INT NOT NULL,
  departmentName VARCHAR(255) NOT NULL,
  universityID VARCHAR(10) NOT NULL,
  PRIMARY KEY (departmentID),
  FOREIGN KEY (facultyID) REFERENCES faculty(facultyID)
			      ON DELETE CASCADE
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS interest;
CREATE TABLE interest (
    interestID INT NOT NULL,
    interestName VARCHAR(255) NOT NULL,
    PRIMARY KEY (interestID)
)   ENGINE=InnoDB DEFAULT CHARSET=utf8;
