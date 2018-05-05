<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseResources!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.rel" value="${json.navTabId }" />
		<input type="hidden" name="baseResources.id" value="${baseResources.id }" />
		<input type="hidden" name="baseResources.version" value="${baseResources.version }" />
		<input type="hidden" name="baseResources.resourceCode" value="${baseResources.resourceCode }" />
		<input type="hidden" name="baseResources.resourceType" value="${baseResources.resourceType }" />
		<input type="hidden" name="baseResources.status" value="${baseResources.status }" />
		<input type="hidden" name="baseResources.createdBy" value="${baseResources.createdBy }" />
		<input type="hidden" name="baseResources.createdDate" value="${baseResources.createdDate }" />
		<input type="hidden" name="baseResources.menuCode" value="${baseResources.menuCode }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>资源名称：</label>
				<input name="baseResources.resourceName" class="required" type="text" size="30"  maxlength="255" alt="[菜单名称][资源名称][功能]" value="${baseResources.resourceName}"/>
			</p>
			<p>
				<label>资源描述：</label>
				<input name="baseResources.resourceDesc" class="required" type="text" size="30" maxlength="255" alt="请输入资源描述" value="${baseResources.resourceDesc}"/>
			</p>
			<p>
				<label>资源标识：</label>
				<input name="baseResources.resourceString" class="required alphanumeric" type="text" size="30" maxlength="255" alt="请输入唯一的资源标识" value="${baseResources.resourceString}"/>
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
