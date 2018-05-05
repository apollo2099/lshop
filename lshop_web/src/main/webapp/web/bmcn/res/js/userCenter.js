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
				      alert("邮件订阅成功！");
				      $("#lsId").val(data.id);
				    }else{
				       alert("邮件退订成功！");
				    }
				 }else{
				   alert("操作失败！");
				 }  
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
					required: "<font color='red'>请输入原密码！</font>",
					minlength: "<font color='red'>密码不能少于6位字符！</font>",
					maxlength: "<font color='red'>密码不能大于16位字符！</font>"
				},
				newPwd: {
					required: "<font color='red'>请输入新密码！</font>",
					minlength: "<font color='red'>密码不能少于6位！</font>",
					maxlength: "<font color='red'>密码不能大于16位字符！</font>"
				},
				truePwd: {
					required: "<font color='red'>请再次输入新密码！</font>",
					minlength: "<font color='red'>密码不能少于6位！</font>",
					maxlength: "<font color='red'>密码不能大于16位字符！</font>",
					equalTo: "<font color='red'>两次输入密码不一致！</font>"
				}
			}
		});
	});
	
	//确认收货
	function confirmShouhuo(code){
		ymPrompt.confirmInfo('<strong>确认收到货</strong>',null,null,'温馨提示',function handler(tp){if(tp=='ok'){
			  window.location.href="/web/userOrder!confirmShouhuo.action?code="+code;
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}
	
	//提醒发货
	function showRemind(oid,shopFlag){
		$.ajax({
		   type: "POST",
		   url: "/web/userOrder!remindOrder.action",
		   data: "oid="+oid+"&shopFlag="+shopFlag,
		   success: function(){
		   		ymPrompt.succeedInfo({
					message:'<strong>提醒发送成功，请耐心等待<br/><br/>或联系在线客服！</strong>',
					title:'温馨提示', 
					maxBtn:false
				});
		   }
		});
	
	}
	
