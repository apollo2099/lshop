<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">

//校验上下区间值是否合理
function checkInterval(){
    var upInterval=$("#upInterval").val();
	var downInterval=$("#downInterval").val();
	if(parseInt(upInterval)>parseInt(downInterval)){
	   alertMsg.error("请输入正确的值，上区间不能大于下区间的值!");
	   return false;
	}
	return true;
}

//提交表单
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}

	//判断上下区间值是否合理
    if(!checkInterval()){
       return false;
    }
	
	//提交表单请求数据
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		   callback(json);
		},
		error: DWZ.ajaxError
	});
	return false;
}
</script>

<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvProductLadderAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="lvProductLadder.productCode" value="${lvProductLadder.productCode }">
			<input type="hidden" name="lvProductLadder.storeId" value="${lvProductLadder.storeId}">
  		    <div class="pageFormContent" layoutH="56">
				<dl>
				<dt>单价：</dt>
					<dd>
                    <input name="lvProductLadder.price" type="text" size="32" maxlength="7" class="required numberNew" />
					</dd>				  
				</dl>
				<dl>
				<dt>上区间：</dt>
				    <dd>
				     <input name="lvProductLadder.upInterval" type="text" size="32" maxlength="9" class="required digits" id="upInterval"/>
				    </dd>
				</dl>
				
				<dl >
				<dt>下区间：</dt>
					<dd>
					<input name="lvProductLadder.downInterval" type="text" size="32" maxlength="9" class="required digits" id="downInterval" onblur="checkInterval()"/>
					</dd>				  
				</dl>
			
		</div>
	
		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
</form>
</div>
</div>
