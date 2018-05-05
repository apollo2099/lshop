/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lshop.common.pojo.logic.LvLotteryLogs;
import com.lshop.manage.lvActivity.service.LvLotteryLogsService;
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
 */@Service("LvLotteryLogsService")
public class LvLotteryLogsServiceImpl extends BaseServiceImpl implements LvLotteryLogsService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvLotteryLogs> findAll(Dto dto) throws ServiceException {
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
		 LvLotteryLogs lvLotteryLogs = (LvLotteryLogs)dto.get("lvLotteryLogs");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvLotteryLogs t where 1=1");
		        if(ObjectUtils.isNotEmpty(lvLotteryLogs.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getActivityCode())) {
		        	sql.append(" and  t.activityCode like :activityCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getActivityName())) {
		        	sql.append(" and  t.activityName like :activityName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getPrizeName())) {
		        	sql.append(" and  t.prizeName like :prizeName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getPrizeCode())) {
		        	sql.append(" and  t.prizeCode like :prizeCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getUserName())) {
		        	sql.append(" and  t.userName like :userName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryLogs.getUserCode())) {
		        	sql.append(" and  t.userCode like :userCode ");
		        }
		        if(ObjectUtils.isNotEmpty(lvLotteryLogs.getIsSystemFlag())) {
		        	sql.append(" and  t.isSystemFlag = :isSystemFlag ");
		        }	
        Map parms = BeanUtils.describeForHQL(lvLotteryLogs);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvLotteryLogs get(Dto dto) throws ServiceException {
		 LvLotteryLogs lvLotteryLogs = (LvLotteryLogs)dto.get("lvLotteryLogs");
		 lvLotteryLogs = (LvLotteryLogs)dao.load(LvLotteryLogs.class, lvLotteryLogs.getId());
		return  lvLotteryLogs;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvLotteryLogs lvLotteryLogs = get(dto);
		dao.delete(  lvLotteryLogs);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvLotteryLogs save(Dto dto) throws ServiceException {
		 LvLotteryLogs lvLotteryLogs = (LvLotteryLogs)dto.get("lvLotteryLogs");
		dao.save( lvLotteryLogs);
		return   lvLotteryLogs;
	}
	/**
	 * 更新
	 */
	public LvLotteryLogs update(Dto dto) throws ServiceException {
		 LvLotteryLogs lvLotteryLogs = (LvLotteryLogs)dto.get("lvLotteryLogs");
		dao.update(lvLotteryLogs);
		return  lvLotteryLogs;
	}

}
