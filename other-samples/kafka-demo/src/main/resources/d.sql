/*
SQLyog Enterprise Trial - MySQL GUI v7.11 
MySQL - 5.5.62 : Database - kafka_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`kafka_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kafka_demo`;

/*Table structure for table `t_info` */

DROP TABLE IF EXISTS `t_info`;

CREATE TABLE `t_info` (
  `id` varchar(32) NOT NULL,
  `topic` varchar(32) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `insert_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
