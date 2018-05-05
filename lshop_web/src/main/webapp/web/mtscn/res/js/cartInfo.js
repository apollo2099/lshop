

$(function (){
	//检验登陆表单
	checkInfoAddress();
	$('#address_form').submit(function(e){
		$(this).find('input:visible').focus();
		var n = checkName($('input[name="lvAccountAddress.relName"]')),
		m = checkMobile($('input[name="lvAccountAddress.mobile"]')),
		t = checkTel($('input[name="lvAccountAddress.tel"]')),
		r = checkCountry($('#contryId')),
		v = checkProvince($('#provinceName'));
		c = checkCity($('input[name="lvAccountAddress.cityName"]')),
		a = checkAddress($('input[name="lvAccountAddress.adress"]')),
		p = checkZip($('input[name="lvAccountAddress.postCode"]'));
		
		if(n && m && t && r && v && c && a && p){
			//手机和电话选一
			if($('input[name="lvAccountAddress.mobile"]').val() == "" && $('input[name="lvAccountAddress.tel"]') == ""){
				errorTip($('input[name="lvAccountAddress.mobile"]'), "手机和固定电话必须填写其中一项");
				errorTip($('input[name="lvAccountAddress.tel"]'), "手机和固定电话必须填写其中一项");
				$('input[name="lvAccountAddress.tel"]').one('focus', function(e){
					successTip($('input[name="lvAccountAddress.mobile"]'));
					successTip($('input[name="lvAccountAddress.tel"]'));
				});
				e.preventDefault();
				return false;
			}
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});
});


//验证登陆
function checkInfoAddress(){
	//收货人
	$("input[name='lvAccountAddress.relName']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.relName']").blur(function(){
		checkAddress($(this));
	});
	
	//手机
	$("input[name='lvAccountAddress.mobile']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.mobile']").blur(function(){
		checkMobile($(this));
	});
	
	//电话
	$("input[name='lvAccountAddress.tel']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.tel']").blur(function(){
		checkTel($(this));
	});
	
	//省/市
	$('#address_form #provinceName').live('focus', function(e){
		if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	});
	$('#address_form #provinceName').live('blur', function(e){
		console.log('blre');
		checkProvince($(this));
	});
	//县/市
	$("input[name='lvAccountAddress.cityName']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.cityName']").blur(function(){
		checkCity($(this));
	});
	
	//街道地址
	$("input[name='lvAccountAddress.adress']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.adress']").blur(function(){
		checkAddress($(this));
	});
	
	//邮编
	$("input[name='lvAccountAddress.postCode']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccountAddress.postCode']").blur(function(){
		checkZip($(this));
	});


}

//选择国家时
function contryChange(obj){
	//收缩效果
	$(obj).next(".country").slideToggle();
	$(".country").find("ul").find("li").click(function(){
		 $(this).parents(".country").prev().text($(this).text());
		 $(obj).next(".country").hide();
		 $("#contryId").val($(this).attr("value"));
		 $("#contrynameId").val($(this).text());
		 
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
		   			$("#provinceIdDiv").remove();
		   			$("#provinceNameDiv").remove();
		   			$("#test").after("<div class='shose_1' id='provinceIdDiv'></div>");
		   			$("#provinceIdDiv").append("<span class='spanchose' onClick='provinceChange(this)'>请选择洲/省</span>");
		   			$("#provinceIdDiv").append("<div class='country'><input type='hidden' id='provinceName' name='lvAccountAddress.provinceId' value=''/><input type='hidden' id='provinceNameId' name='lvAccountAddress.provinceName' value=''/><ul class='provinceCss'></ul></div>");
		   			for(var i=0;i<data.length;i++){
		   				$(".provinceCss").append("<li value='"+data[i].id+"'>"+data[i].nameen+"</li>");
		   			}
		   		}else{
		   			$("#provinceIdDiv").remove();
		   			$("#provinceNameDiv").remove();
		   			$("#test").after("<div class='shose_2' id='provinceNameDiv'><input type='text'  class='inpu' id='provinceName' value='请输入洲/省' name='lvAccountAddress.provinceName' defalt='请输入洲/省'/></div>");
	
		   		}
		   	}
		});
	});
}

//选择洲/省时
function provinceChange(obj){
	//收缩效果
	$(obj).next(".country").slideToggle();
	$(".country").find("ul").find("li").click(function(){
		 $(this).parents(".country").prev().text($(this).text());
		 $(obj).next(".country").hide();
		 $("#provinceName").val($(this).attr("value"));
		 $("#provinceNameId").val($(this).text());
	});
}