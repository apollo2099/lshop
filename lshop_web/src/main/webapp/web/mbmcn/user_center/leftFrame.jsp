<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<!--begin left_Frame-->
		<div class="left_frame">
    	<div class="cm_n_1">
        	<h3>用户中心</h3>
			<h4>用户管理</h4>
            <ul>
            	<li <s:if test="#request.flag=='account'">class="choose" </s:if>><a href="/web/userCenterManage!getAccount.action">我的账户</a></li>
            	<li <s:if test="#request.flag=='info'">class="choose" </s:if>><a href="/web/userCenterManage!getInfoDetail.action">我的资料</a></li>
                <li <s:if test="#request.flag=='pwd'">class="choose" </s:if>><a href="/web/userCenterManage!toUpdatePassword.action">密码管理</a></li>
				<li <s:if test="#request.flag=='address'">class="choose" </s:if>><a href="/web/userCenterManage!getAddressList.action" rel="address">收货地址</a></li>
            </ul>
			
			<h4>交易管理</h4>
            <ul>
            	<li <s:if test="#request.flag=='order'">class="choose" </s:if>><a href="/web/userOrder!getOrderlist.action">我的订单</a></li>
            	<li <s:if test="#request.flag=='cart'">class="choose" </s:if>><a href="/web/mall!getShopCartList.action">我的购物车</a></li>
            	<li <s:if test="#request.flag=='comment'">class="choose" </s:if>><a href="/web/userCenterManage!getCommentList.action">我的评论</a></li>
            </ul>
            					
        </div>
       	<!--left_ad-->
		<ad:ad adkey="AD_TVPAD_LEFT"></ad:ad>
    </div> 
 	<!--End left_Frame-->