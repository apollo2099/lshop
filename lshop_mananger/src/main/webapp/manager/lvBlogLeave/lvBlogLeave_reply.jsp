<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogLeaveAction!replyLook.action?lvBlogLeave.id=${lvBlogLeave.id}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageContent">
	<form method="post" id="leaveFrm"
			action="lvBlogLeaveAction!doReply.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this,navTabAjaxDone);">
		    <input type="hidden"  name="lvBlogLeave.id"  value="${lvBlogLeave.id}" />
		    <input type="hidden"  name="lvBlogLeave.whoId" value="${lvBlogLeave.id}">
		    <input type="hidden"  name="lvBlogLeave.whoName" value="${lvBlogLeave.whoName }">
		    <input type="hidden"  name="lvBlogLeave.lvBlogContent.id"  value="${lvBlogLeave.lvBlogContent.id}" />
		    <input type="hidden"  name="lvBlogLeave.storeId" value="${lvBlogLeave.storeId }">
  		<div class="pageFormContent" >
				<dl class="nowrap">
					<dt>评论者：</dt>
					<dd>
					   ${lvBlogLeave.userName}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>评论内容：</dt>
					<dd>
                        ${lvBlogLeave.content}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>评论时间：</dt>
					<dd>
					    <s:date name="#request.lvBlogLeave.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>回复时间：</dt>
					<dd>
					<input  class="date" name="publishTime"  format="yyyy-MM-dd HH:mm:ss" readonly="true" type="text"  id="bd15" />(如果没有指定回复时间，则系统默认为当前系统时间)
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>评论：</dt>
					<dd>
					<textarea id="leaveId" name="lvBlogLeave.content" class="required" rows="5" cols="80" lang="评论"></textarea>
					</dd>				  
				</dl>
		</div>

		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									回复评论
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
	<table class="table" width="100%" layoutH="340">
		<thead>
			<tr>
				<th width="10%">
					回复人
				</th>
				<th width="70%">
					回复内容
				</th>
				<th width="10%">
					回复时间
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator id="item" value="page.list">
				<tr>
					<td>
						${item.userName}
					</td>
					<td style="text-align: left">
						<s:if test="%{null!=#item.content&&#item.content.length()>40}">
                                  <s:property value="%{#item.content.substring(0, 40)}" />……
                                </s:if>
                         <s:else><s:property value="#item.content"/></s:else>
					</td>
					<td>
						<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a title="删除" target="ajaxTodo"
							href="lvBlogLeaveAction!deleteReplyLook.action?lvBlogLeave.id=${item.id}&json.navTabId=${json.navTabId}"
							class="btnDel">删除</a>
					</td>
				</tr>
			</s:iterator>
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
