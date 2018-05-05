/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopProduct.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvShopProduct.service.LvShopProductService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopProductService")
public class LvShopProductServiceImpl extends BaseServiceImpl implements LvShopProductService {
	 
	 
		/**
		 * 分页查询
		 * @param dto
		 * @return
		 * @throws ServiceException
		 */
		public Pagination findPage(Dto dto) throws ServiceException {
			SimplePage page = (SimplePage)dto.get("page");
			 LvShopProduct lvShopProduct = (LvShopProduct)dto.get("model");
			//组装SQL,时间类型的没组装，如果有需要自己添加SQL
			//字符串类型为模糊查询，不区分大小写
	        StringBuilder sql = new StringBuilder("select t from LvShopProduct t where 1=1 ");
	        if(lvShopProduct!=null){
	        	if(ObjectUtils.isNotEmpty(lvShopProduct.getProductName())) {
		        	sql.append(" and  t.productName like '%"+lvShopProduct.getProductName().trim()+"%'");
		        }
	        	if(ObjectUtils.isNotEmpty(lvShopProduct.getSubjectType())) {
		        	sql.append(" and  t.subjectType='"+lvShopProduct.getSubjectType().trim()+"'");
		        }
	        }
	        
	        sql.append(" order by t.orderValue desc ");
			Finder finder = Finder.create(sql.toString());
			Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
			return pag;
		}
	 
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvShopProduct> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获得单独的实体信息
	 */
	public LvShopProduct get(Dto dto) throws ServiceException {
		 LvShopProduct lvShopProduct = (LvShopProduct)dto.get("model");
		 lvShopProduct = (LvShopProduct)dao.load(LvShopProduct.class, lvShopProduct.getId());
		return  lvShopProduct;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopProduct lvShopProduct = get(dto);
		dao.delete(  lvShopProduct);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public Boolean save(Dto dto) throws ServiceException {
		 LvShopProduct lvShopProduct = (LvShopProduct)dto.get("model");
		 String ids=(String) dto.get("ids");
		 if (ids != null && ids.length()> 0 ){
			 String[] temp_ids= ids.split(",");
			 for(int i=0; i<temp_ids.length; i++){
				 if(temp_ids[i].length()>0){
					 String hql="select p.id as id,p.shopProductType as shopProductType,p.productName as productName,p.code as productCode,p.pubProductCode as pubProductCode, " +
						" p.pimage as pimage,p.pimageAd as pimageAd,p.storeId as storeId,s.domainName as domainName,s.name as storeName,s.parentCode as parentCode " +
						" from LvProduct p,LvStore s where p.status<>-1 and p.isSupport<>0 and p.storeId=s.storeFlag and s.isdel=0 " +
						" and p.code='"+temp_ids[i].trim()+"'";
					 Pagination page= dao.getMapListByHql(hql, null);
					 List list=page.getList();
					 for(int k=0;k<list.size();k++){
						 Map map=(Map) list.get(k);
						 LvShopProduct shopProduct=new LvShopProduct();
						 //shopProduct.setProductName("["+String.valueOf(map.get("storeName"))+"]"+String.valueOf(map.get("productName")));
						 shopProduct.setProductName(String.valueOf(map.get("productName")));
						 shopProduct.setProductCode(String.valueOf(map.get("productCode")));
						 shopProduct.setSubjectType(lvShopProduct.getSubjectType());
						 shopProduct.setPimage(String.valueOf(map.get("pimage")));
						 shopProduct.setPimageAd(String.valueOf(map.get("pimageAd")));
						 
						 /**/
						 if(ObjectUtils.isNotEmpty(map.get("storeId"))){
							 if(String.valueOf(map.get("storeId")).equals("mbscn")||String.valueOf(map.get("storeId")).equals("mtscn")){
								 shopProduct.setUrl("http://"+String.valueOf(map.get("domainName"))+"/web/product!toProduct.action?pcode="+String.valueOf(map.get("productCode"))); 
							 }else{//移动端产品详情页面
								 shopProduct.setUrl("http://"+String.valueOf(map.get("domainName"))+"/products/"+String.valueOf(map.get("productCode"))+".html");
							 }
						 }
//						 shopProduct.setUrl("http://"+String.valueOf(map.get("domainName"))+"/web/product!toProduct.action?pcode="+String.valueOf(map.get("productCode")));
						 shopProduct.setOrderValue(0);
						 
						 LvStore lvStore= (LvStore) dao.findUniqueByProperty(LvStore.class, "code", String.valueOf(map.get("parentCode")));
						 shopProduct.setStoreFlag(lvStore.getStoreFlag());
						 shopProduct.setStoreId(String.valueOf(map.get("storeId")));
						 shopProduct.setCode(CodeUtils.getCode());
						 shopProduct.setCreateTime(new Date());
						 dao.save(shopProduct);
					 }	
				 }
			 }
		 }else{
			return false; 
		 }
		 return true;
	}
	/**
	 * 更新
	 */
	public LvShopProduct update(Dto dto) throws ServiceException {
		 LvShopProduct lvShopProduct = (LvShopProduct)dto.get("model");
		dao.update(lvShopProduct);
		return  lvShopProduct;
	}

}
