<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="/manager/accountorder!consumeList.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="/manager/accountorder!consumeList.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<input type="hidden" name="account" value="${account}" />
		<div class="searchBar">
			<ul class="searchContent" style="height: 51px;">
				<li style="width: 300px;">MAC码：<input type="text" name="mac" value="${mac}" /></li>
				<li style="width: 300px">
					消费时间：<input type="text" name="consumeStartTime" value="${consumeStartTime}" class="date" size="10" />
					&nbsp;至&nbsp;<input type="text" name="consumeEndTime" value="${consumeEndTime}" class="date" size="10" />
				</li>
				<li style="width: 490px"></li>
				<li style="width: 60px"><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar"></div>
		<table class="table" width="100%" layoutH="140">
			<thead>
				<tr>
					<th width="20%">账户</th>
					<th width="20%">交易流水号</th>
					<th width="15%">MAC码</th>
					<th width="15%">消费时间</th>
					<th width="10%">消费金额</th>
					<th width="20%">备注</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td>${item.account}</td>
					<td>${item.tradeno}</td>
					<td>${item.mac}</td>
					<td>${item.date}</td>
					<td>${item.amt}V</td>
					<td>${item.items[0].remark}</td><%-- 一条交易暂时只会购买一个套餐，所以直接取提一条数据 --%>
				</tr>
				</s:iterator> 
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>
