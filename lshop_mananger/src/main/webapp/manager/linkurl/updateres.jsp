<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="pageContent">
	<form  action="/manager/linkUrl!update.action?json.navTabId=${json.navTabId}"  class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="vo.id" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>所属关系：</label>
				<select name="vo.storeFlag">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }" <c:if test="${vo.storeFlag==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
						</select>
			</p>
			<p>
				<label>名称：</label>
				<input name="vo.name" class="required" type="text" maxlength="32" size="30" value="<s:property value="vo.name"/>" alt="请输入资源名称"/>
			</p>
			<p>
				<label>动态地址：</label>
				<input name="vo.dynamicurl" class="required" type="text" maxlength="200" size="30" value="<s:property value="vo.dynamicurl"/>" alt="请输入动态地址"/>
				
			</p>
			<p>
				<label>静态地址：</label>
					<input name="vo.staticurl" class="required" type="text" maxlength="200" size="30" value="<s:property value="vo.staticurl"/>" alt="请输入静态地址"/>
			</p>
			<p>
				<label>HQL：</label>
				<input name="vo.hql" value="<s:property value="vo.hql "/>" maxlength="200" type="text" size="30" alt="请输入HQL语句"/>
			</p>
			<p>
				<label>备注：</label>
				<input name="vo.note"  value="<s:property value="vo.note "/>" maxlength="32" type="text" size="30" alt="请输入标识"/>
				
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

