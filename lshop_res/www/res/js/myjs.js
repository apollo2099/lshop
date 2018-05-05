/*
 * DOC
 * ①当返回值为true且②
 * ②当使用了msg参数时，将会在id元素后面输出msg（如果指定了消息盒（即msgboxId不为空或assignMsgbox(id)），msg将显示在指定消息盒中）
 * ③msg和msgboxId为可选参数，当msgboxId不为空时，必须确保msg不为空
 * 
 * js.assert.msgbox.setStyle(style)					设置消息盒样式。style(css style)
 * js.assert.msgbox.setPosition(position)			设置消息盒的与效验元素相对的位置，position('top', 'bottom', 'right')，默认为right
 * js.assert.msgbox.setMarginTop(value)				设置消息盒上边距
 * js.assert.msgbox.setMarginBottom(value)			设置消息盒下边距
 * js.assert.msgbox.setMarginLeft(value)			设置消息盒左边距
 * js.assert.msgbox.setMarginRight(value)			设置消息盒右边距
 * js.assert.assignMsgbox('id')						使用指定的HTML元素作为消息盒显示消息内容。此时setStyle和setPosition函数对msgbox无效；	
 * js.assert.revokeMsgbox()							取消指定的消息，恢复默认。
 * js.assert.isNull(id, msg, msgboxId)							①③判断id的value是否为空，如果为空返回true，否则返回false。	
 * js.assert.isNotChecked(name, msg, msgboxId)					①③判断所有name = 参数name的单选、复选元素是否其中有选中，没有返回true，否则返回false。如果参数指定了msgboxId，提示信息则显示在msgboxId元素中。	
 * js.assert.isIllegalLength(id, condition, msg, msgboxId)		①③判断id的value.length是否合法，如果value.length在min至max范围，则返回true，否则返回false。
 * js.assert.isNotEquals(id, eqid, msg, msgboxId)				①③比较id的value跟eqid的value是否相同，不相同返回true，否则返回false。
 * js.assert.isNotNumber(id, msg, msgboxId)						①③判断id的value是否为数字，不是返回true，否则返回false。
 * js.assert.isNotEdh(id, msg, msgboxId)						①③判断id的value是否为字母，不是返回true，否则返回false。
 * js.assert.isNotChinese(id, msg, msgboxId)					①③判断id的value是否为汉字，不是返回true，否则返回false。
 * js.assert.isNotEmail(id, msg, msgboxId)						①③判断id的value是否为Email格式，不是返回true，否则返回false。
 * 
 * js.onlyNumber(e, msg, msgboxId)			②③只能输入数字，e为固定值this，如果e.value含有非数字则会清除非数字部分
 * js.onlyEdh(e, msg, msgboxId)				②③只能输入字母，e为固定值this，如果e.value含有字母字则会清除非字母部分
 * js.onlyChinese(e, msg, msgboxId)			②③只能输入汉字，e为固定值this，如果e.value含有非汉字则会清除非汉字部分
 * js.sleep(msel)			线程暂停，msel暂停的时间(以毫秒为单位)。	（注：这里只是模拟线程暂停，让当前线程去做别的事情来达到暂停效果）
 * 
 * 
 * 
 * 文本框中添加“dvalue”属性，会有默认值、获得光标、失去光标的效果
 * 
 */

// 扩展字符串的trim方法
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}
}

$(function(){
	initInputDefaultValue();
	initSelectCheckboxGroup();
	initLenghtLimit();
});

window["js"] = {};
window["js"]["assert"] = {};
window["js"]["assert"]["msgbox"] = {};

window["js"]["openDialog"] = openDialog;
window["js"]["closeDialog"] = closeDialog;
window["js"]["setCookie"] = setCookie;
window["js"]["getCookie"] = getCookie;

var boxflag = "_msgbox";
var assert_msgbox = "";
var msgbox_style = "";		// 有效值(css style)
var msgbox_position = "";	// 有效值('top', 'bottom')
var msgbox_marginTop = "";
var msgbox_marginBottom = "";
var msgbox_marginLeft = "";
var msgbox_marginRight = "";

