<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvOperationLogsAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOperationLogsAction!list.action" method="post">
	<div class="searchBar">
					<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>模块名称</label><input type="text" name="lvOperationLogs.operationModule" value="${lvOperationLogs.operationModule}" /></td>
						<td><label>操作人</label><input type="text" name="lvOperationLogs.operator" value="${lvOperationLogs.operator}" /></td>
						<td><label>操作关键字</label><input type="text" name="lvOperationLogs.operationKey" value="${lvOperationLogs.operationKey}" /></td>
						<td><label>操作时间</label><input type="text" name="lvOperationLogs.startTime" value="${lvOperationLogs.startTime}" class="date"/>-<input type="text" class="date" name="lvOperationLogs.endTime" value="${lvOperationLogs.endTime}"></td>
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
			<li><a class="icon" href="lvOperationLogsAction!exportOperationLogs.action?json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%">ID</th>
				<th width="10%">操作人</th>
				<th width="10%">操作模块</th>
				<th width="10%">操作关键字</th>
				<th width="40%">操作详情</th>
				<th width="10%">操作时间</th>
				<th width="15%" >操作</th>
			</tr>
		</thead>
		<tbody>
		 <s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.operator }</td>
				<td>${item.operationModule}</td>
				<td>${item.operationKey }</td>
				<td>${item.operationDetails }</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvOperationLogsAction!view.action?lvOperationLogs.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="800" height="400" mask="true">查看</a>
					<a class="btnDel"  href="lvOperationLogsAction!del.action?lvOperationLogs.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
