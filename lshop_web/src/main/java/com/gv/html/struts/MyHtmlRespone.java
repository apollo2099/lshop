package com.gv.html.struts;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MyHtmlRespone implements HttpServletResponse{
	public static final Log logger = LogFactory.getLog(MyHtmlRespone.class);
	
	protected PrintWriter psOut = null;
	
	String htmlFile;
	
	protected String characterEncoding = "UTF-8";
	
	protected String contentType = "text/html";
	
	private int contentLength = 0;
	
	private OutputStream outputStream = null;
	
	public MyHtmlRespone(String abFile){
		this.htmlFile = abFile;
	}
	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		logger.error("respone outputstream not implement");
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (null == psOut) {
			psOut = new HtmlResponseWriter(htmlFile, characterEncoding);
		}
		return psOut;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		this.characterEncoding = charset;
	}

	@Override
	public void setContentLength(int len) {
		System.out.println("invoke contentlength");
		this.contentLength = len;
	}

	@Override
	public void setContentType(String type) {
		this.contentType = type;
	}

	@Override
	public void setBufferSize(int size) {
		logger.info("invoke not implement");
		
	}

	@Override
	public int getBufferSize() {
		logger.info("invoke not implement getbuffer size");
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		getWriter().flush();
		
	}

	@Override
	public void resetBuffer() {
		logger.info("invoke not implement");
		
	}

	@Override
	public boolean isCommitted() {
		logger.info("invoke not implement");
		return false;
	}

	@Override
	public void reset() {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setLocale(Locale loc) {
		logger.info("invoke not implement");
		
	}

	@Override
	public Locale getLocale() {
		logger.info("invoke mock local");
		return Locale.CHINA;
	}

	@Override
	public void addCookie(Cookie cookie) {
		logger.info("invoke not implement");
		
	}

	@Override
	public boolean containsHeader(String name) {
		logger.info("invoke not implement");
		return false;
	}

	@Override
	public String encodeURL(String url) {
		logger.info("invoke not implement");
		return null;
	}

	@Override
	public String encodeRedirectURL(String url) {
		logger.info("invoke not implement");
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		logger.info("invoke not implement");
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		logger.info("invoke not implement");
		return null;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		logger.info("invoke not implement");
		
	}

	@Override
	public void sendError(int sc) throws IOException {
		logger.info("invoke not implement");
		
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setDateHeader(String name, long date) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void addDateHeader(String name, long date) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setHeader(String name, String value) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void addHeader(String name, String value) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setIntHeader(String name, int value) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void addIntHeader(String name, int value) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setStatus(int sc) {
		logger.info("invoke not implement");
		
	}

	@Override
	public void setStatus(int sc, String sm) {
		logger.info("invoke not implement");
		
	}
}
