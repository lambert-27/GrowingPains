-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: GrowingPains
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
  `password` varchar(64) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Aoife','Murphy','aoife.murphy@gmail.com','12 Main St, Dublin, Ireland','de9ad42a71b4e9dcd3a2d94791ce6d591327aa3a016ce2764f36c258495d6792','+353123456789'),(2,'Sean','Murphy','sean.oconnor@hotmail.ie','45 Elm Road, Cork, Ireland','e337af8bad50933e51e16a09ed237fc60c7818bd94b1f306c5e5a7d74053a86c','08987654321'),(3,'Padraig','Kelly','padraig.kelly@example.com','34 Pine Lane, Limerick, Ireland','966e2eaa407efb5bc9ea0cacdd9ea2534c67254f3e5b6d92e663d63e0ba17390','067 123 4567'),(4,'Mark','Lambert','marklambert123@gmail.com','Ireland','9390298f3fb0c5b160498935d79cb139aef28e1c47358b4bbba61862b9c26e59','086 1694 202'),(5,'Hannah','Flint','flinthannah@aol.com','Goldthorpe, Yorkshire, England','46b9e6bdf350c8523d18a15d77716d05dc167a0360139f0ea5d775daf095aa44','0827 2727 272'),(16,'Ad','Min','admin@growingpains.com','Admins Basement','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb','0871231234'),(17,'Password = ','a','thisisfor@hashing.debugging','Note the hash output for \"a\"','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb','0000000000');
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
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `shippingAddress` text,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,16,'2025-04-04','00:54:04','Admins Basement',8.99);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Pothos','Pothos 13cm pot, suitable for all owners',14.99,27,'Plant','images/pothos.png'),(4,'Golden Mister','Golden mister, ideal for orchids and high hummidty plants',8.99,12,'Accessory','images/mister.png'),(5,'Monstera','Monstera Adasonii w/ 15cm pot, suitable for all owners',12.99,41,'Plant','images/monsterra.png'),(6,'7cm Giraffe Pot','Gooft Giraffe pot to make your plants more fun',2.99,23,'Accessory','images/griaffe_pot.png'),(7,'Maidenhair Fern','Maidenhair Fern w/ 6cm pot, suitable for all owners',6.99,4,'Plant','images/fern.png'),(8,'Green Pot','Green Pot w/ Eye Design',8.99,3012,'Accessory','images/pot_eyes.png'),(21,'Golden Pothos','Golden Pothos w/ 8cm pot, suitable for all owners',5.99,26,'Plant','images/golden_pothos.png'),(22,'13cm Pot with Motif','Hand painted ceramic pot',10.99,10,'Accessory','images/pot_egg.png'),(23,'Pilea','Pilea - Chinea Money Plant',5.99,27,'Plant','images/pilea.png'),(24,'Moisture Meter','Moisture Meter - Single probe, excellent for all experience levels for watering',4.99,38,'Accessory','images/moisture_meter.png'),(25,'Spider Plant','Spider Plant - Suitable for all experience levels, loves humidity',9.99,27,'Plant','images/spider.png'),(26,'8cm Pot with Wooden Stand','Duck egg blue pot with wooden stand',14.99,4,'Accessory','images/pot_stand.png'),(27,'String of Hearts','String of Hearts - Vining indoor plant',9.99,12,'Plant','images/soh.png'),(28,'String of Bananas','String of Bananas - Succulent vining plant',19.99,10,'Plant','images/sob.png'),(29,'Alocasia Poly','Alocasia - Elephant\'s Ear or Poly',15.99,14,'Plant','images/alocasia.png');
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

-- Dump completed on 2025-04-04  0:56:56
