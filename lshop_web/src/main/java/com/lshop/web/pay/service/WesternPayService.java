package com.lshop.web.pay.service;

import com.gv.core.datastructure.Dto;

public interface WesternPayService {

	public Integer saveWesternInfo(Dto dto);
	
	public boolean isExistsMTCN(Dto dto);
}
