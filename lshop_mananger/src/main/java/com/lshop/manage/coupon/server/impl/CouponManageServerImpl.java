package com.lshop.manage.coupon.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.DwzJsonResponse;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.BeanUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponAid;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.OrderHelp;
import com.lshop.manage.coupon.server.CouponManageServer;

@Service("CouponManageServer")
public class CouponManageServerImpl implements CouponManageServer {

	@Resource
	private HibernateBaseDAO dao;
	
	/**
	 * 获取订单关联的优惠券
	 */
	@Override
	public LvCoupon getCoupon(Dto dto) {
		// TODO Auto-generated method stub
		String oid =dto.getAsString("oid");
		List<LvCoupon> couponList=dao.find("from LvCoupon where oid='"+oid+"'", null);
		if(couponList!=null&&couponList.size()>0){//查询普通优惠券
			return couponList.get(0);
		}else{//查询特殊优惠券
			List<LvCouponAid> couponAipList = dao.find("from LvCouponAid where oid='"+oid+"'", null);
			if(couponAipList!=null&&couponAipList.size()>0){
			couponList=dao.find("from LvCoupon where couponCode='"+couponAipList.get(0).getCcode()+"')", null);
		    if(couponList!=null&&couponList.size()>0){
		    	return couponList.get(0);
		    }	
		}
		}
		return null;
	}
	
