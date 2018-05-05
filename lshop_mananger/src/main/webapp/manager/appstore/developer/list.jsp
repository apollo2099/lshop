<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/manager/common/pagerForm.jsp">
<jsp:param value="devMngAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<script type="text/javascript">
</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="/manager/devMngAction!list.action?json.navTabId=${json.navTabId}"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>账号：</label> <input type="text"
						name="developer.email" value="${developer.email}"
						maxlength="32" /></td>
						
					<td><label>姓名：</label> <input type="text"
						name="developer.contactName" value="${developer.contactName}"
						maxlength="32" /></td>
							
					<td><label>公司名称：</label> <input type="text"
						name="developer.company" value="${developer.company}"
						maxlength="32" /></td>	
						
				</tr>
				<tr>	
					<td><label>账号类型：</label> <select name="developer.dtype" style="width: 135px;">
							<option value="-1">全部</option>
							<option value="0"
								<c:if test="${developer.dtype==0}">selected</c:if>>个人开发者</option>
							<option value="1"
								<c:if test="${developer.dtype==1}">selected</c:if>>企业开发者</option>
					</select></td>
						
					<td><label>状态：</label> <select name="developer.dstatus" style="width: 135px;">
							<option value="-1">全部</option>
							<option value="0"
								<c:if test="${developer.dstatus==0}">selected</c:if>>待审核</option>
							<option value="1"
								<c:if test="${developer.dstatus==1}">selected</c:if>>审核通过</option>
							<option value="2"
								<c:if test="${developer.dstatus==2}">selected</c:if>>审核不通过</option>
					</select></td>
					
					<td><label>创建时间：</label> <input name="createDateStart" type="text" class="date" readonly="readonly"
									value="${createDateStart}" />--
								<input name="createDateEnd" type="text" class="date" readonly="readonly"
									value="${createDateEnd}" />
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
<!-- 
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
		<li>
			<a class="delete" href="/manager/devMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a>
		</li>
		</sec:authorize>
		</ul>
	</div>
	 -->
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr>
<!-- 				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="" >账号</th>
				<th width="5%" >店铺标识</th>
				<th width="8%" >账号类型</th>
				<th width="6%" >状态</th>
				<th width="12%" >创建时间</th>
				<th width="12%" >审核时间</th>
				<th width="8%" >是否同意协议</th>
				<th width="" >操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
<%-- 				<td><input name="ids" value="${item.id }" type="checkbox"></td> --%>
				
				<td>${item.email }</td>
				<td>${item.storeFlag }</td>
				<td>
					<c:if test="${item.dtype==0 }">个人开发者</c:if>
					<c:if test="${item.dtype==1 }">企业开发者</c:if>
				</td>
				<td>
					<c:if test="${item.dstatus==0 }">待审核</c:if>
					<c:if test="${item.dstatus==1 }">审核通过</c:if>
					<c:if test="${item.dstatus==2 }">审核不通过</c:if>
				</td>
				<td><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#item.reviewTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${item.isAgree==0 }">不同意</c:if>
					<c:if test="${item.isAgree==1 }">同意</c:if>
				</td>
				<td>
					<a href="/manager/devMngAction!view.action?json.navTabId=${json.navTabId }&developer.id=${item.id }" target="dialog" mask="true" width="900" height="600" title="查看详情">详情</a>&nbsp;&nbsp;
					<c:if test="${item.dstatus==0 }">
					<a href="/manager/devMngAction!toverify.action?json.navTabId=${json.navTabId }&developer.id=${item.id }" target="dialog" mask="true" width="600" height="400" title="审核">审核</a>
					</c:if>
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

