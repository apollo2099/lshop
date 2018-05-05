/*数据表结构变更*/
ALTER TABLE `lv_account_prize` ADD COLUMN mall_flag VARCHAR(32) COMMENT '商城体系标示';
ALTER TABLE `lv_coupon_product` ADD COLUMN mall_flag VARCHAR(32) COMMENT '商城体系标示';
ALTER TABLE `lv_activity_share` ADD COLUMN mall_flag VARCHAR(32) COMMENT '商城体系标示';


/*数据修改*/
UPDATE lv_coupon_product SET mall_flag='tvpad' WHERE store_id IN('tvpadcn','tvpaden','mtscn');
UPDATE lv_coupon_product SET mall_flag='banana' WHERE store_id IN('bscn','bsen','mbscn');
