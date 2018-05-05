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
				      alert("郵件訂閱成功！");
				      $("#lsId").val(data.id);
				    }else{
				       alert("郵件退訂成功！");
				    }
				 }else{
				   alert("操作失敗！");
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
					required: "<font color='red'>請輸入原密碼！</font>",
					minlength: "<font color='red'>密碼不能少于6位字符！</font>",
					maxlength: "<font color='red'>密碼不能大于16位字符！</font>"
				},
				newPwd: {
					required: "<font color='red'>請輸入新密碼！</font>",
					minlength: "<font color='red'>密碼不能少于6位！</font>",
					maxlength: "<font color='red'>密碼不能大于16位字符！</font>"
				},
				truePwd: {
					required: "<font color='red'>請再次輸入新密碼！</font>",
					minlength: "<font color='red'>密碼不能少于6位！</font>",
					maxlength: "<font color='red'>密碼不能大于16位字符！</font>",
					equalTo: "<font color='red'>兩次輸入密碼不一致！</font>"
				}
			}
		});
	});
	
	//确认收货
	function confirmShouhuo(code){
		ymPrompt.confirmInfo('<strong>確認收到貨</strong>',null,null,'溫馨提示',function handler(tp){if(tp=='ok'){
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
					message:'<strong>提醒發送成功，請耐心等待<br/><br/>或聯繫在線客服！</strong>',
					title:'溫馨提示', 
					maxBtn:false
				});
		   }
		});
	
	}