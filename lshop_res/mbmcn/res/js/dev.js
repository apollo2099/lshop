//main
$(function(){
	//placeholder hook,form submit
	$('form').on('focus', 'input', function(e){
		$(this).attr('placeholder', '');
	}).on('blur','input', function(e){
		$(this).trigger('valiField');
	}).on('required','input', function(e,fname){
		errorTip(this, fname+'不能为空');
	}).on('min', 'input', function(e,fname,min){
		errorTip(this, fname+'不能少于'+min+'位字符');
	}).on('max', 'input', function(e,fname,max){
		errorTip(this, fname+'不能多于'+max+'位字符');
	}).on('reg', 'input', function(e,fname,txt){
		errorTip(this, fname+txt);
	}).on('success', 'input', function(e){
		successTip(this);
	}).submit(function(e){
		$('input:visible').trigger('valiField');
		if(window.beforeFormSubmit){
			//自定义调用函数
			beforeFormSubmit(this);
		}
		if($(this).find('.error:visible').length>0) {
			e.preventDefault();
			$(this).find('.error:visible:first').focus();
			return false;
		}
		//防止订单重复提交
		$(this).unbind('submit').submit(function(e){return false;});
		return true;
	});
	//查看更多分页加载器
	$('.c-pager .more').click(function(e){
		var $n = $(this), $t = $n.parents('.c-pager');
		var next = parseInt($n.attr('nextpage')), total = parseInt($t.attr('totalpage'));
		var build = $n.attr('build');
		var html = window[build](next);
		$(html).insertBefore($n);
		if(next +1 >total){
			$n.hide();
		} else {
			$n.attr('nextpage', next +1 );
		}
	});
});



/**
 * 限制textarea输入字数 调用举例：textareaDisplayValidator($('#SMS_CONTENT'),20,'<s:text
 * name="validator.SMS_CONTENT.onfocus"/>');
 * 
 * @param textArea，输入文字内容的文本域对象
 * @param total，最大输入字数
 * @param message，输入字数超过最大限制的提示消息
 */
function textareaMaxValidator(textArea, total, message) {
	if (/msie/.test(navigator.userAgent.toLowerCase())) { // IE浏览器
		$(textArea).unbind("propertychange").bind("propertychange",
				function(e) {
					e.preventDefault();
					textareaMaxProc(textArea, total, message);
				});
	} else { // ff浏览器
		$(textArea).unbind("input").bind("input", function(e) {
			e.preventDefault();
			textareaMaxProc(textArea, total, message);
		});
	}
}

function textareaMaxProc(textArea, total, message) {
	var max;
	max = total;
	if ($(textArea).val().length > max) {
		$(textArea).val($(textArea).val().substring(0, max));
		alert(message);
	}
}

/**
 * 弹出对话框居中及遮罩
 * @param obj jquery object
 */
function center(obj) {
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width()) / 2;
	var objTop = (screenHeight - obj.height()) / 2;
	var srollTop = $(document).scrollTop();
	obj.css({
		left : objLeft + "px",
		top : objTop + srollTop + "px"
	});
	obj.show();
	$(".mark1").show();

	$(window).resize(function() {
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width()) / 2;
		var objTop = (screenHeight - obj.height()) / 2;
		var srollTop = $(document).scrollTop();
		obj.css({
			left : objLeft + "px",
			top : objTop + srollTop + "px"
		});
	});
	$(window).scroll(function() {
		var screenWidth = $(window).width(), screenHeight = $(window).height();
		var objLeft = (screenWidth - obj.width()) / 2;
		var objTop = (screenHeight - obj.height()) / 2;
		var srollTop = $(document).scrollTop();
		obj.css({
			left : objLeft + "px",
			top : objTop + srollTop + "px"
		});
	});
}
/**
 * 隐藏对话框居中及遮罩
 * @param $obj
 */
