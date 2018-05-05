<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>提交订单成功_banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/bmcn/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/Configure-mall.js"></script>
		<script type="text/javascript">
			var _maq = _maq || [];
			_maq.push(['_setAccount', Mall_Conf.tgAccount]);
			_maq.push(['_setOrder', '${param.oid}']);
			(function() {
			    var ma = document.createElement('script'); ma.type = 'text/javascript'; ma.async = true;
			    ma.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + Mall_Conf.tgDomain;
			    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ma, s);
			})();
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/shopcart_header.jsp" %>
		
		<div class="buy">
  <div class="buy_lc03"></div>
  <div class="message1">	
<ul>
					<li class="pp"><img src="${resDomain}/bmcn/res/images/suc.gif" /> 订单提交成功，请您尽快付款</li>
					<li class="wz">
				    订单号： <a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid}">${param.oid}</a>应付金额：
<font id="orderPrice" style="font-size:20px;" class="redfont fontwz"></font></li>
	</ul>	
				<ul class="btn"><input name="submit" type="button" class="btn04" id="regbtn"  onclick="javascript:window.open('/web/shopCart!toPayOrder.action?oid=${param.oid}');" value="去付款" /></ul>
  </div>
	<!-- 消费提醒 -->
	<div class="add_tips" style="display:none;">
	<ul id="c_order_tip">
		<li class="title">消费提醒：</li>
		<li>下订单并支付成功的用户可以参加以下促销活动 </li>
	</ul>      
	</div>							
</div>
</div>
		<!--End 购物车-->
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	<script type="text/javascript">
	$.post('/web/shopCart!orderSuccess.action', {oid: "${param.oid}"}, function(data){
		if(data.res === "success"){
			$('#orderPrice').text(data.price);
			//订单参加活动
			if(data.activity){
				var html = '';
				for(var i=0;i<data.activity.length;i++){
					if(data.activity[i].givenTypeNum === 1) {
						html += '<li><img src="http://res.bananatv.com/bmcn/res/images/zq.jpg"  /> '+data.activity[i].activityTitle+'</li>';
					} else {
						html += '<li><img src="http://res.bananatv.com/bmcn/res/images/cj.jpg"  /> '+data.activity[i].activityTitle+'</li>';
					}
				}
				if(html){
					$('#c_order_tip').append(html).parent().show();
				}
			}
		} else {
			window.location.href="/";
		}
	});
	</script>
	</body>
</html> 
