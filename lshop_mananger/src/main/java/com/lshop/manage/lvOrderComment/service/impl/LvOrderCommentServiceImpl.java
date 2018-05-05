package com.lshop.manage.lvOrderComment.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrderComment.service.LvOrderCommentService;
@Service("LvOrderCommentService")
public class LvOrderCommentServiceImpl implements LvOrderCommentService {
	private static final Log logger	= LogFactory.getLog(LvOrderCommentServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvOrderService lvOrderService;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午01:39:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午01:39:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvOrderComment t where isDelete=0 and (replyId is null or replyId='')");
		if(lvOrderComment!=null){
			if(ObjectUtils.isNotEmpty(lvOrderComment.getOrderId())){//订单编号
				hql.append(" and orderId like :orderId");
			}
			if(ObjectUtils.isNotEmpty(lvOrderComment.getNickname())){//用户 昵称
				hql.append(" and nickname like :nickname");
			}
			params=BeanUtils.describeForHQL(lvOrderComment);
			
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));

		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			hql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
	
	/**
	 * 
	 * @Method: save 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午04:14:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午04:14:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void save(Dto dto) throws ServiceException {
		//LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		
		LvProductComment lvProductcomment= (LvProductComment) dto.get("lvProductcomment");

		
		//新增订单评论信息
		LvOrderComment lvOrderComment=new LvOrderComment();
		lvOrderComment.setOrderId(lvProductcomment.getOid());
		lvOrderComment.setContent(lvProductcomment.getContent());
		lvOrderComment.setScore(lvProductcomment.getScore());
		lvOrderComment.setGrade(lvProductcomment.getGrade());
		lvOrderComment.setContryId(lvProductcomment.getContryId());
		lvOrderComment.setOprice(lvProductcomment.getOprice());
		lvOrderComment.setPnum(lvProductcomment.getPnum());
		lvOrderComment.setCurrency(lvProductcomment.getCurrency());
		lvOrderComment.setIsDelete(Short.parseShort("0"));
		lvOrderComment.setIsCheck(lvProductcomment.getIsCheck());
		lvOrderComment.setStoreId(lvProductcomment.getStoreId());
		lvOrderComment.setCode(CodeUtils.getCode());
		lvOrderComment.setCreateTime(lvProductcomment.getCreateTime());
		lvOrderComment.setNickname(lvProductcomment.getNickname());
		lvOrderComment.setCommentImages(lvProductcomment.getCommentImages());
		dao.save(lvOrderComment);
		
		//新增产品评论信息
		dao.save(lvProductcomment);
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @Method: save 
	 * @Description:  [新增订单评论]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午01:40:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午01:40:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void saveReply(Dto dto) throws Exception {
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		LvOrder lvOrder=new LvOrder();
		lvOrder.setOid(lvOrderComment.getOrderId());
		dto.put("lvOrder", lvOrder);
		lvOrder=lvOrderService.getOrder(dto);
		lvOrderComment.setStoreId(lvOrder.getStoreId());
		dao.save(lvOrderComment);
		
		//分发新增产品评论回复
		//更加订单编号查询产品评论信息
		if (ObjectUtils.isNotEmpty(lvOrderComment.getOrderId())) {
			String hql="from LvProductComment where isDelete=0 and (replyId is null or replyId='') and oid='"+lvOrderComment.getOrderId()+"'";
			List<LvProductComment> list=dao.find(hql, null);
			for (int i = 0; i < list.size(); i++) {
				LvProductComment lp=list.get(i);
				LvProductComment lvProductComment=new LvProductComment();
				lvProductComment.setUid(lp.getUid());
				lvProductComment.setNickname(lp.getNickname());
				lvProductComment.setOid(lvOrderComment.getOrderId());
				lvProductComment.setProductCode(lp.getProductCode());
				lvProductComment.setContent(lvOrderComment.getContent());
				lvProductComment.setReplyId(lp.getId());
				lvProductComment.setScore(lp.getScore());
				lvProductComment.setGrade(lp.getGrade());
				lvProductComment.setIp(lp.getIp());
				lvProductComment.setContryId(lp.getContryId());
				lvProductComment.setOprice(lp.getOprice());
				lvProductComment.setPnum(lp.getPnum());
				lvProductComment.setCurrency(lp.getCurrency());
				lvProductComment.setIsCheck(Short.parseShort("0"));
				lvProductComment.setIsDelete(Short.parseShort("0"));
				lvProductComment.setStoreId(lp.getStoreId());
				lvProductComment.setCreateTime(new Date());
				lvProductComment.setCode(CodeUtils.getCode());
				dao.save(lvProductComment);
			}
		}
	}
	
	
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.delete() method begin*****");
		}
		String ids=(String) dto.get("ids");
		if(ObjectUtils.isNotEmpty(ids)){
			//软删除产品评论
			String hql="update LvProductComment set isDelete=-1 where oid in(" +
					"select DISTINCT orderId from LvOrderComment where isDelete=0 and id in("+ids+")" +
					")";
			dao.update(hql,null);
			//软删除订单评论
			hql="update LvOrderComment set isDelete=-1 where id in("+ids+")";
			dao.update(hql,null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: deleteReply 
	 * @Description:  [删除订单评论回复]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午04:11:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午04:11:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void deleteReply(Dto dto) throws ServiceException {
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		dao.delete(lvOrderComment);
	}
	
	/**
	 * 
	 * @Method: getReplyList 
	 * @Description:  [获取订单评论回复列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午02:08:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午02:08:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getReplyList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		String hql="from LvOrderComment where isDelete=0 and replyId is not null";
		if(lvOrderComment!=null){
			if(ObjectUtils.isNotEmpty(lvOrderComment.getId())){//产品code
				hql+=" and replyId="+lvOrderComment.getId()+"";
			}
		}
		//判断当前是商城入口，还是商家入口
		String storeListString=""; 
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			String [] arr=dto.getAsString("flag").split(",");
			String temp="";
			for (int i = 0; i < arr.length; i++) {
				if (ObjectUtils.isNotEmpty(arr[i])) {
					for(Map.Entry<String, String> entry:Constants.STORE_LIST.entrySet()){   
						if(arr[i].trim().equals(entry.getKey())){
						   if(ObjectUtils.isNotEmpty(storeListString)){
							   storeListString+=","+entry.getValue();
						   }else{
							   storeListString+=entry.getValue();
						   }
						}
					}
				}
			}
			if(ObjectUtils.isNotEmpty(storeListString)){
				hql+=" and storeId in ("+storeListString+")";
			}else{
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and storeId in("+storeList+")";
			}
		}



		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			hql+=" order by "+ orderField+" "+orderDirection;
		}
		return dao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
	}
	
	/**
	 * 
	 * @Method: updateAudit 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午02:08:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午02:08:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void updateAudit(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.updateAudit() method begin*****");
		}
		String ids=(String) dto.get("ids");
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		if(ObjectUtils.isNotEmpty(ids)){
			//订单评论审核
			String hql="update LvOrderComment set isCheck=1,\n" +
			   " modifyUserId="+lvOrderComment.getModifyUserId()+",\n" +
			   " modifyUserName='"+lvOrderComment.getModifyUserName()+"',\n" +
			   " modifyTime='"+DateUtils.formatDate(lvOrderComment.getModifyTime(), null) +"'\n" +
			   " where id in("+ids+")\n" +
			   " and isCheck=0 \n";
	           dao.update(hql,null);
	           
	       //分发订单评论审核(产品评论)
	       hql="update LvProductComment set isCheck=1,\n" +
			   " modifyUserId="+lvOrderComment.getModifyUserId()+",\n" +
			   " modifyUserName='"+lvOrderComment.getModifyUserName()+"',\n" +
			   " modifyTime='"+DateUtils.formatDate(lvOrderComment.getModifyTime(), null) +"'\n" +
			   " where oid in(select orderId from LvOrderComment where id in("+ids+"))\n" +
			   " and isCheck=0 \n";
	           dao.update(hql,null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.updateAudit() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id获取订单评论信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-25 下午02:13:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-25 下午02:13:37]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrderComment get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.get() method begin*****");
		}
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		lvOrderComment=(LvOrderComment) dao.load(LvOrderComment.class, lvOrderComment.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.get() method end*****");
		}
		return lvOrderComment;
	}
	
	
	

}
