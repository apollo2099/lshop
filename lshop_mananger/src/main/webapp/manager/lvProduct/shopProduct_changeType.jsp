<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvProductAction!changeType.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="ids" type="hidden" size="30" value="${ids}"/>
				<!-- 生成表单 -->
						<p>
						
							&nbsp;&nbsp;共有<font color="red">${num}</font>条数据，请选择要转换的目标商品分类
						</p>
						<p>
							<label>商品分类：</label>
							<select name="lvProduct.shopProductType">
							<option value="">==请选择==</option>
							<c:foreach items="${productTypList}" var="type">
							<option value="${type.code }">${type.typeName }</option>
							</c:foreach>
							</select>
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