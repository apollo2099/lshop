<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/cus.tld" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvDealerApplicationAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvDealerApplicationAction!list.action" method="post"  rel="pagerForm" >
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
				<tr>
						</tr><tr>
						<td><label>申请人/公司</label><input type="text" name="lvDealerApplication.applyCmp" value="${lvDealerApplication.applyCmp}" /></td>
						<td><label>邮箱</label><input type="text" name="lvDealerApplication.applyEmail" value="${lvDealerApplication.applyEmail}" /></td>
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
		<table class="table" width="100%" layoutH="165">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="30">编号</th>
					<th width="30">申请人/公司</th>
					<th width="90">联系人</th>
					<th width="90">联系电话</th>
					<th width="60">E-mail</th>
					<th width="120">通迅地址</th>
					<th width="120">申请代理的区域</th>
					<th width="120">所属商城</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>${item.id}</td>
					<td>${item.applyCmp }</td>
					<td>${item.applyName }</td>
					<td>${item.applyTel }</td>
					<td>${item.applyEmail}</td>
					<td>${item.applyAddr}</td>
                    <td>${item.applyArea}</td>
                    <td><fmt:store param="storeName" shopFlag="${item.storeId}"></fmt:store></td>
					<td>
						<a class="btnView" href="lvDealerApplicationAction!view.action?lvDealerApplication.id=${item.id}" target="dialog" navTabId="lvApplication" rel="lvApplication" title="查看" width="850" height="500">查看</a>
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
</div>