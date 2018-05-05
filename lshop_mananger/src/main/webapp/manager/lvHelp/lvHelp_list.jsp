<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvHelpMngAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvHelpMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
				<tr>
						</tr><tr>
						<td><label>帮助类别</label></td><td><select name="lvHelp.helpId" class="required">
							<option value="">==请选择==</option>
							<s:iterator value="#request.helpTypeList" id="h">
							<option value="${h.id}"
							<c:if test="${lvHelp.helpId==h.id}">selected="selected"</c:if>
							>${h.name}</option></s:iterator>
							</select></td>
						<td><label>名称</label></td><td><input name="lvHelp.name" type="text" size="20" value="${lvHelp.name}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvHelp.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvHelp.storeId}">selected="selected"</c:if>>${store.name}</option>
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
				<li><a class="add" href="lvHelpMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvHelp" rel="lvHelp" width="900" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvHelpMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="165">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="30">编号</th>
							<th width="90">所属关系</th>
							<th orderField="name" width="90" class="${orderField eq 'name' ? orderDirection : ''}">名称</th>
							<th width="90">所属类别</th>
							<th width="60">排序值</th>
							<th width="120">帮助内容</th>
							<th width="120">语言</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					<td>${stat.index+1 }</td>
					<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
					</td>
					<td>${item.name }</td>
					<td><s:iterator value="#request.helpTypeList" id="h"><s:if test="#h.id==#item.helpId">${h.name}</s:if></s:iterator></td>
					<td>${item.orderValue}</td>
					<td title="<s:property value="content" escape="true"/>"><s:if test="%{content.length()>60}">
                          <s:property value="content.substring(0,60)+'...'"/>
                         </s:if>
                         <s:else>
                        <s:property value="content" escape="true"/>
                         </s:else>
                    </td>
                    	<td>
					<c:if test="${item.webLanguage=='cn'}">中文简体</c:if>
					<c:if test="${item.webLanguage=='tw'}">中文繁体</c:if>
					<c:if test="${item.webLanguage=='en'}">英文</c:if>
					<c:if test="${item.webLanguage=='kn'}">韩文</c:if>
					<c:if test="${item.webLanguage=='ja'}">日文</c:if>
					</td>
					<td>
						<a class="btnDel" href="lvHelpMngAction!del.action?lvHelp.id=${item.id}&json.navTabId=${json.navTabId}" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
						<a class="btnEdit" href="lvHelpMngAction!befEdit.action?lvHelp.id=${item.id}&lvHelpType.storeId=${item.storeId }&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvHelp" rel="lvHelp" title="编辑" width="900" height="500" mask="true">编辑</a>
						<a class="btnView" href="lvHelpMngAction!view.action?lvHelp.id=${item.id}" target="dialog" navTabId="lvHelp" rel="lvHelp" title="查看" width="850" height="500">查看</a>
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