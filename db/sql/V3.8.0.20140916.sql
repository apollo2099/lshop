/*数据表结构变更*/
ALTER TABLE `lv_activity_limit_order` ADD COLUMN `lottery_total`  int(11) NULL COMMENT '抽奖机会次数';
ALTER TABLE `lv_activity_limit_order` modify column given_type_name varchar(128) COMMENT '赠送物品名称';


/*Table structure for table `lv_account_lottery` */
DROP TABLE IF EXISTS `lv_account_lottery`;
CREATE TABLE `lv_account_lottery` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `user_code` varchar(32) DEFAULT NULL COMMENT '用户code',
  `lottery_count` int(11) DEFAULT NULL COMMENT '抽奖剩余次数',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户抽奖机会';

/*Table structure for table `lv_account_physical_ticket` */
DROP TABLE IF EXISTS `lv_account_physical_ticket`;
CREATE TABLE `lv_account_physical_ticket` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `account_prize_code` varchar(32) DEFAULT NULL COMMENT '用户奖品关联code',
  `rel_code` varchar(32) DEFAULT NULL COMMENT '联系人编号',
  `rel_name` varchar(64) DEFAULT NULL COMMENT '联系人姓名',
  `post_code` varchar(64) DEFAULT NULL COMMENT '邮编',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机',
  `tel` varchar(64) DEFAULT NULL COMMENT '电话',
  `contry_id` int(11) DEFAULT NULL COMMENT '国家id',
  `contry_name` varchar(64) DEFAULT NULL COMMENT '国家名称',
  `province_id` int(11) DEFAULT NULL COMMENT '省id',
  `province_name` varchar(64) DEFAULT NULL COMMENT '省名称',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `city_name` varchar(64) DEFAULT NULL COMMENT '城市名字',
  `adress` varchar(128) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户实物兑奖';

/*Table structure for table `lv_account_prize` */
DROP TABLE IF EXISTS `lv_account_prize`;
CREATE TABLE `lv_account_prize` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(32) DEFAULT NULL COMMENT '用户code',
  `activity_code` varchar(32) DEFAULT NULL COMMENT '抽奖活动code',
  `prize_name` varchar(64) DEFAULT NULL COMMENT '抽奖物品名称',
  `prize_rel_code` varchar(32) DEFAULT NULL COMMENT '抽奖物品关联code',
  `prize_code` varchar(32) DEFAULT NULL COMMENT '抽奖物品code(备注说明：类型为优惠券是对于优惠码code，类型为实物时为空)',
  `prize_type` smallint(3) DEFAULT NULL COMMENT '抽奖物品类型(1.实物，2.优惠券)',
  `win_date` datetime DEFAULT NULL COMMENT '中奖日期',
  `end_ticket_date` date DEFAULT NULL COMMENT '截止兑奖日期',
  `ticket_date` datetime DEFAULT NULL COMMENT '兑奖日期',
  `status` smallint(3) DEFAULT NULL COMMENT '状态(0未兑奖，1已兑奖未发货，2已兑奖已发货)',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户奖品表';

/*Table structure for table `lv_activity_lottery` */
DROP TABLE IF EXISTS `lv_activity_lottery`;
CREATE TABLE `lv_activity_lottery` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `activity_rule` text DEFAULT NULL COMMENT '活动规则',
  `end_ticket_date` datetime DEFAULT NULL COMMENT '兑奖截止日期',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `store_id` varchar(32) DEFAULT NULL COMMENT '店铺标示',
  `activity_url` varchar(128) DEFAULT NULL COMMENT '活动链接',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='抽奖活动信息';

/*Table structure for table `lv_activity_register` */
DROP TABLE IF EXISTS `lv_activity_register`;
CREATE TABLE `lv_activity_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `given_type_name` varchar(128) DEFAULT NULL COMMENT '赠送类型名称',
  `given_product_code` varchar(32) DEFAULT NULL COMMENT '赠送物品关联',
  `grant_total` int(11) DEFAULT NULL COMMENT '赠送物品已发放总数',
  `uncollected_total` int(11) DEFAULT NULL COMMENT '赠送物品未领取总数',
  `given_type_num` smallint(3) DEFAULT NULL COMMENT '赠送类型字典(1赠送优惠券,2赠送抽奖机会)',
  `lottery_total` int(11) DEFAULT NULL COMMENT '抽奖机会次数',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='注册就送活动信息';


/*Table structure for table `lv_activity_share` */
DROP TABLE IF EXISTS `lv_activity_share`;
CREATE TABLE `lv_activity_share` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `given_type_name` varchar(128) DEFAULT NULL COMMENT '赠送类型名称',
  `given_product_code` varchar(32) DEFAULT NULL COMMENT '赠送物品关联',
  `grant_total` int(11) DEFAULT NULL COMMENT '赠送物品已发放总数',
  `uncollected_total` int(11) DEFAULT NULL COMMENT '赠送物品未领取总数',
  `given_type_num` smallint(3) DEFAULT NULL COMMENT '赠送类型字典(1赠送优惠券,2赠送抽奖机会)',
  `lottery_total` int(11) DEFAULT NULL COMMENT '抽奖机会次数',
  `partake_num` int(11) DEFAULT NULL COMMENT '每日参与活动次数',
  `share_image` varchar(128) DEFAULT NULL COMMENT '微博分享图片',
  `share_desc` varchar(320) DEFAULT NULL COMMENT '微博分享描述',
  `share_link` varchar(128) DEFAULT NULL COMMENT '微信分享连接',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='分享就送活动信息';

/*Table structure for table `lv_lottery_logs` */
DROP TABLE IF EXISTS `lv_lottery_logs`;
CREATE TABLE `lv_lottery_logs` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '抽奖活动code',
  `activity_name` varchar(64) DEFAULT NULL COMMENT '抽奖活动名称',
  `prize_name` varchar(64) DEFAULT NULL COMMENT '中奖物品名称',
  `prize_code` varchar(32) DEFAULT NULL COMMENT '中奖物品code',
  `user_name` varchar(64) DEFAULT NULL COMMENT '中奖人名称',
  `user_code` varchar(32) DEFAULT NULL COMMENT '中奖人code',
  `create_time` datetime DEFAULT NULL COMMENT '中奖时间',
  `is_system_flag` smallint(3) DEFAULT NULL COMMENT '是否虚拟中奖记录(1是，0否)',
  `prize_num` int(11) DEFAULT NULL COMMENT '中奖数目',
  `account_prize_code` varchar(32) DEFAULT NULL COMMENT '用户奖品code',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='抽奖中奖记录';

/*Table structure for table `lv_lottery_prize` */
DROP TABLE IF EXISTS `lv_lottery_prize`;
CREATE TABLE `lv_lottery_prize` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '抽奖活动code',
  `prize_name` varchar(64) DEFAULT NULL COMMENT '奖项物品名称',
  `prize_code` varchar(32) DEFAULT NULL COMMENT '奖项物品code',
  `prize_type` smallint(3) DEFAULT NULL COMMENT '奖项物品类型(1.优惠券2.实物，)',
  `prize_images` varchar(128) DEFAULT NULL COMMENT '奖项图片',
  `prize_total` int(11) DEFAULT '0' COMMENT '奖项数量',
  `grant_total` int(11) DEFAULT '0' COMMENT '已兑奖数量',
  `is_draw` smallint(3) DEFAULT NULL COMMENT '是否可抽中中奖的奖项(1是，0否)',
  `sort_id` smallint(6) DEFAULT NULL COMMENT '排序值',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='抽奖奖项物品';

