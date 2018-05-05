package com.lshop.web.mac.service;

import com.lshop.common.pojo.logic.LvMac;

public interface MacService {
	public Boolean saveMac(String mac);
	public LvMac findByMac(String mac);
}
