<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="/manager/accountorder!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="/manager/accountorder!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<ul class="searchContent" style="height: 51px;">
				<li style="width: 240px">账户：<input type="text" name="account" value="${account}" /></li>
				<li style="width: 850px"></li>
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
					<th width="20%">充值总金额</th>
					<th width="20%">消费总金额</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td>${item.accountno}</td>
					<td>${item.receiptsum}V</td>
					<td>${item.paymentsum}V</td>
					<td>
						<a title="充值明细" class="btn1" href="accountorder!buyList.action?account=${item.accountno}&json.navTabId=${json.navTabId}" target="navTab" rel="r1"><button>充值明细</button></a>
						<a title="消费明细" class="btn1" href="accountorder!consumeList.action?account=${item.accountno}&json.navTabId=${json.navTabId}" target="navTab" rel="r2"><button>消费明细</button></a>
					</td>
				</tr>
				</s:iterator> 
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>
