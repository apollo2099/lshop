/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubProduct.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvPubProductService")
public class LvPubProductServiceImpl extends BaseServiceImpl implements LvPubProductService {
	 private static final Log logger	= LogFactory.getLog(LvPubProductServiceImpl.class);
	 @Resource private HibernateBaseDAO dao;
	 
	 /**
	 * 获得所有
	 */	
	public List<LvPubProduct> findAll(Dto dto) throws ServiceException {
		String hql="select t from LvPubProduct t where status=0";
		Map<String ,Object> param=new HashMap<String ,Object>();
		List<LvPubProduct> list= dao.find(hql, param);
		return list;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		LvPubProduct lvPubProduct = (LvPubProduct)dto.get("lvPubProduct");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvPubProduct t where status=0 ");
	        	if(ObjectUtils.isNotEmpty(lvPubProduct.getProductName())) {
		        	sql.append(" and  t.productName like :productName ");
		        	params.put("productName", "%"+lvPubProduct.getProductName().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvPubProduct.getProductModel())){
	        		sql.append(" and t.productModel like:productModel");
	        		params.put("productModel", "%"+lvPubProduct.getProductModel().trim()+"%");
	        	}
	        	if(ObjectUtils.isNotEmpty(lvPubProduct.getPcode())) {
		        	sql.append(" and  t.pcode like :pcode ");
		        	params.put("pcode", "%"+lvPubProduct.getPcode().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvPubProduct.getCode())){
	        		sql.append(" and  t.code like :code ");
		        	params.put("code", "%"+lvPubProduct.getCode().trim()+"%");
	        	}
	        	if(ObjectUtils.isNotEmpty(lvPubProduct.getPubProductCode())){
	        		sql.append(" and t.code not in (:pubProductCode)");
	        		params.put("pubProductCode", lvPubProduct.getPubProductCode());
	        	}
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubProductServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPubProduct get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.get() method begin*****");
		}
		LvPubProduct lvPubProduct = (LvPubProduct) dto.get("lvPubProduct");
		lvPubProduct = (LvPubProduct) dao.load(LvPubProduct.class,lvPubProduct.getId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.get() method end*****");
		}
		return lvPubProduct;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.del() method begin*****");
		}
		LvPubProduct lvPubProduct = (LvPubProduct)dto.get("lvPubProduct");
		String hql = "update LvPubProduct set status=:status where id=:id";
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", Short.parseShort("-1"));
		param.put("id", lvPubProduct.getId());
		dao.update(hql, param);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.del() method end*****");
		}
	}


	/**
	 * 保存
	 */
	public LvPubProduct save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.save() method begin*****");
		}
		LvPubProduct lvPubProduct = (LvPubProduct) dto.get("lvPubProduct");
		dao.save(lvPubProduct);

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.save() method begin*****");
		}
		return lvPubProduct;
	}
	/**
	 * 更新
	 */
	public LvPubProduct update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.update() method begin*****");
		}
		LvPubProduct lvPubProduct = (LvPubProduct) dto.get("lvPubProduct");
		dao.update(lvPubProduct);

		if (logger.isInfoEnabled()) {
			logger.info("***** LvPubProductServiceImpl.update() method begin*****");
		}
		return lvPubProduct;
	}
	
	/**
	 * 
	 * @Method: findByProductCode 
	 * @Description:  [根据店铺code查询商品库信息(非套餐情况)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-5-9 下午2:14:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-5-9 下午2:14:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvPubProduct
	 */
	public LvPubProduct findByProductCode(String productCode)throws ServiceException{
		LvPubProduct pubProduct=null;
		if(ObjectUtils.isNotEmpty(productCode)){
			String hql="from LvPubProduct where code =(select pubProductCode from LvProduct where code=:code)";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("code", productCode);
			pubProduct= (LvPubProduct) dao.findUnique(hql, param);
		}
		return pubProduct;
	}
	
	/**
	 * 
	 * @Method: findByPcode 
	 * @Description:  [根据是商品库对接编码查询商品库信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-23 下午2:34:25]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-23 下午2:34:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param pcode
	 * @throws ServiceException 
	 * @return LvPubProduct
	 */
	public LvPubProduct findByPcode(String pcode)throws ServiceException{
		LvPubProduct pubProduct=null;
		if(ObjectUtils.isNotEmpty(pcode)){
			String hql="from LvPubProduct where status=0 and pcode=:pcode";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pcode", pcode);
			List<LvPubProduct> list= dao.find(hql, param);
			if(list!=null&&list.size()>0){
				pubProduct=list.get(0);
			}
		}
		return pubProduct;
	}

}
