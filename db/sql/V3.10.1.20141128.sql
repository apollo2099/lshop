
/*新增领券活动*/
ALTER TABLE `lv_activity_link` ADD COLUMN   `strategy_type` SMALLINT(3) DEFAULT NULL COMMENT '活动策略（1无限制，2登录限制，3IP限制)';

/*修复历史数据*/
UPDATE lv_activity_link SET strategy_type=2 WHERE strategy_type IS NULL;