<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.lshop.common.util.PropertiesHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抽獎專題</title>
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
<link href="${resDomain}/www/res/pan/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/www/res/js/jquery.rotate.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/pan/js/rotate.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/share.js"></script>

</head>

<body>
<!--login -->
<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
	<div class="pop_up_box" style="background:white;">
		<span class="box_l">用戶登錄<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/www/res/images/close.gif" border="0" /></a></span></span>
		<div style="color: red; margin: 10px 0 0 90px;"><span id="h_emsg"></span></div>
		<form action="${storeDomain}/web/userCenterManage!login.action" id="divlogin"
		method="post" onsubmit="">
			<input type="hidden" name="loginstyle" value="0"/>
			<input type="hidden" name="jumpurl" id="h_jumpurl" value=""/>
			<ul>
				<li id="erro_msg" style="display: none;height: 20px"><span id="msg" class="redfont" style="margin-left: 90px;"></span></li>
				<li><span class="wd1"><font class="redfont">*</font>賬號：</span><input name="uname" id="h_uname" class="input02" type="text"  value="${uname}"/></li>
				<li><span class="wd1"><font class="redfont">*</font>密 碼：</span><input name="pwd"  id="h_pwd" class="input02"  type="password" /></li>
				<li><span class="wd1">&nbsp;</span><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">忘記密碼？</a>&nbsp;&nbsp;<a href="javascript:toReg('${storeDomain}');">註冊</a></li>
				<li><span class="wd2"><input type="submit" value="登錄" class="user_center_bt02" /></span></li>
	  		</ul>
  		</form>
	</div>
</div>
<div class="lucky_draw_topbg" style="width:100%;background: url(http://res.mtvpad.com/www/res/pan/images/lucky_draw_01.jpg) no-repeat center;">
  <div class="lucky_draw_top">
  		<div class="lucky_draw_t">
        	<ul>
           	  <li id="c_ttl" class="title">可编辑标题</li>
                <li>活动日期：<span id="c_time">2014年7月9日-2014年8月9日</span></li>	
                <li>剩余抽奖次数：<span id="c_chance">0</span></li>		
            </ul>
        </div>
  		 <div class="lucky_draw_share" style="display:none;">分享獲得抽獎機會：<span id="c_shareActivity"></span></div>
  </div>
   
   
</div>
<div class="lucky_draw_top_b">
	<div class="lucky_draw_top_b_t">
		<div class="lucky_draw_zp">
        	<div id="c_point_wrap" class="lucky_draw_zp_zz" style="right: 172px;top: 202px;cursor: pointer;"><img id="c_point" src="${resDomain}/www/res/pan/images/zz.png"  width="160" height="160" /></div>
        	<p class="title">精品獎品</p>
            <ul>
            	<li><img src="${resDomain}/www/res/pan/images/jp01.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/www/res/pan/images/jp02.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/www/res/pan/images/jp03.jpg" width="140" height="115" /></li>     
                <li><img src="${resDomain}/www/res/pan/images/jp04.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/www/res/pan/images/jp05.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/www/res/pan/images/jp06.jpg" width="140" height="115" /></li>            
            </ul>
   
        </div>
        
        <div class="lucky_draw_list">
          <div class="lucky_draw_list_title">
            <ul>
               <li class="t1">中獎用戶 </li>
               <li class="t2"><a href="/web/prize!prizeList.action" target="_blank">查看我的獎品</a></li>
             </ul>
          </div>
         <div class="lucky_draw_list_c"> 
            <ul id="c_lottery_record">
                <!-- <li><span class="wd">用戶名aaaaaaaaaaaaaaa</span>抽中50000000000000000000000元优惠劵一张</li> -->
            </ul>
		</div>
        </div>
        
        
        
         <div class="lucky_draw_rules">
          <div class="lucky_draw_rules_title">
            <ul>
               <li class="t1">抽獎規則 </li>
             </ul>
          </div>
         <div id="c_lottery_rule" class="lucky_draw_rules_c"> 
            1.登录抽奖页面，每天不限抽奖次数，每次抽奖所需根据页面提
			示扣除。<br />
			2. 奖品发放的方式：<br />
			奖品为电子书、电子音乐、彩票、充值卡等，系统在24小时内发放
			到账户，会生成一个订单，不需要发货；奖品为实物商品，中奖后
			系统将在24小时内发到账户，客服将在中奖后5~10个工作日内联系
			核实信息，之后将于10个工作日内安排发货。中奖信息和发货后的
			物流信息，均会在用户中心我的奖品展示。奖品为优惠券，系统在
			24小时内发到账户，可在用户中心>优惠券中查看；
		</div>
        </div>      
     </div>
	<div class="bottom">
	  <div class="content"> Copyright(C) 2007-2015 啟創科技（香港）公司（CREATE NEW TECHNOLOGY(HK) LIMITED ）.All Rights Reserved</div>
	</div>
