package com.lshop.manage.activity.service;

import static org.junit.Assert.*;
import javax.annotation.Resource;
import org.junit.Test;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvLotteryPrize;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvActivity.service.LvLotteryPrizeService;

public class LvLotteryPrizeServiceTest extends BaseSpringTestCase{
	@Resource 
	private LvLotteryPrizeService lvLotteryPrizeService;

	@Test
	public void testFindAll() {
		BaseDto dto=new BaseDto();
		lvLotteryPrizeService.findAll(dto);
	}

	@Test
	public void testFindPage() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
		dto.put("lvLotteryPrize", lvLotteryPrize);
		lvLotteryPrizeService.findPage(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
		lvLotteryPrize.setId(3);
		dto.put("lvLotteryPrize", lvLotteryPrize);
		LvLotteryPrize temp=lvLotteryPrizeService.get(dto);
		assertNotNull(temp);
	}

	@Test
	public void testDel() {
		BaseDto dto=new BaseDto();
		LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
		lvLotteryPrize.setId(24);
		dto.put("lvLotteryPrize", lvLotteryPrize);
		lvLotteryPrizeService.del(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
		lvLotteryPrize.setActivityCode("4270f6fd6ded46488e70fc3d899f93ed");
		lvLotteryPrize.setPrizeName("bananatv遥控器");
		lvLotteryPrize.setPrizeCode("");
		lvLotteryPrize.setPrizeType(Short.parseShort("2"));
		lvLotteryPrize.setPrizeImages("http://res.itvpad.com/upload/tvpadcn/res/images/product_1408518161930.png");
		lvLotteryPrize.setPrizeTotal(100);
		lvLotteryPrize.setGrantTotal(10);
		lvLotteryPrize.setIsDraw(Short.parseShort("1"));
		lvLotteryPrize.setCode(CodeUtils.getCode());
		dto.put("lvLotteryPrize", lvLotteryPrize);
		LvLotteryPrize temp=lvLotteryPrizeService.save(dto);
		assertNotNull(temp);
	}

	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvLotteryPrize lvLotteryPrize = new LvLotteryPrize();
		lvLotteryPrize.setId(4);
		dto.put("lvLotteryPrize", lvLotteryPrize);
		LvLotteryPrize temp=lvLotteryPrizeService.get(dto);
		if(ObjectUtils.isNotEmpty(temp)){
			temp.setPrizeName("步步高点读机JUNIT");
			dto.put("lvLotteryPrize", temp);
			lvLotteryPrizeService.update(dto);
		}
	}

}
