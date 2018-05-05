/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxObtain.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lshop.web.weixin.wxObtain.service.WxObtainService;
import com.lshop.web.weixin.common.pojo.WxObtain;
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
 */@Service("WxObtainService")
public class WxObtainServiceImpl extends BaseServiceImpl implements WxObtainService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	@Resource private HibernateBaseDAO lvlogicWriteDao;
	public List<WxObtain> findAll(Dto dto) throws ServiceException {
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
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxObtain t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxObtain.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtain.getOpenid())) {
		        	sql.append(" and  t.openid like :openid ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxObtain.getNickname())) {
		        	sql.append(" and  t.nickname like :nickname ");
		        }
		        if(ObjectUtils.isNotEmpty(wxObtain.getObtainType())) {
		        	sql.append(" and  t.obtainType = :obtainType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxObtain.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtain.getIpAddress())) {
		        	sql.append(" and  t.ipAddress like :ipAddress ");
		        }
        Map parms = BeanUtils.describeForHQL(wxObtain);
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
	public WxObtain get(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		 wxObtain = (WxObtain)lvlogicReadDao.load(WxObtain.class, wxObtain.getId());
		return  wxObtain;
	}
	
	/**
	 * 是否能够领取
	 * @param wxhConfigId
	 * @param openid
	 * @return
	 * @throws ServiceException
	 */	
	public boolean canObtain(int wxhConfigId, String openid, int obtainType, String ipAddress, String friendOpenid) throws ServiceException {
		int sameIpMaxLimit = 10;
		Calendar cal = Calendar.getInstance();
		String fm1 = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(fm1);
		String today = sdf.format(cal.getTime());
		String todayBegin = today + " 00:00:00";
		String todayEnd = today + " 23:59:59";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		param.put("openid", openid);
		param.put("obtainType", obtainType);
 		if (WxObtain.obtainType_self == obtainType) {
			String hql = "select t from WxObtain t where t.openid like :openid and t.wxhConfigId = :wxhConfigId and t.createTime > '"+todayBegin+"' and t.createTime < '"+todayEnd+"' and t.obtainType = :obtainType";
			List<WxObtain> list = lvlogicReadDao.find(hql, param);
			if (list.size() > 0) {
				return false;
			}
		} else {
			param.put("friendOpenid", friendOpenid);
			String hql1 = "select t from WxObtain t where t.openid like :openid and t.wxhConfigId = :wxhConfigId and t.createTime > '"+todayBegin+"' and t.createTime < '"+todayEnd+"' and t.obtainType = :obtainType and t.openid = :openid and t.friendOpenid = :friendOpenid";
			List<WxObtain> list1 = lvlogicReadDao.find(hql1, param);
			if (list1.size() > 0) {
				return false;
			}
			Map<String, Object> param2 = new HashMap<String, Object>();
			param2.put("wxhConfigId", wxhConfigId);
			param2.put("openid", openid);
			param2.put("obtainType", obtainType);			
			param2.put("ipAddress", ipAddress);
			String hql = "select t from WxObtain t where t.openid like :openid and t.wxhConfigId = :wxhConfigId and t.createTime > '"+todayBegin+"' and t.createTime < '"+todayEnd+"' and t.obtainType = :obtainType and t.ipAddress = :ipAddress";
			List<WxObtain> list = lvlogicReadDao.find(hql, param2);		
			if ((list.size() >= sameIpMaxLimit)) {
				return false;
			}
		}
		return true;
	}	
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxObtain wxObtain = get(dto);
		lvlogicReadDao.delete(  wxObtain);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxObtain save(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		 lvlogicWriteDao.save( wxObtain);
		return   wxObtain;
	}
	/**
	 * 更新
	 */
	public WxObtain update(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		lvlogicReadDao.update(wxObtain);
		return  wxObtain;
	}
}
