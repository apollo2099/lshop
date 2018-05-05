package com.lshop.web.order.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.exception.DAOException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.web.order.service.OrderDataTestService;

/**
 * 订单数据监控
 * @author zhengxue
 * @since 1.0 2013-05-14
 *
 */
@Service("OrderDataTestService")
public class OrderDataTestServiceImpl implements OrderDataTestService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	
	@Resource
	private HibernateBaseDAO lvuserWriteDao;
	
	/**
	 * LvAccountAddress
	 * @return
	 */
	//LvAccountAddress测试查询数据
	public Boolean  searchLvAccountAddress(){
		try {
			lvuserReadDao.countQueryResult(Finder.create("from LvAccountAddress"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvAccountAddress测试保存数据
	public Boolean  saveLvAccountAddress(){

		LvAccountAddress address = new LvAccountAddress();
		address.setRelCode("test1");
		address.setRelName("test");
		address.setPostCode("1111111");
		address.setMobile("13111111111");
		address.setTel("010-1234567");
		address.setContryId(100023);
		address.setContryName("China");
		address.setProvinceId(200005);
		address.setProvinceName("Guangdong");
		address.setCityName("guangzhou");
		address.setAdress("tian he qu 1 hao");
		address.setStoreId("www");
		address.setCode("test0");
		address.setIsDefault((short)0);
		address.setCreateTime(new Date());
		
		try {
			lvuserWriteDao.save(address);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvAccountAddress测试修改数据
	public Boolean  updateLvAccountAddress(){
		try {
			lvuserWriteDao.update("update LvAccountAddress set relName='testUser' where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvAccountAddress测试删除数据
	public Boolean  deleteLvAccountAddress(){
		try {
			lvuserWriteDao.delete("delete from LvAccountAddress where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrder
	 * @return
	 */
	//LvOrder测试查询数据
	public Boolean  searchLvOrder(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrder"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrder测试保存数据
	public Boolean  saveLvOrder(){
		
		LvOrder order = new LvOrder();
		order.setOid("test1");
		order.setCouponNum(1);
		order.setPayStatus((short)1);
		order.setStatus((short)0);
		order.setMemid(1);
		order.setUserEmail("abc@qq.com");
		order.setPaymethod((short)1);
		order.setOrderRemark("test test test test");
		order.setSendRemark("fast fast");
		order.setBreakRemark("error");
		order.setOvertime(new Date());
		order.setPostprice(5.20f);
		order.setTotalPrice(250f);
		order.setThirdPartyOrder("thirdtest");
		order.setExpressCompany("DHL");
		order.setExpressName("DHLDHL");
		order.setExpressNum("test121341414");
		order.setShipTime(new Date());
		order.setCurrency("USD");
		order.setIsdelete((short)0);
		order.setIsServiceAudit((short)1);
		order.setServiceAuditContent("testaudit");
		order.setIsFinanceAudit(0);
		order.setFinanceAuditContent("34343434");
		order.setIsGroup(1);
		order.setGroupCode("3434343434");
		order.setFlag(0);
		order.setIsBalance((short)0);
		order.setStoreId("tvpadcn");
		order.setCode("test0");
		order.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(order);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrder测试修改数据
	public Boolean  updateLvOrder(){
		try {
			lvlogicWriteDao.update("update LvOrder set totalPrice=1 where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrder测试删除数据
	public Boolean  deleteLvOrder(){
		try {
			lvlogicWriteDao.delete("delete from LvOrder where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderAddress
	 * @return
	 */
	//LvOrderAddress测试查询数据
	public Boolean  searchLvOrderAddress(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderAddress"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderAddress测试保存数据
	public Boolean  saveLvOrderAddress(){
		
		LvOrderAddress address = new LvOrderAddress();
		address.setOrderId("test1");
		address.setRelCode("1");
		address.setRelName("test");
		address.setPostCode("1111111");
		address.setMobile("13111111111");
		address.setTel("010-1234567");
		address.setBestDeliveryTime(1);
		address.setContryId(100023);
		address.setContryName("China");
		address.setProvinceId(200005);
		address.setProvinceName("Guangdong");
		address.setCityName("guangzhou");
		address.setAdress("tian he qu 1 hao");
		address.setStoreId("tvpadcn");
		address.setCode("test0");
		address.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(address);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderAddress测试修改数据
	public Boolean  updateLvOrderAddress(){
		try {
			lvlogicWriteDao.update("update LvOrderAddress set relName='testUser' where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderAddress测试删除数据
	public Boolean  deleteLvOrderAddress(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderAddress where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderComment
	 * @return
	 */
	//LvOrderComment测试查询数据
	public Boolean  searchLvOrderComment(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderComment"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderComment测试保存数据
	public Boolean  saveLvOrderComment(){
		
		LvOrderComment comment = new LvOrderComment();
		comment.setUid("test2242424");
		comment.setNickname("test");
		comment.setOrderId("test3333333");
		comment.setContent("testest");
		comment.setReplyId(0);
		comment.setScore(3);
		comment.setGrade((short)2);
		comment.setIsCheck((short)1);
		comment.setContryId(100023);
		comment.setOprice(25f);
		comment.setPnum(1);
		comment.setCurrency("USD");
		comment.setIsDelete((short)0);
		comment.setStoreId("tvpadcn");
		comment.setCode("test0");
		comment.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(comment);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderComment测试修改数据
	public Boolean  updateLvOrderComment(){
		try {
			lvlogicWriteDao.update("update LvOrderComment set nickname='testUser' where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderComment测试删除数据
	public Boolean  deleteLvOrderComment(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderComment where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderDetails
	 * @return
	 */
	//LvOrderDetails测试查询数据
	public Boolean  searchLvOrderDetails(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderDetails"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderDetails测试保存数据
	public Boolean  saveLvOrderDetails(){
		
		LvOrderDetails detail = new LvOrderDetails();
		detail.setOrderId("test232323242424");
		detail.setProductCode("p333333333333");
		detail.setOprice(25f);
		detail.setOremark("33333333");
		detail.setPnum(11);
		detail.setPostPrice(5f);
		detail.setCurrency("USD");
		detail.setIsDelete(0);
		detail.setIsPackage(1);
		detail.setPcode("3333333333333");
		detail.setCouponCode("343434");
		detail.setCouponPrice(100f);
		detail.setStoreId("tvpadcn");
		detail.setCode("test0");
		detail.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(detail);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderDetails测试修改数据
	public Boolean  updateLvOrderDetails(){
		try {
			lvlogicWriteDao.update("update LvOrderDetails set oprice=1 where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderDetails测试删除数据
	public Boolean  deleteLvOrderDetails(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderDetails where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderLogs
	 * @return
	 */
	//LvOrderLogs测试查询数据
	public Boolean  searchLvOrderLogs(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderLogs"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderLogs测试保存数据
	public Boolean  saveLvOrderLogs(){
		
		LvOrderLogs log = new LvOrderLogs();
		log.setUname("test");
		log.setOrd("test343434");
		log.setOperate("test");
		log.setStoreId("tvpadcn");
		log.setCode("test0");
		log.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(log);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderLogs测试修改数据
	public Boolean  updateLvOrderLogs(){
		try {
			lvlogicWriteDao.update("update LvOrderLogs set uname='testUser' where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderLogs测试删除数据
	public Boolean  deleteLvOrderLogs(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderLogs where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderPackageDetails
	 * @return
	 */
	//LvOrderPackageDetails测试查询数据
	public Boolean  searchLvOrderPackageDetails(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderPackageDetails"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderPackageDetails测试保存数据
	public Boolean  saveLvOrderPackageDetails(){
		
		LvOrderPackageDetails detail = new LvOrderPackageDetails();
		detail.setOrderDetailsCode("te232");
		detail.setPnum(2);
		detail.setOprice(100f);
		detail.setProductCode("test3333333333");
		detail.setCouponCode("3333333333");
		detail.setCouponPrice(25f);
		detail.setPcode("4444444444");
		detail.setCurrency("USD");
		detail.setCode("test0");
		detail.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(detail);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//LvOrderPackageDetails测试修改数据
	public Boolean  updateLvOrderPackageDetails(){
		try {
			lvlogicWriteDao.update("update LvOrderPackageDetails set oprice=1 where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderPackageDetails测试删除数据
	public Boolean  deleteLvOrderPackageDetails(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderPackageDetails where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvOrderRemind
	 * @return
	 */
	//LvOrderRemind测试查询数据
	public Boolean  searchLvOrderRemind(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvOrderRemind"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvOrderRemind测试保存数据
	public Boolean  saveLvOrderRemind(){

		LvOrderRemind remind = new LvOrderRemind();
		remind.setUserCode("333333333");
		remind.setOrderId("c333333333");
		remind.setRemindNum("3");
		remind.setRemindTime(new Date());
		remind.setStoreId("tvpadcn");
		remind.setCode("test0");
		remind.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(remind);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderRemind测试修改数据
	public Boolean  updateLvOrderRemind(){
		try {
			lvlogicWriteDao.update("update LvOrderRemind set remindNum='5' where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvOrderRemind测试删除数据
	public Boolean  deleteLvOrderRemind(){
		try {
			lvlogicWriteDao.delete("delete from LvOrderRemind where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvShopCart
	 * @return
	 */
	//LvShopCart测试查询数据
	public Boolean  searchLvShopCart(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvShopCart"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvShopCart测试保存数据
	public Boolean  saveLvShopCart(){
		
		LvShopCart cart = new LvShopCart();
		cart.setUserCode("u3444444444");
		cart.setProductCode("p333333333333");
		cart.setShopNum(3);
		cart.setShopPrice(100f);
		cart.setStoreId("tvpadcn");
		cart.setMallFlag("www");
		cart.setCode("test0");
		cart.setCreateTime(new Date());
		
		try {
			lvlogicWriteDao.save(cart);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvShopCart测试修改数据
	public Boolean  updateLvShopCart(){
		try {
			lvlogicWriteDao.update("update LvShopCart set shopPrice=1 where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvShopCart测试删除数据
	public Boolean  deleteLvShopCart(){
		try {
			lvlogicWriteDao.delete("delete from LvShopCart where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvWesternInfo
	 * @return
	 */
	//LvWesternInfo测试查询数据
	public Boolean  searchLvWesternInfo(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvWesternInfo"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvWesternInfo测试保存数据
	public Boolean  saveLvWesternInfo(){
		
		LvWesternInfo info = new LvWesternInfo();
		info.setAdress("china");
		info.setCode("test0");
		info.setContryId(100023);
		info.setContryName("USA");
		info.setCreateTime(new Date());
		info.setMtcn("5454");
		info.setOid("c222222222");
		info.setRemittance(34f);
		info.setRemitter("user");
		info.setStatus((short)0);
		info.setStoreId("tvpadcn");
		info.setTransferTime("2012/12/12");
		
		try {
			lvlogicWriteDao.save(info);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvWesternInfo测试修改数据
	public Boolean  updateLvWesternInfo(){
		try {
			lvlogicWriteDao.update("update LvWesternInfo set storeId='tvpaden' where code='test0'", null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvWesternInfo测试删除数据
	public Boolean  deleteLvWesternInfo(){
		try {
			lvlogicWriteDao.delete("delete from LvWesternInfo where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvPayLogs
	 * @return
	 */
	//LvPayLogs测试查询数据
	public Boolean  searchLvPayLogs(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvPayLogs"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvPayLogs测试保存数据
	public Boolean  saveLvPayLogs(){

		LvPayLogs log = new LvPayLogs();
		log.setAmount(100f);
		log.setBankorderid("333333333");
		log.setCheckno("111111111111");
		log.setCode("test0");
		log.setCreateTime(new Date());
		log.setCurrency("USD");
		log.setEndTime("2012/12/12");
		log.setOid("p222222222");
		log.setPaydate(new Date());
		log.setPaymethod((short)1);
		log.setRemark("55555555555");
		log.setStartTime("2011/11/11");
		log.setStatus((short)0);
		log.setStoreId("tvpadcn");
		
		try {
			lvlogicWriteDao.save(log);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvPayLogs测试修改数据
	public Boolean  updateLvPayLogs(){
		try {
			lvlogicWriteDao.update("update LvPayLogs set storeId='tvpaden' where code='test0'",null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvPayLogs测试删除数据
	public Boolean  deleteLvPayLogs(){
		try {
			lvlogicWriteDao.delete("delete from LvPayLogs where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * LvProductComment
	 * @return
	 */
	//LvProductComment测试查询数据
	public Boolean  searchLvProductComment(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProductComment"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//LvProductComment测试保存数据
	public Boolean  saveLvProductComment(){
		
		LvProductComment comment = new LvProductComment();
		comment.setCode("test0");
		comment.setContent("goodgood");
		comment.setContryId(100023);
		comment.setCreateTime(new Date());
		comment.setCurrency("USD");
		comment.setGrade((short)2);
		comment.setIp("127.0.0.1");
		comment.setIsCheck((short)0);
		comment.setIsDelete((short)0);
		comment.setNickname("aaaa");
		comment.setOid("33333333");
		comment.setOprice(100f);
		comment.setPnum(5);
		comment.setProductCode("p99999999999");
		comment.setReplyId(4);
		comment.setScore(5);
		comment.setSortId(1);
		comment.setStoreId("tvpadcn");
		comment.setUid("333333333");
		
		try {
			lvlogicWriteDao.save(comment);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductComment测试修改数据
	public Boolean  updateLvProductComment(){
		try {
			lvlogicWriteDao.update("update LvProductComment set oprice=1 where code='test0'",null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductComment测试删除数据
	public Boolean  deleteLvProductComment(){
		try {
			lvlogicWriteDao.delete("delete from LvProductComment where code='test0'", null);
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * search
	 */
	//LvActivity测试查询数据
	public Boolean searchLvActivity(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvActivity"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvActivityInfo测试查询数据
	public Boolean searchLvActivityInfo(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvActivityInfo"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvArea测试查询数据
	public Boolean searchLvArea(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvArea"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvCarriageSet测试查询数据
	public Boolean searchLvCarriageSet(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvCarriageSet"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvCoupon测试查询数据
	public Boolean searchLvCoupon(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvCoupon"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvLogistics测试查询数据
	public Boolean searchLvLogistics(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvLogistics"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvPaymentData测试查询数据
	public Boolean searchLvPaymentData(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvPaymentData"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvPaymentStyle测试查询数据
	public Boolean searchLvPaymentStyle(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvPaymentStyle"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProduct测试查询数据
	public Boolean searchLvProduct(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProduct"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductImage测试查询数据
	public Boolean searchLvProductImage(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProductImage"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductLadder测试查询数据
	public Boolean searchLvProductLadder(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProductLadder"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductPackage测试查询数据
	public Boolean searchLvProductPackage(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProductPackage"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvProductProperty测试查询数据
	public Boolean searchLvProductProperty(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvProductProperty"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvStore测试查询数据
	public Boolean searchLvStore(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvStore"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//LvUserSubscribe测试查询数据
	public Boolean searchLvUserSubscribe(){
		try {
			lvlogicReadDao.countQueryResult(Finder.create("from LvUserSubscribe"), null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
