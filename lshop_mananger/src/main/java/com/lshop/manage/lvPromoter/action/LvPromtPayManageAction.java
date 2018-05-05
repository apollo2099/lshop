package com.lshop.manage.lvPromoter.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.FileUpload;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvExpenseFund;
import com.lshop.common.pojo.logic.LvMaterial;
import com.lshop.common.pojo.logic.LvPromtContent;
import com.lshop.common.pojo.logic.LvVideo;
import com.lshop.common.util.ExcelFileHelp;

@SuppressWarnings({"serial","unchecked"})
@Controller("LvPromtPayManageAction")
@Scope("prototype")
public class LvPromtPayManageAction extends BaseAction
{ 
	private static final Log logger = LogFactory.getLog(LvPromtPayManageAction.class);
	private LvVideo video;
	private LvPromtContent promtContent;
	private LvPromtContent promtContent2;
	private LvExpenseFund expenseFund;
	private int filesize;
	private BufferedInputStream excelStream;
	private File smallimg;
	private File bigimg;
	private File compress;
	private String smallimgFileName;
	private String bigimgFileName;
	private String compressFileName;
	private Date versionTime;
	
	
	

public String list() throws ActionException
  {
	  dto.put("page",page);
	  dto.put("keyword",keyword);
	  page=(Pagination)super.doService("LvPromtManageService","getPreparePayList", dto);
	  return LIST;
  }
  
  public String preparePay() throws ActionException
  {
	  dto.put("id", request.getParameter("id"));
	  super.doService("LvPromtManageService", "preparePay",dto);
	  json.doNavTabTodo();
	  return AJAX;
  }
  
  public String payList() throws ActionException
  {
	  dto.put("page", page);
	  dto.put("keyword", keyword);
	  page=(Pagination)super.doService("LvPromtManageService", "getPayList", dto);
	  
	  return "payList";
  }
  
   
  public String payTrue() throws ActionException
  {
	  BaseUsers user =(BaseUsers)getSession().getAttribute("USER");
	  dto.put("USER", user);
	  dto.put("uid", request.getParameter("uid"));
	  dto.put("id",request.getParameter("id"));
	  dto.put("versionTime", versionTime);
	  Boolean isFlagTmp=(Boolean) this.doService("LvPromtManageService", "isVersionFailure", dto);
	  if(!isFlagTmp){
		  json.setMessage("版本失效,刷新当前数据列表!");
		  json.doNavTabTodo();
		  return AJAX;
	  }
	  Boolean isFlag=(Boolean) super.doService("LvPromtManageService", "pay", dto);
	  if(!isFlag){
		  json.setStatusCode(300);
		  json.setMessage("当前无可生成的结算清单数据");
	  }else{
		  json.setStatusCode(200);
		  json.setMessage("生成结算单成功！");
	  }
	  
	  json.doNavTabTodo();
	  return AJAX;
  }
  
  public String getOrderList() throws ActionException
  {
	  dto.put("page",page);
	  dto.put("keyword", request.getParameter("keyword"));
	  dto.put("uid", request.getParameter("uid"));
	  page=(Pagination) super.doService("LvPromtManageService", "getOrderList", dto);
	  return "orderList";
  }
  
  @SuppressWarnings("deprecation")
  public String export() throws ActionException, IOException, RowsExceededException, WriteException
  {
	   //删除文件下载下面所有的临时文件
	   deleteTempFile(request);
	   
	   dto.put("uid", request.getParameter("uid"));
	   dto.put("ids", ids);
		List list = (List)this.doService("LvPromtManageService","export",dto);
		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
		ExcelFileHelp.create(path, list);
		File file = new File(path);
		try {
			filesize=(int) file.length();
			excelStream = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e){
		}
		file.delete();
		return "excel";
  }
  
