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
	var contentId=$("#contentId").val()	
	if(contentId==null||contentId.length<=0){
		alertMsg.error('请输入页面属性内容!');
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
			action="lvPageinfoAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);" >
			<input type="hidden" name="lvPageinfo.id" value="${lvPageinfo.id }">
		    <input type="hidden" name="lvPageinfo.code" value="${lvPageinfo.code }">
  		    <div class="pageFormContent" layoutH="56">
				<dl class="nowrap">
				<dt>属性标题：</dt>
				    <dd>
				     <input name="lvPageinfo.title" type="text" size="64" maxlength="64" class="required" value="${lvPageinfo.title }"/>
				    </dd>
				</dl>
				
				<dl class="nowrap">
				<dt>属性内容：</dt>
					<dd>
						<textarea class="editor" id="contentId" name="lvPageinfo.content" upImgUrl="/manager/res/upload.action?dir=/images" upImgExt="jpg,jpeg,gif,png" rows="20" cols="80 ">${lvPageinfo.content}</textarea>
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvPageinfo.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvPageinfo.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvPageinfo.modifyUserName"  readonly="true" value="${lvPageinfo.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvPageinfo.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvPageinfo.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
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
