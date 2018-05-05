<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
$(function(){
	$("#verify_select").change(function(){
		var value=$("#verify_select").val();
		if(value==2){
			$("#v_not_reason").css("display","block");
		}
		if(value==1){
			$("#v_not_reason").css("display","none");
		}
	});
});
</script>
<div class="page">
	<div class="pageContent">
		<form rel="" action="/manager/appMngAction!verify.action?json.navTabId=${json.navTabId}" 
		method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="app.id" value="${app.id }">
		<div class="pageFormContent" layoutH="60">
		<div class="" style="border-top-width: 0px;">
				<dl style="width: 100%;text-align: right;">
					<dt>状态：</dt>
					<dd>
						<select name="app.reviewStatus" style="width: 135px;" id="verify_select">
							<option value="1" selected="selected">审核通过</option>
							<option value="2">审核不通过</option>
						</select>
					</dd>				  
				</dl>
				<dl style="width: 100%;text-align: right;display: none;" id="v_not_reason">
					<dt>不通过原因：</dt>
					<dd>
				     <textarea name="app.reason" maxlength="200" style="height: 100px;width: 300px;"></textarea>
					</dd>				  
				</dl>
				
				
		</div>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
		</form>
		
		
	</div>
</div>
