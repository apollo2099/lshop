<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad Trade-in Program</title>
<meta name="description" content="TVpad4, our best product ever, is not for trade-in. You can get a $130 off by offering the MAC address of your old TVpad. And you will get latest TVpad4 with higher more contents, higher definition, faster response and smoother experience at$169." />
<meta name="keywords" content="TVpad Trade-in Program price,TVpad Trade-in Program discount,TVpad Trade-in Program worthy or not,TVpad Trade-in Program coupon" />
<link href="${resDomain}/en/res/tvpad4zt_mac/css/sale_flowEn.css" rel="stylesheet" type="text/css" />
<script src="${resDomain}/en/res/tvpad4zt_mac/js/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function($) {
	$("#tbox .taoba").click(function(event) { 
		var mainpage=this.id;
		var id='.'+'mainpage'+mainpage;
	   $("html,body").animate({scrollTop: $(id).offset().top}, 700);
	   $(".tbox_bj li").eq(mainpage-1).show().siblings().hide();
	   if(mainpage!=1){
		  $(this).addClass("taoba2").parent().siblings().children().removeClass("taoba2 taoba3");
		   
		  }else if(mainpage=1){
		    $(this).addClass("taoba3").parent().siblings().children().removeClass("taoba2");
			  }	 
	});
	$("#tbox .taoba").hover(function(){
		var mainpage=this.id;
		$(".tbox_bj li").eq(mainpage-1).show().siblings().hide();
		},function(){
		var mainpage=this.id;
		$(".tbox_bj li").eq(mainpage-1).hide();	
	});
	
});

function toQuickOrder(){
	$("#macForm").submit();
}
</script>
</head>
<!--[if IE 6]> 
<script type="text/javascript" src=""js/DD_belatedPNG.min.js""></script> 
<script>DD_belatedPNG.fix('*');</script> 
<![endif]-->
<body>
<!-- 调转到快速下单页面 -->
<form id="macForm" action="/web/shopCart!toQuickOrder.action#md" method="get" target="_blank">
   <input type="hidden" name="shopFlag" value="tvpaden"/>
   <input type="hidden" name="productCode" value="0e55accec6f44cd484dc6ca30ba46b3a"/>
   <input type="hidden" name="sourceUrl" value="http://en.mtvpad.com/169/index.html"/>
</form>

<ul id="tbox">
    <li><a id="huanxin" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpaden&productCode=0e55accec6f44cd484dc6ca30ba46b3a&sourceUrl=http://en.mtvpad.com/169/index.html#md" target="_blank"></a></li>
    <li><a class="taoba taoba1" href="javascript:void(0)" id="1" >1</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="2" >2</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="3">3</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="4">4</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="5">5</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="6">6</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="7">7</a></li>
</ul>
<ul class="tbox_bj">
	<li class="taoba_bj1">Back to top</li>
    <li class="taoba_bj2">Trade in now</li>
    <li class="taoba_bj3">About TVpad4</li>
    <li class="taoba_bj4">User review</li>
    <li class="taoba_bj5">Rules</li>
    <li class="taoba_bj6">FAQ</li>
    <li class="taoba_bj7">Contact</li>
</ul>
<!--bananer-->
<div class="mainpage1">
	<a class="btn1" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpaden&productCode=0e55accec6f44cd484dc6ca30ba46b3a&sourceUrl=http://en.mtvpad.com/169/index.html#md"  target="_blank"><img src="${resDomain}/en/res/tvpad4zt_mac/images/btnEn_1.png" /></a>
    <a class="btn2" href="http://www.mtvpad.com/169/index.html">去中文頁</a>
    <div class="guize clearfix">
    	<img src="${resDomain}/en/res/tvpad4zt_mac/images/guizeEn.png" />
        <p>You can get a $130 coupon by offering the MAC address of your old TVpad<br/>to buy our latest <span>TVpad4 at $169.</span></p>
    </div>
	<div class="mainpage1_ban clearfix">
    	<img src="${resDomain}/en/res/tvpad4zt_mac/images/renew_bananerEn.jpg"/>
	</div>
</div>
<!--立即換新-->
<div class="mainpage2">
    <div class="mainpage2_top">
        <div class="liuchen_tit"></div>
        <div class="liuchen_tet">
            <p class="liuchen_p1">Enter your email,<br/>phone number and old<br/>TVpad’s MAC address</p>
            <p class="liuchen_p2">Enter your<br/>shipping address</p>
            <p class="liuchen_p3">Finish payment</p>
        </div>
    </div>
	<div class="mainpage2_buttom">
    	<a class="sale" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpaden&productCode=0e55accec6f44cd484dc6ca30ba46b3a&sourceUrl=http://en.mtvpad.com/169/index.html#md"  target="_blank">Get $130 Off</a>
    </div>
