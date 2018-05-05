<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseResources!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.rel" value="${json.navTabId }" />
		<input type="hidden" name="baseResources.menuCode" value="${param.menuCode }" />
		<div class="pageFormContent nowrap" layoutH="56">
			<p>
				<label>资源名称：</label>
				<input name="baseResources.resourceName" class="required" type="text" size="30"  maxlength="255" alt="[菜单名称][资源名称][功能]" />
				
			</p>
			<p>
				<label>资源描述：</label>
				<input name="baseResources.resourceDesc" class="required" type="text" size="30" maxlength="255" alt="请输入资源描述"/>
			</p>
			<p>
				<label>资源标识：</label>
				<input name="baseResources.resourceString" class="required alphanumeric" type="text" size="30" maxlength="255" alt="请输入唯一的资源标识"/>
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