  @SuppressWarnings("deprecation")
  public String exportExl() throws ActionException, IOException, RowsExceededException, WriteException
  {
	  dto.put("uid", request.getParameter("uid"));
	   dto.put("ids", this.getIds());
		List list = (List)this.doService("LvPromtManageService","exportExl",dto);
		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
		ExcelFileHelp.create(path, list);
		File file = new File(path);
		try 
		{
			filesize=(int) file.length();
			excelStream = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
		}
		file.delete();
		return "exceldetail";
  }
  
  @SuppressWarnings("deprecation")
  public String  exportOrder() throws ActionException, IOException, RowsExceededException, WriteException
  {
	  dto.put("uid", request.getParameter("uid"));
	  dto.put("ids", this.getIds());
		List list = (List)this.doService("LvPromtManageService","exportOrder",dto);
		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
		ExcelFileHelp.create(path, list);
		File file = new File(path);
		try 
		{
			filesize=(int) file.length();
			excelStream = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
		}
		file.delete();
	  return "orderdetail";
  }
  
  public String getFundList() throws ActionException
  {
	  dto.put("promtCode",request.getParameter("promtCode"));
	  dto.put("page",page);
	  page=(Pagination)super.doService("LvPromtManageService", "getFundList", dto);
	  return "fundlist";
  }
  
  public String befExpense() throws ActionException
  {
	  dto.put("uid", request.getParameter("uid"));
	  super.doService("LvPromtManageService", "getExpenseInfo", dto);
	  request.setAttribute("enablefund", dto.getAsString("enablefund"));
	  request.setAttribute("realname", dto.getAsString("realname"));
	  request.setAttribute("uid", request.getParameter("uid"));
	  return "befexpense";
  }
  
  public String expenseFund() throws ActionException
  {
	  dto.put("uid", request.getParameter("uid"));
	  dto.put("expenseFund",expenseFund);
	  super.doService("LvPromtManageService", "expenseFund", dto);
	  return AJAX;
  }
  
  public String getFundDetail() throws ActionException
  {
	  dto.put("page",page);
	  dto.put("uid", request.getParameter("uid"));
	  page=(Pagination)super.doService("LvPromtManageService", "getFundDetail", dto);
	 
	  request.setAttribute("realname", dto.getAsString("realname"));
	  request.setAttribute("totalfund",dto.getAsString("totalfund"));
	  request.setAttribute("totalnum", dto.getAsString("totalnum"));
	  request.setAttribute("disablefund",dto.getAsString("disablefund"));
	  request.setAttribute("enablefund",dto.getAsString("enablefund"));
	  return "expenselist";
  }
  
  public String getContentList() throws ActionException
  {
	  dto.put("page",page);
	  page=(Pagination)super.doService("LvPromtManageService", "getContentList", dto);
	
	  
	  return "contentlist";
  }
  
  public String bfadd() throws ActionException
  {
	  return "bfadd";
  }
  
  public String add() throws ActionException
  {
	  Integer type=Integer.valueOf(request.getParameter("type"));
	  if(type==1)
	  {
		dto.put("model", 1);
	    dto.put("promtContent", promtContent);
	  }
	  else 
	  {
		dto.put("model", 2);
	    dto.put("promtContent", promtContent2);
	  }
	  super.doService("LvPromtManageService", "add", dto);
	  return AJAX;
  }
  
  public String delete() throws ActionException
  {
	  dto.put("ids",ids);
	  super.doService("LvPromtManageService", "delete", dto);
	  json.setStatusCode(200);
	  json.setCallbackType(null);
	  return AJAX;
	  
  }
  
  public String befEdit() throws ActionException
  {
	  dto.put("id", request.getParameter("id"));
	  super.doService("LvPromtManageService", "befEdit", dto);
	  LvPromtContent promtContent=(LvPromtContent)dto.get("promtContent");
	  request.setAttribute("promtContent", promtContent);
	  return "befedit";
  }
  
