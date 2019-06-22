-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hrm_db
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `dept_inf`
--

DROP TABLE IF EXISTS `dept_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `dept_inf`
(
    `id`     int(11)     not null auto_increment,
    `name`   varchar(50) not null,
    `remark` varchar(300) default null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_inf`
--

LOCK TABLES `dept_inf` WRITE;
/*!40000 ALTER TABLE `dept_inf`
    DISABLE KEYS */;
INSERT INTO `dept_inf`
VALUES (1, '技术部', '技术部'),
       (2, '运营部', '运营部'),
       (3, '财务部', '财务部'),
       (5, '总公办', '总公办'),
       (6, '市场部', '市场部'),
       (7, '教学部', '教学部'),
       (10, '外交部', '外交部'),
       (11, '测试部', '测试部'),
       (12, 'spring-boot-hello', 'spring'),
       (13, '游戏策划部', '游戏策划部');
/*!40000 ALTER TABLE `dept_inf`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_inf`
--

DROP TABLE IF EXISTS `document_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `document_inf`
(
    `id`          int(11)      not null auto_increment,
    `title`       varchar(50)  not null,
    `filename`    varchar(300) not null,
    `remark`      varchar(300)          default null,
    `create_date` timestamp    not null default current_timestamp,
    `user_id`     int(11)               default null,
    primary key (`id`),
    key `fk_document_user` (`user_id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_inf`
--

LOCK TABLES `document_inf` WRITE;
/*!40000 ALTER TABLE `document_inf`
    DISABLE KEYS */;
INSERT INTO `document_inf`
VALUES (1, 'test', 'spring', 'test', '2018-06-30 00:40:14', 1);
/*!40000 ALTER TABLE `document_inf`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_inf`
--

DROP TABLE IF EXISTS `employee_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `employee_inf`
(
    `id`          int(11)     not null auto_increment,
    `dept_id`     int(11)     not null,
    `job_id`      int(11)     not null,
    `name`        varchar(20) not null,
    `card_id`     varchar(18) not null,
    `address`     varchar(50) not null,
    `post_code`   varchar(50)          default null,
    `tel`         varchar(16)          default null,
    `phone`       varchar(11) not null,
    `qq_num`      varchar(10)          default null,
    `email`       varchar(50) not null,
    `sex`         int(11)     not null default '1',
    `party`       varchar(10)          default null,
    `birthday`    datetime             default null,
    `race`        varchar(100)         default null,
    `education`   varchar(10)          default null,
    `speciality`  varchar(20)          default null,
    `hobby`       varchar(100)         default null,
    `remark`      varchar(500)         default null,
    `create_date` timestamp   not null default current_timestamp,
    primary key (`id`),
    key `fk_emp_dept` (`dept_id`),
    key `fk_emp_job` (`job_id`),
    constraint `fk_emp_dept` foreign key (`dept_id`) references `dept_inf` (`id`),
    constraint `fk_emp_job` foreign key (`job_id`) references `job_inf` (`id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_inf`
--

LOCK TABLES `employee_inf` WRITE;
/*!40000 ALTER TABLE `employee_inf`
    DISABLE KEYS */;
INSERT INTO `employee_inf`
VALUES (1, 1, 8, '爱丽丝', '4328011988', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com',
        0, '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2016-03-14 03:35:18'),
       (2, 12, 1, '杰克', '410324199909211234', '43234', '123456', '021-12345678', '13899999999', '1234567',
        'jack@qq.com', 2, '党员', '2018-06-25 00:00:00', '汉族', '哈佛大学', '数学', 'wow', '', '2016-03-14 03:35:18'),
       (3, 1, 2, 'bb', '432801197711251038', '广州', '510000', '020-99999999', '13907351532', '36750064',
        '36750064@qq.com', 1, '党员', '1977-11-25 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2016-07-14 01:54:52');
/*!40000 ALTER TABLE `employee_inf`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_inf`
--

DROP TABLE IF EXISTS `job_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `job_inf`
(
    `id`     int(11)     not null auto_increment,
    `name`   varchar(50) not null,
    `remark` varchar(300) default null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_inf`
--

LOCK TABLES `job_inf` WRITE;
/*!40000 ALTER TABLE `job_inf`
    DISABLE KEYS */;
INSERT INTO `job_inf`
VALUES (1, '职员', '职员'),
       (2, 'Java开发工程师', 'Java开发工程师'),
       (3, 'Java中级开发工程师', 'Java中级开发工程师'),
       (4, 'Java高级开发工程师', 'Java高级开发工程师'),
       (5, '系统管理员', '系统管理员'),
       (6, '架构师', '架构师'),
       (7, '主管', '主管'),
       (8, '经理', '经理'),
       (9, '总经理', '总经理'),
       (10, '外交官', '外交官'),
       (11, '电子竞技教练', '电子竞技教练');
/*!40000 ALTER TABLE `job_inf`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_inf`
--

DROP TABLE IF EXISTS `notice_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `notice_inf`
(
    `id`          int(11)     not null auto_increment,
    `title`       varchar(50) not null,
    `content`     text        not null,
    `create_date` timestamp   not null default current_timestamp,
    `user_id`     int(11)              default null,
    primary key (`id`),
    key `fk_notice_user` (`user_id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_inf`
--

LOCK TABLES `notice_inf` WRITE;
/*!40000 ALTER TABLE `notice_inf`
    DISABLE KEYS */;
INSERT INTO `notice_inf`
VALUES (1, '明天放假', '明天放假', '2018-06-30 11:39:04', 1),
       (2, '明天放假', '明天放假', '2018-06-30 11:52:59', 1);
/*!40000 ALTER TABLE `notice_inf`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_inf`
--

DROP TABLE IF EXISTS `user_inf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
create table `user_inf`
(
    `id`          int(11)      not null auto_increment,
    `login_name`  varchar(20)  not null,
    `password`    varchar(512) not null,
    `status`      int(11)      not null default '1',
    `create_date` timestamp    not null default current_timestamp,
    `username`    varchar(20)           default null,
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_inf`
--

LOCK TABLES `user_inf` WRITE;
/*!40000 ALTER TABLE `user_inf`
    DISABLE KEYS */;
INSERT INTO `user_inf`
VALUES (1, 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, '2016-03-12 01:34:28',
        '超级管理员'),
       (2, 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, '2018-03-17 01:52:46',
        'test'),
       (3, 'zhangsan', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, '2018-03-17 01:56:39',
        '张三'),
       (6, 'wuji', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 2, '2018-07-01 00:48:42', '张无忌'),
       (7, 'min', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', 1, '2018-07-01 00:49:45', '赵敏');
/*!40000 ALTER TABLE `user_inf`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2018-07-01  9:10:07
create index index_lp on user_inf (login_name, password);
create index index_c on document_inf (create_date desc);
create index index_c on employee_inf (create_date desc);
create index index_c on notice_inf (create_date desc);
create index index_c on user_inf (create_date desc);
