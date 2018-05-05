<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="pageContent">
	<form method="post" action="baseUser!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>账号：</label>
				<input name="baseUsers.userAccount" class="required alphanumeric" type="text" size="30" minlength="4" maxlength="20" alt="请输入用户登录账号" />
			</p>
			<p>
				<label>姓名：</label>
				<input name="baseUsers.userName" class="required" type="text" size="30" maxlength="50" alt="请输入真实姓名"/>
			</p>
			
			<p style="height:100px;">
				<label>描述：</label>
				<textarea name="baseUsers.userDesc" cols="30" rows="6"  alt="简介/备注"></textarea>
			</p>
			<p >
				<label>所属店铺：</label>
				<select name="baseUsers.storeFlag" class="required" multiple="multiple" style="height:300px;">
				<s:iterator value="#request.storeList" id="store">
				<option value="${storeFlag}">${store.name}</option>
				</s:iterator>
				</select>
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
