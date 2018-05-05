<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvAccountAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<script type="text/javascript">

function output(e){
	var ids = "";
	var ckbs = $(e).closest(".unitBox").find("input:checkbox[name=ids]:checked");
	for (var i = 0; i < ckbs.length; i++) {
		var id = ckbs[i].value;
		if (ids != "") {
			ids += ";";
		}
		ids += id;
	}
	if (ids == "") {
		alertMsg.error("请选择要导出的信息！");
		return;
	}
	if (ids.split(";").length > 100) {
		alertMsg.error("一次最多只能导出100条信息！");	// 用户查询接口限定codes参数最多只能有100个code
		return;
	}
	
	$("#outputCodes").val(ids);
	$("#outputForm").submit();
}

</script>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvAccountAction!list.action?json.navTabId=${json.navTabId}" method="post">
	<div class="searchBar">
			<table class="searchContent" >
			<tr>
				<td>
					<label>账号：</label>
					<input type="text" name="unifiedUser.account" value="${unifiedUser.account}" maxlength="32"/>
				</td>
				<td>
				<label>状态：</label>
					<select name="unifiedUser.status" style="width: 130px;">
						<option></option>
						<option value="1" <c:if test="${unifiedUser.status eq '1'}">selected</c:if>>启用</option>
						<option value="0" <c:if test="${unifiedUser.status eq '0'}">selected</c:if>>停用</option>
						<option value="2" <c:if test="${unifiedUser.status eq '2'}">selected</c:if>>未激活</option>
					</select>
				</td>
				<td>
					<label>注册时间：</label>
					<input type="text" name="unifiedUser.createTimeBegin" value="${unifiedUser.createTimeBegin}" maxlength="32" class="date"/>-<input type="text" name="unifiedUser.createTimeEnd" value="${unifiedUser.createTimeEnd}" class="date" maxlength="32"/>
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
		<sec:authorize url="userEdit">
		<li>
			<form id="outputForm" action="lvAccountAction!export.action" method="post">
				<input type="hidden" name="ids" id="outputCodes" />
				<a href="javascript:void(0);" onclick="output(this)" class="icon"><span>导出Excel</span></a>
			</form>
		</li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
<!-- 				<th width="3%" orderField="id">ID</th> -->
				<th width="25%" >账号</th>
				<th width="15%" >昵称</th>
				<th width="10%" orderField="status">状态</th>
				<th width="10%" >注册时间</th>
				<th width="10%" >注册来源</th>
				<th width="10%" orderField="userLogintime">最近登录时间</th>
				<th width="15%" >操作</th>
			</tr>
		</thead>
		<tbody>
		
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.code}" type="checkbox"></td>
<%-- 				<td>${item.code }</td> --%>
				<td>${item.account}</td>
				<td>${item.nickname}</td>
				<td>
				<c:if test="${item.status eq '1'}">启用</c:if>
				<c:if test="${item.status eq '0'}">停用</c:if>
				<c:if test="${item.status eq '2'}">未激活</c:if>
				</td>
				<td>
					${item.createTime}
				</td>
				<td>
				<c:if test="${item.registerSource eq '0007'}">Banana商城</c:if>
				<c:if test="${item.registerSource eq '0008'}">Tvpad商城</c:if>
				<c:if test="${item.registerSource !='0008' and item.registerSource != '0007'}">其他</c:if>
				</td>
				<td>
					<fmt:formatDate value="${item.loginTimeDate}" pattern="yyyy-MM-dd HH:mm" />
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvAccountAction!view.action?unifiedUser.code=${item.code}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="800" height="500" mask="true">查看</a>
					<a class="btnEdit" href="lvAccountAction!befEdit.action?unifiedUser.code=${item.code}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog" width="800" height="500" mask="true">编辑</a>
					<a href="lvAccountAddressAction!list.action?lvAccountAddress.relCode=${item.code}&json.navTabId=lvShopSubject_2"  rel="lvShopSubject_2" target="navTab" title="收货地址">收货地址</a>
				</sec:authorize>	
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