window["js"]["assert"]["setBoxPosition"] = function(position) {
	msgbox_position = position;
	return js.assert;
} 
window["js"]["assert"]["msgbox"]["setPosition"] = function(position) {
	msgbox_position = position;
}  
window["js"]["assert"]["msgbox"]["setStyle"] = function(style) {
	msgbox_style = style;
}
window["js"]["assert"]["msgbox"]["setMarginTop"] = function(value) {
	msgbox_marginTop = value;
} 
window["js"]["assert"]["msgbox"]["setMarginBottom"] = function(value) {
	msgbox_marginBottom = value;
} 
window["js"]["assert"]["msgbox"]["setMarginLeft"] = function(value) {
	msgbox_marginLeft = value;
} 
window["js"]["assert"]["msgbox"]["setMarginRight"] = function(value) {
	msgbox_marginRight = value;
}
var assignMsgbox = window["js"]["assert"]["assignMsgbox"] = function(id) {
	assert_msgbox = id;
}
var revokeMsgbox = window["js"]["assert"]["revokeMsgbox"] = function() {
	assert_msgbox = "";
}

window["js"]["assert"]["isNull"] = function(id, msg, msgboxId) {
	var e = get(id);
	if (e != null) {
		var v = e.value.trim();
		var dv = $(e).attr("dvalue");
		if (v == "" || v == dv) {
			createMessage(id, msg, msgboxId);
			//try {e.focus();} catch(ex){}
			return true;
		}
		removeMessage(id, msgboxId);
		return false;
	}
}
window["js"]["assert"]["isNotChecked"] = function(name, msg, msgboxId){
	var checked = false;
	var radios = $("input:[name="+ name +"]");
	var id;
	for (var i = 0; i < radios.length; i++) {
		if (i == radios.length - 1) {
			if (radios[i].id == "") {
				radios[i].id = radios[i].name + i;
			}
			id = radios[i].id;
		}
		if (radios[i].checked) {
			checked = true;
		}
	}
	if (!checked) {
		createMessage(id, msg, msgboxId);
		return true;
	}
	removeMessage(id, msgboxId);
	return false;
}
window["js"]["assert"]["isIllegalLength"] = function(id, condition, msg, msgboxId) {
	var e = get(id);
	if (e != null) {
		var flag = false;
		var len = e.value.length;
		var c = condition.replace(/(\s*)/g, "");
		var arr1 = c.split("&&");
		var arr2 = c.split("||");
		if (arr1.length > 1) {
			var f1 = lengthCompare(len, arr1[0]);
			var f2 = lengthCompare(len, arr1[1]);
			flag = f1 && f2;
		} else if (arr2.length > 1) {
			var f1 = lengthCompare(len, arr2[0]);
			var f2 = lengthCompare(len, arr2[1]);
			flag = f1 || f2;
		} else {
			flag = lengthCompare(len, condition);
		}
		if (flag) {
			createMessage(id, msg, msgboxId);
			//e.select();
			return true;
		}
		removeMessage(id, msgboxId);
		return false;
	}
}
function lengthCompare(len, condition) {
	var flag = false;
	var s1 = condition.substr(0, 1); 
	var s2 = condition.substr(0, 2);
	var r1 = condition.substr(1);
	var r2 = condition.substr(2);
	
	if (s2 == "==") {
		flag = len == r2;
	} else if (s2 == ">=") {
		flag = len >= r2;
	} else if (s2 == "<=") {
		flag = len <= r2
	} else if (s1 == ">") {
		flag = len > r1;
	} else if (s1 == "<") {
		flag = len < r1;
	}
	return flag;
}
window["js"]["assert"]["isNotEquals"] = function(id, eqid, msg, msgboxId) {
	var v1 = get(id).value.trim();
	var v2 = get(eqid).value.trim();
	if (v1 != v2) {
		createMessage(eqid, msg, msgboxId);
		//get(eqid).select();
		return true;
	}
	removeMessage(eqid, msgboxId);
	return false;
}
window["js"]["assert"]["isNotNumber"] = function(id, msg, msgboxId) {
	var e = get(id);
	var v = e.value.trim();
	var pass = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(v);
	if (!pass) {
		createMessage(id, msg, msgboxId);
		//e.select();
		return true;
	}
	removeMessage(id, msgboxId);
	return false;
}
window["js"]["assert"]["isNotEdh"] = function(id, msg, msgboxId) {
	var regExp = /^[a-zA-Z]+$/;
	var e = get(id);
	var v = e.value.trim();
	if (!regExp.test(v)) {
		createMessage(id, msg, msgboxId);
		//e.select();
		return true;
	}
	removeMessage(id, msgboxId);
	return false;
}
window["js"]["assert"]["isNotChinese"] = function(id, msg, msgboxId) {
	var regExp = /^[\u4e00-\u9faf]+$/;
	var e = get(id);
	var v = e.value.trim();
		if (!regExp.test(v)) {
			createMessage(id, msg, msgboxId);
			//e.select();
			return true;
		}
	removeMessage(id, msgboxId);
	return false;
}
window["js"]["assert"]["isNotEmail"] =  function(id, msg, msgboxId) {
	var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var e = get(id);
	var v = e.value.trim();
	if (!regExp.test(v)) {
		createMessage(id, msg, msgboxId);
		//e.select();
		return true;
	}
	removeMessage(id, msgboxId);
	return false;
}
window["js"]["onlyNumber"] = function(e, msg, msgboxId) {
	var regExp = /[\D]+/;
	var v = e.value;
	if (regExp.test(v)) {
		e.value = v.replace(regExp, "");
		if (msg != undefined && e.id != "") {
			createMessage(e.id, msg, msgboxId);
			setTimeout(function(){
				removeMessage(e.id, msgboxId);				
			}, 2000);
		}
	}
}
window["js"]["onlyEdh"] = function(e, msg, msgboxId) {
	var regExp = /[^a-zA-Z]+/;		// 非字母
	var v = e.value;
	if (regExp.test(v)) {
		e.value = v.replace(regExp, "");
		if (msg != undefined && e.id != "") {
			createMessage(e.id, msg, msgboxId);
			setTimeout(function(){
				removeMessage(e.id, msgboxId);						   
			}, 2000);
		}
	}
}
window["js"]["onlyChinese"] = function(e, msg, msgboxId) {
	var regExp = /[^\u4e00-\u9faf]+/;	// 非中文
	var v = e.value;
	if (regExp.test(v)) {
		e.value = v.replace(regExp, "");
		if (msg != undefined && e.id != "") {
			createMessage(e.id, msg, msgboxId);
			setTimeout(function(){
				removeMessage(e.id, msgboxId);						   
			}, 2000);
		}
	}
}
window["js"]["sleep"] = function sleep(msel) {
	var date = new Date();
	var now = date.getTime();
	var end = now + msel;
	while (now < end) {
		var newTime = new Date().getTime();
		now = newTime;
		newTime = null; 
	}
}

