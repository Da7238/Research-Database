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

DROP TABLE IF EXISTS public;
CREATE TABLE public (
  publicID INT NOT NULL AUTO_INCREMENT,
  publicName VARCHAR(255) NOT NULL,
  userName VARCHAR(255) NOT NULL,
  pubEmail VARCHAR(255) NOT NULL,
  interestId INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (publicID),
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
			ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO public(publicID, publicName, userName, pubEmail, interestID, password) VALUES (1, 'publicName', 'pubU', 'pubE', 1, 'dsa123');
INSERT INTO public(publicID, publicName, userName, pubEmail, interestID, password) VALUES (2, 'publicName', 'pubU2', 'pubE2', 2, 'dsa123');

DROP TABLE IF EXISTS topic;
CREATE TABLE topic (
  topicID INT NOT NULL AUTO_INCREMENT,
  topicDescribtion VARCHAR(255),
  topicTag VARCHAR(20) NOT NULL,
  PRIMARY KEY (topicID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO topic(topicID, topicDescribtion, topicTag) VALUES (1, 'Java research', 'java');
INSERT INTO topic(topicID, topicDescribtion, topicTag) VALUES (2, 'PHP research', 'php');

DROP TABLE IF EXISTS major;
CREATE TABLE major (
	majorID INT NOT NULL AUTO_INCREMENT,
    majorName VARCHAR(100) NOT NULL,
    majorDescription VARCHAR(255),
    PRIMARY KEY (majorID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO major(majorID, majorName, majorDescription) VALUES (1, 'Mathematics', 'Learning how to solve math problems.');

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  studentID INT NOT NULL AUTO_INCREMENT,
  publicID INT NOT NULL, -- FK
  majorID INT NOT NULL, -- FK
  studentName VARCHAR(255) NOT NULL,
  interestID INT NOT NULL, -- FK
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (studentID),
  FOREIGN KEY (publicID) REFERENCES public(publicID)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
  FOREIGN KEY (interestID) REFERENCES interest(interestID)
            ON DELETE CASCADE
			ON UPDATE CASCADE,
  FOREIGN KEY (majorID) REFERENCES major(majorID)
            ON DELETE CASCADE
			ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO student(studentID, publicID, majorID, studentName, interestID, email) VALUES (1, 1, 1, 'Kevin', 1, '123.edu');

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
  facultyID INT NOT NULL AUTO_INCREMENT,
  facultyName VARCHAR(255),
  email VARCHAR(255) NOT NULL,
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

INSERT INTO faculty(facultyID, facultyName, email, department, abstract, publicID, topicID) VALUES (1, 'Jim', 'jimmy@rit.edu', 'Golisano', 'Web Development',  1, 1);



DROP TABLE IF EXISTS author;
CREATE TABLE author (
	authorID INT NOT NULL AUTO_INCREMENT,
    authorName VARCHAR(100) NOT NULL,
    articlesPublished INT,
    PRIMARY KEY (authorID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO author(authorID, authorName, articlesPublished) VALUES (1, 'kevin', '2');
INSERT INTO author(authorID, authorName, articlesPublished) VALUES (2, 'Jim', '2');




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

INSERT INTO article(articleID, topicID, title, authorID, articleDescription, publishDate) VALUES (1, 1, 'How to make a website', 1, 'Tips and tricks on how to make a good website.', '1991-01-01');


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

INSERT INTO department(departmentID, facultyID, departmentName) VALUES (1, 1, 'Golisano');

