<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="baseMenu!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId}">
		<input type="hidden" name="baseMenu.menuParent" value="${param.menuParent}"/>
		<input type="hidden" name="baseMenu.createdBy" value="${sessionScope.USER.userCode}"/>
		<input type="hidden" name="baseMenu.status" value="1"/>
			<div class="pageFormContent" layoutH="58">
				<div class="divider">divider</div>
				<p>
					<label>名称：</label>
					<input type="text" name="baseMenu.menuName" size="30" maxlength="150" class="required"/>
					
				</p>
				<p>
					<label>URL：</label>
					<input type="text" name="baseMenu.menuUrl" maxlength="250" size="30" />
				</p>
				<p>
					<label>排序：</label>
					<input type="text" name="baseMenu.menuDisorder" maxlength="250" size="30" class="required"/>
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