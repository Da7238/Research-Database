CREATE DATABASE  IF NOT EXISTS `researchdatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `researchdatabase`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: researchdatabase
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `articleID` int NOT NULL AUTO_INCREMENT,
  `topicID` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `authorID` int NOT NULL,
  `articleDescription` varchar(255) DEFAULT NULL,
  `publishDate` date DEFAULT NULL,
  PRIMARY KEY (`articleID`),
  KEY `topicID` (`topicID`),
  KEY `authorID` (`authorID`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`authorID`) REFERENCES `author` (`authorID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,1,'How to make a website',1,'Tips and tricks on how to make a good website.','1991-01-01'),(3,2,'How to be the best coder',1,'Tips and tricks on how to write better code.','2021-02-03'),(5,3,'How to be good at math',2,'How to solve complex math problems','2001-02-06');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `authorID` int NOT NULL AUTO_INCREMENT,
  `authorName` varchar(100) NOT NULL,
  `articlesPublished` int DEFAULT NULL,
  PRIMARY KEY (`authorID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Jim Habermas',2),(2,'Nobody Nowhere',1);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentID` int NOT NULL AUTO_INCREMENT,
  `facultyID` int NOT NULL,
  `departmentName` varchar(255) NOT NULL,
  PRIMARY KEY (`departmentID`),
  KEY `facultyID` (`facultyID`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`facultyID`) REFERENCES `faculty` (`facultyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,1,'Golisano'),(2,2,'Gosnell');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `facultyID` int NOT NULL AUTO_INCREMENT,
  `facultyName` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `departmentID` varchar(255) DEFAULT NULL,
  `abstract` varchar(255) DEFAULT NULL,
  `topicID` int NOT NULL,
  PRIMARY KEY (`facultyID`),
  UNIQUE KEY `email` (`email`),
  KEY `topicID` (`topicID`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Jim','jimmy@rit.edu','1','Web Development',1),(2,'Faculty','faculty@gmail.com','1','Web Development',1);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_accounts`
--

DROP TABLE IF EXISTS `faculty_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_accounts` (
  `facultyID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `accountPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`facultyID`),
  KEY `email` (`email`),
  CONSTRAINT `faculty_accounts_ibfk_1` FOREIGN KEY (`email`) REFERENCES `faculty` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_accounts`
--

LOCK TABLES `faculty_accounts` WRITE;
/*!40000 ALTER TABLE `faculty_accounts` DISABLE KEYS */;
INSERT INTO `faculty_accounts` VALUES (1,'jimmy@rit.edu','jimmy'),(2,'faculty@gmail.com','faculty');
/*!40000 ALTER TABLE `faculty_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `interestID` int NOT NULL AUTO_INCREMENT,
  `interestName` varchar(255) NOT NULL,
  PRIMARY KEY (`interestID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES (1,'php'),(2,'java');
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `majorID` int NOT NULL AUTO_INCREMENT,
  `majorName` varchar(100) NOT NULL,
  `majorDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`majorID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,'Mathematics','Learning how to solve math problems.'),(2,'Computer Science','Learning how to code in multiple languages.');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `public`
--

DROP TABLE IF EXISTS `public`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `public` (
  `publicID` int NOT NULL AUTO_INCREMENT,
  `publicName` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `pubEmail` varchar(255) NOT NULL,
  `interestId` int NOT NULL,
  PRIMARY KEY (`publicID`),
  KEY `interestId` (`interestId`),
  CONSTRAINT `public_ibfk_1` FOREIGN KEY (`interestId`) REFERENCES `interest` (`interestID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `public`
--

LOCK TABLES `public` WRITE;
/*!40000 ALTER TABLE `public` DISABLE KEYS */;
INSERT INTO `public` VALUES (1,'SaraP','UserSara','sarapublic@gmail.com',1),(2,'BillP','UserSara','billpublic@gmail.com',2),(3,'Student','UserStudent','student@gmail.com',2);
/*!40000 ALTER TABLE `public` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) NOT NULL,
  `interestID` int DEFAULT NULL,
  `majorID` int NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`studentID`),
  UNIQUE KEY `email` (`email`),
  KEY `majorID` (`majorID`),
  KEY `interestID` (`interestID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`majorID`) REFERENCES `major` (`majorID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`interestID`) REFERENCES `interest` (`interestID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Kevin',1,1,'kevin@gmail.com'),(2,'Emily',2,2,'emily@gmail.com'),(3,'Student',2,2,'student@gmail.com');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_accounts`
--

DROP TABLE IF EXISTS `student_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_accounts` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `accountPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`studentID`),
  KEY `email` (`email`),
  CONSTRAINT `student_accounts_ibfk_1` FOREIGN KEY (`email`) REFERENCES `student` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_accounts`
--

LOCK TABLES `student_accounts` WRITE;
/*!40000 ALTER TABLE `student_accounts` DISABLE KEYS */;
INSERT INTO `student_accounts` VALUES (1,'kevin@gmail.com','kevin'),(2,'emily@gmail.com','emily'),(3,'student@gmail.com','student');
/*!40000 ALTER TABLE `student_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `topicID` int NOT NULL AUTO_INCREMENT,
  `topicDescription` varchar(255) DEFAULT NULL,
  `topicTag` varchar(20) NOT NULL,
  PRIMARY KEY (`topicID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'PHP research','php'),(2,'Java research','java'),(3,'Calculus research','calc');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'researchdatabase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-28 22:26:22
