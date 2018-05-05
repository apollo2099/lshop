<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvLotteryLogsAction!list.action?lvLotteryLogs.activityCode=${lvLotteryLogs.activityCode }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvLotteryLogsAction!list.action?lvLotteryLogs.activityCode=${lvLotteryLogs.activityCode }&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>中奖物品名称</label></td><td><input name="lvLotteryLogs.prizeName" type="text" size="20" maxlength="32" value="${lvLotteryLogs.prizeName}"/></td>
						<td><label>中奖人名称</label></td><td><input name="lvLotteryLogs.userName" type="text" size="20" maxlength="32" value="${lvLotteryLogs.userName}"/></td>
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
				<li><a class="add" href="lvLotteryLogsAction!befSave.action?lvLotteryLogs.activityCode=${lvLotteryLogs.activityCode }&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvLotteryLogs" rel="lvLotteryLogs" width="600" height="450" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvLotteryLogsAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
				<!-- 生成表单 -->
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="20%">中奖人姓名</th>
				<th width="20%">中奖物品</th>
				<th width="5%">物品类型</th>
				<th width="5%">中奖数目</th>
				<th width="10%">中奖时间</th>
				<th width="10%">状态</th>
				<th width="25%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.userName }</td>
								<td>${item.prizeName }</td>
								<td>
								<s:if test="#item.lvAccountPrize.prizeType==1">优惠券</s:if>
								<s:if test="#item.lvAccountPrize.prizeType==2">实物</s:if>
								</td>
								<td>${item.prizeNum }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
								 <s:if test="#item.lvAccountPrize.status==0">未兑奖</s:if>
								 <s:if test="#item.lvAccountPrize.status==1">已兑奖</s:if>
								 <s:if test="#item.lvAccountPrize.status==2">已发货</s:if>
								</td>

					<td>
						<a class="btnView" href="lvLotteryLogsAction!view.action?lvLotteryLogs.id=${item.id}" target="dialog" navTabId="lvLotteryLogs" rel="lvLotteryLogs" title="查看" width="600" height="450" mask="true">查看</a>
						<a class="btnDel" href="lvLotteryLogsAction!del.action?lvLotteryLogs.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<s:if test="#item.isSystemFlag==0&& #item.lvAccountPrize.status==1 && #item.lvAccountPrize.prizeType==2">
								<a href="lvLotteryLogsAction!toShipment.action?lvLotteryLogs.accountPrizeCode=${item.accountPrizeCode}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvActivity" rel="lvActivity" title="编辑" width="750" height="600" mask="true">发货</a>
						</s:if>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>