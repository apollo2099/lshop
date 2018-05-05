<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseAuthorities!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<input type="hidden" name="baseAuthorities.id" value="${baseAuthorities.id }" />
		<input type="hidden" name="baseAuthorities.version" value="${baseAuthorities.version }" />
		<input type="hidden" name="baseAuthorities.authoritiesCode" value="${baseAuthorities.authoritiesCode }" />
		<input type="hidden" name="baseAuthorities.status" value="${baseAuthorities.status }" />
		<input type="hidden" name="baseAuthorities.createdBy" value="${baseAuthorities.createdBy }" />
		<input type="hidden" name="baseAuthorities.createdDate" value="${baseAuthorities.createdDate }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>名称：</label>
				<input name="baseAuthorities.authorityName" class="required" type="text" size="30" maxlength="50" value="${baseAuthorities.authorityName}" alt="请输权限名称"/>
			</p>
			<p>
				<label>描述：</label>
				<textarea name="baseAuthorities.authorityDesc"  cols="30" rows="6"  alt="备注">${baseAuthorities.authorityDesc}</textarea>
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

