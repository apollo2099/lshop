<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvEmailTplMngAction!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}">
			<input type="hidden" name="lvEmailTpl.code" value="${lvEmailTpl.code}">
			<input type="hidden" name="lvEmailTpl.createTime" value="${lvEmailTpl.createTime}">
			<input type="hidden" name="lvEmailTpl.oldTplKey" value="${lvEmailTpl.tplKey }">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				 <dl class="nowrap">
				<dt>所属关系</dt>
				<dd>
				<select name="lvEmailTpl.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }" <c:if test="${lvEmailTpl.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
						</select>
				</dd>
				</dl>
						<dl class="nowrap">
					           <dt>邮件模板名称：</dt>
					           <dd>
						         		<input name="lvEmailTpl.tplDescribe" type="text"  style="width: 250px;" maxlength="100" value="${lvEmailTpl.tplDescribe }"/>
					           </dd>				  
				         </dl>
					    <input name="lvEmailTpl.id" type="hidden" size="30" value="${lvEmailTpl.id}"/>
							<dl class="nowrap">
							  <dt>key：</dt>
							  <dd>
							<input name="lvEmailTpl.tplKey" type="text" style="width: 250px;" maxlength="50" class="required" value="${lvEmailTpl.tplKey}" />
						     </dd>
						</dl>
						<dl class="nowrap">
					           <dt>邮件标题：</dt>
					            <dd>
						        <input name="lvEmailTpl.enTitle" type="text" style="width: 250px;" maxlength="50" value="${lvEmailTpl.enTitle}" class="required"/>
					           </dd>				  
				         </dl>
				         <dl class="nowrap">
					           <dt>邮件模板(主语言)：</dt>
					            <dd>
						         <textarea class="editor" name="lvEmailTpl.en"  rows="15" cols="100" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=enEmail&storeFlag=${lvEmailTpl.storeId }" upImgExt="jpg,jpeg,gif,png" ><s:property value="lvEmailTpl.en"/></textarea>
					            </dd>				  
				         </dl>
						<dl class="nowrap">
					           <dt>邮件模板(次语言)：</dt>
					           <dd>
						       <textarea class="editor" name="lvEmailTpl.zh"  rows="15" cols="100" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=cnEmail&storeFlag=${lvEmailTpl.storeId }" upImgExt="jpg,jpeg,gif,png" ><s:property value="lvEmailTpl.zh"/></textarea>
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