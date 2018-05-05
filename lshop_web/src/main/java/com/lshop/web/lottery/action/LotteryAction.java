package com.lshop.web.lottery.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.vo.ShareActivity;
import com.lshop.common.util.DateJsonValueProcessor;
import com.lshop.common.util.QRCodeHelper;
import com.lshop.web.activity.service.ShareService;
import com.lshop.web.lottery.bo.LotteryDetail;
import com.lshop.web.lottery.bo.LotteryRecord;
import com.lshop.web.lottery.bo.LotteryResult;
import com.lshop.web.lottery.service.LotteryChanceService;
import com.lshop.web.lottery.service.LotteryService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 抽奖页
 * @author caicl
 *
 */
@Controller("LotteryAction")
@Scope("prototype")
public class LotteryAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2624891929666043242L;

	@Resource
	private LotteryService lotteryService;
	@Resource
	private LotteryChanceService lotteryChanceService;
	@Resource
	private ShareService shareService;
	
	private String lotteryCode;
	
	/**
	 * 抽奖详情
	 * @return
	 * @throws IOException 
	 */
	public void detail() throws IOException {
		LotteryDetail detail = lotteryService.getLotteryDetail(lotteryCode);
		if (null == detail) {
			return;
		}
		//转成json
		JsonConfig config = new JsonConfig();  
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-M-d"));  
		String json = JSONObject.fromObject(detail, config).toString();
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
	/**
	 * 中奖榜
	 * @throws IOException 
	 */
	public void record() throws IOException {
		List<LotteryRecord> records = lotteryService.getLatestRecord(lotteryCode);
		String json = JSONArray.fromObject(records).toString();
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
	/**
	 * 用户抽奖
	 * @throws Exception 
	 */
	public void draw() throws Exception {
		//获取用户的code和nickname
		String userCode=this.getCookieValueToMap(UserConstant.USER_CENTER, true).get("uid");
		String nickname=this.getCookieValueToMap(UserConstant.USER_CENTER, true).get("nickname");
		LotteryResult result = lotteryService.lotteryDraw(userCode, lotteryCode, nickname);
		String json = JSONObject.fromObject(result).toString();
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}
	/**
	 * 获得用户抽奖机会次数
	 * @throws Exception 
	 */
	public void getLotteryChance() throws Exception {
		int chanceNum = 0;//default
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		if (StringUtils.isNotBlank(userCode)) {
			chanceNum = lotteryChanceService.getChanceNum(userCode, lotteryCode);
		}
		String json = "{\"chanceNum\":"+chanceNum+"}";
		getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter writer = getResponse().getWriter();
		writer.append(json);
		writer.close();
	}

	public String getLotteryCode() {
		return lotteryCode;
	}
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
}
