package com.lshop.manage.lvExtendBalance.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.service.ExtendBalanceQuartzService.java]  
 * @ClassName:    [ExtendBalanceQuartzService]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:09:37]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:09:37]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface ExtendBalanceQuartzService extends Runnable{
	public void init()throws ServiceException;
	public void start(Dto dto)throws ServiceException;
}
