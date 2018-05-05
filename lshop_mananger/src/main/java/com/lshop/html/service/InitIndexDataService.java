package com.lshop.html.service;

import com.gv.core.datastructure.Dto;

public interface InitIndexDataService extends Runnable{
	public void init(Dto dto)throws Exception ;
}
