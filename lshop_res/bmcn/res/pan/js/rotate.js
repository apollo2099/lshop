$(function(){
	var _detail;// 活动详情
	$.ajax({url: '/web/lottery!detail.action',
		data: {lotteryCode: LotteryConfig.lotteryCode},
		async: false,
		success: function(detail){
			if(!detail){
				window.location.href='/index.html';//被禁用则跳转到首页
			}
			_detail = detail;
		}
	});
	// 初始化
	if(!LotteryConfig.items){
		LotteryConfig.items = [];
		for(var i=0; i<_detail.items.length; i++) {
			LotteryConfig.items.push(_detail.items[i].itemCode);
		}
	}
	var _step = 360 / LotteryConfig.items.length;// 转盘步长
	var $chance = $('#c_chance'),// 机会数字
		$record = $('#c_lottery_record');//中奖榜
	
	$('#c_point').rotate({angle: LotteryConfig.init});// 转盘转到初始位置
	// 初始化抽奖机会
	$chance.bind('getChance', function(e){
		if(checkLogin()){
			$.post('/web/lottery!getLotteryChance.action', {lotteryCode: LotteryConfig.lotteryCode}, function(data){
				$(e.target).text(data.chanceNum);
			});
		}
	});
	$chance.trigger('getChance');
    // 点击抽奖
    var PointLock=true;
    $('#c_point_wrap').click(function(){
    	//检查登陆
    	if(!checkLogin()) {
    		onshow('tx_b','loginDiv');
    		return false;
    	}
    	//请求抽奖
		if(PointLock){
			PointLock=false;
			$.post('/web/lottery!draw.action', {lotteryCode: LotteryConfig.lotteryCode}, function(data){
				$chance.trigger('getChance');//减抽奖机会
				switch(data.status){
				case 1:
				case 2:
					// 计算旋转的角度
					var idx = $.inArray(data.prizeNo, LotteryConfig.items);
					if(-1 === idx) {
						return false;
					}
					var angle = idx * _step + LotteryConfig.init;
					$('#c_point').rotate({
						angle:angle, 
						duration: 5000, 
						animateTo: angle+1440, 
						callback:function(){
							if(data.status == 1) {
								$('#c_award').text(data.prizeName);
								$('#c_award_area').openTip();
								$record.trigger('history');
							} else {
								$('#c_fail_area').openTip(function(){
									$('#c_wb_publish').is(':visible') && $('#c_fail_share').show();
								});
							}
							PointLock=true;
						}
					});
					break;
				case 3:
					alert('亲,您的机会已经用完了哦!');
					break;
				case 4:
					alert('亲,抽奖还没有开始哦!');
					break;
				case 5:
					alert('亲,抽奖已经结束了哦!');
					break;
				case 6:
					alert('亲,抽奖已经抽完了哦!');
					break;
				default:
					//发生异常
					window.location.reload();
					break;
				}
				return true;
			});
		}
	});
    //查看抽奖记录
    $record.bind('history', function(e){
    	$.post('/web/lottery!record.action', {lotteryCode: LotteryConfig.lotteryCode}, function(data){
    		var html = '';
    		for(var i=0; i<data.length; i++){
    			html += '<li><span class="wd">用户'+data[i].userName+'</span>抽中'+data[i].prizeName+'奖品一份</li>';
    		}
    		$record.empty().append(html);
    	});
    });
    $record.trigger('history');
    //继续抽奖
    $(document).delegate('.c_go_draw', 'click', function(e){
    	$.closeTip();
    	$('#c_point_wrap').trigger('click');
    });
	//登陆后更新抽奖机会
    $('#divlogin').bind('userLoginSuccess', function(e){
    	$chance.trigger('getChance');
    });
    //修改页面
    LotteryConfig.populate && LotteryConfig.populate(_detail);
});
// 引出框
$.fn.openTip = function(callback){
	var obj = this;
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width())/2 ;
	var objTop = (screenHeight - obj.height())/2;
	var srollTop=$(document).scrollTop();
	obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	callback && callback(obj);
	$(window).resize(function(){
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width())/2 ;
		var objTop = (screenHeight - obj.height())/2;
		var srollTop=$(document).scrollTop();
		obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
	$(window).scroll(function(){
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width())/2 ;
		var objTop = (screenHeight - obj.height())/2;
		var srollTop=$(document).scrollTop();
		obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
}
$.closeTip = function(){
	$('.box').hide();
	$(window).unbind("scroll");
	$(window).unbind("resize");
}
//登陆检查
function checkLogin(){
	var users=lshop.getCookieToJSON('user');
	if(users && users.email){
		return true;
	}
	return false;
}