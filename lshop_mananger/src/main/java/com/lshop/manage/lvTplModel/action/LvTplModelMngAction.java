/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplModel.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.file.FileManager;
import com.lshop.common.file.FileManagerImpl;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplDetailPublic;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.ExcelFileHelp;
import com.lshop.manage.common.action.BaseManagerAction;

import freemarker.template.TemplateException;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvTplModelMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvTplModelMngAction  extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvTplModelMngAction.class);
    private String fileName;//文件名
    private File file;//文件
    private String fileContentType;//文件类型
    private Integer tplFlag;//是否覆盖模板标识
    private Integer contentFlag; //是否覆盖内容标识
    private Integer autoTplFlag;//是否自动生成模板页标识
    private Integer autoContentFlag;//是否自动生成内容页标识
	private LvTplModel lvTplModel=new LvTplModel();
   
	public LvTplModel getLvTplModel() {
		return lvTplModel;
	}

	public void setLvTplModel(LvTplModel lvTplModel) {
		this.lvTplModel = lvTplModel;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.save() method begin*****");
		}
		 lvTplModel.setIsDefault((short)0);
		 lvTplModel.setCreateTime(new Date());
		 lvTplModel.setCode(CodeUtils.getCode());//设置关联关系值
		 dto.put("model", lvTplModel);
		 Boolean bflag=(Boolean)this.doService("LvTplModelService", "findCheckName", dto);
		 if(false==bflag){
		 lvTplModel=(LvTplModel) this.doService("LvTplModelService", "save", dto);
		 String templatePath=this.getText("lshop.web.path")+"/"+lvTplModel.getStoreFlag()+"/tpl/template"+lvTplModel.getId();//template+"模板块的id号来区分店铺所选择的模板块"
		 File file=new File(templatePath);
		 if(!file.exists()){//生成模板文件目录
				file.mkdir();
			}
		 json.setStatusCode(200);
		 }else{
			 json.setMessage("该模板类的名称存在！");
			 json.setStatusCode(json.STATUS_CODE_ERROR);
		 }
		 if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.save() method end*****");
		 }

		return AJAX;
	}

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvTplModel);
		
		page = (Pagination)this.doService("LvTplModelService", "findPage", dto);

		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.view() method begin*****");
		}
		dto.put("model", lvTplModel);
		lvTplModel = (LvTplModel)this.doService("LvTplModelService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvTplModel);
		lvTplModel = (LvTplModel)this.doService("LvTplModelService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.edit() method begin*****");
		}
	    BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
	    lvTplModel.setModifyTime(new Date());
	    lvTplModel.setModifyUserId(users.getId());
		lvTplModel.setModifyUserName(users.getUserName());
		dto.put("model", lvTplModel);
		this.doService("LvTplModelService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.del() method begin*****");
		}
		dto.put("model", lvTplModel);
		//删除model
		Integer isuccess=(Integer)this.doService("LvTplModelService", "del", dto);
		if(isuccess==2){
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("该模板已存在明细数据，不能删除！");
		}else{
		json.setStatusCode(200);
		}
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvTplModel.setId(Integer.parseInt(temp_ids[i]));
				dto.put("model", lvTplModel);
				//删除model
				this.doService("LvTplModelService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.delList() method end*****");
		}
		return AJAX;
	}

    /**
     * 设为默认模板
     * @return
     */
	public String doDefault()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.doDefault method begin*****");
		}
		dto.put("model", lvTplModel);
		this.doService("LvTplModelService", "updateDefaultSet", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.doDefault method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 根据模板生成对应的页面
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public String doBuild()throws ActionException, IOException, TemplateException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelMngAction.doBuild method begin*****");
		}
		    dto.put("model", lvTplModel);
		    lvTplModel = (LvTplModel)this.doService("LvTplModelService", "get", dto);
		    String templatePath=this.getText("lshop.web.path")+"/"+lvTplModel.getStoreFlag()+"/tpl/template"+lvTplModel.getId();//template+"模板块的id号来区分店铺所选择的模板块"
			File file=new File(templatePath);
		    if(!file.exists()){//生成模板文件目录
				file.mkdirs();
			 }
		    dto.clear();
		    dto.put("model", lvTplModel);
		    List <LvTplDetail> detailList =(List<LvTplDetail>)this.doService("LvTplDetailService", "findAll", dto);
		    /*****************************生成模板文件**********************************/
		    if(detailList!=null&&detailList.size()>0){
				for(LvTplDetail detail :detailList){
					String nPath=detail.getPagePath();
					String nDir="";
					String nFile="";
					if(nPath.indexOf("/")!=-1){
						nDir=nPath.substring(0, nPath.lastIndexOf("/"));
						File f=new File(templatePath+"/"+nDir);
						if(!f.exists()){
							f.mkdirs();
						}
					}
					FileManager fileManager=new FileManagerImpl();
					fileManager.save(templatePath+"/"+nPath, detail.getContent());//保存文件
				}
			}
		  json.setStatusCode(200);
		  json.setCallbackType(null);
		  if (logger.isInfoEnabled()){
				logger.info("***** LvTplModelMngAction.doBuild method end*****");
			}
		return AJAX;
	}
	/**
	 * 导入excel 模板内容数据
	 */
	public String importExcel()throws ActionException{
		if(file!=null){
		if(!"application/vnd.ms-excel".equals(this.fileContentType)){
			json.setMessage("选择的不是excel文件!");
			json.setStatusCode(json.STATUS_CODE_ERROR);
			return AJAX;
		}
		List<String[]>list=ExcelFileHelp.parseExcel(file);
		if(list.size()>1){
			list.remove(0);//去掉第一行数据
			
			dto.put("list", list);
			dto.put("rootPath", this.getText("lshop.web.path"));
			dto.put("tplFlag", tplFlag);
			dto.put("contentFlag", this.contentFlag);
			dto.put("autoTplFlag", this.autoTplFlag);
			dto.put("autoContentFlag", this.autoContentFlag);
			this.doService("LvTplModelService", "saveUpdateImportExcel", dto);
		}
		json.setMessage("导入数据成功!");
		}else{
			json.setMessage("导入数据失败!");
			json.setStatusCode(json.STATUS_CODE_ERROR);
		}
		return AJAX;
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Integer getTplFlag() {
		return tplFlag;
	}

	public void setTplFlag(Integer tplFlag) {
		this.tplFlag = tplFlag;
	}

	public Integer getContentFlag() {
		return contentFlag;
	}

	public void setContentFlag(Integer contentFlag) {
		this.contentFlag = contentFlag;
	}

	public Integer getAutoTplFlag() {
		return autoTplFlag;
	}

	public void setAutoTplFlag(Integer autoTplFlag) {
		this.autoTplFlag = autoTplFlag;
	}

	public Integer getAutoContentFlag() {
		return autoContentFlag;
	}

	public void setAutoContentFlag(Integer autoContentFlag) {
		this.autoContentFlag = autoContentFlag;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	

	
	
}
