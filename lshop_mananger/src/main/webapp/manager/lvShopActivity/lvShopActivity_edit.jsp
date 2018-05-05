<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopActivityAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvShopActivity.id" type="hidden" size="30" value="${lvShopActivity.id}"/>
			<input name="lvShopActivity.code" type="hidden" size="30" value="${lvShopActivity.code}"/>
			<input name="lvShopActivity.modifyUserId" type="hidden" size="30" value="${lvShopActivity.modifyUserId}"/>
				<!-- 生成表单 -->
				        <p>
							<label>所属商城：</label>
							<select name="lvShopActivity.storeId" class="required">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.storeFlag }" <c:if test="${lvShopActivity.storeId==shop.storeFlag}">selected="selected"</c:if> >${shop.name }</option>
								</c:foreach>
								</select>
						</p>
						<p>
							<label>活动名称：</label>
							<input name="lvShopActivity.avtivityName" type="text" size="30" maxlength="150" class="required" value="<s:property escapeHtml="true" value="lvShopActivity.avtivityName"/>" />
						</p>
						<p>
							<label>活动时间：</label>
							<input name="lvShopActivity.avtivityTime" type="text" size="30" maxlength="150" class="required" value="${lvShopActivity.avtivityTime}"/>
						</p>
						<p>
							<label>活动连接：</label>
							<input name="lvShopActivity.avtivityUrl" type="text" size="30" maxlength="150" class="required" value="${lvShopActivity.avtivityUrl}"/>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvShopActivity.orderValue" type="text" size="30" maxlength="10" class="required digits" value="${lvShopActivity.orderValue}"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvShopActivity.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopActivity.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvShopActivity.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopActivity.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvShopActivity.modifyUserName" type="text" size="30" value="${lvShopActivity.modifyUserName}"/>
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