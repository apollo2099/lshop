/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStore.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.file.FileManager;
import com.lshop.common.file.FileManagerImpl;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreAddress;
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.pojo.logic.LvTplModelPublic;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvStoreMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvStoreMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvStoreMngAction.class);
	private LvStore lvStore = new LvStore();
	private LvStoreAddress lvStoreAddress;
	private LvTplModelPublic lvTplModelPublic;
	private File img;
	private String imgFileName;
	private Integer countryId;
	private Integer currentIndex;
	private Integer soureFlag;
	

	/**
	 * 查看店铺基本资料
	 */
	public String befstoreInfo(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befstoreInfo() method begin*****");
		}
		if(ObjectUtils.isEmpty(currentIndex)){
			currentIndex=0;
			this.getRequest().setAttribute("currentIndex", currentIndex);
		}
		
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		
		List<LvStore> list=new ArrayList<LvStore>();
		for (LvStore lvStore : shopList) {
			//查询店铺所对应的默认模板模块
			LvTplModel model=new LvTplModel();
			model.setStoreFlag(lvStore.getStoreFlag());
			dto.put("model", model);
			LvTplModel tplModel=(LvTplModel)this.doService("LvTplModelService", "getDefaultTplModel", dto);
			if (ObjectUtils.isNotEmpty(tplModel)) {
				lvStore.setDefaultTplModel(tplModel.getModelName());
			}
			list.add(lvStore);
		}
		this.getRequest().setAttribute("shopList", list);


		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
    	
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befstoreInfo() method end*****");
		}
		return "storeInfo";
	}
	
	/**
	 * 查看商城基本资料
	 */
	public String befShopInfo(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befstoreInfo() method begin*****");
		}
		
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		
		if(ObjectUtils.isEmpty(currentIndex)){
			currentIndex=0;
			this.getRequest().setAttribute("currentIndex", currentIndex);
		}
//		this.setFlag();
//		dto.put("storeFlag", this.getFlag());//获取店铺标志
//		lvStore = (LvStore)this.doService("LvStoreService", "findStore", dto);
//		dto.clear();
//		//查询店铺所对应的默认模板模块
//		LvTplModel model=new LvTplModel();
//		model.setStoreFlag(this.getFlag());
//		dto.put("model", model);
//		LvTplModel tplModel=(LvTplModel)this.doService("LvTplModelService", "getDefaultTplModel", dto);
//		this.getRequest().setAttribute("tplModel", tplModel);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befstoreInfo() method end*****");
		}
		return "shopInfo";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		String lshopDemain = this.getText("lshop.domain.base.name");
		this.getRequest().setAttribute("lshopDemain", lshopDemain);
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		
		List<LvTplModelPublic> modelPublicList=(List)this.doService("LvTplModelPublicService", "findAll", dto);
		this.getRequest().setAttribute("modelPublicList", modelPublicList);
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 */
	public String save()throws ActionException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.save() method begin*****");
		}
		lvStore.setIsCod((short)0);
		lvStore.setIsdel((short)0);
		lvStore.setCreateTime(new Date());
		lvStore.setDomainName(lvStore.getDomainName().trim());
		if(lvStore.getDomainName().indexOf(".")<=0){
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("请输入正确的网站域名！");
			return AJAX;
		}
		
		lvStore.setStoreFlag(lvStore.getDomainName().substring(0,lvStore.getDomainName().indexOf(".")));
		dto.put("model", lvStore);
		
		Boolean flag=(Boolean)this.doService("LvStoreService", "checkStoreFlag", dto);
		if(flag==true){//检查商铺标识是否在在
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("该商铺标志已存在，添加失败！");
			return AJAX;
		}
		if(ObjectUtils.isNotEmpty(lvStore.getDomainName())){
			flag=(Boolean)this.doService("LvStoreService", "checkDomain", dto);
			if(flag==true){
				json.setStatusCode(json.STATUS_CODE_ERROR);
				json.setMessage("该商铺域名已存在，添加失败！");
				return AJAX;
			}
		}
		
		flag=(Boolean)this.doService("LvStoreService", "checkStoreNumber", dto);
		if(flag==true){
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("该商铺编号已存在，添加失败！");
			return AJAX;
		}
		if(ObjectUtils.isNotEmpty(lvStore.getName())){
			flag=(Boolean)this.doService("LvStoreService", "checkStoreName", dto);
			if(flag==true){
				json.setStatusCode(json.STATUS_CODE_ERROR);
				json.setMessage("该店铺名称已存在，添加失败！");
				return AJAX;
			}
		}
		
		lvStore.setCode(CodeUtils.getCode());//设置关联关系值
	    BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvStore.setCreateUserId(users.getId());
		lvStore.setCreateUserName(users.getUserName());
		String rootPath=this.getText("lshop.web.path");//获取根目录
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(lvStore.getStoreFlag());//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;  
		String resPath = this.getText("res.domain.path"); //获取资源文件路径
		String filepath=resPath+"/upload/"+lvStore.getStoreFlag()+"/res/images";//文件上传资源路径
				
