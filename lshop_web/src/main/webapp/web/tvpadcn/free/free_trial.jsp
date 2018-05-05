<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad2定制版智能系统网络智能机顶盒</title>
<!-- top -->
<% request.setAttribute("navFlag","cn_free"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>

<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/top_bottom.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/free.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

//表单验证
$().ready(function() {
	$("#myform").validate({
		rules: {
		    'name':{required: true,minlength:2,maxlength:32,isNotChinese:true},
			'tel':{required: true,isChineseChar:true,maxlength:16},
			'email':{required: true,email:true,maxlength:32},
			'postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'name':{
				required: "<font color='red'>请输入申請人姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符！</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>",
				isNotChinese: "<font color='red'>姓名不能含有中文字符</font>"
			},
			'tel':{
				 required: "<font color='red'>请输入電話</font>",
				 isChineseChar: "<font color='red'>電話不能含有特殊字符</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符</font>"
			},
			'email':{
				 required: "<font color='red'>请输入郵箱</font>",
				 email: "<font color='red'>郵箱格式不正確</font>",
				 maxlength: "<font color='red'>郵箱不能大于32位字符</font>"
			},
			'postCode':{
				required: "<font color='red'>请输入郵編</font>",
				isChineseChar: "<font color='red'>郵編不能含有特殊字符</font>",
				maxlength: "<font color='red'>郵編不能大于16位字符</font>"
			}
		},
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var adress=$("#adress");
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(adress.val())==''||$.trim(adress.val())=='街道詳細地址'){
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
	   			$("#test").after("<select name='provinceId' id='provinceName' class='input2'  onchange='provinceChange()' >");
	   			$("#provinceName").append("<option value=''>--請選擇洲/省--</option>");
	   			for(var i=0;i<data.length;i++){
	   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
	   			}
	   			$("#provinceName").after("<input type='hidden' name='provinceName' id='provinceNameId'  value=''/>");
	   		}else{
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<input type='text' name='provinceName' id='provinceName' class='input2' value='洲/省' onfocus='if(this.value==&quot;洲/省&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;洲/省&quot;'/>");

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

<div class="project">
<div class="banner">
  <p class="p1"></p>
  <p class="p2"><span><a href="#shiyong"><img src="${resDomain}/tvpadcn/res/images/free/anniu1.jpg" border="0" /></a></span></p>
</div>
<div class="main">

  <div class="main1">
    <ul>
	  <li><img src="${resDomain}/tvpadcn/res/images/free/gaoqing.jpg" width="50" height="50" /><p><span>高清穩定</span><br />
專為海外華人提供香港、澳門、台灣、大陸超過100個高清穩定的<br />
中文電視直播頻道；</p>
	  </li>
	  <li><img src="${resDomain}/tvpadcn/res/images/free/zhengban.jpg" width="50" height="50" /><p><span>正版保證</span><br />
中國最大廣電集團運營傳媒企業——華數入駐TVpad，正版認證，<br />
片源穩定；</p>
	  </li>
	  <li><img src="${resDomain}/tvpadcn/res/images/free/new.jpg" width="50" height="50" />
	  <p><span>每日更新</span><br />
超過100,000部正版授權的影劇、綜藝、動漫和紀錄片每日更新；</p>
	  </li>
	  <li><img src="${resDomain}/tvpadcn/res/images/free/yule.jpg" width="50" height="50" />
	  <p><span>娛樂體驗</span><br />
卡拉OK，體感遊戲、VOIP電話燈全新娛樂功能體驗，操作簡單；</p>
	  </li>
	</ul>
  </div>
  
  <div class="main2">
    <p class="image1"><span>“每一個中國傳統佳節都牽動著億萬遊子的心。正所謂禮尚往來，一份<br />
體面又有新意的好禮最能表達思念和慰問！”<br />
<a href="#shiyong">立即免費試用>></a></span></p>
	<p class="image2"></p>
  </div>
  
  <div class="main3">
   <p><img src="${resDomain}/tvpadcn/res/images/free/main3_image1.jpg" /></p>
   <p><img src="${resDomain}/tvpadcn/res/images/free/main3_image2.jpg" /></p>
  </div>
  
  <div class="main4" id="shiyong">
    <div class="main4_top"></div>
	<div class="main4_middle">
	  <p class="bt"><img src="${resDomain}/tvpadcn/res/images/free/main4_bg_bt.jpg" /></p>
	  <form action="/web/free!toEmail.action" method="post" id="myform">
	  <ul>
	    <li><p class="text"><span class="red">*</span> 申請人:</p><p class="input"><input name="name" class="input2"  type="text" /></p></li>
		<li><p class="text"><span class="red">*</span> 電話:</p><p class="input"><input name="tel" class="input2"  type="text" /></p></li>
		<li><p class="text"><span class="red">*</span> 郵箱:</p><p class="input"><input name="email" class="input2"  type="text" /></p></li>
		<li><p class="text"><span class="red">*</span> 郵編:</p><p class="input"><input name="postCode" class="input2"  type="text" /></p></li>
		<li><p class="text"><span class="red">*</span> 收貨地址:</p><p class="input"><input  name="adress" id="adress" type="text" class="input3" value="街道詳細地址" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/>  
			- <input  name="cityName" id="cityName" type="text" class="input2" value="縣/市" onfocus="if(this.value=='縣/市')this.value=''" onblur="if(this.value=='')this.value='縣/市'"/>
			- <input type="hidden" id="test" />
			<input type="text" name="provinceName" id="provinceName" class="input2" value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
			-<select name="contryId" id="contryId" onchange="contryChange()" class="input2">
				<option value="">--請選擇國家--</option>
				<c:foreach items="${contryList}" var="c">
					<option value="${c.id}">${c.nameen}</option>
				</c:foreach>
			</select>
			<input type="hidden" name="contryName" id="contrynameId"  value=""/>
		<li id="infoUl" style="display:none"><span id="addressInfo"></span></li>
		<li class="btn"><input type="image" src="${resDomain}/tvpadcn/res/images/free/tijiao.jpg" border="0" /></li>
	  </ul>
	  </form>	
	  <div class="cb"></div>
	</div>
	<div class="main4_bottom"></div>
  </div>
</div>
<!--End 试用-->

<!-- 底部-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>
</body>
</html>