-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: growingpains
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `fName` varchar(35) DEFAULT NULL,
  `lName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` text,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(3,'John','nhoJ','NO','YES','yo123','12393'),(4,'John','nhoJ','123@123.com','model.Address@15d49048','strongpassword','1111'),(5,'John','nhoJ','123@123.com','Main St., Carlow, Carlow, R93823, Ireland','strongpassword','1111'),(6,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(7,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(8,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(9,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(10,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(11,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(12,'David','DaveJ','david@dave.com','Secondary St., Ballinaboola, Wexford, Y20X391, Ireland','mmynameisdavid','800'),(13,'David','DaveJ','david@dave.com','Primary Rd., Goldthorpe, Wexford, X09S631, Ireland','mmynameisdavid','800'),(14,'David','DaveJ','david@dave.com','Primary Rd., Goldthorpe, Wexford, X09S631, Ireland','mmynameisdavid','800');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `customerID` int DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `shippingAddress` text,
  `totalPrice` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,11,'2025-03-04','22:59:00','Primary Rd., Goldthorpe, Wexford, X09S631, Ireland',27.00),(2,1,11,'2025-03-04','22:56:46','Primary Rd., Goldthorpe, Wexford, X09S631, Ireland',27.00),(3,1,11,'2025-03-04','22:59:00','Primary Rd., Goldthorpe, Wexford, X09S631, Ireland',27.00);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(40) DEFAULT NULL,
  `description` text,
  `price` decimal(4,2) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `category` varchar(30) DEFAULT NULL,
  `image_path` varchar(255) NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Pothos','Pothos 13cm pot, suitable for all owners',14.99,25,'Plant','images/pothos.png'),(4,'Golden Mister','Golden mister, ideal for orchids and high hummidty plants',8.99,30,'Accessory','images/mister.png'),(5,'Monstera','Monstera Adasonii w/ 15cm pot, suitable for all owners',12.99,43,'Plant','images/monsterra.png'),(6,'7cm Giraffe Pot','Gooft Giraffe pot to make your plants more fun',2.99,30,'Accessory','images/griaffe_pot.png'),(7,'Maidenhair Fern','Maidenhair Fern w/ 6cm pot, suitable for all owners',6.99,12,'Plant','images/fern.png'),(8,'Green Pot','Green Pot w/ Eye Design',8.99,3012,'Accessory','images/pot_eyes.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-04 23:20:11
