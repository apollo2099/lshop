/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubGift.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.manage.lvPubGift.service.LvPubGiftService;
import com.lshop.common.pojo.logic.LvPubGift;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvPubGiftService")
public class LvPubGiftServiceImpl extends BaseServiceImpl implements LvPubGiftService {
	 private static final Log logger	= LogFactory.getLog(LvPubGiftServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvPubGift> findAll(Dto dto) throws ServiceException {
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
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		LvPubGift lvPubGift = (LvPubGift)dto.get("lvPubGift");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvPubGift t where status<>-1 ");
        if(ObjectUtils.isNotEmpty(lvPubGift)){
        	if(ObjectUtils.isNotEmpty(lvPubGift.getGiftName())) {
	        	sql.append(" and  t.giftName like :giftName ");
	        	params.put("giftName", "%"+lvPubGift.getGiftName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvPubGift.getGiftNameEn())) {
	        	sql.append(" and  t.giftNameEn like :giftNameEn ");
	        	params.put("giftNameEn", "%"+lvPubGift.getGiftNameEn().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvPubGift.getPcode())) {
	        	sql.append(" and  t.pcode like :pcode ");
	        	params.put("pcode", "%"+lvPubGift.getPcode().trim()+"%");
	        }
	        if(ObjectUtils.isNotEmpty(lvPubGift.getStatus())) {
	        	sql.append(" and  t.status = :status ");
	        	params.put("status",lvPubGift.getStatus());
	        }	
        }

        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPubGift get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.get() method begin*****");
		}
		LvPubGift lvPubGift = (LvPubGift)dto.get("lvPubGift");
		lvPubGift = (LvPubGift)dao.load(LvPubGift.class, lvPubGift.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.get() method end*****");
		}
		return  lvPubGift;
	}

	/**
	 * 保存
	 */
	public LvPubGift save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.save() method begin*****");
		}
		LvPubGift lvPubGift = (LvPubGift)dto.get("lvPubGift");
		dao.save( lvPubGift);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.save() method end*****");
		}
		return   lvPubGift;
	}
	/**
	 * 更新
	 */
	public LvPubGift update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.update() method begin*****");
		}
		
		LvPubGift lvPubGift = (LvPubGift)dto.get("lvPubGift");
		dao.update(lvPubGift);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.update() method end*****");
		}
		return  lvPubGift;
	}
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改赠品状态服务]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-6 下午6:25:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-6 下午6:25:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvPubGift
	 */
	public LvPubGift updateStatus(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.updateStatus() method begin*****");
		}
		LvPubGift lvPubGift = (LvPubGift)dto.get("lvPubGift");		
	    String hql=" update LvPubGift set status=:status," +
				   " modifyUserId=:modifyUserId," +
				   " modifyUserName=:modifyUserName," +
				   " modifyTime=:modifyTime" +
				   " where id=:id ";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("status", lvPubGift.getStatus());
		param.put("modifyUserId", lvPubGift.getModifyUserId());
		param.put("modifyUserName", lvPubGift.getModifyUserName());
		param.put("modifyTime", lvPubGift.getModifyTime());
		param.put("id", lvPubGift.getId());
		dao.update(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftServiceImpl.updateStatus() method end*****");
		}
		return  lvPubGift;
	}

}
