package com.lshop.manage.lvProductComment.service.impl;

import java.util.HashMap;
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
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvProductComment.service.LvProductCommentService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductComment.service.impl.LvProductCommentServiceImpl.java]  
 * @ClassName:    [LvProductCommentServiceImpl]   
 * @Description:  [评论信息管理-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 下午06:25:09]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 下午06:25:09]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductCommentService")
public class LvProductCommentServiceImpl implements LvProductCommentService {
	private static final Log logger	= LogFactory.getLog(LvProductCommentServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询评论信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:25:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:25:34]   
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
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvProductComment t where isDelete=0 and (replyId is null or replyId='')");
		if(lvProductComment!=null){
			if(ObjectUtils.isNotEmpty(lvProductComment.getProductCode())){//产品code
				hql.append(" and productCode like :productCode");
			}
			if(ObjectUtils.isNotEmpty(lvProductComment.getOid())){//订单编号
				hql.append(" and oid like :oid");
			}
			if(ObjectUtils.isNotEmpty(lvProductComment.getNickname())){//用户 昵称
				hql.append(" and nickname like :nickname");
			}
			params=BeanUtils.describeForHQL(lvProductComment);
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
	 * @Method: getReplyList 
	 * @Description:  [查询当前评论的回复列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-10 下午01:41:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-10 下午01:41:06]   
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
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		String hql="from LvProductComment where isDelete=0 and replyId is not null";
		if(lvProductComment!=null){
			if(ObjectUtils.isNotEmpty(lvProductComment.getId())){//产品code
				hql+=" and replyId="+lvProductComment.getId()+"";
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
	 * @Method: save 
	 * @Description:  [新增评论信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:25:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:25:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.save() method begin*****");
		}
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		dao.save(lvProductComment);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.save() method end*****");
		}
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [软删除评论信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:26:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:26:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.delete() method begin*****");
		}
		String ids=(String) dto.get("ids");
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvProductComment set isDelete=-1 where id in("+ids+")";
			dao.update(hql,null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.delete() method end*****");
		}
	}
	
	public void deleteReply(Dto dto) throws ServiceException {
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		dao.delete(lvProductComment);
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [查看评论信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:26:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:26:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductComment get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.get() method begin*****");
		}
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		lvProductComment=(LvProductComment) dao.load(LvProductComment.class, lvProductComment.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.get() method end*****");
		}
		return lvProductComment;
	}
	
	/**
	 * 
	 * @Method: updateAudit 
	 * @Description:  [审核评论信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午06:26:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午06:26:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void updateAudit(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.updateAudit() method begin*****");
		}
		String ids=(String) dto.get("ids");
		LvProductComment lvProductComment=(LvProductComment) dto.get("lvProductComment");
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvProductComment set isCheck=1,\n" +
			   " modifyUserId="+lvProductComment.getModifyUserId()+",\n" +
			   " modifyUserName='"+lvProductComment.getModifyUserName()+"',\n" +
			   " modifyTime='"+DateUtils.formatDate(lvProductComment.getModifyTime(), null) +"'\n" +
			   " where id in("+ids+")\n" +
			   " and isCheck=0 \n";
	           dao.update(hql,null);
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommentServiceImpl.updateAudit() method end*****");
		}
	}

}
