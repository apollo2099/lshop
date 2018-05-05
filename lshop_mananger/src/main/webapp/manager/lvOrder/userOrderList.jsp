<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="pagerForm" method="post" action="lvOrderAction!userOrderList.action?lvOrder.userEmail=${lvOrder.userEmail}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderAction!userOrderList.action?lvOrder.userEmail=${lvOrder.userEmail}&json.navTabId=${json.navTabId}" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>商家名称</label>
						<select name="lvOrder.storeId" >
							   <option value="">==请选择==</option>
							    <c:foreach items="${storeList}" var="store">
							   <option value="${store.storeFlag }" <c:if test="${lvOrder.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
							   </c:foreach>
							 </select>
						</td>
				        <td><label>订单编号</label>
						<input type="text" name="lvOrder.oid" value="${lvOrder.oid}" />
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
		<sec:authorize url="userEdit">
			<c:if test="${lvOrder.flag==1}">
			<li><a class="icon" href="lvOrderAction!busShop.action?json.navTabId=${json.navTabId}" target="navTab" navTabId="lvOrder" rel="lvOrder" width="850" height="500" title="商家下订单"><span>商家自主下单</span></a></li>
			<li class="line">line</li>
			</c:if>
			<li><a class="icon" href="lvOrderAction!exportOrder.action?json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			<li class="line">line</li>
			<c:if test="${lvOrder.orderFlag==1}">
			<li><a class="icon" href="lvOrderAction!befComment.action?json.navTabId=${json.navTabId}"  target="dwzDialog"  title="系统批量评论?"  width="500" height="350"  rel="ids"><span>系统批量评论</span></a></li>
			<li class="line">line</li>
			 </c:if>
			 <c:if test="${lvOrder.isdelete!=-1}">
			<li><a class="delete" href="lvOrderAction!del.action?json.navTabId=${json.navTabId}" target="dwzSelectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			</c:if>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="148">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="2%" orderField="id" class="${orderDirection}">ID</th>
				<th >商家名称</th>
				<th width="10%">订单编号</th>
				<th >收货人姓名</th>
				<th >订单总金额</th>
				<th >优惠券</th>
				<th >币种</th>
				<th >订单类型</th>
				<th >支付方式</th>
				<th >支付状态</th>
				<th >订单状态</th>
				<th orderField="createTime" class="${orderDirection}">下单时间</th>
				<th orderField="overTime" class="${orderDirection}">支付时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.oid}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				<c:foreach items="${storeList}" var="store">
				<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
				</c:foreach>
				</td>
				<td>${item.oid }</td>
				<td>${item.relName }</td>
				<td>${item.totalPrice }</td>
				<td>${item.couponCodeList }</td>
				<td>${item.currency }</td>
				<td>
				<c:if test="${item.flag==0}">用户下单</c:if>
				<c:if test="${item.flag==1}">商家下单</c:if>
				</td>
				<td>
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(#item.paymethod)"/>
				</td>
				<td>
				<c:if test="${item.payStatus==0}">未支付</c:if>
				<c:if test="${item.payStatus==1}">已支付,已确认</c:if>
				<c:if test="${item.payStatus==2}">已支付未确认</c:if>
				</td>
				<td>
				<c:if test="${item.status==0}">待发货</c:if>
				<c:if test="${item.status==1}">已发货，未确认收货</c:if>
				<c:if test="${item.status==2}">已收货 ,待评价 </c:if>
				<c:if test="${item.status==3}">已退货</c:if>
				<c:if test="${item.status==4}">已完成</c:if>
				</td>
				<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
				<td><s:date name="overtime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
				<td>
	      	<sec:authorize url="userEdit">
				    <a  href="lvOrderAction!view.action?lvOrder.id=${item.id}&lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看详情"  width="900" height="730" mask="true">查看详情</a>
			</sec:authorize>
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	
<%@ include file="../common/panelBar.jsp"%>
</div>
