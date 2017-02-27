CREATE DATABASE  IF NOT EXISTS `comeonbaby` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `comeonbaby`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 89.223.27.239    Database: comeonbaby
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'admin@mail.com','admin@mail.com','123'),(2,'manager1@mail.com','manager1@mail.com','123'),(3,'manager2@mail.com','manager2@mail.com','123'),(4,'customer1@mail.com','customer1@mail.com','123'),(5,'customer2@mail.com','customer2@mail.com','123'),(6,'customer3@mail.com','customer3@mail.com','123');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_user_profile`
--

DROP TABLE IF EXISTS `app_user_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_user_profile` (
  `user_id` int(11) NOT NULL,
  `user_profile_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_user_profile`
--

LOCK TABLES `app_user_user_profile` WRITE;
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` VALUES (4,1),(5,1),(6,1),(2,3),(3,1),(1,2);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic_questions`
--

DROP TABLE IF EXISTS `basic_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basic_questions` (
  `user_id` int(11) DEFAULT NULL,
  `passed_questions` tinyint(1) NOT NULL,
  `years_not_been_pregnant` varchar(255) DEFAULT NULL,
  `children` varchar(255) DEFAULT NULL,
  `treatment` varchar(255) DEFAULT NULL,
  `causes_infertility_female` varchar(255) DEFAULT NULL,
  `causes_infertility_male` varchar(255) DEFAULT NULL,
  `need_help` varchar(255) DEFAULT NULL,
  `plans_for_family` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_questions`
--

