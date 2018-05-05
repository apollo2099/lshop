package com.lshop.emailsend.service;

import com.gv.core.datastructure.Dto;

public interface EmailSendService {

	/**
	 * 发送邮件通知
	 */
	public Boolean sendEmailNotice (Dto dto);


}
