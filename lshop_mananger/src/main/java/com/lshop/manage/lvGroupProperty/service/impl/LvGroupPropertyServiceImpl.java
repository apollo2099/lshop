package com.lshop.manage.lvGroupProperty.service.impl;

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
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvGroupProperty.service.LvGroupPropertyService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupProperty.service.impl.LvGroupPropertyServiceImpl.java]  
 * @ClassName:    [LvGroupPropertyServiceImpl]   
 * @Description:  [团购扩展属性信息-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-23 下午04:52:35]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-23 下午04:52:35]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvGroupPropertyService")
public class LvGroupPropertyServiceImpl implements LvGroupPropertyService {
	private static final Log logger	= LogFactory.getLog(LvGroupPropertyServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
    /**
     * 
     * @Method: getList 
     * @Description:  [分页查询 团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:52:16]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:52:16]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvGroupProperty lvGroupProperty=(LvGroupProperty) dto.get("lvGroupProperty");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvGroupProperty t where 1=1 ");
		if(lvGroupProperty!=null){
			if(ObjectUtils.isNotEmpty(lvGroupProperty.getGroupCode())){//团购code
				hql.append(" and groupCode =:groupCode");	
				params.put("groupCode", lvGroupProperty.getGroupCode());
			}
			if(ObjectUtils.isNotEmpty(lvGroupProperty.getTitle())){//团购扩展属性标题
				hql.append(" and title like :title");
				params.put("title", "%"+lvGroupProperty.getTitle()+"%");
				
			}
		}
		//店铺标识
//		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
		if (ObjectUtils.isNotEmpty(orderField)&& ObjectUtils.isNotEmpty(orderDirection)) {
			hql.append(" order by " + orderField + " " + orderDirection);
		}
	
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:53:38]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:53:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.delete() method begin*****");
		}
		LvGroupProperty lvGroupProperty=(LvGroupProperty) dto.get("lvGroupProperty");
		dao.delete(lvGroupProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.delete() method end*****");
		}
		
	}

    /**
     * 
     * @Method: save 
     * @Description:  [新增团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:53:58]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:53:58]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.save() method begin*****");
		}
		LvGroupProperty lvGroupProperty=(LvGroupProperty) dto.get("lvGroupProperty");
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			lvGroupProperty.setStoreId(dto.getAsString("flag"));
//		}else{
//			lvGroupProperty.setStoreId("tvpadcn");
//		}
		//根据团购获取团购店铺标识
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dao.findUniqueByProperty(LvGroupBuy.class, "code", lvGroupProperty.getGroupCode());
		lvGroupProperty.setStoreId(lvGroupBuy.getStoreId());
		dao.save(lvGroupProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.save() method end*****");
		}
		return null;
	}

    /**
     * 
     * @Method: update 
     * @Description:  [更新团购扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:54:21]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:54:21]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.update() method begin*****");
		}
		LvGroupProperty lvGroupProperty=(LvGroupProperty) dto.get("lvGroupProperty");
		dao.update(lvGroupProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.update() method end*****");
		}
	}
	
    /**
     * 
     * @Method: get 
     * @Description:  [查询团购扩展属性信息详情]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:54:40]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:54:40]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public LvGroupProperty get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.get() method begin*****");
		}
		LvGroupProperty lvGroupProperty=(LvGroupProperty) dto.get("lvGroupProperty");
		lvGroupProperty=(LvGroupProperty) dao.load(LvGroupProperty.class, lvGroupProperty.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.get() method end*****");
		}
		return lvGroupProperty;
	}

}
