-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nfc_resto
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commandes` (
  `idClient` int(11) NOT NULL,
  `Horodatage` varchar(45) DEFAULT NULL,
  `T_idTables` int(11) NOT NULL,
  `U_idUsers` int(11) NOT NULL,
  KEY `fk_Commandes_Tables_idx` (`T_idTables`),
  KEY `fk_Commandes_Users1_idx` (`U_idUsers`),
  CONSTRAINT `fk_Commandes_Tables` FOREIGN KEY (`T_idTables`) REFERENCES `tables` (`idTables`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Commandes_Users1` FOREIGN KEY (`U_idUsers`) REFERENCES `users` (`idUsers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commandes`
--

LOCK TABLES `commandes` WRITE;
/*!40000 ALTER TABLE `commandes` DISABLE KEYS */;
/*!40000 ALTER TABLE `commandes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plats`
--

DROP TABLE IF EXISTS `plats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plats` (
  `nom` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `T_idTarif` int(11) NOT NULL,
  `idPlat` int(11) NOT NULL AUTO_INCREMENT,
  `idType_Plat` int(11) NOT NULL,
  `image` longblob,
  PRIMARY KEY (`idPlat`),
  KEY `fk_Plats_Tarif1_idx` (`T_idTarif`),
  KEY `idType_Plat_idx` (`idType_Plat`),
  CONSTRAINT `fk_Plats_Tarif1` FOREIGN KEY (`T_idTarif`) REFERENCES `tarif` (`idTarif`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plats`
--

LOCK TABLES `plats` WRITE;
/*!40000 ALTER TABLE `plats` DISABLE KEYS */;
INSERT INTO `plats` VALUES ('Salade Niçoise','Tomates,thon,comcombres',1,2,1,NULL),('Hamburger','Boeuf,fromage,frites',2,3,2,NULL),('Tarte aux citron','Confis de citron, crème légère',1,4,3,NULL),('Jus de ce citron','Glaçon,sucre',6,5,7,NULL);
/*!40000 ALTER TABLE `plats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tables` (
  `idTables` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(200) NOT NULL,
  PRIMARY KEY (`idTables`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarif`
--

DROP TABLE IF EXISTS `tarif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarif` (
  `idTarif` int(11) NOT NULL AUTO_INCREMENT,
  `date_debut` varchar(45) DEFAULT NULL,
  `date_fin` varchar(45) DEFAULT NULL,
  `tva` float DEFAULT NULL,
  `prix` float DEFAULT NULL,
  PRIMARY KEY (`idTarif`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarif`
--

LOCK TABLES `tarif` WRITE;
/*!40000 ALTER TABLE `tarif` DISABLE KEYS */;
INSERT INTO `tarif` VALUES (1,'05/11/2017','05/11/2020',0.25,5),(2,'05/11/2017','05/11/2020',0.25,6),(3,'05/11/2017','05/11/2020',0.25,7),(4,'05/11/2017','05/11/2020',0.25,8),(5,NULL,NULL,NULL,2.5),(6,NULL,NULL,NULL,3);
/*!40000 ALTER TABLE `tarif` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_plat`
--

DROP TABLE IF EXISTS `type_plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_plat` (
  `idType_Plat` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idType_Plat`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_plat`
--

LOCK TABLES `type_plat` WRITE;
/*!40000 ALTER TABLE `type_plat` DISABLE KEYS */;
INSERT INTO `type_plat` VALUES (1,'Entrees'),(5,'Plats'),(6,'Desserts'),(7,'Boissons');
/*!40000 ALTER TABLE `type_plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` tinytext NOT NULL,
  PRIMARY KEY (`idUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-28 10:52:01
