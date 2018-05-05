var countrySelect,provinceSelect,citySelect;
function doSubmitAddAddress(){
	$('input:visible').trigger('valiField');
	if ($('#addressForm').find('.error:visible').length < 1) {
		//检查国家的合法性
		var countryCurrentId = countrySelect.getSelectedId();
		if (countryCurrentId <= 0) {
			  countrySelect.setText('');
		      $("#contry_tip").show();
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
			      $("#province_tip").show();
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
		
		var address = $.trim($("#adress").val());
		var isChinese = /[\u4E00-\u9FA5]/i; 
		if($.trim($("#contryName").val())==''){
		      $("#contry_tip").show();
			  return ;				
		} else if($.trim($("#provinceName").val())==''){
		      $("#province_tip").show();
			  return ;			
		} else if($.trim($("#cityName").val())==''){
		      $("#city_tip").show();
			  return ;			
		} else if (address=='') {
			  $("#adress_tip span").html('请输入正确街道地址');
		      $("#adress_tip").show();
			  return;				
		} else if (isChinese.test(address)) {
			  $("#adress_tip span").html('街道地址不能输入中文！');
		      $("#adress_tip").show();
			  return;				
		}
		document.addressForm.submit();
	}
}

function initArea(){
	countrySelect = new SearchSelect('countrySelect');
	countrySelect.setText('');
	provinceSelect = new SearchSelect('provinceSelect');
	provinceSelect.setTipText('--洲/省--');
	citySelect = new SearchSelect('citySelect');
	citySelect.setTipText('--县/市--');
	//加载国家数据
	$.getScript(resDomainArea+'country.js',function(){
		countrySelect.setData(countryData);
		countrySelect.setTipText('--国家--');
		countrySelect.onchanged(function(itemId){
			$.getScript(resDomainArea+itemId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setTipText('--洲/省--');
				if (provinceData.length<1) {
					citySelect.setData([]);
					citySelect.setTipText('--县/市--');
				}				
			});
		});
	});	
	provinceSelect.onchanged(function(itemId){
		var countryId = countrySelect.getSelectedId();
		$.getScript(resDomainArea+countryId+'/'+itemId+'.js',function(){
			citySelect.setData(cityData);
			citySelect.setTipText('--县/市--');
		});		
	});	
}

function resetArea(){
	countrySelect.clearSelected();
	countrySelect.setTipText('--国家--');
	provinceSelect.clearSelected();
	provinceSelect.setTipText('--洲/省--');
	citySelect.clearSelected();
	citySelect.setTipText('--县/市--');
}

/*地址信息的相关验证信息被绑定在ready函数中，如遇网速慢页面未加载完成用户就提交，则会导致相关信息未被验证，本函数保证必须页面元素加载完成才能正常提交验证*/
function beforeAddressSubmit(){
	return jQuery.isReady;
}
