<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>My Cart_TVpad Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/en/res/js/cart.js"></script>
		<style type="text/css">
		<!--
		.STYLE1 {
			color: #222222;
			font-weight: bold;
		}
		-->
		</style>
		
		
		<!-- 购物车google广告统计代码 -->
		<ad:ad adkey="AD_TVPAD_STAT_SHOPCART"></ad:ad>
		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/shopcart_header.jsp" %>
		
		<div class="buy">	
			<div class="buy_lc"></div>
			<div class="gouwuche">
				  <!-- 判断购物车是否为空 -->
				  <c:if test="${not empty objList}">
				  	<c:foreach items="${objList}" var="ob" varStatus="outStatus">
						<table class="c_shopcart" am="${ob[2]}" sf="${ob[0].storeFlag }" width="1000" border="0" cellpadding="0" cellspacing="1" bgcolor="#dbdbdb">
			           		<tr>
			           			<td height="30" colspan="6" bgcolor="#ffffff">
					     			<table width="1000" border="0" cellspacing="0" cellpadding="0">
			              				<tr>
			               					<td height="51" align="left" colspan="6" style="padding-left:20px">&nbsp;Seller：${ob[0].name }</td>
			              				</tr>
			            			</table>		   
			           			 </td>
			           		</tr>
		          			<tr>
						        <td width="95" height="30" bgcolor="#ffffff" style="padding-left:20px"><input  type="checkbox"  value="" name="box${outStatus.count }" onclick="checkBox('${outStatus.count }',this);"/>Select All</td>
					           <td width="514" align="center" bgcolor="#eeeeee"><span class="STYLE1">Item Name</span></td>
						       <td width="83" align="center" bgcolor="#eeeeee"><span class="STYLE1">Price</span></td>
						       <td width="104" align="center" bgcolor="#eeeeee"><span class="STYLE1">Quantity</span></td>
						       <td width="84" align="center" bgcolor="#eeeeee"><span class="STYLE1">Subtotal</span></td>
						       <td width="105" align="center" bgcolor="#eeeeee"><span class="STYLE1">Operation</span></td>
			          		</tr>
			          		<c:foreach items="${ob[1]}" var="obj" varStatus="status"> 
		         			<tr id="_${outStatus.count }cart${status.count }">
		         				<td width="38" height="82" bgcolor="#ffffff" style="padding-left:20px">
		         				<input name="inBox${outStatus.count }" value="${obj[0].code}" type="checkbox" onclick="checkInBox('${outStatus.count }',this);"/>
		         				<input type="hidden" value="${obj[1].code}" class="product"></input>
		         				</td>
		         				<td height="82" align="center" valign="middle" bgcolor="#ffffff">
				  					<table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
		           						<tr>
		            						<td width="120" align="center" valign="middle"><img src="${obj[1].pimage }" width="70px" height="60px" /></td>
		            						<td width="380" align="left">${obj[1].productName }</td>
		          						</tr>
		         					</table>		
								</td>
		         				<td width="100" height="82" align="center" bgcolor="#ffffff"><span class="price1"><strong>USD <span id="_${outStatus.count }oPrice${status.count }">${obj[0].shopPrice }</span></strong></span></td>
		         				<td width="100" height="82" align="center" bgcolor="#ffffff">
				    				 <p class="gmsl">
				    				 	<span class="jian"><a href="javascript:changeNum('del','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/en/res/images/jian.gif" border="0" /></a></span>
					 					<span class="shuliang"><input type="text" class="input0" id="_${outStatus.count }num${status.count }" name="num" value="${obj[0].shopNum }" readonly/></span>
					 					<span class="jian"><a href="javascript:changeNum('add','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="${resDomain}/en/res/images/jia.gif" border="0" /></a></span>
					 				</p>
					 			</td>
		        		 		<td width="100" height="82" align="center" bgcolor="#ffffff"><span class="price1"><strong>USD <span id="_${outStatus.count }price${status.count }">${obj[2] }</span></strong></span></td>
		         				<td width="100" height="82" align="center" bgcolor="#ffffff"><a href="#" onclick="delCart('${obj[0].code}','${outStatus.count }','${status.count }','${obj[0].shopPrice }')" class="sc">Delete</a></td>
		         			</tr>
		         			</c:foreach>
		         			<tr class="order_activity_tip" style="height:0"></tr>
			     			<tr>
			    				<td height="43" colspan="7" align="right" bgcolor="#FFFFFF" style=" padding:0 30px 0 0;">
			    				<p style="display:inline; float:left;color:#558c40; text-decoration:none; padding-left:20px;" ><a href="#" onclick="deleteShopData('${outStatus.count }','/web/shopCart!delManyCart.action');" class="sc">Bulk Delete</a></p>
			    				<p style="display:inline; padding-right:20px;font-size:14px; font-weight:bold">Quantity：<span class="price1  redfont"><span id="allNum${outStatus.count }">${ob[3]}</span></span></p>
           						<p style="display:inline; font-size:14px; font-weight:bold">Total：<span class="price1 redfont">${ob[0].currency } <span id="allAmount${outStatus.count }">${ob[2] }</span></span></p></td>
			     			</tr>
		        			<tr>
		        				<td height="75" colspan="6" align="right" bgcolor="#ffffff" style="padding:0 10px 0 0;"><a href="javascript:showInfo('${ob[0].storeFlag }','${outStatus.count }');"> <img src="${resDomain}/en/res/images/jiesuan.gif" border="0" /></a></td>
		       				</tr>
		      			</table>
		      		</c:foreach>
		      			<!--<div class="buy_btn"><a href="${storeDomain }/index.html"><img src="${resDomain}/en/res/images/btn01.gif" border="0" /></a></div>  --> 
				  </c:if>
				  <c:if test="${empty objList}">
					<div class="search_none"> 
				   		<p class="biaoqing"><span class="search_text"><img src="${resDomain}/en/res/images/biaoqing.gif" />You have not yet added any items to your Shopping Cart, please choose an item first!</span></p>
				  	</div>
				  </c:if>
		    </div>	
		</div>
		<!--End 购物车-->
		
		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
