<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvSpecialtyStoreTypeAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvSpecialtyStoreType.id" type="hidden" size="30" value="${lvSpecialtyStoreType.id}"/>
			<input name="lvSpecialtyStoreType.storeId" type="hidden" size="30" value="${lvSpecialtyStoreType.storeId}"/>
			<input name="lvSpecialtyStoreType.code" type="hidden" size="30" value="${lvSpecialtyStoreType.code}"/>
			<input name="lvSpecialtyStoreType.modifyUserId" type="hidden" size="30" value="${lvSpecialtyStoreType.modifyUserId}"/>
			<input name="lvSpecialtyStoreType.oldStoreTypeName" type="hidden" value="${lvSpecialtyStoreType.storeTypeName }">
				<!-- 生成表单 -->
						<p>
							<label>专卖店：</label>
							
							<select name="lvSpecialtyStoreType.parentCode" class="required">
								<option value="">==请选择==</option>
								<c:foreach items="${typeList}" var="temp">
								<option value="${temp.code }" <c:if test="${temp.code==lvSpecialtyStoreType.parentCode }">selected="selected"</c:if>>${temp.storeTypeName }</option>
								</c:foreach>
						    </select>
						</p>
						<p>
							<label>分类名称：</label>
							<input name="lvSpecialtyStoreType.storeTypeName" type="text" size="30" maxlength="20" value="${lvSpecialtyStoreType.storeTypeName}" class="required"/>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvSpecialtyStoreType.orderValue" type="text" size="30" maxlength="10" value="${lvSpecialtyStoreType.orderValue}" class="required digits"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvSpecialtyStoreType.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvSpecialtyStoreType.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvSpecialtyStoreType.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvSpecialtyStoreType.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvSpecialtyStoreType.modifyUserName" type="text" size="30" value="${lvSpecialtyStoreType.modifyUserName}" readonly="readonly"/>
						</p>
						
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>