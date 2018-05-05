<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld" %>
<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvNetworkAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvNetworkAction!list.action" method="post"  rel="pagerForm" >
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
				<tr>
						</tr><tr>
						<td><label>所属商城</label></td><td><select name="lvNetwork.storeId" class="required">
							<option value="">==请选择==</option>
							<s:iterator value="#request.storeList" id="store">
							<option value="${store.storeFlag}"
							<c:if test="${lvNetwork.storeId==store.storeFlag}">selected="selected"</c:if>
							>${store.name}</option></s:iterator>
							</select></td>
						<td><label>所属国家</label></td>
						<td>
						<select name="lvNetwork.contryId">
						<option value="">==请选择==</option>
						<c:foreach items="${areaList}" var="area">
						<option  value="${area.id }" <c:if test="${lvNetwork.contryId==area.id}">selected="selected"</c:if>>${area.namecn}</option>
					    </c:foreach>
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
				<li><a class="add" href="lvNetworkAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNetwork" rel="lvNetwork" width="900" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvNetworkAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="165">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="20">编号</th>
					<th width="50">商城</th>
					<th width="60">国家</th>
					<th width="60">城市</th>
					<th width="60">网点名称</th>
					<th width="220">详细地址</th>
					<th width="40">负责人</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<td>${item.id}</td>
					<td><cus:store param="storeName" shopFlag="${item.storeId}"></cus:store></td>
					<td>${item.country }</td>
					<td>${item.city }</td>
					<td>${item.channelName}</td>
					<td>${item.address}</td>
                    <td>${item.responsiblePerson}</td>
					<td>
						<a class="btnDel" href="lvNetworkAction!del.action?lvNetwork.id=${item.id}&json.navTabId=${json.navTabId}" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
						<a class="btnEdit" href="lvNetworkAction!befEdit.action?lvNetwork.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNetwork" rel="lvNetwork" title="编辑" width="900" height="500" mask="true">编辑</a>
						<a class="btnView" href="lvNetworkAction!view.action?lvNetwork.id=${item.id}" target="dialog" navTabId="lvNetwork" rel="lvNetwork" title="查看" width="850" height="500">查看</a>
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