<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvPromtPayMngAction!getVideoList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvPromtPayMngAction!getVideoList.action" method="post">
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
				    <li><a class="add" href="lvPromtPayMngAction!bfAddVideo.action?json.navTabId=${json.navTabId}" height="300"  width="450" target="dialog"  mask="true" title="添加模块"><span>添加</span></a></li>
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:20%;">视频地址</th>
						<th style="width:20%;">flash地址</th>
						<th style="width:20%;">html地址</th>
						<th style="width:5%;">视频名称</th>
						<th style="width:10%;">视频描述</th>
						<th style="width:25%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="obj">
					<tr>
						<td><input name="ids" value="${obj.id}" type="checkbox" class='np'></td>
						<td>${obj.videoAdd}</td>
						<td>${obj.flashAdd}</td>					
						<td>${obj.htmlAdd}</td>
						<td>${obj.videoName}</td>
						<td>${obj.videoDesc}</td>
						<td>
					        <a title="编辑" height="300"  width="450" target="dialog" mask="true" href="lvPromtPayMngAction!befEditVideo?id=${obj.id}&json.navTabId=${json.navTabId}" class="btnEdit">编辑</a>
						    <a href="lvPromtPayMngAction!deleteVideo.action?ids=${obj.id}&json.navTabId=${json.navTabId}" target="navTabTodo" title="确定删除？" lass="btnDel">删除</a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
