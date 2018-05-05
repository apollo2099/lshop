package com.lshop.html.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.util.Constants;
import com.lshop.common.util.SearchUtil;
import com.lshop.html.service.InitIndexDataService;

@Service("InitIndexDataService")
public class InitIndexDataServiceImpl implements InitIndexDataService {
	private static final Log logger = LogFactory.getLog(InitIndexDataServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	
	private Dto dto;
	@Override
	public void init(Dto dto){
		  this.dto=dto;
	      new Thread(this).start();
		
	}
	@Override
	public void run() {
		try {
			SearchUtil.initProductInfo(dao);
			SearchUtil.initStoreInfo(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		
	}

}
