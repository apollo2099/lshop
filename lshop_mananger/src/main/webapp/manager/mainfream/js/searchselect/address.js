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

//

	function validateAddress(){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var adress=$("#adress");
			flag = true;

			//检查国家的合法性
			var countryCurrentId = countrySelect.getSelectedId();
			if (countryCurrentId <= 0) {
				  countrySelect.setText('');
			      $("#addressInfo").html("<font color='red'>請選擇國家！</font>");
			      flag=false;
				  return flag ;				
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
				      flag=false;
					  return flag ;					
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
			if($.trim($("#contryName").val())==''){
			      $("#addressInfo").html("<font color='red'>國家不能為空！</font>");
				  contryId.focus();
				  flag=false;
				  return flag ;	
			}else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
			      $("#addressInfo").html("<font color='red'>洲/省不能為空！</font>");
				  provinceName.focus();
				  flag=false;
				  return flag ;	
			}else if(provinceName.val().length>32){
			      $("#addressInfo").html("<font color='red'>洲/省不能超過32位字符！</font>");
				  provinceName.focus();
				  flag=false;
				  return flag ;	
			}else if(isChinese.test(provinceName.val())) {
			      $("#addressInfo").html("<font color='red'>洲/省不能輸入中文！</font>");
				  provinceName.focus();
				  flag=false;
				  return flag ;	
			}else if($.trim(cityName.val())==''||$.trim(cityName.val())=='縣/市'){
			      $("#addressInfo").html("<font color='red'>縣/市不能為空！</font>");
				  cityName.focus();
				  flag=false;
				  return flag ;	
			}else if(isChinese.test(cityName.val())) {
			      $("#addressInfo").html("<font color='red'>縣/市不能輸入中文！</font>");
				  cityName.focus();
				  flag=false;
				  return flag ;	
			}else if(cityName.val().length>32){
			      $("#addressInfo").html("<font color='red'>縣/市不能超過32位字符！</font>");
				  cityName.focus();
				  flag=false;
				  return flag ;	
			}else if($.trim(adress.val())==''||$.trim(adress.val())=='街道詳細地址'){
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能為空！</font>");
			  adress.focus();
			  flag=false;
			  return flag ;	
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道詳細地址不能超過128位字符！</font>");
			  adress.focus();
			  flag=false;
			  return flag ;	
		    }
			
			return flag;
		    

	
}


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
