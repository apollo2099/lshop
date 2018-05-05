<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function doSubAction(f){
	 if(document.getElementById("fTypeId").checked==true){
		var fileValueStr=$('#fileZId').val();
	 if(fileValueStr.indexOf('.zip')==-1){
	 alert("你当前选择上传zip文件自动解压，请选择zip文件！");
	 return false;
	 }
	
	 }
	 return iframeCallback(f,dialogAjaxDone);
	}
	
//根据店铺标识选择对应店铺的资源文件信息列表
function changeStore(){
$("#storeFlag").val($("#storeId").find("option:selected").val());
$("#pagerForm").submit();
}	
</script>

<!-- 
<form id="pagerForm" method="post"  onsubmit="return navTabSearch(this);" action="resManagerAction!list.action?json.navTabId=${json.navTabId}">
 <input type="hidden" name="storeFlag" value="${storeFlag }" id="storeFlag">
</form>
 -->
<form id="pagerForm" method="post"  onsubmit="return navTabSearch(this);" action="resManagerAction!list.action?dir=${dir}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="storeFlag" value="${storeFlag }" id="storeFlag">
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>


<form method="post" action="resManagerAction!upload.action" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return doSubAction(this);">
       <input type="hidden" name="dir" value="${dir}">
       <input type="hidden" name="json.navTabId" value="${json.navTabId}" />
       <br/>
       <p>
							<label>所属关系：</label>
							<select name="storeFlag" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==storeFlag}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
	   </p> 
	    <br/>
       <p>当前资源路径：${current_path}</p>
       <br/>
                     是否zip文件：<input type="checkbox" name="fType" id="fTypeId"  value="1"/>(选择是上传之后自动解压zip文件)
       <br/>
       <br/>
	   <p>上传文件：<input class="required" type="file" name="file" id="fileZId">&nbsp;<input type="submit" name="sub" value="上传"/></p>
	   <br/>
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="resManagerAction!list.action?dir=${dir}&topDir=${topDir }&json.navTabId=${json.navTabId}"" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" >
				<tr>
						<td><label>文件或者文件夹名称</label></td><td><input name="searchFileName" type="text" size="32" value="${searchFileName}"/></td>
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
				<li><a class="add" href="resManagerAction!befaddFolder.action?dir=${dir}&storeFlag=${storeFlag }&json.navTabId=${json.navTabId}" target="dialog" navTabId="res" rel="res" width="400" height="200" mask="true"><span>添加文件目录</span></a></li>
				<li class="line">line</li>
				<li><a  target="navTab" rel="${json.navTabId}" href="<%=request.getContextPath()%>/manager/resManagerAction!list.action?dir=${dir}&storeFlag=${storeFlag }&json.navTabId=${json.navTabId}" ><span>刷新</span></a></li>
			    <li class="line">line</li>
			    <li><a  target="navTab" rel="${json.navTabId}" href="<%=request.getContextPath()%>/manager/resManagerAction!list.action?dir=<s:property value="dir.substring(0,dir.lastIndexOf('/'))"/>&storeFlag=${storeFlag }&json.navTabId=${json.navTabId}"><span>返回上一级目录</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="280">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th >文件</th>
							<th >文件大小</th>
							<th >更新时间</th>
					        <th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				  <s:iterator value="page.list"  id="tpl">
					<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					            <td><s:if test="#tpl.directory==true"><a target="navTab" rel="${json.navTabId}"  href="<%=request.getContextPath()%>/manager/resManagerAction!list.action?json.navTabId=${json.navTabId}&storeFlag=${storeFlag }&dir=<s:if test="#request.dir!=null">${dir}/</s:if>${tpl.name}"><img src="/manager/common/images/folder.gif"/>${tpl.name}</a></s:if><s:else>${tpl.name}</s:else></td>
								<td> ${tpl.size}kb</td>
								<td> ${tpl.lastModifiedDate}</td>
					<td>
						 <s:if test="#tpl.directory==false">
						 <s:if test="#tpl.name.lastIndexOf('.txt')!=-1||#tpl.name.lastIndexOf('.css')!=-1||#tpl.name.lastIndexOf('.html')!=-1||#tpl.name.lastIndexOf('.js')!=-1">
						 <a class="btnEdit" href="resManagerAction!bfupdate.action?dir=${dir}&filePath=${dir}/${tpl.name }&storeFlag=${storeFlag }" target="dialog"  title="编辑" width="850" height="500" mask="true">编辑</a>
						 </s:if>
						 </s:if>
					     <a class="btnDel" href="resManagerAction!del.action?dir=${dir}&filePath=${dir}/${tpl.name }&storeFlag=${storeFlag }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确定删除吗？">删除</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
				<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>
