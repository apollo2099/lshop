/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivityLogs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lshop.manage.lvActivityLogs.service.LvActivityLogsService;
import com.lshop.common.pojo.logic.LvActivityLogs;
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
 */@Service("LvActivityLogsService")
public class LvActivityLogsServiceImpl extends BaseServiceImpl implements LvActivityLogsService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvActivityLogs> findAll(Dto dto) throws ServiceException {
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
		 LvActivityLogs lvActivityLogs = (LvActivityLogs)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvActivityLogs t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvActivityLogs.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvActivityLogs.getActivityCode())) {
		        	sql.append(" and  t.activityCode like :activityCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvActivityLogs.getOrderId())) {
		        	sql.append(" and  t.orderId like :orderId ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvActivityLogs.getProductInfo())) {
		        	sql.append(" and  t.productInfo like :productInfo ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvActivityLogs.getActors())) {
		        	sql.append(" and  t.actors like :actors ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvActivityLogs.getActorsIp())) {
		        	sql.append(" and  t.actorsIp like :actorsIp ");
		        }
        Map parms = BeanUtils.describeForHQL(lvActivityLogs);
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
	public LvActivityLogs get(Dto dto) throws ServiceException {
		 LvActivityLogs lvActivityLogs = (LvActivityLogs)dto.get("model");
		 lvActivityLogs = (LvActivityLogs)dao.load(LvActivityLogs.class, lvActivityLogs.getId());
		return  lvActivityLogs;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvActivityLogs lvActivityLogs = get(dto);
		dao.delete(  lvActivityLogs);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvActivityLogs save(Dto dto) throws ServiceException {
		 LvActivityLogs lvActivityLogs = (LvActivityLogs)dto.get("model");
		dao.save( lvActivityLogs);
		return   lvActivityLogs;
	}
	/**
	 * 更新
	 */
	public LvActivityLogs update(Dto dto) throws ServiceException {
		 LvActivityLogs lvActivityLogs = (LvActivityLogs)dto.get("model");
		dao.update(lvActivityLogs);
		return  lvActivityLogs;
	}

}
