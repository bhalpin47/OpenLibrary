CREATE DATABASE  IF NOT EXISTS `openlibrary` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `openlibrary`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: openlibrary
-- ------------------------------------------------------
-- Server version	5.5.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `edition` int(10) unsigned DEFAULT NULL,
  `pages` int(10) unsigned NOT NULL,
  `series` varchar(45) DEFAULT NULL,
  `description` varchar(600) NOT NULL,
  `reviews` varchar(45) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  `bookId` int(100) unsigned NOT NULL AUTO_INCREMENT,
  `statusCode` varchar(45) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('1593082037','The Complete Sherlock Holmes, Vol. 1','Sir Arthur Conan Doyle','Barnes & Noble Classics',1,752,'Sherlock Holmes','The Complete Sherlock Holmes, Volume I, by Sir Arthur Conan Doyle, is part of the Barnes & Noble Classics series, which offers quality editions at affordable prices to the student and the general reader, including new scholarship, thoughtful design, and pages of carefully crafted extras. ','','1',1,'A'),('316769177','The Catcher in the Rye','J. D. Salinger','Back Bay Books',3,288,'','Anyone who has read J.D. Salinger\'s New Yorker stories--particularly A Perfect Day for Bananafish, Uncle Wiggily in Connecticut, The Laughing Man, and For Esme With Love and Squalor--will not be surprised by the fact that his first novel is full of children. The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caulfield. ','','2',2,'A'),('143039431','The Grapes of Wrath','John Steinbeck','Penguin Classics',3,464,'',' ','','1',3,'P'),('765342294','Ender\'s Game','Orson Scott Card','Starscape',1,336,NULL,'In order to develop a secure defense against a hostile alien race\'s next attack, government agencies breed child geniuses and train them as soldiers. A brilliant young boy, Andrew \"Ender\" Wiggin lives with his kind but distant parents, his sadistic brother Peter, and the person he loves more than anyone else, his sister Valentine. Peter and Valentine were candidates for the soldier-training program but didn\'t make the cut--young Ender is the Wiggin drafted to the orbiting Battle School for rigorous military training.',NULL,'1',28,'P');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcondition`
--

DROP TABLE IF EXISTS `bookcondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookcondition` (
  `conditionCode` char(2) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`conditionCode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcondition`
--

LOCK TABLES `bookcondition` WRITE;
/*!40000 ALTER TABLE `bookcondition` DISABLE KEYS */;
INSERT INTO `bookcondition` VALUES ('AN','As New'),('BC','Binding Copy'),('BL','Book Club'),('EL','Ex-Library'),('F','Fair'),('FN','Fine'),('G','Good'),('P','Poor'),('VG','Very Good');
/*!40000 ALTER TABLE `bookcondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksowned`
--

DROP TABLE IF EXISTS `booksowned`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksowned` (
  `book` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `bookCondition` varchar(45) NOT NULL,
  `ownedId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ownedId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksowned`
--

LOCK TABLES `booksowned` WRITE;
/*!40000 ALTER TABLE `booksowned` DISABLE KEYS */;
INSERT INTO `booksowned` VALUES ('1','1','AN',1),('2','2','F',2),('1','2','G',16);
/*!40000 ALTER TABLE `booksowned` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksread`
--

DROP TABLE IF EXISTS `booksread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksread` (
  `book` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `readId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`readId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksread`
--

LOCK TABLES `booksread` WRITE;
/*!40000 ALTER TABLE `booksread` DISABLE KEYS */;
INSERT INTO `booksread` VALUES ('1','1',1),('2','2',2),('2','1',3);
/*!40000 ALTER TABLE `booksread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookstatus`
--

DROP TABLE IF EXISTS `bookstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookstatus` (
  `statusCode` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`statusCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookstatus`
--

LOCK TABLES `bookstatus` WRITE;
/*!40000 ALTER TABLE `bookstatus` DISABLE KEYS */;
INSERT INTO `bookstatus` VALUES ('A','Approved'),('D','Denied'),('P','Pending'),('R','Returned for revision');
/*!40000 ALTER TABLE `bookstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookswanted`
--

DROP TABLE IF EXISTS `bookswanted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookswanted` (
  `book` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `wantedId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`wantedId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookswanted`
--

LOCK TABLES `bookswanted` WRITE;
/*!40000 ALTER TABLE `bookswanted` DISABLE KEYS */;
INSERT INTO `bookswanted` VALUES ('1','1',2);
/*!40000 ALTER TABLE `bookswanted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `reviewid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `book` varchar(45) NOT NULL,
  `content` varchar(255) NOT NULL,
  `rating` int(10) unsigned NOT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`reviewid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'My favorite book!','1','This book was so amazing I couldn\'t put it down! I have read it 15 times since I bought it!',10,'2');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fName` varchar(45) NOT NULL,
  `lName` varchar(45) NOT NULL,
  `dob` date DEFAULT NULL,
  `booksOwned` varchar(45) DEFAULT NULL,
  `booksRead` varchar(45) DEFAULT NULL,
  `booksWanted` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `groupId` int(10) unsigned DEFAULT NULL,
  `userCode` char(1) NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'halpin','pass','Brandon','Halpin','2002-02-02','','','','bhalpin47@gmail.com',1,'S'),(2,'root','0','Jane','Smith','2001-01-01','','','','root@domain.com',0,'A');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup` (
  `groupId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`groupId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (0,'User does not belong to a group'),(1,'CSUStan Book Club');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-06 16:30:04
