<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>美國區域TVpad感恩節</title>
<link href="${resDomain}/www/res/css/css_zt4_easter.css" rel="stylesheet" type="text/css" />
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

	//验证输入MAC码的有效性
	function checkMac() {
		var macCode = $("#macCode").val();
		//验证输入mac是否为空
		if (macCode == null || macCode.length <= 0) {
			alert("请输入正确的MAC码");
			return false;
		}

		//验证码格式是否正确
		if (!/^ac867e[a-fA-F0-9]{6}$/.test(macCode.toLowerCase())) {
			alert("请输入正确的MAC码");
			return false;
		}

		//验证cookie是否已经存在该验证码
		var macCookie = getCookie("macCookie");
		if (macCookie != null && macCookie.length > 0 && macCookie == macCode) {
			alert("该MAC码已经领取过优惠券了！");
			return false;
		} else {
			setCookie("macCookie", macCode,1)
		}
		return true;
	}
</script>

</head>

<body>
<% request.setAttribute("navFlag","www_tvpadzt"); %>
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->

<!--头部广告-->
	<div class="tvpad_fuhuo">
		<div class="tvpad_fuhuojie01">
			<img src="${resDomain}/www/res/images/tvpad4zt_easter/fuhuojie_banner01.jpg" />
		</div>
	</div>
	<div class="tvpad_nr">
		<div class="tvpad_fuhuoyh">
			<div class="tvpad_fuhuoimg1">
				<img src="${resDomain}/www/res/images/tvpad4zt_easter/ganenzt-02.jpg" height="171" />
			</div>
			<!--End top-->
		</div>
		<div class="tvpad_fuhuoyh02">
			<div style="position: relative;">
				<span class="bn_zhuanticircleSpan"></span>
				<div class="tvpad_fuhuoimg2">
					<div style="position: relative;">
						<span class="bn_zhuanticircleSpan"></span> 
						<img src="${resDomain}/www/res/images/tvpad4zt_easter/ganenzt-03.jpg" height="328" width="1000" /> 
							<span class="bn_zhuantispotSoan"> 

							<form action="http://www.itvpad.com/web/activity!getCouponByActivityLinkLogout.action?activityCode=ca3b9201207a45e3b3e2aa3168e0942a" method="post" onsubmit="return checkMac()">
								<input type="text" class="input_fuhuo" id="macCode" name="macCode"/> 
								<input type="submit" class="tvpad4_btfuhuo" value=" " /> 
							</form>
							
							</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="tvpad_fuhuoyh1">
		<div class="tvpad_fuhuoimg1">
			<img src="${resDomain}/www/res/images/tvpad4zt_easter/ganenzt-04.jpg" height="403" />
		</div>
		<div class="tvpad_fuhuoimg1">
			<img src="${resDomain}/www/res/images/tvpad4zt_easter/ganenzt-05.jpg" height="250" />
		</div>
		<div class="tvpad_fuhuoimg3">
			<div style="position: relative;">
				<img src="${resDomain}/www/res/images/tvpad4zt_easter/ganenzt-06.jpg" height="127" width="1000"
					usemap="#Map1" />
				<map name="Map1" id="Map1">
					<area shape="rect" coords="65,4,201,59" href="#" />
					<area shape="rect" coords="214,4,351,58" href="#" />
					<area shape="rect" coords="368,6,492,53" href="#" target="_blank" />

				</map>
			</div>
		</div>
	</div>
	<!--11 -参数END-->  


<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
