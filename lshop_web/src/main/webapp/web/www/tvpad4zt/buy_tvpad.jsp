<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad商城</title>
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
<%@include file="/web/www/common/header.jsp" %>
	
  
  <div class="tvpad4">
    <div class="buytvpad">
      <div class="buy_tvpad_banner"><img src="${resDomain}/www/res/images/buy_tvpad.jpg" /></div>
      <div class="tvpad4_hd"><img src="${resDomain}/www/res/images/tvpad4_hd1.jpg" /></div>
      <div class="tvpad4_hd tv2">
        <div class="tvpad_duihuan">
        <form action="http://www.itvpad.com/web/activity!getCouponByActivityLinkLogout.action?activityCode=1a98833e467f46998295cf50cd2712e7" method="post" onsubmit="return checkMac()">
          <input type="text"  id="macCode" name="macCode" class="tvpad4_input"/> <input type="submit" class="tvpad4_bt" value=" "/>
        </form>
        </div>
        <a href="http://www.tvpadfans.com/thread-266803-1-1.html" class="de_tvpad" target="_blank">詳情跳轉論壇</a>
        <a href="http://www.tvpadfans.com/thread-266803-1-1.html" class="tv_pans" target="_blank">Fans论坛</a>
      </div>
      <div class="tvpad4_hd hd3">
       <a href="https://www.facebook.com/mytvpad" class="face_tv4" target="_blank"><img src="${resDomain}/www/res/images/face_tv4.jpg" /></a>
       <a href="http://weibo.com/tvpad" class="weibo_tv4" target="_blank"><img src="${resDomain}/www/res/images/weibo_tv4.jpg" /></a>
       <a href="#" onmouseup="showWx(true)" onmousemove="showWx(true)" onmouseout="showWx(false)" class="wexi_tv4" target="_blank"><img src="${resDomain}/www/res/images/wexi_tv4.jpg" /></a>
        <div class="erweima" style="display:none"><img src="${resDomain}/www/res/images/tv4_erwei.jpg" /></div>
      </div>
    </div>
  </div>
  
<script type='text/javascript' src='http://chat.53kf.com/kf.php?arg=lulucute&style=1'></script>
<script type="text/javascript">
function showWx(flag){
	if(flag){
		$('.erweima').show();	
	}else{
		$('.erweima').hide();
	}
}
</script>

<!-- 第三方统计代码 -->
<div style="display:none;">
<script type="text/javascript"> 
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-39949584-1']);
  _gaq.push(['_setDomainName', 'itvpad.com']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>
<!-- Google Code for Remarketing Tag -->
<script type="text/javascript">/* <![CDATA[ */var google_conversion_id = 978626603;var google_custom_params = window.google_tag_params;var google_remarketing_only = true;/* ]]> */</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js"></script>
<noscript><div style="display:inline;"><img height="1" width="1" style="border-style:none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/978626603/?value=0&guid=ON&script=0"/></div></noscript>
<!--CNZZ Stat-->
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1253222638'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s5.cnzz.com/z_stat.php%3Fid%3D1253222638%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
<!--Baidu Stat-->
<script type="text/javascript"> 
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc15ec875396f2062a273652b959d8b97' type='text/javascript'%3E%3C/script%3E"));
</script>
</div>


</body>
</html>
