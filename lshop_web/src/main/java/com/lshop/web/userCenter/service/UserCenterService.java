package com.lshop.web.userCenter.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.pojo.user.LvAccountInfo;

/**
 * 用户中心模块
 * @author zhengxue
 * @since 2.0 2012-07-03
 *
 */
public interface UserCenterService {
	
	/**
	 * 判断账号是否存在
	 * 由邮箱和昵称共同判断，当邮箱和昵称均存在的情况下，则表示已经注册。
	 * @param dto
	 * @return boolean
	 * @throws ServiceException
	 */
	public Boolean isExistsAccount(Dto dto) throws ServiceException;
	
	/**
	 * 添加账号
	 * 在LvAccount,LvAccountInfo,LvAccountAddress中皆需要保存用户信息
	 * @param dto
	 * @return LvUser
	 * @throws ServiceException
	 */
	public LvAccount addAccount(Dto dto) throws ServiceException;
	
	/**
	 * 查询账号信息
	 * 根据用户code查找
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	public LvAccount getAccount(Dto dto) throws ServiceException;
	
	/**
	 * 用户登陆
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	public LvAccount login(Dto dto) throws ServiceException ;
	
	/**
	 * 找回密码
	 * 根据邮件找回，系统随机生成密码并发邮件给用户
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	public LvAccount password(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——我的账户
	 * 获取提醒的订单状态数据
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto getOrderNum(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——我的资料
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvAccountInfo getAccountInfo(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——我的资料_编辑资料
	 * @param dto
	 * @throws ServiceException
	 */
	public void editInfo(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——修改密码
	 * @param dto
	 * @throws ServiceException
	 */
	public void updatePassword(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——我的订单
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getOrderlist(Dto dto) throws ServiceException;
	
	/**
	 * 我的评论——我发表的评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getCommentList(Dto dto) throws ServiceException;
	
	/**
	 * 我的评论——管理员回复的评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getReplyList(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——收货地址
	 * 显示收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getAddressList(Dto dto) throws ServiceException;
	
	/**
	 * 用户中心——收货地址——通过code查找LvAccountAddress
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvAccountAddress getAddressByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取默认收货地址
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvAccountAddress getDefaultAddress(Dto dto) throws ServiceException; 
	
	/**
	 * 获取非默认收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvAccountAddress> getNonDefaultAddress(Dto dto) throws ServiceException; 
	
	/**
	 * 获取所有收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvAccountAddress> getAllAddress(Dto dto) throws ServiceException;
	
	/**
	 * 根据国家ID获取对应的洲省
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvArea> getProvinces(Dto dto) throws ServiceException;
	
	/**
	 * add by dingh
	 * 查询账号信息
	 * 根据用户账号（邮箱）查找
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	public LvAccount getAccountByEmail(Dto dto) throws ServiceException;
	public LvAccount getAccountByEmail(String email)throws ServiceException;
	
}
