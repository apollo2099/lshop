<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvUserPromotersMngAction!getDetailList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!getDetailList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="id" value="${param.id}"/>
			<div class="searchBar">
				<ul class="searchContent">
				   <li>
						<label>订单号：</label>
						<input name="keyword" type="text" id="keyword" value="${keyword }" width="20"/>
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
				  <li><a class="edit" href="lvUserPromotersMngAction!exportOrderGrade.action?json.navTabId=${json.navTabId}&id=${param.id}" target="dwzExportString" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li>  
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:10%;">订单号</th>
						<th style="width:15%;">下单时间</th>
						<th style="width:10%;">支付成功时间</th>
						<th style="width:10%;">购买套餐数量</th>	
						<th style="width:10%;">购买产品数量</th>	
						<th style="width:10%;">邮寄费用</th>	
						<th style="width:10%;">订单总金额</th>	
						<th style="width:20%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.list" status="stat" id="item">
					<tr>
						<td><input name="ids" value="${item.oid}" type="checkbox" class='np'></td>
						<td>${item.oid}</td>
						<td>${item.createTime}</td>					
						<td>${item.overtime}</td>
						<td><c:if test="${item.isPackage==1}">${item.pnum}</c:if></td>
						<td><c:if test="${item.isPackage!=1}">${item.pnum}</c:if><c:if test="${item.isPackage==1}">${item.MealPnum}</c:if></td>
						<td>${item.postprice}</td>
						<td>${item.totalPrice}</td>
						<td>
						
						</td>
					</tr>	
					</s:iterator>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
