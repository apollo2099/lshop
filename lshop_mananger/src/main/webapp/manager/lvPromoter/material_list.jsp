<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvPromtPayMngAction!getMaterialList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPromtPayMngAction!getMaterialList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar">
				<ul class="searchContent">
				   <li>
						
				   </li>
				 </ul>
				 <div class="subBar">
				         <ul>
							
						</ul>
				</div>
			</div>
		</form>
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
				    <li><a class="add" href="lvPromtPayMngAction!befAddMaterial.action?json.navTabId=${json.navTabId}"  target="dialog"  mask="true" title="添加素材"><span>添加素材</span></a></li>
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:10%;">素材名称</th>
						<th style="width:15%;">小图地址</th>
						<th style="width:15%;">大图地址</th>
						<th style="width:15%;">压缩包地址</th>
						<th style="width:40%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="obj">
					<tr>
						<td><input name="ids" value="${obj.id}" type="checkbox" class='np'></td>
						<td>${obj.materialName}</td>
						<td>${obj.smallPath}</td>					
						<td>${obj.bigPath}</td>
						<td>${obj.compressPath}</td>
						<td>
					        <a title="编辑素材"  target="dialog" mask="true" href="lvPromtPayMngAction!befEditMaterial?id=${obj.id}&json.navTabId=${json.navTabId}" class="btnEdit">编辑素材</a>
						    <a href="lvPromtPayMngAction!deleteMaterial.action?ids=${obj.id}&json.navTabId=${json.navTabId}" target="navTabTodo" title="确定删除？" lass="btnDel">删除</a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
