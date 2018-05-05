<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/en/res/js/userCenter.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		
		<!-- 用户邮件订阅关联用户数据 -->
		<input type="hidden" name="lvUserSubscribe.id" id="lsId" value="${lvUserSubscribe.id }"/>
		<input type="hidden" name="lvUserSubscribe.uid" id="uid" value="${lvUserSubscribe.uid }"/>
		<input type="hidden" name="lvUserSubscribe.userName" id="userName" value="${lvUserSubscribe.userName }"/>
		<input type="hidden" name="lvUserSubscribe.email" id="myemail" value="${lvUserSubscribe.email }"/>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> My Account</span></h1> 
				<div class="usercenter_box">
					<ul class="user_center_pp"><img src="${resDomain}/en/res/images/personel_center_icon01.gif" /></ul>	
					<ul class="user_center_tx">
					  <li>
					  		<font class="fontwz" id="lvnicknameId"></font>,welcome! Your last visit time：<span id="lasttimeId"></span>			    
					    	<input type="checkbox"  value="1" onclick="javascript:emailSubScribe(this)" <s:if test="#request.lvUserSubscribe.id!=null">checked="checked"</s:if>/>  RSS Feed
					   </li>
					</ul>
					<ul class="user_center_tips">
						<li>My transaction reminder</li>
						<li><a href="/web/userOrder!getOrderlist.action?payStatus=0">Await payment<font class="redfont">(${notPlayNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=0">Remind delivery<font class="redfont">(${notFaHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=1">Await confirm receipt<font class="redfont">(${notShouHuoNum})</font></a>  <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=2">Await reviews<font class="redfont">(${notCommentNum})</font></a></li>
					</ul>		
				</div>
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 