  public String edit() throws ActionException
  {
	  dto.put("promtContent", promtContent);
	  super.doService("LvPromtManageService", "edit", dto);
	  json.setCallbackType(null);
	  return AJAX;
  }
  
  public String getMaterialList() throws ActionException
  {
	  dto.put("page",page);
	  page=(Pagination)super.doService("LvPromtManageService", "getMaterialList", dto);
	  return "materiallist";
  }
  
  public String befAddMaterial() throws ActionException
  {
	  
	  return "befaddmaterial";
  }
  
  @SuppressWarnings("deprecation")
  public String addMaterial() throws ActionException
  {
	  String root = request.getRealPath("/");
	  String path = "/upload/";
	  String smallimgName = "";
	  String bigimgName = "";
	  String compressName = "";
      new java.io.File(root+path).mkdirs(); 
	  
		if (smallimg != null && smallimg.isFile()) 
		{
		   smallimgName = "s"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ smallimgFileName.substring(smallimgFileName.lastIndexOf("."));
		   FileUpload.upload(smallimg, root + path, smallimgName);
		}
		if (bigimg != null && bigimg.isFile()) 
		{
		   bigimgName = "b"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ bigimgFileName.substring(bigimgFileName.lastIndexOf("."));
		   FileUpload.upload(bigimg, root + path, bigimgName);
		}
		if (compress != null && compress.isFile()) 
		{
		   compressName = "c"+ new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ compressFileName.substring(compressFileName.lastIndexOf("."));
		   FileUpload.upload(compress, root + path, compressName);
		}
	 dto.put("name", request.getParameter("name"));
	 dto.put("smallimgName", smallimgName);
	 dto.put("bigimgName", bigimgName);
	 dto.put("compressName", compressName);
	 super.doService("LvPromtManageService","addMaterial", dto);
	 return AJAX;
  }
  
  @SuppressWarnings("deprecation")
  public String befEditMaterial() throws ActionException
  {
	String root = request.getRealPath("/")+"/upload/";
	dto.put("id", request.getParameter("id"));
	super.doService("LvPromtManageService", "befEditMaterial", dto);
	LvMaterial material=(LvMaterial)dto.get("material");
	request.setAttribute("id", material.getId());
	request.setAttribute("name", material.getMaterialName());
	request.setAttribute("smallimg", root+material.getSmallPath());
	request.setAttribute("bigimg", root+material.getBigPath());
	request.setAttribute("compress", root+material.getCompressPath());
	return "befeditmaterial";  
  }
  
  @SuppressWarnings("deprecation")
  public String editMaterial() throws ActionException
  {
	  dto.put("id", request.getParameter("myId"));
	  String root = request.getRealPath("/");
	  String path = "/upload/";
	  String smallimgName="";
	  String bigimgName="";
	  String compressName="";
	  if(smallimgFileName!=null&&smallimgFileName.length()>0)
	     smallimgName = "s"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ smallimgFileName.substring(smallimgFileName.lastIndexOf("."));
	  if(bigimgFileName!=null&&bigimgFileName.length()>0)
	     bigimgName = "b"+new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ bigimgFileName.substring(bigimgFileName.lastIndexOf("."));
	  if(compressFileName!=null&&compressFileName.length()>0)
	     compressName = "c"+ new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+ compressFileName.substring(compressFileName.lastIndexOf("."));
      new java.io.File(root+path).mkdirs(); 
	  
		if (smallimg != null && smallimg.isFile()) 
		{
		   FileUpload.upload(smallimg, root + path, smallimgName);
		}
		if (bigimg != null && bigimg.isFile()) 
		{
		   FileUpload.upload(bigimg, root + path, bigimgName);
		}
		if (compress != null && compress.isFile()) 
		{
		   FileUpload.upload(compress, root + path, compressName);
		}
	 dto.put("name", request.getParameter("name"));
	 dto.put("smallimgName", smallimgName);
	 dto.put("bigimgName", bigimgName);
	 dto.put("compressName", compressName);
	 super.doService("LvPromtManageService","editMaterial", dto);
	 return AJAX;
  }
  
