<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.lshop.common.util.CommonUtil"%> 
<%
	String filepath = null;
	
	if (request.getAttribute("path") != null) {
		filepath = request.getAttribute("path").toString();
	} else {
		filepath = request.getParameter("path");
	}
	// 替换反斜杠，(@bsc@)=反斜杠
	filepath = filepath.replace("(@bsc@)", "\\");
	
	//System.out.println(filepath);
	
	if (filepath == null) {
		return;
	}
	String filename = "";
	if (request.getParameter("name") != null) {
		filename = request.getParameter("name");
	} else {
		filename = filepath.substring(filepath.lastIndexOf(File.separatorChar) + 1);
	}
	//System.out.println(filename);
	
	filename = filename.replaceAll(" ", "");	// 解决火狐下载时会以空格截断，导致文件名不完整问题
	filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
	
	
	response.setCharacterEncoding("GBK");
	response.setContentType("application/x-download;charset=UTF-8");	//设置为下载application/x-download 
	//response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "GB2312"));
	response.addHeader("Content-Disposition", "attachment;filename=" + filename);
	
	OutputStream os = null;
	InputStream is = null;
	
	try {
		os = response.getOutputStream();
		is = new FileInputStream(filepath);
		byte[] b = new byte[1024];
		int i = 0;
		while ((i = is.read(b)) > 0) {
			os.write(b, 0, i);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (is != null) {
			is.close();
			is = null;
		}
		if (os != null) {
			os.flush();
			os.close();
			os = null;
		}
	}
	
	out.clear();
	out = pageContext.pushBody(); 
	
	Boolean delete = (Boolean) request.getAttribute("delete");
	if (delete != null && delete == true) {
		new CommonUtil().deleteFile(filepath);
	}
%>
