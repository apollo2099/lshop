package com.lshop.manage.lvAd.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvAd.service.LvAdService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAd.service.impl.LvAdServiceImpl.java]  
 * @ClassName:    [LvAdServiceImpl]   
 * @Description:  [广告信息管理-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 上午10:43:35]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 上午10:43:35]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvAdService")
public class LvAdServiceImpl implements LvAdService {
	private static final Log logger	= LogFactory.getLog(LvAdServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询广告信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:24:09]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:24:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvAd lvAd=(LvAd)dto.get("lvAd");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder sql = new StringBuilder("from LvAd t where 1=1");
		//拼装查询条件
		if(lvAd!=null){
			if(ObjectUtils.isNotEmpty(lvAd.getAdKey())){
				sql.append(" and t.adKey=:adKey");
				params.put("adKey", lvAd.getAdKey());
			}
			if(ObjectUtils.isNotEmpty(lvAd.getStatus())){
				sql.append(" and t.status=:status");
				params.put("status", lvAd.getStatus());
			}
			if(ObjectUtils.isNotEmpty(lvAd.getStoreId())){
				sql.append(" and t.storeId=:storeId");
				params.put("storeId", lvAd.getStoreId());
			}
		}
		sql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"),"storeId","t"));
		sql.append(" order by t.modifyTime desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除广告信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:24:55]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:24:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.delete() method begin*****");
		}
		LvAd lvAd=(LvAd)dto.get("lvAd");
		lvAd=(LvAd) dao.load(LvAd.class, lvAd.getId());
		//删除前台缓存
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AD_CHANGE", lvAd.getStoreId()+"_"+lvAd.getAdKey());
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		dao.delete(lvAd);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询广告信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:09]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvAd get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.get() method begin*****");
		}
		LvAd lvAd=(LvAd)dto.get("lvAd");
		lvAd=(LvAd) dao.load(LvAd.class, lvAd.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.get() method end*****");
		}
		return lvAd;
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增广告信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:19]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.save() method begin*****");
		}
		LvAd lvAd=(LvAd)dto.get("lvAd");
		dao.save(lvAd);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.save() method end*****");
		}
		return null;
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改广告信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:29]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:29]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.update() method begin*****");
		}
		LvAd lvAd=(LvAd)dto.get("lvAd");
		dao.update(lvAd);
		//删除前台缓存
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AD_CHANGE", lvAd.getStoreId()+"_"+lvAd.getAdKey());
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		if (logger.isInfoEnabled()){
			logger.info("***** LvAdServiceImpl.update() method end*****");
		}
		
	}
}