function openDialog(targetId) {
	var target = $("#"+targetId);
	var scrollTop = 0;
	var scrollLeft = 0;
	if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
		scrollLeft = document.documentElement.scrollLeft;
	} else if (document.body) {
		scrollTop = document.body.scrollTop;
		scrollLeft = document.body.scrollLeft;
	}
	var bgdiv = '<div id="dialog_bg_div" style="background-color:#000000; width:100%; height:100%; position:absolute; z-index: 10000; filter:alpha(opacity=50);opacity:0.3; -moz-user-select: none;" onselectstart="return false;"></div>';
	$(bgdiv).appendTo("body");
	$("#dialog_bg_div").css({"top":scrollTop+"px"});
	
	var top = target.css("top");
	var left = target.css("left");
	
	var clientHeight = document.documentElement.clientHeight;
	var paddingTop = parseFloat(target.css("padding-top").replace("px", ""));
	var paddingBottom = parseFloat(target.css("padding-bottom").replace("px", ""));
	var targetHeight = target.height() + paddingTop + paddingBottom;
	top = (clientHeight - targetHeight) / 2 + scrollTop;
	
	var clientWidth = document.documentElement.clientWidth;
	var paddingLeft = parseFloat(target.css("padding-left").replace("px", ""));
	var paddingRight = parseFloat(target.css("padding-right").replace("px", ""));
	var targetWidth = target.width() + paddingLeft + paddingRight;
	left = (clientWidth - targetWidth) / 2 + scrollLeft;
	
	var zindex = $("#dialog_bg_div").css("z-index") + 1;
	target.css({"position":"absolute", "top":top+"px", "left":left+"px", "z-index":zindex});
	target.show();
	$("body").css({"overflow":"hidden"});
	
	
	/*	窗口移动功能（半成品）
	var moveTarget = target.find("div:first");
	var x,y,x1,y1,x2,y2;
	
	moveTarget.mousedown(function(e){
		moveTarget.css("cursor", "move");
		x1 = e.clientX;
		y1 = e.clientY;
		
		var t = target.css("top").replace("px", "");
		var l = target.css("left").replace("px", "");
		t = parseInt(t);
		l = parseInt(l);
		
		//moveTarget.text("x1 = "+ x1 +" --- y1 = "+y1);
		
		moveTarget.mousemove(function(e2){
			x2 = e2.clientX;
			y2 = e2.clientY;
			x = x2 - x1 + l;
			y = y2 - y1 + t;
			
			var leftLimit = clientWidth + scrollLeft - targetWidth;
			var topLimit = clientHeight + scrollTop - targetHeight;
			
			if (x < 0) {x = 0;}
			if (x > leftLimit) {x = leftLimit;}
			if (y < 0) {y = 0;}
			if (y > topLimit) {y = topLimit;}
			
			//moveTarget.text("x = "+ x +" --- y = "+ y);
			
			target.css({"left":x, "top":y});
		});
		moveTarget.mouseup(function(){
			moveTarget.unbind("mousemove");
			moveTarget.css("cursor", "auto");
		});
		moveTarget.mouseout(function(){
			moveTarget.unbind("mousemove");
			moveTarget.css("cursor", "auto");
		});
	});
	*/
}

