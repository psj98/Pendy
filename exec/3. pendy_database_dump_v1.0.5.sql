-- MySQL dump 10.13  Distrib 5.7.38, for Win64 (x86_64)
--
-- Host: stg-yswa-kr-practice-db-master.mariadb.database.azure.com    Database: s09p22a410
-- ------------------------------------------------------
-- Server version	5.6.47.0

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
-- Table structure for table `account_info`
--

DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_info` (
  `account_number` varchar(255) NOT NULL,
  `account_password` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `member_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `FKij2n793wsvhn23ny0210fg087` (`bank_code`),
  KEY `FKlq6c71vlnbs9uwiseu3h6r6xr` (`member_id`),
  CONSTRAINT `FKij2n793wsvhn23ny0210fg087` FOREIGN KEY (`bank_code`) REFERENCES `bank` (`bank_code`),
  CONSTRAINT `FKlq6c71vlnbs9uwiseu3h6r6xr` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES ('100-123-123459',1234,1963501,'088',_binary '?9v�	�K�\�'),('1006145123456',1234,197895000,'020',_binary '$\"O�b\0H��\�'),('101-123-123459',1234,1000001,'088',NULL),('1010',1010,500001,'088',_binary '�~���\"G��\�mK��'),('111-2222-3333',1234,839190690,'090',_binary '�膇;Bm�\��\Z\�)c�'),('123-05-12345-9',1234,2000000,'081',NULL),('123456789',123,1,'081',_binary '\��\�gF:EK�\�0JI�c�'),('222-3443-3536',1234,0,'090',NULL);
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `age_salary`
--

DROP TABLE IF EXISTS `age_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `age_salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `people_num` int(11) NOT NULL,
  `salary` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `age_salary`
--

