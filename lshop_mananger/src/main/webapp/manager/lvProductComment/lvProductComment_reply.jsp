<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<form id="pagerForm" method="post" action="lvProductCommentAction!toReply.action?lvProductComment.id=${lvProductComment.id}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageContent">
	<form method="post" id="leaveFrm"
			action="lvProductCommentAction!reply.action?json.navTabId=${json.navTabId}" 
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="lvProductComment.id" value="${lvProductComment.id }">
		    <input type="hidden" name="lvProductComment.uid" value="${lvProductComment.uid }">
		    <input type="hidden" name="lvProductComment.nickname" value="${lvProductComment.nickname }">
		    <input type="hidden" name="lvProductComment.score" value="${lvProductComment.score }">
		    <input type="hidden" name="lvProductComment.grade" value="${lvProductComment.grade }">
		    <input type="hidden" name="lvProductComment.isCheck" value="${lvProductComment.isCheck }">
		    <input type="hidden" name="lvProductComment.sortId" value="${lvProductComment.sortId }">
		    <input type="hidden" name="lvProductComment.ip" value="${lvProductComment.ip }">
		    <input type="hidden" name="lvProductComment.contryId" value="${lvProductComment.contryId }">
		    <input type="hidden" name="lvProductComment.oprice" value="${lvProductComment.oprice }">
		    <input type="hidden" name="lvProductComment.pnum" value="${lvProductComment.pnum }">
		    <input type="hidden" name="lvProductComment.currency" value="${lvProductComment.currency }">
		    <input type="hidden" name="lvProductComment.isDelete" value="${lvProductComment.isDelete }">
		    <input type="hidden" name="lvProductComment.code" value="${lvProductComment.code }">
		    <input type="hidden" name="lvProductComment.storeId" value="${lvProductComment.storeId }">
		    <input type="hidden" name="lvProductComment.productCode" value="${lvProductComment.productCode }">
  		    <div class="pageFormContent" >
				  <dl>
					<dt>产品名称：</dt>
					<dd>
				         <s:iterator value="#request.productList" id="product">
							 <c:if test="${lvProductComment.productCode==product.code }">${product.productName }</c:if> 
						</s:iterator>
					</dd>				  
				</dl>
					<dl>
				<dt>订单编号：</dt>
					<dd>
                    <input name="lvProductComment.oid" type="text" size="32" maxlength="32"  value="${lvProductComment.oid }" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
				<dt>回复内容：</dt>
				    <dd>
				     <textarea  name="lvProductComment.content" rows="5" cols="80 " class="required"></textarea>
				    </dd>
				</dl>
		</div>
	
		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									回复
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
</form>
</div>
</div>

<div class="pageContent">
<table class="table" width="100%" layoutH="230">
		<thead>
			<tr>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">订单编号</th>
				<th width="10%">用户名称</th>
				<th width="15%">产品名称</th>
				<th >评论内容</th>
				<th width="5%">评分</th>
				<th width="5%">评分等级</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td>${item.id }</td>
				<td>${item.oid }</td>
				<td>${item.nickname }</td>
				<td>
				<c:foreach items="${productList}" var="product">
				   <c:if test="${product.code==item.productCode}">${product.productName }</c:if>
				</c:foreach>
				</td>
				<td>${item.content }</td>
				<td>${item.score }</td>
				<td>
				     <c:if test="${item.grade==1}">差评</c:if>
                     <c:if test="${item.grade==2}">中评</c:if>
                     <c:if test="${item.grade==3}">好评</c:if>
				</td>
				<td>
				<sec:authorize url="userEdit">
					<a class="btnDel"  href="lvProductCommentAction!deleteReply.action?lvProductComment.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<%@ include file="../common/panelBar.jsp"%>
</div>
<script type="text/javascript">
<!--
function doRepay(){
$("#leaveFrm").submit();
}
//-->
</script>
