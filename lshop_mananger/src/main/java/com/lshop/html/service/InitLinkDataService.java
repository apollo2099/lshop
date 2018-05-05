package com.lshop.html.service;

import com.gv.core.datastructure.Dto;

public interface InitLinkDataService extends Runnable{
	public  void init(Dto dto);
	public  void sendStoreToWeb(Dto dto);
	public  void sendHtmlToWeb(String htmlPath,String storeId);
	
}
