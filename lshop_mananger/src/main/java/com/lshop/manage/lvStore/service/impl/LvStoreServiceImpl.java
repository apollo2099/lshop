/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStore.service.impl;
import java.util.ArrayList;
import java.util.Date;
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
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityLink;
import com.lshop.common.pojo.logic.LvContent;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPaymentData;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreAddress;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplDetailPublic;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.pojo.logic.LvTplModelPublic;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvStore.service.LvStoreService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvStoreService")
public class LvStoreServiceImpl extends BaseServiceImpl implements LvStoreService {

	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	public List<LvStore> findAllStore(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String hql="from LvStore t where isdel=0";
		if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
		    hql += " and storeFlag in("+storeList+")";
		}
		hql+=" order by storeFlag desc";
		return dao.find(hql, null);
	}

	
	/**
	 * 获得所有(无店铺标识，包含商城)
	 */
	public List<LvStore> getAll(Dto dto) throws ServiceException{
		StringBuilder hql = new StringBuilder("from LvStore t where isdel=0");
		hql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"), "storeFlag", "t"));
		return dao.find(hql.toString(), null);
	}
	
	/**
	 * 获得所有(无店铺标识，不包含商城)
	 */
	public List<LvStore> getAllNoShop(Dto dto) throws ServiceException{
		String currency=(String) dto.get("currency");
		Map<String ,Object> param=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvStore t where isdel=0");
		if(ObjectUtils.isNotEmpty(currency)){
			hql.append(" and currency=:currency");
			param.put("currency", currency);
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeFlag", "t"));
		return dao.find(hql.toString(), param);
	}
	
	public List<LvStore> getShopList(Dto dto)throws ServiceException{
		String hql="from LvStore t where isdel=0 and parentCode='0'";
		return dao.find(hql, null);
	}
	
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvStore lvStore = (LvStore)dto.get("model");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvStore t where isdel=0 and t.parentCode<>'0'");
        if(lvStore!=null){
        	if(ObjectUtils.isNotEmpty(lvStore.getNumber())) {
	        	sql.append(" and  t.number like :number ");
	        	params.put("number","%"+lvStore.getNumber().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvStore.getName())) {
	        	sql.append(" and  t.name like :name ");
	        	params.put("name","%"+lvStore.getName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvStore.getStoreFlag())) {
	        	sql.append(" and  t.storeFlag =:storeFlag");
	        	params.put("storeFlag",lvStore.getStoreFlag());
	        }
        	if(ObjectUtils.isNotEmpty(lvStore.getCountryId())){
        		sql.append(" and t.countryId=:countryId ");
        		params.put("countryId",lvStore.getCountryId());
        	}
        	if(ObjectUtils.isNotEmpty(lvStore.getCity())){
        		sql.append(" and t.city like :city ");
        		params.put("city","%"+lvStore.getCity().trim()+"%");
        	}
        	if(ObjectUtils.isNotEmpty(lvStore.getStatus())){
        		sql.append(" and t.status=:status");
        		params.put("status",lvStore.getStatus());
        	}
        	if(ObjectUtils.isNotEmpty(lvStore.getIsHot())){
        		sql.append(" and t.isHot=:isHot ");
        		params.put("isHot",lvStore.getIsHot());
        	}
        	if(ObjectUtils.isNotEmpty(lvStore.getIsCommend())){
        		sql.append(" and t.isCommend=:isCommend ");
        		params.put("isCommend",lvStore.getIsCommend());
        	}
        	if(ObjectUtils.isNotEmpty(lvStore.getParentCode())){
        		sql.append(" and t.parentCode=:parentCode");
        		params.put("parentCode",lvStore.getParentCode());
        	}
        	
        }
		//判断当前是商城入口，还是商家入口
		sql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeFlag", "t"));
        sql.append(" order by t.orderValue desc,t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public Pagination getList(Dto dto) throws ServiceException{
		SimplePage page = (SimplePage)dto.get("page");
		LvStore lvStore = (LvStore)dto.get("model");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		StringBuilder sql = new StringBuilder(
				"select t from LvStore t where isdel=0 ");
		if (lvStore != null) {
			if (ObjectUtils.isNotEmpty(lvStore.getName())) {
				sql.append(" and  t.name like '%" + lvStore.getName().trim()+ "%'");
			}
			if (ObjectUtils.isNotEmpty(lvStore.getCountryId())) {
				sql.append(" and t.countryId=" + lvStore.getCountryId() + "");
			}
			if (ObjectUtils.isNotEmpty(lvStore.getCity())) {
				sql.append(" and t.city='" + lvStore.getCity().trim() + "'");
			}
			if (ObjectUtils.isNotEmpty(lvStore.getStatus())) {
				sql.append(" and t.status=" + lvStore.getStatus() + "");
			}
			if (ObjectUtils.isNotEmpty(lvStore.getCityCode())){
				sql.append(" and t.cityCode='"+lvStore.getCityCode()+"'");
			}
		}
		if (lvOrder != null) {
			if (ObjectUtils.isNotEmpty(lvOrder.getStoreId())) {
				sql.append(" and  t.storeFlag not in('" + lvOrder.getStoreId()+ "') ");
			}
		}
		//判断当前是商城入口，还是商家入口
		String storeListString=""; 
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			String [] arr=dto.getAsString("flag").split(",");
			String temp="";
			for (int i = 0; i < arr.length; i++) {
				if (ObjectUtils.isNotEmpty(arr[i])) {
					for(Map.Entry<String, String> entry:Constants.STORE_LIST_ALL.entrySet()){   
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
				sql.append(" and t.storeFlag in ("+storeListString+")");
			}else{
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				sql.append(" and t.storeFlag in("+storeList+")");
			}
		}
        sql.append(" order by t.orderValue desc,t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	public LvStore get(Dto dto) throws ServiceException {
		 LvStore lvStore = (LvStore)dto.get("model");
		 lvStore = (LvStore)dao.load(LvStore.class, lvStore.getId());
		return  lvStore;
	}

	public void del(Dto dto) throws ServiceException {
		 LvStore lvStore = (LvStore)dto.get("model");
		 dao.update("update LvStore set isdel=1 where id="+lvStore.getId(), null);
		 Constants.STORE_IDS.remove(lvStore.getDomainName());//移除域名标志
	}

	public void delList(Dto dto) throws ServiceException {
	}
    /**
     * 新建店铺，设置模板，并添加支付方式,并返回店铺模板对象
     */
	public LvTplModel  save(Dto dto) throws ServiceException {
		 LvStore lvStore = (LvStore)dto.get("model");
		 
		 
		 dao.save( lvStore);
		 LvTplModelPublic lvTplModelPublic=(LvTplModelPublic)dto.get("lvTplModelPublic");
         
		 LvTplModelPublic modelPublicData=(LvTplModelPublic)dao.load(LvTplModelPublic.class, lvTplModelPublic.getId());//获取仅有模板
		 /**复制到对应的店铺模板里 **/
		 LvTplModel  lvTplModel=new LvTplModel();
		 lvTplModel.setCode(CodeUtils.getCode());
		 lvTplModel.setIsDefault((short)1);
		 lvTplModel.setCreateTime(new Date());
		 lvTplModel.setModelName(modelPublicData.getModelName());
		 lvTplModel.setStoreFlag(lvStore.getStoreFlag());//设置店铺标志
		 lvTplModel.setCode(CodeUtils.getCode());
		 Integer lvTplModelId=(Integer)dao.save(lvTplModel);
		 //设置支付方式
		 Finder finder =Finder.create("from LvPaymentStyle where storeFlag='"+lvStore.getStoreFlag()+"'");
		 Integer result= (Integer)dao.countQueryResult(finder, null);
		 if(result==0){
		 List <LvPaymentData>  list =dao.find("from LvPaymentData ", null);
		 if(list!=null&&list.size()>0){
			 for(LvPaymentData data:list){
			 LvPaymentStyle payment=new LvPaymentStyle();
			 payment.setPayName(data.getPayName());
			 payment.setPayValue(data.getPayValue());
			 payment.setIsActivity(0);
			 payment.setOrderValue(0);
			 payment.setPayType(0);
			 payment.setStoreFlag(lvStore.getStoreFlag());
			 payment.setCreateTime(new Date());
			 dao.save(payment);//设置支付方式
			 }
		 }
		 }
		 //把对应的模板块明细，写到对应的店铺模板明细中
		 List<LvTplDetailPublic> detailPublicList=(List)dao.find(" from LvTplDetailPublic where tplModelCode='"+modelPublicData.getCode()+"'", null);
		 //copy 对应公有模板的明细
		 if(detailPublicList!=null&&detailPublicList.size()>0){
		 for(LvTplDetailPublic detailPublic :detailPublicList){
			 LvTplDetail tplDetail=new LvTplDetail();
			 tplDetail.setName(detailPublic.getName());
			 tplDetail.setIsdel(0);
			 tplDetail.setTplModelCode(lvTplModel.getCode());
			 tplDetail.setStoreFlag(lvStore.getStoreFlag());
			 tplDetail.setContent(detailPublic.getContent());
			 tplDetail.setPagePath(detailPublic.getPagePath());
			 tplDetail.setCreateTime(new Date());
			 dao.save(tplDetail);
			 
		 }	 
		 }

		 //修改商城币种信息
		 this.updateParentCurrency(lvStore.getParentCode(), lvStore.getCurrency());
		 return   lvTplModel;
	}
   
	/**
	 * 更新
	 */
	public LvStore update(Dto dto) throws ServiceException {
		 LvStore lvStore = (LvStore)dto.get("lvStore");
		 if(lvStore!=null){
			 dao.update(lvStore);
		 }
		 
		 //修改商城币种信息
		 this.updateParentCurrency(lvStore.getParentCode(), lvStore.getCurrency());
		//修改商城所对应的店铺币种
		 Map param=new HashMap();
	     hql=" update LvStore set currency=:currency where parentCode=:parentCode";
		 param.put("currency", lvStore.getCurrency());
		 param.put("parentCode", lvStore.getCode());
		 dao.update(hql, param);
		 return  lvStore;
	}


	
	
	/**
	 * 检查商铺标志是否重复
	 */
	@Override
	public Boolean checkStoreFlag(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvStore lvStore = (LvStore)dto.get("model");
		Map map =new HashMap();
		map.put("storeFlag", lvStore.getStoreFlag());
//		Object obj=dao.findUnique("from LvStore where storeFlag=:storeFlag and isdel=0", map);
		Object obj=dao.findUnique("from LvStore where storeFlag=:storeFlag ", map);
		return obj!=null?true:false;
	}

	/**
	 * 检查商铺域名是否重复
	 */
	@Override
	public Boolean checkDomain(Dto dto) throws ServiceException {
		LvStore lvStore = (LvStore)dto.get("model");
		Map map =new HashMap();
		map.put("domainName", lvStore.getDomainName());
		Object obj=dao.findUnique("from LvStore where domainName=:domainName and isdel=0", map);
		return obj!=null?true:false;
	}
	
	/**
	 * 检查商铺的商铺编号是否重复
	 */
	public Boolean checkStoreNumber(Dto dto)throws ServiceException{
		LvStore lvStore = (LvStore)dto.get("model");
		Map map =new HashMap();
		map.put("number", lvStore.getNumber());
		Object obj=dao.findUnique("from LvStore where number=:number and isdel=0", map);
		return obj!=null?true:false;
	}
	/**
	 * 检查店铺名称是否重复
	 */
	public Boolean checkStoreName(Dto dto)throws ServiceException{
		LvStore lvStore = (LvStore)dto.get("model");
		Map map =new HashMap();
		map.put("name", lvStore.getName());
		Object obj=dao.findUnique("from LvStore where name=:name and isdel=0", map);
		return obj!=null?true:false;
	}
	
	/**
	 * 根据店铺标志获取店铺
	 */
	@Override
	public LvStore findStore(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String storeFlag = dto.getAsString("storeFlag");
		Map map =new HashMap();
		map.put("storeFlag", storeFlag);
		LvStore lvStore=(LvStore)dao.findUnique("from LvStore where storeFlag=:storeFlag and isdel=0", map);
		return lvStore;
	}
	/**
	 * 根据店铺code获取店铺
	 */
	public LvStore findStoreByCode(Dto dto) throws ServiceException {
		String parentCode=(String) dto.get("parentCode");
		LvStore lvStore=(LvStore)dao.findUnique("from LvStore where code='"+parentCode+"' and isdel=0", null);
		return lvStore;
	}
	/**
	 * 设置店铺区域
	 */
	public Boolean updateStoreArea(Dto dto)throws ServiceException{
		LvStore lvStore = (LvStore)dto.get("model");
		String ids=(String) dto.get("ids");
		Map param =new HashMap();
		String hql="update LvStore set countryId=:countryId,country=:country,city=:city, " +
				" continentCode=:continentCode,countryCode=:countryCode,cityCode=:cityCode" +
				" where id in("+ids+") ";
		param.put("countryId", lvStore.getCountryId());
		param.put("country", lvStore.getCountry());
		param.put("city", lvStore.getCity());
		param.put("continentCode", lvStore.getContinentCode());
		param.put("countryCode", lvStore.getCountryCode());
		param.put("cityCode", lvStore.getCityCode());
		dao.update(hql, param);
		return true;
	}
	/**
	 * 设置热门店铺
	 */
	public Boolean updateStoreHot(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		LvStore lvStore = (LvStore)dto.get("model");
		Map param =new HashMap();
		String hql="update LvStore set isHot=:isHot where id in("+ids+") ";
		param.put("isHot", lvStore.getIsHot());
		dao.update(hql, param);
		return true;
	}
	/**
	 * 设置推荐店铺
	 */
	public Boolean updateStoreCommend(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		LvStore lvStore = (LvStore)dto.get("model");
		Map param =new HashMap();
		String hql="update LvStore set isCommend=:isCommend where id in("+ids+") ";
		param.put("isCommend", lvStore.getIsCommend());
		dao.update(hql, param);
		return true;
	}
	/**
	 * 更新店铺状态
	 */
	public Boolean updateStoreStatus(Dto dto)throws ServiceException{		
		LvStore lvStore = (LvStore)dto.get("model");
		Map param =new HashMap();
		String hql="update LvStore set status=:status where id=:id ";
		param.put("status", lvStore.getStatus());
		param.put("id", lvStore.getId());
		dao.update(hql, param);
		return true;
	}
	/**
	 * 修改排序值
	 */
	public Boolean updateOrderValue(Dto dto)throws ServiceException{
		LvStore lvStore = (LvStore)dto.get("model");
		Map param =new HashMap();
		String hql="update LvStore set orderValue=:orderValue where id=:id ";
		param.put("orderValue", lvStore.getOrderValue());
		param.put("id", lvStore.getId());
		dao.update(hql, param);
		return true;
	}
	
	/**
	 * 刷新店铺编号
	 */
	public Boolean updateStoreNumber(Dto dto)throws ServiceException{
		LvStore lvStore = (LvStore)dto.get("model");
		lvStore=(LvStore) dao.findUniqueByProperty(LvStore.class, "storeFlag", lvStore.getStoreFlag());
		String number=String.format("%05d", lvStore.getId());
		Map param =new HashMap();
		hql="update LvStore set number=:number where id=:id";
		param.put("number", number);
		param.put("id", lvStore.getId());
		dao.update(hql, param);
		return true;
	}
	
	/**
	 * 更新所有店铺域名
	 */
	@Override
	public Integer updateStoreIds(Dto dto)throws ServiceException{
		// TODO Auto-generated method stub
		Constants.STORE_IDS.clear();
		Constants.STORE_LIST.clear();
		Constants.STORE_LIST_ALL.clear();
		Constants.STORE_TO_MALL_SYSTEM.clear();
		Constants.STORE_FLAG_TO_IDS.clear();
		String hql="SELECT domainName,storeFlag,mallSystem,id,currency FROM LvStore WHERE isdel=0 and status=1";
		List list= dao.find(hql, null);
		if(list!=null&&!list.isEmpty()){
			for (Object object : list) {
				String storeListString="";
				Object[]strs=(Object[])object;
				Constants.STORE_IDS.put(strs[0].toString().trim(), strs[1].toString().trim());
				if(ObjectUtils.isNotEmpty(strs[2])){
					Constants.STORE_TO_MALL_SYSTEM.put(strs[1].toString().trim(), strs[2].toString().trim());
				}
				if(ObjectUtils.isNotEmpty(strs[3])){
					Constants.STORE_FLAG_TO_IDS.put(strs[1].toString().trim(), Integer.parseInt(strs[3].toString().trim()));
				}
				if(ObjectUtils.isNotEmpty(strs[4])){
					Constants.STORE_TO_CURRENCY.put(strs[1].toString().trim(), strs[4].toString().trim());
				}
				//根据店铺父级关系查询商城下面的店铺信息
				hql="from LvStore where isdel=0 and status=1 and parentCode in (select code from LvStore where isdel=0 and parentCode='0' and storeFlag=:storeFlag)";
				Map param=new HashMap();
				param.put("storeFlag", strs[1]);
				List storeList=dao.find(hql, param);
				if(storeList!=null&&!storeList.isEmpty()){
					for (int i = 0; i < storeList.size(); i++) {
						LvStore lvStore=(LvStore) storeList.get(i);
						if(lvStore!=null){
							if(ObjectUtils.isNotEmpty(lvStore.getStoreFlag())){
								if(i==storeList.size()-1){
									storeListString+="'"+lvStore.getStoreFlag()+"'";
								}else{
									storeListString+="'"+lvStore.getStoreFlag()+"',";
								}
							}
						}
					}
					Constants.STORE_LIST.put(strs[1].toString().trim(), storeListString);
				}
				
				//根据店铺父级关系查询商城下面的店铺信息
				storeListString="";
				hql="from LvStore where isdel=0 and parentCode in (select code from LvStore where isdel=0 and parentCode='0' and storeFlag=:storeFlag)";
				List storeAllList=dao.find(hql, param);
				if(storeAllList!=null&&!storeAllList.isEmpty()){
					for (int i = 0; i < storeAllList.size(); i++) {
						LvStore lvStore=(LvStore) storeAllList.get(i);
						if(lvStore!=null){
							if(ObjectUtils.isNotEmpty(lvStore.getStoreFlag())){
								if(i==storeAllList.size()-1){
									storeListString+="'"+lvStore.getStoreFlag()+"'";
								}else{
									storeListString+="'"+lvStore.getStoreFlag()+"',";
								}
							}
						}
					}
					Constants.STORE_LIST_ALL.put(strs[1].toString().trim(), storeListString);
				}
			}
		}
		sendJSONToWeb();
		return 1;
	}
	
	
	protected void sendJSONToWeb() {
		Map param = new HashMap();
		param.put("rateNum", Constants.rateNum);//美元兑人民币汇率信息
		param.put("rateNumCNY", Constants.rateNumCNY);//人民币兑美元汇率信息
		param.put("STORE_IDS", Constants.STORE_IDS);
		param.put("STORE_LIST", Constants.STORE_LIST);
		param.put("STORE_LIST_ALL", Constants.STORE_LIST_ALL);
		param.put("STORE_TO_MALL_SYSTEM", Constants.STORE_TO_MALL_SYSTEM);
		param.put("STORE_FLAG_TO_IDS", Constants.STORE_FLAG_TO_IDS);
		param.put("MALL_TO_DOMAIN_POSTFIX", Constants.MALL_TO_DOMAIN_POSTFIX);
		param.put("MALL_SYSTEM_TO_STORE", Constants.MALL_SYSTEM_TO_STORE);
		param.put("STORE_TO_CURRENCY", Constants.STORE_TO_CURRENCY);
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
	}
	
	/**
	 * 
	 * @Method: getParentStore 
	 * @Description:  [根据店铺标识获取父类关系]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-4-1 下午05:08:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-4-1 下午05:08:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvStore getParentStore(Dto dto)throws ServiceException{
		String storeFlag=dto.getAsString("storeFlag");
		String hql="from LvStore where code in(select parentCode from LvStore where storeFlag='"+storeFlag+"')";
		return (LvStore) dao.findUnique(hql, null);
	}
	
	public List<LvStore> findStoreByParent(Dto dto)throws ServiceException{
		String parentCode=(String) dto.get("parentCode");
		String hql="from LvStore where isdel=0 and parentCode=:parentCode";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("parentCode", parentCode);
		List<LvStore> list= dao.find(hql, param);
		return list;
	}
	
	
	/**
	 * 
	 * @Method: updateParentCurrency 
	 * @Description:  [修改商城币种信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-18 下午5:01:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-18 下午5:01:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param parentCode 商城code
	 * @param currency  币种
	 * @return void
	 */
	public void updateParentCurrency(String parentCode,String currency){
		String hql=" update LvStore set currency=:currency where code=:code";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("currency", currency);
		param.put("code", parentCode);
		dao.update(hql, param);
	}
	
	
}
