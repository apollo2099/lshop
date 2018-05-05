<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvUserPromotersMngAction!accountRecord.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!accountRecord.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar" >
				<ul class="searchContent">
				   <li>
						<label>真实姓名：</label>
						<input name="realName" type="text" id="realName" value="${realName }" width="20"/>
				   </li>
				   <li>
						<label>邮箱：</label>
						<input name="email" type="text" id="email" value="${email}" width="20"/>
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
				  <li><a class="edit" href="lvUserPromotersMngAction!exportRecords.action?json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出Excel表格</span></a></li>
				</ul>
			</div>
			<table class="table" layouth="160" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl">全选</th>
						<th style="width:9%;">结算处理时间</th>
						<th style="width:9%;">真实姓名</th>
						<th style="width:9%;">邮箱</th>
						<th style="width:9%;">联系电话</th>
						<th style="width:9%;">账户类型</th>
						<th style="width:9%;">账户</th>
						<th style="width:9%;">结算订单数</th>
						<th style="width:9%;">结算产品数</th>
						<th style="width:9%;">结算金额</th>	
						<th style="width:14%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${objList}" var="obj">
					<tr>
						<td><input name="ids" value="${obj[10]}" type="checkbox" class='np'></td>
						<td>${obj[0]}</td>
						<td>${obj[1]}</td>
						<td>${obj[2]}</td>					
						<td>${obj[3]}</td>
						<td>
							<c:if test="${obj[4]==1}">PayPal</c:if>
			        		<c:if test="${obj[4]==2}">支付宝</c:if>
						</td>
						<td>${obj[5]}</td>
						<td>${obj[6]}</td>					
						<td>${obj[7]}</td>
						<td>${obj[8]}</td>
						<td>
						 <a class="btnLook" href="lvUserPromotersMngAction!bfViewRankPromoter.action?id=${obj[9]}" target="dialog"  title="用户资料" width="850" height="500" mask="true">用户资料</a>
						 <a title="订单详细" target="navTab" href="lvUserPromotersMngAction!getOrderList?aids=${obj[11]}&json.navTabId=${json.navTabId}" class="btnLook"><span>订单详细</span></a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