  public String deleteMaterial() throws ActionException
  {
	  dto.put("ids",ids);
	 super.doService("LvPromtManageService", "deleteMaterial", dto);
	 json.setStatusCode(200);
	  json.setCallbackType(null);
	 return AJAX;
  }
  
  @SuppressWarnings("deprecation")
  public String exportFund() throws ActionException
  {
	    dto.put("ids", this.getIds());
		List list = (List)this.doService("LvPromtManageService","exportFund",dto);
		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
		ExcelFileHelp.create(path, list);
		File file = new File(path);
		try 
		{
			filesize=(int) file.length();
			excelStream = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
		}
		file.delete();
	  return "exportfund";
	  
  }
  
  public String getVideoList() throws ActionException
  {
	  dto.put("page",page);
	  page=(Pagination)super.doService("LvPromtManageService", "getVideoList", dto);
	  return "videolist";
  }
  
  public String bfAddVideo() throws ActionException
  {
	  return "befaddvideo";
  }
  
  public String addVideo() throws ActionException
  {
	  dto.put("video", video);
	  super.doService("LvPromtManageService", "addVideo", dto);
	  return AJAX;
  }
  
  
  public String befEditVideo() throws ActionException
  {
	 dto.put("id", request.getParameter("id"));
	 LvVideo video=(LvVideo)super.doService("LvPromtManageService", "befEditVideo", dto);
	 request.setAttribute("video", video);
     return "befeditvideo";
  }
  
  public String editVideo() throws ActionException
  {
	  dto.put("video", video);
	  super.doService("LvPromtManageService", "editVideo", dto);
	  return AJAX;
  }
  
  public String deleteVideo() throws ActionException
  {
	  dto.put("ids",ids);
	  super.doService("LvPromtManageService", "deleteVideo", dto);
	  json.setStatusCode(200);
	  json.setCallbackType(null);
	  return AJAX;
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
  
  public File getSmallimg() {
		return smallimg;
	}
	
	public void setSmallimg(File smallimg) {
		this.smallimg = smallimg;
	}
	
	public File getBigimg() {
		return bigimg;
	}
	
	public void setBigimg(File bigimg) {
		this.bigimg = bigimg;
	}
	
	public File getCompress() {
		return compress;
	}
	
	public void setCompress(File compress) {
		this.compress = compress;
	}
	
	public String getSmallimgFileName() {
		return smallimgFileName;
	}
	
	public void setSmallimgFileName(String smallimgFileName) {
		this.smallimgFileName = smallimgFileName;
	}
	
	public String getBigimgFileName() {
		return bigimgFileName;
	}
	
	public void setBigimgFileName(String bigimgFileName) {
		this.bigimgFileName = bigimgFileName;
	}
	
	public String getCompressFileName() {
		return compressFileName;
	}
	
	public void setCompressFileName(String compressFileName) {
		this.compressFileName = compressFileName;
	}
	
	public LvPromtContent getPromtContent() {
		return promtContent;
	}
	
	public void setPromtContent(LvPromtContent promtContent) {
		this.promtContent = promtContent;
	}
	
	public LvPromtContent getPromtContent2() {
		return promtContent2;
	}

	public void setPromtContent2(LvPromtContent promtContent2) {
		this.promtContent2 = promtContent2;
	}

	public LvVideo getVideo() {
		return video;
	}

	public void setVideo(LvVideo video) {
		this.video = video;
	}

	public LvExpenseFund getExpenseFund() {
		return expenseFund;
	}

	public void setExpenseFund(LvExpenseFund expenseFund) {
		this.expenseFund = expenseFund;
	}

	public int getFilesize() {
		return filesize;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public Date getVersionTime() {
		return versionTime;
	}

	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}

}
