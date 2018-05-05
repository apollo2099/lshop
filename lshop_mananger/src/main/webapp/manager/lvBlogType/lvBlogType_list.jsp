<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvBlogTypeAction!list.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvBlogTypeAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>序号</label></td><td><input name="lvBlogType.id" type="text" size="20" value="${lvBlogType.id}"/></td>
						<td><label>类别名称</label></td><td><input name="lvBlogType.type" type="text" size="20" value="${lvBlogType.type}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvBlogType.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogType.storeId}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
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
				<li><a class="add" href="lvBlogTypeAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogType" rel="lvBlogType" width="450" height="250"  mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvBlogTypeAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="5%" orderField="tplKey">序号</th>
							<th width="44%">类型</th>
							<th width="10%">排序值</th>
							<th width="10%">所属关系</th>
							<th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								
								<td>${item.type }</td>
								<td>${item.orderId }</td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
									</c:foreach>
								</td>
					<td>
						<a class="btnEdit" href="lvBlogTypeAction!befEdit.action?lvBlogType.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogType" rel="lvBlogType" title="修改" width="450" height="250" mask="true">修改</a>
						<a class="btnDel" href="lvBlogTypeAction!del.action?lvBlogType.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
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