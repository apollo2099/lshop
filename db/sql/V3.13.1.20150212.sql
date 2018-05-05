/*
SQLyog v10.2 
MySQL - 5.1.54-community-log : Database - gv_tvpad_web
*********************************************************************
*/
USE `gv_tvpad_web`;

/*Table structure for table `lv_pattern_country` */
DROP TABLE IF EXISTS `lv_pattern_country`;
CREATE TABLE `lv_pattern_country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL COMMENT 'VERSION',
  `pattern_code` smallint(3) DEFAULT NULL COMMENT '规格(1=美规,2=澳规,3=英规,4=欧规,5=韩规)',
  `country_name` varchar(32) DEFAULT NULL COMMENT '国家名称',
  `country_code` varchar(32) DEFAULT NULL COMMENT '国家编码',
  `country_id` int(11) DEFAULT NULL COMMENT '国家ID',
  `status` int(11) DEFAULT NULL COMMENT '-1=删除,1=正常',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_updated_by` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `last_updated_date` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='产品规格-国家关联表';

/*Data for the table `lv_pattern_country` */

LOCK TABLES `lv_pattern_country` WRITE;

insert  into `lv_pattern_country`(`id`,`version`,`pattern_code`,`country_name`,`country_code`,`country_id`,`status`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`) values (1,0,2,'新西兰','NZ',100190,1,'000000USER20120510183730','2013-02-28 17:30:50',NULL,NULL),(2,0,2,'斐济','FJ',100037,1,'000000USER20120510183730','2013-02-28 17:30:59',NULL,NULL),(3,0,2,'澳大利亚','AU',100007,1,'000000USER20120510183730','2013-02-28 17:31:12',NULL,NULL),(4,0,1,'加拿大','CA',100020,1,'000000USER20120510183730','2013-02-28 17:32:00',NULL,NULL),(5,0,1,'美国','US',100226,1,'000000USER20120510183730','2013-02-28 17:32:13',NULL,NULL),(6,0,1,'多米尼加','DO',100147,1,'000000USER20120510183730','2013-02-28 17:32:32',NULL,NULL),(7,0,1,'日本','JP',100054,1,'000000USER20120510183730','2013-02-28 17:32:44',NULL,NULL),(8,0,1,'巴西','BR',100015,1,'000000USER20120510183730','2013-02-28 17:32:53',NULL,NULL),(9,0,1,'墨西哥','MX',100183,1,'000000USER20120510183730','2013-02-28 17:33:03',NULL,NULL),(10,0,1,'哥伦比亚','CO',100140,1,'000000USER20120510183730','2013-02-28 17:33:17',NULL,NULL),(11,0,1,'关岛','GU',100158,1,'000000USER20120510183730','2013-02-28 17:33:25',NULL,NULL),(12,0,1,'美属萨摩亚','AS',100120,1,'000000USER20120510183730','2013-02-28 17:33:40',NULL,NULL),(13,0,1,'巴拿马','PA',100083,1,'000000USER20120510183730','2013-02-28 17:33:51',NULL,NULL),(14,0,1,'泰国','TH',100218,-1,'000000USER20120510183730','2013-02-28 17:34:02',NULL,NULL),(15,0,1,'菲律宾','PH',100085,1,'000000USER20120510183730','2013-02-28 17:34:16',NULL,NULL),(16,0,1,'委内瑞拉','VE',100228,1,'000000USER20120510183730','2013-02-28 17:34:36',NULL,NULL),(17,0,1,'越南','VN',100115,1,'000000USER20120510183730','2013-02-28 17:34:48',NULL,NULL),(18,0,1,'韩国','KR',100171,1,'000000USER20120510183730','2013-02-28 17:34:58',NULL,NULL),(19,0,1,'台湾','TW',100103,1,'000000USER20120510183730','2013-02-28 17:35:12',NULL,NULL),(20,0,1,'巴拉圭','PY',100084,1,'000000USER20120510183730','2013-02-28 17:35:36',NULL,NULL),(21,0,1,'秘鲁','PE',100197,1,'000000USER20120510183730','2013-02-28 17:35:53',NULL,NULL),(22,0,1,'沙特阿拉伯','SA',100206,-1,'000000USER20120510183730','2013-02-28 17:36:40',NULL,NULL),(23,0,1,'洪都拉斯','HN',100048,1,'000000USER20120510183730','2013-02-28 17:36:46',NULL,NULL),(24,0,1,'阿鲁巴','AW',100124,1,'000000USER20120510183730','2013-02-28 17:37:02',NULL,NULL),(25,0,1,'中国','CN',100023,1,'000000USER20120510183730','2013-02-28 17:37:13',NULL,NULL),(26,0,1,'马来西亚','MY',100179,-1,'000000USER20120510183730','2013-02-28 17:37:28',NULL,NULL),(27,0,1,'孟加拉国','BD',100009,1,'000000USER20120510183730','2013-02-28 17:38:00',NULL,NULL),(28,0,1,'马尔代夫','MV',100066,1,'000000USER20120510183730','2013-02-28 17:38:14',NULL,NULL),(29,0,1,'柬埔寨','KH',100019,1,'000000USER20120510183730','2013-02-28 17:38:31',NULL,NULL),(30,0,1,'哥斯达黎加','CR',100027,1,'000000USER20120510183730','2013-02-28 17:38:43',NULL,NULL),(31,0,1,'波多黎各','PR',100199,1,'000000USER20120510183730','2013-02-28 17:38:54',NULL,NULL),(32,0,1,'危地马拉','GT',100045,1,'000000USER20120510183730','2013-02-28 17:39:18',NULL,NULL),(33,0,1,'古巴','CU',100144,1,'000000USER20120510183730','2013-02-28 17:39:31',NULL,NULL),(34,0,1,'牙买加','JM',100168,1,'000000USER20120510183730','2013-02-28 17:39:42',NULL,NULL),(35,0,1,'尼加拉瓜','NI',100078,1,'000000USER20120510183730','2013-02-28 17:39:53',NULL,NULL),(36,0,1,'尼日利亚','NG',100079,1,'000000USER20120510183730','2013-02-28 17:40:17',NULL,NULL),(37,0,1,'老挝','LA',100059,1,'000000USER20120510183730','2013-02-28 17:40:33',NULL,NULL),(38,0,1,'利比亚','LY',100175,1,'000000USER20120510183730','2013-02-28 17:40:54',NULL,NULL),(39,0,1,'瑙鲁','NR',100075,1,'000000USER20120510183730','2013-02-28 17:41:06',NULL,NULL),(40,0,1,'帕劳','PW',100195,1,'000000USER20120510183730','2013-02-28 17:41:17',NULL,NULL),(41,0,1,'瓦努阿图','VU',100114,1,'000000USER20120510183730','2013-02-28 17:41:29',NULL,NULL),(42,0,3,'新加坡','SG',100096,1,'000000USER20120510183730','2013-02-28 17:42:45',NULL,NULL),(43,0,3,'香港','HK',100163,1,'000000USER20120510183730','2013-02-28 17:42:57',NULL,NULL),(44,0,3,'澳门','MO',100177,1,'000000USER20120510183730','2013-02-28 17:43:08',NULL,NULL),(45,0,3,'爱尔兰','IE',100052,1,'000000USER20120510183730','2013-02-28 17:43:18',NULL,NULL),(46,0,3,'文莱','BN',100132,1,'000000USER20120510183730','2013-02-28 17:48:28',NULL,NULL),(47,0,3,'斯里兰卡','LK',100099,1,'000000USER20120510183730','2013-02-28 17:49:17',NULL,NULL),(48,0,3,'巴林','BH',100126,1,'000000USER20120510183730','2013-02-28 17:49:33',NULL,NULL),(49,0,3,'塞浦路斯','CY',100029,1,'000000USER20120510183730','2013-02-28 17:49:53',NULL,NULL),(50,0,3,'英国','GB',100112,1,'000000USER20120510183730','2013-02-28 17:50:07',NULL,NULL),(51,0,3,'卡塔尔','QA',100087,1,'000000USER20120510183730','2013-02-28 17:51:13',NULL,NULL),(52,0,3,'加纳','GH',100155,1,'000000USER20120510183730','2013-02-28 17:51:32',NULL,NULL),(53,0,3,'肯尼亚','KE',100056,1,'000000USER20120510183730','2013-02-28 17:52:18',NULL,NULL),(54,0,4,'法属圭亚那','GF',100153,1,'000000USER20120510183730','2013-02-28 17:53:50',NULL,NULL),(55,0,4,'马提尼克','MQ',100181,1,'000000USER20120510183730','2013-02-28 17:54:16',NULL,NULL),(56,0,4,'南非','ZA',100211,1,'000000USER20120510183730','2013-02-28 17:54:36',NULL,NULL),(57,0,4,'亚美尼亚','AM',100006,1,'000000USER20120510183730','2013-02-28 17:54:52',NULL,NULL),(58,0,4,'印度尼西亚','ID',100165,1,'000000USER20120510183730','2013-02-28 17:55:07',NULL,NULL),(59,0,4,'印度','IN',100050,1,'000000USER20120510183730','2013-02-28 17:55:07',NULL,NULL),(60,0,4,'捷克','CZ',100145,1,'000000USER20120510183730','2013-02-28 17:55:38',NULL,NULL),(61,0,4,'卢森堡','LU',100063,1,'000000USER20120510183730','2013-02-28 17:55:50',NULL,NULL),(62,0,4,'朝鲜','KP',100057,1,'000000USER20120510183730','2013-02-28 17:56:48',NULL,NULL),(63,0,4,'巴基斯坦','PK',100082,1,'000000USER20120510183730','2013-02-28 17:57:01',NULL,NULL),(64,0,4,'缅甸','MM',100018,1,'000000USER20120510183730','2013-02-28 17:57:13',NULL,NULL),(65,0,4,'阿拉伯联合酋长国','AE',100225,1,'000000USER20120510183730','2013-02-28 17:57:26',NULL,NULL),(66,0,4,'土耳其','TR',100222,1,'000000USER20120510183730','2013-02-28 17:57:39',NULL,NULL),(67,0,4,'尼泊尔','NP',100188,1,'000000USER20120510183730','2013-02-28 17:57:52',NULL,NULL),(68,0,4,'蒙古','MN',100185,1,'000000USER20120510183730','2013-02-28 17:58:01',NULL,NULL),(69,0,4,'乌兹别克斯坦','UZ',100227,1,'000000USER20120510183730','2013-02-28 17:58:44',NULL,NULL),(70,0,4,'吉尔吉斯斯坦','KG',100172,1,'000000USER20120510183730','2013-02-28 17:58:44',NULL,NULL),(71,0,4,'哈萨克斯坦','KZ',100169,1,'000000USER20120510183730','2013-02-28 17:58:44',NULL,NULL),(72,0,4,'伊朗','IR',100051,1,'000000USER20120510183730','2013-02-28 17:58:54',NULL,NULL),(73,0,4,'以色列','IL',100167,1,'000000USER20120510183730','2013-02-28 17:59:07',NULL,NULL),(74,0,4,'西班牙','ES',100212,1,'000000USER20120510183730','2013-02-28 18:00:02',NULL,NULL),(75,0,4,'葡萄牙','PT',100198,1,'000000USER20120510183730','2013-02-28 18:00:02',NULL,NULL),(76,0,4,'匈牙利','HU',100049,1,'000000USER20120510183730','2013-02-28 18:00:02',NULL,NULL),(77,0,4,'希腊','GR',100156,1,'000000USER20120510183730','2013-02-28 18:00:13',NULL,NULL),(78,0,4,'俄罗斯','RU',100201,1,'000000USER20120510183730','2013-02-28 18:00:32',NULL,NULL),(79,0,4,'白俄罗斯','BY',100010,1,'000000USER20120510183730','2013-02-28 18:00:32',NULL,NULL),(80,0,4,'德国','DE',100041,1,'000000USER20120510183730','2013-02-28 18:01:00',NULL,NULL),(81,0,4,'法国','FR',100038,1,'000000USER20120510183730','2013-02-28 18:01:00',NULL,NULL),(82,0,4,'芬兰','FI',100152,1,'000000USER20120510183730','2013-02-28 18:01:32',NULL,NULL),(83,0,4,'乌克兰','UA',100111,1,'000000USER20120510183730','2013-02-28 18:01:32',NULL,NULL),(84,0,4,'波兰','PL',100086,1,'000000USER20120510183730','2013-02-28 18:01:32',NULL,NULL),(85,0,4,'荷兰','NL',100076,1,'000000USER20120510183730','2013-02-28 18:01:32',NULL,NULL),(86,0,4,'挪威','NO',100081,1,'000000USER20120510183730','2013-02-28 18:01:44',NULL,NULL),(87,0,4,'保加利亚','BG',100017,1,'000000USER20120510183730','2013-02-28 18:01:58',NULL,NULL),(88,0,4,'奥地利','AT',100125,1,'000000USER20120510183730','2013-02-28 18:02:12',NULL,NULL),(89,0,4,'斯洛文尼亚','SI',100097,1,'000000USER20120510183730','2013-02-28 18:02:29',NULL,NULL),(90,0,4,'丹麦','DK',100030,1,'000000USER20120510183730','2013-02-28 18:02:44',NULL,NULL),(91,0,4,'罗马尼亚','RO',100088,1,'000000USER20120510183730','2013-02-28 18:03:17',NULL,NULL),(92,0,4,'冰岛','IS',100164,1,'000000USER20120510183730','2013-02-28 18:03:32',NULL,NULL),(93,0,4,'瑞典','SE',100215,1,'000000USER20120510183730','2013-02-28 18:03:44',NULL,NULL),(94,0,4,'瑞士','CH',100102,1,'000000USER20120510183730','2013-02-28 18:03:44',NULL,NULL),(95,0,4,'比利时','BE',100128,1,'000000USER20120510183730','2013-02-28 18:03:59',NULL,NULL),(96,0,4,'阿尔巴尼亚','AL',100119,1,'000000USER20120510183730','2013-02-28 18:04:11',NULL,NULL),(97,0,4,'阿根廷','AR',100123,1,'000000USER20120510183730','2013-02-28 18:04:23',NULL,NULL),(98,0,4,'圭亚那','GY',100047,1,'000000USER20120510183730','2013-02-28 18:04:38',NULL,NULL),(99,0,4,'智利','CL',100138,1,'000000USER20120510183730','2013-02-28 18:04:55',NULL,NULL),(100,0,4,'乌拉圭','UY',100113,1,'000000USER20120510183730','2013-02-28 18:05:04',NULL,NULL),(101,0,4,'埃及','EG',100148,1,'000000USER20120510183730','2013-02-28 18:05:18',NULL,NULL),(102,0,4,'摩洛哥','MA',100186,1,'000000USER20120510183730','2013-02-28 18:05:28',NULL,NULL),(103,0,4,'阿尔及利亚','DZ',100002,1,'000000USER20120510183730','2013-02-28 18:05:39',NULL,NULL),(104,0,4,'莫桑比克','MZ',100074,1,'000000USER20120510183730','2013-02-28 18:05:49',NULL,NULL),(105,0,4,'安哥拉','AO',100121,1,'000000USER20120510183730','2013-02-28 18:05:58',NULL,NULL),(106,0,4,'阿曼','OM',100194,1,'000000USER20120510183730','2013-02-28 18:06:07',NULL,NULL),(107,0,4,'赞比亚','ZM',100118,1,'000000USER20120510183730','2013-02-28 18:06:46',NULL,NULL),(108,0,4,'克罗地亚','HR',100028,1,'000000USER20120510183730','2013-02-28 18:07:02',NULL,NULL),(109,0,4,'埃塞俄比亚','ET',100035,1,'000000USER20120510183730','2013-02-28 18:07:13',NULL,NULL),(110,0,4,'毛里求斯','MU',100182,1,'000000USER20120510183730','2013-02-28 18:07:22',NULL,NULL),(111,0,1,'泰国','TH',100218,1,'000000USER20120510183730','2013-03-12 13:32:17',NULL,NULL),(112,0,3,'马来西亚','MY',100179,1,'72','2013-04-10 09:50:22',NULL,NULL),(113,0,4,'意大利','IT',100053,1,'72','2013-06-03 10:11:53',NULL,NULL),(114,0,3,'沙特阿拉伯','SA',100206,1,'72','2013-08-29 10:23:52',NULL,NULL),(115,0,1,'厄瓜多尔','EC',100032,1,'72','2014-01-25 09:52:56',NULL,NULL),(116,0,4,'留尼汪','RE',100200,1,'72','2014-02-10 09:57:06',NULL,NULL),(117,0,4,'苏里南','SR',100100,1,'80','2014-07-14 15:09:46',NULL,NULL),(118,0,4,'库拉索','CW',300445,1,'1413962910835USERU961TTT','2014-12-09 15:45:45',NULL,NULL),(119,0,4,'马达加斯加','MG',100178,1,'1413962910835USERU961TTT','2014-12-22 15:12:32',NULL,NULL),(120,0,4,'斯洛伐克','SK',100209,1,'1413962910835USERU961TTT','2015-01-22 11:47:28',NULL,NULL),(121,0,1,'美属维尔京群岛','VI',300484,1,'80','2015-01-23 16:36:32',NULL,NULL);

UNLOCK TABLES;