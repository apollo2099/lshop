<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<!--begin left_Frame-->
		<div class="left_frame">
    	<div class="cm_n_1">
        	<h3>用戶中心</h3>
			<h4>用戶管理</h4>
            <ul>
            	<li <s:if test="#request.flag=='account'">class="choose" </s:if>><a href="/web/userCenterManage!getAccount.action">我的帳戶</a></li>
            	<li <s:if test="#request.flag=='info'">class="choose" </s:if>><a href="/web/userCenterManage!getInfoDetail.action">我的資料</a></li>
                <li <s:if test="#request.flag=='pwd'">class="choose" </s:if>><a href="/web/userCenterManage!toUpdatePassword.action">密碼管理</a></li>
				<li <s:if test="#request.flag=='address'">class="choose" </s:if>><a href="/web/userCenterManage!getAddressList.action" rel="address">收貨地址</a></li>
            </ul>
			
			<h4>交易管理</h4>
            <ul>
            	<li <s:if test="#request.flag=='order'">class="choose" </s:if>><a href="/web/userOrder!getOrderlist.action">我的訂單</a></li>
            </ul>
			
			<h4>客戶服務</h4>
            <ul>
            	<li <s:if test="#request.flag=='comment'">class="choose" </s:if>><a href="/web/userCenterManage!getCommentList.action">我的評論</a></li>
            	<li <s:if test="#request.flag=='question'">class="choose" </s:if>><a href="http://chat.53kf.com/webCompany.php?arg=lulucute&style=1" target="_blank">在綫諮詢</a></li>
            </ul>						
        </div>
       	<!--left_ad-->
		<ad:ad adkey="AD_LOCATION_LEFT"></ad:ad>
    </div> 
 	<!--End left_Frame-->