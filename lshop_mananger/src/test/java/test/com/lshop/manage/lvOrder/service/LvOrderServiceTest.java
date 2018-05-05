package test.com.lshop.manage.lvOrder.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import test.com.lshop.manage.util.BeanFactoryUtil;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.util.Constants;
import com.lshop.common.util.OrderHelp;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.message.service.OrderMsgService;

public class LvOrderServiceTest {
	@Resource 
	private HibernateBaseDAO dao;
	
	private static BeanFactory factory=null;
	@BeforeClass
	public static void createBeanFactory(){
		new BeanFactoryUtil();
		factory=BeanFactoryUtil.createBeanFactory();
	}
	//分页查询订单列表
	@Test
	public void testGetList() {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.getList(dto);
	}
    //根据id查询订单详情
	@Test
	public void testGet() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setId(1);
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.get(dto);
	}
    //根据订单编号查询订单详情
	@Test
	public void testGetOrder() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("CD000000000001");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.getOrder(dto);
	}
    //订单软删除
	@Test
	public void testDelete() throws ServiceException, Exception {
		BaseDto dto=new BaseDto();
		//LvOrder lvOrder=new LvOrder();
		dto.put("ids", "'CD000000000002','CD000000000003'");
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.delete(dto);
	}
    //软删除恢复
	@Test
	public void testReceiving() throws ServiceException, Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("CD000000000004");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.delete(dto);
	
	}

	//更新订单状态和订单总金额
	@Test
	public void testUpdate() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("CD000000000001");
		lvOrder.setStatus(Short.parseShort("1"));
		lvOrder.setTotalPrice(10000f);
		lvOrder.setModifyTime(new Date());
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.update(dto);
	}
    //订单审核
	@Test
	public void testUpdateAudit() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("CD000000000001");
		lvOrder.setIsServiceAudit(Short.parseShort("1"));
		lvOrder.setServiceAuditContent("server test");
		lvOrder.setIsFinanceAudit(1);
		lvOrder.setFinanceAuditContent("finance test");
		lvOrder.setModifyTime(new Date());
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@a");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.updateAudit(dto);
	}
    //退货备注
	@Test
	public void testUpdateBreakRemark() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("B2012071917523185657");
		lvOrder.setBreakRemark("break successs");
		lvOrder.setStatus(Short.parseShort("7"));
		lvOrder.setModifyTime(new Date());
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
//		lv.updateBreakRemark(dto);
	}
    //发货备注
	@Test
	public void testUpdateSendRemark() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("B2012072010051996935");
		lvOrder.setSendRemark("send success");
		lvOrder.setModifyTime(new Date());
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
//		lv.updateSendRemark(dto);
	}
    //修改订单状态
	@Test
	public void testUpdateStatus() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("B2012071914554190612");
		lvOrder.setStatus(Short.parseShort("7"));
		lvOrder.setModifyTime(new Date());
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
//		lv.updateStatus(dto);
	}

	//商家自主下单测试
	@Test
	public void testSave() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		LvOrderAddress lvOrderAddress=new LvOrderAddress();
		String oid = OrderHelp.createOrderId("B");
    	//保存订单信息/订单地址信息/订单详情 信息
    	dto.put("oid", oid);
    	dto.put("overtime", new Date());
    	dto.put("lvOrderAddress", lvOrderAddress);
    	dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		try {
			lv.save(dto);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//系统批量评论
	@Test
	public void testSaveSysComment() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setModifyUserId(2);
		lvOrder.setModifyUserName("admin@");
		lvOrder.setModifyTime(new Date());
		dto.put("lvOrder", lvOrder);
		
		LvOrderComment lvOrderComment=new LvOrderComment();
		lvOrderComment.setContent("很好");
		lvOrderComment.setScore(10);
		lvOrderComment.setGrade(Short.parseShort("3"));
		dto.put("lvOrderComment", lvOrderComment);
		dto.put("ids", "B2012080617391491687,B2012072011225044775");
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.saveSysComment(dto);
	}

	@Test
	public void testDoOrder() {
		fail("Not yet implemented");
	}

	//导出订单信息
	@Test
	public void testExportOrder() {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		dto.put("ids", "'CD000000000001','CD000000000002','CD000000000003'");
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.exportOrder(dto);
	}

	//统计所有订单数目
	@Test
	public void testCountAll() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countAll(dto);
	}

	//统计退货订单数目
	@Test
	public void testCountBack() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countBack(dto);
	}
    //统计删除订单数目
	@Test
	public void testCountDelete() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countDelete(dto);
	}
    //统计完成订单数目
	@Test
	public void testCountFinish() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countFinish(dto);
	}
    //统计支付订单数目
	@Test
	public void testCountPay() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countPay(dto);
	}
    //统计未支付订单数目
	@Test
	public void testCountUnPay() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.countUnPay(dto);
	}
    //统计订单销售额
	@Test
	public void testTotalPrice() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.totalPrice(dto);
	}
    //导出订单统计数据
	@Test
	public void testExportStateOrder() throws Exception {
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.exportStateOrder(dto);
	}
    //导出产品销售统计数据
	@Test
	public void testExportStateProduct() throws Exception {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		dto.put("ids","CD1341308735749");
		
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.exportStateProduct(dto);
	}

	//产品销售统计
	@Test
	public void testStateProduct() throws Exception {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.stateProduct(dto);
	}

	//用户订单统计
	@Test
	public void testStateUser() throws Exception {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.stateUser(dto);
	}
    //导出用户订单统计数据
	@Test
	public void testExportUserOrder() throws Exception {
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		dto.put("lvOrder", lvOrder);
		dto.put("ids","'liaoxiongjian.2008@163.com'");
		
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lv.exportUserOrder(dto);
	}
	
	@Test
	public void testWuLiu()throws Exception{
		BaseDto dto=new BaseDto();
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid("W2012082316495383311");
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		lvOrder = (LvOrder) lv.getOrder(dto);
		dto.put("lvOrder", lvOrder);
	    lv.update(dto);
	}
	/**
	 * 	 * 1，接收商务订单发货数据
	 * oid 订单号
	 * expressCompany 物流公司key
	 * expressName 物流公司名称
	 * expressNum 物流单号
	 * status 订单发货状态
	 * @Method: testprocessOrderMSGToWMS 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-24 上午10:15:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-24 上午10:15:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws Exception 
	 * @return void
	 */
	@Test
	public void testprocessOrderMSGToWMS()throws Exception{
		BaseDto dto=new BaseDto();
		dto.put("expressName", "DHL");
		dto.put("expressNum", "TST0000001");
		dto.put("stauts", 1);
		dto.put("oid", "B2012082310062975074");
		OrderMsgService lv= (OrderMsgService) factory.getBean("OrderMsgService");
		lv.processOrderMSGToWMS(dto);
	}
	
	@Test
	public void testShopTypeState()throws Exception{
		BaseDto dto=new BaseDto();
		Pagination page=new Pagination();
		page.setPageNum(1);
		page.setNumPerPage(10);
		dto.put("page",page);
		LvOrder lvOrder=new LvOrder();
		
		dto.put("lvOrder", lvOrder);
		LvOrderService lv= (LvOrderService) factory.getBean("LvOrderService");
		Pagination pageTmp= lv.shopTypeState(dto);
		assertNotNull(pageTmp.getList());
	}
	
	@Test
	public void testExportShopTypeState()throws Exception{
		
	}
	
	@Test
	public void testDoShopNotice()throws Exception{
		
	}
	
	@Test
	public void testVaildCoupon()throws Exception{
		
	}
	
}
