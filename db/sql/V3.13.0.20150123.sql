

USE `gv_tvpad_web`;
/**************************************************************************
 * 赠品发货功能
 **************************************************************************/
/*Table structure for table `lv_activity_appoint_product` */

DROP TABLE IF EXISTS `lv_activity_appoint_product`;

CREATE TABLE `lv_activity_appoint_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `is_use_coupon` smallint(3) DEFAULT '1' COMMENT '是否使用优惠券(1是，0否)',
  `store_id` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `given_type_name` varchar(128) DEFAULT NULL,
  `given_product_code` varchar(32) DEFAULT NULL COMMENT '赠送物品关联',
  `grant_total` int(11) DEFAULT NULL COMMENT '赠送物品已发放总数',
  `occupied_total` int(11) DEFAULT NULL COMMENT '赠送物品占用总数',
  `uncollected_total` int(11) DEFAULT NULL COMMENT '赠送物品未领取总数',
  `given_type_num` smallint(3) DEFAULT NULL COMMENT '赠送类型字典(1赠送优惠券,2赠送抽奖机会,3赠送赠品)',
  `lottery_total` int(11) DEFAULT NULL COMMENT '抽奖机会次数',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lv_activity_appoint_product` */

LOCK TABLES `lv_activity_appoint_product` WRITE;

UNLOCK TABLES;

/*Table structure for table `lv_activity_gift` */

DROP TABLE IF EXISTS `lv_activity_gift`;

CREATE TABLE `lv_activity_gift` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `gift_code` varchar(32) DEFAULT NULL COMMENT '赠品code',
  `gift_name` varchar(32) DEFAULT NULL COMMENT '赠品名称',
  `gift_every_num` int(11) DEFAULT NULL COMMENT '每次赠送数量',
  `gift_total_num` int(11) DEFAULT NULL COMMENT '赠品总量',
  `gift_have_num` int(11) DEFAULT NULL COMMENT '已赠送数量',
  `gift_serplus_num` int(11) DEFAULT NULL COMMENT '剩余赠品数量',
  `order_value` int(11) DEFAULT NULL COMMENT '排序值',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动和赠品的关系表';

/*Data for the table `lv_activity_gift` */

LOCK TABLES `lv_activity_gift` WRITE;

UNLOCK TABLES;

/*Table structure for table `lv_activity_product` */

DROP TABLE IF EXISTS `lv_activity_product`;

CREATE TABLE `lv_activity_product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_code` varchar(32) DEFAULT NULL COMMENT '活动code',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品code',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `currency` varchar(3) DEFAULT NULL COMMENT '币种',
  `price` double(11,2) DEFAULT NULL COMMENT '单价',
  `store_id` varchar(32) DEFAULT NULL COMMENT '店铺标识',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动和商品的关系表';

/*Data for the table `lv_activity_product` */

LOCK TABLES `lv_activity_product` WRITE;

UNLOCK TABLES;

/*Table structure for table `lv_product_gift` */

DROP TABLE IF EXISTS `lv_product_gift`;

CREATE TABLE `lv_product_gift` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品code',
  `gift_code` varchar(32) DEFAULT NULL COMMENT '赠品code',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和赠品关系表';

/*Data for the table `lv_product_gift` */

LOCK TABLES `lv_product_gift` WRITE;

UNLOCK TABLES;

/*Table structure for table `lv_pub_gift` */

DROP TABLE IF EXISTS `lv_pub_gift`;

CREATE TABLE `lv_pub_gift` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `gift_name` varchar(32) DEFAULT NULL COMMENT '赠品中文名称',
  `gift_name_en` varchar(32) DEFAULT NULL COMMENT '赠品英文名称',
  `pcode` varchar(32) DEFAULT NULL COMMENT '启创对接code',
  `status` smallint(3) DEFAULT NULL COMMENT '状态(1启用，0停用，-1删除)',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赠品信息';

/*Data for the table `lv_pub_gift` */

LOCK TABLES `lv_pub_gift` WRITE;

UNLOCK TABLES;



/*Table structure for table `lv_order_gift` */

DROP TABLE IF EXISTS `lv_order_gift`;

CREATE TABLE `lv_order_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品code',
  `gift_code` varchar(32) DEFAULT NULL COMMENT '赠品code',
  `gift_num` int(11) DEFAULT NULL COMMENT '赠品数量',
  `pcode` varchar(32) DEFAULT NULL COMMENT 'SAS对接code',
  `is_delete` smallint(3) DEFAULT NULL COMMENT '赠品详情状态(0=>不删除,-1=>删除)',
  `store_id` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `code` varchar(32) DEFAULT NULL COMMENT 'code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人编号',
  `modify_user_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单赠品详情信息';

