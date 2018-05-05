package com.lshop.manage.lvApp.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvApp.service.LvAppService;


@Service("LvAppService")
public class LvAppServiceImpl implements LvAppService{
	private static final Log logger	= LogFactory.getLog(LvAppServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品应用信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:10:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:10:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.getList() method begin*****");
		}
		
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvApp lvApp=(LvApp) dto.get("lvApp");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvApp t where 1=1 ");
	    if(lvApp!=null){
	    	if(ObjectUtils.isNotEmpty(lvApp.getId())){
	    		hql.append(" and id=:id");
	    	}
	    	if(ObjectUtils.isNotEmpty(lvApp.getName())){
	    		hql.append(" and name like :name");	
	    	}
            if(ObjectUtils.isNotEmpty(lvApp.getVersion())){
            	hql.append(" and version like :version");
	    	}
            params=BeanUtils.describeForHQL(lvApp);
	    }
	    hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
//	    hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			hql.append(" order by "+ orderField+" "+orderDirection);
		}
	
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除产品应用信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-4 上午11:11:28]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-4 上午11:11:28]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.delete() method begin*****");
		}
		LvApp lvApp=(LvApp) dto.get("lvApp");
		dao.delete(lvApp);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:11:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:11:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.save() method begin*****");
		}
		LvApp lvApp=(LvApp) dto.get("lvApp");
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			lvApp.setStoreId(dto.getAsString("flag"));
//		}else{
//			lvApp.setStoreId("tvpadcn");
//		}
		//判断该应用名称是否已经存在
		String hql="from LvApp where name=:name and storeId=:storeId";
		Map param=new HashMap();
		param.put("name", lvApp.getName());
		param.put("storeId", lvApp.getStoreId());
		LvApp app=(LvApp) dao.findUnique(hql, param);
		if(app!=null){
			return false;
		}
		dao.save(lvApp);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.save() method end*****");
		}
		return true;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [保存产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:12:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:12:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.update() method begin*****");
		}
		LvApp lvApp=(LvApp) dto.get("lvApp");
		if(!lvApp.getOldName().equals(lvApp.getName())){
			//判断该应用名称是否已经存在
			String hql="from LvApp where name=:name and storeId=:storeId";
			Map param=new HashMap();
			param.put("name", lvApp.getName());
			param.put("storeId", lvApp.getStoreId());
			LvApp app=(LvApp) dao.findUnique(hql, param);
			if(app!=null){
				return false;
			}
		}
		dao.update(lvApp);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.update() method end*****");
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品应用详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvApp get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.get() method begin*****");
		}
		LvApp lvApp=(LvApp) dto.get("lvApp");
		lvApp=(LvApp) dao.load(LvApp.class, lvApp.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAppServiceImpl.get() method end*****");
		}
		return lvApp;
	}
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [app图片文件上传]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-25 下午01:54:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-25 下午01:54:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public String upload(Dto dto){
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		String storeId="tvpadcn";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			storeId=dto.getAsString("flag");
		}

		String basepath=request.getRealPath("/web/"+storeId+"/res/images");//文件上传资源路径
		String imgName="app_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return "/res/images/"+imgName;
	}
}