//		String rph="/res/upload/logo";
//		String path=rootPath+"/"+lvStore.getStoreFlag()+rph;
		File file=new File(filepath);
		if(!file.exists()){//判断文件目录是否存在，不存在就重新创建一个目录结构
			file.mkdirs();
		}
		//上传图片
		 if(img!=null){
			 String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			if (!(type.toLowerCase().equals(".jpg")
					|| type.toLowerCase().equals(".jpeg")
					|| type.toLowerCase().equals(".png")
					|| type.toLowerCase().equals(".gif"))) {
				json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
				json.setStatusCode(300);
				return AJAX;
			}
			if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
				json.setMessage("文件大小过大，请压缩至2M以内！");
				json.setStatusCode(300);
				return AJAX;
			}
 
			 SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	         String tempName=format.format(new Date())+new Random().nextInt(10000);
	         imgFileName=tempName+type;
			 FileUpload.upload(img, filepath, imgFileName);
			 lvStore.setLogo(resDomain+"/upload/"+lvStore.getStoreFlag()+"/res/images/"+imgFileName);
			}
	   dto.put("model", lvStore);
	   dto.put("lvTplModelPublic", lvTplModelPublic);
	   LvTplModel lvTplModel =(LvTplModel)this.doService("LvStoreService", "save", dto);//返回店铺对应模板对象
	   //刷新店铺编号
	   this.doService("LvStoreService", "updateStoreNumber", dto);
	   
	   //刷新商城与商家关系
	   this.doService("LvStoreService", "updateStoreIds", dto);
	   
	   String templatePath=rootPath+"/"+lvStore.getStoreFlag()+"/tpl/template"+lvTplModel.getId();//template+"模板块的id号来区分店铺所选择的模板块"
		file=new File(templatePath);
	   if(!file.exists()){//生成模板文件目录
			file.mkdirs();
		}
	    dto.clear();
	    dto.put("model",lvTplModel);
	    List <LvTplDetail> detailList =(List<LvTplDetail>)this.doService("LvTplDetailService", "findAll", dto);
	    /*****************************生成模板文件**********************************/
	    if(detailList!=null&&detailList.size()>0){
			for(LvTplDetail detailPublic :detailList){
				String nPath=detailPublic.getPagePath();
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
				fileManager.save(templatePath+"/"+nPath, detailPublic.getContent());//保存文件
			}
		}
		json.setStatusCode(200);//操作成功
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.save() method end*****");
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
			logger.info("***** LvStoreMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvStore);
		page = (Pagination)this.doService("LvStoreService", "findPage", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		
		
		//查询店铺区域信息
		List<LvStoreArea>  storeAreaList=(List<LvStoreArea>) this.doService("LvStoreAreaService","findAll", dto);
		this.getRequest().setAttribute("storeAreaList", storeAreaList);
		
		//商城域名
		String lshopDemain = this.getText("lshop.domain.base.name");
		this.getRequest().setAttribute("lshopDemain", lshopDemain);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.list() method end*****");
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
			logger.info("***** LvStoreMngAction.view() method begin*****");
		}
		dto.put("model", lvStore);
		lvStore = (LvStore)this.doService("LvStoreService", "get", dto);
		dto.clear();
		//查询店铺所对应的默认模板模块
		LvTplModel model=new LvTplModel();
		model.setStoreFlag(lvStore.getStoreFlag());
		dto.put("model", model);
		LvTplModel tplModel=(LvTplModel)this.doService("LvTplModelService", "getDefaultTplModel", dto);
		this.getRequest().setAttribute("tplModel", tplModel);
		
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.view() method end*****");
		}
		return "view";
	}
	
	public String shopView(){
		dto.put("storeFlag", lvStore.getStoreFlag());
		lvStore = (LvStore)this.doService("LvStoreService", "findStore", dto);
		dto.put("model", lvStore);
		lvStore = (LvStore)this.doService("LvStoreService", "get", dto);
		dto.clear();
		//查询店铺所对应的默认模板模块
		LvTplModel model=new LvTplModel();
		model.setStoreFlag(lvStore.getStoreFlag());
		dto.put("model", model);
		LvTplModel tplModel=(LvTplModel)this.doService("LvTplModelService", "getDefaultTplModel", dto);
		this.getRequest().setAttribute("tplModel", tplModel);
		
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befEdit() method begin*****");
		}
		//取配置数据
		String lshopDemain = this.getText("lshop.domain.base.name");
		this.getRequest().setAttribute("lshopDemain", lshopDemain);
		//
		dto.put("model", lvStore);
		lvStore = (LvStore)this.doService("LvStoreService", "get", dto);
		this.getRequest().setAttribute("lvStore", lvStore);
		//查询店铺所对应的默认模板模块
		LvTplModel model=new LvTplModel();
		model.setStoreFlag(lvStore.getStoreFlag());
		dto.put("model", model);
		LvTplModel tplModel=(LvTplModel)this.doService("LvTplModelService", "getDefaultTplModel", dto);
		this.getRequest().setAttribute("tplModel", tplModel);
		
		List<LvTplModelPublic> modelPublicList=(List)this.doService("LvTplModelPublicService", "findAll", dto);
		this.getRequest().setAttribute("modelPublicList", modelPublicList);
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String edit()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.edit() method begin*****");
		}
		
		
	    BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvStore.setModifyUserId(users.getId());
		lvStore.setModifyUserName(users.getUserName());
		lvStore.setModifyTime(new Date());//设置修改时间
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(lvStore.getStoreFlag());//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;  
		String resPath = this.getText("res.domain.path"); //获取资源文件路径
		String filepath=resPath+"/upload/"+lvStore.getStoreFlag()+"/res/images";//文件上传资源路径
				
