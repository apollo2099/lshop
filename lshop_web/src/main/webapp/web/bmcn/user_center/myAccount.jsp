<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/userCenter.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>	
	
		<!-- 用户邮件订阅关联用户数据 -->
		<input type="hidden" name="lvUserSubscribe.id" id="lsId" value="${lvUserSubscribe.id }"/>
		<input type="hidden" name="lvUserSubscribe.uid" id="uid" value="${lvUserSubscribe.uid }"/>
		<input type="hidden" name="lvUserSubscribe.userName" id="userName" value="${lvUserSubscribe.userName }"/>
		<input type="hidden" name="lvUserSubscribe.email" id="myemail" value="${lvUserSubscribe.email }"/>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 我的账户</span></h1> 
				<div class="usercenter_box">
					<ul class="user_center_pp"><img src="${resDomain}/bmcn/res/images/personel_center_icon01.gif" /></ul>	
					<ul class="user_center_tx">
					  <li>
					  		<font class="fontwz" id="lvnicknameId"></font>,欢迎您！您上次登录时间：<span id="lasttimeId"></span>			    
					    	<span style="display:none">
					    	<input type="checkbox"  value="1" onclick="javascript:emailSubScribe(this)" <s:if test="#request.lvUserSubscribe.id!=null">checked="checked"</s:if>/>邮件订阅
					        </span>
					   </li>
					</ul>
					<ul class="user_center_tips">
						<li>我的交易提醒</li>
						<li><a href="/web/userOrder!getOrderlist.action?payStatus=0">待付款<font class="redfont">(${notPlayNum})</font></a>  
						    <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=0">待提醒卖家发货<font class="redfont">(${notFaHuoNum})</font></a>  
						    <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=1">待确认收货<font class="redfont">(${notShouHuoNum})</font></a>  
						    <a href="/web/userOrder!getOrderlist.action?payStatus=1&status=2">待评价<font class="redfont">(${notCommentNum})</font></a></li>
						<li>账户余额：<font class="redfont">${mybalance } V币</font>（您的账户余额低，可以选择 <font class="redfont">
							<a href="/web/recharge!list.action">充值</a></font> ）</li>
					</ul>		
				</div>
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 