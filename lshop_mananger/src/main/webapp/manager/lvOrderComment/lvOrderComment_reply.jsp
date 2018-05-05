<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<form id="pagerForm" method="post" action="lvOrderCommentAction!toReply.action?lvOrderComment.id=${lvOrderComment.id}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageContent">
	<form method="post" id="leaveFrm"
			action="lvOrderCommentAction!reply.action?json.navTabId=${json.navTabId}" 
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
			<input type="hidden" name="lvOrderComment.id" value="${lvOrderComment.id }">
		    <input type="hidden" name="lvOrderComment.uid" value="${lvOrderComment.uid }">
		    <input type="hidden" name="lvOrderComment.nickname" value="${lvOrderComment.nickname }">
		    <input type="hidden" name="lvOrderComment.score" value="${lvOrderComment.score }">
		    <input type="hidden" name="lvOrderComment.grade" value="${lvOrderComment.grade }">
		    <input type="hidden" name="lvOrderComment.isCheck" value="${lvOrderComment.isCheck }">
		    <input type="hidden" name="lvOrderComment.sortId" value="${lvOrderComment.sortId }">
		    <input type="hidden" name="lvOrderComment.ip" value="${lvOrderComment.ip }">
		    <input type="hidden" name="lvOrderComment.contryId" value="${lvOrderComment.contryId }">
		    <input type="hidden" name="lvOrderComment.oprice" value="${lvOrderComment.oprice }">
		    <input type="hidden" name="lvOrderComment.pnum" value="${lvOrderComment.pnum }">
		    <input type="hidden" name="lvOrderComment.currency" value="${lvOrderComment.currency }">
		    <input type="hidden" name="lvOrderComment.isDelete" value="${lvOrderComment.isDelete }">
		    <input type="hidden" name="lvOrderComment.code" value="${lvOrderComment.code }">
		    <input type="hidden" name="lvOrderComment.storeId" value="${lvOrderComment.storeId }">
  		    <div class="pageFormContent" >
					<dl>
				<dt>订单编号：</dt>
					<dd>
                    <input name="lvOrderComment.orderId" type="text" size="32" maxlength="32"  value="${lvOrderComment.orderId }" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
				<dt>回复内容：</dt>
				    <dd>
				     <textarea  name="lvOrderComment.content" rows="5" cols="80 " class="required"></textarea>
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
				<td>${item.orderId }</td>
				<td>${item.nickname }</td>
				<td>${item.content }</td>
				<td>${item.score }</td>
				<td>
				     <c:if test="${item.grade==1}">差评</c:if>
                     <c:if test="${item.grade==2}">中评</c:if>
                     <c:if test="${item.grade==3}">好评</c:if>
				</td>
				<td>
				<sec:authorize url="userEdit">
					<a class="btnDel"  href="lvOrderCommentAction!deleteReply.action?lvOrderComment.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
