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
				required: "<font color='red'>Please enter the consignee name!</font>",
				minlength: "<font color='red'>Consignee name shall be at lease 2 characters!</font>",
				maxlength: "<font color='red'>Consignee name shall not be longer than 32 characters!</font>",
				isNotChinese: "<font color='red'>Name shall not cover Chinese characters!</font>"
			},
			'lvAccountAddress.mobile':{
				isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
				maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
			},
			'lvAccountAddress.tel':{
				 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
				 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
			},
			'lvAccountAddress.postCode':{
				required: "<font color='red'>Please enter the Zip Code!</font>",
				isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
				maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
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
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='State/Province'){
		      $("#addressInfo").html("<font color='red'>State/Province shall not be null!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>Coutry shall not be null!</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(isChinese.test(adress.val())) {
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be in Chinese!</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(isChinese.test(cityName.val())) {
		      $("#addressInfo").html("<font color='red'>County/City shall not be in Chinese!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(isChinese.test(provinceName.val())) {
		      $("#addressInfo").html("<font color='red'>State/Province shall not be in Chinese!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
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
	   			$("#provinceName").append("<option value=''>--State/Province--</option>");
	   			for(var i=0;i<data.length;i++){
	   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
	   			}
	   			$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
	   		}else{
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input2' value='State/Province' onfocus='if(this.value==&quot;State/Province&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;State/Province&quot;'/>");

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
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">
	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font>  Add a new billing address</span></h1> 
		<div class="usercenter_box">
		<font color="red">${msg}</font>
	    <% session.removeAttribute("msg"); %>
	    <font color="red">*To ensure timely receiving our products, please fill in your real address (English Only)</font>
		<form action="/web/userCenterManage!addAddress.action" method="post" id="myform">
			<ul>
				<li class="wd1"><font class="redfont">*</font>Name：：</li>
				<li class="wd2"><input name="lvAccountAddress.relName" class="input2"  type="text" id="relName"/><font class="redfont">(English Only)</font>
				</li>
		    </ul>
		    
		  <ul>
			<li class="wd1">Mobile No.：</li>
			<li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input2"  onkeypress="onlyNumber(event)" type="text" id="mobile"/><span id="mobileInfo"></span>
			</li>
		</ul>
		<ul>
			<li class="wd1">Tel.：</li>
			<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input2"  type="text" id="tel"/><span id="telInfo"></span>
			</li>
		</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>Shipping Address：</li>
			<li class="wd2">
			<input  name="lvAccountAddress.adress" id="adress" type="text" class="input2" value="Street" onfocus="if(this.value=='Street')this.value=''" onblur="if(this.value=='')this.value='Street'"/> 
			-<input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input2" value="County/City" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'"/>
			-<!--<input name="lvAccountAddress.provinceName" id="provinceName"
					type="text" class="input2" value="洲/省"
					onfocus="if(this.value=='洲/省')this.value=''"
					onblur="if(this.value=='')this.value='洲/省'" />  -->
				<input type="hidden" id="test" />
				<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input2" value="State/Province"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
				-<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()" class="input2">
					<option value="">--Country--</option>
					<c:foreach items="${contryList}" var="c">
						<option value="${c.id}" <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
					</c:foreach>
				</select><font class="redfont">*</font>
				<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value=""/>
			</li>
		</ul>
		<ul>
			<li class="wd1"><font class="redfont">*</font>Zip Code：</li>
			<li class="wd2">
			  <input name="lvAccountAddress.postCode" class="input2"  type="text" />
			</li>
		</ul>
		<ul id="infoUl" style="display:none">
			<li class="wd1"></li>
			<li class="wd2"><span id="addressInfo"></span></li>
		</ul>
		<ul class="btn">
			<li class="wd1">&nbsp;</li>
			<li class="wd2"><p class="user_bt"><input name="" type="submit" value="submit"  class="qrtj" /></p></li>
		</ul>
		</form>
		
  	    </div>
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 