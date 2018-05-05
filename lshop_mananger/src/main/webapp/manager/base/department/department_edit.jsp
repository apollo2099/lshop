<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="baseDepartment!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}">
		<input type="hidden" name="baseDepartment.id" value="${baseDepartment.id}">
		<input type="hidden" name="baseDepartment.version" value="${baseDepartment.version}">
		<input type="hidden" name="baseDepartment.departmentCode" value="${baseDepartment.departmentCode}">
		<input type="hidden" name="baseDepartment.departmentParent" value="${baseDepartment.departmentParent}">
		<input type="hidden" name="baseDepartment.status" value="${baseDepartment.status}">
		<input type="hidden" name="baseDepartment.createdBy" value="${baseDepartment.createdBy}">
		<input type="hidden" name="baseDepartment.createdDate" value="${baseDepartment.createdDate}">
		
			<div class="pageFormContent" layoutH="58">
				<p>
					<label>名称：</label>
					<input type="text" name="baseDepartment.departmentName" size="30" maxlength="150" class="required" value="${baseDepartment.departmentName }"/>
					
				</p>
				<p>
					<label>描述：</label>
					<input type="text" name="baseDepartment.departmentDesc" maxlength="250" size="30" value="${baseDepartment.departmentDesc }" />
					
				</p>
				<p>
					<label>排序：</label>
					<input type="text" name="baseDepartment.departmentDisorder" size="30" maxlength="150" class="required" value="${baseDepartment.departmentDisorder }"/>
					
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