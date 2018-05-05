<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="pageContent">
	<form method="post" action="lvCarriageSetAction!edit.action?json.navTabId=${json.navTabId }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="lvCarriageSet.id" value="${lvCarriageSet.id }">
		<input type="hidden" name="lvCarriageSet.code" value="${lvCarriageSet.code }">
		<input type="hidden" name="lvCarriageSet.createTime" value="${lvCarriageSet.createTime }">
		<input type="hidden" name="areaId" value="${lvCarriageSet.deliveryId }">
		<input type="hidden" name="lvCarriageSet.currency" value="${lvCarriageSet.currency }">
		<input type="hidden" name="lvCarriageSet.deliveryId" value="${lvCarriageSet.deliveryId }">
		<input type="hidden" name="lvCarriageSet.storeId" value="${lvCarriageSet.storeId }">
		
		<div class="pageFormContent" layoutH="56">
		<p>
							<label>所属关系：</label>
							<select name="lvCarriageSet.storeId" class="required" disabled="disabled" style="width:150px">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvCarriageSet.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
		        <dl>
					<dt>运费配送地区：</dt>
					<dd>
					<input type="text" value="${lvCarriageSet.deliveryName }" size="32" readonly="readonly">
					</dd>				  
				</dl>
				<dl >
				<dt>运费：</dt>
					<dd>
                       <input name="lvCarriageSet.carriage" class="required numberNew" type="text" size="32" maxlength="7" alt="请输入运费" value="${lvCarriageSet.carriage }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>备注：</dt>
					<dd>
                       <textarea rows="5" cols="50" name="lvCarriageSet.remark" maxlength="160">${lvCarriageSet.remark }</textarea>
					</dd>				  
				</dl>
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
