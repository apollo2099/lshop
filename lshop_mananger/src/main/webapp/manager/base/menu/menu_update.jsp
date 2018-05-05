<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="baseMenu!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}">
		<input type="hidden" name="baseMenu.id" value="${baseMenu.id}">
		<input type="hidden" name="baseMenu.version" value="${baseMenu.version}">
		<input type="hidden" name="baseMenu.menuCode" value="${baseMenu.menuCode}">
		<input type="hidden" name="baseMenu.menuParent" value="${baseMenu.menuParent}">
		<input type="hidden" name="baseMenu.status" value="${baseMenu.status}">
		<input type="hidden" name="baseMenu.createdBy" value="${baseMenu.createdBy}">
		<input type="hidden" name="baseMenu.createdDate" value="${baseMenu.createdDate}">
		<input type="hidden" name="baseMenu.lastUpdatedBy" value="${sessionScope.USER.userCode}">
		
			<div class="pageFormContent" layoutH="58">
				<p>
					<label>名称：</label>
					<input type="text" name="baseMenu.menuName" size="30" maxlength="150" class="required" value="${baseMenu.menuName }"/>
					
				</p>
				<p>
					<label>URL：</label>
					<input type="text" name="baseMenu.menuUrl" maxlength="250" size="30" value="${baseMenu.menuUrl }" />
					
				</p>
				<p>
					<label>排序：</label>
					<input type="text" name="baseMenu.menuDisorder" size="30" maxlength="150" class="required" value="${baseMenu.menuDisorder }"/>
					
				</p>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>