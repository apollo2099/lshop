<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopSubjectAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="lvShopSubject.code"  size="30" value="${lvShopSubject.code}"/>
			<input type="hidden" name="lvShopSubject.modifyUserId"  size="30" value="${lvShopSubject.modifyUserId}"/>
			<input type="hidden" name="lvShopSubject.id"  size="30" value="${lvShopSubject.id}"/>
			<input type="hidden" name="lvShopSubject.oldSubjectName" value="${lvShopSubject.subjectName }">
				<!-- 生成表单 -->
						<p>
							<label>所属商城：</label>
							<select name="lvShopSubject.storeId" class="required">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.storeFlag }" <c:if test="${lvShopSubject.storeId==shop.storeFlag}">selected="selected"</c:if> >${shop.name }</option>
								</c:foreach>
								</select>
						</p>
						<p>
							<label>栏目名称：</label>
							<input name="lvShopSubject.subjectName" type="text" size="30" maxlength="20" value="${lvShopSubject.subjectName}" class="required"/>
						</p>
						<p>
							<label>展现形式:</label>
							<s:select list="#{2:'商品信息',1:'广告图'}" name="lvShopSubject.exhibitType" cssClass="required"></s:select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvShopSubject.orderValue" type="text" size="30" maxlength="20" value="${lvShopSubject.orderValue}" class="required digits"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvShopSubject.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopSubject.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>

						</p>
						<p>
							<label>修改时间：</label>
                            <input name="lvShopSubject.modifyTime" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
					               value="<s:date name="lvShopSubject.modifyTime" format="yyyy-MM-dd HH:mm:ss" />"  />	
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvShopSubject.modifyUserName" type="text" size="30" value="${lvShopSubject.modifyUserName}"/>
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