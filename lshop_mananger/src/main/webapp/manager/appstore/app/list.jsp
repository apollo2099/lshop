<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/manager/common/pagerForm.jsp">
<jsp:param value="" name="pagerFormAction"/>
</jsp:include>

<script type="text/javascript">
</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="/manager/appMngAction!list.action?json.navTabId=${json.navTabId}"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>账号：</label> <input type="text"
						name="app.email" value="${app.email}"
						maxlength="32" /></td>
						
					<td><label>应用名称：</label> <input type="text"
						name="app.name" value="${app.name}"
						maxlength="32" /></td>
							
					<td><label>应用分类：</label> <input type="text"
						name="app.appType" value="${app.appType}"
						maxlength="32" /></td>	
						
					<td><label>状态：</label> <select name="app.reviewStatus" style="width: 135px;">
							<option value="-1">全部</option>
							<option value="0"
								<c:if test="${app.reviewStatus==0}">selected</c:if>>待审核</option>
							<option value="1"
								<c:if test="${app.reviewStatus==1}">selected</c:if>>审核通过</option>
							<option value="2"
								<c:if test="${app.reviewStatus==2}">selected</c:if>>审核不通过</option>
							<option value="3"
								<c:if test="${app.reviewStatus==3}">selected</c:if>>待完善信息</option>
					</select></td>
						
				</tr>
				<tr>	
					<td><label>是否收费：</label> <select name="app.isCharge"
						style="width: 135px;">
							<option value="-1">全部</option>
							<option value="1"
								<c:if test="${app.isCharge==1}">selected</c:if>>是</option>
							<option value="0"
								<c:if test="${app.isCharge==0}">selected</c:if>>否</option>
					</select></td>
						
					<td><label>是否有广告：</label> <select name="app.isAd"
						style="width: 135px;">
							<option value="-1">全部</option>
							<option value="1"
								<c:if test="${app.isAd==1}">selected</c:if>>是</option>
							<option value="0"
								<c:if test="${app.isAd==0}">selected</c:if>>否</option>
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
				<th width="" >应用名称</th>
				<th width="4%" >版本号</th>
				<th width="" >语言</th>
				<th width="" >分类</th>
				<th width="5%" >是否收费</th>
				<th width="6%" >是否有广告</th>
				<th width="6%" >状态</th>
				<th width="11%" >创建时间</th>
				<th width="11%" >审核时间</th>
				<th width="6%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
<%-- 				<td><input name="ids" value="${item.id }" type="checkbox"></td> --%>
				<td>${item.email }</td>
				<td>${item.storeFlag }</td>
				<td>${item.name }</td>
				<td>${item.appVersion }</td>
				<td>${item.lang }</td>
				<td>${item.appType }</td>
				<td>
					<c:if test="${item.isCharge==0 }">不收费</c:if>
					<c:if test="${item.isCharge==1 }">收费</c:if>
				</td>
				<td>
					<c:if test="${item.isAd==0 }">无广告</c:if>
					<c:if test="${item.isAd==1 }">有广告</c:if>
				</td>
				<td>
					<c:if test="${item.reviewStatus==0 }">待审核</c:if>
					<c:if test="${item.reviewStatus==1 }">审核通过</c:if>
					<c:if test="${item.reviewStatus==2 }">审核不通过</c:if>
					<c:if test="${item.reviewStatus==3 }">待完善信息</c:if>
				</td>
				<td><s:date name="#item.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#item.reviewTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<a href="/manager/appMngAction!view.action?json.navTabId=${json.navTabId }&app.id=${item.id }" target="dialog" mask="true" width="900" height="600" title="查看详情">详情</a>&nbsp;&nbsp;
					<c:if test="${item.reviewStatus==0 }">
					<a href="/manager/appMngAction!toverify.action?json.navTabId=${json.navTabId }&app.id=${item.id }" target="dialog" mask="true" width="600" height="400" title="审核">审核</a>
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

