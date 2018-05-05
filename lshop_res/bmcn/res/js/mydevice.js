function qurryDevice(){
	var keyname = $("#keyname").val();
	var keycode = $("#keycode").val();
	var url = "/web/device!myDevice.action?name="+keyname+"&code="+keycode;
	location.href = url;
}
/*
function checkWarranty(id){
	var url = "/web/device!checkWarranty.action?code="+id;
	var iTop = (window.screen.availHeight - 400) / 2;  
    var iLeft = (window.screen.availWidth - 500) / 2;  
    
    var param = "dialogHeight:160px; dialogWidth:400px; help:no; resizable:no; location:no; status:no; dialogLeft:"+iLeft+"px; dialogTop:"+iTop+"px";
	window.showModalDialog(url, null, param);
}
*/
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

/*
function removeBinding(id){
	var url = "/web/device!toUnbinding.action?code="+id;
	var iTop = (window.screen.availHeight - 400) / 2;  
    var iLeft = (window.screen.availWidth - 500) / 2;  
    
    var param = "dialogHeight:210px; dialogWidth:400px;edge:raised ; help:no; resizable:no; location:no; status:no; dialogLeft:"+iLeft+"px; dialogTop:"+iTop+"px";
    var returnValue = window.showModalDialog(url, null, param);
    if(returnValue != null){
    	alert("操作成功！");
    	location.reload();
	}
}
*/
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
		$("#pwdTips").text("请输入密码！");
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
			   alert("操作成功！");
			   location.reload();
		   }else if(date==0){
			   $("#pwdTips").text("密码错误请重新输入！");
		   }else{
			   $("#pwdTips").text("操作失败！");
		   }
		}   
	});
}
