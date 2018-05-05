//删除某条常用地址
function delAddress(addressCode,status){
	if(confirm("確定要刪除嗎？")){
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
		   	 		alert("刪除失敗");
		   	 	}
		   	 }
		});
	}
}

//选择国家时
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
	   async :false,
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

//选择省时
function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
}

//添加常用地址表单验证
$().ready(function() {
	$("#addressForm").validate({
		rules: {
		    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32,isNotChinese:true},
			'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'lvAccountAddress.relName': {
				required: "<font color='red'>请输入收貨人姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>",
				isNotChinese: "<font color='red'>姓名不能含有中文字符</font>"
			 },
			 'lvAccountAddress.mobile': {
				isChineseChar: "<font color='red'>手機不能含有特殊字符!</font>",
				maxlength: "<font color='red'>手機不能大于16位字符!</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>電話不能含有特殊字符!</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符!</font>"
			 },
			'lvAccountAddress.postCode': {
				required: "<font color='red'>请输入郵政區號</font>",
				isChineseChar: "<font color='red'>郵政區號不能含有特殊字符</font>",
				maxlength: "<font color='red'>郵政區號不能大于16位字符</font>"
			 }
		},
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
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
		    
		    
		    if($(".addrItem").length>=5){
               alert("常用地址大于5項，請先刪除再添加！");
               return ;
            }
		    form.submit();
		    
		}
	});
});