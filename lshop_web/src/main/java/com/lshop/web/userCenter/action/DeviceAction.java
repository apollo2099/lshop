/**   
 * @filename: DeviceAction.java
 * @description: TODO
 * @author：dh
 * @time：2014年3月28日 下午4:51:46
 * @version：V1.0   
 */

package com.lshop.web.userCenter.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.gv.epg.common.constants.ApiConstants;
import com.gv.epg.model.device.service.EpgDeviceService;
import com.gv.epg.pojo.UcEpgUserDevice;
import com.lshop.common.action.BaseAction;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.page.PageHandle;

/**   
 * @author：dh
 * @time：2014年3月28日 下午4:51:46
 * @version：V1.0   
 * @description: TODO
 */
@SuppressWarnings("serial")
@Controller("DeviceAction")
@Scope("prototype")
public class DeviceAction extends BaseAction{
	
	private String code;

	private static EpgDeviceService epgservice = new EpgDeviceService();
	private static final Log logger	= LogFactory.getLog(DeviceAction.class);
	
	/**
	 * 根据用户获取设备列表
	 */
	public String myDevice() throws Exception{
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		this.dto.put("usercode", uid);
		List<UcEpgUserDevice> list = (List)epgservice.list(dto).get("list");
		
		String dname = this.getRequest().getParameter("name");
		String dcode = this.getRequest().getParameter("code");
		dname = removeHtml(dname);
		dcode = removeHtml(dcode);
		
		list = qurry(list,dname,dcode);
		this.getRequest().setAttribute("pagination", getPage(list));
		this.getRequest().setAttribute("dname", dname);
		this.getRequest().setAttribute("dcode", dcode);
		this.getRequest().setAttribute("pageurl", "/web/device!myDevice.action?name="+dname+"&code="+dcode+"&page.pageNum=@");
		this.getRequest().setAttribute("flag", "device");
		return "mydevice";
	}
	
	private List<UcEpgUserDevice> qurry(List<UcEpgUserDevice> list,String name,String code){
		if(StringUtil.IsNullOrEmpty(name) && StringUtil.IsNullOrEmpty(code)){
			return list;
		}
		List<UcEpgUserDevice> newList = new ArrayList<UcEpgUserDevice>();
		
		if(!StringUtil.IsNullOrEmpty(name) && !StringUtil.IsNullOrEmpty(code)){
			for(UcEpgUserDevice d : list){
				String syscode = d.getSyscode();
				String deviceName = d.getDevicename();
				if(syscode.contains(code) && deviceName.contains(name)){
					newList.add(highlight(d,name,code));
				}
			}
			return newList;
		}

		if(!StringUtil.IsNullOrEmpty(code)){
			for(UcEpgUserDevice d : list){
				String syscode = d.getSyscode();
				if(syscode.contains(code)){
					newList.add(highlight(d,name,code));
				}
			}
		}
		if(!StringUtil.IsNullOrEmpty(name)){
			for(UcEpgUserDevice d : list){
				String deviceName = d.getDevicename();
				if(deviceName.contains(name)){
					newList.add(highlight(d,name,code));
				}
			}
		}
		return newList;
	}
	
	public UcEpgUserDevice highlight(UcEpgUserDevice d,String name,String code){
		d.setDevicename(d.getDevicename().replace(name, "<font color='red'>"+name+"</font>"));
		d.setSyscode(d.getSyscode().replace(code, "<font color='red'>"+code+"</font>"));;
		return d;
		
	}
	
	/**
	 * 查询保修期
	 */
	public String checkWarranty() {
		String deviceId = this.getRequest().getParameter("code");
		
		System.out.println(deviceId);
		this.getRequest().setAttribute("name", deviceId);
		return "checkwarranty";
	}
	
	/**
	 * 跳转到解除绑定页
	 */
	public String toUnbinding(){
		return "toUnbinding";
	}
	
	/**
	 * 解除绑定
	 * @throws Exception 
	 */
	public void unbinding() throws Exception{
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out=this.getResponse().getWriter();
		int flag = 0;
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		String pwd = this.getRequest().getParameter("p");
		if(validatePwd(uid,MD5.convert32(pwd))){
			this.dto.put("usercode", uid);
			this.dto.put("codes", this.code);
//			this.dto.put("deviceName", deviceName);
			this.dto.put("status", "-1");
			this.dto.put("bizline", UserConstant.BIZLINE_B);
			this.dto.put("terminal", UserConstant.TERMINAL_PC);
			
			Dto d = null;
			try{
				d = epgservice.update(dto);
				String statu = d.getAsString(ApiConstants.KEY_STATUS);
				if(statu.equals(ApiConstants.STATUS_SUCCEED)){
					logger.info("========解除绑定成功");
					flag = 1;
				}
				if(statu.equals(ApiConstants.STATUS_DEVICE_INVALIDATION)){
					logger.info("========解除绑定失败，无效的设备");
					flag = -1;
				}
			}catch(Exception e){
				logger.info("========调取解除绑定方法失败");
				flag = -1;
				e.printStackTrace();
			}
		}
		out.print(flag);
		out.flush();
		out.close();
	}
	
	/**
	 * 驗證密碼
	 */
	private boolean validatePwd(String code,String pwd){
//		dto.put("usercode", code);
//		UcEpgUserInfo info = UcenterForEpg.getUserInfo(dto);
//		if(pwd.equals(info.getPassword())){
//			return true;
//		}
		return false;
	}
	
	private Pagination getPage(List list){
		int len = list.size();
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		for(int i=0;i<len;i++){
			map.put(i+1, list.get(i));
		}
		return PageHandle.getPage(map, this.page.getPageNum(), 10);
	}
	
	/**
	 * 去除特殊符号
	 */
	private String removeHtml(String s){
		if(!StringUtil.IsNullOrEmpty(s)){
			s = s.replace("<", "").replace(">", "").replace("'", "").replace("\"", "").
					replace("/", "").replace("\\", "").replace("?", "").replace("*", "").
					replace("&", "");
		}else{
			return "";
		}
		return s;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
