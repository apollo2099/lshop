<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad海外華人中文電視第一品牌—TVpad官方商城</title>
<meta name="description" content="TVpad提供7*24小時收看港澳、臺灣、大陸、國際主流直播，超過10萬部高清影片即點既播，頂級TV應用供海外華人獨享，真正H.265高清畫質體驗，同時集穩定，豐富，流暢于一身。" />
<meta name="keywords" content="Tvpad4 CN版,TVpad4 CN版介绍，Tvpad4 CN版上市, TVpad CN版优点,TVpad评测, TVpad评价，TVpad4 CN版好不好用，TVpad4 CN版常见问题，TVpad4 CN版最新活动 " />
<link href="${resDomain}/www/res/css/css_zt4_cn_tq.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp" %>

<script type="text/javascript">
    //写cookies
	function setCookie(c_name,value,expiredays){
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+expiredays)
		document.cookie=c_name+ "=" +escape(value)+
		((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	}

	//读取cookies
	function getCookie(c_name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=")
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1
				c_end = document.cookie.indexOf(";", c_start)
				if (c_end == -1)
					c_end = document.cookie.length
				return unescape(document.cookie.substring(c_start, c_end))
			}
		}
		return ""
	}

    //验证报名用户
	function checkCnUser(){
		var username = $("#username").val();
		//验证输入用户是否为空
		if (username == null || username.length <= 0) {
			$("#uTips").text("姓名/昵称不能为空");
			$("#uTips").show();
			return false;
		}else{
			$("#uTips").hide();
			return true;
		}
	}
	
	//验证邮箱
	function checkCnEmail(){
		var userEmail = $("#userEmail").val();
		//验证输入用户是否为空
		if (userEmail == null || userEmail.length <= 0) {
			$("#eTips").text("邮箱地址不能为空");
			$("#eTips").show();
			return false;
		}
		
		//验证邮箱格式
		var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	    if (!regExp.test(userEmail)) {
	    	$("#eTips").text("邮箱格式错误");
	    	$("#eTips").show();
	    	return false;
	    }
	    
		$("#eTips").hide();
		return true;
	}
	//验证报名宣言
	function checkCnDeclaration(){
		var declaration = $("#declaration").val();
		//验证输入用户是否为空
		if (declaration == null || declaration.length <= 0) {
			$("#dTips").text("报名宣言不能为空");
			$("#dTips").show();
			return false;
		}else if(declaration.length>0&&declaration.length<=10){
			$("#dTips").text("报名宣言不少于10个字符");
			$("#dTips").show();
			return false;
		}else{
			$("#dTips").hide();
			return true;
		}
	}
	//验证报名ip
	function checkCnIp(){
		//验证cookie是否已经存在该ip已经领取优惠码
		var ipCookie = getCookie("ipCookie");
		if (ipCookie != null && ipCookie.length > 0 && ipCookie == ip) {
			alert("对不起，每人只能报名一次！");
			return false;
		} else {
			setCookie("ipCookie", ip,30)
			return true;
		}
	}
	
	//验证输输入数据的有效性
	function checkForm() {
		var isvalid= checkCnUser() && checkCnEmail() && checkCnDeclaration() && checkCnIp();
		return isvalid;
	}
	
</script>


<script>
  jQuery(function($){
    var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_='+Math.random();  
    $.getJSON(url, function(data){
	    $("#ip").val(data.Ip);
    });
  });
</script>
</head>

<body>
<% request.setAttribute("navFlag","www_tvpad4zt"); %>
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->

<!--头部广告-->
<div class="tvpad_hod">
  <div class="tvpad_img1">
     <img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/tvpad4_cn_img1.jpg" />
     <div class="tv4_detial">
       <p>为了进一步提升海外华人用户的观看体验，真正做出满足用户需求的产品。</p>
       <p>TVpad诚邀300精英加入产品体验，陪我们一起打造更完美的TVpad4 CN版。</p>
     </div>
  </div> 
</div>

