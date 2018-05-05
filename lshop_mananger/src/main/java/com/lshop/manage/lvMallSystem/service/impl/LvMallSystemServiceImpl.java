/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvMallSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lshop.manage.lvMallSystem.service.LvMallSystemService;
import com.lshop.common.pojo.logic.LvMallSystem;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
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
 */@Service("LvMallSystemService")
public class LvMallSystemServiceImpl extends BaseServiceImpl implements LvMallSystemService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvMallSystem> findAll(Dto dto) throws ServiceException {
		String hql="from LvMallSystem";
		List<LvMallSystem> listMall= dao.find(hql, null);
		return listMall;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvMallSystem lvMallSystem = (LvMallSystem)dto.get("lvMallSystem");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvMallSystem t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvMallSystem.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvMallSystem.getMallSystemName())) {
		        	sql.append(" and  t.mallSystemName like :mallSystemName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMallSystem.getMallSystemFlag())) {
		        	sql.append(" and  t.mallSystemFlag like :mallSystemFlag ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMallSystem.getDomainPostfix())) {
		        	sql.append(" and  t.domainPostfix like :domainPostfix ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMallSystem.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(lvMallSystem.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvMallSystem.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(lvMallSystem);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	
	/**
	 * 根据商城标示查询商城体系信息
	 * @param dto
	 * @throws ServiceException
	 */
	public LvMallSystem findByFlag(Dto dto) throws ServiceException{
		LvMallSystem lvMallSystem=null;
		String mallSystemFlag=(String) dto.get("mallSystemFlag");
		if(ObjectUtils.isNotEmpty(mallSystemFlag)){
			hql="from LvMallSystem where mallSystemFlag=:mallSystemFlag";
			Map param=new HashMap();
			param.put("mallSystemFlag",mallSystemFlag);
			List<LvMallSystem> listMall= dao.find(hql, param);
			if(listMall!=null&&listMall.size()>0){
				lvMallSystem=listMall.get(0);
			}
		}
		return lvMallSystem;
	}
	
	public Boolean isExistMallSystemFlag(Dto dto)throws ServiceException{
		String mallSystemFlag=(String) dto.get("mallSystemFlag");
		if(ObjectUtils.isNotEmpty(mallSystemFlag)){
			hql="from LvMallSystem where mallSystemFlag=:mallSystemFlag";
			Map param=new HashMap();
			param.put("mallSystemFlag",mallSystemFlag);
			List<LvMallSystem> listMall= dao.find(hql, param);
			if(listMall!=null&&listMall.size()>0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获得单独的实体信息
	 */
	public LvMallSystem get(Dto dto) throws ServiceException {
		 LvMallSystem lvMallSystem = (LvMallSystem)dto.get("lvMallSystem");
		 lvMallSystem = (LvMallSystem)dao.load(LvMallSystem.class, lvMallSystem.getId());
		return  lvMallSystem;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvMallSystem lvMallSystem = get(dto);
		dao.delete(  lvMallSystem);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvMallSystem save(Dto dto) throws ServiceException {
		 LvMallSystem lvMallSystem = (LvMallSystem)dto.get("lvMallSystem");
		dao.save( lvMallSystem);
		return   lvMallSystem;
	}
	/**
	 * 更新
	 */
	public LvMallSystem update(Dto dto) throws ServiceException {
		 LvMallSystem lvMallSystem = (LvMallSystem)dto.get("lvMallSystem");
		dao.update(lvMallSystem);
		return  lvMallSystem;
	}

	@Override
    public void initMallSystem(Dto dto)throws ServiceException {
		Constants.MALL_TO_DOMAIN_POSTFIX.clear();
		Constants.MALL_SYSTEM_TO_STORE.clear();
		String hql="from LvMallSystem";
		List<LvMallSystem> listMall= dao.find(hql, null);
		if(listMall!=null&&listMall.size()>0){
			for (LvMallSystem lvMallSystem : listMall) {
				Constants.MALL_TO_DOMAIN_POSTFIX.put(lvMallSystem.getMallSystemFlag(), lvMallSystem.getDomainPostfix());
				//存储商城体系标志对应商城店铺标志
				hql="from LvStore where mallSystem=:mallSystem";
				Map<String,String> param=new HashMap<String, String>();
				param.put("mallSystem", lvMallSystem.getMallSystemFlag());
				List<LvStore> storeList=dao.find(hql, param);
				if(storeList!=null&&storeList.size()>0){
					List<String> list=(List) new ArrayList<String>();
					for (int i = 0; i < storeList.size(); i++) {
						LvStore store=storeList.get(i);
						list.add(store.getStoreFlag());
					}
					Constants.MALL_SYSTEM_TO_STORE.put(lvMallSystem.getMallSystemFlag(), list);
				}
			}
		}
	}
	
}
