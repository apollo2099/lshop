<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<!--begin left_Frame-->
		<div class="left_frame">
    	<div class="cm_n_1">
        	<h3>User Center</h3>
			<h4>Account Info</h4>
            <ul>
            	<li <s:if test="#request.flag=='account'">class="choose" </s:if>><a href="/web/userCenterManage!getAccount.action">My Account</a></li>
            	<li <s:if test="#request.flag=='info'">class="choose" </s:if>><a href="/web/userCenterManage!getInfoDetail.action">My Profile</a></li>
                <li <s:if test="#request.flag=='pwd'">class="choose" </s:if>><a href="/web/userCenterManage!toUpdatePassword.action">Change Password</a></li>
				<li <s:if test="#request.flag=='address'">class="choose" </s:if>><a href="/web/userCenterManage!getAddressList.action" rel="address">Billing Address</a></li>
            </ul>
			
			<h4>Transactions</h4>
            <ul>
            	<li <s:if test="#request.flag=='order'">class="choose" </s:if>><a href="/web/userOrder!getOrderlist.action">My Order</a></li>
            </ul>
			
			<h4>Customer service</h4>
            <ul>
            	<li <s:if test="#request.flag=='comment'">class="choose" </s:if>><a href="/web/userCenterManage!getCommentList.action">My Comments</a></li>
            	<li <s:if test="#request.flag=='question'">class="choose" </s:if>><a href="http://chat.53kf.com/webCompany.php?arg=lulucute&style=1" target="_blank">Online Help</a></li>
            </ul>						
        </div>
       	<!--left_ad-->
		<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
    </div> 
 	<!--End left_Frame-->