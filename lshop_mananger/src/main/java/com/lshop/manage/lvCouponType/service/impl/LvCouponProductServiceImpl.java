/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponType.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.lshop.common.pojo.logic.LvCouponProduct;
import com.lshop.manage.lvCouponType.service.LvCouponProductService;
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
 */@Service("LvCouponProductService")
public class LvCouponProductServiceImpl extends BaseServiceImpl implements LvCouponProductService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvCouponProduct> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvCouponProduct lvCouponProduct = (LvCouponProduct)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvCouponProduct t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvCouponProduct.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvCouponProduct.getCouponTypeCode())) {
		        	sql.append(" and  t.couponTypeCode like :couponTypeCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvCouponProduct.getRelationCode())) {
		        	sql.append(" and  t.relationCode like :relationCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvCouponProduct.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(lvCouponProduct.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvCouponProduct.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(lvCouponProduct);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvCouponProduct get(Dto dto) throws ServiceException {
		 LvCouponProduct lvCouponProduct = (LvCouponProduct)dto.get("model");
		 lvCouponProduct = (LvCouponProduct)dao.load(LvCouponProduct.class, lvCouponProduct.getId());
		return  lvCouponProduct;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvCouponProduct lvCouponProduct = get(dto);
		dao.delete(  lvCouponProduct);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvCouponProduct save(Dto dto) throws ServiceException {
		 LvCouponProduct lvCouponProduct = (LvCouponProduct)dto.get("model");
		dao.save( lvCouponProduct);
		return   lvCouponProduct;
	}
	/**
	 * 更新
	 */
	public LvCouponProduct update(Dto dto) throws ServiceException {
		 LvCouponProduct lvCouponProduct = (LvCouponProduct)dto.get("model");
		dao.update(lvCouponProduct);
		return  lvCouponProduct;
	}

}
