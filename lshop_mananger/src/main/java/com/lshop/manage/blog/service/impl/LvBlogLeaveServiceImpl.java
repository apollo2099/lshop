package com.lshop.manage.blog.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogLeave;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.blog.service.LvBlogContentService;
import com.lshop.manage.blog.service.LvBlogLeaveService;
/** 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvBlogLeaveServiceImpl.java]  
 * @ClassName:    [LvBlogLeaveServiceImpl]   
 * @Description:  [博客文章留言管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午04:41:15]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午04:41:15]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvBlogLeaveService")
public class LvBlogLeaveServiceImpl extends BaseServiceImpl implements LvBlogLeaveService{
	private static final Log logger	= LogFactory.getLog(LvBlogLeaveServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvBlogContentService lvBlogContentService;
	
	private LvBlogLeave lvBlogLeave;
	private LvBlogContent lvBlogContent;
	
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询博客文章留言列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:44:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:44:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination list(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		lvBlogLeave= (LvBlogLeave)dto.get("model");
		
		StringBuilder hql = new StringBuilder("from LvBlogLeave t where 1=1 and (whoId is null or whoId='')");
		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append("order by createTime desc");
		//返回执行的结果对象
		return dao.find(Finder.create(hql.toString()),
				page.getPageNum(), page.getNumPerPage());
	}
	
	/**
	 * 
	 * @Method: add 
	 * @Description:  [添加博客留言]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:43:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:43:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto add(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.add() method begin*****");
		}
		lvBlogLeave=(LvBlogLeave)dto.get("model");
		if(lvBlogLeave!=null &&ObjectUtils.isEmpty(lvBlogLeave.getWhoId())){
			String hql="update LvBlogContent set replyNum=replyNum+1 where id="+lvBlogLeave.getLvBlogContent().getId();
			this.dao.update(hql,null);
		}
		dao.save(lvBlogLeave);
		result.setAppCode(StatusCode.M_Success);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.add() method end*****");
		}
		return result;
	}

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除博客留言]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:43:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:43:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.delete() method begin*****");
		}
		// TODO Auto-generated method stub
		lvBlogLeave=(LvBlogLeave)dto.get("model");
		lvBlogLeave=(LvBlogLeave) dao.load(LvBlogLeave.class, lvBlogLeave.getId());
		
		//先修改文章的回复数，在删除留言
		if(ObjectUtils.isEmpty(lvBlogLeave.getWhoId())&&ObjectUtils.isNotEmpty(lvBlogLeave.getLvBlogContent().getId())){
			lvBlogContent=new LvBlogContent();
			lvBlogContent.setId(lvBlogLeave.getLvBlogContent().getId());
			dto.put("model", lvBlogContent);
			lvBlogContent=lvBlogContentService.get(dto);
			if(ObjectUtils.isNotEmpty(lvBlogContent.getReplyNum())){
				dao.update("update LvBlogContent set replyNum=replyNum-1 where id="+lvBlogContent.getId(),null);
			}else{
				dao.update("update LvBlogContent set replyNum=0 where id="+lvBlogContent.getId(),null);
			}
		}
		dao.delete(lvBlogLeave);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.delete() method end*****");
		}
	}

	
	@Override
	public LvBlogLeave get(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public LvBlogLeave save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LvBlogLeave update(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @Method: replyLook 
	 * @Description:  [文章回复]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:44:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:44:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination replyLook(Dto dto) {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogLeaveServiceImpl.replyLook() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		lvBlogLeave=(LvBlogLeave)dto.get("model");
		HttpServletRequest request=(HttpServletRequest)dto.get("request");
		lvBlogLeave=(LvBlogLeave)this.dao.load(LvBlogLeave.class, lvBlogLeave.getId());
		request.setAttribute("lvBlogLeave", lvBlogLeave);
		String hql="FROM LvBlogLeave where whoId="+lvBlogLeave.getId()+" ";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and storeId in ("+storeList+")";
		}
		hql+=" order by id desc";
		return dao.find(Finder.create(hql),
				page.getPageNum(), page.getNumPerPage());
	}
	


	public LvBlogLeave getLvBlogLeave() {
		return lvBlogLeave;
	}

	public void setLvBlogLeave(LvBlogLeave lvBlogLeave) {
		this.lvBlogLeave = lvBlogLeave;
	}

}
