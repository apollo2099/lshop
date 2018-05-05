<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad商城</title>
<link href="${resDomain}/www/res/css/tg_zt.css" rel="stylesheet" type="text/css" />
</head>
<% 
String couponCode=request.getParameter("couponCode");
request.setAttribute("couponCode",couponCode);
%>

<body>
<div class="tg_banner">
  <ul>
    <li><p class="cankaojia">(当地参考价：<span class="red">USD259</span>)</p><p class="tiyan"><a href="#">免費體驗>></a></p></li>
	<li><a href="http://cn.mtvpad.com/web/product!toProduct.action?pcode=f89bb9d4a100420ca8e945d0f3868cd4" target="_blank"><img src="${resDomain}/www/res/images/tg/btn1.gif" border="0" /></a> <a href="http://chat.53kf.com/webCompany.php?arg=lulucute&style=1" target="_blank"><img src="${resDomain}/www/res/images/tg/btn2.gif" border="0" /></a></li>
  </ul>
</div>
<!--banner结束-->

<div class="tg_ggw">
 <p class="tg_tu"><img src="${resDomain}/www/res/images/tg/ggw.jpg" /></p>
 <p class="lipinquan">${couponCode }</p>
</div>
<!--广告位结束-->

<div class="tg_main">
  <div class="tg_main_left">
    <div class="tg_main_left1">
	  <h1 class="yijian_bt">來自用戶的意見</h1>
	  <dl>
	    <dt><img src="${resDomain}/www/res/images/tg/touxiang.jpg" /></dt>
		<dd>“每周六全家一起通过TVpad觀看浙江衛視觀看“中國好聲音”是一道回味無窮的盛宴。通過TVpad連接至55”大屏幕高清電視機、家庭影院環繞立體聲，絕對身臨其境。”  
		<span>Dallas</span>
		</dd>
	  </dl>
	  <dl>
	    <dt><img src="${resDomain}/www/res/images/tg/touxiang1.jpg" /></dt>
		<dd>“有了TVpad的日子，家居生活比之前開心熱鬧多了，我依舊是守著最愛的賽馬，太太喜愛看影視谷的點播劇集和電影；老母親雖然年邁，卻仍是每早追看劇集。”  
		<span>阿二</span>
		</dd>
	  </dl>
	  <dl>
	    <dt><img src="${resDomain}/www/res/images/tg/touxiang2.jpg" /></dt>
		<dd>“TVpad徹底改變了我的生活。下班回家收看国内频道，和国内朋友零距离交流。tvpad频道内容丰富，画面效果出色，没有丝毫停顿感，更不用大电脑连TV上影响居室美观，增加耗电量。”  
		<span>澳洲奥利奥</span>
		</dd>
	  </dl>
	  <dl>
	    <dt><img src="${resDomain}/www/res/images/tg/touxiang3.jpg" /></dt>
		<dd>“经过一个多月的使用，一句话：非常满意! 简单，易用，流畅，清晰，就连我妈（快80的人了）这种录音机都不会开的人也能用TVpad！”  
		<span>Mr.Wu</span>
		</dd>
	  </dl>
	  <dl class="none_line">
	    <dt><img src="${resDomain}/www/res/images/tg/touxiang4.jpg" /></dt>
		<dd>“TVpad小巧玲瓏，外形精緻，不會佔用太多空間對住房细小的移民人士來說，太合適了。操作簡單，插上电源連好电视及网络，輕輕按幾下遙控器就可进入想看的节目。內容豐富絕不比電腦遜色。”   
		<span>Sfjohn</span>
		</dd>
	  </dl>
	</div>
  </div>
  <!--left结束-->
  
  <div class="tg_main_right"> 
    <p>
	TVpad 2是基於定製版智能系统的網絡電視盒，使用者通過安裝TVpad Store上的第三方<span class="red">正版APP</span>可樂享中港台電視、影視點播、K歌、VOIP電話、體感遊戲等休閒娛樂服務。
	</p>
	<img src="${resDomain}/www/res/images/tg/images1.jpg" />
	<img src="${resDomain}/www/res/images/tg/images2.jpg" />
	<img src="${resDomain}/www/res/images/tg/images3.jpg" />
	<img src="${resDomain}/www/res/images/tg/images4.jpg" />
	<p class="chaxun"><a href="#">更多TVpad詳情請點擊>></a></p>
  </div>
  <!--right结束-->
  
</div>
</body>
</html>
