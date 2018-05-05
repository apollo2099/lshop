<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="/manager/userorder!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="/manager/userorder!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<ul class="searchContent" style="height: 51px;">
				<li style="width: 350px;">订单号：<input type="text" name="orderNo" value="${orderNo}" /></li>
				<li style="width: 350px;">充值账号：<input type="text" name="account" value="${account}" /></li>
				<li style="width: 350px">
					充值时间：<input type="text" name="startTime" value="${startTime}" class="date" size="10" />
					&nbsp;至&nbsp;<input type="text" name="endTime" value="${endTime}" class="date" size="10" />
				</li>
				<li style="width: 350px;">
					支付状态：
					<select name="payStatus">
						<option value="">全部</option>
						<option value="0" <c:if test="${payStatus eq '0'}">selected</c:if>>未支付</option>
						<option value="1" <c:if test="${payStatus eq '1'}">selected</c:if>>已支付</option>
					</select>
				</li>
				<li style="width: 350px;">
					订单状态：
					<select name="orderStatus">
						<option value="">全部</option>
						<option value="0" <c:if test="${orderStatus eq '0'}">selected</c:if>>未受理</option>
						<option value="1" <c:if test="${orderStatus eq '1'}">selected</c:if>>已成功</option>
						<option value="3" <c:if test="${orderStatus eq '3'}">selected</c:if>>已取消</option>
					</select>
				</li>
				<li style="width: 350px;"></li>
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
					<th width="18%">订单号</th>
					<th width="18%">充值账号</th>
					<th width="14%">充值时间</th>
					<th width="10%">金额</th>
					<th width="10%">V币数量</th>
					<th width="10%">支付状态</th>
					<th width="10%">订单状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody style="">
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td>${item.rnum}</td>
					<td>${item.accounts}</td>
					<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><span style="color: red; font-weight: bold;">${item.moneyStr}</span> ${item.currency}</td>
					<td><span style="color: red; font-weight: bold;">${item.vbNum}</span> V</td>
					<td>
						<c:choose>
							<c:when test="${item.payStatus eq 1}">已支付</c:when>
							<c:when test="${item.payStatus eq 0}">未支付</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${item.status eq 0}">未受理</c:when>
							<c:when test="${item.status eq 1}">已成功</c:when>
							<c:when test="${item.status eq 3}">已取消</c:when>
						</c:choose>
					</td>
					<td>
					<c:if test="${item.payStatus eq 1 and item.status eq 0}">
						<a href="/manager/userorder!charge.action?orderNo=${item.rnum}&json.navTabId=${json.navTabId}" target="ajaxTodo" class="btn1"><button>受理</button></a>
					</c:if>
					</td>
				</tr>
				</s:iterator> 
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>
