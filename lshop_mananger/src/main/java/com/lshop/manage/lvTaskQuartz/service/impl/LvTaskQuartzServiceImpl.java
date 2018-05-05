/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTaskQuartz.service.impl;

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
import com.lshop.common.pojo.logic.LvTaskQuartz;
import com.lshop.common.task.TaskService;
import com.lshop.manage.lvTaskQuartz.service.LvTaskQuartzService;

/**
 * @author 好视网络-网站研发部：tangd
 * @version 1.0
 * @since 1.0
 */
 @Service("LvTaskQuartzService")
public class LvTaskQuartzServiceImpl extends BaseServiceImpl implements LvTaskQuartzService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	@Resource private TaskService taskService;
	public List<LvTaskQuartz> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return dao.find("from LvTaskQuartz", null);
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvTaskQuartz LvTaskQuartz = (LvTaskQuartz)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvTaskQuartz t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(LvTaskQuartz.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getTaskName())) {
		        	sql.append(" and  t.taskName like :taskName ");
		        }
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getTargetObject())) {
		        	sql.append(" and  t.targetObject like :targetObject ");
		        }
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getTargetMethod())) {
		        	sql.append(" and  t.targetMethod like :targetMethod ");
		        }
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getTargetTime())) {
		        	sql.append(" and  t.targetTime like :targetTime ");
		        }
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getDescription())) {
		        	sql.append(" and  t.description like :description ");
		        }
		        if(ObjectUtils.isNotEmpty(LvTaskQuartz.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(LvTaskQuartz.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(LvTaskQuartz);
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
	public LvTaskQuartz get(Dto dto) throws ServiceException {
		 LvTaskQuartz lvTaskQuartz = (LvTaskQuartz)dto.get("model");
		 lvTaskQuartz = (LvTaskQuartz)dao.load(LvTaskQuartz.class, lvTaskQuartz.getId());
		return  lvTaskQuartz;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvTaskQuartz lvTaskQuartz = get(dto);
		dao.delete( lvTaskQuartz);
		dto.put("lvTaskQuartz", lvTaskQuartz);
		taskService.deleteJob(dto);//删除任务
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvTaskQuartz save(Dto dto) throws ServiceException {
		 LvTaskQuartz lvTaskQuartz = (LvTaskQuartz)dto.get("model");
		 lvTaskQuartz.setTargetMethod(lvTaskQuartz.getTargetMethod().trim());
		 lvTaskQuartz.setTargetObject(lvTaskQuartz.getTargetObject().trim());
		 lvTaskQuartz.setTargetTime(lvTaskQuartz.getTargetTime().trim());
		 lvTaskQuartz.setTaskName(lvTaskQuartz.getTaskName().trim());
		dao.save( lvTaskQuartz);
		dto.put("lvTaskQuartz", lvTaskQuartz);
		taskService.addJob(dto);//增加任务
		return   lvTaskQuartz;
	}
	/**
	 * 更新
	 */
	public LvTaskQuartz update(Dto dto) throws ServiceException {
		 LvTaskQuartz lvTaskQuartz = (LvTaskQuartz)dto.get("model");
		 lvTaskQuartz.setTargetMethod(lvTaskQuartz.getTargetMethod().trim());
		 lvTaskQuartz.setTargetObject(lvTaskQuartz.getTargetObject().trim());
		 lvTaskQuartz.setTargetTime(lvTaskQuartz.getTargetTime().trim());
		 lvTaskQuartz.setTaskName(lvTaskQuartz.getTaskName().trim());
		 dao.update(lvTaskQuartz);
		 dto.put("lvTaskQuartz", lvTaskQuartz);
		 taskService.updateJob(dto);//修改任务
		return  lvTaskQuartz;
	}

	public LvTaskQuartz updatePojo(Dto dto)throws ServiceException{
		 LvTaskQuartz lvTaskQuartz = (LvTaskQuartz)dto.get("model");
		 dao.update(lvTaskQuartz);
		 return lvTaskQuartz;
	}
}
