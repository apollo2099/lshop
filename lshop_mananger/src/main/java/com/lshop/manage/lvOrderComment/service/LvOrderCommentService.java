package com.lshop.manage.lvOrderComment.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvOrderComment;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrderComment.service.LvOrderCommentService.java]  
 * @ClassName:    [LvOrderCommentService]   
 * @Description:  [订单评论信息管理-接口定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-9 下午03:36:24]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-9 下午03:36:24]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
public interface LvOrderCommentService {
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增订单评论信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:36:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:36:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return void
	 */
	public void saveReply(Dto dto)throws Exception;
	public void save(Dto dto)throws ServiceException;
	public Pagination getList(Dto dto) throws ServiceException;
	public Pagination getReplyList(Dto dto) throws ServiceException;
	public void delete (Dto dto) throws ServiceException;
	public void updateAudit(Dto dto) throws ServiceException ;
	public LvOrderComment get(Dto dto)throws ServiceException;
	public void deleteReply(Dto dto)throws ServiceException;

}
