package com.lshop.manage.lvStore.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvStore;

public interface InitStoreDataService {
	//总入口
	public boolean initData(Dto dto);
	//移植邮件模板数据
	public boolean insertEmailTpl(String targetFlag,String sampleFlag);
	//移植广告数据
	public boolean insertAd(String targetFlag,String sampleFlag);
	//移植菜单导航
	public boolean insertNavigation(String targetFlag,String sampleFlag);
	//移植静态化数据
	public boolean insertLinkUrl(String targetFlag,String sampleFlag);
	//移植运费设置
	public boolean insertCarriageSet(String targetFlag,String sampleFlag);
	//移植产品信息
	public boolean insertProduct(String targetFlag,String sampleFlag);
	//移植支付方式
	public boolean updatePaymentStyle(String targetFlag,String sampleFlag);
	//插入模板内容
	public boolean	insertContent(String targetFlag,String sampleFlag);
	//更新产品套餐关系
	public void updateProductPackage(Dto dto) throws ServiceException;

}
