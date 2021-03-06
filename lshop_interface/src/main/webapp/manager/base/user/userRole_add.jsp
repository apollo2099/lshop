<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="baseUsersRoles!bfAdd.action?userCode=${param.userCode}"
		name="pagerFormAction" />
</jsp:include>

<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);"
		action="baseUsersRoles!bfAdd.action?userCode=${param.userCode}" method="post">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>名称：</label>
					<input type="text" name="keyword" value="${keyword}" />
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<form method="post" action="baseUsersRoles!add.action?userCode=${param.userCode}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="panelBar">
			<ul class="toolBar">
			</ul>
		</div>
		<div class="pageFormContent" style="padding: 0px">
			<table class="table" width="100%" layoutH="175">
				<thead>
					<tr>
						<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th width="5%">ID</th>
						<th width="35%">角色名称</th>
						<th width="20%">描述</th>
						<th width="20%">创建时间</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="baseRoles">
						<tr>
							<td><input name="ids" value="${baseRoles.roleCode}" type="checkbox"></td>
							<td>${baseRoles.id}</td>
							<td>${baseRoles.roleName}</td>
							<td>${baseRoles.roleDesc}</td>
							<td><fmt:formatDate value="${baseRoles.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:foreach>
				</tbody>
			</table>

			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>



