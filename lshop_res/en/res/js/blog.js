		
		//验证用户是否已经登录，如果未登录阻止用户信息提交并弹出友好提示
		function onSub(storeDomain){
			var users=lshop.getCookieToJSON('user');
			if(null==users.uid||""==users.uid){
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
				ymPrompt.confirmInfo('<strong>Please login first. If you don\'t have an account yet, please register!</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
					  toLo(storeDomain);
				  }
				if(tp=='cancel'){
					ymPrompt.close();
				  }} );
				  return false;
			}else if($("#review").val()=="Click here to enter your comment…"||$("#review").val()==""){
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
			    ymPrompt.alert({title:'Tips',message:'Please enter your comments!'}) ;
			    return false;
			}else{
				return true;
			}
		}
		//复制网址
		function fCopyToClicp(id){
		  var tempHref=document.location.href;
		  var a = document.getElementById(id);
		  window.clipboardData.setData('text',tempHref);
		  ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
		}
		
		 function copyClipboard(txt){
		 	txt=document.location.href;
		  	if(window.clipboardData){
				window.clipboardData.clearData();		
				window.clipboardData.setData("Text",txt);
		 		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
			}else if(navigator.userAgent.indexOf("Opera")!=-1){
				window.location=txt;
			}else if(window.netscape){
				try
				{
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}catch(e)
				{
				 	var a=	prompt("The current browser does not support shortcut key, please copy the code to clipboard by using 'Ctrl+C'",txt);
				  	if(a!=null){
		 				mPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  				ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
				  	}
				}
				var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);		
				if(!clip)return ;		
				var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);		
				if(!trans)return ;	
				trans.addDataFlavor('text/unicode');	
				var str=new Object();	
				var len=new Object();	
				var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);	
				var copytext=txt;		
				str.data=copytext;		
				trans.setTransferData("text/unicode",str,copytext.length*2);	
				var clipid=Components.interfaces.nsIClipboard;	
				if(!clip)return false;	
				clip.setData(trans,null,clipid.kGlobalClipboard);	
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		  		ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
			}
		  }
		//当用户焦点放在评论时，清除自带的文字内容
		function changeText(){
			var review=$("#review").val();
				if(review=="Click here to enter your comment…"){
				$("#review").val("")
			}
		}
		
		function toLo(storeDomain){
			var url=window.location;
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=1&jumpurl="+url+"";
		}
		
		function rss(){
			//先判断用户是否已经登录
			var users=lshop.getCookieToJSON('user');
			if(null==users.uid||""==users.uid){
				ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
				ymPrompt.confirmInfo('Please login first. If you don\'t have an account yet, please register!',null,null,'Tips',function handler(tp){if(tp=='ok'){
					  onshow('tx_b','loginDiv');
				  }
				if(tp=='cancel'){
					ymPrompt.close();
				 }} );
			}else{
				var tempHref=$("#tempHref").val();
				$("#rssId").attr("href",tempHref);
			}
		}