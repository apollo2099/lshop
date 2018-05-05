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
create index index_openid on wx_integral
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


