<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="pageContent">
	<form method="post" action="lvProductTypeAction!add.action?json.navTabId=${json.navTabId }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
							<label>所属关系：</label>
							<select name="lvProductType.storeId" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
			</p>  
			<p>
				<label>类别标示：</label>
				<select name="lvProductType.typeFlag" class="required">
				   <option value="">==请选择==</option>
				   <option value="1" >产品</option>
				   <option value="2" >应用</option>
				</select>
			</p>
			<p>
				<label>类型名称：</label>
				<input name="lvProductType.typeName" class="required" type="text" size="30" maxlength="32" alt="请输入类型名称"/>
			</p>
			<p>
				<label>排序值：</label>
				<input name="lvProductType.orderId" class="required digits" type="text" size="30" maxlength="4" alt="请输入排序值"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