function closeDialog(targetId) {
	$("#"+targetId).css({
		"top": "auto",
		"left": "auto"
	});
	$("#"+targetId).hide();
	$("#dialog_bg_div").remove();
	$("body").css({"overflow-y":"scroll"});
}

function createMessage(id, msg, msgboxId) {
	if (msg == undefined) return;
	removeMessage(id);
	if (msgboxId != undefined) {
		cee2(msgboxId, msg);
	} else {
		assert_msgbox == "" ? cee1(id, msg) : cee2(assert_msgbox, msg);
	}
}
function removeMessage(id, msgboxId) {
	if (msgboxId != undefined) {
		ree2(msgboxId);
	} else {
		assert_msgbox == "" ? ree1(id) : ree2(assert_msgbox);
	}
}

/*
function cee1(id, msg) {
	var e = get(id);
	var width = e.offsetWidth;
	var height = e.offsetHeight;
	var top = $(e).position().top;
	var left = $(e).position().left + 5;
	switch (msgbox_position) {
	case "top":
		top = top - height;
		break;
	case "bottom":
		top = top + height;
		break;
	default:
		left = left + width;
		break;
	}
	top = msgbox_marginTop == "" ? top : top + msgbox_marginTop;
	top = msgbox_marginBottom == "" ? top : top - msgbox_marginBottom;
	left = msgbox_marginLeft == "" ? left : left + msgbox_marginLeft;
	left = msgbox_marginRight == "" ? left : left - msgbox_marginRight;
	var boxid = id + boxflag;
	var style = 'position:absolute;'+
				'z-index:999;'+
				'top:'+top+'px;'+
				'left:'+left+'px;'+
				'height:'+height+'px;'+
				'line-height:'+height+'px;'+
				'padding:0 3px;'+
				'background-color:#FFFFFF;'+
				//'opacity: 0.5;'+
				'color:#FF0000;'+
				'font-size: 10pt;';
	if (msgbox_style != "") {
		style += msgbox_style;
	}
	var msgbox = '<span id="'+boxid+'" style="'+ style +'">'+ msg +'</span>';
	//$(e).parent().append(msgbox);
	$(msgbox).appendTo("body").fadeIn(50);
}
*/

