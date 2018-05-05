<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvOrderRemindAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderRemindAction!list.action" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>订单编号</label>
						<input type="text" name="lvOrderRemind.orderId" value="${lvOrderRemind.orderId}" />
						</td>
				
						<td><label>提醒时间</label><input type="text" name="lvOrderRemind.startTime" value="${lvOrderRemind.startTime}" class="date"/>-<input type="text" class="date" name="lvOrderRemind.endTime" value="${lvOrderRemind.endTime}"></td>
				</tr><tr>
				</tr>
			</table>
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
			<li><a class="delete"  href="lvOrderRemindAction!del.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">用户昵称</th>
				<th width="20%">用户Email</th>
				<th width="20%">订单编号</th>
				<th width="10%">提醒次数</th>
				<th width="15%" orderField="remindTime" class="${orderDirection}">提醒时间</th>
				<th width="20%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.nickname }</td>
				<td>${item.email }</td>
				<td>${item.orderId }</td>
				<td>${item.remindNum }</td>
				<td>${item.remindTime }</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvOrderRemindAction!view.action?lvOrderRemind.id=${item.id}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="800" height="400" mask="true">查看</a>
					<a class="btnDel"  href="lvOrderRemindAction!del.action?ids=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
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
