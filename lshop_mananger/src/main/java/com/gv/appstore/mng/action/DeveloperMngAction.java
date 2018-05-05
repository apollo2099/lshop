package com.gv.appstore.mng.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.appstore.pojo.LvDeveloper;
import com.gv.appstore.util.LogUtil;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.manage.common.action.BaseManagerAction;

@Controller("DeveloperMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class DeveloperMngAction extends BaseManagerAction {
	private static final Log logger = LogFactory.getLog(DeveloperMngAction.class);
	private LvDeveloper developer;
	private String createDateStart;
	private String createDateEnd;

	public String list() {
		LogUtil.log(logger, "***** DeveloperMngAction.list() method begin*****");

		this.dto.put("page", this.page);
		this.dto.put("developer", this.developer);
		this.dto.put("createDateStart", this.createDateStart);
		this.dto.put("createDateEnd", this.createDateEnd);
		this.page = (Pagination) this.doService("DeveloperMngService","findPage", dto);

		LogUtil.log(logger, "***** DeveloperMngAction.list() method end*****");
		return this.LIST;
	}

	public String view() {
		dto.put("id", developer.getId());
		this.developer = (LvDeveloper)this.doService("DeveloperMngService", "get", dto);
		return this.VIEW;
	}
	
	public void idimg(){
		dto.put("id", developer.getId());
		this.developer = (LvDeveloper)this.doService("DeveloperMngService", "get", dto);
		Blob photo = developer.getIdUrl();
		OutputStream sout = null;// 二进制流可以直接在jsp页面显示
		try {
			InputStream in = photo.getBinaryStream();
			sout = this.getResponse().getOutputStream();
			byte b[] = new byte[1024];
			int len = in.read(b);
			while (len != -1) {
				sout.write(b);
				len = in.read(b);
			}
			sout.flush();
			sout.close();
			in.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toverify() {

		return "verify";
	}

	public String verify() {
		LogUtil.log(logger, "***** DeveloperMngAction.verify() method begin*****");
		this.dto.put("developer", this.developer);
		this.doService("DeveloperMngService", "verify", dto);
		LogUtil.log(logger, "***** DeveloperMngAction.verify() method end*****");
		return AJAX;
	}

	public LvDeveloper getDeveloper() {
		return developer;
	}

	public void setDeveloper(LvDeveloper developer) {
		this.developer = developer;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

}
