<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvOrderCommentAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderCommentAction!list.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>订单编号：</label>
				<input type="text" name="lvOrderComment.orderId" value="${lvOrderComment.orderId}"/>
			</li>
			<li>
				<label>用户名称：</label>
				<input type="text" name="lvOrderComment.nickname" value="${lvOrderComment.nickname}"/>
			</li>
		</ul>
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
		    <li><a class="icon" href="lvOrderCommentAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600" title="添加系统评论"><span>添加系统评论</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="lvOrderCommentAction!audit.action?json.navTabId=${json.navTabId}"  target="selectedTodo"  title="批量审核?"   rel="ids"><span>批量审核</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"  href="lvOrderCommentAction!del.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="5%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">订单编号</th>
				<th width="10%">用户名称</th>
				<th width="25%">评论内容</th>
				<th width="5%">评论图片</th>
				<th width="5%">评分</th>
				<th width="5%">评分等级</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="#request.page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.orderId }</td>
				<td>${item.nickname }</td>
				<td>
				 <s:if test="%{null!=#item.content&&#item.content.length()>40}">
                           <s:property value="%{#item.content.substring(0, 40)}" />
                           ……
                          </s:if>
                              <s:else><s:property value="#item.content"/></s:else>
				</td>
				<td>
					<c:if test="${item.commentImages!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${item.commentImages}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.commentImages}" width="20px" height="20px"/></a>
					</c:if>
				</td>
				<td>${item.score }</td>
				<td>
				     <c:if test="${item.grade==1}">差评</c:if>
                     <c:if test="${item.grade==2}">中评</c:if>
                     <c:if test="${item.grade==3}">好评</c:if>
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvOrderCommentAction!view.action?lvOrderComment.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="900" height="600" mask="true">查看</a>
					<a class="btnDel"  href="lvOrderCommentAction!del.action?ids=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
					<a  href="lvOrderCommentAction!toReply.action?lvOrderComment.id=${item.id}&json.navTabId=lvProductComment_1"  rel="lvProductComment_1"  target="navTab" title="管理员留言评论回复">回复</a>
					<c:if test="${item.isCheck==0}">
					<a  href="lvOrderCommentAction!audit.action?ids=${item.id}&json.navTabId=${json.navTabId }"  title="确实要审核这些记录吗?该操作请谨慎!" target="ajaxTodo"  navTabId="lvProduct" rel="lvProduct"  mask="true">审核</a>
					</c:if>
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
