<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvCarriageSetAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvCarriageSetAction!list.action" method="post">
	<div class="searchBar">
					<table class="searchContent" >
				<tr>
						</tr><tr>
						<%--
						<td><label>配送地区</label>
						<select name="lvCarriageSet.deliveryId">
						 <option value="">==请选择==</option>
						 	<c:foreach items="${areaList}" var="area">
						 	<option value="${area.id }"
						 	 <c:if test="${area.id==lvCarriageSet.deliveryId}">selected="selected"</c:if>
						 	 >${area.namecn}</option>
				             </c:foreach>
						</select>
						</td>
						 --%>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvCarriageSet.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvCarriageSet.storeId}">selected="selected"</c:if>>${store.name}</option>
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
		<sec:authorize url="userEdit">
		    <li><a class="add" href="lvCarriageSetAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" width="600" height="400" mask="true" title="添加运费"><span>添加</span></a></li>
		    <li class="line">line</li>
			<li><a class="delete"  href="lvCarriageSetAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="15%">所属关系</th>
				<th width="20%">配送地区</th>
				<th width="30%">运费</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				
						<c:foreach items="${storeList}" var="store">
							<c:if test="${store.storeFlag==item.storeId}">
							<a style="color: blue" href="lvStoreMngAction!shopView.action?lvStore.storeFlag=${item.storeId}&json.navTabId=${json.navTabId }" target="dialog" title="查看店铺信息"  width="900" height="730" mask="true">
							${store.name}</a></c:if>
						</c:foreach>
				</td>
				<td>
				<c:if test="${item.deliveryId==100000}">运费默认值地区</c:if>
				<c:if test="${item.deliveryId!=1000000 }">
				${item.deliveryName }
				</c:if>
				<%-- 
				<c:foreach items="${areaList}" var="area">
				 <c:if test="${area.id==item.deliveryId}">${area.namecn}</c:if>
				</c:foreach>
				--%>
				</td>
				<td>${item.carriage}</td>
				<td>
				<sec:authorize url="userEdit">
				<!-- 
				    <a class="btnView" href="lvCarriageSetAction!view.action?lvCarriageSet.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  wwidth="600" height="400" mask="true">查看</a>
				     -->
				    <a class="btnEdit" href="lvCarriageSetAction!befEdit.action?lvCarriageSet.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog" width="600" height="400"  mask="true">编辑</a>
					<a class="btnDel"  href="lvCarriageSetAction!del.action?lvCarriageSet.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
