<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvOrderAction!userOrderStat.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderAction!userOrderStat.action" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>注册时间</label><input type="text" name="lvAccount.startTime" value="${lvAccount.startTime}" class="date"/>
						-<input type="text" class="date" name="lvAccount.endTime" value="${lvAccount.endTime}"></td>
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
			<li><a class="icon" href="lvOrderAction!exportUserStat.action?lvOrder.startTime=${lvOrder.startTime }&lvOrder.endTime=${lvOrder.endTime }&json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			<li class="line">line</li>
			<!-- 
			<li><a class="delete" href="lvAccountAction!deleteByEmail.action?json.navTabId=${json.navTabId}" target="dwzSelectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			 -->
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th >Emial</th>
				<th >昵称</th>
				<th >未支付订单</th>
				<th >已经支付订单</th>
				<th >重复购买次数</th>
				<!-- 
				<th >订单总台数</th>
				 -->
				<th >总订单数</th>
				<th >销售额(USD)</th>
				<th >注册时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="page.list"  id="item">
			<tr>
				<td><input name="ids" value="${item.userEmail }" type="checkbox"></td>
				<td>${item.userEmail }</td>
				<td>${item.nickname }</td>
				<td>${item.countUnPay}</td>
				<td>${item.countPay  }</td>
				<td>${item.countAll-1 }</td>
			    <!-- 
				<td></td>
				-->
				<td>${item.countAll }</td>
				<td>${item.totalPrice }</td>
				<td><s:date name="createTime " format="yyyy-MM-dd HH:mm"/></td>
				<td>
				<sec:authorize url="userEdit">
				    <!-- 
					<a class="btnDel"  href="lvAccountAction!deleteByEmail.action?lvAccount.email=${item.userEmail }&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
					 -->
					<a  href="lvOrderAction!userOrderList.action?lvOrder.userEmail=${item.userEmail}&json.navTabId=userOrderList_1"  rel="userOrderList_1" title="查看订单" target="navTab"  navTabId="lvProduct" rel="lvProduct"  mask="true">查看订单</a>
				</sec:authorize>	
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
