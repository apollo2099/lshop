/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopActivity.service.impl;

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
import com.lshop.common.pojo.logic.LvShopActivity;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvShopActivity.service.LvShopActivityService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvShopActivityService")
public class LvShopActivityServiceImpl extends BaseServiceImpl implements LvShopActivityService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvShopActivity> findAll(Dto dto) throws ServiceException {
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
		LvShopActivity lvShopActivity = (LvShopActivity)dto.get("model");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvShopActivity t where 1=1 ");
        if(lvShopActivity!=null){
        	if(ObjectUtils.isNotEmpty(lvShopActivity.getAvtivityName())) {
	        	sql.append(" and  t.avtivityName like :avtivityName ");
	        	params.put("avtivityName", "%"+lvShopActivity.getAvtivityName()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvShopActivity.getStoreId())){
        		sql.append(" and t.storeId =:storeId");
        		params.put("storeId", lvShopActivity.getStoreId());
        	}
        }
        sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        sql.append(" order by t.orderValue desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvShopActivity get(Dto dto) throws ServiceException {
		 LvShopActivity lvShopActivity = (LvShopActivity)dto.get("model");
		 lvShopActivity = (LvShopActivity)dao.load(LvShopActivity.class, lvShopActivity.getId());
		return  lvShopActivity;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvShopActivity lvShopActivity = get(dto);
		dao.delete(  lvShopActivity);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvShopActivity save(Dto dto) throws ServiceException {
		LvShopActivity lvShopActivity = (LvShopActivity) dto.get("model");
		dao.save(lvShopActivity);
		return lvShopActivity;
	}
	/**
	 * 更新
	 */
	public LvShopActivity update(Dto dto) throws ServiceException {
		 LvShopActivity lvShopActivity = (LvShopActivity)dto.get("model");
		dao.update(lvShopActivity);
		return  lvShopActivity;
	}

}
