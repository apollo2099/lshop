<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvCouponMacAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvCouponMacAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>用户邮箱</label></td><td><input name="lvCouponMac.userEmail" type="text" size="32" value="${lvCouponMac.userEmail}"/></td>
						<td><label>MAC</label></td><td><input name="lvCouponMac.mac" type="text" size="32" value="${lvCouponMac.mac}"/></td>
						<td><label>优惠码</label></td><td><input name="lvCouponMac.couponCode" type="text" size="32" value="${lvCouponMac.couponCode}"/></td>
						</tr><tr>
						<td><label>状态</label></td><td>
							<select name="lvCouponMac.status" style="width:200px;">
								<option>--请选择--</option>
								<option value="0" <c:if test="${lvCouponMac.status==0 }">selected="selected"</c:if>>已领券</option>
								<option value="1" <c:if test="${lvCouponMac.status==1 }">selected="selected"</c:if>>已下单</option>
							</select>
						</td>
						<td><label>创建时间</label></td><td><input type="text" name="lvCouponMac.startTime" value="${lvCouponMac.startTime}" class="date"/>
						-<input type="text" class="date" name="lvCouponMac.endTime" value="${lvCouponMac.endTime}">
						</td>
						
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
                <li><a class="icon" href="lvCouponMacAction!exportCouponMac.action?json.navTabId=${json.navTabId}"  target="dwzExport" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvCouponMacAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					    <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="5%">序号</th>
							<th width="20%">用户邮箱</th>
							<th width="15%">MAC</th>
							<th width="15%">优惠码</th>
							<th width="5%">状态</th>
							<th width="15%">兑换来源</th>
							<th width="10%">IP地址</th>
							<th width="10%">创建时间</th>
					        <th width="4%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.userEmail }</td>
								<td>${item.mac }</td>
								<td>${item.couponCode }</td>
								<td>
								<c:if test="${item.status==0 }">已领券</c:if>
								<c:if test="${item.status==1 }">已下单</c:if>
								</td>
								<td>${item.sourceUrl }</td>
								<td>${item.ip }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnView" href="lvCouponMacAction!view.action?lvCouponMac.id=${item.id}" target="dialog" navTabId="lvCouponMac" rel="lvCouponMac" title="查看" width="850" height="500">查看</a>
						<a class="btnDel" href="lvCouponMacAction!del.action?lvCouponMac.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>