<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_购物车</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/cart.js"></script>

<!-- 购物车google广告统计代码 -->
<ad:ad adkey="AD_TVPAD_STAT_SHOPCART"></ad:ad>

</head>

<body>
<header>
   <div class="top">
      <div class="title1">
        <h1>购物车</h1>
      </div>
   </div>
</header>

<!-- 判断是否登陆 -->
<article id="loginCartId" style="display:none;">
  <div class="sholoign">
    <div class="boxhos">
     <a href="javascript:toLo('${storeDomain }');">登  录</a>
     <span>您可以在登录后同步电脑与手机购物车中的商品</span>
      <div class="clear"></div>
    </div>
  </div>
</article>

<!-- 判断购物车是否为空 -->
<c:if test="${not empty objList}">
<article class="gouwuche">
   <c:foreach items="${objList}" var="ob" varStatus="outStatus">
   <div class="mycart c_shopcart" am="${ob[2]}" sf="${ob[0].storeFlag }">
       <div class="settleaccounts">
           <div class="settleaccounts_inner">
              <div class="settleaccounts_inner_left">
                 <h2>${ob[0].name }</h2> 
                 <input type="checkbox" value="" name="box${outStatus.count }" onclick="checkBox('${outStatus.count }',this);">
                 <span>商品总金额：</span><i>USD <font id="allAmount${outStatus.count }">${ob[2] }</font></i>
              </div>
              <div class="settleaccounts_inner_right">
                <a href="javascript:showInfo('${ob[0].storeFlag }','${outStatus.count }');">去结算</a>
              </div>
              <div class="clear"></div>
           </div>
       </div>
       
       <div class="cartlist">
       <c:foreach items="${ob[1]}" var="obj" varStatus="status"> 
          <ul>
              <li>
                 <div class="imgchek">
                   <table width="100%" border="0">
                        <tr>
                            <td width="21%">
                            <input name="inBox${outStatus.count }" value="${obj[0].code}" type="checkbox" onclick="checkInBox('${outStatus.count }',this);">
                            <input type="hidden" value="${obj[1].code}" class="product"></input>
                            </td>
                            <td width="79%" align="center" valign="middle">
                              <a href="http://${ob[0].domainName }/products/${obj[1].code}.html">
                                <img width="90%" src="${obj[1].pimage }">
                              </a>
                            </td>
                        </tr>
                   </table>

                 </div>
                 <div class="totalconts">
                    <a href="http://${ob[0].domainName }/products/${obj[1].code}.html">
                     <div class="imgname"><p>${obj[1].productName }</p></div>
                   </a>
                   
                    <div>
                       <table width="100%" border="0">
                         <tr>
                           <td width="48%" valign="middle" class="imgprcont"><span class="imgjiag">USD <font id="_${outStatus.count }oPrice${status.count }">${obj[0].shopPrice }</font></span> X</td>
                           <td width="52%" class="spanblock">
                            <span class="cut" onclick="changeNum('del','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"></span>
                            <span class="zhi" id="_${outStatus.count }num${status.count }">${obj[0].shopNum }</span>
                            <span class="add" onclick="changeNum('add','${outStatus.count }','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"></span>
                            <span id="_${outStatus.count }price${status.count }" style="display:none">${obj[2] }</span>
                           </td>
                         </tr>
                         
                      </table>

                    </div>
                    <div class="imgjiag"></div>
                 </div>  
                 <div class="clear"></div> 
              </li>         
           </ul>
          </c:foreach>
          <div class="addcoupons order_activity_tip" style="display:none;"></div>
        </div>
       <div class="delete">
          <a href="javascript:deleteShopData('${outStatus.count }','/web/shopCart!delManyCart.action');">删除选中项</a>
       </div>
   </div><!-- end mycart -->
   </c:foreach>
</article>
</c:if>

<c:if test="${empty objList}">
<article>
  <div class="shnullbox">
    <div class="shopimg">
       
    </div>
    <div class="tisopn">
      您的购物车还是空的，快去选购吧！
    </div>
  </div>
  
  <div class="goshop">
    <a href="/">去逛逛</a>
  </div> 
   
</article>
</c:if>
<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>
<script type="text/javascript">
function loadActivityTip(orderPay, shopFlag,productCodeList) {
	$.post('/web/shopCart!getOrderActivityTip.action', {orderPay: parseFloat(orderPay), shopFlag: shopFlag,productCodeList:productCodeList}, function(data){
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
		
		loadActivityTip(orderPay,shopFlag,productStr);
	});
});
</script>
</body>
</html>
