<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>免费领TVpad4啦！</title>
<link href="${resDomain}/mtmcn/res/weixin/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resDomain}/mtmcn/res/weixin/js/jquery-1.4.4.min.js"></script>
</head>

<body>
  <article>
    <div class="tv4_q">
     <div class="tv4_mfn">
       <img src="${resDomain}/mtmcn/res/weixin/images/tv4_img1.jpg" width="100%" />
       <a href="http://m.mtvpad.com/tvpad4/index.html?utm_source=weixin&utm_medium=FX&utm_campaign=20150114"><img src="${resDomain}/mtmcn/res/weixin/images/detial_bt.png" width="100%"></a>
     </div>
    </div>
  </article>
  <article>
     <div class="tv4_pm">
        <dt>TA已筹得<span id="obtainAmount" class="tip_h">${obtainAmount}</span>元</dt>
        <dd>排名第<span id="rank" class="tip_h">${rank}</span>位</dd>
     </div>
     <div id="tv4_bt" class="tv4_bt lq">
		<a href="javascript:;" id="obtain" onclick="doObtain();">帮TA开奖</a>
        <a href="http://mp.weixin.qq.com/s?__biz=MzAwMTEzMjMzMw==&mid=202346859&idx=1&sn=23d8b6df7a43bc486a9becad8973392f#rd">领取我的页面</a>
     </div>     
     <div class="tv4_a">
        <a href="/web/obtainAction!rank.action?lastNum=103">&gt;&gt;查看排行榜</a>
        <a href="/web/mtmcn/weixin/obtain/detail.jsp">&gt;&gt;查看奖品 & 规则</a>
     </div>
     <div id="tip_tkboxy" class="tip_tkboxy" style="display:none;">
        <div class="tip_tk"></div>
         <p class="tip_txt">
          	 帮TA成功领取<span class="red">100</span>元
         </p>
     </div>  
  </article>
<script>
var openid = '${openid}';
var friendOpenid = '${friendOpenid}';
var param = 'openid='+openid+ '&friendOpenid='+friendOpenid;

function doObtain(){
	var $tv4_bt = $('#tv4_bt');
	if (!$tv4_bt.hasClass('lq')) {
		$.post('/web/obtainAction!friendObtain.action', param, function(data){
			eval("var dataObj = " +data);
			if (dataObj.res = 'ok') {
				$('#obtainAmount').html(dataObj.obtainAmount);
				$('#rank').html(dataObj.rank);
				$tv4_bt.addClass('lq');
				$('#tip_tkboxy').fadeIn('slow', function(){
					setTimeout('hideTkboxy()', 1000);
				});
			}
		});		
	}
}

function refresh(){
	$.post('/web/obtainAction!friendRefresh.action', param, function(data){
		eval("var dataObj = " +data);
		$('#obtainAmount').html(dataObj.obtainAmount);
		$('#rank').html(dataObj.rank);
		var $tv4_bt = $('#tv4_bt');
		if (dataObj.res == 'ok') {
			if ($tv4_bt.hasClass('lq')) {
				$tv4_bt.removeClass('lq');					
			}
		} else {
			if (!$tv4_bt.hasClass('lq')) {
				$tv4_bt.addClass('lq');					
			}			
		}
	});		
}
refresh();

function hideTkboxy(){
	$('#tip_tkboxy').fadeOut(1000);
}

//微信分享
_wxShare();
function _wxShare(imgUrl, imgWidth, imgHeight, title, desc, link, appid) {
	//初始化参数
	imgUrl = imgUrl || "${resDomain}/mtmcn/res/weixin/images/tv4_img1.jpg";
	imgWidth = imgWidth || 200;
	imgHeight = imgHeight || 200;
	title = title || document.title;
	desc = desc || document.title;
	link = "${storeDomain}/web/obtainAction!index.action?openid="+openid+"&isappinstalled=100";
	appid = appid || '';
	//微信内置方法
	function _shareFriend() {
		WeixinJSBridge.invoke('sendAppMessage', {
			'appid' : appid,
			'img_url' : imgUrl,
			'img_width' : imgWidth,
			'img_height' : imgHeight,
			'link' : link,
			'desc' : desc,
			'title' : document.title
		}, function(res) {
			//_report('send_msg', res.err_msg);
		});
	}
	function _shareTimeline() {
		WeixinJSBridge.invoke('shareTimeline', {
			'img_url' : imgUrl,
			'img_width' : imgWidth,
			'img_height' : imgHeight,
			'link' : link,
			'desc' : desc,
			'title' : document.title
		}, function(res) {
			//_report('timeline', res.err_msg);
		});
	}

	// 当微信内置浏览器初始化后会触发WeixinJSBridgeReady事件。
	document.addEventListener('WeixinJSBridgeReady',
			function onBridgeReady() {
			// 发送给好友
			WeixinJSBridge.on('menu:share:appmessage', function(argv) {
				_shareFriend();
			});
			// 分享到朋友圈
			WeixinJSBridge.on('menu:share:timeline', function(argv) {
				_shareTimeline();
			});
		}, false);
}
</script>  
  
</body>
</html>
