<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<form id="pagerForm" method="post" action="lvPatternCountryAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvPatternCountryAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
						</tr><tr>
							<td><label>国家名称</label></td><td><input name="lvPatternCountry.countryName" type="text" size="20" value="${lvPatternCountry.countryName}"/></td>
							<td><label>国家编码</label></td><td><input name="lvPatternCountry.countryCode" type="text" size="20" value="${lvPatternCountry.countryCode}"/></td>
						    <td><label>规格</label></td><td><gv:dictionary type="select" code="PRODUCT_PATTERN_KEY"  name="lvPatternCountry.patternCode" value="${lvPatternCountry.patternCode }"/></td>
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
				<li><a class="add" href="lvPatternCountryAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPatternCountry" rel="lvPatternCountry" width="500" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvPatternCountryAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="140">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<!-- 生成表单 -->
					<th width="5%">id</th>
					<th width="5%">version</th>
					<th width="15%">规格</th>
					<th width="15%">国家名称</th>
					<th width="15%">国家编码</th>
					<th width="20%">创建时间</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.version }</td>
								<td><gv:dictionary type="text" code="PRODUCT_PATTERN_KEY" value="${item.patternCode}"/></td>
								<td>${item.countryName }</td>
								<td>${item.countryCode }</td>
								<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnView" href="lvPatternCountryAction!view.action?lvPatternCountry.id=${item.id}" target="dialog" navTabId="lvPatternCountry" rel="lvPatternCountry" title="查看" width="600" height="450">查看</a>
						<%--
						<a class="btnEdit" href="lvPatternCountryAction!befEdit.action?lvPatternCountry.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPatternCountry" rel="lvPatternCountry" title="编辑" width="850" height="500" mask="true">编辑</a>
						 --%>
						<a class="btnDel" href="lvPatternCountryAction!del.action?lvPatternCountry.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>