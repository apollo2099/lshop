<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
var isIE = false;
var isFF = false;
var isSa = false;

if ((navigator.userAgent.indexOf("MSIE")>0) && (parseInt(navigator.appVersion) >=4))isIE = true;
if(navigator.userAgent.indexOf("Firefox")>0)isFF = true;
if(navigator.userAgent.indexOf("Safari")>0)isSa = true; 

function onlyNumber(e)
{
    var key;
    iKeyCode = window.event?e.keyCode:e.which;
    if( !(((iKeyCode >= 48) && (iKeyCode <= 57)) || (iKeyCode == 13) || (iKeyCode == 46) || (iKeyCode == 45) || (iKeyCode == 37) || (iKeyCode == 39) || (iKeyCode == 8)))
    {    
        if (isIE)
        {
            e.returnValue=false;
        }
        else
        {
            e.preventDefault();
        }
    }
} 
//-->
function isChineseChar(str){   
   var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
   return reg.test(str);
}
</script>
      
<script type="text/javascript">

var editInfoFlag=true;
function checkEditNick(){
	var nickname=$('#editNickName');
	var nInfo=$('#editNickInfo');
	 if($.trim(nickname.val())==''){
		 nInfo.html("");
	 }else{
		 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
		 if(pat.test($.trim(nickname.val()))==true) 
		 { 
			 nInfo.html("");
		 }
			 $.ajax({   
				 url: '/web/userCenterManage!isExistsUser.action',
				 data:"lvAccount.nickname="+$.trim(nickname.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					   editInfoFlag=false;
					  nInfo.html("<font color='red'>This nickname is already existed!</font>");
				   }else if(num==0){
					    editInfoFlag=true;
					    nInfo.html("");
					
					   }
				 }   
		    });
	 }
}
//提交表单信息
function onSub(){
	$("#myform").submit();
}

