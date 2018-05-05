package com.gv.html.struts;

import javax.servlet.ServletContext;

import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;

/**
 * 运行时环境
 * @author caicl
 *
 */
public class ApplicationHolder {
	public static PrepareOperations prepare;
	public static ExecuteOperations execute;
	public static ServletContext servletContext;
}
