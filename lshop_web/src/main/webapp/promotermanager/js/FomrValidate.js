
(function () { 
		 //公用属性
	var mobile = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/; //是否是手机号码
	var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//是否是email
	var patrn = /^(\+[0-9]{2,6}\-)?(0[0-9]{2,3}\-)?([0-9]{7,8})+(\-[0-9]{1,4})?$/;//电话号码
	/*********************绑定在window下的方法************************************/
	 

		//检查是否为空  value 要检查的值   msg 提示消息
	var isNULL = window["isNULL"] = function (value) {
		if (value == null || value.split(" ").length == value.length + 1) {
			return true;
		}
		return false;
	};
		//检查电子邮件格式
		//value 字符串对象
	var checkEmail = window["checkEmail"] = function (value) {
		if (email.test(value)) {
			return true;
		} else {
			return false;
		}
	};
		//检查是否是数字
		//obj 文本框对象
	var onNumber = window["onNumber"] = function (obj) {
		obj.value = obj.value.replace(/\D+/g, "");
	};
				
		//检查是否为正确电话号码,可以为空
		//value 要检查的电话号码, msn 提示消息
	var checkUserTel = window["checkUserTel"] = function (value, msn) {
		if (isNULL(value)) {
			return true;
		} else {
			if (!patrn.test(value)) {
				alert(msn);
				return false;
			}
		}
		return true;
	};

		//控制字符的数目
		//obj 控制的对象
		//字符数目
	var controlCharNum = window["controlCharNum"] = function (obj, num) {
		if (parseInt(obj.value.length) > parseInt(num)) {
			obj.value = obj.value.substring(0, parseInt(num));
		}
	};
	/*********************扩展现有js库************************************/

		 //扩展js本地库添加字符串的 trim方法
	if (!String.prototype.trim) {
		String.prototype.trim = function () {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		};
	}
	if (!String.prototype.lTrim) {
		String.prototype.lTrim = function () {
			return this.replace(/(^\s*)/g, "");
		};
	}
	if (String.prototype.rTrim) {
		String.prototype.rTrim = function () {
			return this.replace(/(\s*$)/g, "");
		};
	}

	/*********************建立自己的js库************************************/
	window["lshop"] = {};  //命名空间lshop(调用命名空间下的方法lshop.test())
	/***
		 * 兼容IE/FF的复制到剪贴板函数
		 */
	var copyToClipboard = window["lshop"]["copyToClipboard"] = function (txt) {
		if (window.clipboardData) {
			window.clipboardData.clearData();
			window.clipboardData.setData("Text", txt);
			alert("\u590d\u5236\u6210\u529f\uff01");
		} else {
			if (navigator.userAgent.indexOf("Opera") != -1) {
				window.location = txt;
			} else {
				if (window.netscape) {
					try {
						netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
					}
					catch (e) {
						alert("\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff01\n\u8bf7\u5728\u6d4f\u89c8\u5668\u5730\u5740\u680f\u8f93\u5165'about:config'\u5e76\u56de\u8f66\n\u7136\u540e\u5c06'signed.applets.codebase_principal_support'\u8bbe\u7f6e\u4e3a'true'");
					}
					var clip = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
					if (!clip) {
						return;
					}
					var trans = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
					if (!trans) {
						return;
					}
					trans.addDataFlavor("text/unicode");
					var str = new Object();
					var len = new Object();
					var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
					var copytext = txt;
					str.data = copytext;
					trans.setTransferData("text/unicode", str, copytext.length * 2);
					var clipid = Components.interfaces.nsIClipboard;
					if (!clip) {
						return false;
					}
					clip.setData(trans, null, clipid.kGlobalClipboard);
					alert("\u590d\u5236\u6210\u529f\uff01");
				}
			}
		}
	};
		//检查是否是整数
		//obj 文本框对象
	var onInteger = window["lshop"]["onInteger"] = function (obj) {
		var vaue = obj.value.replace(/\D+/g, "");
		obj.value = (vaue == 0 || vaue == "") ? "" : parseInt(vaue, 10);
	};
	
    var getCookieVal=window["lshop"]["getCookieVal"]=function(offset) {
		try {
			var endstr = document.cookie.indexOf(";", offset);
			if (endstr == -1) {
				endstr = document.cookie.length;
			}
			return unescape(decodeURI(document.cookie.substring(offset, endstr)));
		}
		catch (e) {
			return "";
		}
	}
	var getCookie=window["lshop"]["getCookie"]=function(name) {
		var arg = name + "=";
		var alen = arg.length;
		var clen = document.cookie.length;
		var i = 0;
		while (i < clen) {
			var j = i + alen;
			if (document.cookie.substring(i, j) == arg) {
				return getCookieVal(j);
			}
			i = document.cookie.indexOf(" ", i) + 1;
			if (i == 0) {
				break;
			}
		}
		return "";
	}
	var getCookieToJSON=window["lshop"]["getCookieToJSON"]=function(name) {
		var cookieValue=getCookie(name);
		if (cookieValue == null || ""==cookieValue || cookieValue.indexOf("*") < 0) {
			return "";
		}else{
		   var values=cookieValue.split("*");
		   var cookieJson={};
		   for(var i=0;i<values.length;i++){
		      var value=values[i];
		     if(value!=''&&value.indexOf("=") > 0){
		        cookieJson[value.split("=")[0]]=value.split("=")[1];
		     
		     }
		   }
		  return cookieJson;
		}
	}
	
   var setCookie=window["lshop"]["setCookie"]=function(name,value) {
		var argv = setCookie.arguments;
		var argc = setCookie.arguments.length;
		var expires=null;
		if(argc > 2){
		  var exp1 = new Date();
		  exp1.setTime(exp1.getTime() + argv[2]);
		  expires=exp1;
		}
		var path = (argc > 3) ? argv[3] : "/";
		var domain = (argc > 4) ? argv[4] : null;
		var secure = (argc > 5) ? argv[5] : false;
		document.cookie = name + "=" + encodeURI(value) + ((expires==null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
	}
	 var deleteCookie=window["lshop"]["deleteCookie"]=function(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
	}
	
	var openwin=window["lshop"]["openwin"]=function(obj) {
		/*-------------------------创建弹窗html-------------------*/
		var div = document.getElementById('win');
		if(!div){
		   div=document.createElement('div');
		   div.id='win';
		   document.body.appendChild(div);
		}
		div.innerHTML = '<div  style="z-index:10000;border-color:#666666;background-color: #fff;top:'+(document.body.scrollHeight-850)+'px;position: absolute;left:'+(window.screen.width/2-400)+'px"><iframe width="782px" frameborder="0" scrolling="no" height="430px" style="z-index:-100;position: absolute;"></iframe> <span style="display:block; width:782px; height:23px; background:url(/tvpad/images/TVpad/tanchu_bg.gif) repeat-x; text-align:center; padding-top:5px;color: #fff;">'+obj.title+'</span><div id="wininfo"><div style="border-color:#666666;width:480px; height:150px; padding-top:150px; padding-left:300px;background-color: #eeeeee;"><img src="/res/images/loading.gif" /><br /><br />正在加载中 请稍后</div></div></div><div id="winmask" style="background-color:#000000;width:'+document.body.clientWidth+'px;height:'+document.body.clientHeight+'px;position:absolute;top:0px;left:0px;text-align:center;z-index:9999;filter:alpha(opacity=10);opacity:0.1"></div>';
		$("#wininfo").load(obj.message);

		
	
	}
	var closewin=window["lshop"]["closewin"]=function() {
		$('#win').html('');
		
	}
   var	onshare=window["lshop"]["onshare"]=function(id,url){
		var href=encodeURIComponent(url||window.location.href),title='';
		 switch (id){
			   case 'Twitter':
			     url= "http://twitter.com/home?status="+title+"+"+href;
			     window.open(url,'_blank');
				 break;
			   case 'Myshare':
			     url= "http://myshare.url.com.tw/index.php?func=newurl&url="+href;
			     window.open(url,'_blank');
				 break;
			   case 'Myspace':
			     url= "http://www.jiathis.com/send/?webid=myspace&url="+href+"&title="+title+" "+href;
			     window.open(url,'_blank');
				 break;
			   case 'yahoo':
			     url= "http://compose.mail.yahoo.com/?To=&Subject="+title+href;
			     window.open(url,'_blank');
				 break;
			    case 'Buzz':
			     url= "http://www.google.com/buzz/post?url="+href;
			     window.open(url,'_blank');
				 break;
			 case 'MSN':
			     url= "http://profile.live.com/badge?url="+href;
			     window.open(url,'_blank');
				 break; 
			 case 'Fackbook':
			     url= "http://www.facebook.com/sharer.php?u="+href+"&t="+title;
			     window.open(url,'_blank');
				 break; 
			 case 'COPY':
			     copyToClipboard(decodeURIComponent(href));
				 break; 
		}
	}
 	var	copyToClipboard=window["lshop"]["copyToClipboard"]=function(txt){
			if(window.clipboardData)
			{
				window.clipboardData.clearData();		
				window.clipboardData.setData("Text",txt);
                alert("复制成功,请粘贴到你的QQ/MSN上推荐给你的好友！");
			}else if(navigator.userAgent.indexOf("Opera")!=-1)
			{
				window.location=txt;
			}else if(window.netscape)
			{
				try
				{
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}catch(e)
				{
				 var a=	prompt("你使用的是浏览器不支持给快捷键，请按下 Ctrl+C 复制代码到剪贴板",txt);
				  if(a!=null){
				     alert("复制成功,请粘贴到你的QQ/MSN上推荐给你的好友！");
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
				alert("复制成功,请粘贴到你的QQ/MSN上推荐给你的好友！");
			}
		}
})();
//图片延迟加载
if(jQuery){
(function (a) {
	a.fn.scrollLoading = function (b) {
		var c = {attr:"data-url"};
		var d = a.extend({}, c, b || {});
		d.cache = [];
		a(this).each(function () {
			var g = this.nodeName.toLowerCase(), f = a(this).attr(d.attr);
			if (!f) {
				return;
			}
			var h = {obj:a(this), tag:g, url:f};
			d.cache.push(h);
		});
		var e = function () {
			var f = a(window).scrollTop(), g = f + a(window).height();
			a.each(d.cache, function (k, l) {
				var m = l.obj, h = l.tag, j = l.url;
				if (m&&m.position) {
					post = m.position().top;
					posb = post + m.height();
					if ((post > f && post < g) || (posb > f && posb < g)) {
						if (h === "img") {
							m.attr("src", j);
						} else {
							m.load(j);
						}
						l.obj = null;
					}
				}
			});
			return false;
		};
		e();
		a(window).bind("scroll", e);
	};
})(jQuery);
}



