<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponTypeAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCouponType.id" type="hidden" size="30" value="${lvCouponType.id}"/>
			<input name="lvCouponType.oldName" type="hidden" size="30" value="${lvCouponType.name}"/>
			<input name="lvCouponType.code" type="hidden" size="30" value="${lvCouponType.code}"/>
			
				<!-- 生成表单 -->
				<dl class="nowrap">
					<dt>优惠券名称：</dt>
					<dd>
					  <input name="lvCouponType.name" type="text" size="34" class="required" maxlength="128" value="${lvCouponType.name}"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>描述信息：</dt>
					<dd>
					  <textarea rows="5" cols="80" name="lvCouponType.remark" maxlength="200">${lvCouponType.remark }</textarea>
					</dd>				  
				</dl>
				
				<dl class="nowrap">
					<dt>重复使用：</dt>
					<dd>
					  <s:if test="lvCouponType.reuse==1">是</s:if>
					  <s:if test="lvCouponType.reuse==0">否</s:if>
					</dd>				  
				</dl>
				<c:if test="${lvCouponType.reuse==1 }">
				<dl class="nowrap" id="reuseNumDiv">
					<dt>重复使用次数：</dt>
					<dd>
						<input name="lvCouponType.reuseNum" type="text" size="34" maxlength="4" class="required digits" value="${lvCouponType.reuseNum }"/>
					</dd>				  
				</dl>
				</c:if>
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