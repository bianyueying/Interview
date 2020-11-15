/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.19 : Database - interview
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`interview` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `interview`;

/*Table structure for table `handle` */

DROP TABLE IF EXISTS `handle`;

CREATE TABLE `handle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note_id` int NOT NULL COMMENT '请假条id',
  `username` varchar(32) NOT NULL COMMENT '处理人',
  `result` varchar(64) NOT NULL COMMENT '处理意见 同意或者不同意',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `handle` */

insert  into `handle`(`id`,`note_id`,`username`,`result`) values (7,1,'111111','驳回'),(8,1,'111111','同意'),(9,1,'222222','同意'),(10,1,'333333','同意'),(11,1,'111111','驳回');

/*Table structure for table `note` */

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `person` varchar(32) NOT NULL COMMENT '谁要请假',
  `user_id` int NOT NULL COMMENT '谁写的',
  `type` tinyint NOT NULL COMMENT '假条类型',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `reason` varchar(256) NOT NULL COMMENT '请假原因',
  `note_status` int DEFAULT '301' COMMENT '假条状态码',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `note` */

insert  into `note`(`id`,`person`,`user_id`,`type`,`start_date`,`end_date`,`reason`,`note_status`) values (1,'边月白',1,2,'2020-05-17 08:00:00','2020-05-31 08:00:00','修改测试',300),(2,'是小边啊',1,2,'2020-05-16 08:00:00','2020-05-30 08:00:00','我有事',301),(3,'王二',1,3,'2020-05-15 08:00:00','2020-05-30 08:00:00','详情13245648945',302),(4,'张三',1,1,'2020-05-23 08:00:00','2020-05-30 08:00:00','年假',303),(5,'李四',1,2,'2020-05-16 21:46:52','2020-05-16 21:46:54','嗯哼',304),(6,'888888',1,1,'2020-09-24 08:00:00','2020-05-17 08:00:00','632541',301),(7,'边月白',1,2,'2020-09-23 08:00:00','2020-09-24 08:00:00','',300),(8,'张三',1,1,'2020-10-17 14:29:48','2020-10-17 14:29:48','生病',301),(9,'测试',1,1,'2020-10-16 15:00:58','2020-10-17 15:01:02','无',301),(10,'测试2',1,1,'2020-10-15 15:01:16','2020-10-17 15:01:19','无',301),(11,'测试3',2,1,'2020-10-15 15:01:33','2020-10-21 15:01:35','无',301),(12,'分页测试',1,2,'2020-10-21 08:00:00','2020-10-29 08:00:00','无',301);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `role` int NOT NULL COMMENT '对应审核，普通为0,1为一级审核；依次递增',
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`role`) values (1,'123456','123456',0),(2,'111111','111111',1),(3,'222222','222222',2),(4,'333333','333333',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
