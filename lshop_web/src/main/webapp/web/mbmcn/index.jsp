<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="st" uri="/WEB-INF/tld/gv-style.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana 商城</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
</head>

<body>
<header>
   <div class="top">
      <div class="title">
        <h1>banana 商城</h1>
        <span><a href="#">English</a></span>
      </div>
      <div class="shopping">
         <div class="shoplebg">
            <div class="shopicon"><a href="javascript:toCar('${storeDomain}');"></a><span id="shopCartNum" style="display:none;"></span></div>
         </div>
      </div>
   </div>
</header>
<article>
   <section>
   	  <!-- 滚动图片 -->
   	  <ad:ad adkey="AD_TVPAD_INDEX"></ad:ad>
   </section>
   
   <section>
       <cus:navigation style="5"></cus:navigation>
       <div class="clear"></div>
       
   </section>
   
   <!-- 商品信息 广告图及商品列表-->
   <st:shopProduct address="false" style="4"></st:shopProduct>
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>
<script>
(function(){
	//幻灯片宽度
	var $u = $('#tt');
	var n = $u.find('li').length;
	$u.css('width', n+'00%');
	$u.find('li').css('width',(100/n)+'%');
	var tw = (n*30)+'px';
	$('.indexli').css('width', tw);
	$('.indexinner').css('width', tw);
})();
(function(){
	var $container = $('.adimg');
	//bind touch event
	var element, moved, startX, startY, lastX, step;
	element = $container.find('ul')[0]; //element to delegate
	moveX = 0;	//move x coordinate
	startX = 0; //starting x coordinate
	lastX = 0;	//last x coordinate
	
	moveY = 0;	//move y coordinate
	startY = 0; //starting y coordinate
	scrollH = 0;
	scrollL = 0;
	step = 2;	//the px amount to move
	vstep = 20;//vertical px move
	if (element.addEventListener) {
		//touchstart           
		element.addEventListener("touchstart", function(e) {
		    moved = false;
		    moveX = e.touches[0].clientX;
		    startX = e.touches[0].clientX;
		    lastX = e.touches[0].clientX;
		    moveY = e.touches[0].clientY;
		    startY = e.touches[0].clientY;
		    scrollH = $(document).scrollTop();
		    scrollL = $(document).scrollLeft();
		}, false);
		//touchmove
		element.addEventListener("touchmove", function(e) {
			e.preventDefault();
		    if (Math.abs(e.touches[0].clientY - moveY) > vstep){
		    	moveY = e.touches[0].clientY;
		    	scrollH = scrollH - moveY + startY;
		    	scroll(scrollL, scrollH);
		    	startY = e.touches[0].clientY;
		    	scrollH = $(document).scrollTop();
		    }
		    //horizen move
		    if (Math.abs(e.touches[0].clientX - moveX) > step){
		    	moveX = e.touches[0].clientX;
		    }
		    lastX = e.touches[0].clientX;
		}, false);
		//touchmove
		element.addEventListener("touchend", function(e) {
			if(Math.abs(lastX - startX) < step) return false;
			if (lastX > startX){
				$container.trigger('prev');
			}else{
				$container.trigger('next');
			}
		}, false);
	}
})();
//容器移动事件
function showPic($e, ind){
	var wid = $e.width() / $e.find('li').length;
	var mglt = '-'+ind*wid+'px';
	$e.animate({"margin-left":mglt},500, function(){
		$e.css('margin-left', mglt);
	});
	//cur point
	$('#cur').attr('id', '');
	$('.indexinner span:eq('+ind+')').attr('id','cur');
}
$('.adimg').bind('prev',function(e){
	var $e = $(this).find('ul');
	var wid = $e.find('li').width(), ml = $e.css('margin-left');
	var mlt = -parseInt(ml.substring(0, ml.length-2));
	var cur = Math.round(mlt / wid);
	if(cur == 0){
		showPic($e, 0);
	} else {
		showPic($e, cur-1);
	}
}).bind('next', function(e){
	var $e = $(this).find('ul');
	var len = $e.find('li').length, wid = $e.find('li').width(), ml = $e.css('margin-left');
	var mlt = -parseInt(ml.substring(0, ml.length-2));
	var cur = Math.round(mlt / wid);
	if(cur == len-1){
		showPic($e, cur);
	} else {
		showPic($e, cur+1);
	}
});
</script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/html.js"></script>
</body>
</html>

