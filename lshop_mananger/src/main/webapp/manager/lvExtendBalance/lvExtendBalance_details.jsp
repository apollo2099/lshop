<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvExtendBalanceAction!getBalanceDetails.action?lvExtendBalance.balanceId=${lvExtendBalance.balanceId}&json.navTabId=${json.navTabId }" name="pagerFormAction"/>
</jsp:include>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
		     <li><a class="icon" href="lvExtendBalanceAction!exportDetailsBalance.action?json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="确定要导出这些记录吗?"  rel="ids"><span>导出结算清单</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="12%">结算单编号</th>
				<th width="15%">订单编号</th>
				<th width="10%">下单时间</th>
				<th width="10%">支付时间</th>
				<th width="15%">产品名称</th>
				<th width="5%">套餐数</th>
				<th width="5%">订单台数</th>
				<th width="5%">邮件费用</th>
				<th width="10%">订单总金额</th>
				<th width="7%">优惠码</th>
				<th width="5%">操作</th>
			</tr>
		</thead>
		<tbody>
		   <s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.balanceId}" type="checkbox"></td>
				<td>${item.balanceId }</td>
				<td>${item.orderId }</td>
				<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="overtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.productName }</td>
				<td><c:if test="${item.isPackage==1}">${item.pnum }</c:if></td>
				<td><c:if test="${item.isPackage!=1}">${item.pnum }</c:if><c:if test="${item.isPackage==1}">${item.MealPnum}</c:if></td>
				<td>${item.postprice }</td>
				<td>${item.totalPrice }</td>
				<td>${item.couponCode }</td>
				<td>
				<c:if test="${lvExtendBalance.balanceStatus==0}">
				<!-- 
				<a class="btnDel" href="lvExtendBalanceAction!deleteBalanceDetails.action?balanceId=${item.balanceId}&orderId=${item.orderId }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
				 -->
				<a class="btnDel" href="lvExtendBalanceAction!updateExtendBalance.action?lvExtendBalance.id=${lvExtendBalance.id}&balanceId=${item.balanceId}&orderId=${item.orderId }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确定要删除结算单详情关系，并且更新结算单信息?">删除</a>
				</c:if>
				</td>
				
			</tr>
	      </s:iterator>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
