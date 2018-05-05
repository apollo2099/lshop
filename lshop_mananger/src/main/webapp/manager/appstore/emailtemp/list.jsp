<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/manager/common/pagerForm.jsp">
<jsp:param value="emailMngAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<script type="text/javascript">

</script>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);"
			action="/manager/emailMngAction!list.action?json.navTabId=${json.navTabId}"
			method="post" rel="pagerForm">
			<div class="searchBar">
				<table class="searchContent">
					<tr><td><label>模版名称：</label><input name="templ.name"
							type="text" size="20" value="${templ.name}"
							maxlength="32" /></td>
						<td><label>邮件标题：</label><input name="templ.title"
							type="text" size="20" value="${templ.title}"
							maxlength="32" /></td>
						<td><label>店铺标识：</label>
							<select name="templ.mallSign">
							<option value="">全部 </option>
							<option value="bmcn" 
							<c:if test="${templ.mallSign=='bmcn' }">selected="selected"</c:if>
							>banana中文</option>
							<option value="bmen"
							<c:if test="${templ.mallSign=='bmen' }">selected="selected"</c:if>
							>banana英文</option>
							<option value="tvpadcn"
							<c:if test="${templ.mallSign=='tvpadcn' }">selected="selected"</c:if>
							>华扬中文</option>
							<option value="tvpaden"
							<c:if test="${templ.mallSign=='tvpaden' }">selected="selected"</c:if>
							>华扬英文</option>
							</select></td>
						<td><label>模版分类：</label>
							<select name="templ.ttype">
							<option value="-1">全部 </option>
							<option value="1"
								<c:if test="${templ.ttype==1 }">selected="selected"</c:if>>开发者审核通过</option>
							<option value="2"
								<c:if test="${templ.ttype==2 }">selected="selected"</c:if>>开发者审核不通过</option>
							<option value="3"
								<c:if test="${templ.ttype==3 }">selected="selected"</c:if>>应用审核通过</option>
							<option value="4"
								<c:if test="${templ.ttype==4 }">selected="selected"</c:if>>应用审核不通过</option>
							</select></td>
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
				<li><a class="add" href="/manager/emailMngAction!befAdd.action?json.navTabId=${json.navTabId}" target="dialog" width="900" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="/manager/emailMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="" layoutH="137">
			<thead>
				<tr>
					<th width="2%"><input type="checkbox" group="ids"
						class="checkboxCtrl"></th>
					<th width="">模版名称</th>
					<th width="">店铺标识</th>
					<th width="">模版分类</th>
					<th width="">邮件标题</th>
					<th width="">创建时间</th>
					<th width="">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
					<tr>
						<td style="text-align: center;"><input name="ids" value="${item.id}"
							type="checkbox"></td>
						<td>${item.name}</td>
						<td>
							<c:if test="${item.mallSign=='bmcn' }">banana中文
							</c:if>
							<c:if test="${item.mallSign=='bmen' }">banana英文
							</c:if>
							<c:if test="${item.mallSign=='tvpadcn' }">华扬中文
							</c:if>
							<c:if test="${item.mallSign=='tvpaden' }">华扬英文
							</c:if>
						</td>
						<td>
							<c:if test="${item.ttype==1 }">开发者审核通过
							</c:if>
							<c:if test="${item.ttype==2 }">开发者审核不通过
							</c:if>
							<c:if test="${item.ttype==3 }">应用审核通过
							</c:if>
							<c:if test="${item.ttype==4 }">应用审核不通过
							</c:if>
						</td>
						<td>${item.title}</td>
						<td><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><a class="btnEdit"
							href="/manager/emailMngAction!befEdit.action?json.navTabId=${json.navTabId}&templ.id=${item.id}"
							target="dialog" title="编辑" width="900" height="500"></a> <a
							class="btnView"
							href="/manager/emailMngAction!view.action?&json.navTabId=${json.navTabId}&templ.id=${item.id}"
							target="dialog" title="查看" width="900" height="500"></a></td>
					</tr>
				</s:iterator>

			</tbody>
		</table>
		<jsp:include page="/manager/common/panelBar.jsp">
			<jsp:param value="navTab" name="targetType" />
			<jsp:param value="" name="rel" />
		</jsp:include>
	</div>
</div>

