/*订单信息表-新增表字段*/
ALTER TABLE lv_order ADD COLUMN third_order_num  varchar(32) NULL COMMENT '第三方订单号' AFTER third_party_order;
ALTER TABLE lv_order ADD COLUMN third_order_source  smallint(3) NULL COMMENT '第三方订单来源(0=其他，1=亚马逊，2=淘宝，3=速卖通，4=ebuy，5=Banana TV)' AFTER third_order_num;

