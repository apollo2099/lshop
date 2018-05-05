一 ,lshop.properties 配置说明

#多邮件发送配置
//邮件服务器地址
lshop.email.send.many.smtpHost=smtp.ym.163.com
//邮件帐号
lshop.email.send.many.userName=system@tvpad.hk,system1@tvpad.hk,system2@tvpad.hk,system3@tvpad.hk,system4@tvpad.hk,system5@tvpad.hk,system6@tvpad.hk,system7@tvpad.hk,system8@tvpad.hk,system9@tvpad.hk
//邮件密码
lshop.email.send.many.password=pwd.123
//发送源标题配置
lshop.email.mailfromname=\u83EF\u63DA\u570B\u969B\u5B98\u7F51\

#web 用于店铺模板文件生成目录
lshop.web.path=E\:\\workspace\\lshop_new\\WebRoot\\web

#mq 配置
jms.message.maxLoadNum = 100//最大jms 数
jms.brokerURL = tcp://10.0.1.219:61616//mq 地址
jms.destination.queueSender =Queue/bzOrderReceiverQueue//发堆栈名称
jms.destination.queueReceiver =Queue/bzOrderSenderQueue//接收堆栈名称

#jms Cod use   cod 平台
#jms.destination.queueSenderCod =dspSender_test//发堆栈名称
#jms.destination.queueReceiverCod =kbzusacod//接收堆栈名称

#domain.base.name share colike  网站二级域名条件约束
domain.base.name=.tvpad.com

#lshopcn博客订阅
lshop.lshopcn.url=http\://cn.tvpad.com/web/tvpadcn/ //前台域名

#用户订单统计数据同步标志
com.lshop.manage.state.lvStateUser.stateUserFlag=0

#sampleFlag 新建店铺初始化数据源样板店标志(中文和英文)
InitStoreDataService.sampleFlag.zh=tvpadvs
InitStoreDataService.sampleFlag.en=vsen

#免费使用模块申请邮件发送方
email.free=market@hmall.hk

#搜商品初始化数据的存放路径
search.product.path=temp\\search\\product
#搜店铺初始化数据的存放路径
search.store.path=temp\\search\\store

#商城域名配置
lshop.domain.base.name=www.tvpad.com

二 ,jdbc.properties 配置说明
修改数据源 对于 的库, 用户名,密码

##jdbc.driver
jdbc.driverClassName=com.mysql.jdbc.Driver//驱动名称

#################################################################################################################################
##hibernate Hibernate配置信息
hibernate.dialect = org.hibernate.dialect.MySQLDialect//hql方言
hibernate.show_sql = true   //是否显示 sql语句
hibernate.format_sql = true //是否格式化sql语句
hibernate.generate_statistics = true
hibernate.use_sql_comments = true;
hibernate.cache.use_second_level_cache = true//是否启用二级缓存

#################################################################################################################################
##gvworkflow reader DB Server config  JDBC数据源配置信息
jdbc.url=jdbc:mysql://10.0.1.103:3306/gv_tvpad_web?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8//数据库连接地址
jdbc.username.des=9309167137a4c1c96194924d9eb110dc6732560d31ee149d//用户名
jdbc.password.des=f6f41b740274535eaf63b160c517b3816732560d31ee149d//密码
jdbc.maxPoolSize=5//最大连接数
jdbc.minPoolSize=1//最小连接数

注意事项：jdbc用户名和密码采用了DES两次加密。
系统初始化数据：系统配置信息，支付方式，邮件模板，店铺模板。


三 ,res 目录 保留 只允许直接覆盖 不允许删除
