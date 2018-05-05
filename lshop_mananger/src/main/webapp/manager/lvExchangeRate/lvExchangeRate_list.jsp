<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvExchangeRateAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvExchangeRateAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>兑换货币名称</label></td><td>
						<select name="lvExchangeRate.currency">
						 <option value="">==请选择==</option>
						 <option value="USD" <c:if test="${lvExchangeRate.currency=='USD' }">selected="selected"</c:if>>USD  美元</option>
						 <option value="CNY" <c:if test="${lvExchangeRate.currency=='CNY' }">selected="selected"</c:if>>CNY  人民币</option>
						 <option value="EUR" <c:if test="${lvExchangeRate.currency=='EUR' }">selected="selected"</c:if>>EUR  欧元</option>
						 <option value="AUD" <c:if test="${lvExchangeRate.currency=='AUD' }">selected="selected"</c:if>>AUD  澳元</option>
						 <option value="GBP" <c:if test="${lvExchangeRate.currency=='GBP' }">selected="selected"</c:if>>GBP  英镑</option>
						 <option value="HKD" <c:if test="${lvExchangeRate.currency=='HKD' }">selected="selected"</c:if>>HKD  港币</option>
						 <option value="JPY" <c:if test="${lvExchangeRate.currency=='JPY' }">selected="selected"</c:if>>JPY  日元</option>
						 <option value="KER" <c:if test="${lvExchangeRate.currency=='KER' }">selected="selected"</c:if>>KER  韩元</option>
						 <option value="THB" <c:if test="${lvExchangeRate.currency=='THB' }">selected="selected"</c:if>>THB  泰铢</option>
						 <option value="TWD" <c:if test="${lvExchangeRate.currency=='TWD' }">selected="selected"</c:if>>TWD  台币</option>
						</select>
						</td>
						<td><label>基准货币名称</label></td><td>
						<select name="lvExchangeRate.mainCurrency">
						 <option value="">==请选择==</option>
						 <option value="USD" <c:if test="${lvExchangeRate.mainCurrency=='USD' }">selected="selected"</c:if>>USD  美元</option>
						 <option value="CNY" <c:if test="${lvExchangeRate.mainCurrency=='CNY' }">selected="selected"</c:if>>CNY  人民币</option>
						 <option value="VB" <c:if test="${lvExchangeRate.mainCurrency=='VB' }">selected="selected"</c:if>>VB  V币</option>
						</select>
						</td>
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
				<li><a class="add" href="lvExchangeRateAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvExchangeRate" rel="lvExchangeRate" width="500" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" >序号</th>
							<th width="15%" orderField="currencyName" >兑换货币名称</th>
							<th width="10%" orderField="exchangeRate" >兑换汇率</th>
							<th width="15%" orderField="exchangeRate" >基准货币名称</th>
							<th width="15%" orderField="createTime" >创建时间</th>
							<th width="15%" orderField="modifyTime" >修改时间</th>
							<th width="15%">修改人名称</th>
							
					        <th width="25%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.currencyName }</td>
								<td>${item.exchangeRateStr }</td>
								<td>${item.mainCurrencyName }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
								<td>${item.modifyUserName}</td>
					<td>
					    <a class="btnDel" href="lvExchangeRateAction!del.action?lvExchangeRate.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvExchangeRateAction!befEdit.action?lvExchangeRate.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvExchangeRate" rel="lvExchangeRate" title="编辑" width="500" height="300" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>