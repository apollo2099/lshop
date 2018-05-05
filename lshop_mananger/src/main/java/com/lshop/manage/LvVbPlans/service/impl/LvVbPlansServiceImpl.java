/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.LvVbPlans.service.impl;

import java.util.HashMap;
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
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvVbPlans;
import com.lshop.manage.LvVbPlans.service.LvVbPlansService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvVbPlansService")
public class LvVbPlansServiceImpl extends BaseServiceImpl implements LvVbPlansService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvVbPlans> findAll(Dto dto) throws ServiceException {
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
		 LvVbPlans lvVbPlans = (LvVbPlans)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
         StringBuilder sql = new StringBuilder("select t from LvVbPlans t where 1=1 ");
                Map params =new HashMap();
	        	if(ObjectUtils.isNotEmpty(lvVbPlans.getMallFlag())) {
		        	sql.append(" and  t.mallFlag =:mallFlag ");
		        	params.put("mallFlag", lvVbPlans.getMallFlag());
		        }
	        	if(ObjectUtils.isNotEmpty(lvVbPlans.getTitle())){
	        		sql.append(" and  t.title like:title ");
		        	params.put("title", "%"+lvVbPlans.getTitle()+"%");
	        	}
        sql.append(" order by t.mallFlag,t.porder asc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvVbPlans get(Dto dto) throws ServiceException {
		 LvVbPlans lvVbPlans = (LvVbPlans)dto.get("model");
		 lvVbPlans = (LvVbPlans)dao.load(LvVbPlans.class, lvVbPlans.getId());
		return  lvVbPlans;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvVbPlans lvVbPlans = get(dto);
		dao.delete(  lvVbPlans);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvVbPlans save(Dto dto) throws ServiceException {
		 LvVbPlans lvVbPlans = (LvVbPlans)dto.get("model");
		 Map param=new HashMap();
		 param.put("mallFlag", lvVbPlans.getMallFlag());
		 param.put("ptype", true);
		 Object obj=dao.findUnique("from LvVbPlans where mallFlag=:mallFlag and ptype=:ptype", param);
		 if(obj!=null&&lvVbPlans.getPtype()==true){
			 return null;
		 }
		 
		 dao.save( lvVbPlans);
		return   lvVbPlans;
	}
	/**
	 * 更新
	 */
	public LvVbPlans update(Dto dto) throws ServiceException {
		 LvVbPlans lvVbPlans = (LvVbPlans)dto.get("model");
		dao.update(lvVbPlans);
		return  lvVbPlans;
	}
	@Override
	public List getVBExchangRate(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return dao.find("from LvExchangeRate where mainCurrency='VB'", null);
	}
	@Override
	public LvVbPlans findCheckLvVbPlansNum(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		 Map param=new HashMap();
		 LvVbPlans lvVbPlans = (LvVbPlans)dto.get("model");
		 param.put("mallFlag", lvVbPlans.getMallFlag());
		 param.put("vbNum", lvVbPlans.getVbNum());
		 List list=dao.find("from LvVbPlans where mallFlag=:mallFlag and vbNum=:vbNum", param);
		 if(list==null||list.size()==0)return null;
		return (LvVbPlans)list.get(0);
	}

}
