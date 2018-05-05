<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad以舊換新</title>
<meta name="description" content="TVpad以舊換新價格,TVpad有舊換新優惠,TVpad以舊換新劃不划算,TVpad以舊換新值不值,TVpad以舊換新優惠券！" />
<meta name="keywords" content="TVpad以舊換新價格,TVpad有舊換新優惠,TVpad以舊換新劃不划算,TVpad以舊換新值不值,TVpad以舊換新優惠券" />
		
<link href="${resDomain}/www/res/tvpad4zt_mac/css/sale_flow.css" rel="stylesheet" type="text/css" />
<script src="${resDomain}/www/res/tvpad4zt_mac/js/jquery-1.10.2.js" type="text/javascript"></script>
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
   <input type="hidden" name="shopFlag" value="tvpadcn"/>
   <input type="hidden" name="productCode" value="6f2390c6bd794e28a34fc5dc512e4a2e"/>
   <input type="hidden" name="sourceUrl" value="http://www.mtvpad.com/2/index.html"/>
</form>

<ul id="tbox">
    <li><a id="huanxin" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpadcn&productCode=6f2390c6bd794e28a34fc5dc512e4a2e&sourceUrl=http://www.mtvpad.com/2/index.html#md"  target="_blank"></a></li>
    <li><a class="taoba taoba1" href="javascript:void(0)" id="1" >1</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="2" >2</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="3">3</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="4">4</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="5">5</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="6">6</a></li>
    <li><a class="taoba" href="javascript:void(0)" id="7">7</a></li>
</ul>
<ul class="tbox_bj">
	<li class="taoba_bj1">返回顶部</li>
    <li class="taoba_bj2">立即換新</li>
    <li class="taoba_bj3">瞭解TVpad4</li>
    <li class="taoba_bj4">用戶評論</li>
    <li class="taoba_bj5">活動細則</li>
    <li class="taoba_bj6">常見問題</li>
    <li class="taoba_bj7">我要諮詢</li>
</ul>
<!--bananer-->
<div class="mainpage1">
	<a class="btn1" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpadcn&productCode=6f2390c6bd794e28a34fc5dc512e4a2e&sourceUrl=http://www.mtvpad.com/2/index.html#md"  target="_blank"><img src="${resDomain}/www/res/tvpad4zt_mac/images/btn_1.png" /></a>
    <a class="btn2" href="http://en.mtvpad.com/2/index.html">TO English page</a>
    <div class="guize clearfix">
    	<img src="${resDomain}/www/res/tvpad4zt_mac/images/guize.png" />
        <p>憑舊機MAC碼直接抵扣$130<br/>仅需<span>$</span><em>169</em><span>/臺换购</span>TVpad最新機型TVpad4 </p>
    </div>
	<div class="mainpage1_ban clearfix">
    	<img src="${resDomain}/www/res/tvpad4zt_mac/images/renew_bananer.jpg"/>
	</div>
</div>
<!--立即換新-->
<div class="mainpage2">
    <div class="mainpage2_top">
        <div class="liuchen_tit"></div>
        <div class="liuchen_tet">
            <p  class="liuchen_p1">輸入您的資料<br/>及舊機MAC碼</p><p>輸入<br/>收貨地址</p><p>付款<br/>完成換購</p>
        </div>
    </div>
	<div class="mainpage2_buttom">
    	<a class="sale" href="/web/shopCart!toQuickOrder.action?shopFlag=tvpadcn&productCode=6f2390c6bd794e28a34fc5dc512e4a2e&sourceUrl=http://www.mtvpad.com/2/index.html#md"  target="_blank">立減$130换购</a>
    </div>	
</div>
<!--瞭解TVpad4-->
<div class="mainpage3">
	<div class="mainpage3_top"></div>
    <div class="mainpage3_conter">
    	<h1 class="conter_t1">更高清</h1><h1>更流暢</h1><h1>更靈敏</h1><h1>更多內容</h1>
    	<p class="conter_p1">採用最新H.265解碼<br>技術，支持2K高清視頻</p>
        <p>系統優化升級，全新算<br>法給你飛一般流暢體驗</p>
        <p> 4核CPU，效率更高<br/>換台時間縮短80%</p>
        <p>採用安卓開放系統<br>支持更多中港臺應用</p>
    </div>
    <div class="mainpage3_buttom">
    	<iframe src="https://www.youtube.com/embed/qBjH6SSIjYk" allowfullscreen="" frameborder="0" width="855" height="508"></iframe>
    </div>
