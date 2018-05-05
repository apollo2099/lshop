<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreMngAction!updateOrderValue.action?json.navTabId=${json.navTabId}"  class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		    <input type="hidden" name="lvStore.id" value="${lvStore.id}">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				
				       <dl >
							<dt>排序值：</dt>
							<dd>
								<c:if test="${!empty lvStore.orderValue }">
								<input name="lvStore.orderValue" type="text" size="30" maxlength="10"  class="required digits" 
								 value="${lvStore.orderValue }"/> </c:if>
								<c:if test="${empty lvStore.orderValue }">
								<input name="lvStore.orderValue" type="text" size="30" maxlength="10"  class="required digits" 
								 value="0"/> </c:if>
							</dd>
					    </dl>
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