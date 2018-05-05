<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvPayLogsAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPayLogsAction!list.action" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>订单编号</label>
						<input type="text" name="lvPayLogs.oid" value="${lvPayLogs.oid}" />
						</td>
				
						<td><label>支付时间</label><input type="text" name="lvPayLogs.startTime" value="${lvPayLogs.startTime}" class="date"/>-<input type="text" class="date" name="lvPayLogs.endTime" value="${lvPayLogs.endTime}"></td>
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
			<li><a class="delete"  href="lvPayLogsAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="15%">订单编号</th>
				<th width="10%">支付方式</th>
				<th width="15%">支付时间</th>
				<th width="10%">支付总金额</th>
				<th width="10%">币种</th>
				<th width="10%">状态</th>
				<th width="25%" >操作</th>
			</tr>
		</thead>
		<tbody>
		 <s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.oid }</td>
				<td>
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(#item.paymethod)"/>
				</td>
				<td><s:date name="paydate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.amount }</td>
				<td>${item.currency }</td>
				<td>
				<c:if test="${item.status==0}">未支付</c:if>
				<c:if test="${item.status==1}">已支付未发货</c:if>
				<c:if test="${item.status==2}">已发货未确认</c:if>
				<c:if test="${item.status==3}">完成</c:if>
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvPayLogsAction!view.action?lvPayLogs.id=${item.id}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="800" height="400" mask="true">查看</a>
					<a class="btnDel"  href="lvPayLogsAction!del.action?lvPayLogs.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