/*Data for the table `lv_order_gift` */

LOCK TABLES `lv_order_gift` WRITE;

UNLOCK TABLES;


/*修改表字段*/
ALTER TABLE lv_order ADD COLUMN is_gift  smallint(3) NULL COMMENT '是否存在赠品（1是，0否）' AFTER is_sync_sas;
ALTER TABLE lv_shop_product ADD COLUMN is_gift  smallint(3) NULL COMMENT '是否存在赠品（1是，0否）' AFTER order_value;
ALTER TABLE lv_blog_content ADD COLUMN is_recommend  SMALLINT(3) NULL COMMENT '是否首页推荐（1是，0否）' AFTER is_red;
ALTER TABLE lv_manager_pay_log MODIFY COLUMN uname  VARCHAR(32) NULL COMMENT '操作对象';


/*修改部分数据表类型*/
alter table lv_dealer_application type = InnoDB;
alter table lv_developer type = InnoDB;
alter table lv_developer_app type = InnoDB;
alter table lv_developer_templet type = InnoDB;
alter table lv_ip_country type = InnoDB;
alter table lv_mapp_cards_tmp type = InnoDB;
alter table lv_mapp_cards_user type = InnoDB;
alter table lv_operation_logs type = InnoDB;
alter table lv_recharge_paylog type = InnoDB;
alter table lv_service_network type = InnoDB;
alter table lv_sys_config type = InnoDB;
alter table lv_user_th type = InnoDB;
alter table lv_vb_plans type = InnoDB;


/*活动部分表结构添加索引*/
ALTER TABLE `lv_activity_appoint_product` ADD INDEX INX_ACTIVITY_APPOINT_CODE(`activity_code`);
ALTER TABLE `lv_activity_gift` ADD INDEX INX_ACTIVITY_GIFT_AC_CODE(`activity_code`);
ALTER TABLE `lv_activity_gift` ADD INDEX INX_ACTIVITY_GIFT_CODE(`gift_code`);
ALTER TABLE `lv_activity_product` ADD INDEX INX_ACTIVITY_PRODUCT_AC_CODE(`activity_code`);
ALTER TABLE `lv_activity_product` ADD INDEX INX_ACTIVITY_PRODUCT_CODE(`product_code`);


/*更新数据*/
update lv_order set is_gift=0;
update lv_blog_content set is_recommend=0;


/**************************************************************************
 * 推广优惠券功能改造
 **************************************************************************/
ALTER TABLE lv_coupon_type  ADD reuse INT(6) NOT NULL COMMENT '重复使用（1是，0否）';
UPDATE lv_coupon_type SET reuse=0;