	@Override
	public Pagination couponlist(Dto dto) {
		LvCoupon obj=(LvCoupon) dto.getDefaultPo();
		Pagination page=dto.getDefaultPage();
		String validitydate=dto.getAsString("validitydate");
		StringBuffer hql=new StringBuffer("FROM LvCoupon WHERE isdel=0 AND  isactivate=:isactivate");
		if (obj.getCouponType()!=null) {
			hql.append(" AND couponType='").append(obj.getCouponType()).append("'");
		}
		if (obj.getCouponName()!=null&&!"".equals(obj.getCouponName().trim())) {
			hql.append(" AND couponName LIKE '%").append(obj.getCouponName()).append("%'");
		}
		if (obj.getFaceValue()!=null) {
			hql.append(" AND faceValue='").append(obj.getFaceValue()).append("'");
		}
		if (obj.getCurrency()!=null&&!"".equals(obj.getCurrency().trim())) {
			hql.append(" AND currency='").append(obj.getCurrency()).append("'");
		}
		if (obj.getValidityDate()!=null) {
			hql.append(" AND validitydate LIKE '%").append(DateUtils.formatDate(obj.getValidityDate(), "yyyy-MM-dd")).append("%'");
		}
		if(obj.getCouponCode()!=null&&!"".equals(obj.getCouponCode().trim())){
			hql.append(" and couponCode like'").append(obj.getCouponCode().trim()).append("%'");
		}
		page=	dao.find(Finder.create(hql.toString()).setParam("isactivate", obj.getIsActivate()), page.getNextPage(), page.getNumPerPage());
		return page;
	}
	@Override
	public Integer doDel(Dto dto){
		String ids=dto.getAsString("ids");
		String hql="DELETE LvCoupon WHERE isactivate=0 AND id in ("+ids+")";//只可刪除為出庫的優惠劵
		dao.delete(hql, null);
		return DwzJsonResponse.STATUS_CODE_OK;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Dto doActivationCoupon(Dto dto){
		List list = new ArrayList();
		 //增加表头
		 String[]title=new String[5];
		 title[0]="优惠劵码";
		 title[1]="面值";
		 title[2]="优惠卷名称";
		 title[3]="优惠卷类型";
		 title[4]="有效期";
		 list.add(title);
		 String ids=dto.getAsString("ids");
		
		StringBuilder hql=new StringBuilder("FROM LvCoupon WHERE isdel=0 AND  isactivate=0 ");
		if (ids!=null&&!"".equals(ids.trim())) {
			hql.append(" AND id in ("+ids+")");
		}
		LvCoupon lvCoupon=(LvCoupon) dto.getDefaultPo();
		int count=20;
		if (lvCoupon!=null) {
			if (lvCoupon.getFaceValue()!=null) {
				
				hql.append(" AND faceValue=").append(lvCoupon.getFaceValue());
			}
			if (lvCoupon.getCouponType()!=null) {
				
				hql.append(" AND couponType=").append(lvCoupon.getCouponType());
			}
			if (lvCoupon.getCurrency()!=null&&!"".equals(lvCoupon.getCurrency().trim())) {
				
				hql.append(" AND currency='").append(lvCoupon.getCurrency().trim()).append("' ");
			}
			if (lvCoupon.getValidityDate()!=null) {
				
				hql.append(" AND validitydate LIKE '").append(DateUtils.formatDate(lvCoupon.getValidityDate(), "yyyy-MM-dd")).append("%' ");
			}
			count=dto.getAsInteger("count")==null?1000:dto.getAsInteger("count");
		}
		
	    List<LvCoupon> dataList=	 (List<LvCoupon>) dao.find(Finder.create(hql.toString()), 1,count).getList();
	    String[]body=null;
	    ids="";
	    for (LvCoupon obj : dataList) {
	    	ids+=obj.getId()+",";
	    	body=new String[5];
	    	body[0]=obj.getCouponCode();
	    	body[1]=obj.getFaceValue()+obj.getCurrency();
	    	body[2]=obj.getCouponName();
	    	body[3]=obj.getCouponType()==1?"特殊优惠卷":"普通优惠卷";
	    	body[4]=DateUtils.formatDate(obj.getValidityDate(), "yyyy-MM-dd");
	    	list.add(body);
		}
	    if (!"".equals(ids.trim())) {
	    	hql=new StringBuilder("UPDATE LvCoupon SET isactivate=1 WHERE isactivate=0 AND  id in (").append(ids.substring(0, ids.length()-1)).append(")");//修改優惠劵激活狀態
	  	    dao.update(hql.toString(),null);
		}
	  
	    dto.setDefaultAList(list);
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer createCoupon(Dto dto) {
		final int count=dto.getAsInteger("count");//需要生成的优惠劵数量
		final LvCoupon bean=(LvCoupon) dto.getDefaultPo();
	    new Thread(){
			@Override
			public void run() {
				doCreateCoupon(bean,count);
			}
	    	
	    	
	    }.start();
		return DwzJsonResponse.STATUS_CODE_OK;
	}
	/**
	 * 生成不含标识的优惠卷
	 * @param bean
	 * @param count
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private synchronized  void doCreateCoupon(LvCoupon bean,int count){
		String hql="SELECT couponCode FROM LvCoupon WHERE createtime>='"+DateUtils.formatDate(new Date(), "yyyy-MM-dd HH")+"'";
		List<String>couponCodes=dao.find(hql,null);
		if (couponCodes==null) {
			couponCodes=new ArrayList<String>();
		}
		
		for (int i = 0; i < count;) {
			LvCoupon obj=new LvCoupon();
			String couponCode= OrderHelp.createCouponCode();
			if (!couponCodes.contains(couponCode)) {//優惠劵排重
				couponCodes.add(couponCode);
				obj.setCouponCode(couponCode);
				obj.setValidityDate(bean.getValidityDate());
				obj.setCreateTime(new Date());
				obj.setCurrency(bean.getCurrency());
				obj.setFaceValue(bean.getFaceValue());
				obj.setIsActivate((short)0);//設置優惠劵激活狀態--未激活
				obj.setIsDel((short)0);
				obj.setCouponType(bean.getCouponType());
				obj.setCouponName(bean.getCouponName());
				 try {
					 dao.save(obj);
					 i++;
				} catch (Exception e) {
					couponCodes.remove(couponCode);
				}
				
			}
		}
	}

	/**
	 * 生成含标识的优惠卷
	 * @param bean
	 * @param count
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private synchronized  List doCreateCoupon(LvCoupon bean,int count,String type){
		String hql="SELECT couponCode FROM LvCoupon where length(couponCode)=8"; 
		List<String>couponCodes=dao.find(hql,null);
		List lvCoupons=new ArrayList(count);
		if (couponCodes==null) {
			couponCodes=new ArrayList<String>();
		}
		for (int i = 0; i < count;) {
			String couponCode= OrderHelp.createCouponCode(type);
			if (!couponCodes.contains(couponCode)) {//優惠劵排重
				LvCoupon obj=new LvCoupon();
				couponCodes.add(couponCode);
				obj.setCouponCode(couponCode);
				obj.setValidityDate(bean.getValidityDate());
				obj.setCreateTime(new Date());
				obj.setCurrency(bean.getCurrency());
				obj.setFaceValue(bean.getFaceValue());
				obj.setIsDel((short)0);
				obj.setIsActivate((short)1);//設置優惠劵激活狀態--已激活
				obj.setCouponType(bean.getCouponType());
				obj.setCouponName(bean.getCouponName());
				lvCoupons.add(obj);
				i++;
			}
		}
		dao.saveOrUpdate(lvCoupons);
		return lvCoupons;
	}
	

	

	
	@Override
	public Map createSpreadCoupon(Dto dto) {
		Map map=new HashMap();
		final int count=dto.getAsInteger("count");//需要生成的优惠劵数量
		final LvCoupon bean=(LvCoupon) dto.getDefaultPo();
		String type=dto.getAsString("type");
		
		List<LvCoupon> numList=doCreateCoupon(bean,count,type);
		List<LvUser> userList=this.dao.find("FROM LvUser where id in("+dto.getAsString("ids")+")", null);
		map.put("numList", numList);
		map.put("userList", userList);
		return map;
	}
	
	public Map createRankCoupon(Dto dto) {
		Map map=new HashMap();
		final int count=dto.getAsInteger("count");//需要生成的优惠劵数量
		final LvCoupon bean=(LvCoupon) dto.getDefaultPo();
		String type=dto.getAsString("type");
		
		List<LvCoupon> numList=doCreateCoupon(bean,count,type);
		map.put("numList", numList);
		return map;
	}

	@Override
	public LvCoupon getCouponInfo(Dto dto) {
		// TODO Auto-generated method stub
		Integer id=dto.getAsInteger("id");
		return (LvCoupon)dao.load(LvCoupon.class, id);
	}
	@Override
	public void update(Dto dto) throws ServiceException{
		// TODO Auto-generated method stub
		dao.update(dto.getDefaultPo());
	}
}
