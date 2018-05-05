function qurryDevice(){
	var keyname = $("#keyname").val();
	var keycode = $("#keycode").val();
	var url = "/web/device!myDevice.action?name="+keyname+"&code="+keycode;
	location.href = url;
}

function checkWarranty(id){
	var url = "/web/device!checkWarranty.action?code="+id;
    $.ajax({   
		 url: url,
		 type: 'POST', 
		 async:false,
		 success: function(date){   
			 ymPrompt.alert({title:'',
				message:date,
				okTxt:'',
				titleBar:false});
		}   
	});
    
}

function removeBinding(id){
	var url = "/web/device!toUnbinding.action?code="+id;
	$.ajax({   
		 url: url,
		 type: 'POST', 
		 async:false,
		 success: function(date){   
			 ymPrompt.alert({title:'',
				message:date,
				okTxt:'',
				titleBar:false});
		}   
	});
}

function submitUnbinding(){
	var p = $("#password").val();
	var c = $("#code").val();
	if(p == ''){
		$("#pwdTips").text("Please enter your password");
		return;
	}
	$.ajax({   
		 url: '/web/device!unbinding.action',
		 data:{'p':p,'code':c},   
		 type: 'POST', 
		 async:false,
		 success: function(date){   
		   if(date==1){
			   ymPrompt.doHandler("ok",undefined);
			   alert("Unbound success!");
			   location.reload();
		   }else if(date==0){
			   $("#pwdTips").text("Wrong password, please re-enter");
		   }else{
			   $("#pwdTips").text("Operation failed!");
		   }
		}   
	});
}
