<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="com.lshop.common.util.PropertiesHelper" %>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抽奖专题</title>
<!-- 加载公共JS -->
<%@include file="/web/bmcn/common/top.jsp"%>
<link href="${resDomain}/bmcn/res/pan/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/bmcn/res/js/jquery.rotate.js"></script>
<script type="text/javascript" src="${resDomain}/bmcn/res/pan/js/rotate.js"></script>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/share.js"></script>
</head>

<body>
<div id="sc_top">
	<!--login -->
	<div id="loginDiv"   style="display:none;background-color:#000000;width:800px;height:600px;position:absolute;top:0px;left:0px;text-align:center;z-index:9000;filter:alpha(opacity=10);opacity:0.3"></div>
	<div id="tx_b" style="top:124px;position: absolute;display: none;z-index:9999;">
		<div class="pop_up_box" style="background:white;">
			<span class="box_l">用户登录<span class="box_r"><a href="javascript:onhide('tx_b','loginDiv');"><img src="${resDomain}/bmcn/res/images/close.gif" border="0" /></a></span></span>
			<form id="divlogin"  action="${storeDomain}/web/userCenterManage!login.action" method="post" onsubmit="">
				<input type="hidden" name="loginstyle" value="0"/>
				<input type="hidden" name="jumpurl" id="jumpurl" value=""/>
				<ul>
					<li id="erro_msg" style="display: none;"><span id="msg" class="redfont" style="margin-left: 90px;"></span></li>
					<li><span class="wd1">Email：</span><span class="wd_input"><input name="uname" id="uname" class="input02" type="text"  value="${uname}" maxlength="60"/></span></li>
					<li><span class="wd1">密 码：</span><span class="wd_input"><input name="pwd"  id="pwd" class="input02"  type="password" /></span></li>
					<li class="btn"><font class="redfont"><a href="${storeDomain}/web/userCenterManage!toFindPassword.action">忘记密码？</a></font> <font class="redfont"><a href="javascript:toReg('${storeDomain}');">注册</a></font></li>
					<li class="btn"><input type="submit" value="登录" class="user_center_bt02"/></li>
		  		</ul>
	  		</form>
		</div>
	</div>
</div>
<div class="lucky_draw_topbg">
  <div class="lucky_draw_top">
  		<div class="lucky_draw_t">
        	<ul>
           	  <li id="c_ttl" class="title"></li>
                <li>活动日期：<span id="c_time"></span></li>	
                <li>剩余抽奖次数：<span id="c_chance">0</span></li>		
            </ul>
        </div>
  		 <div class="lucky_draw_share" style="display:none;">分享获得抽奖机会：<span id="c_shareActivity"></span></div>
  </div>
   
   
</div>
<div class="lucky_draw_top_b">
	<div class="lucky_hei"></div>
	<div class="lucky_draw_top_b_t">
		<div class="lucky_draw_zp">
        	<div id="c_point_wrap" class="lucky_draw_zp_zz" style="right: 172px;top: 202px;cursor: pointer;"><img id="c_point" src="${resDomain}/bmcn/res/pan/images/zz.png" width="160" height="160" /></div>
        	<p class="title">精品奖品</p>
            <ul>
            	<li><img src="${resDomain}/bmcn/res/pan/images/jp01.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/bmcn/res/pan/images/jp02.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/bmcn/res/pan/images/jp03.jpg" width="140" height="115" /></li>     
                <li><img src="${resDomain}/bmcn/res/pan/images/jp04.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/bmcn/res/pan/images/jp05.jpg" width="140" height="115" /></li>
                <li><img src="${resDomain}/bmcn/res/pan/images/jp06.jpg" width="140" height="115" /></li>            
            </ul>
   
        </div>
        
        <div class="lucky_draw_list">
          <div class="lucky_draw_list_title">
            <ul>
               <li class="t1">中奖用户 </li>
               <li class="t2"><a href="/web/prize!prizeList.action" target="_blank">查看我的奖品</a></li>
             </ul>
          </div>
         <div class="lucky_draw_list_c"> 
            <ul id="c_lottery_record"></ul>
		</div>
        </div>
        
        
        
         <div class="lucky_draw_rules">
          <div class="lucky_draw_rules_title">
            <ul>
               <li class="t1">抽奖规则 </li>
             </ul>
          </div>
         <div id="c_lottery_rule" class="lucky_draw_rules_c" style="word-break: break-all;"></div>
        </div>
        <div class="clear"></div>
     </div>
	<!-- footerinfo -->
	<div class="bottom"> 		
		<cus:store param="footerinfo"/>
	</div>
</div>
<!-- 引出提示框 -->
<div id="c_award_area" class="box" style="position: absolute;z-index: 100;display: none;">
	<p class="box_close"><a href="javascript:void(0)" onclick="$.closeTip();"><img src="${resDomain}/bmcn/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c">恭喜您，抽中了<font id="c_award" class="red"></font><br />
		<input type="button" value="继续抽奖" class="btn_box c_go_draw" style="cursor: pointer;"/><br />
		<a href="/web/prize!prizeList.action" target="_blank">查看我的奖品</a>
	</div>
</div>
<div id="c_fail_area" class="box" style="position: absolute;z-index: 100;display: none;">
	<p class="box_close"><a href="javascript:void(0)" onclick="$.closeTip();"><img src="${resDomain}/bmcn/res/images/close.gif" width="37" height="19" /></a></p>
	<div class="box_c"><font class="red">很遗憾，这次没中</font><br />
		<input type="button" value="继续抽奖" class="btn_box c_go_draw" style="cursor: pointer;"/><br />
		<span id="c_fail_share" style="display:none;">分享赢抽奖：<a id="c_go_share_weibo" href="javascript:void(0);"><img src="${resDomain}/bmcn/res/pan/images/wb.gif" width="22" height="19" /></a>
		<a id="c_go_share_weixin" href="javascript:void(0);"><img src="${resDomain}/bmcn/res/pan/images/wx.gif" width="22" height="19" /></a></span>
	</div>
</div>
<script type="text/javascript">
function FormatDate(date){
	var dd = date.split('-');
	return dd[0]+'年'+dd[1]+'月'+dd[2]+'日';
}
//抽奖活动配置,可修改成其它抽奖活动配置
var LotteryConfig = {
	lotteryCode : '<%out.print(PropertiesHelper.getProperty("bmcn.lottery_code"));%>',	//抽奖活动号
	//items : [],	//抽奖图中对应的抽奖项
	init : -10, //初始指针角度
	populate : function(detail) {
		$('#c_ttl').text(detail.title);
		$('#c_time').text(FormatDate(detail.startTime)+"-"+FormatDate(detail.endTime));
		$('#c_lottery_rule').html(detail.rule);
	}
};
//分享活动设置
$('#c_shareActivity').activityShare({
	shareid: '<%out.print(PropertiesHelper.getProperty("bmcn.lottery.share_code"));%>',
	beforeShare: function(){
		var res = checkLogin();
		!res && onshow('tx_b','loginDiv');
		return res;
	},
	weiboSuccess: function(a){
		var uid = lshop.getCookieToJSON('user').uid;
		//回调赠送抽奖机会
		$.post('/web/activity!shareCallback.action', {userCode: uid, activityCode: '<%out.print(PropertiesHelper.getProperty("bmcn.lottery.share_code"));%>', t: new Date().getTime()}, function(data){
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