</div>
<!--用戶評論-->
<div class="mainpage4">
	<img src="${resDomain}/www/res/tvpad4zt_mac/images/renew_bananer2.jpg" usemap="#Map" alt="TVpad以旧换新"/>
    <map name="Map" id="Map">
        <area shape="rect" coords="289,265,730,360" href="http://cn.mtvpad.com/web/product!ztProductComments.action?pcode=6f2390c6bd794e28a34fc5dc512e4a2e" target="_blank" />
    </map>
</div>
<!--活動細則-->
<div class="mainpage5">
	<div class="xize"></div>
    <div class="xizebj">
    	<h1 class="xizebj_h1">活動時間</h1><h1 class="xizebj_h2">參與活動的條件</h1><h1 class="xizebj_h3">活動范圍</h1>
    	<p class="xizebj_t1">不同版本活動時效不同,<br/>以您的盒子彈窗通知時<br/>間為準。</p>
        <p class="xizebj_t2">TVpad老用户憑舊機MAC碼,<br/>均有資格參與此次以舊換新活動,<br/>每個MAC碼只有一次參與機會。</p>
        <p class="xizebj_t3">以舊換新活動只限于TVpad<br/>商城範圍內的TVpad4單品,<br/>其他線上管道途徑無效。</p>
    </div>
</div>
<!--常見問題-->
<div class="mainpage6">
	<div class="mainpage6_jz">
        <h1>常見問題</h1>
        <div class="Qn">
            <div class="Qn_h">Q1</div>
            <h2>在哪裡查看MAC碼？一個MAC碼能重複使用嗎？</h2>
            <p>A: 請在盒子的背面查看</p>
            <img src="${resDomain}/www/res/tvpad4zt_mac/images/chkan.jpg" />
            <p class="tit_em"><em>★</em>如標籤汙損或脫落，請在盒子<em>系統設置-關於-系統資訊</em>中查看，一個MAC碼只能換購一台TVpad4，不能重複使用。請抓緊機會！</p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q2</div>
            <h2>憑MAC碼以舊換新活動是否一定要在TVpad商城使用？Amazon,Aliexpress授權店鋪可以用嗎？</h2>
            <p>A: 以舊換新活動是TVpad官網專屬<em>(www.mtvpad.com)</em>，其他線上管道途徑無效。</p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q3</div>
            <h2>兌換新TVpad4的盒子後，舊盒子還能繼續使用嗎？</h2>
            <p>A: 舊盒子即將停機，無法繼續使用！請抓緊時間換新機。現在換新機立減130元美金，包郵送貨上門！</p>
        </div>
        <div class="Qn">
            <div class="Qn_h">Q4</div>
            <h2>支付完成之後幾天可以發貨？大概多少天可以收貨？</h2>
            <p>A: 因以舊換新期間發貨量巨大，支付之後約一個星期之內發貨，我们均采用国际快递或者本地发货，具體各地區到貨時間可到幫助中心物流配送中查看。期間請您保持手機及其它通訊方式的順暢，確保順利收貨。</p>
        </div>
	</div>
</div>
<!--我要諮詢-->
<div class="mainpage7">
	<img src="${resDomain}/www/res/tvpad4zt_mac/images/renew_bananer3.jpg" />
	<div class="lianxi clearfix">
    	 <h1>若仍有疑問，請聯繫7×24小時客服服務中心</h1>
        <div class="dianhua">
        	<img src="${resDomain}/www/res/tvpad4zt_mac/images/dianhua.png"/><p>24小時電話客服:<br/><span>(00852)21349910<br/>(00852)21349920<br/>(00852)21349901</span></p>
        </div>
        <div class="qq">
        	<img src="${resDomain}/www/res/tvpad4zt_mac/images/QQ.png" /><p>客服QQ:<br/><span>2389075307<br/>2276814617</span></p>
        </div>
        <div class="youjian">
        	<img src="${resDomain}/www/res/tvpad4zt_mac/images/youjian.png" class="youjian_img"/><p>Email:<br/><span>service@mtvpad.com</span></p>
        </div>
    </div>
    <div class="TV_weixin"></div>
    <p class="jieshiq">*TVpad官方在法律範圍內擁有本活動的最終解釋權</p>
</div>


<!-- 第三方统计代码 -->
<cus:store param="statcode"/>

</body>
</html>