//表单验证
$().ready(function() {
	$("#myform").validate({
		rules: {
		    'lvAccountInfo.qq':{qq:true,maxlength:16},
		    'lvAccountInfo.msn':{msn:true,maxlength:32},
			'lvAccountInfo.name':{required: true,maxlength:32},
			'lvAccountInfo.tel':{isChineseChar:true,maxlength:16},
		    'lvAccountInfo.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountInfo.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			 'lvAccountInfo.qq': {
				 qq: "<font color='red'>QQ number format incorrect!</font>",
				maxlength: "<font color='red'>QQ number shall not be longer than 16 characters!</font>"
			 },
			 'lvAccountInfo.msn': {
				 msn: "<font color='red'>MSN format incorrect!</font>",
				maxlength: "<font color='red'>MSN shall not be longer than 32 characters!</font>"
			 },
			'lvAccountInfo.name': {
				required: "<font color='red'>Please enter your real name!</font>",
				maxlength: "<font color='red'>Real Name shall not be longer than 32 characters!</font>"
			 },
			 'lvAccountInfo.tel': {
				 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
				 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
			 },
			 'lvAccountInfo.mobile': {
				isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
				maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
			 },
			'lvAccountInfo.postCode': {
				required: "<font color='red'>Please enter the Zip Code!</font>",
				isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
				maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
			 }
		},
		submitHandler:function(form){
			var tel=$("#tel");
			var mobile=$("#mobile");
			var adress=$("#adress");
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  $("#telInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  mobile.focus();
			  return ;
		   }else if($.trim(adress.val())==''||$.trim(adress.val())=='Street'){
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be null!</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='County/City'){
		      $("#addressInfo").html("<font color='red'>County/City shall not be null!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='State'){
		      $("#addressInfo").html("<font color='red'>State/Province shall not be null!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>Coutry shall not be null!</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>Detailed street address shall not be longer than 128 characters!</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>County/City shall not be longer than 32 characters!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>State/Province shall not be longer than 32 characters!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }
		    form.submit();
		}
	});
});





</script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> My Profile</span></h1> 
		<form action="/web/userCenterManage!editInfo.action" method="post" id="myform">
			<input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}"/>
			<input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}"/>
			<input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}"/>
			<input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}"/>
			<input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}"/>
			<input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}"/>
			<input type="hidden" name="ordnickname" id="ordnickname" value="${lvAccount.nickname}"/>
			<div class="usercenter_box">
			<font color="red" ><c:if test="${not empty msg}">Successfully changed!</c:if></font>
		    <% session.removeAttribute("msg"); %>
				<ul>
					<li class="wd1">Account ID：</li>
					<li class="wd2">${lvAccount.email }</li>
			    </ul>
			   <ul>
					<li class="wd1"><font class="redfont">*</font>Nickname：</li>
					<!--<li class="wd2"><input  type="text"  name="lvAccount.nickname" id="editNickName" onblur="checkEditNick()" value="${lvAccount.nickname}" class="input2" /> 
					<span id="editNickInfo"></span>
					</li>  --><li class="wd2">${lvAccount.nickname}</li>
			  </ul>	
			  <ul>
					<li class="wd1"><font class="redfont">*</font>Gender：</li>
					<li class="wd2">
					  <input type="radio" name="lvAccountInfo.sex" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked" </s:if>/>Male  
					  <input name="lvAccountInfo.sex" type="radio" <s:if test="lvAccountInfo.sex==1">checked="checked" </s:if> value="1" /> 
					  Female<input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked" </s:if> /> 
					  Secrecy</li>
			  </ul>
			  <ul>
				<li class="wd1">QQ:</li>
				<li class="wd2"><input  type="text"  onkeypress="onlyNumber(event)" name="lvAccountInfo.qq" id="qq"  value="${lvAccountInfo.qq}"  class="input2" />
				</li>
			</ul>
			<ul>
				<li class="wd1">MSN:</li>
				<li class="wd2"><input name="lvAccountInfo.msn" value="${lvAccountInfo.msn}" id="msn"  class="input2"  type="text" /></li>
			</ul>
			<ul>
				<li class="wd1"><font class="redfont">*</font>Real name：</li>
				<li class="wd2"><input name="lvAccountInfo.name" id="realName"  value="${lvAccountInfo.name}" class="input2"  type="text" />
				</li>
			</ul>
			<ul>
				<li class="wd1">Tel.：</li>
				<li class="wd2"><input name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" class="input2"  type="text" />
				<span id="telInfo"></span>
				</li>
			</ul>
			<ul>
				<li class="wd1">Mobile No.：</li>
				<li class="wd2"><input name="lvAccountInfo.mobile" onkeypress="onlyNumber(event)" value="${lvAccountInfo.mobile }" id="mobile"  type="text" class="input2" />
				<span id="mobileInfo"></span>
				</li>
			</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>Address：</li>
			<li class="wd2">
			<input  name="lvAccountInfo.address" id="adress" type="text" class="input2" value="${lvAccountInfo.address }" onfocus="if(this.value=='Street')this.value=''" onblur="if(this.value=='')this.value='Street'"/> 
			-<input  name="lvAccountInfo.cityName" id="cityName" type="text" class="input2" value="${lvAccountInfo.cityName }" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'"/>
			-<input  name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input2" value="${lvAccountInfo.provinceName }" onfocus="if(this.value=='State')this.value=''" onblur="if(this.value=='')this.value='State'"/>
				-<select name="lvAccountInfo.contryId" id="contryId" onchange="contryChange()" class="input2">
					<option value="">--Country--</option>
					<c:foreach items="${contryList}" var="c">
						<option value="${c.id}" <c:if test="${c.id==lvAccountInfo.contryId  }">selected</c:if>>${c.nameen}</option>
					</c:foreach>
				</select><font class="redfont">*</font>
				<input type="hidden" name="lvAccountInfo.contryName" id="contrynameId"  value="${lvAccountInfo.contryName }"/>
			</li>
		</ul>
			<ul>
				<li class="wd1"><font class="redfont">*</font>Zip Code：</li>
				<li class="wd2"><input name="lvAccountInfo.postCode"  id="postcodeId" type="text" value="${lvAccountInfo.postCode}" class="input2" />
				</li>
			</ul>
			<ul id="infoUl" style="display:none">
				<li class="wd1"></li>
				<li class="wd2"><span id="addressInfo"></span></li>
			</ul>
			<ul class="btn">
				<li class="wd1">&nbsp;</li>
				<li class="wd2"><input type="image"  src="${resDomain}/tvpaden/res/images/user_center_btn.gif" /></li>
			</ul>	
			</div>
		</form>
  </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 