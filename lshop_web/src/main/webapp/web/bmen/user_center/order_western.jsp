<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/western.js"></script>
		<script type="text/javascript">
			if(lshop.getCookie('issuccess')=='-1'){
				$("#msg").html("Notice: <span class='orangefont fontwz' >Adding failed, please try it again!</span>");
			     lshop.setCookie('issuccess',0);
			     window.setTimeout(function(){
			     $("#msg").html("");
			     },5000);
			}
		</script>
		<style type="text/css">
		#myform input[type="text"]{color:#666;}
		</style>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Submit Western Union Info</span></h1>  
				<div class="usercenter_box">
					<form id="myform" action="/web/userOrder!saveWesternInfo.action" method="post" >
					<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid}"/>
					<input type="hidden" name="lvWesternInfo.storeId" value="${lvOrder.storeId }"/>
					<ul>
						<li class="wd1">Payer：</li>
						<li class="wd2"><input type="text" class="input4" id="remitter" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1">Amount：</li>
					<li class="wd2"><input type="text" class="input4" id="remittance" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice }"/><font class="redfont">(Default Amount unit is "USD")</font>
					</li>
				</ul>
				<ul>
					<li class="wd1">Country：</li>
					<li class="wd2"><input type="text" class="input4" id="country" name="lvWesternInfo.contryName"/>
					</li>
				</ul>
				<ul>
					<li class="wd1">City：</li>
					<li class="wd2">
					  <input type="text" class="input4" name="lvWesternInfo.adress"/>
					</li>
				</ul>
				<ul>
					<li class="wd1">Date：</li>
					<li class="wd2">
					  <input type="text" class="input4" name="lvWesternInfo.transferTime"/><font class="redfont"></font>
					</li>
				</ul>
				<ul>
					<li class="wd1">MTCN：</li>
					<li class="wd2">
					  <input type="text" class="input4" id="mtcn" name="lvWesternInfo.mtcn" onkeyup="lshop.onInteger(this)" />
					</li>
				</ul>
				
				<ul class="btn">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><input type="submit" value="Save Changes" class="user_center_bt" />&nbsp;&nbsp;<input type="button" onclick="javascript:history.go(-1);" value="Back" class="user_center_bt" /></li>
				</ul>
				</form>
				
		  	    </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	</body>
</html> 