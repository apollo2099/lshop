package com.lshop.manage.lvPromoter.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvUserPromoters;

@SuppressWarnings("unchecked")
public interface LvSettledLogService 
{
  public Pagination getLogList(Dto dto) throws ServiceException;
  public LvUserPromoters getUserDetail(Dto dto) throws ServiceException;
  public Pagination getOrderList(Dto dto) throws ServiceException;
  public List export(Dto dto) throws ServiceException; 
}
