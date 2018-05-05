/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplModelPublic.service.impl;

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
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvTplModelPublic;
import com.lshop.manage.lvTplModelPublic.service.LvTplModelPublicService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvTplModelPublicService")
public class LvTplModelPublicServiceImpl extends BaseServiceImpl implements LvTplModelPublicService {

	@Resource private HibernateBaseDAO dao;
	public List<LvTplModelPublic> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return dao.find("from LvTplModelPublic", null);
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvTplModelPublic lvTplModelPublic = (LvTplModelPublic)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvTplModelPublic t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvTplModelPublic.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplModelPublic.getModelName())) {
		        	sql.append(" and  t.modelName like :modelName ");
		        }
		        if(ObjectUtils.isNotEmpty(lvTplModelPublic.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplModelPublic.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvTplModelPublic.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
        Map parms = BeanUtils.describeForHQL(lvTplModelPublic);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        }
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvTplModelPublic get(Dto dto) throws ServiceException {
		 LvTplModelPublic lvTplModelPublic = (LvTplModelPublic)dto.get("model");
		 if(lvTplModelPublic.getId()!=null){
		 lvTplModelPublic = (LvTplModelPublic)dao.load(LvTplModelPublic.class, lvTplModelPublic.getId());
		 }else{
		 lvTplModelPublic=(LvTplModelPublic)dao.findUnique("from LvTplModelPublic where code='"+lvTplModelPublic.getCode()+"'", null);
		 }
		return  lvTplModelPublic;
	}

	public Integer del(Dto dto) throws ServiceException {
	
		LvTplModelPublic lvTplModelPublic = get(dto);
		List DetailPublicList=dao.find("from LvTplDetailPublic where tplModelCode='"+lvTplModelPublic.getCode()+"'", null);
		if(DetailPublicList!=null&&DetailPublicList.size()>0){
			return 2;
		}
		dao.delete(lvTplModelPublic);
		return 0;
	
	}

	public void delList(Dto dto) throws ServiceException {

	}

	public LvTplModelPublic save(Dto dto) throws ServiceException {
		 LvTplModelPublic lvTplModelPublic = (LvTplModelPublic)dto.get("model");

		dao.save( lvTplModelPublic);
		return   lvTplModelPublic;
	}

	public LvTplModelPublic update(Dto dto) throws ServiceException {
		 LvTplModelPublic lvTplModelPublic = (LvTplModelPublic)dto.get("model");
		dao.update(lvTplModelPublic);
		return  lvTplModelPublic;
	}
	/**
	 * 检查模板类别是否存在
	 */
	@Override
	public boolean findCheckName(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String storeFlag=dto.getAsString("flag");
		LvTplModelPublic lvTplModel = (LvTplModelPublic)dto.get("model");
		Map params=new HashMap();
		params.put("modelName", lvTplModel.getModelName());
		String hql="from LvTplModelPublic where   modelName=:modelName";
	    Object obj=dao.findUnique(hql, params);
		return obj==null?false:true;
	}

}
