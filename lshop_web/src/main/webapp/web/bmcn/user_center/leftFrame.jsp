<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<!--begin left_Frame-->
		<div class="left_frame" id="left_menu">
    	<div class="cm_n_1">
        	<h3><a id="left_menu_ucenter" href="/web/userCenterManage!getAccount.action" <s:if test="#request.flag=='account'">class="choose" </s:if>>用户中心</a></h3>
        	<h4>订单中心</h4>
            <ul>
            	<li <s:if test="#request.flag=='order'">class="choose" </s:if>><a href="/web/userOrder!getOrderlist.action">我的订单</a></li>
            	<li <s:if test="#request.flag=='cart'">class="choose" </s:if>><a href="/web/mall!getShopCartList.action">我的购物车</a></li>
            	<li <s:if test="#request.flag=='comment'">class="choose" </s:if>><a href="/web/userCenterManage!getCommentList.action">我的评论</a></li>
            </ul>
			<h4>账户中心</h4>
            <ul>
            	<li <s:if test="#request.flag=='info'">class="choose" </s:if>><a href="/web/userCenterManage!getInfoDetail.action">账户信息</a></li>
                <li <s:if test="#request.flag=='pwd'">class="choose" </s:if>><a href="/web/userCenterManage!toUpdatePassword.action">账户安全</a></li>
				<li id="left_menu_zh_3" <s:if test="#request.flag=='balance'">class="choose" </s:if>><a href="/web/recharge!list.action">账户余额</a></li>
				<li <s:if test="#request.flag=='coupon'">class="choose" </s:if>><a href="/web/couponManage!getCouponList.action?useStatus=1">我的优惠券</a></li>
				<li <s:if test="#request.flag=='prize'">class="choose" </s:if>><a href="/web/prize!prizeList.action">我的奖品</a></li>
				<li id="left_menu_zh_4" <s:if test="#request.flag=='buylog'">class="choose" </s:if>><a href="/web/recharge!tradeList.action?recordType=1">交易记录</a></li>
           		<li <s:if test="#request.flag=='address'">class="choose" </s:if>><a href="/web/userCenterManage!getAddressList.action" rel="address">收货地址</a></li>
            </ul>
            <!-- 
            <h4>设备中心</h4>
            <ul>
            	<li <s:if test="#request.flag=='device'">class="choose" </s:if>><a href="/web/device!myDevice.action">我的设备</a></li>
			</ul>
			-->
        </div>
       	<!--left_ad-->
		<ad:ad adkey="AD_TVPAD_LEFT"></ad:ad>
    </div> 
 	<!--End left_Frame-->
 	
<script type="text/javascript">
$(function(){
	var leftmenu = $("#left_menu");
	
	leftmenu.find("li").each(function(){
		<%-- 每次点击菜单时，将菜单的id保存到cookie --%>
		var li = $(this);
		li.click(function(){
			var menuid = li.attr("id");
			lshop.setCookie("leftMenuId", menuid);
		});
	});
	
	<%--
	如果所有的菜单中都没有选中样式，则取上次点击时的菜单将其设置为选中样式。
	此处对原有的选中方式没有影响，只是针对某个菜单内有多处链接操作而省去在后台频繁记录flag值。
	 --%>
	if (leftmenu.find("li.choose").length == 0) {
		if (leftmenu.find("#left_menu_ucenter").attr("class") == "choose") {
			return;
		}
		var menuid = lshop.getCookie("leftMenuId");
		if (menuid != undefined && menuid != "") {
			var menu = leftmenu.find("#"+menuid);
			menu.attr("class", "choose");
		}
	}
});
</script>