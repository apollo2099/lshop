<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvBlogTagsAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvBlogTags.id" value="${lvBlogTags.id }">
			<input type="hidden" name="lvBlogTags.isShow" value="${lvBlogTags.isShow }">
			<input type="hidden" name="lvBlogTags.code" value="${lvBlogTags.code}">
			<input type="hidden" name="lvBlogTags.createTime" value="${lvBlogTags.createTime}">
  		<div class="pageFormContent" layoutH="56">
  		               <p>
							<label>所属关系：</label>
							<select name="lvBlogTags.storeId" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogTags.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>标签：</label>
							 <input name="lvBlogTags.tagName" type="text" size="30"
							maxlength="32" class="required " value="${lvBlogTags.tagName}"/>
						</p>
						<p>
							<label>排序：</label>
							 <input name="lvBlogTags.orderId" type="text" size="30"
							maxlength="10" class="required digits" value="${lvBlogTags.orderId}"/>
						</p>
		</div>
	
		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
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
