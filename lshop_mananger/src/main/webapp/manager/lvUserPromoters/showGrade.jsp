<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvUserPromotersMngAction!showGrade.action?myId=${myId }" >
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField }" />
	<input type="hidden" name="orderDirection" value="${orderDirection }" />
	
</form>

<div class="page">
	<div class="pageHeader">
	   <form onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!showGrade.action?myId=${myId }" method="post" rel="pagerForm">
	   <input type="hidden" name="json.navTabId" value="${json.navTabId}" />
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
				<tr>
						<td><label>真实姓名</label></td><td><input name="lvUserPromoters.realName" type="text" size="20" value="${lvUserPromoters.realName}"/></td>
						<td><label>邮箱地址</label></td><td><input name="lvUserPromoters.email" type="text" size="20" value="${lvUserPromoters.email}"/></td>
						<td>
						   <label>推广码：</label>
						   <input name="couponCode" type="text" id="couponCode" value="${couponCode}" width="20"/>
				         </td>
				</tr>
				<tr>
					<td>用户类型：</td>
					<td>
					<s:select name="lvUserPromoters.userType" list="#{0:'普通用户',1:'特殊用户'}" headerKey="" headerValue=""></s:select>
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
	  <span>统计：产生的订单数 <font color="red">${totalOrder}</font> 张，总推广台数 <font color="red">${totalNum}</font> 台，总佣金 <font color="red">${totalAmout}</font> 美元</span>
	</div>
	<div class="pageContent">
		<div class="panelBar">
		 <ul class="toolBar">
				  <li><a class="edit" href="lvUserPromotersMngAction!exportExl.action?myId=${myId }&json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li>  
		 </ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th >编号</th>
							<th>推广者</th>
							<th>邮箱地址</th>
							<th>推广码</th>
							<th>发送时间</th>
							<th>截止时间</th>
							<th>第一次使用时间</th>
							<th orderField="settlementedNum" class="${orderField eq 'orderNum' ? orderDirection : ''}">产生的订单数</th>
					        <th orderField="settlementedNum" class="${orderField eq '' ? orderDirection : ''}">推广台数</th>
					        <th orderField="settlementedNum" class="${orderField eq 'settlementedNum' ? page.orderDirection : ''}">已结算台数</th>
					        <th>未结算台数</th>
					        <th orderField="settlementedAmount" class="${orderField eq 'settlementedAmount' ? orderDirection : ''}">累计收益</th> 
					<th width="120">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
		            <td><input name="ids" value="${item.id}" type="checkbox"></td>
		            <!-- 生成表单 -->
					<td>${stat.count}</td>
					<td>${item.realName }</td>
					<td>${item.email}</td>
					<td>${item.couponCode}</td>
					<td><s:date name="sendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="validitydate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="overtime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${item.orderNum}</td>
					<td>${item.nonSettlementNum+item.settlementNum+item.settlementedNum}</td>
					<td>${item.settlementedNum}</td>
					<td>${item.nonSettlementNum+item.settlementNum}</td>
					<td>${totalAmount}</td>
					<td>
					    <s:if test="#item.couponCode!=null">
						    <s:if test="#item.couponIsdel==1">
						    <a href="lvUserPromotersMngAction!start.action?couponid=${item.couponId}" target="ajaxTodo" title="确定启用推广码?">启用</a>
						    </s:if>
						    <s:if test="#item.couponIsdel==0">
						    <a href="lvUserPromotersMngAction!stop.action?couponid=${item.couponId}" target="ajaxTodo" title="确定停用推广码?">停用</a>
						    </s:if>
					    </s:if>
					    
					&nbsp;&nbsp;
						<a class="" href="lvUserPromotersMngAction!view.action?lvUserPromoters.id=${item.id}" target="dialog"  rel="lvUserPromoters" title="查看" width="850" height="500">用户资料</a>
					     <a title="推广明细" target="navTab" href="lvUserPromotersMngAction!getDetailList?id=${item.id}&json.navTabId=${json.navTabId}" rel="lvUserPromoters"><span>推广明细</span></a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>