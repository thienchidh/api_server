CREATE DATABASE  IF NOT EXISTS `web_adv` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `web_adv`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: web_adv
-- ------------------------------------------------------
-- Server version	5.5.62-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k8h1bgqoplx0rkngj01pm1rgp` (`username`),
  KEY `FKnjuop33mo69pd79ctplkck40n` (`user_id`),
  CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'2020-01-13 18:34:50','$2a$10$KylDziAOPV.KNDDCsuz0jelMhGWIZcIbROyUAFwPjyl0PbZL2nO8C','$2a$10$OCn/egOeaA5vGOZ39m22m.R52ZgNZE93s.ZEhzXkRW1chanBpbAYS','thienchidh',1),(2,'2020-01-13 18:35:59','$2a$10$2JtSiUcg9OPp0VVgyYr51eIbVv82J1Y5AePYG.XFdKAkvz2scjFMm','$2a$10$RTZ7rfV2T1tNtgU1fTuIve35DmGWgXRGW1ccOrd7PYHSB7jVP3F8y','admin',2),(3,'2020-01-14 02:23:03','$2a$10$rJFcr3jJPH9FwnOkZ4UW0uRUBEaAmatNzAdJMHpRiM2zZLdObyhl6','$2a$10$4ehPpZPB7wrQpESd9Xj.s.s0w/nFIH/SshVcjPe7E7zObiHYxMM7C','thienchidh@gmail.com',5),(4,'2020-01-14 02:26:50','$2a$10$1TRPo4c47NAlr94Vn7MMZ.J9b6IkdKbFUsG6Pq.NiD11uUOd45Tqy','$2a$10$bGxUzd2TcWP3Pl9sEX8.EOgn8odsM/pQ.WXGR5LFvfoKxrw2rEelq','chi',6),(5,'2020-01-14 15:58:24','$2a$10$rpU4RTtCGRniqmZ3sk.4GOFl/QiSuXMEFAGzC0u4qZho8JcO2rWna','$2a$10$q3lXxuNIzXwUv7FkGrdtZuMSMEzEflBvyIAPMKKsXdKeFH.Qh9Hxe','chi1236vn',7);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb5o626f86h46m4s7ms6ginnop` (`user_id`),
  CONSTRAINT `FKb5o626f86h46m4s7ms6ginnop` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,2),(4,7);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts_products`
--

DROP TABLE IF EXISTS `carts_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carts_products` (
  `carts_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL,
  KEY `FKq9ns7lphr8im6vg3i5dwknsbn` (`products_id`),
  KEY `FK5us81lf041269lqffseedp8rq` (`carts_id`),
  CONSTRAINT `FK5us81lf041269lqffseedp8rq` FOREIGN KEY (`carts_id`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKq9ns7lphr8im6vg3i5dwknsbn` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts_products`
--

LOCK TABLES `carts_products` WRITE;
/*!40000 ALTER TABLE `carts_products` DISABLE KEYS */;
INSERT INTO `carts_products` VALUES (4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(4,100),(1,132),(1,135),(1,136),(1,137);
/*!40000 ALTER TABLE `carts_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (5),(5),(5),(5),(5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82433 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (100,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(101,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(102,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(103,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(104,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(105,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(106,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(107,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(108,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(109,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(110,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(111,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(112,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(113,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(114,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(115,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(116,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(117,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(118,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(119,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(120,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(121,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(122,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(123,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(124,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(132,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(135,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(136,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(137,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(138,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(145,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(146,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(147,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(148,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(149,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(150,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(151,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(152,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(153,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(154,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(155,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(156,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(157,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(158,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(159,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(160,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(161,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(162,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(163,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(164,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(165,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(166,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(167,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(168,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(169,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(170,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(171,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(172,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(173,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(174,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(175,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(176,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(177,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(178,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(179,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(180,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(181,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(182,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(183,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(184,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(185,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(186,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(187,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(188,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(189,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(192,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(193,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(194,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(195,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(196,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(197,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(198,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(199,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(200,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(201,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000),(202,'https://vn-test-11.slatic.net/p/7a6a57489a4bd15543ee26b1ad994c60.jpg','Mũ lưỡi trai','description here',150000);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sessions`
--

DROP TABLE IF EXISTS `user_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sessions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_expired` datetime NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dyv200n8t8bn2vt6pu071b5l0` (`token`),
  KEY `FKsu9fki99h74021nlshm2i5gme` (`account_id`),
  KEY `FK8klxsgb8dcjjklmqebqp1twd5` (`user_id`),
  CONSTRAINT `FK8klxsgb8dcjjklmqebqp1twd5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsu9fki99h74021nlshm2i5gme` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sessions`
--

LOCK TABLES `user_sessions` WRITE;
/*!40000 ALTER TABLE `user_sessions` DISABLE KEYS */;
INSERT INTO `user_sessions` VALUES (1,'2020-02-12 18:34:50','thienchidh$2a$10$GAFdJsBkom/1wbicYH3MmumLnZ64.CrD4hzp2jYO49gdmuxTgqNIC',1,1),(2,'2020-02-13 19:43:09','admin$2a$10$Mtq9jLnlnj0Rcrv.ImColuVs9L7I9h/qbdp9JbcagY3cN5oPt/D1O',2,2),(3,'2020-02-12 18:36:08','admin$2a$10$FnggNhXJA13Tzs5O8WsEQesumL2rvskvyB6uJM5YDL6a5NY7N9Kz2',2,2),(4,'2020-02-12 19:34:40','admin$2a$10$JjcsP8o9iAKaabJLhq2xjOmE0wegadD4HkmD7UOrJZMZpXUb6DSSG',2,2),(5,'2020-02-12 19:36:05','admin$2a$10$3HwebGfj6S7/7NPt3au84.S6mzuFwOEQSWTaohtZsgDDWhCUa8xVC',2,2),(6,'2020-02-12 19:36:29','admin$2a$10$jmPhiZFebWkEir9s4a6ozOMRwZDUylXD3iGI/npS/cMKiZXKjLGRS',2,2),(7,'2020-02-12 19:55:03','admin$2a$10$vbX9zFNAxezjOu5VbK3Yb.R3Royht9al2cg4jH9VAblnXesz9dtuq',2,2),(8,'2020-02-12 19:56:31','admin$2a$10$qbgKC/iTJO0A5ge.PDsCpeLfPJZppiiD9Gml1NypwNEshB3DrweDG',2,2),(9,'2020-02-12 19:57:12','admin$2a$10$1ZwvDCDMplDYSLq1w0SSmO4w.v50Ax5K6I8Q.VcBxZVPVuLShsVam',2,2),(10,'2020-02-12 20:18:15','admin$2a$10$RAYGwR5.cOT2PMbV7QBWCeTkgY01oiJLo0bC2qKl5txmaX6NgtT7u',2,2),(11,'2020-02-12 20:19:53','admin$2a$10$faLX3pVUyCO8ntGu5kXMn.6K3MBlfaVVrxsGLtq6tifDZ2ZfHGtiC',2,2),(12,'2020-02-12 20:21:13','admin$2a$10$apF9138urTCujwPlGMhDFOQSCdDomWw/GCZADxf7s//3v1lCJqaJq',2,2),(13,'2020-02-12 20:22:52','admin$2a$10$VOijPpPiCwjA5d0fcPDScO0r/m.tWZIbE2KUKsBebspEEoJHZhZMS',2,2),(14,'2020-02-12 20:25:15','admin$2a$10$OVyPGEdlvZ0CBk6qfEPHbO60eNJ5WYk8Agb45jfcctRj/MvLXO60e',2,2),(15,'2020-02-12 20:30:48','admin$2a$10$yEJQwebeoeuUOmgr6D0rNuiArW/heBTQQfEAT7w5MbnIhQglUU7H2',2,2),(16,'2020-02-12 20:31:47','admin$2a$10$ZximzIjRAnSmKHoXI9wYLOvXuzL0Lfo/ULoyHB.n2sGEK4/B/CFYK',2,2),(17,'2020-02-12 20:37:07','admin$2a$10$wmtRVw/4oR09BKwB/1yr6.wGbbIqKuR0/xsVARVjXEuipArFIk6..',2,2),(18,'2020-02-12 20:44:44','admin$2a$10$ULGiIl0bh3eDaDGeSwLA0elOE/rWRD3.iSqYlYm2nK.YaB.z.y/bK',2,2),(19,'2020-02-12 20:53:46','admin$2a$10$MKH.BXQ0yEywfeuL1oAGiOoXzCyjQ6WX6G.7ebKB9cgd6/3QiFYtW',2,2),(20,'2020-02-12 20:54:40','admin$2a$10$M18gzPTN4l8M5H/JcgXh2uUEcldhl2LrAuVG7Qh4ui8ZssD5r4wgm',2,2),(21,'2020-02-12 21:03:43','admin$2a$10$dxgqKnCsv2DyPePqv98wuOLhTvNUALjAoqsauIF5YsvXhvuSVq6C.',2,2),(22,'2020-02-13 00:17:49','admin$2a$10$4Y8GXhsD.w9sCAps9a/H2.pZctIBeXUQkdXcPMSIQ3W3wwqaNPLVq',2,2),(23,'2020-02-13 00:47:10','admin$2a$10$DqDcWmGPcvmTVRITwDKE4e4Duo/cTNas11IK.A.z0YVYmgFFnO9HK',2,2),(24,'2020-02-13 00:47:56','admin$2a$10$GENZWicci/FBiP980.cTw.OJVxoVWH2WCcDEmGOipDOBRAaFqS9mq',2,2),(25,'2020-02-13 00:48:23','admin$2a$10$5tJEmJ3Lqa6/LYfGXbrAkOF4YJD8nxUZkvQ2mXMRfZUyQgsml1VM2',2,2),(26,'2020-02-13 02:23:03','thienchidh@gmail.com$2a$10$zlqr4rgnko2Ynjq/h3.0DOsw2AVvjXr6xJGdoe/UE4To7r3fVCWQ.',3,5),(27,'2020-02-13 02:26:50','chi$2a$10$quDZDH/fC63KNZxHqvl8seT.L4M1qEH626AZVx3w/Wz72P91/M0.i',4,6),(28,'2020-02-13 15:58:24','chi1236vn$2a$10$A3LFUNR/nAN.JcdcVgPwoeVrOo7miTjFWbKGoHm8NDM9FQZMDeGti',5,7),(29,'2020-02-13 16:33:24','admin$2a$10$tBeAR14mTli6KKMxVRdjse5983R8Z3vH7eva5dQBTXUwLif53zpPW',2,2),(30,'2020-02-13 22:18:02','admin$2a$10$LPn7iFGwPhdI4wDUTSf1yeWDi8fZzSI0ONyBtLx3hJO3wOvXqW0Sm',2,2),(31,'2020-02-13 22:23:50','admin$2a$10$d9Nzg2TikdI4qu3Zi.Gwq.l.oJSHPsXw9aae8xXXZSIyOCHc1pWH6',2,2),(32,'2020-02-13 22:25:30','admin$2a$10$AXzAiXwdT0XHzJ2JPly7bOKOGUy0BedijGE0uByxb4C2j8r/7zxd6',2,2),(33,'2020-02-13 22:26:01','admin$2a$10$l5otrfh.8KAa76WWT63sKOpUnGwLu/e7xGf7gRlnmWf3y8q45grZm',2,2),(34,'2020-02-13 22:27:01','admin$2a$10$MBCebm.clnvOzTSnrFnTK.RZiNNXR0UeiwwWTi6UYY9yL19yO0MVy',2,2),(35,'2020-02-13 22:43:00','admin$2a$10$KVF2fOr11njGa1LNFYzpouRvuzzD5FTEzVeMsmO.idvYHXVAPZCPG',2,2),(36,'2020-02-13 22:43:33','admin$2a$10$CgU.v5uITzeqxJBG0jqPsuE/EdjKaI57ryRNxo2dCTaVKM6A55VdS',2,2),(37,'2020-02-13 22:44:47','admin$2a$10$80YZcKdwyZNdhLRaAN7PHOkrpQ5jzfm3.EGvg6LfqNXdjdvj5.ymi',2,2),(38,'2020-02-13 23:05:16','chi1236vn$2a$10$1fzl1R29nrQBKznHXTlcCet3k0ogQjOOPI8Xmn7NDhtzSg9/Z8Aoa',5,7),(39,'2020-02-14 02:02:31','chi1236vn$2a$10$2oXSGOhw2ntzcUc4GGq7aOD/O6l3GFwe9mfC.9YodDtMwZUSEFoAK',5,7),(40,'2020-02-14 03:14:44','chi1236vn$2a$10$rw4vdjXkxmHJjwOEgr1Ts.QfM//em33p2FyXpQuITvRSEcSzzRlTG',5,7),(41,'2020-02-14 06:33:45','chi1236vn$2a$10$5NCZLFX1IHTLxxX656IPMu16m8M45L4maB.FOoMLBev26GQSEQbw6',5,7),(42,'2020-02-14 06:35:08','admin$2a$10$Gd/qn1EhEDobwO2p8Bsh7.AuJ/gkP7O33/mSUkQxbC9OiKmvhY2kC',2,2);
/*!40000 ALTER TABLE `user_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `other` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'IS_USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user address','thienchidh@gmail.com','tran','chi','user other','IS_USER'),(2,'admin address','admin@admin.com','tran','chi','admin other','IS_ADMIN'),(5,'_','thienchidh@gmaill.com','tran','chi','_','IS_USER'),(6,'_','thienchidh@gmail','tran','chi','_','IS_USER'),(7,'_','chi@gmail.com','chi','chi','_','IS_USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'web_adv'
--

--
-- Dumping routines for database 'web_adv'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-15 16:48:34
