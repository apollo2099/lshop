<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="baseDepartment!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}">
		<input type="hidden" name="baseDepartment.departmentParent" value="${param.departmentParent}"/>
		<input type="hidden" name="baseDepartment.departmentLevel" value="${param.level+1}"/>
		<input type="hidden" name="baseDepartment.status" value="1"/>
			<div class="pageFormContent" layoutH="58">
				<div class="divider">divider</div>
				<p>
					<label>名称：</label>
					<input type="text" name="baseDepartment.departmentName" size="30" maxlength="150" class="required"/>
					
				</p>
				<p>
					<label>描述：</label>
					<input type="text" name="baseDepartment.departmentDesc" size="30" maxlength="150" class="required"/>
					
				</p>
				<p>
					<label>排序：</label>
					<input type="text" name="baseDepartment.departmentDisorder" maxlength="250" size="30" class="required"/>
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