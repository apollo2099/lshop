<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
	
</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="/manager/vbPlansMngAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvVbPlans.createTime" value="<s:date name="lvVbPlans.createTime" format="yyyy-MM-dd HH:mm:ss"/>">
			<input type="hidden" name="lvVbPlans.mallFlag" value="${lvVbPlans.mallFlag}"/>
			<input type="hidden" name="lvVbPlans.id" value="${lvVbPlans.id}"/>
			<input type="hidden" name="lvVbPlans.ptype" value="${lvVbPlans.ptype}"/>
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt style="text-align: right;">
						所属商城：
					</dt>
					<dd>
					 <c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==lvVbPlans.mallFlag}">
					${store.name}
					<s:iterator value="#request.exchangeRateList" id="o"><c:if  test="${o.currency==store.currency}">(lVB=${o.exchangeRate}${o.currency})</c:if></s:iterator>
					</c:if>
				   </c:foreach> 
					</dd>
				</dl>
               	<dl>
					<dt style="text-align: right;">
						排序值：
					</dt>
					<dd>
					<input name="lvVbPlans.porder"  value="${lvVbPlans.porder}" type="text" style="width: 200px;" style="" maxlength="6" class="required digitsNoZore" />
					</dd>
				</dl>
               	<dl>
					<dt style="text-align: right;">
						VB选项类型：
					</dt>
					<dd>
						<s:if test="lvVbPlans.ptype==0">选择类型</s:if>
						<s:elseif test="lvVbPlans.ptype==1">输入类型</s:elseif>
					</dd>
				</dl>
				
				<dl>
					<dt style="text-align: right;">
						选项名称：
					</dt>
					<dd>
						<input name="lvVbPlans.title" type="text" style="width: 200px;"
							style="" maxlength="16" class="required"  value="${lvVbPlans.title}"/>
					</dd>
				</dl>

				<s:if test="lvVbPlans.ptype==0">
				<dl  class="vb_choose_type">
					<dt style="text-align: right;">
						选项VB数量：
					</dt>
					<dd>
						<input name="lvVbPlans.vbNum" type="text" style="width: 200px;"
							style="" maxlength="8" class="required digitsNoZore"  value="${lvVbPlans.vbNum}"/>
					</dd>
				</dl>
				</s:if>
				<s:else>
				<dl class="vb_input_type">
					<dt style="text-align: right;">
						VB输入最小值：
					</dt>
					<dd>
						<input name="lvVbPlans.minNum" type="text"  style="width: 200px;"
							style="" maxlength="6" value="${lvVbPlans.minNum}" class="required digitsNoZore"/>
					</dd>
				</dl>
				<dl  class="vb_input_type">
					<dt style="text-align: right;">
						是否填写倍数：
					</dt>
					
					<dd>
						是<input type="radio" name="lvVbPlans.isMul" value="true" <s:if test="lvVbPlans.isMul==true">checked="checked"</s:if>/>  否<input type="radio" name="lvVbPlans.isMul" <s:if test="lvVbPlans.isMul==false">checked="checked"</s:if> value="false"/>
					</dd>
				</dl>
				</s:else>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									修改
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>