/*订单字端新增*/
ALTER TABLE `lv_order` ADD COLUMN `is_sync_sas`  SMALLINT(3) NULL COMMENT '是否同步启创标志' AFTER `is_balance_first`;

/*订单评论新增*/
ALTER TABLE `lv_order_comment` ADD COLUMN `comment_images`  VARCHAR(128) NULL COMMENT '评论图片';
ALTER TABLE `lv_product_comment` ADD COLUMN `comment_images`  VARCHAR(128) NULL COMMENT '评论图片';


/*线上数据修复*/
UPDATE lv_order SET is_sync_sas=1 WHERE is_service_audit=1 AND is_finance_audit=1;
UPDATE lv_order SET is_sync_sas=0 WHERE is_service_audit<>1 OR is_finance_audit<>1;



/*优惠码数据表添加索引*/
ALTER TABLE `lv_coupon` ADD INDEX INX_COUPON_CODE(`coupon_code`);
ALTER TABLE `lv_coupon` ADD INDEX INX_COUPON_TYPE_CODE(`coupon_type_code`);
ALTER TABLE `lv_coupon` ADD INDEX INX_COUPON_GRANT_WAY(`grant_way`);


/*添加facebook初始化配置数据*/
insert  into `lv_sys_config`(`code`,`name`,`val`,`c_desc`,`type`,`status`,`create_time`,`update_time`,`modify_user_code`,`modify_user_name`) values
('5ae5f333930e4f33940aafd3dc888992','weibo.config','{\"client_ID\":\"101174919\",\"client_SERCRET\":\"8926989e6d34a9de3f645e7d8043a691\",\"redirect_URI\":\"http://www.itvpad.com/web/threeauth!weiboUrl.action\",\"baseURL\":\"https://api.weibo.com/2/\",\"accessTokenURL\":\"https://api.weibo.com/oauth2/access_token\",\"authorizeURL\":\"https://api.weibo.com/oauth2/authorize\",\"rmURL\":\"https\\://rm.api.weibo.com/2/\"}',NULL,1,1,NULL,NULL,NULL,NULL);