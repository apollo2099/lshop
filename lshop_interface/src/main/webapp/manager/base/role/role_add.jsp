<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<div class="pageContent">
	<form method="post" action="baseRoles!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.rel" value="${json.navTabId }" />
		<input type="hidden" name="baseRoles.departmentCode" value="${param.departmentCode}" />
		<div class="pageFormContent nowrap" layoutH="56">
			<p>
				<label>角色名称：</label>
				<input name="baseRoles.roleName" class="required" type="text" size="30"  maxlength="255" alt="角色名称" />
				
			</p>
			<p>
				<label>角色描述：</label>
				<input name="baseRoles.roleDesc" class="required" type="text" size="30" maxlength="255" alt="角色描述"/>
			</p>
			<p>
				<label>所属店铺：</label>
				<select  name= "storeFlagList" multiple="multiple">
				   <c:foreach items="${storeList}" var="item">
				     <option value="${item.storeFlag }">${item.name }</option>
				   </c:foreach>
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
