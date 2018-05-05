<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvExtendBalanceAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvExtendBalanceAction!list.action" method="post">
	<input type="hidden" name="lvExtendBalance.uid" value="${lvExtendBalance.uid }">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>结算编号</label>
						<input type="text" name="lvExtendBalance.balanceId" value="${lvExtendBalance.balanceId}" />
						</td>
				
						<td><label>结算时间</label><input type="text" name="lvExtendBalance.startTime" value="${lvExtendBalance.startTime}" class="date"/>-<input type="text" class="date" name="lvExtendBalance.endTime" value="${lvExtendBalance.endTime}"></td>
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
		     <!--
		     <li><a class="icon" href="lvExtendBalanceAction!sysBalance.action?json.navTabId=${json.navTabId}"  target="ajaxTodo" title="确实要系统手动结算这些记录吗?该操作请谨慎!"><span>系统手动结算</span></a></li>
		     <li class="line">line</li>
		     -->
		     <li><a class="icon" href="lvExtendBalanceAction!exportBalance.action?json.navTabId=${json.navTabId}"  target="dwzExport" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出结算单</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="13%">结算单编号</th>
				<th width="5%">结算台数</th>
				<th width="10%">结算金额</th>
				<th width="15%">结算时间</th>
				<th width="5%">结算状态</th>
				<th width="10%">优惠码</th>
				<th width="15%">Email</th>
				<th width="22%" >操作</th>
			</tr>
		</thead>
		<tbody>
		     <s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.balanceId }</td>
				<td>${item.balanceNum }</td>
				<td>${item.balancePrice }</td>
				<td>
				<s:date name="balanceTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${item.balanceStatus==0}">未结算</c:if>
				<c:if test="${item.balanceStatus==1}">已结算</c:if>
				</td>
				<td>${item.couponCode }</td>
				<td>${item.userEmail }</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvExtendBalanceAction!view.action?lvExtendBalance.id=${item.id}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="800" height="400" mask="true">查看</a>
					<c:if test="${item.balanceStatus==0}">
					<a href="lvExtendBalanceAction!updateBalance.action?lvExtendBalance.id=${item.id}&versionTime=${item.modifyTime }&json.navTabId=${json.navTabId }"  title="财务确认结算?" target="ajaxTodo"   mask="true" class="btnSelect">财务结算</a>
					</c:if>
					<a href="lvExtendBalanceAction!getBalanceDetails.action?lvExtendBalance.balanceId=${item.balanceId}&json.navTabId=lvExtendBalanceDetails_1" target="navTab" navTabId="lvAccount" rel="lvExtendBalanceDetails_1" title="查看结算详情"  width="900" height="600" mask="true" class="btnLook">查看结算详情</a>
					<!-- 
					<c:if test="${item.balanceStatus==0}">
					<a href="lvExtendBalanceAction!updateExtendBalance.action?lvExtendBalance.id=${item.id}&json.navTabId=${json.navTabId }"  title="确定要更新结算单吗?" target="ajaxTodo"   mask="true" class="btnSelect">更新结算单</a>
					</c:if>
					 -->
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
