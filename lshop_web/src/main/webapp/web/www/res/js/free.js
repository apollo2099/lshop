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
					var code=$('#yzm');
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
				    }else if($.trim(code.val())==''){
						$("#addressInfo").html("<font color='red'>請輸入驗證碼！</font>");
						$("#infoUl").show();
						code.focus();
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