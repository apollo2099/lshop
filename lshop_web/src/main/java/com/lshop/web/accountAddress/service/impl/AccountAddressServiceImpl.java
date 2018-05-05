package com.lshop.web.accountAddress.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.redis.RedisExpire;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.CodeUtils;
import com.lshop.web.accountAddress.component.AddressCacheComponent;
import com.lshop.web.accountAddress.service.AccountAddressService;

@Service("AccountAddressService")
public class AccountAddressServiceImpl implements AccountAddressService{
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	@Resource
	private HibernateBaseDAO lvuserWriteDao;
	@Resource
	private AddressCacheComponent addressCacheComponent;
	
	@Override
	public String addAddress(Dto dto) throws ServiceException {
		LvAccountAddress address = (LvAccountAddress) dto.get("addr");
		String storeFlag = address.getStoreId();
		String userCode = dto.getAsString("userCode");
		boolean setDefault = (Boolean) dto.get("setDefault");
		
		//先判断是否超过五个收货地址
		String hql = "select count(*) from LvAccountAddress where relCode=:userCode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		Object count = lvuserReadDao.findUnique(hql, param);
		int num = 0;
		if (ObjectUtils.isEmpty(count)) {
			return null;
		} else {
			num = new Long((Long) count).intValue();
		}
		if(num>=5){
			return null;
		}
		address.setId(null);
		address.setRelCode(userCode);
		address.setStoreId(storeFlag);
		address.setCode(CodeUtils.getCode());
		address.setCreateTime(new Date());
		address.setModifyTime(address.getCreateTime());
		//如果是用户第一个地址,则设置为默认地址;否则不是默认地址
		if (num == 0) {
			address.setIsDefault(AppDataConstant.DEFAULT_ADDRESS);
		} else {
			address.setIsDefault(AppDataConstant.NOT_DEFAULT_ADDRESS);
		}
		
		lvuserWriteDao.save(address);
		//增加用户收货地址缓存
		addressCacheComponent.put(RedisKeyConstant.AccountAddressKey(userCode), address.getCode(), address);
		if (setDefault) {
			Dto dto2 = new BaseDto();
			dto2.put("userCode", userCode);
			dto2.put("addressCode", address.getCode());
			dto2.put("storeFlag", address.getStoreId());
			setDefAddress(dto2);
		}
		return address.getCode();
	}

	@Override
	public String editAddress(Dto dto) throws ServiceException {
		LvAccountAddress address = (LvAccountAddress) dto.get("addr");
		String userCode = dto.getAsString("userCode");
		Dto dto3 = new BaseDto();
		dto3.put("userCode", userCode);
		dto3.put("addressCode", address.getCode());
		LvAccountAddress orign = getAddressByCode(dto3);
		orign.setRelName(address.getRelName());
		orign.setTel(address.getTel());
		orign.setMobile(address.getMobile());
		orign.setAdress(address.getAdress());
		orign.setCityId(address.getCityId());
		orign.setCityName(address.getCityName());
		orign.setProvinceId(address.getProvinceId());
		orign.setProvinceName(address.getProvinceName());
		orign.setContryId(address.getContryId());
		orign.setContryName(address.getContryName());
		orign.setPostCode(address.getPostCode());
		orign.setModifyTime(new Date());
		orign.setIsDefault(AppDataConstant.NOT_DEFAULT_ADDRESS);
		
		lvuserWriteDao.update(orign);
		//修改缓存
		addressCacheComponent.put(RedisKeyConstant.AccountAddressKey(userCode), orign.getCode(), orign);
		return orign.getCode();
	}

	@Override
	public boolean delAddress(Dto dto) throws ServiceException {
		String userCode = dto.getAsString("userCode");
		String addressCode=dto.getAsString("addressCode");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		param.put("addressCode", addressCode);
		LvAccountAddress orign = getAddressByCode(dto);
		lvuserWriteDao.delete(orign);
		//删除缓存
		addressCacheComponent.delete(RedisKeyConstant.AccountAddressKey(userCode), addressCode);
		if (orign.getIsDefault() == AppDataConstant.DEFAULT_ADDRESS) {
			//若删除的地址是默认地址,则设置新默认地址
			Dto dto2 = new BaseDto();
			dto2.put("userCode", userCode);
			dto2.put("storeFlag", orign.getStoreId());
			setDefAddress(dto2);
		}
		return true;
	}

	@Override
	public LvAccountAddress getAddressByCode(Dto dto) throws ServiceException {
		String addressCode=dto.getAsString("addressCode");
		String userCode = dto.getAsString("userCode");//用户号
		List<LvAccountAddress> addresses = getUserAddress(dto);
		if (CollectionUtils.isNotEmpty(addresses)) {
			for (Iterator<LvAccountAddress> iterator = addresses.iterator(); iterator.hasNext();) {
				LvAccountAddress lvAccountAddress = iterator.next();
				if (addressCode.equals(lvAccountAddress.getCode())) {
					return lvAccountAddress;
				}
			}
		}
		return null;
	}

