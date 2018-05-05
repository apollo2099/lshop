<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogContentAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
</form>



<div class="page">
<div class="pageHeader">
       
	<form onsubmit="return navTabSearch(this);"
		action="lvBlogContentAction!list.action?json.navTabId=${json.navTabId}"
		method="post" rel="pagerForm">
			<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>博客类别</label></td><td><select id="locationId" name="lvBlogContent.type">
						<option value="">
							所有文章
						</option>
						<s:iterator value="#request.typeList" id="t">
							<option
								<s:if test="#request.tempType==#t.id">selected="selected"</s:if>
								value="${t.id}">
								${t.type}
							</option>
						</s:iterator>
					</select></td>
						<td><label>文章标题</label></td><td><input type="text" name="lvBlogContent.title" value="${lvBlogContent.title}" /></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvBlogContent.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogContent.storeId}">selected="selected"</c:if>>${store.name}</option>
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
				<li><a class="add" href="/manager/lvBlogContentAction!befAdd.action?json.navTabId=${json.navTabId}" target="navTab" navTabId="lvBlogType" rel="lvBlogType" width="1020" height="650"  mask="true"><span>发表博客</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvBlogContentAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
						    
							<th width="44%" orderField="tplKey">标题</th>
							<th width="10%" >分类</th>
							<th width="5%">浏览</th>
							<th width="5%">回复</th>
							<th width="5%">排序值</th>
							<th width="5%">发布人</th>
							<th width="10%">发布时间</th>
							<th width="5%">所属关系</th>
							<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>  <s:if test="%{null!=#item.content&&#item.title.length()>40}">
                           <s:property value="%{#item.title.substring(0, 40)}" />
                           ……
                          </s:if>
                          <s:else><s:property value="#item.title"/></s:else></td>
								<td>
								<s:iterator value="#request.typeList" id="t">
							    <s:if test="#t.id==#item.type">${t.type}<s:set name="flag"
									value="false" />
							    </s:if>
						        </s:iterator>
						        <s:if test="#flag==true">
							          未分类
							    </s:if>
								</td>
								<td>${item.clickNum }</td>
								<td>${item.replyNum }</td>
								<td>${item.orderId  }</td>
								<td>${item.userName }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
									</c:foreach>
								</td>
					<td>
					    <a class="btnView" href="lvBlogContentAction!view.action?lvBlogContent.id=${item.id}" target="dialog" navTabId="lvBlogContent" rel="lvBlogContent" title="查看" width="1020" height="650" mask="true">查看</a>
						<a class="btnEdit" href="lvBlogContentAction!befEdit.action?lvBlogContent.id=${item.id}&lvBlogType.storeId=${item.storeId }&json.navTabId=${json.navTabId}" target="navTab" navTabId="lvBlogContent" rel="lvBlogContent" title="修改博客" width="1020" height="650" mask="true">修改</a>
						<a class="btnDel" href="lvBlogContentAction!del.action?lvBlogContent.id=${item.id}" target="ajaxTodo" title="删除">删除</a>
						
						
						
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>