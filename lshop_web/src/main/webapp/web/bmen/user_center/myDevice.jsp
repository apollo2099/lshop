<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/ymPrompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/mydevice.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>	
	
		<!-- 用户邮件订阅关联用户数据 -->
		<input type="hidden" name="lvUserSubscribe.id" id="lsId" value="${lvUserSubscribe.id }"/>
		<input type="hidden" name="lvUserSubscribe.uid" id="uid" value="${lvUserSubscribe.uid }"/>
		<input type="hidden" name="lvUserSubscribe.userName" id="userName" value="${lvUserSubscribe.userName }"/>
		<input type="hidden" name="lvUserSubscribe.email" id="myemail" value="${lvUserSubscribe.email }"/>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img
								src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a
							href="/index.html">Home</a> --><a
							href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> My bananaTV</span></h1> 
				<div class="usercenter_box">
				
				
					<div class="order_record">
					    <p class="js">
						  <span><font class="fontwz">Device Name：</font> <input name="keyname" id="keyname" type="text" class="input3" value="${dname }"/></span>
					      <span><font class="fontwz">Device ID：</font> <input name="keycode" id="keycode" type="text" class="input3" value="${dcode }"/></span>
						  <span><img src="${resDomain}/bmen/res/images/jiansuo.jpg" border="0" onclick="javascript:qurryDevice();" style="cursor:pointer;"/></span>
						</p>
						<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e3e2e2">
			              <tr>
			               <td width="19%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Device Name</td>
			               <td width="22%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Device ID</td>
			               <td width="26%" height="30" align="center" valign="middle" bgcolor="#f2f1f1">Type</td>
			               <td height="30" colspan="2" align="center" valign="middle" bgcolor="#f2f1f1">Operation</td>
			              </tr>
			              
			              <c:foreach items="${pagination.list }" var="device">
			              	 <tr>
				               <td height="30" align="center" valign="middle" bgcolor="#FFFFFF">${device.devicename }</td>
				               <td height="30" align="center" valign="middle" bgcolor="#FFFFFF">${device.syscode }</td>
				               <td height="30" align="center" valign="middle" bgcolor="#FFFFFF">
				               		<c:if test="${device.bizline eq '1' }">TVpad</c:if>
				               		<c:if test="${device.bizline eq '2' }">banana</c:if>
				               		<c:if test="${device.bizline eq '3' }">TVpad mini</c:if>
				               		<c:if test="${device.bizline eq '4' }">banana mini</c:if>
				               		<c:if test="${device.bizline eq '5' }">私有云</c:if>
				               		<c:if test="${device.bizline eq '6' }">可视对讲</c:if>
				               </td>
				               <td width="21%" height="30" align="center" valign="middle" bgcolor="#FFFFFF">
				               		<a href="javascript:checkWarranty('${device.code }');">Warranty</a>
				               </td>
				               <td width="12%" align="center" valign="middle" bgcolor="#FFFFFF">
				               		<a href="javascript:removeBinding('${device.code }');">Unbind</a>
				               </td>
				              </tr>
			              </c:foreach> 
					    </table>
					    
					    <c:if test="${pagination.totalPage>1}">
							  <u:newPage href="" language="en"></u:newPage>
							  <script type="text/javascript">
								function toPage(){
									var pageNum = $("#pageValue").val();
									var url = "/web/device!myDevice.action?name=${dname}&code=${dcode}&page.pageNum="+pageNum;
								   	window.location.href=url;
								
								}
							 </script>
						 </c:if>
					  
					  </div>
		
				</div>
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>

	</body>
</html> 