<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvPromtPayMngAction!getFundList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPromtPayMngAction!getFundList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar">
				<table class="searchContent" layoutH="690">
					<tr>
					  <td><label>总推广台数: ${totalnum}</label></td>
					  <td><label>当前推广者:${realname}</label></td>
					</tr><tr>
							<td><label>总基金:${totalfund}</label></td>
							<td><label>已报销：${disablefund}</label><label>可用基金：${enablefund}</label></td>
					</tr>
				 </table>
				 <div class="subBar">
				         <ul>
				         <!-- 
							<li>
								<div class="buttonActive">
									<div class="buttonContent"><button type="submit">检索</button></div>
								</div>
							</li>
						 -->
						</ul>
				</div>
			</div>
		</form>
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
				  
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:25%;">报销时间</th>  
						<th style="width:15%;">报销金额</th>
						<th style="width:15%;">报销说明</th>
						<th style="width:40%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="obj">
					<tr>
						<td><input name="ids" value="${obj.id}" type="checkbox" class='np'></td>
						<td>${obj.expensetime}</td>  
						<td>${obj.fund}</td>					
						<td>${obj.detail}</td>
						<td>

						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