</div>
<!--瞭解TVpad4-->
<div class="mainpage3">
	<h1>Why TVpad4 Streams Better?</h1>
    <div class="mainpage3_conter">
    	<h1 class="conter_t1">High Definition</h1><h1>Smoother</h1><h1>Faster Response</h1><h1>Bigger on Contents</h1>
    	<p class="conter_p1">Support H.265<br/>technology and<br/>2K HD display</p>
        <p>Updated OS brings<br>you a smoother<br>experience</p>
        <p>4G CPU,<br>response time<br>reduced by 80%</p>
        <p>Open Android OS<br>supports more<br>third-party apps</p>
    </div>
    <div class="mainpage3_buttom">
    	<iframe src="https://www.youtube.com/embed/qBjH6SSIjYk" allowfullscreen="" frameborder="0" width="855" height="508"></iframe>
    </div>
</div>
<!--用戶評論-->
<div class="mainpage4">
	<img src="${resDomain}/en/res/tvpad4zt_mac/images/renew_bananerEn2.jpg" usemap="#Map" alt="TVpad以旧换新"/>
    <map name="Map" id="Map">
        <area shape="rect" coords="290,265,732,359" href="http://tvpaden.mtvpad.com/web/product!ztProductComments.action?pcode=0e55accec6f44cd484dc6ca30ba46b3a" target="_blank" />
    </map>
</div>
<!--活動細則-->
<div class="mainpage5">
	<div class="xize"></div>
    <div class="xizebj">
    	<h1 class="xizebj_h1">Time</h1><h1 class="xizebj_h2">Qualification</h1><h1 class="xizebj_h3">Limitation</h1>
    	<p class="xizebj_t1">It varies in different TVpad<br/>version, please pay attention<br/>to the notice on your TVpad.</p>	
        <p class="xizebj_t2">TVpad trade-in program is available<br/>for all old users. Each MAC address<br/>can be used for only once.</p>
        <p class="xizebj_t3">Only TVpad4 is available<br/>for the trade-in and on<br/>TVpad Mall only.</p>
    </div>
</div>
<!--常見問題-->
<div class="mainpage6">
    <div class="mainpage6_jz">
        <h1>FAQ</h1>
        <div class="Qn">
            <div class="Qn_h">Q1</div>
            <h2>Where can I find the MAC address? How many times can the MAC address be used?</h2>
            <p>A: You can find it on the back of the box.</p>
            <img src="${resDomain}/en/res/tvpad4zt_mac/images/chkan.jpg" />
            <p class="tit_em"><em>★</em> If the label is broken or fallen, you can find the MAC address on Settings-About-System Info. Each MAC address can be used for only once.</p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q2</div>
            <h2>Is the trade-in program only available on TVpad Mall? Can I take part in the trade-in program on TVpad authorized Amazon or AliExpress store?</h2>
            <p>A: TVpad trade-in program is only available on TVpad official website <em>(www.mtvpad.com).</em></p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q3</div>
            <h2>After trading in my old TVpad for the new TVpad4, will my old TVpad still work?</h2>
            <p>A: After trade-in finished, we’ll stop the service and your old TVpad won’t work.</p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q4</div>
            <h2>How long does it take for you to arrange shipment after I finished the payment? And how long does it take to receive the package?</h2>
            <p>A: The shipment will be arranged within a week after payment. You can check the order status at “Help Center-Logistics”. Please keep yourself in touch before receipt of your package.</p>
        </div>
    </div>
</div>
<!--我要諮詢-->
<div class="mainpage7">
	<img src="${resDomain}/en/res/tvpad4zt_mac/images/renew_bananerEn3.jpg" />
    <div class="lianxi clearfix">
        <h1>If you still have questions, please feel free to contact us</h1>
        <div class="dianhua">
        	<img src="${resDomain}/en/res/tvpad4zt_mac/images/dianhua.png"/><p>Tel:<br/><span>(00852)21349910<br/>(00852)21349920<br/>(00852)21349901</span></p>
        </div>
        <div class="qq">
        	<img src="${resDomain}/en/res/tvpad4zt_mac/images/QQ.png" /><p>QQ:<br/><span>2389075307<br/>2276814617</span></p>
        </div>
        <div class="youjian">	
            <img src="${resDomain}/en/res/tvpad4zt_mac/images/youjian.png" class="youjian_img"/><p>Email:<br/><span>service@mtvpad.com</span></p>
        </div>
    </div>
    <div class="TV_weixin"></div>
    <p class="jieshiq">*TVpad reserves the right to interpret the program.</p>
</div>

<!-- 第三方统计代码 -->
<cus:store param="statcode"/>

</body>
</html>