LOCK TABLES `basic_questions` WRITE;
/*!40000 ALTER TABLE `basic_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `basic_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` varchar(3056) NOT NULL,
  `datetime` datetime NOT NULL,
  `images` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (6,1,4,'hdhcjcjcj','nchxh j','2017-02-24 14:33:42','edc20433-5135-4b23-a9f3-fde34f070f9d.jpg'),(7,1,2,'Recipe','jfuxhc cjchc jchcu uc icucucj j u y y ycuxhxy','2017-02-24 14:39:53','43f42747-2213-4b56-bf9b-bc622a35c8c0.jpg,139b13cd-f0cd-4c91-816c-b945a5e5396e.jpg'),(8,1,2,'jfjxj k j j h hxh','jchxhxhcuxgxgxg','2017-02-24 14:43:21','3d39bef2-ce51-4b86-a5de-aadb6eab9090.jpg,e9bb9001-d464-4792-b5bc-776ae36773f2.jpg,a983bae9-f68f-469f-a0f1-393b7d1b166b.jpg');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (2,'Busan'),(3,'Gunsan'),(4,'Gwangju'),(1,'Seoul'),(5,'Yangsan');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id_blog` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text` varchar(255) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fertilization_guide`
--

DROP TABLE IF EXISTS `fertilization_guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fertilization_guide` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fertilization_guide`
--

LOCK TABLES `fertilization_guide` WRITE;
/*!40000 ALTER TABLE `fertilization_guide` DISABLE KEYS */;
INSERT INTO `fertilization_guide` VALUES (1,'Guide 1','2010-10-30','https://s-media-cache-ak0.pinimg.com/564x/a0/65/52/a06552adb6060d7fe34ce84b72eb330d.jpg'),(2,'Guide 2','2010-10-20','https://sdl-stickershop.line.naver.jp/stickershop/v1/product/1031622/iphone/main@2x.png');
/*!40000 ALTER TABLE `fertilization_guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `id_body` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `images_0` varchar(255) NOT NULL,
  `images_1` varchar(255) NOT NULL,
  `images_2` varchar(255) NOT NULL,
  `images_3` varchar(255) NOT NULL,
  `images_4` varchar(255) NOT NULL,
  `images_5` varchar(255) NOT NULL,
  `images_6` varchar(255) NOT NULL,
  `images_7` varchar(255) NOT NULL,
  `images_8` varchar(255) NOT NULL,
  `images_9` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgtext`
--

DROP TABLE IF EXISTS `imgtext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgtext` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `img` varchar(45) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `notice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgtext`
--

LOCK TABLES `imgtext` WRITE;
/*!40000 ALTER TABLE `imgtext` DISABLE KEYS */;
/*!40000 ALTER TABLE `imgtext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `id_blog` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `like` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `bbt` float DEFAULT NULL,
  `recommended_food` varchar(255) DEFAULT NULL,
  `recommended_nuts` varchar(255) DEFAULT NULL,
  `has_nuts` tinyint(1) DEFAULT NULL,
  `recommended_tea` varchar(255) DEFAULT NULL,
  `has_tea` tinyint(1) DEFAULT NULL,
  `has_exercise` tinyint(1) DEFAULT NULL,
  `recommended_exercise` varchar(255) DEFAULT NULL,
  `going_to_bed_from` varchar(255) DEFAULT NULL,
  `going_to_bed_to` varchar(255) DEFAULT NULL,
  `water_intake` float DEFAULT NULL,
  `heating_bathing` int(11) DEFAULT NULL,
  `vitamin` tinyint(1) DEFAULT NULL,
  `folic_acid` tinyint(1) DEFAULT NULL,
  `coffee_intake` int(11) DEFAULT NULL,
  `alcohol_intake` int(11) DEFAULT NULL,
  `smoking` tinyint(1) DEFAULT NULL,
  `emotional_state` int(11) DEFAULT NULL,
  `bmi` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (1,1,'2017-02-10',NULL,'duck,Pacific saury','Walnut,Peanut,Almond,Pine nut',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,25.2),(2,1,'2017-02-01',34.5,'potato,Adjudication','Walnut,Peanut,Almond',1,'Jujube,Herbal tea,Golden tea',1,1,'Running / Walking,Yoga / Aerobics','7:0','23:0',2,30,1,1,1,2,1,4,25.2),(3,1,'2017-02-02',36.5,'Anchovy','Walnut',1,'Jujube',1,1,'Running / Walking','7:0','23:0',2,30,1,NULL,1,2,0,0,25.2),(4,1,'2017-02-03',NULL,NULL,NULL,NULL,'Jujube,Herbal tea',1,1,'Yoga / Aerobics',NULL,NULL,NULL,10,NULL,1,NULL,2,1,NULL,25.2),(5,1,'2017-02-07',NULL,'Kim',NULL,NULL,'Jujube',1,1,'Biking / Swimming','7:0','23:0',2,10,1,NULL,NULL,NULL,1,3,25.2),(6,1,'2017-02-05',34.5,'beef','Walnut,Peanut',1,'Jujube,Burdock tea',1,NULL,NULL,NULL,NULL,NULL,30,NULL,1,NULL,2,NULL,3,25.2),(7,1,'2017-02-09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.5,30,1,1,1,1,0,NULL,25.2),(8,1,'2017-02-13',NULL,NULL,NULL,NULL,NULL,NULL,1,'Running / Walking','7:0','23:0',1.5,30,NULL,1,1,1,1,NULL,25.2),(9,1,'2017-02-15',34.5,'Flounder','Walnut',1,'Jujube',1,NULL,NULL,NULL,NULL,1.5,30,1,1,NULL,NULL,NULL,NULL,25.2),(10,1,'2017-02-17',37.5,'Ground garlic,Blue','Walnut,Peanut,Almond',1,NULL,NULL,NULL,NULL,NULL,NULL,1.5,30,1,1,NULL,NULL,NULL,NULL,25.2),(11,1,'2017-02-18',35.5,NULL,NULL,NULL,'Jujube,Burdock tea,Mushroom tea',1,1,'Running / Walking',NULL,NULL,1.5,30,NULL,NULL,NULL,NULL,NULL,NULL,25.2),(12,1,'2017-02-20',36.3,'Mustard kimchi,Perilla','Walnut,Peanut,Almond,Pine nut',1,'Jujube,Burdock tea,Ginger tea,Herbal tea,Mushroom tea,Golden tea',1,1,'Running / Walking,Biking / Swimming,Climb,Yoga / Aerobics','7:0','23:0',1.5,30,1,1,1,2,1,0,25.2),(13,1,'2017-02-19',36.7,'Chinese cabbage','Walnut',1,'Jujube',1,1,'Yoga / Aerobics','7:0','23:0',1.5,30,1,1,1,1,0,2,25.2),(14,1,'2017-02-11',36.3,'Kim,dandelion,Cabbage','Walnut,Peanut,Almond,Pine nut',1,'Jujube,Burdock tea,Ginger tea,Herbal tea',1,1,'Running / Walking','7:0','23:0',1.5,30,1,1,1,1,0,2,25.2),(15,1,'2017-02-14',36.2,'Adjudication,mushroom','Walnut,Peanut',1,'Jujube,Burdock tea',1,1,'Running / Walking','7:0','23:0',1.5,30,1,NULL,2,1,0,2,25.2),(16,2,'2017-02-15',36.5,NULL,NULL,NULL,'Burdock tea,Mushroom tea,Golden tea',1,1,'Climb',NULL,NULL,2,30,NULL,NULL,NULL,NULL,NULL,NULL,1.8),(17,2,'2017-02-18',NULL,'Chinese cabbage,beef,Taran',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.8),(18,3,'2017-02-15',39.5,NULL,NULL,NULL,NULL,NULL,1,'Biking / Swimming,Climb',NULL,NULL,1.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5),(19,3,'2017-02-20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,40,NULL,NULL,NULL,NULL,NULL,NULL,5),(20,4,'2017-02-02',36.3,'salt,salmon','Walnut,Peanut',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15.8),(21,64,'2017-02-21',36.5,'Borkley','Peanut,Almond',1,'Herbal tea',1,NULL,NULL,'7:1','23:2',1.5,30,NULL,NULL,NULL,NULL,NULL,NULL,44.6),(22,5,'2017-02-15',36.5,NULL,'Almond',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(23,5,'2017-02-14',36.3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(24,5,'2017-02-18',35.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(25,5,'2017-02-09',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(26,5,'2017-02-07',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(27,5,'2017-02-06',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.2),(28,6,'2017-02-14',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8.8),(29,6,'2017-02-17',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8.8),(30,5,'2017-02-11',35.5,'chives,tuna','Walnut,Peanut',1,'Jujube',1,1,'Running / Walking','7:4','23:0',1.5,30,1,1,1,NULL,1,4,2.2),(31,1,'2017-02-12',36.3,'eel','Walnut,Peanut',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,40,1,1,NULL,NULL,NULL,NULL,25.2),(32,4,'2017-02-01',37.5,'Mackerel,tuna,stop','Walnut,Peanut,Almond,Pine nut',1,'Jujube,Burdock tea',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15.8),(33,4,'2017-02-03',36.6,'yam,Samchi',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15.8),(34,4,'2017-02-04',35.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,15.8),(35,2,'2017-02-01',36.5,NULL,NULL,NULL,'Herbal tea',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,1,NULL,1.8),(36,2,'2017-02-05',36.5,'sardine,salt',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,30,NULL,NULL,NULL,2,NULL,NULL,1.8),(37,2,'2017-02-09',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.8),(38,2,'2017-02-07',36.5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.8),(39,2,'2017-02-20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.8),(40,11,'2017-02-22',NULL,'Blue',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1.8);
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `text` varchar(255) NOT NULL,
  `html` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` int(10) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferences` (
  `user_id` bigint(20) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthday` int(11) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `menstrual_cycle` int(11) DEFAULT NULL,
  `red_days` int(11) DEFAULT NULL,
  `last_menstruation_start_day` varchar(255) DEFAULT NULL,
  `weigth` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `is_agreement` tinyint(1) DEFAULT NULL,
  `is_finish_question` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
INSERT INTO `preferences` VALUES (1,1,'Olegggggg','false',1987,'dhjjv',26,6,'2017-02-05',80,178,'b3533e44-e81e-44e8-b883-22a672028fa2.png',1,1),(2,2,'Ivan','true',1986,'kiev',28,7,'2017-02-21',55,555,'',1,1),(3,1,'','true',1970,'kiev',28,5,'2017-02-21',55,333,'',1,1),(4,3,'','true',1975,'Street',26,7,'2017-02-03',50,178,'',1,1),(5,1,'ivayyn','false',1970,'kiev',23,4,'2017-02-18',56,500,'',1,1),(6,4,'rr','false',1970,'ivan iyfxiyfd',28,7,'2017-02-21',55,250,'',1,1),(7,1,'kok','false',1970,'cuutt7f',28,7,'2017-02-21',55,22,'',1,1),(8,1,'fhf','false',1970,'grght',28,7,'2017-02-22',555,555,'',1,1),(9,1,'ff','false',1970,'rrf',28,7,'2017-02-22',55,555,'',1,1),(10,NULL,'ivan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(11,1,'ivan','false',1970,'street',28,7,'2017-02-22',55,555,'487a8894-e2fa-482c-ad9a-a0c6a3cedefa.png',1,1),(12,1,'?????','false',1975,'???',28,7,'2017-02-24',55,255,'',1,1),(13,1,'ㅗ효표','false',1974,'ㅗㄹㅇㄷ',28,7,'2017-02-24',55,555,'',1,1),(64,4,'','false',1970,'yhhghhhhjhhh',28,4,'2017-02-11',55,111,'',1,1);
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `q_a`
--

DROP TABLE IF EXISTS `q_a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `q_a` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `is_access` tinyint(1) NOT NULL,
  `answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `q_a`
--

LOCK TABLES `q_a` WRITE;
/*!40000 ALTER TABLE `q_a` DISABLE KEYS */;
/*!40000 ALTER TABLE `q_a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_guide`
--

DROP TABLE IF EXISTS `recipe_guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_guide` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `image_thumbnail` varchar(255) NOT NULL,
  `url_naver` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_guide`
--

LOCK TABLES `recipe_guide` WRITE;
/*!40000 ALTER TABLE `recipe_guide` DISABLE KEYS */;
INSERT INTO `recipe_guide` VALUES (1,'New Recept','2017-10-15','https://s-media-cache-ak0.pinimg.com/736x/f4/f7/10/f4f7100948a588ccd0d0cc7734ea0fb0.jpg','http://m.terms.naver.com/entry.nhn?cid=48168&categoryId=48220&docId=199235'),(2,'새우 칠리 소스 만드는 법','2017-02-23','http://dbscthumb.phinf.naver.net/2756_000_1/20131109032113873_B6TH1L5IQ.jpg/ck_407_i1.jpg?type=w450_fst_n','http://m.terms.naver.com/entry.nhn?cid=48168&categoryId=48220&docId=1992356');
/*!40000 ALTER TABLE `recipe_guide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,'CUSTOMER'),(2,'ADMIN'),(3,'MANAGER');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `social_id` bigint(20) DEFAULT NULL,
  `login_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'oleg@mail.com','qwerty',NULL,'EMAIL'),(2,'ggg@nn.com','',345271282,'KAKAO'),(3,'ivan.pursheha@gmail.com','23kt4qb',171741323309110,'FACEBOOK'),(4,'oleg.sh.vi@gmail.com','u9mdr7',1834663330137272,'FACEBOOK'),(5,'rrrr@gf.com','12345',NULL,'EMAIL'),(6,'ttt@hh.com','12345',NULL,'EMAIL'),(7,'ttt@vvy.com','12345',NULL,'EMAIL'),(8,'trb@h.com','12345',NULL,'EMAIL'),(9,'ivany@h.com','12345',NULL,'EMAIL'),(10,'ivan@mail.com','12345',NULL,'EMAIL'),(11,'ivan2@mail.com','12345',NULL,'EMAIL'),(12,'m@m.com','12345',NULL,'EMAIL'),(13,'g@g.com','12345',NULL,'EMAIL');
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

-- Dump completed on 2017-02-24 15:28:21
