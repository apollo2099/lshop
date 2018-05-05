package com.lshop.manage.lvExtendBalance.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvExtendBalance;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvBanner.action.LvBannerMngAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.action.LvExtendBalanceAction.java]  
 * @ClassName:    [LvExtendBalanceAction]   
 * @Description:  [推广商结算信息管理- action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-2 下午05:27:19]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-2 下午05:27:19]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvExtendBalanceAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvExtendBalanceAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvExtendBalanceAction.class);
	private LvExtendBalance lvExtendBalance;
	private BufferedInputStream excelStream;
	private String balanceId;
	private String orderId;
	private Date versionTime;



	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询推广商结算清单列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午05:27:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午05:27:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvExtendBalance", lvExtendBalance);
		//产品信息分页集合对象
		page=(Pagination) this.doService("LvExtendBalanceService", "getList", dto);
		
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: getBalanceByUid 
	 * @Description:  [根据uid查询该用户的结算清单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-10-16 下午03:29:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-16 下午03:29:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String secondList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvExtendBalance", lvExtendBalance);
		//产品信息分页集合对象
		page=(Pagination) this.doService("LvExtendBalanceService", "getList", dto);
		
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.list() method end*****");
		}
		return "secondList";
	}
	
	/**
	 * 
	 * @Method: sysBalance 
	 * @Description:  [手动推广商批量结算]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午05:27:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午05:27:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String sysBalance(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.sysBalance() method begin*****");
		}
		//推广商手动自动结算
		this.doService("ExtendBalanceQuartzService", "start", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.sysBalance() method end*****");
		}
		return AJAX;
	}
	
	

	/**
	 * 
	 * @Method: updateBalance 
	 * @Description:  [结算清单财务确认]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 上午11:49:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 上午11:49:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String updateBalance(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.updateBalance() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvExtendBalance.setModifyUserId(users.getId());
		lvExtendBalance.setModifyUserName(users.getUserName());
		lvExtendBalance.setModifyTime(new Date());
		dto.put("lvExtendBalance", lvExtendBalance);
		dto.put("versionTime", versionTime);
		Boolean isFlag=(Boolean) this.doService("LvExtendBalanceService", "isVersionFailure", dto);
	    if(!isFlag){
			  json.setMessage("版本失效,刷新当前数据列表!");
			  json.doNavTabTodo();
			  return AJAX;
		}
		this.doService("LvExtendBalanceService", "updateBalance", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.updateBalance() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: updateExtendBalance 
	 * @Description:  [删除结算详情后，更新结算单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-11-26 上午10:46:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-11-26 上午10:46:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String updateExtendBalance(){
		//根据结算单号和订单号删除结算清单详情
		dto.put("balanceId", balanceId);
		dto.put("orderId", orderId);
		Boolean deleteFlag=(Boolean) this.doService("LvExtendBalanceDetalsService", "delete", dto);
		if(!deleteFlag){
			json.setMessage("结算单"+balanceId+"更新失败,最少保持一个结算清单关系!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		//更新结算清单信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("USER", users);
		dto.put("lvExtendBalance", lvExtendBalance);
		lvExtendBalance=(LvExtendBalance) this.doService("LvExtendBalanceService", "get", dto);
		//更新结算单信息
		dto.put("lvExtendBalance", lvExtendBalance);
		Boolean isFlag=(Boolean) this.doService("LvExtendBalanceService", "updateStatement", dto);
		if(isFlag){
			json.setMessage("结算单"+lvExtendBalance.getBalanceId()+"更新成功!");
			json.setStatusCode(200);
		}else{
			json.setMessage("结算单"+lvExtendBalance.getBalanceId()+"更新失败!");
			json.setStatusCode(300);
		}
		json.doNavTabTodo();
		return AJAX;
	}
	/**
	 * 
	 * @Method: getBalanceDetails 
	 * @Description:  [查看结算清单详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 上午11:52:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 上午11:52:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String getBalanceDetails(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.getBalanceDetails() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvExtendBalance", lvExtendBalance);
		//产品信息分页集合对象
		page=(Pagination) this.doService("LvExtendBalanceService", "getBalanceDetails", dto);
		
		//查询结算清单信息
		lvExtendBalance=(LvExtendBalance) this.doService("LvExtendBalanceService", "getBalance", dto);
		this.getRequest().setAttribute("lvExtendBalance", lvExtendBalance);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvExtendBalanceAction.getBalanceDetails() method end*****");
		}
		return "details";
	}
	
	
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [根据id查询结算信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午03:18:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午03:18:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String view(){
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.view() method begin*****");
		}
		dto.put("lvExtendBalance", lvExtendBalance);
		lvExtendBalance=(LvExtendBalance) this.doService("LvExtendBalanceService", "get", dto);
		this.getRequest().setAttribute("lvExtendBalance", lvExtendBalance);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.view() method begin*****");
		}
		return VIEW;
	}
	

	/**
	 * 
	 * @Method: exportBalance 
	 * @Description:  [导出结算清单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 上午11:53:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 上午11:53:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return
	 * @throws FileNotFoundException 
	 * @return String
	 */
	public String exportBalance() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.exportOrder() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("ids",ids);
		List list = (List) this.doService("LvExtendBalanceService", "exportBalance", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.exportOrder() method end*****");
		}
		return "excel";
	}
	
	public String exportDetailsBalance()throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.exportOrder() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("ids",ids);
		List list = (List) this.doService("LvExtendBalanceService", "exportDetailsBalance", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvExtendBalanceAction.exportOrder() method end*****");
		}
		return "excelDetails";
	}
	
	public void deleteTempFile(HttpServletRequest request){
		//删除文件上传临时文件夹下面所有临时文件
		String pathTmp = request.getRealPath("/temp");
		File fileTemp=new File(pathTmp);
		if(fileTemp.isDirectory()){
			 File[] f=fileTemp.listFiles();
			 for (File fs : f) {
				boolean isFlag=fs.delete();
				if (logger.isInfoEnabled()) {
					logger.info("删除文件是否成功:"+fs.getName()+"->"+isFlag);
				}
			}
		}
	}
	

	
	public LvExtendBalance getLvExtendBalance() {
		return lvExtendBalance;
	}

	public void setLvExtendBalance(LvExtendBalance lvExtendBalance) {
		this.lvExtendBalance = lvExtendBalance;
	}
	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Date getVersionTime() {
		return versionTime;
	}

	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}

}