CREATE TABLE `lv_coupon_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `coupon_code` varchar(32) DEFAULT NULL COMMENT '优惠券业务编码',
  `apply` varchar(32) DEFAULT NULL COMMENT '使用人',
  `apply_name` varchar(32) DEFAULT NULL COMMENT '使用人名称',
  `apply_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_id` varchar(32) DEFAULT NULL COMMENT '使用订单号',
  PRIMARY KEY (`id`),
  KEY `index_0` (`code`),
  KEY `index_1` (`coupon_code`),
  KEY `index_2` (`apply`),
  KEY `index_3` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='优惠券使用信息表';

CREATE TABLE `lv_coupon_obtain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `coupon_code` varchar(32) DEFAULT NULL COMMENT '优惠券业务编码',
  `obtain` varchar(32) DEFAULT NULL COMMENT '获取人code',
  `obtain_name` varchar(32) DEFAULT NULL COMMENT '获取人名称',
  `obtain_time` datetime DEFAULT NULL COMMENT '获取时间',
  PRIMARY KEY (`id`),
  KEY `index_0` (`code`),
  KEY `index_1` (`coupon_code`),
  KEY `index_2` (`obtain`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='优惠券获取信息表';



INSERT INTO lv_coupon_obtain(code,coupon_code,obtain,obtain_name,obtain_time) SELECT replace(UUID(), '-', ''),o.code,o.obtain,o.obtain_name,o.obtain_time FROM lv_coupon o WHERE o.obtain IS NOT NULL;

INSERT INTO lv_coupon_apply(code,coupon_code,apply,apply_name,apply_time,order_id) SELECT replace(UUID(), '-', ''),o.code,o.apply,o.apply_name,o.apply_time,o.order_id FROM lv_coupon o WHERE o.apply IS NOT NULL;




/****************************************************************/
/************** 微信公众平台 ************************************/
/*==============================================================*/
/* Table: wx_gzh_config微信公众号信息配置                       */
/*==============================================================*/
drop table if exists wx_gzh_config;
create table wx_gzh_config
(
   id                   int(11) not null auto_increment,
   wxh_name             varchar(32) not null comment '微信公众号名称',
   pre_url              varchar(255) not null comment 'URL前缀',
   token                varchar(32) not null comment 'token',
   encoding_aes_key     varchar(43) not null comment '消息加密密钥',
   app_id               varchar(32) not null comment '应用ID',
   app_secret           varchar(128) not null comment '应用密钥',
   access_token         varchar(512) comment 'access_token凭证',
   access_token_expires int comment '凭证失效时间，单位：秒',
   access_token_time    datetime comment '凭证获取时间',
   code                 varchar(32) comment 'code，第3方对接时使用，内部系统关联还是使用id',
   create_time          datetime comment '创建时间',
   modify_time          datetime comment '修改时间',
   modify_user_id       int(11) comment '修改人编号',
   modify_user_name     varchar(32) comment '修改人名称',
   primary key (id)
);

/*==============================================================*/
/* Table: wx_obtain_news_tpl领取图文消息模版                    */
/*==============================================================*/
drop table if exists wx_obtain_news_tpl;
create table wx_obtain_news_tpl
(
   id                   int(11) not null auto_increment,
   push_keyword         varchar(32) not null comment '关键词，用户发送关键词时推送活动图文消息',
   query_keyword        varchar(32) comment '查询领取金额关键词',
   begin_time           datetime comment '活动开始时间',
   end_time             datetime comment '活动结束时间',
   news_id              int comment '关联图文素材表id ',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   modify_user_id       int(11) comment '修改人编号',
   modify_user_name     varchar(32) comment '修改人名称',
   primary key (id)
);

/*==============================================================*/
/* Table: wx_user用户列表                                       */
/*==============================================================*/
drop table if exists wx_user;
create table wx_user
(
   id                   int(11) not null auto_increment,
   openid               varchar(64) not null comment '用户的标识，对当前公众号唯一',
   nickname             varchar(64) not null comment '用户的昵称',
   email                varchar(128) comment 'tvpad用户email',
   bind_time            datetime comment '绑定时间',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);
create unique index index_openid on wx_user
(
   openid
);

/*==============================================================*/
/* Table: wx_obtain领取详情                                     */
/*==============================================================*/
drop table if exists wx_obtain;
create table wx_obtain
(
   id                   int(11) not null auto_increment,
   openid               varchar(64) not null comment '用户的标识，对当前公众号唯一',
   nickname             varchar(64) not null comment '用户的昵称',
   obtain_type          int not null default 1 comment '领取类型：1-自己领取，2-好友支持',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   ip_address           varchar(15) comment 'ip地址',
   primary key (id)
);
create index index_openid on wx_obtain
(
   openid
);

/*==============================================================*/
/* Table: wx_menu菜单管理                                       */
/*==============================================================*/
drop table if exists wx_menu;
create table wx_menu
(
   id                   int(11) not null auto_increment,
   name                 varchar(16) not null comment '菜单名称',
   menu_type            varchar(16) not null default 'click' comment '菜单类型：click-单击菜单后推送消息,view-URL链接',
   menu_key             varchar(64) not null comment '菜单的对外标识key',
   menu_url             varchar(256) comment 'url地址',
   material_type        int not null default 1 comment '素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文',
   material_id          int comment '对应素材的id值',
   menu_parent          int not null default 0 comment '父菜单的id',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   primary key (id)
);

/*==============================================================*/
/* Table: wx_passive_reply被动自动回复                          */
/*==============================================================*/
drop table if exists wx_passive_reply;
create table wx_passive_reply
(
   id                   int(11) not null auto_increment,
   description          varchar(64) not null comment '描述',
   material_type        int not null default 1 comment '素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文',
   material_id          int comment '对应素材的id值',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);


/*==============================================================*/
/* Table: wx_keywords_reply关键词自动回复                       */
/*==============================================================*/
drop table if exists wx_keywords_reply;
create table wx_keywords_reply
(
   id                   int(11) not null auto_increment,
   name                 varchar(60) not null comment '规则名称',
   keywords             varchar(154) comment '多个关键词使用英文逗号隔开',
   material_type        int not null default 1 comment '素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文',
   material_id          int comment '对应素材的id值',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);


/*==============================================================*/
/* Table: wx_text_material文本素材                              */
/*==============================================================*/
drop table if exists wx_text_material;
create table wx_text_material
(
   id                   int(11) not null auto_increment,
   name                 varchar(60) not null comment '素材名称',
   content              varchar(512) comment '消息内容',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: wx_news_material图文素材                              */
/*==============================================================*/
drop table if exists wx_news_material;
create table wx_news_material
(
   id                   int(11) not null auto_increment,
   name                 varchar(60) not null comment '素材名称',
   news_type            int not null default 1 comment '图文消息类型：1-单图文；2-多图文；',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);


/*==============================================================*/
/* Table: wx_news_material_item图文素材数据项                   */
/*==============================================================*/
drop table if exists wx_news_material_item;
create table wx_news_material_item
(
   id                   int(11) not null auto_increment,
   news_type            int not null default 1 comment '图文消息类型：1-单图文；2-多图文；',
   news_id              int not null comment '关联图文素材id',
   title                varchar(64) not null comment '标题',
   description          varchar(512) not null comment '描述',
   url                  varchar(256) not null comment '点击后跳转的链接',
   picurl               varchar(256) not null comment '图文消息的图片链接',
   wxh_config_id        int not null comment '公众号配置id',
   create_time          datetime not null comment '创建时间',
   modify_time          datetime comment '修改时间',
   primary key (id)
);

/* 修改数据默认引擎 */
alter table wx_gzh_config engine=innodb;
alter table wx_keywords_reply engine=innodb;
alter table wx_menu engine=innodb;
alter table wx_news_material engine=innodb;
alter table wx_news_material_item engine=innodb;
alter table wx_obtain engine=innodb;
alter table wx_obtain_news_tpl engine=innodb;
alter table wx_passive_reply engine=innodb;
alter table wx_text_material engine=innodb;
alter table wx_user engine=innodb;
/* 用户表添加字段 */
ALTER TABLE wx_user ADD COLUMN obtain_amount int not null default 0 comment '领取总金额';
ALTER TABLE `wx_menu`   
  CHANGE `material_type` `material_type` INT(11) DEFAULT 1  NULL  COMMENT '素材类型：1-文本，2-图片，3-语音，4-视频，5-音乐，6-图文';
ALTER TABLE wx_gzh_config ADD COLUMN store_id             varchar(32) comment '店铺编号';
create unique index unique_storeId on wx_gzh_config
(
   store_id
);
create unique index unique_wxhConfigId on wx_obtain_news_tpl
(
   wxh_config_id
);
create unique index unique_wxhConfigId on wx_passive_reply
(
   wxh_config_id
);



/*新增脚本20150202*/
alter table wx_gzh_config modify column encoding_aes_key     varchar(43) comment '消息加密密钥';
create unique index unique_appId on wx_gzh_config
(
   app_id
);
/*领取详情表修改*/
alter table wx_obtain add column friend_openid varchar(64) comment '好友用户标识';
create index unique_friend_openid on wx_obtain
(
   friend_openid
);
/*用户表*/
alter table wx_user add column last_obtain_time datetime comment '最后领取时间';

INSERT INTO `lv_task_quartz`(`task_name`,`target_object`,`target_method`,`target_time`,`description`,`status`,`create_time`,`modify_time`,`modify_user_id`,`modify_user_name`) VALUES ( 'initWxUserQuartz','WxUserQuartz','init','0 0 2 * * ?','自动获取微信公众号的用户列表（每天凌晨两点）','1',NULL,NULL,NULL,NULL);
INSERT INTO `lv_task_quartz`(`task_name`, `target_object`, `target_method`, `target_time`, `description`, `status`, `create_time`, `modify_time`, `modify_user_id`, `modify_user_name`) values('initOrderFromCreatentTaskTrigger','OrderFromCreatentService','orderFromCreatentTask','0 45 11 * * ?','定时同步启创物流信息','1','2015-01-29 11:41:28',NULL,NULL,NULL);



/*新增脚本20150205*/
ALTER TABLE lv_user_th MODIFY COLUMN th_type INT(3) COMMENT '三方类型(0=微信,1=qq,2=微博，3=facebook,4=twitter)';
ALTER TABLE wx_menu ADD COLUMN order_value INT(11) NOT NULL DEFAULT 0 COMMENT '排序值';



/*新增脚本20150207*/
ALTER TABLE lv_activity ADD COLUMN activity_title_en VARCHAR(128) NULL COMMENT '活动标题(EN)' AFTER activity_title;






