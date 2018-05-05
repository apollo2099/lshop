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
<title>提交西聯匯款信息_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<!-- top -->
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
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
			 	required: "<font color='red'>请输入匯款人</font>",
				maxlength: "<font color='red'>匯款人不能大于32位字符</font>"
			 },
			'lvWesternInfo.remittance': {
			 	required: "<font color='red'>请输入匯款金額，</font>",
			 	number:"<font color='red'>匯款金額格式不正確，只能為數字，</font>",
			 	maxlength:"<font color='red'>匯款金額不能大于11位字符，</font>"
			 },
			'lvWesternInfo.contryName': {
			 	required: "<font color='red'>请输入匯款國家</font>",
			 	maxlength:"<font color='red'>匯款國家不能大于32位字符</font>"
			 },
			 'lvWesternInfo.adress': {
			 	required: "<font color='red'>請輸入匯款城市</font>",
			 	maxlength:"<font color='red'>匯款城市不能大于48位字符</font>"
			 },
			 'lvWesternInfo.transferTime': {
			 	required: "<font color='red'>请输入匯款時間</font>",
			 	date: "<font color='red'>匯款時間格式不正確，正確格式請參照：2012/01/01</font>",
			 	maxlength:"<font color='red'>匯款時間不能大于48位字符</font>"
			 },
			'lvWesternInfo.mtcn': {
			 	required: "<font color='red'>請輸入MTCN</font>",
			 	digits:"<font color='red'>MTCN格式不正確，只能為整數</font>",
			 	maxlength:"<font color='red'>MTCN不能大于10位字符</font>"
			 }
		}
	});
});
</script>
</head>
<body>

<div  class="xlhk_main">
	<ul>
	<form action="/web/shopCart!saveWesternInfo.action" method="post" id="myform">
		<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid }"/>
		<li><span class="wd3"><b class="bt">您已在西聯點匯款，請務必準確填寫以下信息：</b></span></li>
		<li><span class="wd3"><font class="redfont">*</font>匯款人（PAYER）：</span><input type="text" class="input3" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/></li>
		<li><span class="wd3"><font class="redfont">*</font>匯款金額（AMOUNT）：</span><input type="text" class="input3" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice}"/><font class="redfont">默認金額單位為：USD </font></li>	
		<li><span class="wd3"><font class="redfont">*</font>匯款國家（Country）：</span><input type="text" class="input3" name="lvWesternInfo.contryName"/></li>	
		<li><span class="wd3"><font class="redfont">*</font>匯款城市（CITY）：</span><input type="text" class="input3" name="lvWesternInfo.adress"/></li>						
	  	<li><span class="wd3"><font class="redfont">*</font>匯款時間（DATE）：</span><input type="text" class="input3" name="lvWesternInfo.transferTime"/><font class="redfont"></font></li>
		<li><span class="wd3"><font class="redfont">*</font>MTCN：</span><input type="text" class="input3" name="lvWesternInfo.mtcn"/></li>
		<c:if test="${not empty msg}">
			<li><span class="qrtj_1"><font class="redfont">您已提交西聯匯款信息，不能重複提交！</font></span></li>
		</c:if>
		<li><span class="qrtj_1"><input name="" type="submit"  value="提 交"  class="qrtj" style="CURSOR: pointer; "/></span></li>
	</form>
		<p class="xlhk_sm">如果您還未在西聯匯款，請點擊 <a href="/web/tvpadcn/mall/western.jsp" class="xxsm" target="_blank">詳細說明>></a> 
          <br />
          匯款以後可前往“用戶中心—我的訂單”提交西聯匯款信息。</p>
		  <div class="cb"></div>
	
	</ul>
</div>
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