<script type="text/javascript">
function loadActivityTip(orderPay, shopFlag,productCodeList) {
	$.post('web/shopCart!getOrderActivityTip.action', {orderPay: parseFloat(orderPay), shopFlag: shopFlag,productCodeList:productCodeList}, function(data){
		var couponTxt = $.trim(data);
		if(couponTxt) {
			$('.c_shopcart[sf="'+shopFlag+'"]').find('.order_activity_tip').empty().append(couponTxt).show();
		} else {
			$('.c_shopcart[sf="'+shopFlag+'"]').find('.order_activity_tip').hide();
		}
	});
}
$(function(){
	//加载订单活动信息
	$('.c_shopcart').each(function(i, elem){
		var shopFlag = $(elem).attr('sf');
		var orderPay = $(elem).attr('am');
		var productList=$(elem).find(".product");
		var productStr="";
		for(var i=0;i<productList.length;i++){
			if(i==productList.length-1){
				productStr+=productList[i].value;
			}else{
				productStr+=productList[i].value+",";
			}
		}
		loadActivityTip(orderPay, shopFlag,productStr);
	});
	//绑定价格改变事件
	$('.gouwuche').delegate('.c_shopcart', 'payChang', function(e, param){
		var shopFlag = $(this).attr('sf');
		var orderPay = param.orderPay;
		
		var productList=$(this).find(".product");
		var productStr="";
		for(var i=0;i<productList.length;i++){
			if(i==productList.length-1){
				productStr+=productList[i].value;
			}else{
				productStr+=productList[i].value+",";
			}
		}
		
		loadActivityTip(orderPay, shopFlag,productStr);
	});
});
//用户登陆成功后刷新购物车列表
$('#divlogin').bind('userLoginSuccess', function(e){
	window.location.reload();
});
</script>
	</body>
</html> 
