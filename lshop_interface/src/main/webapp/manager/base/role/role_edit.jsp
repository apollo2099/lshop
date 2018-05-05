<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseRoles!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.rel" value="${json.navTabId }" />
		<input type="hidden" name="baseRoles.id" value="${baseRoles.id }" />
		<input type="hidden" name="baseRoles.version" value="${baseRoles.version }" />
		<input type="hidden" name="baseRoles.roleCode" value="${baseRoles.roleCode }" />
		<input type="hidden" name="baseRoles.status" value="${baseRoles.status }" />
		<input type="hidden" name="baseRoles.createdBy" value="${baseRoles.createdBy }" />
		<input type="hidden" name="baseRoles.createdDate" value="${baseRoles.createdDate }" />
		<input type="hidden" name="baseRoles.departmentCode" value="${baseRoles.departmentCode}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名称：</label>
				<input name="baseRoles.roleName" class="required" type="text" size="30"  maxlength="255" alt="角色名称" value="${baseRoles.roleName}"/>
			</p>
			<p>
				<label>角色描述：</label>
				<input name="baseRoles.roleDesc" class="required" type="text" size="30" maxlength="255" alt="角色描述" value="${baseRoles.roleDesc}"/>
			</p>
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
