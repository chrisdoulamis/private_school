-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: privateschool
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `schedule_per_course`
--

DROP TABLE IF EXISTS `schedule_per_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule_per_course` (
  `id_sc_p_c` int(11) NOT NULL AUTO_INCREMENT,
  `monday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tuesday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `wednesday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `thursday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `friday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `saturday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `sunday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `idcourse` int(11) NOT NULL,
  PRIMARY KEY (`id_sc_p_c`),
  KEY `fkIdx_209` (`idcourse`),
  CONSTRAINT `FK_209` FOREIGN KEY (`idcourse`) REFERENCES `courses` (`idcourse`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_per_course`
--

LOCK TABLES `schedule_per_course` WRITE;
/*!40000 ALTER TABLE `schedule_per_course` DISABLE KEYS */;
INSERT INTO `schedule_per_course` VALUES (1,'algevra','geom.','analisi','sinartisiaki','grm.alg','-','-',1),(2,'math1','math2','math3','math4','math5','-','-',2),(3,'math1','math2','math3','math4','math5','-','-',3),(4,'math1','math2','math3','math4','math5','-','-',4),(7,'a','a','a','a','a','a','a',7),(8,'r','t','y','y','d','f','f',8);
/*!40000 ALTER TABLE `schedule_per_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18 23:34:38