</div>
<!-- 引出提示框 -->
<div id="c_award_area" class="box" style="position: absolute;z-index: 100;display: none;background: #ece4ce;">
	<p class="box_close"><a href="javascript:$.closeTip();"><img src="${resDomain}/www/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c">恭喜您，抽中了<font id="c_award" class="red">5元優惠券</font><br />
		<input type="button" value="繼續抽獎" class="btn_box c_go_draw" style="cursor: pointer;" /><br />
		<a href="/web/prize!prizeList.action">查看我的獎品</a>
	</div>
</div>
<div id="c_fail_area" class="box" style="position: absolute;z-index: 100;display: none;background: #ece4ce;">
	<p class="box_close"><a href="javascript:$.closeTip();"><img src="${resDomain}/www/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c"><font class="red">很遺憾，這次沒中</font><br />
		<input type="button" value="繼續抽獎" class="btn_box c_go_draw" style="cursor: pointer;" /><br />
		<span id="c_fail_share" style="display:none;">分享贏抽獎：<a id="c_go_share_weibo" href="javascript:void(0);"><img src="${resDomain}/www/res/pan/images/wb.gif" width="22" height="19" /></a>
		<a id="c_go_share_weixin" href="javascript:void(0);"><img src="${resDomain}/www/res/pan/images/wx.gif" width="22" height="19" /></a></span>
	</div>
</div>
<script type="text/javascript">
function FormatDate(date){
	var dd = date.split('-');
	return dd[0]+'年'+dd[1]+'月'+dd[2]+'日';
}
//抽奖活动配置,可修改成其它抽奖活动配置
var LotteryConfig = {
	lotteryCode : '<%out.print(PropertiesHelper.getProperty("www.lottery_code"));%>',	//抽奖活动号
	//items : [],	//抽奖图中对应的抽奖项
	init : -10, //初始指针角度
	populate : function(detail) {
		$('#c_ttl').text(detail.title);
		$('#c_time').text(FormatDate(detail.startTime)+"-"+FormatDate(detail.endTime));
		$('#c_lottery_rule').html(detail.rule);
	}
}
//分享活动设置
$('#c_shareActivity').activityShare({
	shareid: '<%out.print(PropertiesHelper.getProperty("www.lottery.share_code"));%>',
	beforeShare: function(){
		var res = checkLogin();
		!res && onshow('tx_b','loginDiv');
		return res;
	},
	weiboSuccess: function(a){
		var uid = lshop.getCookieToJSON('user').uid;
		//回调赠送抽奖机会
		$.post('/web/activity!shareCallback.action', {userCode: uid, activityCode: '<%out.print(PropertiesHelper.getProperty("www.lottery.share_code"));%>', t: new Date().getTime()}, function(data){
			data.prize && $('#c_chance').trigger('getChance');
		});
	}
});
//弹窗内抽奖
$('#c_go_share_weibo').click(function(e){
	$.closeTip();
	$('#c_wb_publish').trigger('click');
});
$('#c_go_share_weixin').click(function(e){
	$.closeTip();
	$('#c_wx_publish').trigger('click');
});
</script>
</body>
</html>
