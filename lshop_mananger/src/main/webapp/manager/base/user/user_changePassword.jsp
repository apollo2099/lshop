<%@ page language="java" pageEncoding="utf-8"%>

<div class="pageContent">
	<form method="post" action="baseUser!changePassword.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<input type="hidden" name="userCode" value="${sessionScope.USER.userCode}" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>原密码：</label>
				<input name="oldPassword" class="required" type="password" size="30" minlength="4" maxlength="20" alt="请输入原密码" />
			</p>
			<p>
				<label>新密码：</label>
				<input name="newPassword" id="newPassword" class="required" type="password" size="30" minlength="4" maxlength="20" alt="请输入新密码"/>
			</p>
			<p>
				<label>重新输入：</label>
				<input name="reNewPassword" class="required" equalTo="#newPassword" type="password" size="30" minlength="4" maxlength="20" alt="请再次输入新密码"/>
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
