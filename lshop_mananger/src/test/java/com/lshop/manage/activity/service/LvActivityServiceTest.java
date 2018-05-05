package com.lshop.manage.activity.service;

import static org.junit.Assert.*;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.manage.lvActivity.service.LvActivityService;

public class LvActivityServiceTest extends BaseSpringTestCase {
	
	@Resource
	private LvActivityService lvActivityService;

	@Test
	public void testFindAllActivity() {
		BaseDto dto=new BaseDto();
		dto.put("typeKey", 5);
	    lvActivityService.findAllActivity(dto);
	}

	@Test
	public void testFindAllActivityLimitOrder() {
		BaseDto dto=new BaseDto();
		dto.put("givenProductCode", "c2d5ff0063d14ff0bff2c188f9797934");
		lvActivityService.findAllActivityLimitOrder(dto);
	}

	@Test
	public void testFindAllActivityLink() {
		BaseDto dto=new BaseDto();
		dto.put("givenProductCode", "759dfa6b4b79495da1b02f49ee1ff437");
		lvActivityService.findAllActivityLink(dto);
	}

	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvActivity lvActivity = new LvActivity();
		dto.put("lvActivity", lvActivity);
		lvActivityService.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvActivity lvActivity = new LvActivity();
		lvActivity.setId(1);
		dto.put("lvActivity", lvActivity);
		lvActivityService.get(dto);
	}

	@Test
	public void testFindByCode() {
		BaseDto dto=new BaseDto();
		dto.put("code", "598222ca030942669480c6d1dc3657f7");
		lvActivityService.findByCode(dto);
	}

	@Test
	public void testDel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindLimtTimeByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","a1988ad687ed4a15aa8f38957d5bb781");
		lvActivityService.findLimtTimeByCode(dto);
	}

	@Test
	public void testFindLimtNumByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","8b51cad9027e41599d3eafe2147155ab");
		lvActivityService.findLimtNumByCode(dto);
	}

	@Test
	public void testFindLimtOrderByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","12dd161c28db4e39a05626cd3ed7a079");
		lvActivityService.findLimtOrderByCode(dto);
	}

	@Test
	public void testFindLinkByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","8d61be4a75bd431eadde70ddffc47698");
		lvActivityService.findLinkByCode(dto);
	}

	@Test
	public void testFindACRegisterByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","9fa33effd582437499ca22eb534a6391");
		lvActivityService.findACRegisterByCode(dto);
	}

	@Test
	public void testFindACShareByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","a1988ad687ed4a15aa8f38957d5bb781");
		lvActivityService.findACShareByCode(dto);
	}

	@Test
	public void testFindACLotteryByCode() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode","0ceeee45f741400ab21b34959f780cb9");
		lvActivityService.findLimtTimeByCode(dto);
	}

	@Test
	public void testIsExistActivityLimitTime() {
		BaseDto dto=new BaseDto();
		dto.put("productCode","9cf67de6449c4972a1a2402f711f3279");
		Boolean isFlag=lvActivityService.isExistActivityLimitTime(dto);
		assertTrue(isFlag);
	}

	@Test
	public void testIsExistActivityLimitNum() {
		BaseDto dto=new BaseDto();
		dto.put("productCode","0020ee6f7c164fa58e48e8f154c3f740");
		Boolean isFlag=lvActivityService.isExistActivityLimitNum(dto);
		assertTrue(isFlag);
	}

	@Test
	public void testIsEffectActivityLimitOrder() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode", "12dd161c28db4e39a05626cd3ed7a079");
		dto.put("givenProductCode","c2d5ff0063d14ff0bff2c188f9797934");
		Boolean isFlag=lvActivityService.isEffectActivityLimitOrder(dto);
		assertTrue(isFlag);
	}

	@Test
	public void testIsEffectActivityLink() {
		BaseDto dto=new BaseDto();
		dto.put("activityCode", "e84f0331be2a44088181dbb6fd0d6b40");
		dto.put("givenProductCode","096b230670e647cebcb0487c75770264");
		Boolean isFlag=lvActivityService.isEffectActivityLimitOrder(dto);
		assertTrue(isFlag);
	}


	@Test
	public void testSendJSONToWeb() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testupdateActvityGivenName(){
		lvActivityService.updateActvityGivenName("LvActivityRegister", (short)2, "993bcb2e89ff4ec98d2d8e74b4b84448", "抽奖赠送活动222");
	}

}
