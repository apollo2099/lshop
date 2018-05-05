/**
 * @requires jquery.validate.js
 * @author ZhangHuihua@msn.com
 */
(function($){
	if ($.validator) {
		$.validator.addMethod("alphanumeric", function(value, element) {
			return this.optional(element) || /^\w+$/i.test(value);
		}, "Letters, numbers or underscores only please");
		
		$.validator.addMethod("lettersonly", function(value, element) {
			return this.optional(element) || /^[a-z]+$/i.test(value);
		}, "Letters only please"); 
		
		$.validator.addMethod("phone", function(value, element) {
			return this.optional(element) || /^[0-9 \(\)]{7,50}$/.test(value);
		}, "Please specify a valid phone number");
		
		$.validator.addMethod("postcode", function(value, element) {
			return this.optional(element) || /^[0-9 A-Za-z]{5,20}$/.test(value);
		}, "Please specify a valid postcode");
		
		$.validator.addMethod("numberNew", function(value, element) {
			return this.optional(element) || (/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value)&&value>0);
		}, "请输入大于零的数");
		
		$.validator.addMethod("numberEqual", function(value, element) {
			return this.optional(element) || (/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value)&&value>=0);
		}, "请输入大于等于零的数");
		$.validator.addMethod("digitsNoZore", function(value, element) {
			return this.optional(element) || (/^\d+$/.test(value)&&value>0);
		}, "请输入正整数");
		$.validator.addMethod("numberAPoint", function(value, element) {
			return this.optional(element) || (/^(\d+\.\d{1,1}|\d+)$/.test(value)&&value>0);
		}, "请输入小数点为一位的浮点数");
		$.validator.addMethod("numberToThree", function(value, element) {
			var flag=true;
			if(value.indexOf(".")!=-1){
				if(value.length-(value.indexOf(".")+1)>3){
					flag=false;
				}
			}
			return this.optional(element) || flag;
		}, "不能超过3位小数点");
		
		
		$.validator.addMethod("date", function(value, element) {
			value = value.replace(/\s+/g, "");
			if (String.prototype.parseDate){
				var $input = $(element);
				var pattern = $input.attr('format') || 'yyyy-MM-dd HH:mm:ss';
	
				return !$input.val() || $input.val().parseDate(pattern);
			} else {
				return this.optional(element) || value.match(/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/);
			}
		}, "Please enter a valid date.");
		
		$.validator.addClassRules({
			date: {date: true},
			alphanumeric: { alphanumeric: true },
			lettersonly: { lettersonly: true },
			phone: { phone: true },
			postcode: {postcode: true},
			numberNew:{numberNew:true},
			numberEqual:{numberEqual:true},
			digitsNoZore:{digitsNoZore:true},
			numberAPoint:{numberAPoint:true}
		});
		$.validator.setDefaults({errorElement:"span"});
		$.validator.autoCreateRanges = true;
		
	}

})(jQuery);