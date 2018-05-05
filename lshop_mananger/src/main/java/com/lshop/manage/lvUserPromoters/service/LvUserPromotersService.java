/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvUserPromoters.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;
import com.lshop.common.pojo.logic.LvUserPromoters;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public interface LvUserPromotersService extends BaseService{ 
	
	/**
	 * 获得所有
	 */
	public List<LvUserPromoters> findAll(Dto dto) throws ServiceException;

	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvUserPromoters get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 批量删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void delList(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvUserPromoters save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public List getEmailTplList(Dto dto)throws ServiceException;//邮件模板
	public LvUserPromoters update(Dto dto)throws ServiceException;
	
	public Integer isCheckedYes(Dto dto) throws ServiceException;
	public Integer isCheckedNo(Dto dto) throws ServiceException;
	public LvUserPromoters getUserPromoter(Dto dto) throws ActionException;
	public void saveEdit(Dto dto) throws ActionException;
	public Dto getRankPromoter(Dto dto) throws ServiceException; 
	public Dto add(Dto dto) throws ServiceException; 
	public LvRankfirstLogin getLogin(Dto dto) throws ServiceException;
	public Dto editRankPromoter(Dto dto) throws ServiceException;
    public List<LvEmailTpl> getEmailModel(Dto dto) throws ServiceException;
    public Dto sendRankEmail(Dto dto) throws ServiceException; 
    public void startOrStop(Dto dto) throws ServiceException;
    public void startOrStopAccount(Dto dto) throws ServiceException;
	public Dto gradePage(Dto dto) throws ServiceException;
	public List export(Dto dto) throws ServiceException;
	public List exportExcel(Dto dto) throws ServiceException;
	public Pagination getDetailList(Dto dto) throws ServiceException; 
	public List exportOrder(Dto dto) throws ServiceException; 
	public List exportInfos(Dto dto) throws ServiceException;
	
	/**
	 * @description 一级推广商查看操作
	 * @version 2.0
	 * @author zhengxue
     * @createDate2012/03/31
	 */
	public LvRankfirstInfo view(Dto dto) throws ServiceException;
	
   /**
     * @description 一级推广商列表
	 * @version 2.0
	 * @author zhengxue
     * @createDate 2012/04/06
    */
	public Pagination getLvRankFirstInfoList(Dto dto) throws ServiceException;
	
   /**
     * @description一级推广商结算操作
	 * @version 2.0
	 * @author zhengxue
     * @createDate  2012/04/10
    */
	public Boolean pay(Dto dto) throws ServiceException ;
	
	/**
	 * @description 获取清算记录列表
	 * @version 2.0
	 * @author zhengxue
     * @createDate2012/04/10
	 */
	public Pagination getLogList(Dto dto) throws ServiceException;
	
	/**
	 * @description 一级推广商结算记录导出
	 * @version2.0
	 * @author zhengxue
     * @createDate 2012/04/11
	 */
	public List exportRecords(Dto dto) throws ServiceException;
	
	/**
	 * @description 一级推广商结算记录中查看订单详情
	 * @version 2.0
	 * @author zhengxue
     * @createDate  2012/04/11
	 */
	public Pagination getOrderList(Dto dto) throws ServiceException;
	
	/**
	 * @description 一级推广商结算记录中订单详情数据导出
	 * @version 2.0
	 * @author zhengxue
     * @createDate 2012/04/11
	 */
	public List exportOrders(Dto dto) throws ServiceException;
	public List exportOrderGrade(Dto dto)throws ServiceException;

	/**
	 * @description 一级推广商管理模块，查看业绩操作 
	 * @version 2.0
	 * @autho zhengxuew
	 * @updateDate 2012/04/25
	 */
	public Dto showGrade(Dto dto) throws ServiceException;
	public Boolean isVersionFailure(Dto dto)throws ServiceException;
 } 
