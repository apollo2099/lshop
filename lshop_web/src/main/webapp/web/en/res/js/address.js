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
				   async: false,
				   success: function(jsonData){
				   		var data=eval(jsonData);
				   		if(data.length>=1){
				   			$("#provinceName").remove();
				   			$("#provinceNameId").remove();
				   			$("#test").after("<select name='lvAccountAddress.provinceId' id='provinceName'  onchange='provinceChange()' >");
				   			$("#provinceName").append("<option value=''>--State/Province--</option>");
				   			for(var i=0;i<data.length;i++){
				   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
				   			}
				   			$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
				   		}else{
				   			$("#provinceName").remove();
				   			$("#provinceNameId").remove();
				   			$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input3' value='State/Province' onfocus='if(this.value==&quot;State/Province&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;State/Province&quot;'/>");
			
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
					    
					    
					    if($(".addrItem").length>=5){
			               ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			  		 	   ymPrompt.alert({title:'Tips',height:230,message:'If you have set 5 frequently used addresses and want to add a new one, please remove an old one first!'}) ;
			               return ;
			            }
					    form.submit();
					    
					}
				});
			});