package com.lshop.manage.res.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.json.util.JSONUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.file.FileManager;
import com.lshop.common.file.FileManagerImpl;
import com.lshop.common.file.Tpl;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvTplModelPublic.action.LvTplModelPublicMngAction;
@Controller("ResManagerAction")
@Scope("prototype")
@SuppressWarnings("all")
/**
 * res资源文件管理
 */
public class ResManagerAction extends  BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(ResManagerAction.class);
	private List<Tpl> list;
	private List allList;// 获取目录下所有文件
	private String dir;
	private String folderName;//文件夹名称
	private File file;//文件
	private String fileFileName;//原文件名
	private String filePath;
	private int filesize;
	private Tpl tpl;
	private String storeFlag;
	private String flag;//菜单访问标志
	private String searchFileName;//搜索文件名称
	

	/**
	  * 解压zip文件
	  * 
	  * @param zip
	  *            zip文件绝对路径
	  * @param unZipDir
	  *            解压到的目录
	  * @throws Exception
	  */

	private void unzip(File zip,File unZipDir) throws IOException {
		if (!zip.exists()) {
			return;
		}
		if (!unZipDir.exists()) {
			unZipDir.mkdirs();
		}
		if (!unZipDir.isDirectory()) {
			return;
		}
		 int BUFFER = 1024;  
	    int fileCount = 0;
		ZipFile zipfile = new ZipFile(zip);
		BufferedOutputStream ds = null;
		BufferedInputStream is = null;
		ZipEntry entry = null;
		Enumeration entries = zipfile.entries();
		while (entries.hasMoreElements()) {
			try {
				entry = (ZipEntry) entries.nextElement();
				File tempFile = new File(unZipDir.getAbsolutePath() + "/"
						+ entry.getName());
				if(entry.isDirectory()){
					tempFile.mkdirs();
					continue;
				} else if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(unZipDir
						.getAbsolutePath()
						+ "/" + entry.getName());
				ds = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					ds.write(data, 0, count);
				}
				ds.flush();

			} catch (IOException ex) {
				throw ex;

			} finally {
				try {
					if (ds != null)
						ds.close();

					if (is != null)
						is.close();
				} catch (IOException e) {
					// ignore
	
				}
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("Finished !");
		}
	}

	  
	/**
	 * 文件资源上传
	 * @return
	 * @throws IOException 
	 */
	public String upload() throws IOException{
		if (file!=null) {
			String basepath=dir!=null?this.getPath(storeFlag)+"/"+dir:this.getPath(storeFlag);//文件上传资源路径
			boolean isupload=FileUpload.upload(file,
					basepath, fileFileName);
			if (isupload) {
				json.setMessage("上传成功！");
				json.setStatusCode(200);
			}else{
				json.setMessage("上传失败!");
				json.setStatusCode(300);
			}
			if("1".equals(request.getParameter("fType"))&&"zip".equals(fileFileName.substring(fileFileName.lastIndexOf(".")+1))){
				this.unzip(file ,new File(basepath));
			}
		}
		
		return this.AJAX;
	}
	/**
	 * 删除文件
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws UnsupportedEncodingException 
	 */
	public String del() {
		request
		.setAttribute("current_path", "/res"
				+ this.dir);
          String path = this.getPath(storeFlag) + "/" + filePath;
          File file = new File(path);
          if(file.isDirectory()){
        	if(file.list().length>0){
        	  json.setMessage("该文件夹含有子文件，不能删除！");
        	  json.setStatusCode(300);
        	}else{
        		file.delete();
        	}
          }else{
          file.delete();
          json.setStatusCode(200);
          }
          json.doNavTabTodo();
		return this.AJAX;
	}
	public String list() {
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if(ObjectUtils.isEmpty(storeFlag)){
			LvStore lvStore=storeList.get(0);
			storeFlag=lvStore.getStoreFlag();
			this.getRequest().setAttribute("storeFlag", storeFlag);
		}
		
		
		String path = this.getPath(storeFlag);
		if (this.dir != null && !"".equals(this.dir.trim())) {
			path += "/" + this.dir;
			request.setAttribute("current_path","/res/"
					+ this.dir);
		} else {
			request.setAttribute("current_path", "/res");
		}
		
		FileManager tplM = new FileManagerImpl(path);
//		this.list = (List<Tpl>) tplM.getChild(path);
		page = tplM.getChild(path,page,searchFileName);
		return this.LIST;
	}
		
	/**
	 * 修改文件
	 * 
	 * @return
	 */
	public String bfupdate() {
		request.setAttribute("current_path","/res/"
				+ this.dir);
		String path = this.getPath(storeFlag) + "/" + filePath;
		FileManager tplM = new FileManagerImpl(this.getPath(storeFlag));
		this.tpl = tplM.get(path);
		return this.BFUPDATE;
	}

	/**
	 * 保存修改文件
	 * 
	 * @throws IOException
	 */
	public String update() throws IOException {
		String content = request.getParameter("content");
		if (content != null && !"".equals(content.trim())) {
			FileManager tplM = new FileManagerImpl(this.getPath(storeFlag));
			String path=this.getPath(storeFlag)+"/"+this.getFilePath();
			tplM.update(path, content.trim());
			json.setMessage("保存成功！");
			json.setStatusCode(200);
		} else {
			json.setMessage("保存失败！");
			json.setStatusCode(json.STATUS_CODE_ERROR);
		}
		return this.AJAX;
	}
	
	/**
	 * 文件夹的绝对路径
	 * 
	 * @return
	 */
	private String getPath(String storeFlag) {
		
		String resPath = this.getText("res.domain.path"); //获取资源文件路径
		String path = resPath+"/"+storeFlag+"/res"; //因为是直接上传的资源文件，前台路径未跟进，所以要存放在前台原资源目录下面
//		String path = this.getText("lshop.web.path")+"/"+storeFlag+"/res";
		return path;
	}
    /**
     * 添加文件目录
     * @return
     */
	public String befaddFolder()throws Exception {
		
		return "addfolder";
	}
	/**
	 * 保存文件目录
	 */
	public String saveFolder()throws IOException {
		String path = this.getPath(storeFlag);
		if (this.dir != null && !"".equals(this.dir.trim())) {
			path += "/" + this.dir;
			request.setAttribute("current_path", this.getText("lshop.web.path") + "/"
					+ this.dir);
		} else {
			request.setAttribute("current_path", this.getText("lshop.web.path"));
		}
		File file = new File(path+"/"+folderName);
		if(!file.exists()){
			file.mkdir();
			json.setMessage("操作成功！");
			json.setStatusCode(json.STATUS_CODE_OK);
			json.setForwardUrl("/manager/resManagerAction!list.action?dir="+dir+"&json.navTabId="+json.getNavTabId()+"&storeFlag="+storeFlag);
			json.doForward();
			json.doClose();
		}else{
			json.setMessage("文件目录已存在！");
			json.setStatusCode(json.STATUS_CODE_ERROR);
		}
		return AJAX;
	}
	/**
	 * 添加文件
	 * @return
	 */
	public String befaddFile()throws Exception {
		return "addfile";
	}
	/**
	 * 保存文件
	 * @return
	 */
	public String saveFile()throws IOException {
		return AJAX;
	}
	public List<Tpl> getList() {
		return list;
	}

	public void setList(List<Tpl> list) {
		this.list = list;
	}

	public List getAllList() {
		return allList;
	}

	public void setAllList(List allList) {
		this.allList = allList;
	}

	public String getDir() {
		return dir;
	}

	

	public Tpl getTpl() {
		return tpl;
	}

	public void setTpl(Tpl tpl) {
		this.tpl = tpl;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public int getFilesize() {
		return filesize;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSearchFileName() {
		return searchFileName;
	}

	public void setSearchFileName(String searchFileName) {
		this.searchFileName = searchFileName;
	}

}
