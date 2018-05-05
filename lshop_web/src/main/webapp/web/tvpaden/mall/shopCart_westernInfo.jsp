<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Submit Western Union Information-HUA YANG MALL</title>
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
//提交表单
function onSub(){
	document.myform.submit();
}

//表单验证
$().ready(function() {
	$("#myform").validate({
		rules: {
		    'lvWesternInfo.remitter':{required: true,maxlength:32},
			'lvWesternInfo.remittance':{required: true,number:true,maxlength:11},
			'lvWesternInfo.contryName':{required: true,maxlength:32},
			'lvWesternInfo.adress':{required: true,maxlength:48},
			'lvWesternInfo.transferTime':{required: true,date:true,maxlength:48},
			'lvWesternInfo.mtcn':{required: true,digits:true,maxlength:10}
		},
		messages: {
			'lvWesternInfo.remitter': {
			 	required: "<font color='red'>Please enter the Payer!</font>",
				maxlength: "<font color='red'>Payer shall not be longer than 32 characters!</font>"
			 },
			'lvWesternInfo.remittance': {
			 	required: "<font color='red'>Please enter the Amount!</font>",
			 	number:"<font color='red'>Invalid Amount format, please enter figures only!</font>",
			 	maxlength:"<font color='red'>Amount shall not be longer than 11 characters!</font>"
			 },
			'lvWesternInfo.contryName': {
			 	required: "<font color='red'>Please enter the Country!</font>",
			 	maxlength:"<font color='red'>Country shall not be longer than 32 characters!</font>"
			 },
			 'lvWesternInfo.adress': {
			 	required: "<font color='red'>Please enter the City!</font>",
			 	maxlength:"<font color='red'>City shall not be longer than 48 characters</font>"
			 },
			 'lvWesternInfo.transferTime': {
			 	required: "<font color='red'>Please enter the Date!</font>",
			 	date: "<font color='red'>Incorrect Date format, correct format: 2012/01/01</font>",
			 	maxlength:"<font color='red'>Date shall not be longer than 48 characters!/font>"
			 },
			'lvWesternInfo.mtcn': {
			 	required: "<font color='red'>Please enter the MTCN!</font>",
			 	digits:"<font color='red'>MTCN format incorect, please enter round figures only!</font>",
			 	maxlength:"<font color='red'>MTCN shall not be longer than 10 characters!</font>"
			 }
		}
	});
});
</script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
<div  class="xlhk_main">
	<ul>
	<form action="/web/shopCart!saveWesternInfo.action" method="post" id="myform">
		<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid }"/>
		<li><span class="wd5"><b class="bt">Here’s Western Union, please fill in the following information correctly：</b></span></li>
		<li><span class="wd3"><font class="redfont">*</font>PAYER：</span><input type="text" class="input3" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/></li>
		<li><span class="wd3"><font class="redfont">*</font>AMOUNT：</span><input type="text" class="input3" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice}"/><font class="redfont">(Default Amount unit is "USD ")</font></li>	
		<li><span class="wd3"><font class="redfont">*</font>Country：</span><input type="text" class="input3" name="lvWesternInfo.contryName"/></li>	
		<li><span class="wd3"><font class="redfont">*</font>CITY：</span><input type="text" class="input3" name="lvWesternInfo.adress"/></li>						
	  	<li><span class="wd3"><font class="redfont">*</font>DATE：</span><input type="text" class="input3" name="lvWesternInfo.transferTime"/><font class="redfont"></font></li>
		<li><span class="wd3"><font class="redfont">*</font>MTCN：</span><input type="text" class="input3" name="lvWesternInfo.mtcn"/></li>
		<c:if test="${not empty msg}">
			<li><span class="qrtj_1"><font class="redfont">You have submitted Western Union information， please do not repeat submission!</font></span></li>
		</c:if>
		<li><span class="qrtj_1"><input name="" type="submit"  value="Submit"  class="qrtj" style="CURSOR: pointer; "/></span></li>
	</form>
		<p class="xlhk_sm">If you have not yet made a remittance at Western Union, please click<a href="/web/tvpaden/mall/western.jsp" class="xxsm" target="_blank">Detail>></a> 
          <br />
         After the remittance is finished, you can submit your remittance information at<br /> 
          "User Center-My Order"</p>
		  <div class="cb"></div>
	
	</ul>
</div>
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
