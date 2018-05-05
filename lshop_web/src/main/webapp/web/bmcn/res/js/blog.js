	//订阅
	function rss(){
		//先判断用户是否已经登录
		var users=lshop.getCookieToJSON('user');
		if(null==users.uid||""==users.uid){
			alert("请先登录，如果没有账户请先注册账户!");
			onshow('tx_b','loginDiv');
		}else{
			var tempHref=$("#tempHref").val();
			$("#rssId").attr("href",tempHref);
		}
	}
	
	
	//验证用户是否已经登录，如果未登录阻止用户信息提交并弹出友好提示
	function onSub(storeDomain){
		var users=lshop.getCookieToJSON('user');
		if(null==users.uid||""==users.uid){
			toLo(storeDomain);
		}else if($("#review").val()=="点击这里发表评论…"||$("#review").val()==""){
			alert("请填写评论內容!");
		}else{
			$("#blogfrm").submit();
		}
	}
	
	//复制网址
	function fCopyToClicp(id){
	  var tempHref=document.location.href;
	  var a = document.getElementById(id);
	  window.clipboardData.setData('text',tempHref);
	  alert("已复制到剪贴板，您可以粘贴到QQ/MSN上发送给您的好友！");
	}
	
	 function copyClipboard(txt){
	 	txt=document.location.href;
	  	if(window.clipboardData){
			window.clipboardData.clearData();		
			window.clipboardData.setData("Text",txt);
	 		alert("已复制到剪贴板，您可以粘贴到QQ/MSN上发送给您的好友！");
		}else if(navigator.userAgent.indexOf("Opera")!=-1){
			window.location=txt;
		}else if(window.netscape){
			try
			{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			}catch(e)
			{
			 	var a=	prompt("您使用的浏览器不支持快捷键，请按下 Ctrl+C 复制代码到剪贴板！",txt);
			  	if(a!=null){
	 					alert("已复制到剪贴板，您可以粘贴到QQ/MSN上发送给您的好友！");
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
			alert("已复制到剪贴板，您可以粘贴到QQ/MSN上发送给您的好友！");
		}
	}
	  
	//当用户焦点放在评论时，清除自带的文字内容
	function changeText(){
		var review=$("#review").val();
			if(review=="点击这里发表评论…"){
			$("#review").val("")
		}
	}
	
	function toLo(storeDomain){
		var url=window.location;
		window.location.href=storeDomain+"/web/bmcn/noLoginInfo.jsp?jumpurl="+url;
	}