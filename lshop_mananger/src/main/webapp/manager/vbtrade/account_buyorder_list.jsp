<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="/manager/accountorder!buyList.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="/manager/accountorder!buyList.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<input type="hidden" name="account" value="${account}" />
		<div class="searchBar">
			<ul class="searchContent" style="height: 51px;">
				<li style="width: 300px">
					充值时间：<input type="text" name="buyStartTime" value="${buyStartTime}" class="date" size="10" />
					&nbsp;至&nbsp;<input type="text" name="buyEndTime" value="${buyEndTime}" class="date" size="10" />
				</li>
				<li style="width: 790px"></li>
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
					<th width="20%">充值时间</th>
					<th width="20%">充值金额</th>
					<th width="20%">充值来源</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td>${item.account}</td>
					<td>${item.tradeno}</td>
					<td>${item.date}</td>
					<td>${item.amt}V</td>
					<td>
						<c:choose>
							<c:when test="${item.client eq '1'}">启创网站</c:when>
							<c:when test="${item.client eq '2'}">机顶盒</c:when>
							<c:when test="${item.client eq '3'}">手动充值</c:when>
							<c:when test="${item.client eq '4'}">华扬中文商城</c:when>
							<c:when test="${item.client eq '5'}">华扬英文商城</c:when>
							<c:when test="${item.client eq '6'}">华扬中文移动商城</c:when>
							<c:when test="${item.client eq '8'}">banana中文商城</c:when>
							<c:when test="${item.client eq '9'}">banana英文商城</c:when>
							<c:when test="${item.client eq '10'}">banana中文移动商城</c:when>
						</c:choose>
					</td>
				</tr>
				</s:iterator> 
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>
