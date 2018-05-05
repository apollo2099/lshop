<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/manager/common/pagerForm.jsp">
<jsp:param value="vbPlansMngAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>
<script type="text/javascript">
</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);"
		action="/manager/vbPlansMngAction!list.action?json.navTabId=${json.navTabId}"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><label>所属商城：</label>
						    <select name="lvVbPlans.mallFlag">
							<option value="">--请选择--</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvVbPlans.mallFlag}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
					        </select>
					</td>
					<td><label>选项名称：</label> <input type="text"
						name="lvVbPlans.title" value="${lvVbPlans.title}"
						maxlength="32" /></td>						
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
		<li><a class="add" href="/manager/vbPlansMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvStore" rel="lvStore" width="450" height="350" mask="true"><span>添加</span></a></li>
		<li class="line">line</li>
		</sec:authorize>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr>
<!-- 				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="3%" >排序值</th>
				<th width="15%" >所属商城</th>
				<th width="13%" >选项名称</th>
				<th width="7%" >选项类型</th>
				<th width="10%" >VB数量</th>
				<th width="10%" >最小值</th>
				<th width="5%" >是否填写倍数</th>
				<th width="8%" >创建时间</th>
				<th width="10%" >修改时间</th>
				<th width="8%" >最后修改人</th>
				<th width="5%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
          <%-- <td><input name="ids" value="${item.id }" type="checkbox"></td> --%>	
				<td>${item.porder }</td>
				<td><c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.mallFlag}">
					${store.name}
					<s:iterator value="#request.exchangeRateList" id="o"><c:if  test="${o.currency==store.currency}">(lVB=${o.exchangeRate}${o.currency})</c:if></s:iterator>
					</c:if>
				   </c:foreach>   </td>
				<td>${item.title}</td>
				<td><s:if test="#item.ptype==false">选择</s:if><s:else>输入</s:else></td>
				<td><s:if test="#item.ptype==false">${item.vbNum }</s:if><s:else>--</s:else></td>
				<td><s:if test="#item.ptype==false">--</s:if><s:else>${item.minNum}</s:else></td>
				<td><s:if test="#item.ptype==false">--</s:if><s:else><s:if test="#item.isMul==true">是</s:if><s:else>否</s:else></s:else></td>
				<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.modifyUserName }</td>
				<td>
					<a title="编辑" target="dialog" mask="true" href="/manager/vbPlansMngAction!befEdit.action?json.navTabId=${json.navTabId}&lvVbPlans.id=${item.id }" class="btnEdit" width="450" height="300" title="编辑">编辑</a>
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo"href="/manager/vbPlansMngAction!del.action?lvVbPlans.id=${item.id}&json.navTabId=${json.navTabId}" class="btnDel">删除</a>
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

