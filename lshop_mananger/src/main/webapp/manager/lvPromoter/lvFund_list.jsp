<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvPromtPayMngAction!getFundList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPromtPayMngAction!getFundList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar">
				<ul class="searchContent">
				    <li>
						<label>推广码：</label>
						<input name="promtCode" type="text" id="promtCode" value="${promtCode}" width="20"/>
				   </li>
				 </ul>
				 <div class="subBar">
				         <ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent"><button type="submit">检索</button></div>
								</div>
							</li>
						</ul>
				</div>
			</div>
		</form>
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
				  <li><a class="edit" href="lvPromtPayMngAction!exportFund.action?json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li>  
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:10%;">姓名</th>
						<th style="width:10%;">邮件</th>
						<th style="width:10%;">推广码</th>
						<th style="width:5%;">总台数</th>
						<th style="width:10%;">总基金</th>
						<th style="width:10%;">已报销基金</th>
						<th style="width:10%;">可用广告基金</th>
						<th style="width:30%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="obj">
					<tr>
						<td><input name="ids" value="${obj.id}" type="checkbox" class='np'></td>
						<td>${obj.realname}</td>
						<td>${obj.email}</td>					
						<td>${obj.couponCode}</td>
						<td>${obj.totalnum}</td>
						<td>${obj.totalfund}</td>
						<td>${obj.disablefund}</td>
						<td>${obj.enablefund}</td>
						<td>
					        <a title="报销" target="dialog" mask="true" href="lvPromtPayMngAction!befExpense?uid=${obj.uid}&json.navTabId=${json.navTabId}" class="btnEdit">报销</a>
						    <a title="报销历史" target="navTab" href="lvPromtPayMngAction!getFundDetail?uid=${obj.uid}&json.navTabId=${json.navTabId}" class="btnLook"><span>报销历史</span></a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
