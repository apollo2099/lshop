package com.lshop.promotermanager;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;

public interface RankPromoterManageService 
{
	public LvRankfirstLogin login(Dto dto) throws ServiceException;
	public Dto getPromtCodeList(Dto dto) throws ServiceException; 
	public LvRankfirstInfo getUserInfo(Dto dto) throws ServiceException;
	public LvRankfirstLogin getUser(Dto dto) throws ServiceException;
	public void edit(Dto dto) throws ServiceException;
	public void editPwd(Dto dto) throws ServiceException;
	public Dto sendRequest(Dto dto) throws ServiceException; 
	public Dto getInfo(Dto dto) throws ServiceException; 
	public LvRankfirstLogin getRankUser(Dto dto) throws ServiceException; 
	/**
	 * @deprecated 一级推广商结算查询模块
	 * @param dto
	 * @throws ServiceException
	 * @author zhengxue 
	 * @since 1.0  2012/03/29
	 */
	public Pagination getSettledList(Dto dto) throws ServiceException;
	
	/**
	 * @description 一级推广商提交结算申请
	 * @version 1.0 
	 * @author zhengxue 
	 * @createDate 2012/04/18
	 */
  public Dto payRequest(Dto dto) throws ServiceException;
  
  /**
   * @description 显示可申请（以及未满15天结算条件的）结算佣金的订单详情页面
   * @version 1.0 
   * @author zhengxue 
   * @createDate 2012/04/18
   */
  public Dto getSettleInfo(Dto dto) throws ServiceException;
  
}
