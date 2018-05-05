package com.lshop.manage.lvApp.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductActivity;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvActivity.action.LvActivityAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvApp.action.LvAppAction.java]  
 * @ClassName:    [LvAppAction]   
 * @Description:  [产品应用信息-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-6 上午10:57:35]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-6 上午10:57:35]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0] 
 */
@Controller("LvAppAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAppAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAppAction.class);
	private LvApp lvApp;
	
	private File img;//产品图片
	private String imgFileName;//产品名称
	private String imgSrc;
	/**
     * 
     * @Method: list 
     * @Description:  [分页查询产品应用信息列表]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-4 上午11:56:55]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-4 上午11:56:55]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductActivityAction.list() method begin*****");
		}

		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvApp", lvApp);
		//产品活动信息分页集合对象
		page=(Pagination) this.doService("LvAppService", "getList", dto);

		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到编辑产品应用信息页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.befEdit() method begin*****");
		}
		//查询产品活动信息信息
		dto.put("lvApp", lvApp);
		lvApp=(LvApp) this.doService("LvAppService", "get", dto);
		this.getRequest().setAttribute("lvApp", lvApp);
		
		//查询应用类型
		dto.put("typeStoreId", lvApp.getStoreId());
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAppAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.edit() method begin*****");
		}
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			String imgPath=(String) this.doService("LvAppService", "upload", dto);
			lvApp.setAppImage(imgPath);
			lvApp.setAppImage(imgPath);
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvApp.setModifyUserId(users.getId());
		lvApp.setModifyUserName(users.getUserName());
		lvApp.setModifyTime(new Date());
		dto.put("lvApp", lvApp);
		Boolean isFlag=(Boolean) this.doService("LvAppService", "update", dto);
		if(!isFlag){
			json.setMessage("该应用名称已经存在，请重新输入！");
			json.setStatusCode(300);
		}

		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.edit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: befAdd 
	 * @Description:  [跳转到产品应用信息新增页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAdd(){
		//查询应用类型
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAppAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		return "befAdd";
	}
	/**
	 * 
	 * @Method: add 
	 * @Description:  [新增产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.add() method begin*****");
		}	
		if(img!=null){
			dto.put("img", img);
			dto.put("imgFileName", imgFileName);
			dto.put("request", this.getRequest());
			String imgPath=(String) this.doService("LvAppService", "upload", dto);
			lvApp.setAppImage(imgPath);
		}
		lvApp.setCode(CodeUtils.getCode());//code设置
		lvApp.setCreateTime(new Date());   //创建时间
		dto.put("lvApp", lvApp);
		Boolean isFlag=(Boolean) this.doService("LvAppService", "save", dto);
		if(!isFlag){
			json.setMessage("该应用名称已经存在，请重新输入！");
			json.setStatusCode(300);
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.add() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看产品应用信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.view() method begin*****");
		}
		//查询产品活动信息信息
		dto.put("lvApp", lvApp);
		lvApp=(LvApp) this.doService("LvAppService", "get", dto);
		this.getRequest().setAttribute("lvApp", lvApp);
		
		//查询应用类型
		List<LvProductType> typeList=(List<LvProductType>) this.doService("LvProductTypeService", "getAppAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.view() method end*****");
		}
		
		//根据登录用户判断用户对应店铺的域名信息
		BaseUsers user=	(BaseUsers) getSession().getAttribute("USER");
		String nowmain=request.getServerName();
		String domain="";
		for(Map.Entry<String, String> entry:Constants.STORE_IDS.entrySet()){   
		    if(user.getStoreFlag().equals(entry.getValue())){
		    	domain=entry.getKey();
		    	break;
		    }
		}
		this.getRequest().setAttribute("domain", domain);
		
		return "view";
	}
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.del() method begin*****");
		}
		dto.put("lvApp", lvApp);
		this.doService("LvAppService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.del() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除产品应用信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:57:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:57:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvApp=new LvApp();
				lvApp.setId(id);
				dto.put("lvApp", lvApp);
				//删除model
				this.doService("LvAppService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAppAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: toAppType 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-20 上午11:37:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-20 上午11:37:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toAppType(){
		PrintWriter out = null;
		dto.put("typeStoreId", lvApp.getStoreId());
		if(lvApp!=null&&ObjectUtils.isNotEmpty(lvApp.getStoreId())){
			List<LvProductType> list=(List<LvProductType>) this.doService("LvProductTypeService", "getAppAll", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvProductType lvProductType=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("productTypeCode", lvProductType.getCode());
					map.put("productTypeName", lvProductType.getTypeName());
					listTmp.add(map);
					
				}
				mapTmp.put("listTmp", listTmp);
				try {
					response.setContentType("text/html;charset=UTF-8");   
					response.setCharacterEncoding("UTF-8");
				    out=this.getResponse().getWriter();
				    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
				    logger.info("json_message"+jsonTmp);
					out.print(jsonTmp );
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	 * @Method: upload 
	 * @Description:  [文件上传]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	private String upload(){
		//String picBath = this.getText("cod.upload.picture.url");
		String basepath=request.getRealPath("/res/images/app");//文件上传资源路径
		String imgName=System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return "/res/images/app/"+imgName;
	}
	
	public LvApp getLvApp() {
		return lvApp;
	}

	public void setLvApp(LvApp lvApp) {
		this.lvApp = lvApp;
	}
	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}


}
