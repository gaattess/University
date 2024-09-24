-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: etravel
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Temporary view structure for view `alps_view`
--

DROP TABLE IF EXISTS `alps_view`;
/*!50001 DROP VIEW IF EXISTS `alps_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `alps_view` AS SELECT 
 1 AS `ID`,
 1 AS `NAME`,
 1 AS `ADRESS`,
 1 AS `PHONE`,
 1 AS `EMAIL`,
 1 AS `PACK`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `CID` int NOT NULL AUTO_INCREMENT,
  `CNAME` varchar(14) NOT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `CNAME` (`CNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (4,'FAMILY'),(2,'ROMANTIC'),(3,'SUMMER'),(1,'WINTER');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CUSTID` int NOT NULL AUTO_INCREMENT,
  `CSNAME` varchar(50) NOT NULL,
  `CADRESS` varchar(60) NOT NULL,
  `CPHONE` varchar(11) NOT NULL,
  `CEMAIL` varchar(255) NOT NULL,
  `CPACK` int NOT NULL,
  PRIMARY KEY (`CUSTID`),
  KEY `CPACK` (`CPACK`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`CPACK`) REFERENCES `packages` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Alfonso Santana','2137 Ipsum St.','6999881132','alfonsosantana@aol.com',4),(2,'Constance Davidson','9676 Cras Av.','2226148298','constancedavidson@outlook.com',7),(3,'Leandra Beach','1478 Nunc, Street','2450974482','leandrabeach@protonmail.com',6),(4,'Chase Tyler','Ap #379-6580 Auctor. Rd.','6944467445','chasetyler@yahoo.com',2),(5,'Simone Harris','Ap #723-9708 Vitae St.','2628461581','simoneharris@google.com',8),(6,'Lars Conley','Ap #220-6316 Odio. Road','6922627264','larsconley7819@google.com',5),(7,'Latifah Hendricks','9597 Turpis Avenue','2932504105','latifahhendricks@protonmail.com',1),(8,'Dean Kirby','862-5583 Egestas, Road','2657681522','deankirby@hotmail.com',3),(9,'Olympia Morse','Ap #126-6932 Libero. Road','2778856521','olympiamorse@google.com',3),(10,'Jarrod Harmon','Ap #350-9911 Elit St.','6966751831','jarrodharmon@hotmail.com',8),(11,'Chloe Cline','429-4974 Nascetur St.','2775115683','chloecline@yahoo.com',9);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packages`
--

DROP TABLE IF EXISTS `packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages` (
  `PID` int NOT NULL AUTO_INCREMENT,
  `PLOC` varchar(30) DEFAULT NULL,
  `BDATE` date NOT NULL,
  `EDATE` date NOT NULL,
  `CATEGORY` varchar(14) NOT NULL,
  `TRANSP` varchar(14) NOT NULL,
  `COST` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`PID`),
  KEY `TRANSP` (`TRANSP`),
  KEY `CATEGORY` (`CATEGORY`),
  CONSTRAINT `packages_ibfk_1` FOREIGN KEY (`TRANSP`) REFERENCES `transport` (`TNAME`),
  CONSTRAINT `packages_ibfk_2` FOREIGN KEY (`CATEGORY`) REFERENCES `categories` (`CNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages`
--

LOCK TABLES `packages` WRITE;
/*!40000 ALTER TABLE `packages` DISABLE KEYS */;
INSERT INTO `packages` VALUES (1,'VENICE','2022-02-10','2022-02-15','ROMANTIC','AIRPLANE',1100.00),(2,'BANSKO','2021-12-19','2021-12-21','WINTER','BUS',900.00),(3,'PRAGUE','2021-12-23','2021-12-26','FAMILY','AIRPLANE',2000.00),(4,'PALMA','2022-06-10','2022-06-30','SUMMER','AIRPLANE',7000.00),(5,'PALMA','2022-06-10','2022-06-30','SUMMER','SHIP',4600.00),(6,'PARIS','2021-12-29','2022-01-02','ROMANTIC','AIRPLANE',4000.00),(7,'PARIS','2021-12-29','2022-01-02','FAMILY','AIRPLANE',6000.00),(8,'ALPS','2022-01-15','2022-01-19','WINTER','AIRPLANE',3000.00),(9,'ARACHOVA','2022-01-20','2022-01-23','WINTER','BUS',300.00);
/*!40000 ALTER TABLE `packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `palma_view`
--

DROP TABLE IF EXISTS `palma_view`;
/*!50001 DROP VIEW IF EXISTS `palma_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `palma_view` AS SELECT 
 1 AS `ID`,
 1 AS `NAME`,
 1 AS `ADRESS`,
 1 AS `PHONE`,
 1 AS `EMAIL`,
 1 AS `PACK`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `TID` int NOT NULL AUTO_INCREMENT,
  `TNAME` varchar(14) NOT NULL,
  PRIMARY KEY (`TID`),
  UNIQUE KEY `TNAME` (`TNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
INSERT INTO `transport` VALUES (1,'AIRPLANE'),(2,'BUS'),(4,'SHIP'),(3,'TRAIN');
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `alps_view`
--

/*!50001 DROP VIEW IF EXISTS `alps_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `alps_view` (`ID`,`NAME`,`ADRESS`,`PHONE`,`EMAIL`,`PACK`) AS select `customers`.`CUSTID` AS `CUSTID`,`customers`.`CSNAME` AS `CSNAME`,`customers`.`CADRESS` AS `CADRESS`,`customers`.`CPHONE` AS `CPHONE`,`customers`.`CEMAIL` AS `CEMAIL`,`customers`.`CPACK` AS `CPACK` from `customers` where (`customers`.`CPACK` = 8) group by `customers`.`CUSTID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `palma_view`
--

/*!50001 DROP VIEW IF EXISTS `palma_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `palma_view` (`ID`,`NAME`,`ADRESS`,`PHONE`,`EMAIL`,`PACK`) AS select `customers`.`CUSTID` AS `CUSTID`,`customers`.`CSNAME` AS `CSNAME`,`customers`.`CADRESS` AS `CADRESS`,`customers`.`CPHONE` AS `CPHONE`,`customers`.`CEMAIL` AS `CEMAIL`,`customers`.`CPACK` AS `CPACK` from `customers` where ((`customers`.`CPACK` = 4) or (`customers`.`CPACK` = 5)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-13  3:37:39