function cee1(id, msg) {
	var e = get(id);
	var width = e.offsetWidth;
	var height = e.offsetHeight;
	var top = $(e).offset().top;
	var left = $(e).offset().left + 5;
	switch (msgbox_position) {
	case "top":
		top = top - height;
		break;
	case "bottom":
		top = top + height;
		break;
	default:
		left = left + width;
		break;
	}
	top = msgbox_marginTop == "" ? top : top + msgbox_marginTop;
	top = msgbox_marginBottom == "" ? top : top - msgbox_marginBottom;
	left = msgbox_marginLeft == "" ? left : left + msgbox_marginLeft;
	left = msgbox_marginRight == "" ? left : left - msgbox_marginRight;
	var boxid = id + boxflag;
	var style = 'height:'+height+'px;'+
				'line-height:'+height+'px;'+
				'padding:0 3px;'+
				'background-color:#FFFFFF;'+
				//'opacity: 0.5;'+
				'color:#FF0000;'+
				'font-size: 12px;'+
				'vertical-align: top;';
	if (msgbox_style != "") {
		style += msgbox_style;
	}
	var msgbox = '<span id="'+boxid+'" style="'+ style +'">'+ msg +'</span>';
	//var msgbox = '<span id="'+boxid+'" style="vertical-align: top; background:#fef2f2; height:25px; border:1px #ffb8b8 solid; padding:5px 10px; margin:10px auto 0 auto; color:#f40000;"><img src="/agent/images/usercenter_error.gif" width="18" height="19" />&nbsp;'+ msg +'</span>';
	
	ree1(id);
	$(e).after(msgbox);
}
function ree1(id) {
	var boxid = id + boxflag;
	var msgbox = get(boxid);
	if (msgbox != null) {
		$("#"+boxid).remove();
	}
}
function cee2(id, msg) {
	//var e = get(assert_msgbox);
	var e = get(id);
	if (e.style.color == "") {
		e.style.color = "#FF0000";
	}
	e.innerHTML = '<span style="color:#f40000;">'+ msg +'</span>';
	//e.innerHTML = '<span style="background:#fef2f2; height:25px; border:1px #ffb8b8 solid; padding:5px 10px; margin:10px auto 0 auto; color:#f40000;"><img src="/agent/images/usercenter_error.gif" width="18" height="19" />&nbsp;'+ msg +'</span>';
}
function ree2(id) {
	//get(assert_msgbox).innerHTML = "";
	get(id).innerHTML = "";
}


function get(id) {
	return document.getElementById(id);
}

/**
 * 初始化所有带有“dvalue”属性的文本框，添加默认值、onfocus事件和onblur事件
 */
function initInputDefaultValue() {
	$("body").focus();
	var ipts = $("input[type='text'][dvalue],textarea[dvalue]");
	ipts.each(function(){
		var ipt = $(this);
		if (ipt.val() == "") {
			var dvalue = ipt.attr("dvalue");
			ipt.css({"color": "#999999"});
			ipt.val(dvalue);
		}
	});
	ipts.focus(function(){
		var ipt = $(this);
		var value = ipt.val();
		var dvalue = ipt.attr("dvalue");
		if (value == dvalue) {
			ipt.val("");
			ipt.css({"color":"#000000"});
		}											 
	});
	ipts.blur(function(){
		var ipt = $(this);
		var value = ipt.val();
		var dvalue = ipt.attr("dvalue");
		if (value.trim() == "") {
			ipt.css({"color":"#999999"});
			ipt.val(dvalue);
		}
	});
}

/**
 * 清理默认值
 * @param {Object} ids
 */
