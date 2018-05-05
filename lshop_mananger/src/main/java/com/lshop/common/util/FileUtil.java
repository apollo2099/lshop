package com.lshop.common.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class FileUtil {
	/**
	 * 
	 * @Method: deleteTempFile 
	 * @Description:  [删除文件上传临时文件夹下面所有临时文件]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午2:21:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午2:21:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request 
	 * @return void
	 */
	public static void deleteTempFile(HttpServletRequest request){
		String pathTmp = request.getRealPath("/temp");
		File fileTemp=new File(pathTmp);
		if(fileTemp.isDirectory()){
			 File[] f=fileTemp.listFiles();
			 for (File fs : f) {
				boolean isFlag=fs.delete();
			}
		}
	}
}