//		String rph="/res/upload/logo";
//		String path=this.getText("lshop.web.path")+"/"+lvStore.getStoreFlag()+rph;
		File file=new File(filepath);
		if(!file.exists()){//判断文件目录是否存在，不存在就重新创建一个目录结构
			file.mkdirs();
		}
		//上传图片
		 if(img!=null){//是否选择上传图片
			 String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
			 if(!(type.toLowerCase().equals(".jpg")||type.toLowerCase().equals(".jpeg")||
		              type.toLowerCase().equals(".png")||type.toLowerCase().equals(".gif"))){
		        	 json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
		        	 json.setStatusCode(300);
		        	 return AJAX;
		         }
		         if(new FileInputStream(img).available()>1024*1024*2){
		        	 json.setMessage("文件大小过大，请压缩至2M以内！");
		        	 json.setStatusCode(300);
		        	 return AJAX;
		         }
			 SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	         String tempName=format.format(new Date())+new Random().nextInt(10000);
	         imgFileName=tempName+type;
	         FileUpload.upload(img, filepath, imgFileName);
			 lvStore.setLogo(resDomain+"/upload/"+lvStore.getStoreFlag()+"/res/images/"+imgFileName);
		 }else{
			    dto.put("model", lvStore);
				LvStore tempStore = (LvStore)this.doService("LvStoreService", "get", dto);
				if(tempStore.getLogo()!=null){
					lvStore.setLogo(tempStore.getLogo());
				}
		 }
		
		dto.put("lvStore", lvStore);
		dto.put("model", lvStore);
		dto.put("lvStoreAddress", lvStoreAddress);
		if(!lvStore.getOldName().equals(lvStore.getName())){
			Boolean flag=(Boolean)this.doService("LvStoreService", "checkStoreName", dto);
			if(flag==true){
				json.setStatusCode(json.STATUS_CODE_ERROR);
				json.setMessage("该店铺名称已存在，添加失败！");
				return AJAX;
			}
		}
		
		//店铺状态变化时，目录转移
		if(ObjectUtils.isNotEmpty(lvStore.getStatus())
				&&ObjectUtils.isNotEmpty(lvStore.getOldStatus())
				&&!lvStore.getStatus().equals(lvStore.getOldStatus())){
			String rootPath=this.getText("lshop.web.path");//获取根目录
			String destPath=this.getText("lshop.web.disable.path");//临时存放目录
			lvStore=(LvStore) this.doService("LvStoreService", "get", dto);
			try {
				String srcDirPath=null;
				String destDirPath=null;
				//启用停用时文件资料转移
				if(lvStore!=null){
					if(ObjectUtils.isNotEmpty(lvStore.getStatus())&&lvStore.getStatus()==1){
						srcDirPath=rootPath;
						destDirPath=destPath;
					}else if(ObjectUtils.isNotEmpty(lvStore.getStatus())&&lvStore.getStatus()==0){
						srcDirPath=destPath;
						destDirPath=rootPath;
					}
					String pathTmp=srcDirPath+"/"+lvStore.getStoreFlag();
					File srcDirFile=new File(pathTmp);
					File destDirFile=new File(destDirPath);
					if(srcDirFile.exists()&&destDirFile.exists()){
						FileUtils.copyDirectoryToDirectory(srcDirFile, destDirFile);
						FileUtils.deleteDirectory(srcDirFile);
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		this.doService("LvStoreService", "update", dto);
		if(soureFlag==1){
			json.setForwardUrl("/manager/mainfream/lvStoreMngAction!befShopInfo.action?currentIndex="+currentIndex+"&json.navTabId="+json.getNavTabId());
			json.doForward();	
		}else if(soureFlag==2){
			json.setForwardUrl("/manager/mainfream/lvStoreMngAction!befstoreInfo.action?currentIndex="+currentIndex+"&json.navTabId="+json.getNavTabId());
			json.doForward();
		}
		
		//刷新商城与商家关系
		this.doService("LvStoreService", "updateStoreIds", dto);
		
		json.setStatusCode(json.STATUS_CODE_OK);
    	json.setMessage("修改成功！");
    	
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.edit() method end*****");
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
			logger.info("***** LvStoreMngAction.del() method begin*****");
		}
		dto.put("model", lvStore);
		//删除model
		this.doService("LvStoreService", "del", dto);

		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.del() method end*****");
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
			logger.info("***** LvStoreMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvStore.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvStore);
				//删除model
				this.doService("LvStoreService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setMessage("删除成功！");
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public String updateDomain()throws ActionException{
		Integer isuccess=(Integer)this.doService("LvStoreService", "updateStoreIds", dto);
		if(isuccess==1){
		json.setStatusCode(200);
		json.setMessage("更新成功！");
		}else{
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("更新失败！");
		}
		json.setCallbackType(null);
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: initStoreData 
	 * @Description:  [店铺初始化数据一键赋值]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-29 下午05:33:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-29 下午05:33:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String initStoreData(){
		if(lvStore!=null){
			if(ObjectUtils.isEmpty(lvStore.getParentCode())){
				json.setMessage("当前店铺不属于任何商城，不能进行初始化操作 ！");
				json.setStatusCode(200);
				return AJAX;
			}
		}
		
		dto.put("lvStore", lvStore);
		this.doService("InitStoreDataService", "initData", dto);
		json.doNavTabTodo();
		return AJAX;
	}
	
	public String updateStoreStatus(){
		String rootPath=this.getText("lshop.web.path");//获取根目录
		String destPath=this.getText("lshop.web.disable.path");//临时存放目录
		dto.put("model", lvStore);
	
		lvStore=(LvStore) this.doService("LvStoreService", "get", dto);
		try {
			String srcDirPath=null;
			String destDirPath=null;
			//启用停用时文件资料转移
			if(lvStore!=null){
				if(ObjectUtils.isNotEmpty(lvStore.getStatus())&&lvStore.getStatus()==1){
					srcDirPath=rootPath;
					destDirPath=destPath;
				}else if(ObjectUtils.isNotEmpty(lvStore.getStatus())&&lvStore.getStatus()==0){
					srcDirPath=destPath;
					destDirPath=rootPath;
				}
				String path=srcDirPath+"/"+lvStore.getStoreFlag();
				File srcDirFile=new File(path);
				File destDirFile=new File(destDirPath);
				if(srcDirFile.exists()&&destDirFile.exists()){
					FileUtils.copyDirectoryToDirectory(srcDirFile, destDirFile);
					FileUtils.deleteDirectory(srcDirFile);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//更新订单
		this.doService("LvStoreService", "updateStoreStatus", dto);
		json.doNavTabTodo();
		
		return AJAX;
	}
	
	public String befOrderValue(){
		dto.put("model", lvStore);
		lvStore = (LvStore)this.doService("LvStoreService", "get", dto);
		this.getRequest().setAttribute("lvStore", lvStore);
		return "befOrderValue";
	}
	
	public String updateOrderValue(){
		dto.put("model", lvStore);
		this.doService("LvStoreService", "updateOrderValue", dto);
		return AJAX;
	}
	
	public String befStoreArea(){
		return "befStoreArea";
	}
	
	public String updateStoreArea(){
	    dto.put("ids", ids);
		dto.put("model", lvStore);
		this.doService("LvStoreService", "updateStoreArea", dto);
		return AJAX;
	}
	
	public String befStoreHot(){
		return "befStoreHot";
	}
	
	public String updateStoreHot(){
		 dto.put("ids", ids);
		 dto.put("model", lvStore);
		 this.doService("LvStoreService", "updateStoreHot", dto);
		 return AJAX;
	}
	
	public String befStoreCommend(){
		return "befStoreCommend";
	}
	
	public String updateStoreCommend(){
		 dto.put("ids", ids);
		 dto.put("model", lvStore);
		 this.doService("LvStoreService", "updateStoreCommend", dto);
		 return AJAX;
	}
	
	
	
	/**
	 * 
	 * @Method: toCity 
	 * @Description:  [根据店铺区域国家编号查询对应国家的城市信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-6 上午11:25:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-6 上午11:25:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toCity(){
		PrintWriter out = null;
		dto.put("countryId", countryId);
		if(ObjectUtils.isNotEmpty(countryId)){
			List<LvStoreArea> list=(List<LvStoreArea>) this.doService("LvStoreAreaService", "findAllCity", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvStoreArea lvArea=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("city", lvArea.getAreaName());
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
    
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public LvTplModelPublic getLvTplModelPublic() {
		return lvTplModelPublic;
	}

	public void setLvTplModelPublic(LvTplModelPublic lvTplModelPublic) {
		this.lvTplModelPublic = lvTplModelPublic;
	}

	public LvStore getLvStore() {
		return lvStore;
	}

	public void setLvStore(LvStore lvStore) {
		this.lvStore = lvStore;
	}

	public LvStoreAddress getLvStoreAddress() {
		return lvStoreAddress;
	}

	public void setLvStoreAddress(LvStoreAddress lvStoreAddress) {
		this.lvStoreAddress = lvStoreAddress;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	
	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
	public Integer getSoureFlag() {
		return soureFlag;
	}

	public void setSoureFlag(Integer soureFlag) {
		this.soureFlag = soureFlag;
	}
}
