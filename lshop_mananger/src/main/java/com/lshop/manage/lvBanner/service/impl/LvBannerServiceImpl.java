/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvBanner.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBanner;
import com.lshop.manage.lvBanner.service.LvBannerService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvBannerService")
public class LvBannerServiceImpl extends BaseServiceImpl implements LvBannerService {

	@Resource private HibernateBaseDAO dao;
	public List<LvBanner> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvBanner lvBanner = (LvBanner)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvBanner t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvBanner.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvBanner.getBannerKey())) {
		        	sql.append(" and  t.bannerKey like :bannerKey ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getTitle())) {
		        	sql.append(" and  t.title like :title ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getBody())) {
		        	sql.append(" and  t.body like :body ");
		        }
		        if(ObjectUtils.isNotEmpty(lvBanner.getOrderValue())) {
		        	sql.append(" and  t.orderValue = :orderValue ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvBanner.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(lvBanner.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvBanner.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getAtrr())) {
		        	sql.append(" and  t.atrr like :atrr ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getAtrr1())) {
		        	sql.append(" and  t.atrr1 like :atrr1 ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getAtrr2())) {
		        	sql.append(" and  t.atrr2 like :atrr2 ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvBanner.getAtrr3())) {
		        	sql.append(" and  t.atrr3 like :atrr3 ");
		        }
                Map parms = BeanUtils.describeForHQL(lvBanner);
                sql.append(" order by t.orderValue desc");
      
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);

		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvBanner get(Dto dto) throws ServiceException {
		 LvBanner lvBanner = (LvBanner)dto.get("model");
		 lvBanner = (LvBanner)dao.load(LvBanner.class, lvBanner.getId());
		return  lvBanner;
	}

	public void del(Dto dto) throws ServiceException {
		 LvBanner lvBanner = get(dto);
		dao.delete(  lvBanner);
	}

	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}

	public LvBanner save(Dto dto) throws ServiceException {
		 LvBanner lvBanner = (LvBanner)dto.get("model");
		dao.save( lvBanner);
		return   lvBanner;
	}

	public LvBanner update(Dto dto) throws ServiceException {
		 LvBanner lvBanner = (LvBanner)dto.get("model");
		dao.update(lvBanner);
		return  lvBanner;
	}

}
