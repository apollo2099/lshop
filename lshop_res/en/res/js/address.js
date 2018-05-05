var countrySelect,provinceSelect,citySelect;
//删除某条常用地址
function delAddress(addressCode,status){
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
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
		   	 		alert("Operation failure!");
		   	 	}
		   	 }
		});
	  }
	if(tp=='cancel'){
		ymPrompt.close();
	  }} );
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
			required: "<font color='red'>Please enter the consignee name!</font>",
			minlength: "<font color='red'>Consignee name shall be at lease 2 characters!</font>",
			maxlength: "<font color='red'>Consignee name shall not be longer than 32 characters!</font>",
			isNotChinese: "<font color='red'>Name shall not cover Chinese characters!</font>"
		 },
		 'lvAccountAddress.mobile': {
			isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
			maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
		 },
		 'lvAccountAddress.tel': {
			 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
			 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
		 },
		'lvAccountAddress.postCode': {
			required: "<font color='red'>Please enter the Zip Code!</font>",
			isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
			maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
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
			      $("#addressInfo").html("<font color='red'>Choose your country!</font>");
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
				      $("#addressInfo").html("<font color='red'>Choose State/Province!</font>");
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
			  $("#mobileInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  $("#telInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='Street address'){
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be null!</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim($("#contryId").val())==''){
			      $("#addressInfo").html("<font color='red'>Coutry shall not be null!</font>");
			      $("#infoUl").show();
				  contryId.focus();
				  return ;
			}else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='State/Province'){
			      $("#addressInfo").html("<font color='red'>State/Province shall not be null!</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if(isChinese.test(provinceName.val())) {
			      $("#addressInfo").html("<font color='red'>State/Province shall not be in Chinese!</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if(provinceName.val().length>32){
			      $("#addressInfo").html("<font color='red'>State/Province shall not be longer than 32 characters!</font>");
			      $("#infoUl").show();
				  provinceName.focus();
				  return ;
			}else if($.trim(cityName.val())==''||$.trim(cityName.val())=='County/City'||$.trim(cityName.val())=='--County/City--'){
		      $("#addressInfo").html("<font color='red'>County/City shall not be null!</font>");
		      $("#infoUl").show();
			  cityName.focus();
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
		    }
		    
		    form.submit();
	}})
	);
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
				required: "<font color='red'>Please enter the consignee name!</font>",
				minlength: "<font color='red'>Consignee name shall be at lease 2 characters!</font>",
				maxlength: "<font color='red'>Consignee name shall not be longer than 32 characters!</font>",
				isNotChinese: "<font color='red'>Name shall not cover Chinese characters!</font>"
			 },
			 'lvAccountAddress.mobile': {
				isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
				maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
				 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
			 },
			'lvAccountAddress.postCode': {
				required: "<font color='red'>Please enter the Zip Code!</font>",
				isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
				maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
			 }
		},
		submitHandler:function(form){
			var paymethod=$("input[name='paymethod']:checked");
		    if(paymethod.length==0){
		       $("#msgbox").html("Please select a mode of payment");
		       return false;
		    }
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  $("#telInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='Street address'){
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be null!</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='County/City'||$.trim(cityName.val())=='--County/City--'){
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

function initArea(){
	countrySelect = new SearchSelect('countrySelect');
	countrySelect.setText('');
	provinceSelect = new SearchSelect('provinceSelect');
	provinceSelect.setTipText('--State/Province--');
	citySelect = new SearchSelect('citySelect');
	citySelect.setTipText('--County/City--');
	//加载国家数据
	$.getScript(resDomainArea+'country.js',function(){
		countrySelect.setData(countryData);
		countrySelect.setTipText('--Country--');
		countrySelect.onchanged(function(itemId){
			$.getScript(resDomainArea+itemId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setTipText('--State/Province--');
				if (provinceData.length<1) {
					citySelect.setData([]);
					citySelect.setTipText('--County/City--');
				}				
			});
		});
	});	
	provinceSelect.onchanged(function(itemId){
		var countryId = countrySelect.getSelectedId();
		$.getScript(resDomainArea+countryId+'/'+itemId+'.js',function(){
			citySelect.setData(cityData);
			citySelect.setTipText('--County/City--');
		});		
	});	
}

function resetArea(){
	countrySelect.clearSelected();
	countrySelect.setTipText('--Country--');
	provinceSelect.clearSelected();
	provinceSelect.setTipText('--State/Province--');
	citySelect.clearSelected();
	citySelect.setTipText('--County/City--');
}

/*地址信息的相关验证信息被绑定在ready函数中，如遇网速慢页面未加载完成用户就提交，则会导致相关信息未被验证，本函数保证必须页面元素加载完成才能正常提交验证*/
function beforeAddressSubmit(){
	return jQuery.isReady;
}
