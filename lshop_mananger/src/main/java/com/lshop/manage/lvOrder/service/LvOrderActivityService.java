package com.lshop.manage.lvOrder.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.lshop.common.pojo.logic.LvOrderActivity;

public interface LvOrderActivityService {
	public List<LvOrderActivity> findOrderActivityByOrderId(Dto dto);
}
