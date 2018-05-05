/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPaymentStyle.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvPaymentData;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvPaymentStyle.service.LvPaymentStyleService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvPaymentStyleService")
public class LvPaymentStyleServiceImpl extends BaseServiceImpl implements LvPaymentStyleService {
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvPaymentStyle> findAll(Dto dto) throws ServiceException {
//		String storeFlag=dto.getAsString("flag"); 

		
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        String hql=" from LvPaymentStyle t where  isActivity=:isActivity";
        Map map = new HashMap();
//        map.put("storeFlag", storeFlag);
        map.put("isActivity", 1);
        
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
				hql+=" and t.storeFlag in ("+storeListString+")";
			}else{
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and t.storeFlag in("+storeList+")";
			}
		}
		hql+=" order by  t.orderValue asc";
        List<LvPaymentStyle> list=dao.find(hql,map);
		return list;
	}
	
	public List<LvPaymentStyle> findAllByStoreFlag(Dto dto) throws ServiceException{
		  String storeFlag=dto.getAsString("storeFlag"); 
		  String hql=" from LvPaymentStyle t where  isActivity=:isActivity and storeFlag=:storeFlag";
	        Map map = new HashMap();
	        map.put("storeFlag", storeFlag);
	        map.put("isActivity", 1);
	        hql+=" order by  t.orderValue asc";
	        List<LvPaymentStyle> list=dao.find(hql,map);
			return list;
	}
	
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
	    LvPaymentStyle lvPaymentStyle = (LvPaymentStyle)dto.get("model");
	    Map<String,Object> paramMap=new HashMap<String, Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder(" from LvPaymentStyle t where 1=1 ");
        if(ObjectUtils.isNotEmpty(lvPaymentStyle)){
        	if(ObjectUtils.isNotEmpty(lvPaymentStyle.getStoreFlag())){
        		sql.append(" and storeFlag=:storeFlag");
        		paramMap.put("storeFlag", lvPaymentStyle.getStoreFlag());
        	}
        	if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPayName())){
        		sql.append(" and payName like :payName");
        		paramMap.put("payName", "%"+lvPaymentStyle.getPayName().trim()+"%");
        	}
        	if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPayValue())){
        		sql.append(" and payValue=:payValue");
        		paramMap.put("payValue", lvPaymentStyle.getPayValue());
        	}
        }
        sql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"), "storeFlag", "t"));
        sql.append("order by t.storeFlag desc, t.orderValue asc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(paramMap);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPaymentStyle get(Dto dto) throws ServiceException {
		 LvPaymentStyle lvPaymentStyle = (LvPaymentStyle)dto.get("model");
		 lvPaymentStyle = (LvPaymentStyle)dao.load(LvPaymentStyle.class, lvPaymentStyle.getId());
		return  lvPaymentStyle;
	}
	
	public List <LvPaymentData> findPaymentDataAll(Dto dto)throws ServiceException{
		
		return dao.find("from LvPaymentData", null);
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvPaymentStyle lvPaymentStyle = get(dto);
		//删除缓存
	   Map<String, Object> param = new HashMap<String, Object>();
	   param.put("storeId", lvPaymentStyle.getStoreFlag());
	   param.put("PAYMENT_CHANGE", lvPaymentStyle.getId().toString());
	   messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		dao.delete(  lvPaymentStyle);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public Integer save(Dto dto) throws ServiceException {//返回1表示成功，返回2表示支付方式已存在 
		  LvPaymentStyle lvPaymentStyle = (LvPaymentStyle)dto.get("model");
//		  String storeFlag=dto.getAsString("flag"); 
//		  lvPaymentStyle.setStoreFlag(storeFlag);
		  String hql="from LvPaymentStyle where payType=:payType and payValue=:payValue and storeFlag=:storeFlag ";
		  Map map = new HashMap();
		  map.put("storeFlag", lvPaymentStyle.getStoreFlag());
		  map.put("payType", lvPaymentStyle.getPayType());
		  map.put("payValue", lvPaymentStyle.getPayValue());
		  Object obj=dao.findUnique(hql, map);
		  if(obj!=null){
			  return 2;//表示该支付方式value值已存在 
		  }else{
		   dao.save(lvPaymentStyle);
		   //新建缓存
		   Map<String, Object> param = new HashMap<String, Object>();
		   param.put("storeId", lvPaymentStyle.getStoreFlag());
		   param.put("PAYMENT_CHANGE", lvPaymentStyle.getId().toString());
		   messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		  }
	return  1;
	}
	/**
	 * 更新
	 */
	public LvPaymentStyle update(Dto dto) throws ServiceException {
		 LvPaymentStyle lvPaymentStyle = (LvPaymentStyle)dto.get("model");
		 dao.update(lvPaymentStyle);
		 //修改缓存
		 Map<String, Object> param = new HashMap<String, Object>();
		 param.put("storeId", lvPaymentStyle.getStoreFlag());
		 param.put("PAYMENT_CHANGE", lvPaymentStyle.getId().toString());
		 messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
		return  lvPaymentStyle;
	}
	
	/**
	 * 
	 * @Method: findPaymentStyle 
	 * @Description:  [根据店铺标志和支付方式值查找支付信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-21 上午10:28:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-21 上午10:28:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvPaymentStyle
	 */
	public LvPaymentStyle findPaymentStyle(Dto dto)throws ServiceException{
		  LvPaymentStyle lvPaymentStyle=null;
		  String storeFlag=(String) dto.get("storeFlag");
		  Short payValue=(Short) dto.get("payValue");
		  String hql="from LvPaymentStyle where payValue=:payValue and storeFlag=:storeFlag ";
		  Map<String,Object> map = new HashMap<String,Object>();
		  map.put("storeFlag", storeFlag);
		  map.put("payValue", payValue);
		  lvPaymentStyle=(LvPaymentStyle) dao.findUnique(hql, map);
		  return lvPaymentStyle;
	}

}
