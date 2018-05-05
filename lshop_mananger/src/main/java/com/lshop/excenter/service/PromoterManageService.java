package com.lshop.excenter.service;

import java.text.ParseException;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;

public interface PromoterManageService 
{
  public Pagination getOrderDetail(Dto dto) throws ServiceException,ParseException;
  public Dto getCodeList(Dto dto)throws ServiceException; 
  public Dto befPay(Dto dto) throws ServiceException; 
  public Dto payRequest(Dto dto) throws ServiceException; 
  public Pagination getSettledList(Dto dto) throws ServiceException;
  public Pagination getDetailList(Dto dto) throws ServiceException;
  public Pagination getSettleOrderList(Dto dto) throws ServiceException,ParseException; 
  public Pagination getNsettleOrderList(Dto dto) throws ServiceException,ParseException; 
  public Pagination getFundRelate(Dto dto) throws ServiceException; 
  public List<Object[]> getUserDetail(Dto dto) throws ServiceException; 
  public Dto getToolDetail(Dto dto) throws ServiceException; 
  public Dto getContent(Dto dto) throws ServiceException; 
  public Dto getHtmlContent(Dto dto) throws ServiceException;
  public Dto getPromtPage(Dto dto) throws ServiceException; 
}
