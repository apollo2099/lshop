<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="baseUser!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="baseUser!list.action?json.navTabId=${json.navTabId}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名称：</label>
				<input type="text" name="keyword" value="${keyword}"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
			<li><a class="add" href="baseUser!bfAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="700" height="600" title="添加用户"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="baseUser!delete.action?json.navTabId=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%" >账号</th>
				<th width="10%" >姓名</th>
				<th width="10%" >所属店铺</th>
				<th width="10%" >描述</th>
				<th width="5%" orderField="status" class="${orderDirection}">状态</th>
				<th width="10%" orderField="userLogintime" class="${orderDirection}">最近登录时间</th>
				<th width="10%" >最近登录IP</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="user">
			<tr>
				<td><input name="ids" value="${user.id}" type="checkbox"></td>
				<td>${user.id}</td>
				<td>${user.userAccount}</td>
				<td>${user.userName}</td>
				<td>${user.storeFlag}</td>
				<td>${user.userDesc}</td>
				<td>
				<c:if test="${user.status==1}">正常</c:if>
				</td>
				<td><fmt:formatDate value="${user.userLogintime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>${user.userLoginip}</td>
			
				<td>
				<sec:authorize url="userEdit">
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo" href="baseUser!delete.action?ids=${user.id}&json.navTabId=${json.navTabId }" class="btnDel">删除</a>
					<a title="编辑" target="dialog" mask="true" href="baseUser!bfEdit.action?userCode=${user.userCode}&json.navTabId=${json.navTabId }" class="btnEdit" width="700" height="600" title="编辑">编辑</a>
					<a title="确实要重置${user.userAccount}的密码吗?该操作请谨慎!" target="ajaxTodo" href="baseUser!resetPassword.action?userCode=${user.userCode}&json.navTabId=${json.navTabId }" class="btnSelect">重置密码</a>
				</sec:authorize>	
					<a title="权限明细" target="navTab" href="baseUsersRoles!list.action?userCode=${user.userCode}&json.navTabId=baseUsersRoles_Item" rel="baseUsersRoles_Item"  class="btnLook">权限明细</a>
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
