<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreAreaAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvStoreArea.id" type="hidden"  value="${lvStoreArea.id}"/>
			<input name="lvStoreArea.areaId" type="hidden"  value="${lvStoreArea.areaId}"/>
			<input name="lvStoreArea.parentCode" type="hidden"  value="${lvStoreArea.parentCode}"/>
			<input name="lvStoreArea.storeId" type="hidden"  value="${lvStoreArea.storeId}"/>
			<input name="lvStoreArea.areaName" type="hidden"  value="${lvStoreArea.areaName}"/>
			<input name="lvStoreArea.areaNameEn" type="hidden" value="${lvStoreArea.areaNameEn }">
			<input name="lvStoreArea.code" type="hidden"  value="${lvStoreArea.code}"/>
			<input name="lvStoreArea.oldAreaName" type="hidden" value="${lvStoreArea.areaName }">
			<input name="lvStoreArea.oldAreaNameEn" type="hidden" value="${lvStoreArea.areaNameEn }">
			
				<!-- 生成表单 -->
				       <p>
							<label>所属洲：</label>
							<select name="lvStoreArea.parentCode" disabled="disabled" style="width:150px">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${continentList}" var="area">
						   <option value="${area.code }" <c:if test="${lvStoreArea.parentCode==area.code }">selected="selected"</c:if>>${area.areaName }</option>
						   </c:foreach>
						   </select>
						</p>
						<p>
							<label>国家名称：</label>
							
						   <select name="lvStoreArea.areaId" onchange="changeCountry()" id="contryid" disabled="disabled" style="width:150px">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${areaList}" var="area">
						   <option value="${area.id }" <c:if test="${lvStoreArea.areaId==area.id }">selected="selected"</c:if>>${area.namecn }</option>
						   </c:foreach>
						   </select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvStoreArea.orderValue" type="text" size="10" value="${lvStoreArea.orderValue}" maxlength="10" class="required digits"/>
						</p>
						<p>
							<label>创建时间：</label>
								 <input name="lvStoreArea.createTime"  readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvStoreArea.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
							 <input name="lvStoreArea.modifyTime"  readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvStoreArea.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvStoreArea.modifyUserName" readonly="readonly" type="text" size="30" value="${lvStoreArea.modifyUserName}"/>
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