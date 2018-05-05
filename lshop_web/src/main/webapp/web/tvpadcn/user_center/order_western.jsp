<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

//
if(lshop.getCookie('issuccess')=='-1'){
     $("#msg").html("提示消息:<span class='orangefont fontwz' >添加失敗,請重試!</span>");
     lshop.setCookie('issuccess',0);
     window.setTimeout(function(){
     $("#msg").html("");
     },5000);
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
  
//表单提交  
function onSub(){
	$("#myform").onSubmit();
} 
</script>

</head>
<body>
	
<div class="content_main">
	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 提交西聯信息</span></h1> 
		<div class="usercenter_box">
			<form id="myform" action="/web/userOrder!saveWesternInfo.action" method="post" >
			<input type="hidden" name="lvWesternInfo.oid" value="${lvOrder.oid}"/>
			<ul>
				<li class="wd1">匯款人(payer)：</li>
				<li class="wd2"><input type="text" class="input2" id="remitter" name="lvWesternInfo.remitter" value="${lvOrderAdress.relName}"/>
				</li>
		    </ul>
		    
		  <ul>
			<li class="wd1">匯款金額(amount)：</li>
			<li class="wd2"><input type="text" class="input2" id="remittance" name="lvWesternInfo.remittance" value="${lvOrder.totalPrice }"/><font class="redfont">默認金額單位為：USD </font>
			</li>
		</ul>
		<ul>
			<li class="wd1">匯款國家(country)：</li>
			<li class="wd2"><input type="text" class="input2" id="country" name="lvWesternInfo.contryName"/>
			</li>
		</ul>
		<ul>
			<li class="wd1">匯款城市(city)：</li>
			<li class="wd2">
			  <input type="text" class="input2" name="lvWesternInfo.adress"/>
			</li>
		</ul>
		<ul>
			<li class="wd1">匯款時間(date)：</li>
			<li class="wd2">
			  <input type="text" class="input2" name="lvWesternInfo.transferTime"/><font class="redfont"></font>
			</li>
		</ul>
		<ul>
			<li class="wd1">MTCN：</li>
			<li class="wd2">
			  <input type="text" class="input2" id="mtcn" name="lvWesternInfo.mtcn" onkeyup="lshop.onInteger(this)" />
			</li>
		</ul>
		
		<ul class="btn">
			<li class="wd1">&nbsp;</li>
			<li class="wd2"><input type="image" onclick="onSub();" src="${resDomain}/tvpadcn/res/images/user_center_btn.gif" width="101" height="34" /><input type="image" onclick="javascript:history.go(-1);" src="${resDomain}/tvpadcn/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></li>
		</ul>
		</form>
		
  	    </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
	
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

<script type="text/javascript">

<%--
function checkData(){
      var issuccess=true;
      //收款人
      var remitter=$("#remitter");
      if(remitter.val()=='')
      {
         remitter.parents("ul").addClass("redfont");
         issuccess=false;
         
      }else{
         remitter.parents("ul").removeClass("redfont");
      
      }
      var mtcn=$("#mtcn");
      if(mtcn.val()=='')
      {
         mtcn.parents("ul").addClass("redfont");
         issuccess=false;
         
      }else{
         mtcn.parents("ul").removeClass("redfont");
      
      }
      var patrn=/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
      var remittance=$("#remittance");
      if(!patrn.test(remittance.val()))
      {
         remittance.parents("ul").addClass("redfont");
         issuccess=false;
         
      }else{
         remittance.parents("ul").removeClass("redfont");
      
      }
      var country=$("#country");
      if(country.val()=='')
      {
         country.parents("ul").addClass("redfont");
         issuccess=false;
         
      }else{
         country.parents("ul").removeClass("redfont");
      
      }
      return issuccess;

}
--%>
</script>	
</body>
</html> 