function clearDefaultValue(ids) {
	if (ids == undefined) {
		var ipts = $("input[type='text'][dvalue],textarea[dvalue]");
		ipts.each(function(){
			var ipt = $(this);
			var value = ipt.val();
			var dvalue = ipt.attr("dvalue");
			if (value == dvalue) {
				ipt.val("");
			}
		});
	} else {
		for (var i = 0; i < ids.length; i++) {
			var ipt = $("#"+ids[i]);
			var value = ipt.val();
			var dvalue = ipt.attr("dvalue");
			if (value == dvalue) {
				ipt.val("");
			}
		}
	}
}


// 检测密码等级
function checkPasswordLevel(pwd) {
	var a = 0; b = 0; c = 0;
	for (var i = 0; i < pwd.length; i++) {
		var ch = pwd.charCodeAt(i);
		if ((48 <= ch && ch <= 57)) {//数字
			a = 1;
		} else if((65 <= ch && ch <= 90)||(97 <= ch && ch <= 122)) {//字母
            b = 1
		} else{//其它字符
            c = 1;
		}
	}
	return a + b + c;
}

/**
 * 获取select中的Text
 * @param {Object} target：id或元素自身
 */
function getSelectText(target) {
	var select = null;
	if (typeof(target) == "string") {
		select = $("#"+target);
	} else {
		select = $(target);
	}
	return select.find("option:selected").text();
}

// checkbox关联选择
function initSelectCheckboxGroup() {
	$("input:checkbox[group]").change(function(){
		var checked = this.checked;
		var child = $(this).attr("group");
		$("input:checkbox[name='"+ child +"']").each(function(){
			if (!this.disabled) {
				this.checked = checked;
			}
		});
		
	});
}

// 最大长度限制
function initLenghtLimit() {
	var ipts = $("input[maxlength],textarea[maxlength]");
	ipts.each(function(){
		var ipt = $(this);
		var max = $.trim(ipt.attr("maxlength"));
		if (!isNaN(max)) {
			$(this).keydown(function(){
				var value = ipt.val();
				if (value.length > max) {
					value = value.substring(0, max);
					ipt.val(value);
				}
			});
			this.onpropertychange = function() {
				ipt.keydown();
			}
		}
	});
}

/**
 * 设置cookie
 * @param {Object} key		cookie键
 * @param {Object} value	cookie值
 * @param {Object} params 参数集合
 */
function setCookie(key, value, params) {
	var cookie = key +"="+ escape(value);
	if (params != undefined) {
		var expires = params.expires;
		var path = params.path;
		var domain = params.domain;
		var secure = params.secure;
		if (expires != undefined) {
			var date = new Date();
			date.setTime(date.getTime() + expires);
			cookie += ";expires=" + date.toGMTString();
		}
		if (path != undefined) {
			cookie += ";path=" + path;
		}
		if (domain != undefined) {
			cookie += ";domain=" + domain;
		}
		if (secure != undefined) {
			cookie += ";secure";
		}
	}
	document.cookie = cookie;
}

/**
 * 获取cookie
 * @param {Object} key	要获取的cookie键
 */
function getCookie(key) {
	var cookies = unescape(document.cookie);
	cookies = cookies.split(";");
	for (var i = 0; i < cookies.length; i++) {
		var cookie = cookies[i];
		if (cookie != null && cookie != "" && cookie.indexOf("=") != -1) {
			var k = cookie.split("=")[0];
			if (k == key) {
				var v = cookie.split("=")[1];
				return v;
			}
		}
	}
}

// 不是数字返回true，否则返回false
function isNotNumber(num) {
	var regExp = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
	return !regExp.test(num);
}

// 扩展日期格式化方法	
Date.prototype.format = function(fmt) {  
  var o = {   
    "M+" : this.getMonth()+1,                 //月份    
    "d+" : this.getDate(),                    //日    
    "HH+" : this.getHours(),                  //小时    
    "m+" : this.getMinutes(),                 //分    
    "s+" : this.getSeconds(),                 //秒    
    "q+" : Math.floor((this.getMonth()+3)/3), //季度    
    "s"  : this.getMilliseconds()             //毫秒    
  };   
  if(/(y+)/.test(fmt)) {
  	fmt = fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  }   
  for(var k in o) {
    if(new RegExp("("+ k +")").test(fmt)) { 
  		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
  }   
  return fmt;   
} 

