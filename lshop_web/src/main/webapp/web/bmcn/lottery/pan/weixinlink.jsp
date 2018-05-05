<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信回调 Demo</title>
<script type="text/javascript" src="http://res.bananatv.com/bmcn/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="http://res.bananatv.com/bmcn/res/js/WeixinApi.js?a=1"></script>
</head>
<body>
	<p>分享给朋友,朋友圈或者腾讯微博可以回调活动!</p>
<script type="text/javascript">
//需要分享的内容，请放到ready里
WeixinApi.ready(function(Api) {
	//query param
	var queries = {};
		$.each(document.location.search.substr(1).split('&'),function(c,q){
		var i = q.split('=');
		queries[i[0].toString()] = i[1].toString();
	});
	// 微信分享的数据
	var wxData = {
		"appId" : "", // 服务号可以填写appId
		"link" : window.location.href,
		"title" : document.getElementsByTagName('title')[0].innerHTML
	};
	// 分享的回调
	var wxCallbacks = {
		// 分享操作开始之前
		ready : function() {
			// 你可以在这里对分享的数据进行重组
			$.get('http://www.bananatv.com/web/activity!shareValicode.action');
		},
		// 分享被用户自动取消
		cancel : function(resp) {
			// 你可以在你的页面上给用户一个小Tip，为什么要取消呢？
		},
		// 分享失败了
		fail : function(resp) {
			// 分享失败了，是不是可以告诉用户：不要紧，可能是网络问题，一会儿再试试？
		},
		// 分享成功
		confirm : function(resp) {
			//回调赠送抽奖机会
			$.post('http://www.bananatv.com/web/activity!shareCallback.action', {userCode: queries.u, activityCode: queries.a}, function(data){
				//赠送结果
			});
		},
		// 整个分享过程结束
		all : function(resp) {
			// 如果你做的是一个鼓励用户进行分享的产品，在这里是不是可以给用户一些反馈了？
		}
	};

	// 用户点开右上角popup菜单后，点击分享给好友，会执行下面这个代码
	Api.shareToFriend(wxData, wxCallbacks);
	// 点击分享到朋友圈，会执行下面这个代码
	Api.shareToTimeline(wxData, wxCallbacks);
	// 点击分享到腾讯微博，会执行下面这个代码
	Api.shareToWeibo(wxData, wxCallbacks);
});
</script>
</body>
</html>