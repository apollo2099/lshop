<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<jsp:include page="/manager/common/pageBreak.jsp">
	<jsp:param
		value="/manager/linkUrl!list.action?json.navTabId=${json.navTabId}"
		name="pagerFormAction" />
</jsp:include>
<div class="pageHeader">
	<form  onsubmit="return navTabSearch(this);" action="/manager/linkUrl!list.action?json.navTabId=${json.navTabId }" method="post">
	<div class="searchBar">
				<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>所属关系</label></td>
						<td>
						<select name="vo.storeFlag">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==vo.storeFlag}">selected="selected"</c:if>>${store.name}</option>
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
			<li><a class="add" href="/manager/linkUrl!bufAdd.action?json.navTabId=${json.navTabId}" target="dialog" rel="linkUrl" mask="true" width="500" height="300"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a  href="/manager/linkUrl!del.action?json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?" target="selectedTodo"  class="delete" rel="ids"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a  href="/manager/linkUrl!htmlStaticBatch.action?json.navTabId=${json.navTabId }" title="确实要批量静态化这些文件吗?" target="selectedTodo"  class="delete" rel="ids"><span>批量静态化</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="148">
		<thead>
			<tr>
				<th width="5%"><input type="checkbox" name="orderIndexs" group="ids" class="checkboxCtrl">编号</th>
				<th width="10%">所属关系</th>
				<th width="10%">名称</th>
				<th width="20%">动态地址</th>
				<th width="15%">静态地址</th>
				<th width="20%">HQL语句</th>
				<th width="10%">备注</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<s:if test="!page.list.isEmpty()">
		<tbody>
		<s:iterator value="page.list" status="obj" >
			<tr target="sid_user">
				<td><input name="ids" value="<s:property value="id" />" type="checkbox"><s:property value="#obj.count"/></td>
				<td>
				<c:foreach items="${storeList}" var="store">
				    <c:if test="${storeFlag==store.storeFlag }">${store.name }</c:if>
				</c:foreach>
				</td>
				<td><s:property value="name"/></td>
				<td><s:property value="dynamicurl"/></td>
				<td>
				${staticurl}
				</td>
				<td>${hql }</td>
				<td><s:property value="note" /></td>
				<td>
					<a title="删除" target="ajaxTodo" href="/manager/linkUrl!del.action?ids=<s:property value="id" />&json.navTabId=${json.navTabId }"  class="btnDel">删除</a>
					<a title="修改"  width="500" height="300" target="dialog"  href="/manager/linkUrl!view.action?id=<s:property value="id" />&json.navTabId=${json.navTabId }" class="btnEdit">修改</a>
					<a href="/manager/linkUrl!htmlStatic.action?id=<s:property value="id" />&json.navTabId=${json.navTabId }"  title="确实要静态化该组页面吗?该操作请谨慎!" target="ajaxTodo">静态化</a>
				</td>
			</tr>
		</s:iterator>
		</tbody>
		</s:if>
	</table>
	<%@ include file="../common/panelBar.jsp"%>
</div>

