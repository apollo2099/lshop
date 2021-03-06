<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Submit Western Union Info_TVpad Mall</title>
		<link href="${resDomain}/en/res/css/css.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/en/res/js/western.js"></script>
		<script type="text/javascript">
			//提交表单
			function onSub(){
				document.myform.submit();
			}
		</script>
		<style type="text/css">
		.xlhk_main ul li .wd3{width: 160px;float: left;text-align: right;margin-right: 10px;}
		</style>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div  class="xlhk_main">
			<ul>
			<form action="/web/shopCart!saveWesternInfo.action" method="post" id="myform">
				<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid }"/>
				<input type="hidden" name="lvWesternInfo.storeId" value="${lvOrder.storeId }"/>
				<!--<li><span class="wd3"><b class="bt">您已在西聯點匯款，請務必準確填寫以下信息：</b></span></li>  -->
				<li><span class="wd3"><font class="redfont">*</font>Payer：</span><input type="text" class="input2" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/></li>
				<li><span class="wd3"><font class="redfont">*</font>Amount：</span><input type="text" class="input2" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice}"/><font class="redfont">(Default Amount unit is "USD") </font></li>	
				<li><span class="wd3"><font class="redfont">*</font>Country：</span><input type="text" class="input2" name="lvWesternInfo.contryName"/></li>	
				<li><span class="wd3"><font class="redfont">*</font>City：</span><input type="text" class="input2" name="lvWesternInfo.adress"/></li>						
			  	<li><span class="wd3"><font class="redfont">*</font>Date：</span><input type="text" class="input2" name="lvWesternInfo.transferTime"/><font class="redfont"></font></li>
				<li><span class="wd3"><font class="redfont">*</font>MTCN：</span><input type="text" class="input2" name="lvWesternInfo.mtcn"/></li>
				<c:if test="${not empty msg}">
					<li><span class="qrtj_1"><font class="redfont">You have submitted Western Union information， please do not repeat submission!</font></span></li>
				</c:if>
				<li><span class="wd3" style="height:15px;"></span><span class="qrtj_1"><input name="" type="submit"  value="Submit"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
				<li><span class="wd3" style="height:15px;"></span>
				<p>If you have not yet made a remittance at Western Union, please click <a href="/web/en/mall/western.jsp" class="xxsm" target="_blank">Detail>></a> 
		          <br />
		          After the remittance is finished, you can submit your remittance information at<br /> 
		          "User Center-My Order"</p>
				  <div class="cb"></div>
				</li>
			</form>
			
			</ul>
		</div>
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
	
	</body>
</html> 
