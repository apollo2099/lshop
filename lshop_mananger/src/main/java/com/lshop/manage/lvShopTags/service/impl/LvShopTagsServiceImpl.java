/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopTags.service.impl;

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
import com.lshop.common.pojo.logic.LvShopTags;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvShopTags.service.LvShopTagsService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopTagsService")
public class LvShopTagsServiceImpl extends BaseServiceImpl implements LvShopTagsService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvShopTags> findAll(Dto dto) throws ServiceException {
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
		 LvShopTags lvShopTags = (LvShopTags)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
	    Map<String ,Object> params=new HashMap<String ,Object>();
        StringBuilder sql = new StringBuilder("select t from LvShopTags t where 1=1 ");
        if(lvShopTags!=null){
        	if(ObjectUtils.isNotEmpty(lvShopTags.getTagName())) {
	        	sql.append(" and  t.tagName like :tagName");
	        	params.put("tagName", "%"+lvShopTags.getTagName()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvShopTags.getStoreId())){
        		sql.append(" and  t.storeId =:storeId");
        		params.put("storeId", lvShopTags.getStoreId());
        		
        	}
        }
        sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        sql.append(" order by t.orderValue desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvShopTags get(Dto dto) throws ServiceException {
		 LvShopTags lvShopTags = (LvShopTags)dto.get("model");
		 lvShopTags = (LvShopTags)dao.load(LvShopTags.class, lvShopTags.getId());
		return  lvShopTags;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopTags lvShopTags = get(dto);
		dao.delete(  lvShopTags);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvShopTags save(Dto dto) throws ServiceException {
		LvShopTags lvShopTags = (LvShopTags) dto.get("model");
		dao.save(lvShopTags);
		return lvShopTags;
	}
	/**
	 * 更新
	 */
	public LvShopTags update(Dto dto) throws ServiceException {
		 LvShopTags lvShopTags = (LvShopTags)dto.get("model");
		dao.update(lvShopTags);
		return  lvShopTags;
	}

}
