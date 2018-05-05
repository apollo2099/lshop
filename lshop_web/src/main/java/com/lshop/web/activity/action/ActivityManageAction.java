package com.lshop.web.activity.action;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.RegistActivity;
import com.lshop.common.activity.vo.ShareActivity;
import com.lshop.common.coupon.vo.CouponView;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvCouponMac;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateJsonValueProcessor;
import com.lshop.common.util.IPUtils;
import com.lshop.common.util.QRCodeHelper;
import com.lshop.common.util.StringUtil;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.activity.service.RegistService;
import com.lshop.web.activity.service.ShareService;
import com.lshop.web.coupon.service.CouponMacService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 
 * @Description:活动管理
 * @author CYJ
 * @date 2014-6-27 下午4:26:17
 */
@Controller("ActivityManageAction")
@Scope("prototype")
public class ActivityManageAction extends BaseAction {
	
	private static final Log logger = LogFactory.getLog(ActivityManageAction.class);
	private static final long serialVersionUID = 1L;

	@Resource
	private ActivityService activityService;
	@Resource
	private ShareService shareService;
	@Resource
	private RegistService registService;
	@Resource
	private CouponMacService couponMacService;
	private String activityCode;
	
	private String macCode;//mac码
	private String userEmail;//用户邮箱
	private String sourceUrl;

	private CouponView couponView;

