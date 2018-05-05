package com.lshop.web.accountAddress.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.test.BaseTest;
import com.lshop.common.pojo.user.LvAccountAddress;

public class AccountAddressServiceTest extends BaseTest{
	@Resource AccountAddressService service;
	String userCode = "98f888c4139b403da21d963d4b774a37";
	String storeFlag = "mbmcn";
	String addressCode = "d61486b27c3043c39d529bd22a20e1bf";
	@Test
	public void testAddAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditAddress() {
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("addressCode", addressCode);
		LvAccountAddress address = service.getAddressByCode(dto);
		address.setRelName(address.getRelName()+"ap");
		dto.put("addr", address);
		service.editAddress(dto);
		System.out.println();
	}

	@Test
	public void testDelAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAddressByCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDefAddress() {
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("addressCode", addressCode);
		LvAccountAddress address = service.getAddressByCode(dto);
		dto.put("storeFlag", address.getStoreId());
		service.setDefAddress(dto);
		LvAccountAddress address2 = service.getUserDefAddress(dto);
		System.out.println();
	}

	@Test
	public void testGetUserAddress() {
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("storeFlag", storeFlag);
		List<LvAccountAddress> addresses = service.getUserAddress(dto);
		System.out.println();
	}

}
