package com.lshop.excenter.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserInfo;
import com.lshop.common.pojo.logic.LvUserPromoters;

public interface UserService {
	/**
	 * 获取用户信息
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvUserPromoters getUserInfo(Dto dto) throws ServiceException;

	public LvUser login(Dto dto) throws ServiceException;

	public Boolean isExistsUser(Dto dto) throws ServiceException;

	public Integer add(Dto dto) throws ServiceException;
	public LvUser getUser(Dto dto) throws ServiceException;

	public void edit(Dto dto) throws ServiceException;

	public void editPwd(Dto dto) throws ServiceException;
	public LvUser password(Dto dto)throws ServiceException;
	public LvUserCoupon getUserCoupon(Dto dto) throws ServiceException;
}
