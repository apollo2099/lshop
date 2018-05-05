package com.lshop.manage.lvPromoter.service;

import java.text.ParseException;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvVideo;

@SuppressWarnings("unchecked")
public interface LvPromtManageService
{
	public Pagination getPreparePayList(Dto dto) throws ServiceException; 
	public void preparePay(Dto dto) throws ServiceException,ParseException;
	public Pagination getPayList(Dto dto) throws ServiceException; 
	public Boolean pay(Dto dto) throws ServiceException; 
	public Pagination getOrderList(Dto dto) throws ServiceException; 
	public List export(Dto dto) throws ServiceException;
	public List exportOrder(Dto dto) throws ServiceException; 
	public List exportExl(Dto dto) throws ServiceException; 
	public Pagination getFundList(Dto dto) throws ServiceException; 
	public Dto getExpenseInfo(Dto dto) throws ServiceException; 
	public Dto expenseFund(Dto dto) throws ServiceException; 
	public Pagination getFundDetail(Dto dto) throws ServiceException;
	public Pagination getContentList(Dto dto) throws ServiceException; 
	public Dto add(Dto dto) throws ServiceException; 
	public Dto delete(Dto dto) throws ServiceException; 
	public Dto befEdit(Dto dto) throws ServiceException;
	public Dto edit(Dto dto) throws ServiceException; 
	public Pagination getMaterialList(Dto dto) throws ServiceException; 
	public Dto deleteMaterial(Dto dto) throws ServiceException; 
	public Dto addMaterial(Dto dto) throws ServiceException; 
	public Dto befEditMaterial(Dto dto) throws ServiceException; 
	public Dto editMaterial(Dto dto) throws ServiceException; 
	public List exportFund(Dto dto) throws ServiceException; 
	public Pagination getVideoList(Dto dto) throws ServiceException; 
	public Dto addVideo(Dto dto) throws ServiceException;
	public Dto editVideo(Dto dto) throws ServiceException; 
	public LvVideo befEditVideo(Dto dto) throws ServiceException; 
	public Dto deleteVideo(Dto dto) throws ServiceException; 
	public Boolean isVersionFailure(Dto dto) throws ServiceException;
}
