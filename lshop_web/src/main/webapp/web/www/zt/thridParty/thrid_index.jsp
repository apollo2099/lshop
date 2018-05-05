<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在EBAY，AMAZON等第三方平臺購買TVpad正品</title>
<meta name="description" content="您可在以下授權第三方平臺店鋪進行購買TVpad正品-AMAZON美國站，EBAY香港站，ALIEXPRESS和淘寶店；您可享受官方售後服務保障，請放心購買。" />
<meta name="keywords" content="第三方平臺，AMAZON，EBAY，Aliexpress, taobao, 阿裏速賣通，淘寶，TVpad4正品，TVpad官方旗艦店" />
<link href="${resDomain}/www/res/css/css_zt_thrid.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp" %>
</head>

<body>
<% request.setAttribute("navFlag","www_product"); %>
<%@include file="/web/www/common/header.jsp" %>
<!--End top-->

<div class="the_third_top Jbg"><img src="${resDomain}/www/res/images/thridParty/The_third_01.jpg" width="1920" height="500" /></div>
<div class="the_third_wrap">
<div class="the_third_zw">
	<p class="the_third_zw_t">第三方平台</p>
	<p class="the_third_zw_p1">我們在以下購物網站開設TVpad店鋪，讓您輕鬆自由選擇更習慣的購物方式。您通過以下官方開設的
TVpad店鋪所購買的TVpad產品，同樣可以獲得官方售後服務保障。</p>
  <p class="the_third_zw_p2"><font class="redfont">*</font> TVpad未授權任何個人或商家在網上銷售TVpad產品，除以下鏈接提供的店鋪外，您在其他店鋪購買的TVpad產品可能無法獲得官方售後服務保障。</p>
  
   <!-- 第三方购买平台专题广告位定义 -->
   <ad:ad adkey="AD_TVPAD_THIRD_ZT"></ad:ad>
   
   <%--
    <p class="the_third_zw_pic"><img src="${resDomain}/www/res/images/thridParty/The_third_02.jpg" width="800" height="246" border="0" usemap="#Map" />
      <map name="Map" id="Map">
        <area shape="rect" coords="8,28,197,226" href="http://www.amazon.com/TVpad-Generation-M418-Streaming-Player/dp/B00R10C2ZS/ref=sr_1_4?ie=UTF8&amp;qid=1419231542&amp;sr=8-4&amp;keywords=tvpad4" target="_blank" />
        <area shape="rect" coords="207,27,396,227" href="#" target="_blank" />
        <area shape="rect" coords="412,27,589,228" href="http://item.taobao.com/item.htm?spm=a230r.1.14.21.LUtBjq&amp;id=42638518310&amp;ns=1&amp;abbucket=5#detail" target="_blank" />
        <area shape="rect" coords="601,28,794,233" href="http://www.aliexpress.com/item/World-Debut-TVpad4-streaming-player/32243491951.html" target="_blank" />
      </map>
    </p>
     --%>
    <p class="the_third_zw_p3">TVpad官網舉辦的各類促銷活動可能不會在以上店鋪進行，請您留意。</p>
</div>
</div>

<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>

<!-- 引入图片居中JS处理-->
<script type="text/javascript">
	    var bgArr=$($(".Jbg")[0]).find("img"),W=window.screen.width,w;
		if(W<=1920){
			w=-parseInt((1920-W)/2)+"px";
			bgArr.css("margin-left",w);
		}
		else{
			w=parseInt((W-1920)/2)+"px";
			bgArr.css("margin-left",w);
		}
</script>
</body>
</html>
