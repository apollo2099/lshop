<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="accountbind!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<script type="text/javascript">

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="accountbind!list.action?json.navTabId=${json.navTabId}" method="post">
	<div class="searchBar">
			<table class="searchContent" >
			<tr>
				<td><label>账号：</label> <input name="useremail" type="text" value="${useremail}" maxlength="32"/>
				<td>
				<td><label>创建时间：</label> <input name="createDateStart" type="text" class="date" readonly="readonly"
									value="${createDateStart}" />--
								<input name="createDateEnd" type="text" class="date" readonly="readonly"
									value="${createDateEnd}" />
				<td>
				<label>第三方类型：</label>
					<select name="lvUserTh.thType" style="width: 130px;">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${lvUserTh.thType eq '0'}">selected</c:if>>微信移动端</option>
						<option value="1" <c:if test="${lvUserTh.thType eq '1'}">selected</c:if>>QQ</option>
						<option value="2" <c:if test="${lvUserTh.thType eq '2'}">selected</c:if>>新浪微博</option>
						<option value="3" <c:if test="${lvUserTh.thType eq '3'}">selected</c:if>>支付宝</option>
						<option value="4" <c:if test="${lvUserTh.thType eq '4'}">selected</c:if>>微信PC端</option>
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
		<li>
			<a class="delete"  href="accountbind!disableList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要停用这些记录吗?"  rel="ids"><span>批量停用</span></a>
		</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="">账号</th>
				<th width="6%">商城标识</th>
				<th width="">第三方类型</th>
				<th width="">第三方授权用户code</th>
				<th width="11%">授权绑定时间</th>
				<th width="">状态</th>
				<th width="">操作人</th>
				<th width="8%">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.lvUserTh.id}" type="checkbox"></td>
				<td>${item.email}</td>
				<td>${item.lvUserTh.mallFlag}</td>
				<td>
					<c:if test="${item.lvUserTh.thType eq '0'}">微信移动端</c:if>
					<c:if test="${item.lvUserTh.thType eq '1'}">QQ</c:if>
					<c:if test="${item.lvUserTh.thType eq '2'}">新浪微博</c:if>
					<c:if test="${item.lvUserTh.thType eq '3'}">支付宝</c:if>
					<c:if test="${item.lvUserTh.thType eq '4'}">微信PC端</c:if>
				</td>
				<td>${item.lvUserTh.thCode}</td>
				<td><fmt:formatDate value="${item.lvUserTh.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${item.lvUserTh.status eq '1'}">正常</c:if>
					<c:if test="${item.lvUserTh.status eq '0'}">停用</c:if>
				</td>
				<td>${item.lvUserTh.modifyUserName}</td>

				<td>
				<c:if test="${item.lvUserTh.status eq '1'}">
					<a href="accountbind!disable.action?lvUserTh.id=${item.lvUserTh.id}&json.navTabId=${json.navTabId }"  
					target="ajaxTodo" title="确实要停用吗?">停用</a>
				</c:if>
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

