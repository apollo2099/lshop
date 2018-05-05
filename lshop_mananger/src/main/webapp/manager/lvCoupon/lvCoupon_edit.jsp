<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCoupon.id" type="hidden" size="30" value="${lvCoupon.id}"/>
			<input name="lvCoupon.code" type="hidden" size="30" value="${lvCoupon.code}"/>
			<input name="lvCoupon.modifyUserId" type="hidden" size="30" value="${lvCoupon.modifyUserId}"/>
			<input name="lvCoupon.isDel" type="hidden" size="30" value="${lvCoupon.isDel}"/>
			<input name="lvCoupon.oldCouponCode" type="hidden" value="${lvCoupon.couponCode}">
			<input name="lvCoupon.couponType" type="hidden" value="${lvCoupon.couponType }">
			<input name="lvCoupon.isActivate" type="hidden" value="${lvCoupon.isActivate }">
			
				<!-- 生成表单 -->
						<p>
							<label>优惠卷名称：</label>
							<input name="lvCoupon.couponName" type="text" size="32" maxlength="128" value="${lvCoupon.couponName}"/>
						</p>
						<p>
							<label>面值：</label>
							<input name="lvCoupon.faceValue" type="text" size="32" maxlength="8" class="required numberEqual" value="${lvCoupon.faceValue}"/>
						</p>
						<p>
							<label>币种：</label>
							<select name="lvCoupon.currency" class="required">
							<option value="">==请选择==</option>
							<option value="USD" <c:if test="${lvCoupon.currency=='USD'}">selected="selected"</c:if>>USD</option>
						    </select>
						    <font color="red">*</font>
						</p>
						<p>
							<label>优惠码：</label>
							<input name="lvCoupon.couponCode" type="text" size="32" maxlength="30" class="required" readonly="readonly" value="${lvCoupon.couponCode}"/>
						</p>
						<p>
							<label>有效期：</label>
							<input type="text" name="lvCoupon.validityDate" class="required date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvCoupon.validityDate" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>

						<p>
							<label>优惠卷类型：</label>
							<select name="lvCoupon.couponType" class="required" disabled="disabled">
							<option value="">==请选择==</option>
							<option value="1" <c:if test="${lvCoupon.couponType==1}">selected="selected"</c:if>>特殊优惠卷</option>
							<option value="0" <c:if test="${lvCoupon.couponType==0}">selected="selected"</c:if>>普通优惠卷</option>
						    </select>
						</p>
				
						<p>
							<label>创建时间：</label>
							<input type="text" name="lvCoupon.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvCoupon.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
						    <input type="text" name="lvCoupon.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvCoupon.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvCoupon.modifyUserName" type="text" size="32" readonly="readonly" value="${lvCoupon.modifyUserName}"/>
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