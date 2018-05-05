<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="resManagerAction!saveFile.action?json.navTabId=${json.navTabId}&storeFlag=${storeFlag }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>名称：</label>
							<input name="file" type="text" size="30" maxlength="32" class="required"/>
						</p>
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