LOCK TABLES `age_salary` WRITE;
/*!40000 ALTER TABLE `age_salary` DISABLE KEYS */;
INSERT INTO `age_salary` VALUES (3,30,1,5000),(4,60,2,3000),(5,20,1,3000),(6,30,1,3000);
/*!40000 ALTER TABLE `age_salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avg_consumption_amount`
--

DROP TABLE IF EXISTS `avg_consumption_amount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avg_consumption_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reg_date` datetime DEFAULT NULL,
  `sum_amount` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `age_salary_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbtxq0e1i4q3j47ffmvj43sc6s` (`category_id`),
  KEY `FKq0ikfo7bmujc6pm0i3cf2q6dj` (`age_salary_id`),
  CONSTRAINT `FKbtxq0e1i4q3j47ffmvj43sc6s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKq0ikfo7bmujc6pm0i3cf2q6dj` FOREIGN KEY (`age_salary_id`) REFERENCES `age_salary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avg_consumption_amount`
--

LOCK TABLES `avg_consumption_amount` WRITE;
/*!40000 ALTER TABLE `avg_consumption_amount` DISABLE KEYS */;
INSERT INTO `avg_consumption_amount` VALUES (1,'2023-09-20 16:41:36',20000,7,3),(2,'2023-09-20 16:42:08',13000,1,3),(3,'2023-09-20 17:13:45',3500,4,3),(13,'2023-09-21 09:46:48',1970000,4,4),(14,'2023-09-21 09:50:32',309500,1,4),(15,'2023-09-21 09:50:51',862000,7,4),(16,'2023-09-21 09:51:56',510000,8,4),(17,'2023-09-21 10:02:56',205000,2,4),(18,'2023-09-21 10:05:43',40000,6,4),(19,'2023-09-21 10:11:44',21400,5,4),(20,'2023-09-25 06:41:31',120000,3,4),(21,NULL,0,1,3),(22,NULL,0,2,3),(23,NULL,0,3,3),(24,NULL,0,1,3),(25,NULL,0,4,3),(26,NULL,0,2,3),(27,NULL,0,5,3),(28,NULL,0,3,3),(29,NULL,0,6,3),(30,NULL,0,4,3),(31,NULL,0,7,3),(32,NULL,0,5,3),(33,NULL,0,8,3),(34,NULL,0,6,3),(35,NULL,0,7,3),(36,NULL,0,1,4),(37,NULL,0,8,3),(38,NULL,0,2,4),(39,NULL,0,1,4),(40,NULL,0,3,4),(41,NULL,0,2,4),(42,NULL,0,4,4),(43,NULL,0,3,4),(44,NULL,0,5,4),(45,NULL,0,4,4),(46,NULL,0,6,4),(47,NULL,0,5,4),(48,NULL,0,7,4),(49,NULL,0,6,4),(50,NULL,0,8,4),(51,NULL,0,7,4),(52,NULL,0,8,4),(53,'2023-10-03 19:32:35',30000,4,6),(54,'2023-10-04 09:26:37',18000,1,4),(55,'2023-10-04 09:27:34',12300,5,4),(56,'2023-10-04 10:30:01',6600,4,4);
/*!40000 ALTER TABLE `avg_consumption_amount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `bank_code` varchar(255) NOT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bank_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES ('002','KDB산업은행'),('003','IBK기업은행'),('004','KB국민은행'),('011','NH농협은행'),('020','우리은행'),('027','씨티은행'),('031','DGB대구은행'),('032','부산은행'),('034','광주은행'),('035','제주은행'),('037','전북은행'),('039','경남은행'),('045','새마을금고'),('048','신협'),('071','우체국예금보험'),('081','하나은행'),('088','신한은행'),('089','케이뱅크'),('090','카카오뱅크'),('092','토스뱅크');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'food'),(2,'traffic'),(3,'online'),(4,'offline'),(5,'cafe'),(6,'housing'),(7,'fashion'),(8,'culture');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_statistic`
--

DROP TABLE IF EXISTS `daily_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_statistic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `reg_date` date NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `member_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9vthqo3lfyfkm5i7r4at9cies` (`category_id`),
  KEY `FKaujrcp8kya9o87dcffaw7p3c0` (`member_id`),
  CONSTRAINT `FK9vthqo3lfyfkm5i7r4at9cies` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKaujrcp8kya9o87dcffaw7p3c0` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_statistic`
--

LOCK TABLES `daily_statistic` WRITE;
/*!40000 ALTER TABLE `daily_statistic` DISABLE KEYS */;
INSERT INTO `daily_statistic` VALUES (1,13000,'2023-09-21',1,_binary '$\"O�b\0H��\�'),(2,0,'2023-09-21',2,_binary '$\"O�b\0H��\�'),(3,0,'2023-09-21',3,_binary '$\"O�b\0H��\�'),(4,0,'2023-09-21',4,_binary '$\"O�b\0H��\�'),(5,4600,'2023-09-21',5,_binary '$\"O�b\0H��\�'),(6,0,'2023-09-21',6,_binary '$\"O�b\0H��\�'),(7,0,'2023-09-21',7,_binary '$\"O�b\0H��\�'),(8,0,'2023-09-21',8,_binary '$\"O�b\0H��\�'),(41,0,'2023-08-21',1,_binary '$\"O�b\0H��\�'),(42,0,'2023-08-21',2,_binary '$\"O�b\0H��\�'),(43,0,'2023-08-21',3,_binary '$\"O�b\0H��\�'),(44,0,'2023-08-21',4,_binary '$\"O�b\0H��\�'),(45,0,'2023-08-21',5,_binary '$\"O�b\0H��\�'),(46,0,'2023-08-21',6,_binary '$\"O�b\0H��\�'),(47,0,'2023-08-21',7,_binary '$\"O�b\0H��\�'),(48,0,'2023-08-21',8,_binary '$\"O�b\0H��\�'),(49,0,'2023-09-25',1,_binary '�膇;Bm�\��\Z\�)c�'),(50,0,'2023-09-25',2,_binary '�膇;Bm�\��\Z\�)c�'),(51,0,'2023-09-25',3,_binary '�膇;Bm�\��\Z\�)c�'),(52,0,'2023-09-25',4,_binary '�膇;Bm�\��\Z\�)c�'),(53,0,'2023-09-25',5,_binary '�膇;Bm�\��\Z\�)c�'),(54,0,'2023-09-25',6,_binary '�膇;Bm�\��\Z\�)c�'),(55,0,'2023-09-25',7,_binary '�膇;Bm�\��\Z\�)c�'),(56,0,'2023-09-25',8,_binary '�膇;Bm�\��\Z\�)c�'),(57,0,'2023-10-03',1,_binary '$\"O�b\0H��\�'),(58,0,'2023-10-03',2,_binary '$\"O�b\0H��\�'),(59,0,'2023-10-03',3,_binary '$\"O�b\0H��\�'),(60,0,'2023-10-03',4,_binary '$\"O�b\0H��\�'),(61,0,'2023-10-03',5,_binary '$\"O�b\0H��\�'),(62,0,'2023-10-03',6,_binary '$\"O�b\0H��\�'),(63,0,'2023-10-03',7,_binary '$\"O�b\0H��\�'),(64,0,'2023-10-03',8,_binary '$\"O�b\0H��\�');
/*!40000 ALTER TABLE `daily_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `reg_date` datetime NOT NULL,
  `stamp_type` int(11) NOT NULL,
  `member_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbyluyva0mxnf5jitf297oxlxd` (`member_id`),
  CONSTRAINT `FKbyluyva0mxnf5jitf297oxlxd` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emotion`
--

DROP TABLE IF EXISTS `emotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emotion` (
  `emotion_score` int(11) NOT NULL,
  `emotion_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emotion_score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotion`
--

LOCK TABLES `emotion` WRITE;
/*!40000 ALTER TABLE `emotion` DISABLE KEYS */;
INSERT INTO `emotion` VALUES (1,'매우안좋음'),(2,'안좋음'),(3,'보통'),(4,'좋음'),(5,'많이 좋음');
/*!40000 ALTER TABLE `emotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_by_category`
--

DROP TABLE IF EXISTS `goal_by_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goal_by_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_goal_amount` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `total_goal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk7f5i3qsuaq074v1lg94b1pr8` (`category_id`),
  KEY `FKsl0opqhqw923qrjrr95ns7mn5` (`total_goal_id`),
  CONSTRAINT `FKk7f5i3qsuaq074v1lg94b1pr8` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKsl0opqhqw923qrjrr95ns7mn5` FOREIGN KEY (`total_goal_id`) REFERENCES `total_goal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_by_category`
--

LOCK TABLES `goal_by_category` WRITE;
/*!40000 ALTER TABLE `goal_by_category` DISABLE KEYS */;
INSERT INTO `goal_by_category` VALUES (9,300000,1,2),(10,150000,2,2),(11,0,3,2),(12,200000,4,2),(13,50000,5,2),(14,100000,6,2),(15,500000,7,2),(16,500000,8,2),(137,1200000,1,14),(138,100000,2,14),(139,300000,3,14),(140,400000,4,14),(141,200000,5,14),(142,100000,6,14),(143,200000,7,14),(144,500000,8,14);
/*!40000 ALTER TABLE `goal_by_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` binary(16) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salary` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '�膇;Bm�\��\Z\�)c�',65,'qkrTkvl@ssafy.com','박싸피','$2a$10$/l1Jj7EO/PfP9Kyy4UX1z.2FHkiQpYkE5a1uBAap1vWOQ0Qeeatd6',32000000),(_binary '$\"O�b\0H��\�',67,'choi@ssafy.com','최덕팔','$2a$10$ALqneAXKsBIVtv0CCtz51.xJh3Y6kek8ziaLJtHxFeqv8y0sYMOTC',37000000),(_binary '?9v�	�K�\�',30,'admin@ssafy.com','김싸피','$2a$10$apfYFO20jCAuOjBETYIe8eo.oInHjjfJ3ObzbYIHCbWNJwNNm.bVC',50000000),(_binary '�~���\"G��\�mK��',20,'qq@qq.com','Qq','$2a$10$aGdIOLfIQOtZnI0ad1LSf.8iKUEtD9oyjoLj6hd0EXLI7BDIAYeP6',30000000),(_binary '\��\�gF:EK�\�0JI�c�',30,'asd@asd.asd','김낙수','$2a$10$2SCd5S/IlV8w1awiqOKGoOHeoZNXI7ZXDNE/e6jxNucviHykf8Wvu',30000000);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_statistic`
--

DROP TABLE IF EXISTS `monthly_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_statistic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `reg_date` date NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `member_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtclbtgv59tl56x7xyqjg73hx9` (`category_id`),
  KEY `FKlf28qu6jx3fj2jctomk3id93w` (`member_id`),
  CONSTRAINT `FKlf28qu6jx3fj2jctomk3id93w` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKtclbtgv59tl56x7xyqjg73hx9` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_statistic`
--

LOCK TABLES `monthly_statistic` WRITE;
/*!40000 ALTER TABLE `monthly_statistic` DISABLE KEYS */;
INSERT INTO `monthly_statistic` VALUES (49,0,'2023-08-21',1,_binary '$\"O�b\0H��\�'),(50,0,'2023-08-21',2,_binary '$\"O�b\0H��\�'),(51,0,'2023-08-21',3,_binary '$\"O�b\0H��\�'),(52,0,'2023-08-21',4,_binary '$\"O�b\0H��\�'),(53,0,'2023-08-21',5,_binary '$\"O�b\0H��\�'),(54,0,'2023-08-21',6,_binary '$\"O�b\0H��\�'),(55,0,'2023-08-21',7,_binary '$\"O�b\0H��\�'),(56,0,'2023-08-21',8,_binary '$\"O�b\0H��\�'),(57,0,'2023-09-21',1,_binary '$\"O�b\0H��\�'),(58,0,'2023-09-21',2,_binary '$\"O�b\0H��\�'),(59,0,'2023-09-21',3,_binary '$\"O�b\0H��\�'),(60,0,'2023-09-21',4,_binary '$\"O�b\0H��\�'),(61,0,'2023-09-21',5,_binary '$\"O�b\0H��\�'),(62,0,'2023-09-21',6,_binary '$\"O�b\0H��\�'),(63,0,'2023-09-21',7,_binary '$\"O�b\0H��\�'),(64,0,'2023-09-21',8,_binary '$\"O�b\0H��\�'),(65,0,'2023-07-21',1,_binary '$\"O�b\0H��\�'),(66,0,'2023-07-21',2,_binary '$\"O�b\0H��\�'),(67,0,'2023-07-21',3,_binary '$\"O�b\0H��\�'),(68,0,'2023-07-21',4,_binary '$\"O�b\0H��\�'),(69,0,'2023-07-21',5,_binary '$\"O�b\0H��\�'),(70,0,'2023-07-21',6,_binary '$\"O�b\0H��\�'),(71,0,'2023-07-21',7,_binary '$\"O�b\0H��\�'),(72,0,'2023-07-21',8,_binary '$\"O�b\0H��\�'),(73,0,'2023-06-21',1,_binary '$\"O�b\0H��\�'),(74,0,'2023-06-21',2,_binary '$\"O�b\0H��\�'),(75,0,'2023-06-21',3,_binary '$\"O�b\0H��\�'),(76,0,'2023-06-21',4,_binary '$\"O�b\0H��\�'),(77,0,'2023-06-21',5,_binary '$\"O�b\0H��\�'),(78,0,'2023-06-21',6,_binary '$\"O�b\0H��\�'),(79,0,'2023-06-21',7,_binary '$\"O�b\0H��\�'),(80,0,'2023-06-21',8,_binary '$\"O�b\0H��\�'),(81,0,'2023-05-21',1,_binary '$\"O�b\0H��\�'),(82,0,'2023-05-21',2,_binary '$\"O�b\0H��\�'),(83,0,'2023-05-21',3,_binary '$\"O�b\0H��\�'),(84,0,'2023-05-21',4,_binary '$\"O�b\0H��\�'),(85,0,'2023-05-21',5,_binary '$\"O�b\0H��\�'),(86,0,'2023-05-21',6,_binary '$\"O�b\0H��\�'),(87,0,'2023-05-21',7,_binary '$\"O�b\0H��\�'),(88,0,'2023-05-21',8,_binary '$\"O�b\0H��\�'),(89,0,'2023-04-21',1,_binary '$\"O�b\0H��\�'),(90,0,'2023-04-21',2,_binary '$\"O�b\0H��\�'),(91,0,'2023-04-21',3,_binary '$\"O�b\0H��\�'),(92,0,'2023-04-21',4,_binary '$\"O�b\0H��\�'),(93,0,'2023-04-21',5,_binary '$\"O�b\0H��\�'),(94,0,'2023-04-21',6,_binary '$\"O�b\0H��\�'),(95,0,'2023-04-21',7,_binary '$\"O�b\0H��\�'),(96,0,'2023-04-21',8,_binary '$\"O�b\0H��\�'),(97,0,'2023-10-21',1,_binary '$\"O�b\0H��\�'),(98,0,'2023-10-21',2,_binary '$\"O�b\0H��\�'),(99,0,'2023-10-21',3,_binary '$\"O�b\0H��\�'),(100,0,'2023-10-21',4,_binary '$\"O�b\0H��\�'),(101,0,'2023-10-21',5,_binary '$\"O�b\0H��\�'),(102,0,'2023-10-21',6,_binary '$\"O�b\0H��\�'),(103,0,'2023-10-21',7,_binary '$\"O�b\0H��\�'),(104,0,'2023-10-21',8,_binary '$\"O�b\0H��\�'),(105,0,'2023-11-21',1,_binary '$\"O�b\0H��\�'),(106,0,'2023-11-21',2,_binary '$\"O�b\0H��\�'),(107,0,'2023-11-21',3,_binary '$\"O�b\0H��\�'),(108,0,'2023-11-21',4,_binary '$\"O�b\0H��\�'),(109,0,'2023-11-21',5,_binary '$\"O�b\0H��\�'),(110,0,'2023-11-21',6,_binary '$\"O�b\0H��\�'),(111,0,'2023-11-21',7,_binary '$\"O�b\0H��\�'),(112,0,'2023-11-21',8,_binary '$\"O�b\0H��\�'),(113,0,'2023-12-21',1,_binary '$\"O�b\0H��\�'),(114,0,'2023-12-21',2,_binary '$\"O�b\0H��\�'),(115,0,'2023-12-21',3,_binary '$\"O�b\0H��\�'),(116,0,'2023-12-21',4,_binary '$\"O�b\0H��\�'),(117,0,'2023-12-21',5,_binary '$\"O�b\0H��\�'),(118,0,'2023-12-21',6,_binary '$\"O�b\0H��\�'),(119,0,'2023-12-21',7,_binary '$\"O�b\0H��\�'),(120,0,'2023-12-21',8,_binary '$\"O�b\0H��\�'),(121,0,'2023-03-22',1,_binary '$\"O�b\0H��\�'),(122,0,'2023-03-22',2,_binary '$\"O�b\0H��\�'),(123,0,'2023-03-22',3,_binary '$\"O�b\0H��\�'),(124,0,'2023-03-22',4,_binary '$\"O�b\0H��\�'),(125,0,'2023-03-22',5,_binary '$\"O�b\0H��\�'),(126,0,'2023-03-22',6,_binary '$\"O�b\0H��\�'),(127,0,'2023-03-22',7,_binary '$\"O�b\0H��\�'),(128,0,'2023-03-22',8,_binary '$\"O�b\0H��\�'),(129,0,'2023-02-22',1,_binary '$\"O�b\0H��\�'),(130,0,'2023-02-22',2,_binary '$\"O�b\0H��\�'),(131,0,'2023-02-22',3,_binary '$\"O�b\0H��\�'),(132,0,'2023-02-22',4,_binary '$\"O�b\0H��\�'),(133,0,'2023-02-22',5,_binary '$\"O�b\0H��\�'),(134,0,'2023-02-22',6,_binary '$\"O�b\0H��\�'),(135,0,'2023-02-22',7,_binary '$\"O�b\0H��\�'),(136,0,'2023-02-22',8,_binary '$\"O�b\0H��\�'),(137,0,'2023-01-22',1,_binary '$\"O�b\0H��\�'),(138,0,'2023-01-22',2,_binary '$\"O�b\0H��\�'),(139,0,'2023-01-22',3,_binary '$\"O�b\0H��\�'),(140,0,'2023-01-22',4,_binary '$\"O�b\0H��\�'),(141,0,'2023-01-22',5,_binary '$\"O�b\0H��\�'),(142,0,'2023-01-22',6,_binary '$\"O�b\0H��\�'),(143,0,'2023-01-22',7,_binary '$\"O�b\0H��\�'),(144,0,'2023-01-22',8,_binary '$\"O�b\0H��\�'),(145,0,'2023-09-25',1,_binary '�膇;Bm�\��\Z\�)c�'),(146,0,'2023-09-25',2,_binary '�膇;Bm�\��\Z\�)c�'),(147,0,'2023-09-25',3,_binary '�膇;Bm�\��\Z\�)c�'),(148,0,'2023-09-25',4,_binary '�膇;Bm�\��\Z\�)c�'),(149,0,'2023-09-25',5,_binary '�膇;Bm�\��\Z\�)c�'),(150,0,'2023-09-25',6,_binary '�膇;Bm�\��\Z\�)c�'),(151,0,'2023-09-25',7,_binary '�膇;Bm�\��\Z\�)c�'),(152,0,'2023-09-25',8,_binary '�膇;Bm�\��\Z\�)c�'),(153,0,'2023-10-03',1,_binary '�~���\"G��\�mK��'),(154,0,'2023-10-03',2,_binary '�~���\"G��\�mK��'),(155,0,'2023-10-03',3,_binary '�~���\"G��\�mK��'),(156,0,'2023-10-03',4,_binary '�~���\"G��\�mK��'),(157,0,'2023-10-03',5,_binary '�~���\"G��\�mK��'),(158,0,'2023-10-03',6,_binary '�~���\"G��\�mK��'),(159,0,'2023-10-03',7,_binary '�~���\"G��\�mK��'),(160,0,'2023-10-03',8,_binary '�~���\"G��\�mK��'),(161,0,'2023-10-03',1,_binary '\��\�gF:EK�\�0JI�c�'),(162,0,'2023-10-03',2,_binary '\��\�gF:EK�\�0JI�c�'),(163,0,'2023-10-03',3,_binary '\��\�gF:EK�\�0JI�c�'),(164,0,'2023-10-03',4,_binary '\��\�gF:EK�\�0JI�c�'),(165,0,'2023-10-03',5,_binary '\��\�gF:EK�\�0JI�c�'),(166,0,'2023-10-03',6,_binary '\��\�gF:EK�\�0JI�c�'),(167,0,'2023-10-03',7,_binary '\��\�gF:EK�\�0JI�c�'),(168,0,'2023-10-03',8,_binary '\��\�gF:EK�\�0JI�c�');
/*!40000 ALTER TABLE `monthly_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `total_goal`
--

DROP TABLE IF EXISTS `total_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `total_goal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ai_analysis` varchar(255) DEFAULT NULL,
  `goal_amount` int(11) NOT NULL,
  `goal_date` date NOT NULL,
  `member_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmj765ebcqwugkb6hnjvgytkmc` (`member_id`),
  CONSTRAINT `FKmj765ebcqwugkb6hnjvgytkmc` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_goal`
--

LOCK TABLES `total_goal` WRITE;
/*!40000 ALTER TABLE `total_goal` DISABLE KEYS */;
INSERT INTO `total_goal` VALUES (2,NULL,1810000,'2023-09-21',_binary '$\"O�b\0H��\�'),(14,NULL,3000000,'2023-09-25',_binary '�膇;Bm�\��\Z\�)c�'),(15,NULL,0,'2023-10-03',_binary '�~���\"G��\�mK��'),(16,NULL,0,'2023-10-03',_binary '\��\�gF:EK�\�0JI�c�'),(17,NULL,0,'2023-10-03',_binary '$\"O�b\0H��\�'),(18,NULL,0,'2023-08-03',_binary '$\"O�b\0H��\�'),(19,NULL,0,'2023-07-04',_binary '$\"O�b\0H��\�');
/*!40000 ALTER TABLE `total_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_info`
--

DROP TABLE IF EXISTS `transaction_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `after_balance` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `transaction_amount` int(11) DEFAULT NULL,
  `transaction_name` varchar(255) DEFAULT NULL,
  `transaction_type` int(11) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `emotion_id` int(11) DEFAULT 3,
  PRIMARY KEY (`id`),
  KEY `FKcoadlm0u2aopp2kpteohbu5ea` (`account_number`),
  KEY `FK2us7yf4qir0oux7xk62w76xh3` (`category_id`),
  KEY `FKlb8vuhedrebstevjjcyix72jw` (`emotion_id`),
  CONSTRAINT `FK2us7yf4qir0oux7xk62w76xh3` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKcoadlm0u2aopp2kpteohbu5ea` FOREIGN KEY (`account_number`) REFERENCES `account_info` (`account_number`),
  CONSTRAINT `FKlb8vuhedrebstevjjcyix72jw` FOREIGN KEY (`emotion_id`) REFERENCES `emotion` (`emotion_score`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_info`
--

LOCK TABLES `transaction_info` WRITE;
/*!40000 ALTER TABLE `transaction_info` DISABLE KEYS */;
INSERT INTO `transaction_info` VALUES (1,996500,'2023-09-13 14:20:29',3500,'씨유역삼신웅점',2,'100-123-123459',4,3),(2,1996500,'2023-09-15 16:39:35',1000000,'삼성SSAFY',1,'100-123-123459',NULL,NULL),(3,1983500,'2023-09-15 18:25:23',13000,'전주콩뿌리콩나물국',2,'100-123-123459',1,3),(4,1963500,'2023-09-15 19:23:22',20000,'클라이밍파크',2,'100-123-123459',7,3),(5,1963501,'2023-09-20 16:24:41',1,'Pendy664',1,'100-123-123459',NULL,NULL),(6,202020000,'2023-07-01 19:46:48',100000,'슈퍼마켓 구매',2,'1006145123456',4,3),(7,200520000,'2023-07-02 10:02:29',800000,'가전제품 구매',2,'1006145123456',4,3),(8,200370000,'2023-07-05 10:02:56',150000,'자동차 정비비용',2,'1006145123456',2,3),(9,200300000,'2023-07-07 10:03:40',70000,'옷 쇼핑',2,'1006145123456',4,3),(10,200180000,'2023-07-09 10:04:10',120000,'건강검진 비용',2,'1006145123456',8,3),(11,201970000,'2023-07-10 19:50:32',50000,'레스토랑 식사',2,'1006145123456',1,3),(12,200100000,'2023-07-11 13:04:30',80000,'가족 모임 식사',2,'1006145123456',1,3),(13,199950000,'2023-07-14 10:04:51',150000,'연례 테니스 대회 참가비',2,'1006145123456',7,3),(14,201770000,'2023-07-15 09:50:51',200000,'테니스 클럽 회원비',2,'1006145123456',7,3),(15,199890000,'2023-07-17 10:05:23',60000,'음식 재료 구매',2,'1006145123456',1,3),(16,201470000,'2023-07-20 09:51:56',300000,'딸기 농장 운영 비용',2,'1006145123456',8,3),(17,199850000,'2023-07-29 10:05:43',40000,'미용실 비용',2,'1006145123456',6,3),(71,197265001,'2023-09-21 09:35:33',1,'Pendy241',1,'1006145123456',NULL,NULL),(72,199900000,'2023-08-01 19:46:48',100000,'슈퍼마켓 구매',2,'1006145123456',4,3),(73,199850000,'2023-08-10 19:50:32',50000,'레스토랑 식사',2,'1006145123456',1,3),(74,199650000,'2023-08-15 09:50:51',200000,'테니스 클럽 회원비',2,'1006145123456',7,3),(75,199350000,'2023-08-20 09:51:56',300000,'딸기 농장 운영 비용',2,'1006145123456',8,3),(76,199200000,'2023-08-25 09:52:19',150000,'컴퓨터 교육 수업료',2,'1006145123456',7,3),(77,198400000,'2023-08-02 10:02:29',800000,'가전제품 구매',2,'1006145123456',4,3),(78,198250000,'2023-08-05 10:02:56',150000,'자동차 정비비용',2,'1006145123456',2,3),(79,198180000,'2023-08-07 10:03:40',70000,'옷 쇼핑',2,'1006145123456',4,3),(80,198060000,'2023-08-09 10:04:10',120000,'건강검진 비용',2,'1006145123456',8,3),(81,197980000,'2023-08-11 13:04:30',80000,'가족 모임 식사',2,'1006145123456',1,3),(82,197830000,'2023-08-14 10:04:51',150000,'연례 테니스 대회 참가비',2,'1006145123456',7,3),(83,197770000,'2023-08-17 10:05:23',60000,'음식 재료 구매',2,'1006145123456',1,3),(84,197730000,'2023-08-29 10:05:43',40000,'미용실 비용',2,'1006145123456',6,3),(85,197690000,'2023-09-01 10:10:16',40000,'서점 도서 구매',2,'1006145123456',7,3),(86,197630000,'2023-09-04 10:10:35',60000,'스포츠 용품 구매',2,'1006145123456',7,3),(87,197560000,'2023-09-07 10:11:00',70000,'친목 모임 비용',2,'1006145123456',1,3),(88,197470000,'2023-09-09 10:11:19',90000,'의료 검진',2,'1006145123456',8,3),(89,197465000,'2023-09-11 10:11:44',5000,'메가커피',2,'1006145123456',5,3),(90,197265000,'2023-09-15 10:12:32',200000,'테니스 클럽 회원비',2,'1006145123456',7,3),(91,197260401,'2023-09-21 14:35:30',4600,'메가커피',2,'1006145123456',5,3),(92,197247401,'2023-09-21 15:41:22',13000,'전주콩뿌리콩나물국',2,'1006145123456',1,3),(93,197242401,'2023-09-22 10:06:55',5000,'커피',2,'1006145123456',5,3),(94,197230401,'2023-09-22 12:37:49',12000,'극장에서 영화 티켓 구매',2,'1006145123456',7,3),(95,197180401,'2023-09-22 12:39:22',50000,'주유소에서 기름 구매',2,'1006145123456',2,3),(96,198180401,'2023-09-22 16:28:28',1000000,'싸월급',1,'1006145123456',4,3),(97,198130401,'2023-09-22 16:31:22',50000,'축의금',2,'1006145123456',7,3),(98,839355090,'2023-09-24 11:35:25',3900,'스타벅스',2,'111-2222-3333',5,3),(99,839346090,'2023-09-24 12:25:15',9000,'순대국',2,'111-2222-3333',1,3),(100,839226090,'2023-09-24 17:16:23',120000,'옷 쇼핑',2,'111-2222-3333',3,3),(101,839216090,'2023-09-24 19:36:25',10000,'제육볶음',2,'111-2222-3333',1,3),(102,839213590,'2023-09-25 06:47:38',2500,'지하철',2,'111-2222-3333',2,2),(103,839208090,'2023-09-25 06:50:29',5500,'맥모닝',2,'111-2222-3333',1,3),(104,839205190,'2023-09-25 07:32:49',2900,'바나프레소',2,'111-2222-3333',5,4),(105,839193190,'2023-09-25 12:37:19',12000,'냉모밀',2,'111-2222-3333',1,2),(106,839190690,'2023-09-25 18:25:57',2500,'지하철',2,'111-2222-3333',2,3),(107,500001,'2023-10-03 19:20:33',1,'Pendy340',1,'1010',NULL,NULL),(108,30001,'2023-10-03 19:26:13',1,'Pendy385',1,'123456789',NULL,NULL),(109,1,'2023-10-03 19:32:35',30000,'서울강남구영동대로432준앤빌딩4층(주)\\n하이브로',2,'123456789',4,3),(110,198112401,'2023-10-01 12:37:37',18000,'맛보듬대가토종순대',2,'1006145123456',1,3),(111,198103601,'2023-10-03 09:27:34',8800,'메가엠지씨커피사가',2,'1006145123456',5,3),(112,198100101,'2023-10-04 09:00:19',3500,'매머드익스프레스역',2,'1006145123456',5,3),(113,198093501,'2023-10-04 10:30:01',6600,'씨유역삼신웅점',2,'1006145123456',4,3),(114,198000000,'2023-10-05 09:46:48',93501,'슈퍼마켓 구매',2,'1006145123456',4,3),(115,197950000,'2023-10-05 12:03:40',50000,'옷 쇼핑',2,'1006145123456',4,3),(116,197900000,'2023-10-06 09:13:45',50000,'음식 재료 구매',2,'1006145123456',4,3),(117,197895000,'2023-10-06 10:03:40',5000,'매머드익스프레스역',2,'1006145123456',5,3);
/*!40000 ALTER TABLE `transaction_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-04 10:41:38
