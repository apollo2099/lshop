<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvBlogTypeAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="lvBlogType.id" value="${lvBlogType.id }">
			<input type="hidden" name="lvBlogType.code" value="${lvBlogType.code}">
			<input type="hidden" name="lvBlogType.createTime" value="${lvBlogType.createTime}">
				<!-- 生成表单 -->
				       <p>
							<label>所属关系：</label>
							<select name="lvBlogType.storeId" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogType.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
						</p> 
						<p>
							<label>类型名称：</label>
							<input name="lvBlogType.type" type="text" size="32" maxlength="32" class="required true" value="${lvBlogType.type }" />
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvBlogType.orderId" type="text" size="32" maxlength="4" class="required digits" value="${lvBlogType.orderId}" min="1" max="10"/>
						</p>
						
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>