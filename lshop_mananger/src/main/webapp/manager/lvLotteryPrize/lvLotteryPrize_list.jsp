<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvLotteryPrizeAction!list.action?lvLotteryPrize.activityCode=${lvLotteryPrize.activityCode }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvLotteryPrizeAction!list.action?lvLotteryPrize.activityCode=${lvLotteryPrize.activityCode }&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>奖项物品名称</label></td><td><input name="lvLotteryPrize.prizeName" type="text" size="20" maxlength="32" value="${lvLotteryPrize.prizeName}"/></td>
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
				<li><a class="add" href="lvLotteryPrizeAction!befSave.action?lvLotteryPrize.activityCode=${lvLotteryPrize.activityCode }&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvLotteryPrize" rel="lvLotteryPrize" width="750" height="450" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvLotteryPrizeAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
				<!-- 生成表单 -->
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="25%">奖项物品名称</th>
				<th width="10%">奖项物品图片</th>
				<th width="10%">奖项物品类型</th>
				<th width="10%">奖项数量</th>
				<th width="10%">抽中数量</th>
				<th width="10%">排序值</th>
                <th width="10%">是否可抽奖项</th>
				<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.prizeName }</td>
								<td>
								<a href="lvShopProductAction!showPic.action?imgSrc=${item.prizeImages}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.prizeImages}" width="20px" height="20px"/></a>
								</td>
								<td>
								 <s:if test="#item.prizeType==1">优惠券</s:if>
								 <s:if test="#item.prizeType==2">实物</s:if>
								</td>
								<td>${item.prizeTotal }</td>
								<td>${item.grantTotal }</td>
								<td>${item.sortId }</td>
								<td>
								<s:if test="#item.isDraw==1">是</s:if>
								<s:if test="#item.isDraw==0">否</s:if>
								</td>
					<td>
						<a class="btnView" href="lvLotteryPrizeAction!view.action?lvLotteryPrize.id=${item.id}" target="dialog" navTabId="lvLotteryPrize" rel="lvLotteryPrize" title="查看" width="850" height="500" mask="true">查看</a>
						<a class="btnEdit" href="lvLotteryPrizeAction!befEdit.action?lvLotteryPrize.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvLotteryPrize" rel="lvLotteryPrize" title="编辑" width="600" height="450" mask="true">编辑</a>
						<a class="btnDel" href="lvLotteryPrizeAction!del.action?lvLotteryPrize.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>