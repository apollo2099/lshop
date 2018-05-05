//删除某条常用地址
function delAddress(addressCode,status){
	if(confirm("确定要删除吗？")){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!delAddress.action",
		   data: "code="+addressCode,
		   async: false, 
		   success: function(flag){
		   	 	if(flag){
		   	 		$("#address"+status).remove();
		   	 		$('#UladdAddrArea').show();
		   	 	}else{
		   	 		alert("删除失败");
		   	 	}
		   	 }
		});
	}
}

//选择国家时
function contryChange(){
	//给国家名称赋值
	$("#contrynameId").val($("#contryId").find("option:selected").text());
	
	//获取对应的省
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==$("#contryId").val()){
				if(provinceObj[1].length>=1){
					$("#provinceName").remove();
					$("#provinceNameId").remove();
					$("#cityName").remove();
					$("#cityNameId").remove();
					$("#test").after("<select name='lvAccountAddress.provinceId' id='provinceName' onchange='provinceChange()' >");
					$("#provinceName").append("<option value=''>--请选择洲/省--</option>");
					$.each(provinceObj[1],function(n,cityObj) {
						$("<option></option>").val(cityObj[0].id).text(cityObj[0].nameen).appendTo($("#provinceName"));
					});
					$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
					$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
				}else{
					$("#provinceName").remove();
					$("#provinceNameId").remove();
					$("#cityName").remove();
					$("#cityNameId").remove();
					$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input04' value='洲/省' onfocus='if(this.value==&quot;洲/省&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;洲/省&quot;'/>");
					$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
				}
			}
		});	
	}
}

//选择洲省时
function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
	
	//获取对应的市
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==$("#contryId").val()){
				if(provinceObj[1].length>=1){
					$.each(provinceObj[1],function(n,cityObj) {
						if(cityObj[0].id==$("#provinceName").val()){
							if(cityObj[1].length>=1){
								$("#cityName").remove();
								$("#cityNameId").remove();
								$("#cityTest").after("<select name='lvAccountAddress.cityId' id='cityName' onchange='cityChange()' >");
								$("#cityName").append("<option value=''>--请选择县/市--</option>");
								$.each(cityObj[1],function(n,city) {
									$("<option></option>").val(city.id).text(city.nameen).appendTo($("#cityName"));
								});
								$("#cityName").after("<input type='hidden' name='lvAccountAddress.cityName' id='cityNameId'  value=''/>");
							}else{
								$("#cityName").remove();
								$("#cityNameId").remove();
								$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
							}
						}
					});
				}
				
			}
		});	
	}
}

//选择县市时
function cityChange(){
	//给洲省名称赋值
	$("#cityNameId").val($("#cityName").find("option:selected").text());
}

//显露出收货地址校验器重用
var AddressFormValidate = {
	rules: {
	    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32},
		'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
		'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
		'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
	},
	messages: {
		'lvAccountAddress.relName': {
			required: "<font color='red'>请输入收货人姓名</font>",
			minlength: "<font color='red'>姓名不能少于2位字符</font>",
			maxlength: "<font color='red'>姓名不能大于32位字符</font>"
		 },
		 'lvAccountAddress.mobile': {
			isChineseChar: "<font color='red'>手机不能含有特殊字符!</font>",
			maxlength: "<font color='red'>手机不能大于16位字符!</font>"
		 },
		 'lvAccountAddress.tel': {
			 isChineseChar: "<font color='red'>电话不能含有特殊字符!</font>",
			 maxlength: "<font color='red'>电话不能大于16位字符!</font>"
		 },
		'lvAccountAddress.postCode': {
			required: "<font color='red'>请输入邮政区号</font>",
			isChineseChar: "<font color='red'>邮政区号不能含有特殊字符</font>",
			maxlength: "<font color='red'>邮政区号不能大于16位字符</font>"
		 }
	}
}
//添加常用地址表单验证
$().ready(function() {
	$("#addressForm").validate($.extend({}, AddressFormValidate, {
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
			  $("#telInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='街道详细地址'){
			  $("#addressInfo").html("<font color='red'>街道详细地址不能为空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='县/市'){
		      $("#addressInfo").html("<font color='red'>县/市不能为空！</font>");
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
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道详细地址不能超过128位字符！</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>县/市不能超过32位字符！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>洲/省不能超过32位字符！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }
		    
		    form.submit();
		    
		}
	}));
});

$().ready(function() {
	$("#addressFormVbId").validate({
		rules: {
		    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32},
			'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'lvAccountAddress.relName': {
				required: "<font color='red'>请输入收货人姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>"
			 },
			 'lvAccountAddress.mobile': {
				isChineseChar: "<font color='red'>手机不能含有特殊字符!</font>",
				maxlength: "<font color='red'>手机不能大于16位字符!</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>电话不能含有特殊字符!</font>",
				 maxlength: "<font color='red'>电话不能大于16位字符!</font>"
			 },
			'lvAccountAddress.postCode': {
				required: "<font color='red'>请输入邮政区号</font>",
				isChineseChar: "<font color='red'>邮政区号不能含有特殊字符</font>",
				maxlength: "<font color='red'>邮政区号不能大于16位字符</font>"
			 }
		},
		submitHandler:function(form){
			var paymethod=$("input[name='paymethod']:checked");
		    if(paymethod.length==0){
		       $("#msgbox").html("请选择支付方式！");
		       return ;
		    }
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
			  $("#telInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='街道详细地址'){
			  $("#addressInfo").html("<font color='red'>街道详细地址不能为空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='县/市'){
		      $("#addressInfo").html("<font color='red'>县/市不能为空！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='州/省'){
		      $("#addressInfo").html("<font color='red'>州/省不能为空！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>国家不能为空！</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道详细地址不能超过128位字符！</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>县/市不能超过32位字符！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>州/省不能超过32位字符！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }
		    form.submit();
		    
		}
	});
});