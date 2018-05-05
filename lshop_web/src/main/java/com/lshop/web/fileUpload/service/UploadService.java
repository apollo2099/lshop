package com.lshop.web.fileUpload.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface UploadService {
	public String uploadImg(Dto dto)throws ServiceException; 

}
