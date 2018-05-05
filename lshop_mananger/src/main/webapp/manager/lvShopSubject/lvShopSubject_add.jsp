<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopSubjectAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->

						<p>
							<label>所属商城：</label>
							<select name="lvShopSubject.storeId" class="required">
								<c:foreach items="${shopList}" var="shop">
								<option value="${shop.storeFlag }">${shop.name }</option>
								</c:foreach>
								</select>
						</p>
						<p>
							<label>栏目名称：</label>
							<input name="lvShopSubject.subjectName" type="text" size="30" maxlength="20" class="required"/>
						</p>
						<p>
							<label>展现形式：</label>
							<s:select list="#{2:'商品信息',1:'广告图'}" name="lvShopSubject.exhibitType" cssClass="required"></s:select>
							
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvShopSubject.orderValue" type="text" size="30" maxlength="10" class="required digits" value="0"/>
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