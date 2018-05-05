package com.lshop.manage.LvAccountAddress.service.impl;

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
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.LvAccountAddress.service.LvAccountAddressService;

/** 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.LvAccountAddress.service.impl.LvAccountAddressServiceImpl.java]  
 * @ClassName:    [LvAccountAddressServiceImpl]   
 * @Description:  [用户收货地址管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 下午06:16:48]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 下午06:16:48]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [华扬v2.0]
 */
@Service("LvAccountAddressService")
public class LvAccountAddressServiceImpl implements LvAccountAddressService {
	private static final Log logger	= LogFactory.getLog(LvAccountAddressServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:45:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:45:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.getList() method end*****");
		}
		String flag=dto.getAsString("flag");//店铺标识
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvAccountAddress lvAccountAddress=(LvAccountAddress)dto.get("lvAccountAddress");
		Map params=new HashMap();
		String hql="from LvAccountAddress s where EXISTS(select a from LvAccount a where s.relCode=a.code ";
		       hql+=StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"), "storeId", "a").toString();
			   hql+=")";
	    if(lvAccountAddress!=null){
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getRelName())){//联系人
	    		hql+=" and s.relName like :relName";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getContryName())){//国家
	    		hql+=" and s.contryName like :contryName";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getProvinceName())){//州/省
	    		hql+=" and s.provinceName like :provinceName";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getProvinceId())){
	    		hql+=" and s.provinceId=:provinceId";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getCityName())){//市
	    		hql+=" and s.cityName like :cityName";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getRelCode())){
	    		hql+=" and s.relCode like:relCode";
	    	}
	    	if(ObjectUtils.isNotEmpty(lvAccountAddress.getContryId())){
	    		hql+=" and s.contryId=:contryId";
	    	}
	    	params=BeanUtils.describeForHQL(lvAccountAddress);
	    	params.remove("addressList");
	    }
		
		if(!StringUtil.IsNullOrEmpty(orderField)&&!StringUtil.IsNullOrEmpty(orderDirection)){
			hql+=" order by s."+ orderField+" "+orderDirection;
		}
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
	
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:46:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:46:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.delete() method begin*****");
		}
		LvAccountAddress lvAccountAddress=(LvAccountAddress)dto.get("lvAccountAddress");
		dao.delete(lvAccountAddress);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.delete() method end*****");
		}
	}

	/**
	 * 
	 * @Method: get 
	 * @Description:  [查看用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:47:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:47:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvAccountAddress get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.get() method begin*****");
		}
		LvAccountAddress lvAccountAddress=(LvAccountAddress)dto.get("lvAccountAddress");
		lvAccountAddress=(LvAccountAddress) dao.load(LvAccountAddress.class, lvAccountAddress.getId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.get() method end*****");
		}
		return lvAccountAddress;
	}



	@Override
	public Dto save(Dto dto) throws ServiceException {
		LvAccountAddress lvAccountAddress=(LvAccountAddress)dto.get("lvAccountAddress");
		String flag=dto.getAsString("flag");//店铺标识
		if(ObjectUtils.isNotEmpty(flag)){
			lvAccountAddress.setStoreId(flag);
		}else{
			lvAccountAddress.setStoreId("tvpadcn");
		}
		dao.save(lvAccountAddress);
		return null;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:47:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:47:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method begin*****");
		}
		LvAccountAddress lvAccountAddress=(LvAccountAddress)dto.get("lvAccountAddress");
		dao.update(lvAccountAddress);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method end*****");
		}
		
	}

}
