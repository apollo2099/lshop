package com.lshop.manage.sysfile.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.lshop.manage.common.action.BaseManagerAction;


@SuppressWarnings("unchecked")
@Controller("FileDownloadAction")
@Scope("prototype")
public class FileDownloadAction extends BaseManagerAction{
	private static final long serialVersionUID = 1L;
	private String fileName; 
	/**
	 *返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流 
	 */
    public InputStream getDownloadFile() throws Exception {
    	InputStream is=null;
		try {
			is = ServletActionContext.getServletContext().getResourceAsStream("report/"+fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return is ;
    }  
    
    // 下载
    public String downloadFile() throws Exception {
    	System.out.println("test");
             return SUCCESS;
    }
    
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
