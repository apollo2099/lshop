package com.lshop.manage.activity.service;

import static org.junit.Assert.*;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvLotteryLogs;
import com.lshop.manage.lvActivity.service.LvLotteryLogsService;

public class LvLotteryLogsServiceTest extends BaseSpringTestCase{
	
	@Resource 
	private LvLotteryLogsService lvLotteryLogsService;



	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
		dto.put("lvLotteryLogs", lvLotteryLogs);
		lvLotteryLogsService.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
		lvLotteryLogs.setId(3);
		dto.put("lvLotteryLogs", lvLotteryLogs);
		LvLotteryLogs temp=lvLotteryLogsService.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
		lvLotteryLogs.setId(1);
		dto.put("lvLotteryLogs", lvLotteryLogs);
		lvLotteryLogsService.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
		lvLotteryLogs.setActivityCode("60f3154f26dc431394eaa4d4a81e418c");
		lvLotteryLogs.setActivityName("开发tvapd抽奖JUNIT");
		lvLotteryLogs.setPrizeName("华扬优惠券（读写分离）JUNIT");
		lvLotteryLogs.setPrizeCode("47e8d78d05b844f19880a6db75d94e8e");
		lvLotteryLogs.setUserName("gvxieco");
		lvLotteryLogs.setUserCode("X7DV61H7I14029775045242107U3CRYS");
		lvLotteryLogs.setCreateTime(new Date());
		lvLotteryLogs.setIsSystemFlag(Short.parseShort("0"));
		lvLotteryLogs.setPrizeNum(1);
		lvLotteryLogs.setAccountPrizeCode("a4af1f51d32e44e98d7063515f20TEST");
		dto.put("lvLotteryLogs", lvLotteryLogs);
		LvLotteryLogs temp=lvLotteryLogsService.save(dto);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
		lvLotteryLogs.setId(3);
		dto.put("lvLotteryLogs", lvLotteryLogs);
		LvLotteryLogs temp=lvLotteryLogsService.get(dto);
		if(ObjectUtils.isNotEmpty(temp)){
			temp.setActivityName("Banana抽奖活动信息JUNIT");
			dto.put("lvLotteryLogs", temp);
			lvLotteryLogsService.update(dto);
		}
	}

}
