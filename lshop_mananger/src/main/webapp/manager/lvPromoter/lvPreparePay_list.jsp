<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvPromtPayMngAction!list.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPromtPayMngAction!list.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar">
				<ul class="searchContent">
				   <li>
						<label>真实姓名：</label>
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
				  <li><a class="edit" href="lvPromtPayMngAction!exportExl.action?json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li> 
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:5%;">真实姓名</th>
						<th style="width:15%;">邮箱</th>
						<th style="width:15%;">联系电话</th>
						<th style="width:10%;">账号类型</th>
						<th style="width:10%;">账号</th>	
						<th style="width:5%;">可结算金额</th>	
						<th style="width:5%;">可结算产品数</th>	
						<th style="width:5%;">已结算金额</th>
						<th style="width:5%;">已结算产品数</th>	
						<th style="width:20%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="prmt">
					<tr>
						<td><input name="ids" value="${prmt.id }" type="checkbox" class='np'></td>
						<td>${prmt.realName}</td>
						<td>${prmt.email}</td>
						<td>${prmt.tel}</td>	
						<td><c:if test="${prmt.accountTypes==1}">PayPal</c:if><c:if test="${prmt.accountTypes==2}">支付宝</c:if></td>
						<td>${prmt.accountNumber}</td>
						<td>${prmt.settlementAmount}</td>
						<td>${prmt.settlementNum}</td>
						<td>${prmt.settlementedAmount}</td>
						<td>${prmt.settlementedNum}</td>
						<td>
					      <a title="预结算?" target="ajaxTodo" href="lvPromtPayMngAction!preparePay.action?id=${prmt.id}&json.navTabId=${json.navTabId}"  class="btnSelect"><span>预结算</span></a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
