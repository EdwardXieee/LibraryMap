-- MySQL dump 10.13  Distrib 5.7.37, for Linux (x86_64)
--
-- Host: localhost    Database: untitled
-- ------------------------------------------------------
-- Server version	5.7.37

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
-- Table structure for table `library_map`
--

DROP TABLE IF EXISTS `library_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library_map` (
  `id` varchar(100) NOT NULL COMMENT '主键id',
  `img_path` varchar(255) DEFAULT NULL COMMENT '地图图片',
  `floor_num` tinyint(4) NOT NULL DEFAULT '0' COMMENT '楼层数',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书馆地图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_map`
--

LOCK TABLES `library_map` WRITE;
/*!40000 ALTER TABLE `library_map` DISABLE KEYS */;
INSERT INTO `library_map` VALUES ('id1', 'img_path1', 1, '2022-08-17 09:03:26'),('id2', 'img_path2', 2, '2022-08-17 09:03:26'),('id3', 'img_path3', 3, '2022-08-17 09:03:26');
/*!40000 ALTER TABLE `library_map` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `facility_info`
--

DROP TABLE IF EXISTS `facility_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility_info` (
  `id` varchar(100) NOT NULL COMMENT '主键id',
  `category` int(11) NOT NULL DEFAULT 1 COMMENT '设施分类',
  `name_CN` varchar(255) DEFAULT NULL COMMENT '中文名称',
  `name_EN` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `description_CN` varchar(255) DEFAULT NULL COMMENT '中文描述',
  `description_EN` varchar(255) DEFAULT NULL COMMENT '英文描述',
  `floor_num` tinyint(4) NOT NULL DEFAULT '0' COMMENT '楼层数',
  `content_for_search` varchar(255) DEFAULT NULL COMMENT '搜索条目（用#进行区分）',
  `img_path` varchar(255) DEFAULT NULL COMMENT '设施图片',
  `status` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书馆设施';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_info`
--

LOCK TABLES `facility_info` WRITE;
/*!40000 ALTER TABLE `facility_info` DISABLE KEYS */;
INSERT INTO `facility_info` VALUES ('fid1', 1, '名字', 'name', '描述', 'description', 1, "#多功能#万能#投影","nqprod/oDwsO5Mjxnj2-O1yXl064bOQbELQ/article/1910312CX6FK5N0H/0.png",1,'2022-08-17 09:03:26'),('fid2', 1, '名字', 'name', '描述', 'description', 2, "#多功能#万能#投影","nqprod/oDwsO5Mjxnj2-O1yXl064bOQbELQ/article/1910312CX6FK5N0H/0.png",1,'2022-08-17 09:03:26');
/*!40000 ALTER TABLE `facility_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `search_record`
--

DROP TABLE IF EXISTS `search_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_record` (
  `id` varchar(45) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_record`
--

LOCK TABLES `search_record` WRITE;
/*!40000 ALTER TABLE `search_record` DISABLE KEYS */;
INSERT INTO `search_record` VALUES ('sd','sdf');
/*!40000 ALTER TABLE `search_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelf_info`
--

DROP TABLE IF EXISTS `shelf_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shelf_info` (
  `id` varchar(100) NOT NULL COMMENT '主键id',
  `facility_id` varchar(100) NOT NULL COMMENT '设施id',
  `category_CN` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '中文类别',
  `category_EN` varchar(255) DEFAULT NULL COMMENT '英文类别',
  `popularity_CN` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '欢迎程度中文描述',
  `popularity_EN` varchar(255) DEFAULT NULL COMMENT '欢迎程度英文描述',
  `status` int(11) NOT NULL DEFAULT '1',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `shelf_info_idx` (`facility_id`),
  CONSTRAINT `shelf_info_ibfk_1` FOREIGN KEY (`facility_id`) REFERENCES `facility_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书馆设施';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelf_info`
--

LOCK TABLES `shelf_info` WRITE;
/*!40000 ALTER TABLE `shelf_info` DISABLE KEYS */;
INSERT INTO `shelf_info` VALUES ('id1', 'fid1', '类别', 'category','欢迎','popularity',1, '2022-08-17 09:03:26');
/*!40000 ALTER TABLE `shelf_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-31 20:11:35facility_info