<%@ page language="java" pageEncoding="utf-8"%>
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
					  nInfo.html("<font color='red'>该昵称已存在！</font>");
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
			'lvAccountInfo.name':{required: true,isUnlawful:true,maxlength:32},
			'lvAccountInfo.tel':{isChineseChar:true,maxlength:16},
		    'lvAccountInfo.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountInfo.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			 'lvAccountInfo.qq': {
				 qq: "<font color='red'>qq號碼格式錯誤</font>",
				maxlength: "<font color='red'>qq號碼不能大于16位字符</font>"
			 },
			 'lvAccountInfo.msn': {
				 msn: "<font color='red'>msn格式錯誤</font>",
				maxlength: "<font color='red'>msn不能大于32位字符</font>"
			 },
			'lvAccountInfo.name': {
				required: "<font color='red'>请输入真實姓名</font>",
				isUnlawful: "<font color='red'>真實姓名不能含有非法字符</font>",
				maxlength: "<font color='red'>真實姓名不能大于32位字符</font>"
			 },
			 'lvAccountInfo.tel': {
				 isChineseChar: "<font color='red'>電話不能含有特殊字符</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符</font>"
			 },
			 'lvAccountInfo.mobile': {
				isChineseChar: "<font color='red'>手機不能含有特殊字符</font>",
				maxlength: "<font color='red'>手機不能大于16位字符</font>"
			 },
			'lvAccountInfo.postCode': {
				required: "<font color='red'>请输入郵政區號</font>",
				isChineseChar: "<font color='red'>郵政區號不能含有特殊字符</font>",
				maxlength: "<font color='red'>郵政區號不能大于16位字符</font>"
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
			  $("#mobileInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  $("#telInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='街道詳細地址'){
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能為空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='縣/市'){
		      $("#addressInfo").html("<font color='red'>縣/市不能為空！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
		      $("#addressInfo").html("<font color='red'>洲/省不能為空！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>國家不能為空！</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道詳細地址不能超過128位字符！</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>縣/市不能超過32位字符！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>洲/省不能超過32位字符！</font>");
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
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 我的資料</span></h1> 
		<form action="/web/userCenterManage!editInfo.action" method="post" id="myform">
			<input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}"/>
			<input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}"/>
			<input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}"/>
			<input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}"/>
			<input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}"/>
			<input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}"/>
			<input type="hidden" name="ordnickname" id="ordnickname" value="${lvAccount.nickname}"/>
			<div class="usercenter_box">
			<font color="red" ><c:if test="${not empty msg}">修改成功！</c:if></font>
		    <% session.removeAttribute("msg"); %>
				<ul>
					<li class="wd1">登錄帳號：</li>
					<li class="wd2">${lvAccount.email }</li>
			    </ul>
			   <ul>
					<li class="wd1"><font class="redfont">*</font>暱 稱：</li>
					<!--<li class="wd2"><input  type="text"  name="lvAccount.nickname" id="editNickName" onblur="checkEditNick()" value="${lvAccount.nickname}" class="input2" /> 
					<span id="editNickInfo"></span>
					</li>  --><li class="wd2">${lvAccount.nickname}</li>
			  </ul>	
			  <ul>
					<li class="wd1"><font class="redfont">*</font>性 別：</li>
					<li class="wd2">
					  <input type="radio" name="lvAccountInfo.sex" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked" </s:if>/>男 
					  <input name="lvAccountInfo.sex" type="radio" <s:if test="lvAccountInfo.sex==1">checked="checked" </s:if> value="1" /> 
					  女<input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked" </s:if> /> 
					  保密</li>
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
				<li class="wd1"><font class="redfont">*</font>真實姓名：</li>
				<li class="wd2"><input name="lvAccountInfo.name" id="realName"  value="${lvAccountInfo.name}" class="input2"  type="text" />
				</li>
			</ul>
			<ul>
				<li class="wd1">電話號碼：</li>
				<li class="wd2"><input name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" class="input2"  type="text" />
				<span id="telInfo"></span>
				</li>
			</ul>
			<ul>
				<li class="wd1">手機號碼：</li>
				<li class="wd2"><input name="lvAccountInfo.mobile" onkeypress="onlyNumber(event)" value="${lvAccountInfo.mobile }" id="mobile"  type="text" class="input2" />
				<span id="mobileInfo"></span>
				</li>
			</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>地址：</li>
			<li class="wd2">
			<input  name="lvAccountInfo.address" id="adress" type="text" class="input2" value="${lvAccountInfo.address }" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/> 
			-<input  name="lvAccountInfo.cityName" id="cityName" type="text" class="input2" value="${lvAccountInfo.cityName }" onfocus="if(this.value=='縣/市')this.value=''" onblur="if(this.value=='')this.value='縣/市'"/>
			-<input  name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input2" value="${lvAccountInfo.provinceName }" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/>
				-<select name="lvAccountInfo.contryId" id="contryId" onchange="contryChange()" class="input2">
					<option value="">--请选择国家--</option>
					<c:foreach items="${contryList}" var="c">
						<option value="${c.id}" <c:if test="${c.id==lvAccountInfo.contryId  }">selected</c:if>>${c.nameen}</option>
					</c:foreach>
				</select><font class="redfont">*</font>
				<input type="hidden" name="lvAccountInfo.contryName" id="contrynameId"  value="${lvAccountInfo.contryName }"/>
			</li>
		</ul>
			<ul>
				<li class="wd1"><font class="redfont">*</font>郵編：</li>
				<li class="wd2"><input name="lvAccountInfo.postCode"  id="postcodeId" type="text" value="${lvAccountInfo.postCode}" class="input2" />
				</li>
			</ul>
			<ul id="infoUl" style="display:none">
				<li class="wd1"></li>
				<li class="wd2"><span id="addressInfo"></span></li>
			</ul>
			<ul class="btn">
				<li class="wd1">&nbsp;</li>
				<li class="wd2"><input type="image"  src="${resDomain}/tvpadcn/res/images/user_center_btn.gif" /></li>
			</ul>	
			</div>
		</form>
  </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
		
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 