function uncenter($obj){
	$obj.hide();
	$(".mark1").hide();
	$(window).unbind("scroll");
	$(window).unbind("resize");
}
/**
 * 表单输入域提示错误信息
 * @param field
 * @param text
 */
function errorTip(field, text){
	var tx = "";
	var $f = $(field);
	$f.addClass('error');
	if(text){
		tx = text;
	}
	$t = $f.next();
	$t.find('span').text(tx);
	$t.show();
}
/**
 * 域校验成功操作
 * @param field
 */
function successTip(field){
	var $f = $(field);
	$f.removeClass('error');
	$f.next().hide();
}
/**
 * 表单输入域校验,先校验长度,再校验正则
 * @param event
 * id: 标识
 * tip: 提示语
 * reg: 正则表达式
 * max: 最大长度
 * min: 最小长度
 * txt: 验证错误信息
 * beforeVali: 准备回调
 * callback: 通过校验回调
 * @returns {Boolean}
 */
function valiField(event){
	var $f = $(event.target), val = $.trim($f.val());
	val = val ? val : '';
	//去除输入域前后空白, 设置域值
	if("INPUT" == $f.get(0).tagName && ("text" == $f.attr('type') || "password" == $f.attr('type'))){
		$f.val(val);
	}
	if($f.is(':hidden')){
		//若是隐藏元素,则直接通过
		return true;
	}
	//避免输入域的值是提示语
	if(event.data.tip && event.data.tip == val){
		$f.focus();
		val = $.trim($f.val());
	}
	//检验
	if(event.data.beforeVali){
		eval(event.data.beforeVali+"('"+val+"')");
	}
	//清除之前判断
	$f.removeClass('error');
	//必填校验
	var required = event.data.required;
	if(required && val.length == 0){
		$f.trigger('required', event.data.name);
		return false;
	}
	//长度检验器
	var max = event.data.max, min = event.data.min ? event.data.min : 0;
	if(min && min > val.length){
		$f.trigger('min', [event.data.name, min]);
		return false;
	}
	if(max && val && max<val.length){
		$f.trigger('max', [event.data.name, max]);
		return false;
	}
	//正则校验
	var reg = new RegExp(event.data.reg);
	if(val && !reg.test(val)){
		$f.trigger('reg', [event.data.name, event.data.txt]);
		return false;
	}
	//回调方法
	if(event.data.callback){
		eval(event.data.callback+"('"+val+"')");
	}
	if(!$f.hasClass('error')){
		$f.trigger('success');
		return true;
	}
}
/**
 * 根据国家加载省
 * 使用前要引入area_cn.js文件
 * @param country
 */
function loadProvince(country){
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==country){
				if(provinceObj[1].length>=1){
					//list province
					$('#provinceName').hide();
					$('#c_province').show();
					//input city
					$('#cityName').show();
					$('#c_city').hide();
					var html = '';
					$.each(provinceObj[1],function(n,cityObj) {
						html += '<option value="'+cityObj[0].id+'">'+cityObj[0].nameen+'</option>';
					});
					$('#c_province').empty().append(html).trigger('change');
				}else{
					//input province
					$('#provinceName').show();
					$('#c_province').hide();
					//input city
					$('#cityName').show();
					$('#c_city').hide();
				}
			}
		});	
	}
}
/**
 * 根据省加载市
 * 使用前要引入area_cn.js文件
 * @param country
 */
function loadCity(province){
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==$("#c_contry").val()){
				if(provinceObj[1].length>=1){
					$.each(provinceObj[1],function(n,cityObj) {
						if(cityObj[0].id==province){
							if(cityObj[1].length>=1){
								//list city
								$('#cityName').hide();
								$('#c_city').show();
								var html = '';
								$.each(cityObj[1],function(n,city) {
									html += '<option value="'+city.id+'">'+city.nameen+'</option>';
								});
								$('#c_city').empty().append(html).trigger('change');
							}else{
								//input city
								$('#cityName').show();
								$('#c_city').hide();
							}
						}
					});
				}
				
			}
		});	
	}
}