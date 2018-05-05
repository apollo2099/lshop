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
	var bannerId=$("#bannerId").val()	
	if(bannerId==null||bannerId.length<=0){
		alertMsg.error('请输入banner属性内容!');
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
		<form method="post" action="lvBannerMngAction!edit.action?json.navTabId=${json.navTabId}"
				class="pageForm required-validate" 
				onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input name="lvBanner.id" type="hidden" value="${lvBanner.id}"/>
			<input name="lvBanner.createTime" type="hidden" value="${lvBanner.createTime}"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>key：</label>
							<s:select list="#{'':'==请选择==','BANNER_LOCATION_TOP':'导航页面位置','BANNER_LOCATION_DOWN':'店铺/活动显示位置'}" name="lvBanner.bannerKey" cssClass="required"></s:select>
						</p>
						<p>
							<label>标题：</label>
							<input name="lvBanner.title" type="text" size="30" value="${lvBanner.title}" maxlength="128" class="required"/>
						</p>
						<dl class="nowrap">
							<label>banner内容：</label>
							<dd>
								<textarea name="lvBanner.body" id="bannerId" upImgUrl="/manager/res/upload.action?dir=/images" upImgExt="jpg,jpeg,gif,png"  rows="16" cols="90" class="editor">${lvBanner.body}</textarea>
					    </dl>
					    <p><label>排序值</label><input type="text" name="lvBanner.orderValue" class="required digits" value="${lvBanner.orderValue}" maxlength="3"/></p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>