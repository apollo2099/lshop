<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	var contentId=$("#contentId").val()	
	if(contentId==null||contentId.length<=0){
		alertMsg.error('请输入发送邮件的内容!');
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
		      action="lvOrderAction!doNotice.action" 
		      class="pageForm required-validate" 
		      onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="userEmail" value="${lvOrder.userEmail}"/>
			<input type="hidden" name="lvOrder.storeId" value="${lvOrder.storeId}"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>用户Email：</label>
							${lvOrder.userEmail}
						</p>
						<p>
							<label>收货人姓名 ：</label>
							${lvOrderAddress.relName}
						</p>
							<dl class="nowrap">
					<dd>
						<hr width="1000">
					</dd>				  
				</dl>
				<dl  class="nowrap">
					<dt>邮件标题：</dt>
					<dd>
						<input type="text" name="title" value="" size="50" class="required">
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>邮件内容：</dt>
					<dd><textarea class="editor" id="contentId" name="content"  upImgUrl="/manager/res/upload.action?dir=/images" upImgExt="jpg,jpeg,gif,png" rows="20" cols="90" ></textarea></dd>				  
				</dl>			
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
</div>
</div>