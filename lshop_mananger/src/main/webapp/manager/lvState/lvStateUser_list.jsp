<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvStateUserAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvStateUserAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
	    <div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>Email</label><input type="text" name="lvStateUser.userEmail" value="${lvStateUser.userEmail}" maxlength="50"/></td>
						<td><label>昵称</label><input type="text" name="lvStateUser.nickName" value="${lvStateUser.nickName}" maxlength="50"/></td>
						<td><label>注册时间</label><input type="text" name="lvStateUser.startTime" value="${lvStateUser.startTime}" class="date"/>
						-<input type="text" class="date" name="lvStateUser.endTime" value="${lvStateUser.endTime}"></td>
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
			<li><a class="icon" href="lvStateUserAction!exportUserStat.action?lvStateUser.startTime=${lvStateUser.startTime }&lvStateUser.endTime=${lvStateUser.endTime }&json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要数据同步这些记录吗?该操作请谨慎!" target="ajaxTodo" href="lvStateUserAction!synchronousData.action?json.navTabId=${json.navTabId}" class="delete"><span>数据同步</span></a></li>
		    </sec:authorize>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="148">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="15%">Email</th>
							<th width="10%">呢称</th>
							<th width="10%">未支付订单数</th>
							<th width="10%">已支付订单数</th>
							<th width="10%">重复购买数</th>
							<th width="10%">总订单数目</th>
							<th width="10%">销售额(USD)</th>
							<th width="10%">注册时间</th>
					        <th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					<td>${item.userEmail }</td>
					<td>${item.nickName }</td>
					<td>${item.countUnPay}</td>
					<td>${item.countPay }</td>
					<td>${item.countAll-1 } </td>
					<td>${item.countAll } </td>
                    <td>${item.totalPrice }</td>
                    <td><s:date name="registerTime " format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
					<td>
					<sec:authorize url="userEdit">
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
</div>