	@Override
	public boolean setDefAddress(Dto dto) throws ServiceException {
		String userCode = dto.getAsString("userCode");
		String addressCode = dto.getAsString("addressCode");
		
		if (StringUtils.isBlank(addressCode)) {
			//选择最近修改的收货地址为默认地址
			Dto dto2 = new BaseDto();
			dto2.put("userCode", userCode);
			List<LvAccountAddress> addresses = getUserAddress(dto2);
			if (ObjectUtils.isNotEmpty(addresses)) {
				addressCode = addresses.get(0).getCode();
			}
		}
		if (StringUtils.isNotBlank(addressCode)) {
			Map<String, Object> param = new HashMap<String, Object>();
			//设置默认收货地址
			param.put("userCode", userCode);
			String hql = "update LvAccountAddress set isDefault="+ AppDataConstant.NOT_DEFAULT_ADDRESS
					+" where isDefault="+ AppDataConstant.DEFAULT_ADDRESS +" and relCode=:userCode";
			lvuserWriteDao.update(hql, param);
			param = new HashMap<String, Object>();
			param.put("addressCode", addressCode);
			param.put("userCode", userCode);
			
			hql = "update LvAccountAddress set isDefault="+ AppDataConstant.DEFAULT_ADDRESS
					+" where code=:addressCode and relCode=:userCode";
			lvuserWriteDao.update(hql, param);
			//默认地址修改缓存
			String key = RedisKeyConstant.AccountAddressKey(userCode);
			List<LvAccountAddress> cacheAddr = addressCacheComponent.values(key);
			if (CollectionUtils.isNotEmpty(cacheAddr)) {
				LvAccountAddress dAddress = null;
				for (Iterator<LvAccountAddress> iterator = cacheAddr.iterator(); iterator
						.hasNext();) {
					LvAccountAddress lvAccountAddress = iterator.next();
					if (lvAccountAddress.getIsDefault().shortValue() == AppDataConstant.DEFAULT_ADDRESS) {
						lvAccountAddress.setIsDefault(AppDataConstant.NOT_DEFAULT_ADDRESS);
						addressCacheComponent.put(key, lvAccountAddress.getCode(), lvAccountAddress);
					}
					if (lvAccountAddress.getCode().equals(addressCode)) {
						dAddress = lvAccountAddress;
					}
				}
				if (null != dAddress) {
					dAddress.setIsDefault(AppDataConstant.DEFAULT_ADDRESS);
					addressCacheComponent.put(key, addressCode, dAddress);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean cancelDefAddress(Dto dto) throws ServiceException {
		String addressCode=dto.getAsString("addressCode");
		String userCode = dto.getAsString("userCode");
		String hql = "update LvAccountAddress set isDefault="+ AppDataConstant.NOT_DEFAULT_ADDRESS+" where code=:addressCode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("addressCode", addressCode);
		lvuserWriteDao.update(hql, param);
		//更新缓存
		String key = RedisKeyConstant.AccountAddressKey(userCode);
		List<LvAccountAddress> cacheAddr = addressCacheComponent.values(key);
		if (CollectionUtils.isNotEmpty(cacheAddr)) {
			for (Iterator<LvAccountAddress> iterator = cacheAddr.iterator(); iterator.hasNext();) {
				LvAccountAddress lvAccountAddress = iterator.next();
				if (lvAccountAddress.getCode().equals(addressCode)) {
					lvAccountAddress.setIsDefault(AppDataConstant.NOT_DEFAULT_ADDRESS);
					addressCacheComponent.put(key, lvAccountAddress.getCode(), lvAccountAddress);
					break;
				}
			}
		}
		return true;
	}

	@Override
	public List<LvAccountAddress> getUserAddress(Dto dto)
			throws ServiceException {
		return getUserAddress(dto.getAsString("userCode"));
	}

	@Override
	public List<LvAccountAddress> getUserAddress(String userCode)
			throws ServiceException {
		String cacheKey = RedisKeyConstant.AccountAddressKey(userCode);
		List<LvAccountAddress> addr = addressCacheComponent.values(cacheKey);
		if (CollectionUtils.isEmpty(addr)) {
			addr = initUserAddresCache(userCode);
		}
		return addr;
	}

	@Override
	public LvAccountAddress getUserDefAddress(Dto dto) throws ServiceException {
		String userCode = dto.getAsString("userCode");
		List<LvAccountAddress> addresses = getUserAddress(dto);
		if (CollectionUtils.isNotEmpty(addresses)) {
			for (Iterator<LvAccountAddress> iterator = addresses.iterator(); iterator.hasNext();) {
				LvAccountAddress lvAccountAddress = iterator.next();
				if (lvAccountAddress.getIsDefault().shortValue() == AppDataConstant.DEFAULT_ADDRESS) {
					return lvAccountAddress;
				}
			}
			return addresses.get(0);//没有默认则返回第一个地址
		}
		return null;
	}
	/**
	 * 从数据库中读取收货地址
	 * @param userCode
	 * @return
	 */
	private List<LvAccountAddress> getUserAddresFromDB(String userCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		String hql = "from LvAccountAddress where relCode=:userCode order by modifyTime desc";
		return lvuserReadDao.find(hql, param);
	}
	/**
	 * 初始化用户收货地址缓存
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	private List<LvAccountAddress> initUserAddresCache(String userCode) throws ServiceException {
		List<LvAccountAddress> userAddr = getUserAddresFromDB(userCode);
		if (CollectionUtils.isNotEmpty(userAddr)) {
			String key = RedisKeyConstant.AccountAddressKey(userCode);
			Map<String, LvAccountAddress> m = new HashMap<String, LvAccountAddress>();
			for (Iterator<LvAccountAddress> iterator = userAddr.iterator(); iterator.hasNext();) {
				LvAccountAddress lvAccountAddress = iterator.next();
				m.put(lvAccountAddress.getCode(), lvAccountAddress);
			}
			addressCacheComponent.putAll(key, m);
			addressCacheComponent.expire(key, RedisExpire.AccountAddressDay, TimeUnit.DAYS);
		}
		return userAddr;
	}

}
