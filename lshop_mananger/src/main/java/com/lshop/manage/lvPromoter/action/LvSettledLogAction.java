package com.lshop.manage.lvPromoter.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.ExcelFileHelp;

@SuppressWarnings({"serial","unchecked"})
@Controller("LvSettledLogAction")
@Scope("prototype")
public class LvSettledLogAction extends BaseAction
{
	private int filesize;
	private BufferedInputStream excelStream;
	private String couponCode;
	private String aids;
	


	public String list() throws ActionException
	{
	  dto.put("name", request.getParameter("name"));
	  dto.put("email", request.getParameter("email"));
	  dto.put("couponCode",request.getParameter("couponCode"));
	  dto.put("overtime", request.getParameter("overtime"));
	  dto.put("accountNumber",request.getParameter("accountNumber"));
	  dto.put("page",page);
	  page=(Pagination)super.doService("LvSettledLogService", "getLogList", dto);
	  List<Object[]> objList=(List<Object[]>)page.getList();
	  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  for(int i=0;i<objList.size();i++)
	  {
		  Object[] obj=objList.get(i);
		  obj[0]=dateFormat.format(obj[0]);
		  if (ObjectUtils.isNotEmpty(obj[7])) {
			  String aids=obj[7].toString();
			  String[] aid=aids.split(",");
			  int size=aid.length;
			  obj[7]=size;
		  }
	  }
	  request.setAttribute("objList", objList);
	  return LIST;	
	}
	
	public String getUserDetail() throws ActionException
	{
		dto.put("uid", request.getParameter("uid"));
		LvUserPromoters userPromoters=(LvUserPromoters)super.doService("LvSettledLogService", "getUserDetail", dto);
		request.setAttribute("lvUserPromoters", userPromoters);
		return "userDetail";
	}
	
	public String getOrderList() throws ActionException
	{
		dto.put("keyword", keyword);
		dto.put("page",page);
		dto.put("couponCode",couponCode);
		dto.put("ids", ids);
		dto.put("aids", aids);
		page=(Pagination)super.doService("LvSettledLogService", "getOrderList", dto);
		request.setAttribute("page", page);
		return "orderList";
	}
	
	@SuppressWarnings("deprecation")
	public String export() throws ActionException, IOException, RowsExceededException, WriteException
	  {
		    dto.put("ids", ids);
		    dto.put("couponCode",couponCode);
			List list = (List)this.doService("LvSettledLogService","export",dto);
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
			return "excellog";
	  }
	
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getFilesize() {
		return filesize;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}
	
	public String getAids() {
		return aids;
	}

	public void setAids(String aids) {
		this.aids = aids;
	}
}
