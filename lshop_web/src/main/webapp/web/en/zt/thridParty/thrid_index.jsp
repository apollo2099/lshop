<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Purchase Genuine TVpad Products on eBay, Amazon and Other Third Party Platforms</title>
<meta name="description" content="You can purchase genuine TVpad products and enjoy after-sale services provided on TVpad Mall on the following authorized third party platforms: Amazon.US, eBay.HK, AliExpress and Taobao." />
<meta name="keywords" content="Third party platform, Amazon, eBay, AliExpress, Taobao, TVpad4 genuine products, TVpad4 quality guaranteed products, TVpad4 streaming player, TVpad4 Official Store, TVpad Mall " />
<link href="${resDomain}/en/res/css/css_zt_thrid.css" rel="stylesheet" type="text/css" />
<%@include file="/web/en/common/top.jsp" %>
</head>

<body>

<% request.setAttribute("navFlag","www_tvpadzt"); %>
<%@include file="/web/en/common/header.jsp" %>
<!--End top-->


<div class="the_third_top Jbg"><img src="${resDomain}/en/res/images/thridParty/The_third_01.jpg" width="1920" height="500" /></div>
<div class="the_third_wrap">
<div class="the_third_zw">
	<p class="the_third_zw_t">Third Party Platform</p>
	<p class="the_third_zw_p1">We have set up TVpad stores on Taobao, AliExpress, eBay, and Amazon, offering you a more convenient way to purchase our TVpad products and providing you with the same after-sale services provided by TVpad Mall.</p>
  <p class="the_third_zw_p2"><font class="redfont">*</font> TVpad never authorized anyone or any stores to sell TVpad products online, except the following official stores. If you purchased TVpad products on other unauthorized online stores, you may not enjoy the official after-sale services provided by TVpad Mall.</p>
  
     <!-- 第三方购买平台专题广告位定义 -->
   <ad:ad adkey="AD_TVPAD_THIRD_ZT"></ad:ad>
   
   <%--
    <p class="the_third_zw_pic"><img src="${resDomain}/en/res/images/thridParty/The_third_02.jpg" width="800" height="246" border="0" usemap="#Map" />
      <map name="Map" id="Map">
        <area shape="rect" coords="8,28,197,226" href="http://www.amazon.com/TVpad-Generation-M418-Streaming-Player/dp/B00R10C2ZS/ref=sr_1_4?ie=UTF8&amp;qid=1419231542&amp;sr=8-4&amp;keywords=tvpad4" target="_blank" />
        <area shape="rect" coords="207,27,396,227" href="#" target="_blank" />
        <area shape="rect" coords="412,27,589,228" href="http://item.taobao.com/item.htm?spm=a230r.1.14.21.LUtBjq&amp;id=42638518310&amp;ns=1&amp;abbucket=5#detail" target="_blank" />
        <area shape="rect" coords="601,28,794,233" href="http://www.aliexpress.com/item/World-Debut-TVpad4-streaming-player/32243491951.html" target="_blank" />
      </map>
    </p>
     --%>
    <p class="the_third_zw_p3">Please note that the promotions on TVpad Mall (official website) may not be available on the above stores.</p>
</div>
</div>

<!-- footer -->
<%@include file="/web/en/common/footer.jsp" %>


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
