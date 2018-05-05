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
//表单验证
$().ready(function() {

	$("#myform").validate({
		rules: {
		    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32,isNotChinese:true},
			'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'lvAccountAddress.relName':{
				required: "<font color='red'>请输入收貨人姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>",
				isNotChinese: "<font color='red'>姓名不能含有中文字符</font>"
			},
			'lvAccountAddress.mobile':{
				isChineseChar: "<font color='red'>手機不能含有特殊字符</font>",
				maxlength: "<font color='red'>手機不能大于16位字符</font>"
			},
			'lvAccountAddress.tel':{
				 isChineseChar: "<font color='red'>電話不能含有特殊字符</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符</font>"
			},
			'lvAccountAddress.postCode':{
				required: "<font color='red'>请输入郵政區號</font>",
				isChineseChar: "<font color='red'>郵政區號不能含有特殊字符</font>",
				maxlength: "<font color='red'>郵政區號不能大于16位字符</font>"
			}
		},
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
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
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能为空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='縣/市'){
		      $("#addressInfo").html("<font color='red'>縣/市不能为空！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
		      $("#addressInfo").html("<font color='red'>洲/省不能为空！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>国家不能为空！</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(isChinese.test(adress.val())) {
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能輸入中文！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(isChinese.test(cityName.val())) {
		      $("#addressInfo").html("<font color='red'>縣/市不能輸入中文！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(isChinese.test(provinceName.val())) {
		      $("#addressInfo").html("<font color='red'>洲/省不能輸入中文！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
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


function contryChange(){
	//给国家名称赋值
	$("#contrynameId").val($("#contryId").find("option:selected").text());
	
	//获取对应的省市
	var provinceId=$("#provinceId");
	$.ajax({
	   type: "POST",
	   url: "/web/userCenterManage!getProvinces.action",
	   data: "parentid="+$("#contryId").val(),
	   dataType:"JSON",
	   success: function(jsonData){
	   		var data=eval(jsonData);
	   		if(data.length>=1){
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<select name='lvAccountAddress.provinceId' id='provinceName' class='input2'  onchange='provinceChange()' >");
	   			$("#provinceName").append("<option value=''>--請選擇洲/省--</option>");
	   			for(var i=0;i<data.length;i++){
	   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
	   			}
	   			$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
	   		}else{
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input2' value='洲/省' onfocus='if(this.value==&quot;洲/省&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;洲/省&quot;'/>");

	   		}
	   	}
	});
}	

function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
}
</script>
</head>
<body>

<div class="content_main">
	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 編輯收貨地址</span></h1> 
		<div class="usercenter_box">
		<font color="red">${msg}</font>
	    <% session.removeAttribute("msg"); %>
		<form action="/web/userCenterManage!editAddress.action" method="post" id="myform">
			<input type="hidden" name="lvAccountAddress.code" value="${lvAccountAddress.code }"/>
			<input type="hidden" name="lvAccountAddress.id" value="${lvAccountAddress.id }"/>
			<input type="hidden" name="lvAccountAddress.relCode" value="${lvAccountAddress.relCode }"/>
			<input type="hidden" name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }"/>
			<input type="hidden" name="lvAccountAddress.isDefault" value="${lvAccountAddress.isDefault }"/>
			<input type="hidden" name="lvAccountAddress.createTime" value="${lvAccountAddress.createTime }"/>
			<ul>
				<li class="wd1"><font class="redfont">*</font>收貨人姓名：</li>
				<li class="wd2"><input name="lvAccountAddress.relName"  type="text" class="input2" value="${lvAccountAddress.relName }" /><font class="redfont">(姓名必須為英文)</font>
				</li>
		    </ul>
		    
		  <ul>
			<li class="wd1">手機號碼：</li>
			<li class="wd2">
			  <input name="lvAccountAddress.mobile" type="text" class="input2" onkeypress="onlyNumber(event)" value="${lvAccountAddress.mobile }" id="mobile"/><span id="mobileInfo"></span>
			</li>
		</ul>
		<ul>
			<li class="wd1">固定電話：</li>
			<li class="wd2"><input name="lvAccountAddress.tel"  type="text" class="input2" value="${lvAccountAddress.tel }" id="tel"/><span id="telInfo"></span>
			</li>
		</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>收貨地址：</li>
			<li class="wd2">
			<input  name="lvAccountAddress.adress" id="adress" type="text" class="input2" value="${lvAccountAddress.adress }" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/> 
			-<input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input2" value="${lvAccountAddress.cityName }" onfocus="if(this.value=='縣/市')this.value=''" onblur="if(this.value=='')this.value='縣/市'"/>
			-<!--<input  name="lvAccountAddress.provinceName" id="provinceName" type="text" class="input2" value="${lvAccountAddress.provinceName }" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/>  -->
			<input type="hidden" id="test" />
			<c:if test="${not empty provinceList}">
				<select name="lvAccountAddress.provinceId" id="provinceName" class="input2"  onchange="provinceChange()" >
					<option value="">--請選擇洲/省--</option>
					<c:foreach items="${provinceList}" var="p">
						<option value="${p.id}" <c:if test="${p.id==lvAccountAddress.provinceId  }">selected</c:if>>${p.nameen}</option>
					</c:foreach>
				</select>
				<input type="hidden" name="lvAccountAddress.provinceName" id="provinceNameId"  value="${lvAccountAddress.provinceName }"/>
			</c:if>
			<c:if test="${empty provinceList}">
				<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input2" value="${lvAccountAddress.provinceName }"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
			</c:if>
			-<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()" class="input2">
					<option value="">--请选择国家--</option>
					<c:foreach items="${contryList}" var="c">
						<option value="${c.id}" <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
					</c:foreach>
				</select>
				<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value="${lvAccountAddress.contryName }"/>
			</li>
		</ul>
		<ul id="infoUl" style="display:none">
			<li class="wd1"></li>
			<li class="wd2"><span id="addressInfo"></span></li>
		</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>郵政區號：</li>
			<li class="wd2">
			  <input name="lvAccountAddress.postCode"  type="text" class="input2" value="${lvAccountAddress.postCode }" />
			</li>
		</ul>
		<ul class="btn">
			<li class="wd1">&nbsp;</li>
			<li class="wd2"><input type="image" onclick="onSub();" src="${resDomain}/tvpadcn/res/images/user_center_btn.gif" width="101" height="34" /></li>
		</ul>
		</form>
		
  	    </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 