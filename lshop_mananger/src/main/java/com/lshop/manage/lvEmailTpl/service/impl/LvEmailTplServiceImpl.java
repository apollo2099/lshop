/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvEmailTpl.service.impl;

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
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvEmailTpl.service.LvEmailTplService;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvEmailTplService")
public class LvEmailTplServiceImpl extends BaseServiceImpl implements LvEmailTplService {

	@Resource private HibernateBaseDAO dao;
	
	public List<LvEmailTpl> findAll(Dto dto) throws ServiceException {
		String hql="FROM LvEmailTpl where 1=1";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
    		hql+=" and storeId in("+storeList+")";
	    }
		List<LvEmailTpl> list=dao.find(hql, null);
		return list;
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvEmailTpl lvEmailTpl = (LvEmailTpl)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		Map<String ,Object> params=new HashMap<String ,Object>();
        StringBuilder sql = new StringBuilder("select t from LvEmailTpl t where 1=1 ");
        if(lvEmailTpl!=null){
        	if(ObjectUtils.isNotEmpty(lvEmailTpl.getTplKey())) {
	        	sql.append(" and  t.tplKey like :tplKey ");
	        	params.put("tplKey", "%"+lvEmailTpl.getTplKey()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvEmailTpl.getStoreId())){
        		sql.append(" and  t.storeId =:storeId ");
        		params.put("storeId", lvEmailTpl.getStoreId());
        	}
        }
	    //组装SQL,根据配置查询商城下的店铺关联关系并且拼接查询脚本(包含商城本身)    	
	    sql.append(StoreHelpUtil.getShopRelevanceString(dto.getAsString("flag"), "storeId","t"));
	    sql.append(" order by t.createTime desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	
	public LvEmailTpl get(Dto dto) throws ServiceException {
		LvEmailTpl lvEmailTpl = (LvEmailTpl)dto.get("model");
		lvEmailTpl = (LvEmailTpl)dao.load(LvEmailTpl.class, lvEmailTpl.getId());
		return lvEmailTpl;
	}

	public void del(Dto dto) throws ServiceException {
		LvEmailTpl lvEmailTpl = get(dto);
		dao.delete(lvEmailTpl);
	}

	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}

	public LvEmailTpl save(Dto dto) throws ServiceException {
		LvEmailTpl lvEmailTpl = (LvEmailTpl)dto.get("model");
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			lvEmailTpl.setStoreId(dto.getAsString("flag"));
//	    }
		dao.save(lvEmailTpl);
		return lvEmailTpl;
	}

	public LvEmailTpl update(Dto dto) throws ServiceException {
		LvEmailTpl lvEmailTpl = (LvEmailTpl)dto.get("model");
		dao.update(lvEmailTpl);
		return lvEmailTpl;
	}

	public Boolean isExistEmailTpl(Dto dto)throws ServiceException{
		LvEmailTpl lvEmailTpl = (LvEmailTpl)dto.get("model");
		String hql="from LvEmailTpl where tplKey=:tplKey and storeId=:storeId ";
		Map param=new HashMap();
		param.put("tplKey",lvEmailTpl.getTplKey());
		param.put("storeId",lvEmailTpl.getStoreId());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}
}
