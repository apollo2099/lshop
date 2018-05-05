<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad2定制版智能系统网络智能机顶盒</title>
<link href="/res/css/css.css" rel="stylesheet" type="text/css" />
<script type=text/javascript src="/res/js/jquery-1.4.4.min.js"></script>
<script type=text/javascript src="/res/js/slides.jquery.js"></script>
<script type="text/javascript" language="javascript"> 
function iFrameHeight() { 
var ifm= document.getElementById("iframepage"); 
var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument; 
if(ifm != null && subWeb != null) { 
ifm.height = subWeb.body.scrollHeight; 
} 
} 
</script> 
</head>
<body>
<div class="top">
  <p class="logo"><a href="index.html"><img src="/res/images/logo.jpg" border="0" /></a></p>
  <p class="mail"><span>고객 서비스 센터 ： </span><a href="mailto:korea@itvpad.com">korea@itvpad.com</a></p>
</div>
<!--End top-->

<div id="mainbody">
<!-- 代码 开始 -->
<script>
	$(function(){
		//滚动Banner图片的显示
		$('#slides').slides({
			preload: false,
			preloadImage: '//res/images/loading.gif',
			effect: 'fade',
			slideSpeed: 400,
			fadeSpeed: 100,
			play: 3000,
			pause: 100,
			hoverPause: true
		});
        	//$('#js-news').ticker();
    	});
	</script>
<!-- 滚动图片 -->
  <div id="slides" class="banner">
   <div class="bannerImg">
	<div class="slides_container">
	    <div id="banner_pic_1"><a href="http://www.aliexpress.com/store/product/TVpad2-with-1080P-HD-SMART-SET-TOP-BOX-100-Free-Shipping-A/315267_858074505.html"><img src="/res/images/banner01.jpg" width="1001" height="366"/></a></div>
		<div style="DISPLAY: none" id="banner_pic_2"><a href="http://www.aliexpress.com/store/product/TVpad2-with-1080P-HD-SMART-SET-TOP-BOX-100-Free-Shipping-A/315267_858074505.html"><img src="/res/images/banner02.jpg" width="1001" height="366"/></a></div>
		<div style="DISPLAY: none" id="banner_pic_3"><a href="http://www.aliexpress.com/store/product/TVpad2-with-1080P-HD-SMART-SET-TOP-BOX-100-Free-Shipping-A/315267_858074505.html"><img src="/res/images/banner03.jpg" width="1001" height="366"/></a></div>
	</div>
  </div>
 </div>
</div>
<!--End banner-->

<div class="project">
<iframe id="iframepage" src="/products.html"  frameborder="no"  SCROLLING="NO" width="100%"  BORDER="0" onLoad="iFrameHeight()" /> </iframe>

</div>
<!--End 專題-->

<!-- footer -->
<%@include file="/web/kr/common/footer.jsp" %>
</body>
</html>
