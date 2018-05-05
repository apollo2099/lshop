package com.lshop.manage.lvProductLadder.service.impl;

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
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductLadder.service.LvProductLadderService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductLadder.service.impl.LvProductLadderServiceImpl.java]  
 * @ClassName:    [LvProductLadderServiceImpl]   
 * @Description:  [产品阶梯价-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-5 上午09:51:03]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-5 上午09:51:03]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductLadderService")
public class LvProductLadderServiceImpl implements LvProductLadderService {
	private static final Log logger	= LogFactory.getLog(LvProductLadderServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvProductService lvProductService;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品阶梯价信息列表]  
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
			logger.info("***** LvProductLadderServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvProductLadder t where 1=1 ");
        if(lvProductLadder!=null){
        	if(ObjectUtils.isNotEmpty(lvProductLadder.getProductCode())){
        		hql.append(" and productCode=:productCode");
        		params.put("productCode", lvProductLadder.getProductCode());
        	}
        }
        //店铺标识
//        hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
        //排序规则
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
     * @Description:  [删除产品阶梯价信息]  
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
			logger.info("***** LvProductLadderServiceImpl.delete() method begin*****");
		}
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		dao.delete(lvProductLadder);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品阶梯价信息]  
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
			logger.info("***** LvProductLadderServiceImpl.save() method begin*****");
		}
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		dto.put("productCode", lvProductLadder.getProductCode());
		List<LvProductLadder> list= this.getProductLadder(dto);
		//判断是否存在交错阶梯价
		for (int i = 0; i < list.size(); i++) {
			LvProductLadder ladder=list.get(i);
//			if((lvProductLadder.getUpInterval()>ladder.getUpInterval()&&lvProductLadder.getUpInterval()<ladder.getDownInterval())
//				||(lvProductLadder.getDownInterval()>ladder.getUpInterval()&&lvProductLadder.getDownInterval()<ladder.getDownInterval())){
//				return false;
//			}
			//上区间大于下区间，下区间少于上区间（求反）
			if(!(lvProductLadder.getUpInterval()>ladder.getDownInterval()||lvProductLadder.getDownInterval()<ladder.getUpInterval())){
				return false;
			}
		}
		
		
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvProductLadder.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=lvProductService.getProduct(dto);
		lvProductLadder.setStoreId(lvProduct.getStoreId());
		//保存阶梯价信息
		dao.save(lvProductLadder);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.save() method end*****");
		}
		return true;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [保存产品阶梯价信息]  
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
			logger.info("***** LvProductLadderServiceImpl.update() method begin*****");
		}
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		List<LvProductLadder> list=null;
		//判断如果修改的区间在原始区间内则不进行交错阶梯价判断
		if (!(lvProductLadder.getUpInterval()>=lvProductLadder.getOldUpInterval()
			  &&lvProductLadder.getDownInterval()<=lvProductLadder.getOldDownInterval())) {
			dto.put("id", lvProductLadder.getId());
			dto.put("productCode", lvProductLadder.getProductCode());
			list= this.getProductLadderNoId(dto);
			//判断是否存在交错阶梯价
			for (int i = 0; i < list.size(); i++) {
				LvProductLadder ladder=list.get(i);
//				if((lvProductLadder.getUpInterval()>=ladder.getUpInterval()&&lvProductLadder.getUpInterval()<=ladder.getDownInterval())
//					||(lvProductLadder.getDownInterval()>=ladder.getUpInterval()&&lvProductLadder.getDownInterval()<=ladder.getDownInterval())){
//					return false;
//				}	
				//上区间大于下区间，下区间少于上区间（求反）
				if(!(lvProductLadder.getUpInterval()>ladder.getDownInterval()||lvProductLadder.getDownInterval()<ladder.getUpInterval())){
					return false;
				}
			}
		}
		
		//保存阶梯价信息
		dao.update(lvProductLadder);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.update() method end*****");
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品扩展信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductLadder get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.get() method begin*****");
		}
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		lvProductLadder=(LvProductLadder) dao.load(LvProductLadder.class, lvProductLadder.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.get() method end*****");
		}
		return lvProductLadder;
	}
	/**
	 * 
	 * @Method: getProductLadder 
	 * @Description:  [根据产品code查询产品阶梯价信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-21 上午10:11:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-21 上午10:11:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProductLadder> getProductLadder(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.getProductLadder() method begin*****");
		}	
		List<LvProductLadder> list=null;
		String productCode=(String) dto.get("productCode");
		if (ObjectUtils.isNotEmpty(productCode)) {
			String hql = "from LvProductLadder where productCode='"+productCode+"'";
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and storeId in("+storeList+")";
			}
			list = dao.find(hql, null);
			
			if (logger.isInfoEnabled()){
				logger.info("***** LvProductLadderServiceImpl.getProductLadder() method end*****");
			}	
		}
		return list;
	}
	@Override
	public List<LvProductLadder> getProductLadderNoId(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductLadderServiceImpl.getProductLadder() method begin*****");
		}	
		List<LvProductLadder> list=null;
		Integer id=(Integer) dto.get("id");
		String productCode=(String) dto.get("productCode");
		if (ObjectUtils.isNotEmpty(productCode)) {
			String hql = "from LvProductLadder where productCode='"+productCode+"' and id<>"+id+"";
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and storeId in("+storeList+")";
			}
			list = dao.find(hql, null);
			
			if (logger.isInfoEnabled()){
				logger.info("***** LvProductLadderServiceImpl.getProductLadder() method end*****");
			}	
		}
		return list;
	}
	
	
}
