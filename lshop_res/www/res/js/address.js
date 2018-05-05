var countrySelect,provinceSelect,citySelect;
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

//显露出收货地址校验器重用
var AddressFormValidate = {
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
	}	
};
//添加常用地址表单验证
$().ready(function() {
	$("#addressForm").validate($.extend({}, AddressFormValidate, {
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
			
			//检查国家的合法性
			var countryCurrentId = countrySelect.getSelectedId();
			if (countryCurrentId <= 0) {
				  countrySelect.setText('');
			      $("#addressInfo").html("<font color='red'>請選擇國家！</font>");
			      $("#infoUl").show();
				  return ;				
			} else {
				$("#contryId").val(countryCurrentId);
				$("#contryName").val(countrySelect.getSelectedText());				
			}
			//检查省份的合法性
			var provinceCurrentId = provinceSelect.getSelectedId();
			if (provinceSelect.isEmptyData()) {
				$("#provinceId").val('');
				$("#provinceName").val(provinceSelect.getText());
				$("#cityId").val('');
				$("#cityName").val(citySelect.getText());				
			} else {
				if (provinceCurrentId <= 0) {
					  provinceSelect.setText('');
				      $("#addressInfo").html("<font color='red'>請選擇洲/省！</font>");
				      $("#infoUl").show();
					  return ;				
				} else {//检查城市的合法性
					var cityCurrentId = citySelect.getSelectedId();
					if (citySelect.isEmptyData()) {
						$("#provinceId").val(provinceCurrentId);
						$("#provinceName").val(provinceSelect.getSelectedText());
						$("#cityId").val('');
						$("#cityName").val(citySelect.getText());						
					} else {
						if (cityCurrentId <= 0) {
							$("#provinceId").val(provinceCurrentId);
							$("#provinceName").val(provinceSelect.getSelectedText());
							$("#cityId").val('');
							$("#cityName").val(citySelect.getText());								
						} else {
							$("#provinceId").val(provinceCurrentId);
							$("#provinceName").val(provinceSelect.getSelectedText());
							$("#cityId").val(cityCurrentId);
							$("#cityName").val(citySelect.getSelectedText());							
						}
					}				
				}				
			}
			
			var cityName=$("#cityName");
			var cityId=$("#cityId");
			var provinceId=$("#provinceId");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			var contryName=$("#contryName");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  $("#telInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim($("#contryName").val())==''){
			      $("#addressInfo").html("<font color='red'>國家不能為空！</font>");
			      $("#infoUl").show();
				  contryId.focus();
				  return ;
			}else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
			      $("#addressInfo").html("<font color='red'>洲/省不能為空！</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if(provinceName.val().length>32){
			      $("#addressInfo").html("<font color='red'>洲/省不能超過32位字符！</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if(isChinese.test(provinceName.val())) {
			      $("#addressInfo").html("<font color='red'>洲/省不能輸入中文！</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if($.trim(cityName.val())==''||$.trim(cityName.val())=='縣/市'){
			      $("#addressInfo").html("<font color='red'>縣/市不能為空！</font>");
			      $("#infoUl").show();
				  cityName.focus();
				  return ;
			}else if(isChinese.test(cityName.val())) {
			      $("#addressInfo").html("<font color='red'>縣/市不能輸入中文！</font>");
			      $("#infoUl").show();
				  cityName.focus();
				  return ;
			}else if(cityName.val().length>32){
			      $("#addressInfo").html("<font color='red'>縣/市不能超過32位字符！</font>");
			      $("#infoUl").show();
				  cityName.focus();
				  return ;
			}else if($.trim(adress.val())==''||$.trim(adress.val())=='街道詳細地址'){
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能為空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(isChinese.test(adress.val())) {
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能輸入中文！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道詳細地址不能超過128位字符！</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }
		    
		    form.submit();
		}})
	);
});
$(document).ready(function() {
	$("#addressFormVbId").validate({
		rules: {
		    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32},
			'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'lvAccountAddress.relName': {
				required: "<font color='red'>请输入姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>"
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

function initArea(){
	countrySelect = new SearchSelect('countrySelect');
	countrySelect.setText('');
	provinceSelect = new SearchSelect('provinceSelect');
	provinceSelect.setTipText('--洲/省--');
	citySelect = new SearchSelect('citySelect');
	citySelect.setTipText('--縣/市--');
	//加载国家数据
	$.getScript(resDomainArea+'country.js',function(){
		countrySelect.setData(countryData);
		countrySelect.setTipText('--國家--');
		countrySelect.onchanged(function(itemId){
			$.getScript(resDomainArea+itemId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setTipText('--洲/省--');
				if (provinceData.length<1) {
					citySelect.setData([]);
					citySelect.setTipText('--縣/市--');
				}
			});
		});
	});	
	provinceSelect.onchanged(function(itemId){
		var countryId = countrySelect.getSelectedId();
		$.getScript(resDomainArea+countryId+'/'+itemId+'.js',function(){
			citySelect.setData(cityData);
			citySelect.setTipText('--縣/市--');
		});		
	});	
}

function resetArea(){
	countrySelect.clearSelected();
	countrySelect.setTipText('--國家--');
	provinceSelect.clearSelected();
	provinceSelect.setTipText('--洲/省--');
	citySelect.clearSelected();
	citySelect.setTipText('--縣/市--');
}
/*地址信息的相关验证信息被绑定在ready函数中，如遇网速慢页面未加载完成用户就提交，则会导致相关信息未被验证，本函数保证必须页面元素加载完成才能正常提交验证*/
function beforeAddressSubmit(){
	return jQuery.isReady;
}
