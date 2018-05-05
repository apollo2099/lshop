<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvUserPromotersMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!list.action" method="post" rel="pagerForm">
		<input type="hidden" name="lvUserPromoters.approvalStatus" value="${lvUserPromoters.approvalStatus }">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
					<tr>
						<td><label>真实姓名</label></td>
						<td><input name="lvUserPromoters.realName" type="text" size="20" value="${lvUserPromoters.realName}" /></td>
						<td><label>邮箱地址</label></td>
						<td><input name="lvUserPromoters.email" type="text" size="20" value="${lvUserPromoters.email}" /></td>
						<c:if test="${approvalStatus==1}">
						<td><label>所属父级</label></td>
						<td><input name="parentName" type="text" size="20" value="${parentName}" /></td>
						</c:if>
					</tr>
					<tr>
						<td><label>状态</label></td>
						<td>
						
						<select
						<c:if test="${not empty lvUserPromoters.approvalStatus}">disabled="disabled"</c:if>
						>
						 <option value="" ></option>
						 <option value="0" <c:if test="${lvUserPromoters.approvalStatus==0}">selected="selected"</c:if>>未审核</option>
						 <option value="1" <c:if test="${lvUserPromoters.approvalStatus==1}">selected="selected"</c:if>>审核通过</option>
						</select>
						<c:if test="${approvalStatus==1}">
						<td><label>用户类型</label></td>
						<td><s:select name="lvUserPromoters.userType" list="#{0:'普通用户',1:'特殊用户'}" headerKey="" headerValue=""></s:select></td>
						</c:if>
						<td><label>优惠码</label></td>
						<td><input name="couponCode" type="text" size="20" value="${couponCode}" /></td>
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
				 <li><a class="edit" href="lvUserPromotersMngAction!export.action?approvalStatus=${lvUserPromoters.approvalStatus}&json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li>  
			</ul>
		</div>
		<table class="table" width="100%" layoutH="180">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="30">编号</th>
							<th width="90">真实姓名</th>
							<th width="90">邮箱地址</th>
							<c:if test="${approvalStatus==1}">
								<th width="90">推广码</th>
								<th width="90">所属父级</th>
							</c:if>
							<th width="90">收款帐户类型</th>
							<th width="90">收款帐户</th>
							<th width="90">联系电话</th>
							<th width="200">地址</th>
							<th width="70">审核状态</th>
					       <th width="150">操作</th>
					       <th >描述</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count}</td>
								<td>${item.realName}</td>
								<td>${item.email}</td>
								<c:if test="${approvalStatus==1}">
									<td>${couponCode}</td>
									<td>${item.parentName }</td>
								</c:if>
								<td>
								<c:if test="${item.accountTypes==1}">PayPal</c:if>
								<c:if test="${item.accountTypes==2}">支付宝</c:if>
								</td>
								<td>${item.accountNumber}</td>
								<td>${item.tel}</td>
								<td>${item.adress}</td>
								<td><s:if test="#item.approvalStatus==0">未审核</s:if><s:elseif test="#item.approvalStatus==1">审核通过</s:elseif><s:elseif test="#item.approvalStatus==-1">审核未通过</s:elseif></td>
					<td>
					    <s:if test="#item.approvalStatus==0">
					    <a href="lvUserPromotersMngAction!auditing.action?lvUserPromoters.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" width="700" height="500" title="审核"  style="color:green">审核</a>&nbsp;
						</s:if>
						<a  href="lvUserPromotersMngAction!view.action?lvUserPromoters.id=${item.id}" target="dialog" navTabId="lvUserPromoters" rel="lvUserPromoters" title="查看" width="850" height="500">查看</a>
					    	<a title="编辑" target="dialog" mask="true" href="lvUserPromotersMngAction!bfEdit.action?id=${item.id}&json.navTabId=${json.navTabId }">编辑</a>
					    	<a href="lvUserPromotersMngAction!del.action?lvUserPromoters.id=${item.id}&approvalStatus=${lvUserPromoters.approvalStatus}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确定永久删除该条记录？">删除</a>
					    <s:if test="lvUserPromoters.approvalStatus==1">
					    <s:iterator value="#request.uList" id="u">
					    <s:if test="#u.id==#item.uid">
					    <s:if test="#u.isdelete==1">
					    <a href="lvUserPromotersMngAction!startAccount.action?lvUserPromoters.uid=${item.uid}" target="navTabTodo" title="确定启用账号?">启用</a>
					    </s:if>
					    <s:if test="#u.isdelete==0">
					    <a href="lvUserPromotersMngAction!stopAccount.action?lvUserPromoters.uid=${item.uid}" target="navTabTodo" title="确定停用账号?">停用</a>
					    </s:if>
					    </s:if>
					      </s:iterator>
					    </s:if>
					</td>
					<td title="${item.description}">${item.description}</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>