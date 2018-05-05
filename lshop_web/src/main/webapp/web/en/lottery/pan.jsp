<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.lshop.common.util.PropertiesHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Luck Draw</title>
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
<link href="${resDomain}/en/res/pan/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/en/res/js/jquery.rotate.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/pan/js/rotate.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/share.js"></script>

</head>

<body>
<!--login -->
<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
	<div class="pop_up_box" style="background:white;">
		<span class="box_l">User Login<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/en/res/images/close.gif" border="0" /></a></span></span>
		<div style="color: red; margin: 10px 0 0 90px;"><span id="h_emsg"></span></div>
		<form action="${storeDomain}/web/userCenterManage!login.action" id="divlogin"
		method="post" onsubmit="">
			<input type="hidden" name="loginstyle" value="0"/>
			<input type="hidden" name="jumpurl" id="h_jumpurl" value=""/>
			<ul>
				<li id="erro_msg" style="display: none;height: 20px"><span id="msg" class="redfont" style="margin-left: 90px;"></span></li>
				<li><span class="wd1"><font class="redfont">*</font>Account：</span><input name="uname" id="h_uname" class="input02" type="text"  value="${uname}"/></li>
				<li><span class="wd1"><font class="redfont">*</font>Password：</span><input name="pwd"  id="h_pwd" class="input02"  type="password" /></li>
				<li><span class="wd1">&nbsp;</span><a href="${storeDomain}/web/userCenterManage!toFindPassword.action"> Forget password?</a>&nbsp;&nbsp;<a href="javascript:toReg('${storeDomain}');">Register</a></li>
				<li><span class="wd2"><input type="submit" value="Login" class="user_center_bt02" /></span></li>
	  		</ul>
  		</form>
	</div>
</div>
<div class="lucky_draw_topbg" style="width:100%;background: url(http://res.mtvpad.com/www/res/pan/images/lucky_draw_01.jpg) no-repeat center;">
  <div class="lucky_draw_top">
  		<div class="lucky_draw_t">
        	<ul>
           	  <li id="c_ttl" class="title"></li>
                <li>Promotion period ：<span id="c_time"></span></li>	
                <li>Remaining chance ：<span id="c_chance">0</span></li>		
            </ul>
        </div>
  		 <div class="lucky_draw_share" style="display:none;">Share to win a lucky draw chance ：<span id="c_shareActivity"></span></div>
  </div>
   
   
</div>
<div class="lucky_draw_top_b">
	<div class="lucky_draw_top_b_t">
		<div class="lucky_draw_zp">
        	<div id="c_point_wrap" class="lucky_draw_zp_zz" style="right: 172px;top: 202px;cursor: pointer;"><img id="c_point" src="${resDomain}/en/res/pan/images/zz.png"  width="160" height="160" /></div>
        	<p class="title">Attractive Prizes</p>
            <ul>
            	<li><img src="${resDomain}/en/res/pan/images/jp01.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/en/res/pan/images/jp02.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/en/res/pan/images/jp03.jpg" width="140" height="115" /></li>     
                <li><img src="${resDomain}/en/res/pan/images/jp04.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/en/res/pan/images/jp05.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/en/res/pan/images/jp06.jpg" width="140" height="115" /></li>            
            </ul>
   
        </div>
        
        <div class="lucky_draw_list">
          <div class="lucky_draw_list_title">
            <ul>
               <li class="t1">Winners List </li>
               <li class="t2"><a href="/web/prize!prizeList.action" target="_blank">View my prizes</a></li>
             </ul>
          </div>
         <div class="lucky_draw_list_c"> 
            <ul id="c_lottery_record">
            </ul>
		</div>
        </div>
        
        
        
         <div class="lucky_draw_rules">
          <div class="lucky_draw_rules_title">
            <ul>
               <li class="t1">Promotion rules</li>
             </ul>
          </div>
         <div id="c_lottery_rule" class="lucky_draw_rules_c"> 
		</div>
        </div>      
     </div>
	<div class="bottom">
	  <div class="content"> Copyright(C) 2007-2015 啟創科技（香港）公司（CREATE NEW TECHNOLOGY(HK) LIMITED ）.All Rights Reserved</div>
	</div>
</div>
<!-- 引出提示框 -->
<div id="c_award_area" class="box" style="position: absolute;z-index: 100;display: none;background: #ece4ce;">
	<p class="box_close"><a href="javascript:$.closeTip();"><img src="${resDomain}/en/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c" style="padding-top: 40px;">Congratulations!<br /> You`ve won a <font id="c_award" class="red"></font><br />
		<input type="button" value="Continue" class="btn_box c_go_draw" style="cursor: pointer;" /><br />
		<a href="/web/prize!prizeList.action">View my prizes</a>
	</div>
</div>
<div id="c_fail_area" class="box" style="position: absolute;z-index: 100;display: none;background: #ece4ce;">
	<p class="box_close"><a href="javascript:$.closeTip();"><img src="${resDomain}/en/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c"><font class="red">Better luck next time!</font><br />
		<input type="button" value="Continue" class="btn_box c_go_draw" style="cursor: pointer;" /><br />
		<span id="c_fail_share" style="display:none;">Share to get a lucky draw chance：<a id="c_go_share_weibo" href="javascript:void(0);"><img src="${resDomain}/en/res/pan/images/wb.gif" width="22" height="19" /></a>
		<a id="c_go_share_weixin" href="javascript:void(0);"><img src="${resDomain}/en/res/pan/images/wx.gif" width="22" height="19" /></a></span>
	</div>
</div>
<script type="text/javascript">
function FormatDate(date){
	var dd = date.split('-');
	var mon='';
	switch(dd[1]){
	case "1":
		mon = "Jan.";
		break;
	case "2":
		mon = "Feb.";
		break;
	case "3":
		mon = "Mar.";
		break;
	case "4":
		mon = "Apr.";
		break;
	case "5":
		mon = "May.";
		break;
	case "6":
		mon = "Jun.";
		break;
	case "7":
		mon = "Jul.";
		break;
	case "8":
		mon = "Aug.";
		break;
	case "9":
		mon = "Sep.";
		break;
	case "10":
		mon = "Oct.";
		break;
	case "11":
		mon = "Nov.";
		break;
	case "12":
		mon = "Dec.";
		break;
	}
	return mon+' '+dd[2]+', '+dd[0];
}
//抽奖活动配置,可修改成其它抽奖活动配置
var LotteryConfig = {
	lotteryCode : '<%out.print(PropertiesHelper.getProperty("en.lottery_code"));%>',	//抽奖活动号
	//items : [],	//抽奖图中对应的抽奖项
	init : -10, //初始指针角度
	populate : function(detail) {
		$('#c_ttl').text(detail.title);
		$('#c_time').text(FormatDate(detail.startTime)+" - "+FormatDate(detail.endTime));
		$('#c_lottery_rule').html(detail.rule);
	}
}
//分享活动设置
$('#c_shareActivity').activityShare({
	shareid: '<%out.print(PropertiesHelper.getProperty("en.lottery.share_code"));%>',
	beforeShare: function(){
		var res = checkLogin();
		!res && onshow('tx_b','loginDiv');
		return res;
	},
	weiboSuccess: function(a){
		var uid = lshop.getCookieToJSON('user').uid;
		//回调赠送抽奖机会
		$.post('/web/activity!shareCallback.action', {userCode: uid, activityCode: '<%out.print(PropertiesHelper.getProperty("en.lottery.share_code"));%>', t: new Date().getTime()}, function(data){
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
