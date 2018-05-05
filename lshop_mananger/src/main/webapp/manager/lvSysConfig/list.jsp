<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/manager/common/pagerForm.jsp">
<jsp:param value="sysConfigMngAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>
<script type="text/javascript">
</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="/manager/sysConfigMngAction!list.action?json.navTabId=${json.navTabId}"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>配置名称：</label> <input type="text"
						name="config.name" value="${config.name}"
						maxlength="128" /></td>						
					<td><label>配置值：</label> <input type="text"
						name="config.val" value="${config.val}"
						maxlength="512" /></td>						
					<td><label>配置类型：</label>
						    <select name="config.type">
							<option value="">--请选择--</option>
							<option  value="0" <c:if test="${config.type==0 }">selected</c:if>>公共配置</option>
							<option  value="1" <c:if test="${config.type==1 }">selected</c:if>>tvpad项目</option>
							<option  value="2" <c:if test="${config.type==2 }">selected</c:if>>banana项目</option>
					        </select>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>


<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
		<li><a class="add" href="/manager/sysConfigMngAction!befAdd.action?json.navTabId=${json.navTabId}" 
			target="dialog" navTabId="lvStore" rel="lvStore" width="700" height="350" mask="true"><span>添加</span></a></li>
		<li class="line">line</li>
		<li><a class="add" href="/manager/sysConfigMngAction!initData.action?json.navTabId=${json.navTabId}" 
		target="ajaxTodo" title="确定?该操作请谨慎!"><span>同步到前台</span></a></li>
		<li class="line">line</li>
		</sec:authorize>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="142">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="12%">名称</th>
				<th width="15%">配置值</th>
				<th width="10%">描述</th>
				<th width="6%">类型</th>
				<th width="10%" >创建时间</th>
				<th width="10%" >更新时间</th>
				<th width="6%" >最后修改人</th>
				<th width="8%" >操作</th>
			</tr>
		</thead>
		<tbody>
		
		<s:iterator value="page.list" status="stat" id="item">
		<tr>
          <td><input name="ids" value="${item.id }" type="checkbox"></td>	
				<td title="${item.name }">${fn:substring(item.name, 0, 30)}</td>
				<td title="${item.val }">${fn:substring(item.val, 0, 45)}</td>
				<td title="${item.cdesc }">${fn:substring(item.cdesc, 0, 30)}</td>
				<td>
					<c:if test="${item.type==0 }">公共配置</c:if>
					<c:if test="${item.type==1 }">tvpad项目</c:if>
					<c:if test="${item.type==2 }">banana项目</c:if>
				</td>
				<td><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#item.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.modifyUserName }</td>
				<td>
					<a title="查看" target="dialog" mask="true" href="/manager/sysConfigMngAction!view.action?json.navTabId=${json.navTabId}&config.id=${item.id }" 
					class="btnView" width="700" height="350"></a>
					<a title="编辑" target="dialog" mask="true" href="/manager/sysConfigMngAction!befEdit.action?json.navTabId=${json.navTabId}&config.id=${item.id }" 
					class="btnEdit" width="700" height="350"></a>
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo"
					href="/manager/sysConfigMngAction!del.action?config.id=${item.id}&json.navTabId=${json.navTabId}" class="btnDel"></a>
				</td>
		</tr>
		</s:iterator>
		</tbody>
	</table>
	
	<jsp:include page="/manager/common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>

