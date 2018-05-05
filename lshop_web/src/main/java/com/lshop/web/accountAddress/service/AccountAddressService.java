package com.lshop.web.accountAddress.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.user.LvAccountAddress;

/**
 * 用户收货地址
 * @author caicl
 *
 */
public interface AccountAddressService {
	/**
	 * 增加收货地址
	 * @param LvAccountAddress addr 收货地址
	 * @param String userCode 用户号
	 * @param boolean setDefault 是否设置成默认收货地址
	 * @return
	 * @throws ServiceException
	 */
	String addAddress(Dto dto) throws ServiceException;
	/**
	 * 编辑收货地址
	 * @param LvAccountAddress addr 收货地址
	 * @param String userCode 用户号
	 * @return
	 * @throws ServiceException
	 */
	String editAddress(Dto dto) throws ServiceException;
	/**
	 * 删除收货地址
	 * @param String addressCode 收货地址号
	 * @param String userCode 用户号
	 * @return
	 * @throws ServiceException
	 */
	boolean delAddress(Dto dto) throws ServiceException;
	/**
	 * 根据收货地址号查询收货地址
	 * @param String addressCode 收货地址号
	 * @param String userCode 用户号
	 * @return
	 * @throws ServiceException
	 */
	LvAccountAddress getAddressByCode(Dto dto) throws ServiceException;
	/**
	 * 设置用户默认地址
	 * @param String userCode 用户号
	 * @param String addressCode 收货地址号
	 * @return
	 * @throws ServiceException
	 */
	boolean setDefAddress(Dto dto) throws ServiceException;
	/**
	 * 取消用户默认收货地址
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	boolean cancelDefAddress(Dto dto) throws ServiceException;
	/**
	 * 返回用户所有收货地址,最近修改的显示在前面
	 * @param String userCode 用户号
	 * @return
	 * @throws ServiceException
	 */
	List<LvAccountAddress> getUserAddress(Dto dto) throws ServiceException;
	List<LvAccountAddress> getUserAddress(String userCode) throws ServiceException;
	/**
	 * 返回用户的默认地址
	 * @param String userCode 用户号
	 * @return
	 * @throws ServiceException
	 */
	LvAccountAddress getUserDefAddress(Dto dto) throws ServiceException;
}
