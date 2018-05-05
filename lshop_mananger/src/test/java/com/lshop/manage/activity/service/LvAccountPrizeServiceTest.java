package com.lshop.manage.activity.service;

import static org.junit.Assert.*;
import javax.annotation.Resource;
import org.junit.Test;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;
import com.lshop.common.pojo.logic.LvAccountPrize;
import com.lshop.manage.lvActivity.service.LvAccountPrizeService;

public class LvAccountPrizeServiceTest extends BaseSpringTestCase{

	@Resource
	private  LvAccountPrizeService lvAccountPrizeService;
	
	@Test 
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvAccountPrize lvAccountPrize = new LvAccountPrize();
		lvAccountPrize.setId(1);
		dto.put("model", lvAccountPrize);
		LvAccountPrize temp=lvAccountPrizeService.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testFindByCode() {
		BaseDto dto=new BaseDto();
		dto.put("accountPrizeCode", "480f7970e8b04d709797097a4ccd1111");
		LvAccountPrize temp=lvAccountPrizeService.findByCode(dto);
		assertNotNull(temp);
	}

	@Test
	public void testFindByAcountPrizeCode() {
		BaseDto dto=new BaseDto();
		dto.put("accountPrizeCode", "480f7970e8b04d709797097a4ccdb555");
		LvAccountPhysicalTicket temp=lvAccountPrizeService.findByAcountPrizeCode(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdateAccountPrizeStatus() {
		BaseDto dto=new BaseDto();
		LvAccountPhysicalTicket apt=new LvAccountPhysicalTicket();
		apt.setId(1);
		apt.setAccountPrizeCode("480f7970e8b04d709797097a4ccdb555");
		apt.setRelCode("40I1LC4NO1404282390785FN1K2G6VGU");
		apt.setRelName("liaoxj111");
		apt.setPostCode("518000");
		apt.setMobile("15874031191");
		apt.setContryId(100023);
		apt.setContryName("china");
		apt.setProvinceId(200005);
		apt.setProvinceName("Guangdong");
		apt.setCityName("shenzhen");
		apt.setAdress("futian");
		dto.put("lvAccountPhysicalTicket", apt);
		
		LvAccountPrize lvAccountPrize = new LvAccountPrize();
		lvAccountPrize.setCode("480f7970e8b04d709797097a4ccdb555");
		dto.put("lvAccountPrize", lvAccountPrize);
		lvAccountPrizeService.updateAccountPrizeStatus(dto);
	}

}
