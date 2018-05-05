<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/www/res/js/userCenter.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>	
	
		<!-- 用户邮件订阅关联用户数据 -->
		<input type="hidden" name="lvUserSubscribe.id" id="lsId" value="${lvUserSubscribe.id }"/>
		<input type="hidden" name="lvUserSubscribe.uid" id="uid" value="${lvUserSubscribe.uid }"/>
		<input type="hidden" name="lvUserSubscribe.userName" id="userName" value="${lvUserSubscribe.userName }"/>
		<input type="hidden" name="lvUserSubscribe.email" id="myemail" value="${lvUserSubscribe.email }"/>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>我的帳戶</a></font> </span></h1> 
				<div class="usercenter_box">
					<ul class="user_center_pp"><img src="${resDomain}/www/res/images/personel_center_icon01.gif" /></ul>	
					<ul class="user_center_tx">
					  <li>
					  		<font class="fontwz" id="lvnicknameId"></font>,歡迎您！您上次登錄時間：<span id="lasttimeId"></span>			    
					    	<input type="checkbox"  value="1" onclick="javascript:emailSubScribe(this)" <s:if test="#request.lvUserSubscribe.id!=null">checked="checked"</s:if>/>郵件訂閱
					   </li>
					</ul>
					<ul class="user_center_tips">
						<li>我的交易提醒</li>
						<li><a href="/web/userOrder!getOrderlist.action?payStatus=0">待付款<font class="redfont">(${notPlayNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=0">待提醒賣家發貨<font class="redfont">(${notFaHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=1">待確認收貨<font class="redfont">(${notShouHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=2">待評價<font class="redfont">(${notCommentNum})</font></a></li>
					</ul>		
				</div>
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 