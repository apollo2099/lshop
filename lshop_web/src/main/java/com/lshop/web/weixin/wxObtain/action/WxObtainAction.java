/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxObtain.action;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.web.userCenter.UserCommon;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxObtain;
import com.lshop.web.weixin.common.pojo.WxUser;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.wxObtain.service.WxObtainService;
import com.lshop.web.weixin.wxUser.service.WxUserService;
import com.gv.core.exception.ActionException;
import com.lshop.common.action.BaseAction;
import com.lshop.common.util.Constants;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxObtainAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class WxObtainAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxObtainAction.class);
	@Resource
	private WxUserService wxUserService;
	@Resource
	private WxObtainService wxObtainService;
	@Resource
	private WxGzhConfigService wxGzhConfigService;
	private WxObtain wxObtain = new WxObtain();
	private String redirectUri = "/web/obtainAction!authRedirect.action?openid={openid}";
	private String openid;
	private String jumpurl;

	public WxObtain getWxObtain() {
		return wxObtain;
	}

	public void setWxObtain(WxObtain wxObtain) {
		this.wxObtain = wxObtain;
	}
	
	/**
	 * 领取页面
	 * @return
	 * @throws ActionException
	 */
	public String index()throws Exception{
		HttpServletRequest request = this.getRequest();
		String isappinstalled = request.getParameter("isappinstalled");
		logger.info("===============index(),isappinstalled:"+isappinstalled);
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		if (isappinstalled == null) {
			String openid = request.getParameter("openid");
			Integer wxhConfigId =  wxGzhConfig.getId();
			WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
			int obtainAmount = 0;
			if (user != null) {
				obtainAmount = user.getObtainAmount();
			}
			request.setAttribute("obtainAmount", obtainAmount);
			int rank = wxUserService.getRank(wxhConfigId, openid);
			request.setAttribute("rank", rank);
			Boolean canObtain = wxObtainService.canObtain(wxGzhConfig.getId(), openid, WxObtain.obtainType_self, null, null);
			request.setAttribute("canObtain", canObtain);
			return "self";			
		} else {
			String openid = request.getParameter("openid");
			String redirect = redirectUri.replaceAll("\\{openid\\}", openid);
			String oauthUrl = UserCommon.getWXOauthUrl(this.getFlag(), wxGzhConfig.getAppId(), redirect);
			this.getResponse().sendRedirect(oauthUrl);
			return null;
		}
	}	
	
	public String authRedirect()throws Exception{
		HttpServletRequest request = this.getRequest();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();
		String openid = request.getParameter("openid");
		logger.info("authRedirect(),openid:" + openid);
		WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
		int obtainAmount = 0;
		if (user != null) {
			obtainAmount = user.getObtainAmount();
		}
		request.setAttribute("obtainAmount", obtainAmount);
		int rank = wxUserService.getRank(wxhConfigId, openid);
		request.setAttribute("rank", rank);
		request.setAttribute("openid", openid);
		String code = this.getRequest().getParameter("code");
		String friendOpenid = UserCommon.getWXOpenId(code, wxGzhConfig.getAppId(), wxGzhConfig.getAppSecret());
		request.setAttribute("friendOpenid", friendOpenid);
		logger.info("authRedirect(),openid:" + openid + ",friendOpenid:"+friendOpenid+",appId:"+wxGzhConfig.getAppId());
		String ipAddress = this.getRemoteAddress();
		
		Boolean canObtain = wxObtainService.canObtain(wxhConfigId, openid, WxObtain.obtainType_friend, ipAddress, friendOpenid);
		request.setAttribute("canObtain", canObtain);				
		return "friend";
	}	
	
	/**
	 * 领取金额
	 * @return
	 * @throws ActionException
	 * @throws IOException
	 */
	public String obtain()throws ActionException, Exception{
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();
		String openid = request.getParameter("openid");
		String ipAddress = this.getRemoteAddress();
		WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
		String nickname = "";
		if (user != null) {
			nickname = user.getNickname();
		} else {
			nickname = wxUserService.getNicknameFromWx(wxhConfigId, openid);
			WxUser userNew = new WxUser();
			userNew.setOpenid(openid);
			userNew.setWxhConfigId(wxhConfigId);
			userNew.setCreateTime(Calendar.getInstance().getTime());
			userNew.setNickname(nickname);
			userNew.setObtainAmount(0);
			dto.put("model", userNew);
			user = wxUserService.save(dto);
		}
		WxObtain wxObtain = new WxObtain();
		wxObtain.setOpenid(openid);
		wxObtain.setNickname(nickname);
		wxObtain.setObtainType(WxObtain.obtainType_self);
		wxObtain.setWxhConfigId(wxhConfigId);
		wxObtain.setIpAddress(ipAddress);
		Calendar cal = Calendar.getInstance();
		wxObtain.setCreateTime(cal.getTime());

		Boolean canObtain = wxObtainService.canObtain(wxhConfigId, openid, WxObtain.obtainType_self, ipAddress, null);
		if (canObtain) {
			dto.put("model", wxObtain);
			wxObtainService.save(dto);
			int obtainAmount = user.getObtainAmount();
			int newObtainAmount = obtainAmount + WxConstant.obtain_amount;
			user.setObtainAmount(newObtainAmount);
			user.setLastObtainTime(cal.getTime());
			dto.put("model", user);
			wxUserService.update(dto);
			int rank = wxUserService.getRank(wxhConfigId, openid);
			String sRes = MessageFormat.format(WxConstant.responseTemplate, WxConstant.response_res_ok);
			JSONObject jo = JSONObject.fromObject(sRes);
			jo.put("obtainAmount", newObtainAmount);
			jo.put("rank", rank);
			response.getWriter().print(jo.toString());
		} else {
			response.getWriter().print(MessageFormat.format(WxConstant.responseTemplate, WxConstant.response_res_error));
		}		
		return null;
	}
	
	/**
	 * 助TA领取金额
	 * @return
	 * @throws ActionException
	 * @throws IOException
	 */
	public String friendObtain()throws ActionException, Exception{
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();
		String openid = request.getParameter("openid");
		String friendOpenid = request.getParameter("friendOpenid");
		String ipAddress = this.getRemoteAddress();
		WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
		String nickname = "";
		if (user != null) {
			nickname = user.getNickname();
		} else {
			nickname = wxUserService.getNicknameFromWx(wxhConfigId, openid);
			WxUser userNew = new WxUser();
			userNew.setOpenid(openid);
			userNew.setWxhConfigId(wxhConfigId);
			userNew.setCreateTime(Calendar.getInstance().getTime());
			userNew.setNickname(nickname);
			userNew.setObtainAmount(0);
			dto.put("model", userNew);
			user = wxUserService.save(dto);
		}
		WxObtain wxObtain = new WxObtain();
		wxObtain.setOpenid(openid);
		wxObtain.setNickname(nickname);
		wxObtain.setObtainType(WxObtain.obtainType_friend);
		wxObtain.setWxhConfigId(wxhConfigId);
		wxObtain.setIpAddress(ipAddress);
		Calendar cal = Calendar.getInstance();
		wxObtain.setCreateTime(cal.getTime());
		wxObtain.setFriendOpenid(friendOpenid);
		
		Boolean canObtain = wxObtainService.canObtain(wxhConfigId, openid, WxObtain.obtainType_friend, ipAddress,friendOpenid);				
		if (canObtain) {
			dto.put("model", wxObtain);
			wxObtainService.save(dto);
			int obtainAmount = user.getObtainAmount();
			int newObtainAmount = obtainAmount + WxConstant.obtain_amount;
			user.setObtainAmount(newObtainAmount);
			user.setLastObtainTime(cal.getTime());
			dto.put("model", user);
			wxUserService.update(dto);
			int rank = wxUserService.getRank(wxhConfigId, openid);
			String sRes = MessageFormat.format(WxConstant.responseTemplate, WxConstant.response_res_ok);
			JSONObject jo = JSONObject.fromObject(sRes);
			jo.put("obtainAmount", newObtainAmount);
			jo.put("rank", rank);				
			response.getWriter().print(jo.toString());
		} else {
			response.getWriter().print(MessageFormat.format(WxConstant.responseTemplate, WxConstant.response_res_error));
		}		
		return null;
	}	
	
	public String refresh()throws ActionException, Exception{
		HttpServletRequest request = this.getRequest();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();
		String openid = request.getParameter("openid");
		String ipAddress = this.getRemoteAddress();
		
		WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
		int obtainAmount = 0;
		if (user != null) {
			obtainAmount = user.getObtainAmount();
		}
		request.setAttribute("obtainAmount", obtainAmount);
		int rank = wxUserService.getRank(wxhConfigId, openid);
		JSONObject jo = new JSONObject();
		jo.put("obtainAmount", obtainAmount);
		jo.put("rank", rank);		
		
		HttpServletResponse response = this.getResponse();
		Boolean canObtain = wxObtainService.canObtain(wxhConfigId, openid, WxObtain.obtainType_self, ipAddress, null);
		if (canObtain) {
			jo.put("res", WxConstant.response_res_ok);
			response.getWriter().print(jo.toString());
		} else {
			jo.put("res", WxConstant.response_res_error);
			response.getWriter().print(jo.toString());			
		}
		return null;
	}	
	
	public String friendRefresh()throws ActionException, Exception{
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();
		String openid = request.getParameter("openid");
		String friendOpenid = request.getParameter("friendOpenid");
		String ipAddress = this.getRemoteAddress();
		logger.info("friendRefresh(),openid:" + openid + ",friendOpenid:"+friendOpenid);
		
		WxUser user = wxUserService.getByOpenid(wxhConfigId, openid);
		int obtainAmount = 0;
		if (user != null) {
			obtainAmount = user.getObtainAmount();
		}
		request.setAttribute("obtainAmount", obtainAmount);
		int rank = wxUserService.getRank(wxhConfigId, openid);
		JSONObject jo = new JSONObject();
		jo.put("obtainAmount", obtainAmount);
		jo.put("rank", rank);
		
		//如果自己在微信端点击分享链接
		if (openid.equals(friendOpenid)) {
			jo.put("res", WxConstant.response_res_error);
			response.getWriter().print(jo.toString());	
			return null;
		}
		
		Boolean canObtain = wxObtainService.canObtain(wxhConfigId, openid, WxObtain.obtainType_friend, ipAddress, friendOpenid);		
		if (canObtain) {
			jo.put("res", WxConstant.response_res_ok);
			response.getWriter().print(jo.toString());
		} else {
			jo.put("res", WxConstant.response_res_error);
			response.getWriter().print(jo.toString());
		}
		return null;
	}
	
	
	/**
	 * 排行榜
	 * @return
	 * @throws ActionException
	 */
	public String rank()throws ActionException{
		HttpServletRequest request = this.getRequest();
		String sLastNum = request.getParameter("lastNum");
		int maxLastNum = 103;
		int lastNum = maxLastNum;
		if (sLastNum != null) {
			lastNum = Integer.valueOf(sLastNum);
			if (lastNum > maxLastNum) {
				lastNum = maxLastNum;
			}
		}
		WxGzhConfig wxGzhConfig = wxGzhConfigService.getByStoreId(this.getFlag());
		Integer wxhConfigId =  wxGzhConfig.getId();		
		List<Map<String, Object>> mapList = wxUserService.getRankList(wxhConfigId, lastNum);
		request.setAttribute("mapList", mapList);
		return "rank";
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getJumpurl() {
		return jumpurl;
	}

	public void setJumpurl(String jumpurl) {
		this.jumpurl = jumpurl;
	}	

}
