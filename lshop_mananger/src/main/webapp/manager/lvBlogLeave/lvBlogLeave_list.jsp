<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogLeaveAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvBlogLeaveAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar" style="display:none">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>序号</label></td><td><input name="lvBlogType.id" type="text" size="20" value="${lvBlogType.id}"/></td>
						<td><label>类别名称</label></td><td><input name="lvBlogType.type" type="text" size="20" value="${lvBlogType.type}"/></td>
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
		<div class="panelBar" style="display:none">
			<ul class="toolBar">
				<li><a class="add" href="lvBlogLeaveAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogType" rel="lvBlogType" width="450" height="350"  mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvBlogLeaveAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="60">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="35%" orderField="tplKey">文章标题</th>
							<th width="35%">评论内容</th>
							<th width="5%">评论人</th>
							<th width="9%">评论时间</th>
							<th width="5%">所属关系</th>
							<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>
								 <s:if test="%{null!=#item.lvBlogContent.title&&#item.lvBlogContent.title.length()>40}">
                           <s:property value="%{#item.lvBlogContent.title.substring(0, 40)}" />
                           ……
                          </s:if>
                          <s:else><s:property value="#item.lvBlogContent.title"/></s:else>
								</td>
								<td>
			               <s:if test="%{null!=#item.content&&#item.content.length()>30}">
                           <s:property value="%{#item.content.substring(0, 30)}" />
                           ……
                           </s:if>
                           <s:else><s:property value="#item.content"/></s:else>
								</td>
								<td>${item.userName }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
									</c:foreach>
							</td>	
								
					<td>
						<a  href="lvBlogLeaveAction!replyLook.action?lvBlogLeave.id=${item.id}&lvBlogLeave.storeId=${item.storeId }&json.navTabId=lvBlogLeave_1"  rel="lvBlogLeave_1"  target="navTab" title="管理员留言评论回复">回复</a>
						<a  href="lvBlogLeaveAction!del.action?lvBlogLeave.id=${item.id}&lvBlogLeave.userName=${item.userName }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>