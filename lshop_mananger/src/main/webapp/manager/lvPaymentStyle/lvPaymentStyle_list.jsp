<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvPaymentStyleMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvPaymentStyleMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
		   <tr>
						</tr><tr>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvPaymentStyle.storeFlag" style="width:130px;" id="storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvPaymentStyle.storeFlag}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
						<td><label>支付名称</label><input name="lvPaymentStyle.payName" type="text" size="20" value="${lvPaymentStyle.payName}"/></td>
						<td><label>支付方式</label>
						<select name="lvPaymentStyle.payValue" style="width:130px;">
						<option value="">==请选择==</option>
						 <s:iterator value="@com.lshop.common.util.Constants@PAY_METHODS" status="stat" id="item">
				             <option value="${item.key }" <c:if test="${lvPaymentStyle.payValue==item.key}">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
					    </select>
						</td>
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
				<li><a class="add" href="lvPaymentStyleMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPaymentStyle" rel="lvPaymentStyle" width="450" height="300" mask="true"><span>添加</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="145">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" width="50">编号</th>
							<th width="10%" >所属关系</th>
							<th width="30%" >支付名称</th>
							<th width="10%" >支付类型</th>
							<th width="10%" >支付方式值</th>
							<th width="10%" >支付币种</th>
							<th width="8%" >排序值</th>
							<th width="8%" >是否开启</th>
					        <th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeFlag}">${store.name}</c:if>
									</c:foreach>
								</td>
								<td>${item.payName }</td>
								<td>${item.payType eq 0 ? "在线支付" : "在线充值" }</td>
								<td>${item.payValue }</td>
								<td>${item.currency }</td>
								<td>${item.orderValue}</td>
								<td> <s:if test="isActivity==1">开启</s:if><s:else>关闭</s:else></td>
					<td>
						<a class="btnEdit" href="lvPaymentStyleMngAction!befEdit.action?lvPaymentStyle.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPaymentStyle" rel="lvPaymentStyle" title="编辑" width="700" height="550" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>