<div class="tvpad_tq">
  <div class="tvpad_tq_inner">
     <p class="tv4_tq">
         获取<span class="tv4_red">100美金优惠劵</span>，在指定日期内可以<span class="tv4_red">99美金特价</span>购买TVpad4 CN版；<br />
         第一手的固件升级体验，第一手的内测合作app体验；<br />
         后续新品的优先购买权（可享特价）。<br />
     </p>
     <p class="tv4_yw">
       1、同意接受TVpad官方的回访，以便我们了解产品的使用情况；<br />
       2、在TVpad官方社区（<a href="http://www.tvpadfans.com/">http://www.tvpadfans.com/</a>）积极反馈使用过程中的问题，帮助产品不断完善。
     </p>
     
  </div>
</div>

<div class="yhj_bg">
   <div class="tv4_bm">
      <div class="tv4_box">
         <div class="tv4_box_left">
           <img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/tv_img.jpg" />
         </div>
         <form action="http://www.mtvpad.com/web/activity!getCouponByActivityLinkLogout.action?activityCode=3188c81fd588443b9a524276c1d611f0" method="post" onsubmit="return checkForm()">
         <input type="hidden" name="ip" id="ip"/>
         <div class="tv4_box_right">
            <div class="errortips" id="uTips">错误提示</div>
            <p>
              <span class="fir_span">姓名/昵称：</span>
              <span><input type="text" name="username" id="username" onblur="checkCnUser();"/></span>
              <div class="clear"></div>
            </p>
             <div class="errortips" id="eTips">邮箱地址</div>
            <p>
              <span class="fir_span">邮箱地址：</span>
              <span><input type="text" name="userEmail" id="userEmail" onblur="checkCnEmail();" /></span>
              <div class="clear"></div>
            </p>
             <div class="errortips" id="dTips">(不少于10个字符)</div>
            <p>
              <span class="fir_span">报名宣言：</span>
              <span><textarea name="declaration" id="declaration" onblur="checkCnDeclaration();" ></textarea></span>
               <div class="clear"></div>
            </p>
            <p id="lastp">  
              <input type="submit" value="立即报名" />
            </p>
         </div>
         </form>
         <div class="clear"> </div>
      </div>
   </div>
   <div class="sygz">
      <div class="sygz_inner">
         <p>
          1. 凭券购买TVpad4 CN版可抵扣100美金（即99美金购机）；<br />
          2. 此券仅限TVpad商城（<a href="http://www.mtvpad.com/">http://www.mtvpad.com/</a>）使用；<br />
          3. 购买TVpad4 CN版付款时，优惠券栏输入券码即可减价，若不成功或不明处可联系在线客服；<br />
          4. 此券码仅限本活动，不与其他活动同时使用；<br />
          5. 券码使用时间截至：2015.1.31（北京时间）。
         </p>
      </div>
   </div>
</div>

<div class="tv4_gfhd">
  <div class="tv4_gf_title">参加更多TVpad官方平台活动</div>
  <div class="tv4_link">
   <a href="https://www.facebook.com/mytvpad" target="_blank"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/facebook.jpg" /></a>
   <a href="http://weibo.com/tvpad" target="_blank"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/weibo.jpg" /></a>
   <a href="#" onmouseup="showWx(true)" onmousemove="showWx(true)" onmouseout="showWx(false)" target="_blank"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/weixin.jpg" /></a>
   <a href="http://www.tvpadfans.com/forum.php" target="_blank"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/fans.jpg" /></a>
   <a href="http://www.mtvpad.com/index.html" target="_blank"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/tv_logo.jpg" /></a>
   <div class="erm" style="display:none"><img src="${resDomain}/www/res/images/tvpad4zt_cn_tq/tv4_erwei.jpg" /></div>
  </div>
  <div class="tvpad_bq">本活动最终解释权归TVpad官方所有</div>
</div>

<script type="text/javascript">
function showWx(flag){
	if(flag){
		$('.erm').show();	
	}else{
		$('.erm').hide();
	}
}
</script>
<script type='text/javascript' src='http://chat.53kf.com/kf.php?arg=lulucute&style=1'></script>
<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>

</body>
</html>
