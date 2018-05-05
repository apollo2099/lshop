package com.lshop.web.tvpad.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.web.tvpad.service.IndexService;

/**
 * 店铺模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
@Service("IndexService")
public class IndexServiceImpl implements IndexService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	
	private static final Log logger=LogFactory.getLog(IndexServiceImpl.class);
	
	/**
	 * 获取店铺信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvStore getStore(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** IndexServiceImpl.getStore() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql="from LvStore where storeFlag=:storeFlag and isdel=0";
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		List list=lvlogicReadDao.find(hql, param);
		
		LvStore lvStore=null;
		if(null!=list && list.size()>0){
			lvStore=(LvStore)list.get(0);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** IndexServiceImpl.getStore() method end*****");
		}	
		return lvStore;
	}
	
	/**
	 * 获取国家及关联省列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto getAreas(Dto dto) throws ServiceException{
		
		List<Object[]> objList = new ArrayList<Object[]>();  //一个国家对应多个省 0，国家   1，省列表
		String hql = "";
		HashMap param = new HashMap();
		
		//获取国家
		hql = "from LvArea where parentid is null";
		List<LvArea> countryList = (List<LvArea>)lvlogicReadDao.find(hql, null);
		
		//为每个国家封装对应的省
		if(null!=countryList && countryList.size()>0){
			for(LvArea country : countryList){
				Object obj[] = new Object[2]; //0，国家   1，省列表
				hql = "from LvArea where parentid=:parentid";
				param.put("parentid", country.getId());
				List<LvArea> provinceList = (List<LvArea>)lvlogicReadDao.find(hql, param);
				obj[0] = country;
				obj[1] = provinceList;
				objList.add(obj);
			}
		}
		
		dto.put("objList", objList);
		return dto;
	}
	
	/**
	 * 获取国家及关联省列表（如果是中国则显示中文，其他显示英文）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto getCnAreas(Dto dto) throws ServiceException{
		
		String hql = "";
		HashMap param = new HashMap();
		
		//获取国家
		hql = "from LvArea where parentid is null";
		List<LvArea> countryList = (List<LvArea>)lvlogicReadDao.find(hql, null);
		
		
		//为每个国家封装对应的省
		List<Object[]> country_provinceList = new ArrayList<Object[]>(); //一个国家对应多个省 0，国家   1，省列表
		if(null!=countryList && countryList.size()>0){
			for(LvArea country : countryList){
				//获取国家对应的省
				hql = "from LvArea where parentid=:parentid";
				param.put("parentid", country.getId());
				List<LvArea> provinceList = (List<LvArea>)lvlogicReadDao.find(hql, param);
				
				//为每个省封装对应的市
				List<Object[]> province_cityList = new ArrayList<Object[]>();//一个省对应多个市  0，省  1，市列表
				if(null!=provinceList && provinceList.size()>0){
					for(LvArea province : provinceList){
						//获取省对应的市
						hql = "from LvArea where parentid=:parentid";
						param.put("parentid", province.getId());
						List<LvArea> cityList = (List<LvArea>)lvlogicReadDao.find(hql, param);
						
						Object ob[] = new Object[2]; //0，省  1，市列表
						ob[0] = province;
						ob[1] = cityList;
						province_cityList.add(ob);
					}
				}
				
				//国家如果是中国时，则给英文字段赋予中文语言
				if(country.getId()==100023){
					//给国家赋予中文
					country.setNameen(country.getNamecn());
					//给对应的省赋予中文
					if(null!=provinceList && provinceList.size()>0){
						for(LvArea province : provinceList){
							province.setNameen(province.getNamecn());
						}
					}
				}
				
				Object obj[] = new Object[2]; //0，国家   1，省列表
				obj[0] = country;
				obj[1] = province_cityList;
				country_provinceList.add(obj);
			}
		}
			
		dto.put("objList", country_provinceList);
		return dto;
	}

}
