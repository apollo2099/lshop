		//邮件订阅
		function emailSubScribe(obj){
			var flag=null;
			if(obj.checked){
				flag=true;
			}else{
				flag=false;
			}
		
		    $.ajax({   
			     url: "/web/userCenterManage!subscribe.action",
				 data:"lvUserSubscribe.id="+$("#lsId").val()+"&lvUserSubscribe.uid="+$("#uid").val()+"&lvUserSubscribe.userName="+$("#userName").val()+"&lvUserSubscribe.email="+$("#myemail").val()+"&lvUserSubscribe.isSubscribe="+flag,   
				 type: "POST",  
				 dataType:"json",   
				 success: function(data){   
					 if(data!=null){
					    if(data.isSubscribe){
					      ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    		  ymPrompt.succeedInfo({title:'Tips',message:'Email subscription success!'}) ;
					      $("#lsId").val(data.id);
					    }else{
					       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    		   ymPrompt.succeedInfo({title:'Tips',message:'Email subscription canceled!'}) ;
					    }
					 }else{
					 	  ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    		  ymPrompt.alert({title:'Tips',message:'Operation failure!'}) ;
					 }  
				 } 
				});
		}
		
		//确认收货
		function confirmShouhuo(code){
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			ymPrompt.confirmInfo('<strong> Are you sure you have received your items?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
				  window.location.href="/web/userOrder!confirmShouhuo.action?code="+code;
			  }
			if(tp=='cancel'){
				ymPrompt.close();
			  }} );
		}
		
		//提醒发货
		function showRemind(oid,shopFlag){
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			$.ajax({
			   type: "POST",
			   url: "/web/userOrder!remindOrder.action",
			   data: "oid="+oid+"&shopFlag="+shopFlag,
			   success: function(){
			   		ymPrompt.succeedInfo({
						message:'<strong>Your reminder has been sent successfully, please be patient! Or contact our online customer service!</strong>',
						title:'Tips', 
						maxBtn:false
					});
			   }
			});
		
		}
		
		//修改密码验证
		$().ready(function() {
			$("#passForm").validate({
				rules: {
					pwd: {
						required: true,
						minlength: 6,
						maxlength: 16
					},
			        newPwd:{
				     required: true,
				     minlength: 6,
				     maxlength: 16
		            }
		            ,truePwd:{
				     required: true,
				     minlength: 6,
				     maxlength: 16,
				     equalTo: "#newPwd"
		            }
					
				},
				messages: {
					pwd: {
						required: "<font color='red'>Please enter the old password!</font>",
						minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
						maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>"
					},
					newPwd: {
						required: "<font color='red'>Please enter the new password!</font>",
						minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
						maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>"
					},
					truePwd: {
						required: "<font color='red'>Please enter the new password again!</font>",
						minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
						maxlength: "<font color='red'>Password shall not be longer than 16 characters!</font>",
						equalTo: "<font color='red'>The two passwords you entered do not match!</font>"
					}
				}
			});
		});