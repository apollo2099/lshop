/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.emailsend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lshop.common.pojo.logic.LvMailConfig;
import com.lshop.emailsend.service.LvMailConfigService;
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
 */@Service("LvMailConfigService")
public class LvMailConfigServiceImpl extends BaseServiceImpl implements LvMailConfigService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	public List<LvMailConfig> findAll(Dto dto) throws ServiceException {
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
		 LvMailConfig lvMailConfig = (LvMailConfig)dto.get("lvMailConfig");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvMailConfig t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvMailConfig.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getSendSmtpHost())) {
		        	sql.append(" and  t.sendSmtpHost like :sendSmtpHost ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getSendUserName())) {
		        	sql.append(" and  t.sendUserName like :sendUserName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getSendPassword())) {
		        	sql.append(" and  t.sendPassword like :sendPassword ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getMailFrom())) {
		        	sql.append(" and  t.mailFrom like :mailFrom ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getMallSystem())) {
		        	sql.append(" and  t.mallSystem like :mallSystem ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(lvMailConfig.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvMailConfig.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(lvMailConfig);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvMailConfig get(Dto dto) throws ServiceException {
		 LvMailConfig lvMailConfig = (LvMailConfig)dto.get("lvMailConfig");
		 lvMailConfig = (LvMailConfig)lvlogicReadDao.load(LvMailConfig.class, lvMailConfig.getId());
		return  lvMailConfig;
	}
	
	public LvMailConfig findByMallFlag(Dto dto) throws ServiceException{
		LvMailConfig lvMailConfig =null;
		String mallFlag=(String) dto.get("mallFlag");
		if(ObjectUtils.isNotEmpty(mallFlag)){
			String hql="from LvMailConfig where mallSystem=:mallSystem";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("mallSystem", mallFlag);
			List<LvMailConfig> list=lvlogicReadDao.find(hql, param);
			if(list!=null&&list.size()>0){
				lvMailConfig=list.get(0);
			}
		}
		return lvMailConfig;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvMailConfig lvMailConfig = get(dto);
		 lvlogicReadDao.delete(  lvMailConfig);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvMailConfig save(Dto dto) throws ServiceException {
		 LvMailConfig lvMailConfig = (LvMailConfig)dto.get("lvMailConfig");
		 lvlogicReadDao.save( lvMailConfig);
		return   lvMailConfig;
	}
	/**
	 * 更新
	 */
	public LvMailConfig update(Dto dto) throws ServiceException {
		 LvMailConfig lvMailConfig = (LvMailConfig)dto.get("lvMailConfig");
		 lvlogicReadDao.update(lvMailConfig);
		return  lvMailConfig;
	}

}
