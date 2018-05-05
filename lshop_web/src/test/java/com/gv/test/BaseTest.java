package com.gv.test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:conf/service/spring-*.xml"
})
@TransactionConfiguration(transactionManager="lvlogicReadTransactionManager") //可选，默认就是这个
@Transactional
public class BaseTest  extends AbstractTransactionalJUnit4SpringContextTests{
	
//	@BeforeClass
//	public void setup(){
//		DataSource lvlogicReadDao = (DataSource) applicationContext.getBean("lvlogicReadDao");
//		this.simpleJdbcTemplate = new SimpleJdbcTemplate(lvlogicReadDao);
//	}
	
	@Override
	@Resource
	public void setDataSource(DataSource lvlogicReaddataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(lvlogicReaddataSource);
	}
}
