<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7&mallSysFlag=${mallSysFlag }" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvActivityAction!selectSimpleACLottery.action?lvActivity.typeKey=7&mallSysFlag=${mallSysFlag }" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>抽奖活动名称:</label>
				<input class="textInput" name="lvActivity.activityTitle" value="${lvActivity.activityTitle }" type="text">
			</li>	
			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th >抽奖活动名称</th>
				<th >活动开始时间</th>
				<th >活动结束时间</th>
				<th >查找带回</th>
			</tr>
		</thead>
		<tbody>
		    <s:iterator value="#request.page.list" status="stat" id="item">
			<tr>
			    <td>${item.activityTitle }</td>
				<td><s:date name="startTime" format="yyyy-MM-dd"/></td>
				<td><s:date name="endTime" format="yyyy-MM-dd"/></td>
				<td>
					<%-- <a class="btnSelect" href="javascript:$.bringBack({acLotteryCode:'<s:property escapeHtml="true" value="#item.code"/>', acLotteryName:'<s:property escapeHtml="true" value="#item.activityTitle"/>'})" title="查找带回">选择</a>--%>
					<a class="btnSelect" href="javascript:$.bringBack({acLotteryCode:'${item.code}', acLotteryName:'${item.activityTitle }'})" title="查找带回">选择</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>

	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="dialog" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>