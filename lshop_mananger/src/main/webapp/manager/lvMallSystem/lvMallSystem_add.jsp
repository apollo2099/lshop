<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvMallSystemAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>商城体系名称：</label>
							<input name="lvMallSystem.mallSystemName" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>商城体系标示：</label>
							<input name="lvMallSystem.mallSystemFlag" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>商城体系域名后缀：</label>
							<input name="lvMallSystem.domainPostfix" type="text" size="30" maxlength="32" class="required"/>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>