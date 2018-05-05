package com.lshop.emailsend.service;

import com.gv.core.datastructure.Dto;

public interface EmailSendService {

	/**
	 * 多邮件发送公共方法
	 * @param dto
	 * @return
	 */
	public Boolean sendEmailNoticePub(Dto dto);
	/**
	 * 发送邮件通知
	 */
	public Boolean sendEmailNotice (Dto dto);

}
