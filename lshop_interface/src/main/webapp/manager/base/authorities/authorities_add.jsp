<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseAuthorities!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>名称：</label>
				<input name="baseAuthorities.authorityName" class="required" type="text" size="30" maxlength="50" alt="请输权限名称"/>
			</p>
			<p>
				<label>描述：</label>
				<textarea name="baseAuthorities.authorityDesc" cols="30" rows="6"  alt="备注"></textarea>
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
