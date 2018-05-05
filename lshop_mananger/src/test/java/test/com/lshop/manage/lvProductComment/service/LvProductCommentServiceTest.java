package test.com.lshop.manage.lvProductComment.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvProductComment.service.LvProductCommentService;
import com.lshop.manage.lvProductLadder.service.LvProductLadderService;

public class LvProductCommentServiceTest {
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	
	@Test
	public void testGetList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.getList(dto);
	}

	@Test
	public void testGetReplyList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		
		LvProductComment lvProductComment=new LvProductComment();
        lvProductComment.setId(16);
		dto.put("lvProductComment", lvProductComment);
		
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.getReplyList(dto);
	}

	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvProductComment lvProductComment=new LvProductComment();
		lvProductComment.setOid("TEST0000000123");
		lvProductComment.setProductCode("CD1341308735749");
		lvProductComment.setContent("JUNIT TEST CASE");
		lvProductComment.setGrade(Short.parseShort("3"));
		lvProductComment.setScore(10);
		lvProductComment.setIsCheck(Short.parseShort("0"));
		lvProductComment.setIsDelete(Short.parseShort("0"));
		lvProductComment.setSortId(4);
		lvProductComment.setCode(CodeUtils.getCode());
		lvProductComment.setCreateTime(new Date());
		dto.put("lvProductComment", lvProductComment);
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.save(dto);
	}

	@Test
	public void testDelete() {
		BaseDto dto=new BaseDto();
	    dto.put("ids", "33");
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.delete(dto);
	}

	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvProductComment lvProductComment=new LvProductComment();
		lvProductComment.setId(11);
		dto.put("lvProductComment", lvProductComment);
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.get(dto);
	}

	@Test
	public void testUpdateAudit() {
		BaseDto dto=new BaseDto();
		dto.put("ids", "11,12");
		LvProductComment lvProductComment=new LvProductComment();
		lvProductComment.setModifyUserId(2);
		lvProductComment.setModifyUserName("admin@");
		lvProductComment.setModifyTime(new Date());
		dto.put("lvProductComment", lvProductComment);
		LvProductCommentService lv= (LvProductCommentService) factory.getBean("LvProductCommentService");
		lv.updateAudit(dto);
	}

}
