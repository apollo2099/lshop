package com.lshop.manage.message.service;

/**
 * 向前台发送产品添加、变更消息
 * @author jiangch
 *
 */
public interface ProductMsgService {

	/**
	 * 当有新产品添加，或者产品信息变更的时候，将产品的code（注意，不是pcode），发送给前台
	 * 前台会根据这个code到数据库中load产品信息，更新缓存，如果消息出现问题，将会导致前台不会
	 * 更新产品信息
	 * @param code
	 */
	public void sendProductMsg(String code);
}
