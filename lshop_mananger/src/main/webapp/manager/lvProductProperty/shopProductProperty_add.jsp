<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	var blogContent=$("#blogContent").val()	
	if(blogContent==null||blogContent.length<=0){
		alertMsg.error('请输入产品扩展属性内容!');
		return false;
	}
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
			action="lvProductPropertyAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="lvProductProperty.productCode" value="${lvProductProperty.productCode }">
			<input type="hidden" name="lvProductProperty.storeId" value="${lvProductProperty.storeId }">
  		    <div class="pageFormContent" layoutH="56">
				<dl>
				<dt>属性标题：</dt>
				    <dd>
				     <input name="lvProductProperty.title" type="text" size="32" maxlength="100" class="required" />
				    </dd>
				</dl>
				
				<dl class="nowrap">
				<dt>属性内容：</dt>
					<dd>
						<textarea class="editor" id="blogContent" name="lvProductProperty.content" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=product" upImgExt="jpg,jpeg,gif,png" rows="20" cols="80 "></textarea>
					</dd>				  
				</dl>
				<dl>
				<dt>排序：</dt>
					<dd>
                    <input name="lvProductProperty.sortId" type="text" size="32" maxlength="4"  class="required digits"/>
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
