package com.lshop.web.activity.card.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvMappCardsTmp;
import com.lshop.common.pojo.logic.LvMappCardsUser;

public interface CardService {
	
	public LvMappCardsTmp getCardByName(Dto dto) throws ServiceException;
	
    public void save(Dto dto)throws ServiceException;
    
    public LvMappCardsUser getMappCardsUser(Dto dto)throws ServiceException;
    
	public Dto getUserCard(Dto dto) throws ServiceException;
	
	public List<LvMappCardsTmp> getList(Dto dto) throws ServiceException;
}
