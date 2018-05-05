/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopProductType.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvShopProductType.service.LvShopProductTypeService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopProductTypeService")
public class LvShopProductTypeServiceImpl extends BaseServiceImpl implements LvShopProductTypeService {
	

	 @Resource private HibernateBaseDAO dao;
	 /**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage) dto.get("page");
		LvShopProductType lvShopProductType = (LvShopProductType) dto.get("model");
		// 组装SQL,时间类型的没组装，如果有需要自己添加SQL
		// 字符串类型为模糊查询，不区分大小写
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder sql = new StringBuilder("select t from LvShopProductType t where 1=1 ");
		if(lvShopProductType!=null){
			if (ObjectUtils.isNotEmpty(lvShopProductType.getTypeName())) {
				sql.append(" and  t.typeName like :typeName");
				params.put("typeName", "%"+lvShopProductType.getTypeName().trim()+"%");
			}
		}
		sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		sql.append(" order by t.orderValue desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		List listTmp=new ArrayList();
		List list=pag.getList();
		if(list!=null&&list.size()>0){
		   for (int i = 0; i < list.size(); i++) {
			   LvShopProductType lvs=(LvShopProductType) list.get(i);
			   if(lvs!=null){
				   if(ObjectUtils.isNotEmpty(lvs.getCode())){
					   LvShopProductType lv=new LvShopProductType();
					   Map map=new HashMap();
					   hql="from LvProduct p where p.shopProductType=:shopProductType and p.status<>-1 and p.isSupport=1";
					   map.put("shopProductType", lvs.getCode());
					   List listProduct=dao.find(hql, map);
					   lv.setId(lvs.getId());
					   lv.setShopProductCount(listProduct.size());
					   lv.setOrderValue(lvs.getOrderValue());
					   lv.setCreateTime(lvs.getCreateTime());
					   lv.setModifyTime(lvs.getModifyTime());
					   lv.setTypeName(lvs.getTypeName());
					   lv.setCode(lvs.getCode());
					   lv.setStoreId(lvs.getStoreId());
					   listTmp.add(lv);
				   }
			   }
		   }
		}
		pag.setList(listTmp);
		return pag;
	}
	 /**
	 * 获得所有
	 */
	public List<LvShopProductType> findAll(Dto dto) throws ServiceException {
		LvShopProductType lvShopProductType=(LvShopProductType) dto.get("lvShopProductType");
		String hql="select t from LvShopProductType t where 1=1";
		if(lvShopProductType!=null){
			if(ObjectUtils.isNotEmpty(lvShopProductType.getStoreId())){
				hql+="and storeId='"+lvShopProductType.getStoreId()+"'";
			}
		}
		return dao.find(hql, null);
	}
	
	/**
	 * 获得单独的实体信息
	 */
	public LvShopProductType get(Dto dto) throws ServiceException {
		 LvShopProductType lvShopProductType = (LvShopProductType)dto.get("model");
		 lvShopProductType = (LvShopProductType)dao.load(LvShopProductType.class, lvShopProductType.getId());
		return  lvShopProductType;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopProductType lvShopProductType = get(dto);
		dao.delete(  lvShopProductType);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvShopProductType save(Dto dto) throws ServiceException {
		LvShopProductType lvShopProductType = (LvShopProductType) dto
				.get("model");
		dao.save(lvShopProductType);

		return lvShopProductType;
	}
	/**
	 * 更新
	 */
	public LvShopProductType update(Dto dto) throws ServiceException {
		 LvShopProductType lvShopProductType = (LvShopProductType)dto.get("model");
		dao.update(lvShopProductType);
		return  lvShopProductType;
	}
	
	/**
	 * 
	 * @Method: IsExistType 
	 * @Description:  [判断当前商城分类名称是否存在重复添加]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-8 上午10:53:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-8 上午10:53:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean IsExistType(Dto dto)throws ServiceException{
		LvShopProductType lvShopProductType = (LvShopProductType)dto.get("model");
		String hql="from LvShopProductType where typeName=:typeName";
		Map param=new HashMap();
		param.put("typeName",lvShopProductType.getTypeName());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: IsExistShopProductType 
	 * @Description:  [判断商城分类下是否存在商品信息(非删除)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-8 上午10:53:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-8 上午10:53:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean IsExistShopProductType(Dto dto)throws ServiceException{
		LvShopProductType lvShopProductType = (LvShopProductType)dto.get("model");
		String hql="from LvProduct where shopProductType=:shopProductType and status<>-1";
		Map param=new HashMap();
		param.put("shopProductType",lvShopProductType.getCode());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}
	

}
