// 手机号码验证 
jQuery.validator.addMethod("mobile", function(value, element) { 
var length = value.length; 
var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/ 
return this.optional(element) || (length == 11 && mobile.test(value)); 
}, "手机号码格式错误"); 

// 电话号码验证 
jQuery.validator.addMethod("phone", function(value, element) { 
var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/; 
return this.optional(element) || (tel.test(value)); 
}, "电话号码格式错误"); 

// 邮政编码验证 
jQuery.validator.addMethod("zipCode", function(value, element) { 
var tel = /^[0-9]{6}$/; 
return this.optional(element) || (tel.test(value)); 
}, "邮政编码格式错误"); 

// QQ号码验证 
jQuery.validator.addMethod("qq", function(value, element) { 
var tel = /^[1-9]\d{4,10}$/; 
return this.optional(element) || (tel.test(value)); 
}, "qq号码格式错误"); 

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) { 
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "Ip地址格式错误"); 

// 字母和数字的验证 
jQuery.validator.addMethod("chrnum", function(value, element) { 
var chrnum = /^([a-zA-Z0-9]+)$/; 
return this.optional(element) || (chrnum.test(value)); 
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)"); 

// 中文的验证 
jQuery.validator.addMethod("chinese", function(value, element) { 
var chinese = /^[\u4e00-\u9fa5]+$/; 
return this.optional(element) || (chinese.test(value)); 
}, "只能输入中文"); 

// 下拉框验证 
$.validator.addMethod("selectNone", function(value, element) { 
return value == "请选择"; 
}, "必须选择一项"); 

// 字节长度验证 
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) { 
var length = value.length; 
for (var i = 0; i < value.length; i++) { 
if (value.charCodeAt(i) > 127) { 
length++; 
} 
} 
return this.optional(element) || (length >= param[0] && length <= param[1]); 
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));

//MSN的验证 
jQuery.validator.addMethod("msn", function(value, element) { 
var msn = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
return this.optional(element) || (msn.test(value)); 
}, "msn格式不正确"); 

//含有特殊字符
jQuery.validator.addMethod("isChineseChar", function(value, element) { 
	var isChineseChar = /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
	return this.optional(element) || !(isChineseChar.test(value)); 
}, "不能含有特殊字符"); 


//昵称检查
jQuery.validator.addMethod("isUnlawful", function(value, element) { 
	var isUnlawful = /[^a-zA-Z0-9\_\u4e00-\u9fa5]/; 
	return this.optional(element) || !(isUnlawful.test(value)); 
}, "昵称不能使用非法字符！"); 

// 不能为中文
jQuery.validator.addMethod("isNotChinese", function(value, element) { 
var chinese = /[\u4E00-\u9FA5]/i; 
return this.optional(element) || !(chinese.test(value)); 
}, "不能输入中文"); 