	/**
	 * 
	 * @Method: getCouponByActivityLink 
     * @Description:  [领券活动领券-需登录入口]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-11-26 下午3:02:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-11-26 下午3:02:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String getCouponByActivityLink() throws ActionException {
		if(logger.isInfoEnabled()){
			  logger.info("***** ActivityManageAction.getCouponByActivityLink() method begin*****");
		}
		String toUrl = null;
		try {

			String flag = this.getFlag();

			String username = this.getCookieValue(UserConstant.USER_ID, true);
			
			//添加服务端验证
			if(ObjectUtils.isEmpty(activityCode)){
				logger.error("活动编码不能为空");
//            	ResultMsg resultMsg=new ResultMsg();
//            	if(flag.equals("www")||flag.equals("mtmcn")){
//            		resultMsg.setMsg("该活动不存在!");
//            	}else if(flag.equals("www")||flag.equals("mtmcn")){
//            		resultMsg.setMsg("This activity does not exist!");
//            	}
//            	this.getRequest().setAttribute("resultMsg", resultMsg);
//            	return "toValidate";
            	return "e404";
			}
            if(ObjectUtils.isEmpty(username)){
            	logger.error("用户未登录，无法领券");
            	return "e404";
            }
			
			
			//根据链接活动code查询活动信息
			
			ResultMsg resultMsg = activityService.getCouponByActivityLink(activityCode, username, flag);
			if (resultMsg.isSuccess()) {
				couponView = (CouponView) resultMsg.getReObj();
				// 领取成功
				toUrl = "toSuccess";
			} else {
				if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_404) || resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_666)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 已领完
					toUrl = "toClose";
				} else if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_888)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 已领取
					toUrl = "toFail";
				} else if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_000)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 还未开始
					toUrl = "toFuture";
				}else if(resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_NO_EXSITS)){
	            	this.getRequest().setAttribute("resultMsg", resultMsg);
	            	return "e404";
				}
			}
		} catch (Exception e) {
			throw new ActionException(e.getMessage(), e);
		}
		if(logger.isInfoEnabled()){
			  logger.info("***** ActivityManageAction.getCouponByActivityLink() method end*****");
		}
		return toUrl;
	}
	/**
	 * 
	 * @Method: getCouponByActivityLinkLogout 
	 * @Description:  [领券活动领券-无登录入口]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-11-26 下午3:01:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-11-26 下午3:01:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String getCouponByActivityLinkLogout() throws ActionException {
		if(logger.isInfoEnabled()){
			  logger.info("***** ActivityManageAction.getCouponByActivityLinkLogout() method begin*****");
		}
		String toUrl = null;
		try {
			
			
			String flag = this.getFlag();
			//添加服务端验证
			if(ObjectUtils.isEmpty(activityCode)){
				logger.error("活动编码不能为空");
            	return "e404";
			}

			
			//根据链接活动code查询活动信息
			ResultMsg resultMsg = activityService.getCouponByActivityLink(activityCode, null, flag);
			if (resultMsg.isSuccess()) {
				couponView = (CouponView) resultMsg.getReObj();
				// 保存mac以旧换新记录信息
				String ip=IPUtils.getIpAddr(this.getRequest());
				couponMacService.save(macCode,couponView.getCouponCode(),userEmail,ip,sourceUrl);
				// 领取成功
				toUrl = "toSuccess";
			} else {
				if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_404) 
						|| resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_666)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 已领完
					toUrl = "toClose";
				} else if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_888)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 已领取
					toUrl = "toFail";
				} else if (resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_000)) {
					couponView = (CouponView) resultMsg.getReObj();
					// 还未开始
					toUrl = "toFuture";
				}else if(resultMsg.getReCode().equals(ActivityConstant.RETURN_CODE_NO_EXSITS)){
					return "e404";
				}
			}
		} catch (Exception e) {
			throw new ActionException(e.getMessage(), e);
		}

		if(logger.isInfoEnabled()){
			  logger.info("***** ActivityManageAction.getCouponByActivityLinkLogout() method end*****");
		}
		return toUrl;
	}
	/**
	 * 查看系统注册活动
	 * @throws Exception
	 */
	public String getRegistActivity() throws Exception {
		String mallFlag = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag());
		List<RegistActivity> activities = registService.getAllRegistActivitie(mallFlag);
		getRequest().setAttribute("activities", activities);
		return "registActivity";
	}
	/**
	 * 返回分享活动详情json
	 * @throws Exception 
	 */
	public void getShareActivity() throws Exception {
		ShareActivity activity = shareService.getShareDetail(activityCode);
		if (ObjectUtils.isEmpty(activity)) {
			return;
		}
		//转成json
		JsonConfig config = new JsonConfig();  
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy年M月d日"));  
		String json = JSONObject.fromObject(activity, config).toString();
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
	/**
	 * 微信活动链接二维码
	 * @throws Exception 
	 */
	public void wxqrcode() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);//分享用户号
		if (StringUtils.isBlank(userCode)) {
			userCode = "";
		}
		String activityCode = getRequest().getParameter("a");//分享活动号
		String link = getRequest().getParameter("l");//分享的链接
		//二维码内容
		String url = link;
		if (link.contains("?")) {
			url += "&u="+userCode+"&a="+activityCode;
		} else {
			url += "?u="+userCode+"&a="+activityCode;
		}
		BufferedImage image = QRCodeHelper.createImage(url, 200, 200);
		// 返回二维码
		getResponse().setContentType("image/png");
		OutputStream stream = getResponse().getOutputStream();
		ImageIO.write(image, "png", stream);
		stream.flush();
		stream.close();
	}
	/**
	 * 获得分享活动检测码
	 */
	public void shareValicode() {
		String validCode = CodeUtils.getCode();
		getRequest().getSession().setAttribute(ActivityConstant.SHARE_ACTIVITY_VALID, validCode);
		getResponse().addCookie(new Cookie(ActivityConstant.SHARE_ACTIVITY_VALID, validCode));
	}
	/**
	 * 分享成功回调
	 * @throws Exception 
	 */
	public void shareCallback() throws Exception {
		//检查检测码
		String scode = StringUtil.isNotEmpty(getRequest().getSession().getAttribute(ActivityConstant.SHARE_ACTIVITY_VALID));
		String ccode = this.getCookieValue(ActivityConstant.SHARE_ACTIVITY_VALID, true);//分享用户号
		if (StringUtils.isBlank(scode) || !scode.equals(ccode)) {
			//没有通过检测
			return;
		} else {
			getRequest().getSession().removeAttribute(ActivityConstant.SHARE_ACTIVITY_VALID);
		}
		//分享
		String userCode = getRequest().getParameter("userCode");
		boolean prize = false;
		if (StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(activityCode)) {
			prize = shareService.shareSuccess(userCode, activityCode);//分享获得奖励结果
		}
		String json = "{\"prize\":"+prize+"}";
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
	
	/**
	 * 根据mac查询是否存在兑换优惠券的记录(json)
	 * @throws Exception 
	 */
	public void findByMac() throws Exception {
		LvCouponMac couponMac=couponMacService.findByMac(macCode);
		if (ObjectUtils.isEmpty(couponMac)) {
			return;
		}
		//转成json  
		String json = JSONObject.fromObject(couponMac).toString();
		System.out.println(json);
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
  
	
	public void initActivityUserCache() throws ActionException {
		try {
			activityService.initActivityUserCache();
		} catch (Exception e) {
			throw new ActionException(e.getMessage(), e);
		}

	}
	
	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public CouponView getCouponView() {
		return couponView;
	}

	public void setCouponView(CouponView couponView) {
		this.couponView = couponView;
	}

	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	
	
}
