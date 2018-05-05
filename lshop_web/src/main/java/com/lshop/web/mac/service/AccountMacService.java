package com.lshop.web.mac.service;

import com.lshop.common.pojo.logic.LvAccountMac;

public interface AccountMacService {
	
	public LvAccountMac findByMac(String userEmail,String mac);
	public LvAccountMac findByUserCode(String userCode);
	public Boolean saveAccountMac(LvAccountMac accountMac);
	public Boolean saveAccountMac(String userEmail,String userCode,String contactPhone,String contactTel,String mac,String ip,String sourceUrl);
	public void updateStatus(String userEmail, String mac);

}
