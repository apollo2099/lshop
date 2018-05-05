package com.lshop.manage.lvCarriageSet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvCarriageSet.service.LvCarriageSetService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvCarriageSet.service.impl.LvCarriageSetServiceImpl.java]  
 * @ClassName:    [LvCarriageSetServiceImpl]   
 * @Description:  [运费设置管理-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-3 上午11:54:54]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-3 上午11:54:54]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvCarriageSetService")
public class LvCarriageSetServiceImpl implements LvCarriageSetService {
	private static final Log logger	= LogFactory.getLog(LvCarriageSetServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询运费设置管理列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:54:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:54:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto){
		Pagination pageTmp = null;
			if (logger.isInfoEnabled()){
				logger.info("***** LvCarriageSetServiceImpl.getList() method begin*****");
			}
			
			String orderField = dto.getAsString("orderField");
			String orderDirection = dto.getAsString("orderDirection");
			SimplePage page = (SimplePage)dto.get("page");
			LvCarriageSet lvCarriageSet=(LvCarriageSet) dto.get("lvCarriageSet");
			Map<String ,Object> params=new HashMap<String ,Object>();
			StringBuilder hql = new StringBuilder("from LvCarriageSet t where 1=1 ");
			if(lvCarriageSet!=null){
				if(ObjectUtils.isNotEmpty(lvCarriageSet.getDeliveryId())){//配送地区编号
					hql.append(" and deliveryId=:deliveryId");
					params.put("deliveryId", lvCarriageSet.getDeliveryId());
				}
				if(ObjectUtils.isNotEmpty(lvCarriageSet.getStoreId())){
					hql.append(" and storeId=:storeId");
					params.put("storeId", lvCarriageSet.getStoreId());
				}
			}
			//店铺标识
			hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
			//排序规则
			if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
				hql.append(" order by "+ orderField+" "+orderDirection);
			}
			Finder finder = Finder.create(hql.toString());
			finder.setParams(params);

			pageTmp = dao.find(finder, page.getPageNum(), page.getNumPerPage());
			//数据包装
			List tempList=new ArrayList();
			List<LvCarriageSet> list= (List<LvCarriageSet>) pageTmp.getList();
			if(ObjectUtils.isNotEmpty(list)){
				for (LvCarriageSet cs : list) {
					List<LvArea> areaList= LvAreaCache.list;
					for (int i = 0; i < areaList.size(); i++) {
						LvArea area=areaList.get(i);
						if(cs.getDeliveryId().equals(area.getId())){
							System.out.println(area.getNamecn());
							cs.setDeliveryName(area.getNamecn());
							break;
						}
					}
					tempList.add(cs);
				}
			}
			pageTmp.setList(tempList);
		return pageTmp;
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除运费设置管理信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:54:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:54:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.delete() method begin*****");
		}
		LvCarriageSet lvCarriageSet=(LvCarriageSet) dto.get("lvCarriageSet");
		lvCarriageSet=(LvCarriageSet) dao.load(LvCarriageSet.class, lvCarriageSet.getId());
		//从缓存中更新
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("CARRIAGE_CHANGE", lvCarriageSet.getDeliveryId());
		param.put("storeId", lvCarriageSet.getStoreId());
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		dao.delete(lvCarriageSet);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.delete() method end*****");
		}
		
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [查询运费设置管理信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:53:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:53:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvCarriageSet get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.get() method begin*****");
		}
		LvCarriageSet lvCarriageSet=(LvCarriageSet) dto.get("lvCarriageSet");
		lvCarriageSet=(LvCarriageSet) dao.load(LvCarriageSet.class, lvCarriageSet.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.get() method end*****");
		}
		return lvCarriageSet;
	}
	/**
	 * 
	 * @Method: getCarriageSet 
	 * @Description:  [根据运费地区编号查询运费设置管理信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:53:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:53:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvCarriageSet getCarriageSet(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.getCarriageSet() method begin*****");
		}
		String storeId=(String) dto.get("storeId");
		Integer deliveryId=(Integer) dto.get("deliveryId");
		Map param=new HashMap();
		String hql="from LvCarriageSet where deliveryId=:deliveryId";
		if(ObjectUtils.isNotEmpty(storeId)){//店铺标示
			hql+=" and storeId=:storeId ";
			param.put("storeId", storeId);
		}
		param.put("deliveryId", deliveryId);
		List list=dao.find(hql, param);
		LvCarriageSet lvCarriageSet=null;
		if(list!=null&&list.size()>0){
			lvCarriageSet=(LvCarriageSet) list.get(0);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.getCarriageSet() method end*****");
		}
		return lvCarriageSet;
	}
	
	
    /**
     * 
     * @Method: save 
     * @Description:  [新增运费设置管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 上午11:53:12]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 上午11:53:12]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.save() method begin*****");
		}
		LvCarriageSet lvCarriageSet=(LvCarriageSet) dto.get("lvCarriageSet");
		Map<String ,Object> params=new HashMap<String ,Object>();
		if(lvCarriageSet!=null){
			if(ObjectUtils.isNotEmpty(lvCarriageSet.getDeliveryId())){
				String hql="from LvCarriageSet where deliveryId=:deliveryId and storeId=:storeId";
				params.put("deliveryId", lvCarriageSet.getDeliveryId());
				params.put("storeId", lvCarriageSet.getStoreId());
				LvCarriageSet lcs= (LvCarriageSet) dao.findUnique(hql, params);
				if(lcs!=null){
					return false;
				}
			}
		}
		

		dao.save(lvCarriageSet);
		//从缓存中更新
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("CARRIAGE_CHANGE", lvCarriageSet.getDeliveryId());
		param.put("storeId", lvCarriageSet.getStoreId());
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.save() method end*****");
		}
		return true;
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [更新运费设置管理信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:52:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:52:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public boolean update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.update() method begin*****");
		}
		LvCarriageSet lvCarriageSet=(LvCarriageSet) dto.get("lvCarriageSet");
		Map<String ,Object> params=new HashMap<String ,Object>();
		Integer areaId=(Integer) dto.get("areaId");
		if(lvCarriageSet!=null){
			if(ObjectUtils.isNotEmpty(lvCarriageSet.getDeliveryId())&&ObjectUtils.isNotEmpty(areaId)){
				if(!areaId.equals(lvCarriageSet.getDeliveryId())){
					String hql="from LvCarriageSet where deliveryId=:deliveryId and storeId=:storeId ";
					params.put("deliveryId", lvCarriageSet.getDeliveryId());
					params.put("storeId", lvCarriageSet.getStoreId());
					LvCarriageSet lcs= (LvCarriageSet) dao.findUnique(hql, params);
					if(lcs!=null){
						return false;
					}
				}
			}
		}
		
		dao.update(lvCarriageSet);
		//从缓存中更新
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("CARRIAGE_CHANGE", lvCarriageSet.getDeliveryId());
		param.put("storeId", lvCarriageSet.getStoreId());
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		if (logger.isInfoEnabled()){
			logger.info("***** LvCarriageSetServiceImpl.update() method end*****");
		}
		return true;
	}

}
