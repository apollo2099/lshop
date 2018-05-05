/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplDetailPublic.service.impl;

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
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplDetailPublic;
import com.lshop.manage.lvTplDetailPublic.service.LvTplDetailPublicService;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvTplDetailPublicService")
public class LvTplDetailPublicServiceImpl extends BaseServiceImpl implements LvTplDetailPublicService {

	@Resource private HibernateBaseDAO dao;
	public List<LvTplDetailPublic> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String tplModelCode=dto.getAsString("tplModelCode");
		return dao.find("from LvTplDetailPublic where tplModelCode='"+tplModelCode+"'", null);
	}
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvTplDetailPublic lvTplDetailPublic = (LvTplDetailPublic)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvTplDetailPublic t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
		        if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getTplModelCode())) {
		        	sql.append(" and  t.tplModelCode ='"+lvTplDetailPublic.getTplModelCode().trim()+"' ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getContent())) {
		        	sql.append(" and  t.content like :content ");
		        }
		        if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvTplDetailPublic.getPagePath())) {
		        	sql.append(" and  t.pagePath like :pagePath ");
		        }
        Map parms = BeanUtils.describeForHQL(lvTplDetailPublic);
        parms.remove("tplModelCode");
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        }
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvTplDetailPublic get(Dto dto) throws ServiceException {
		 LvTplDetailPublic lvTplDetailPublic = (LvTplDetailPublic)dto.get("model");
		 lvTplDetailPublic = (LvTplDetailPublic)dao.load(LvTplDetailPublic.class, lvTplDetailPublic.getId());
		return  lvTplDetailPublic;
	}

	public void del(Dto dto) throws ServiceException {
		 LvTplDetailPublic lvTplDetailPublic = get(dto);
		dao.delete(  lvTplDetailPublic);
	}

	public void delList(Dto dto) throws ServiceException {

	}

	public LvTplDetailPublic save(Dto dto) throws ServiceException {
		 LvTplDetailPublic lvTplDetailPublic = (LvTplDetailPublic)dto.get("model");
		dao.save( lvTplDetailPublic);
		return   lvTplDetailPublic;
	}

	public LvTplDetailPublic update(Dto dto) throws ServiceException {
		 LvTplDetailPublic lvTplDetailPublic = (LvTplDetailPublic)dto.get("model");
		dao.update(lvTplDetailPublic);
		return  lvTplDetailPublic;
	}
	
	@Override
	public boolean findCheckNameOrPath(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvTplDetailPublic lvTplDetailPublic = (LvTplDetailPublic)dto.get("model");
		Map params=new HashMap();
		params.put("tplModelCode", lvTplDetailPublic.getTplModelCode());
		params.put("name", lvTplDetailPublic.getName());
		params.put("pagePath", lvTplDetailPublic.getPagePath());
		Object obj=dao.findUnique("from LvTplDetailPublic where tplModelCode=:tplModelCode and (name=:name or pagePath=:pagePath)",params);
		return obj==null?false:true;
	}
}
