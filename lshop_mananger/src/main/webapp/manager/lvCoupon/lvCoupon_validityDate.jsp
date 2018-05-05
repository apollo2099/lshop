<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponAction!updateValidityDate.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCoupon.id" type="hidden" size="30" value="${lvCoupon.id}"/>
			
				<!-- 生成表单 -->
						<p>
							<label>优惠码：</label>
							<input name="lvCoupon.couponCode" type="text" size="32" maxlength="30" class="required" readonly="readonly" value="${lvCoupon.couponCode}"/>
						</p>
						<p>
							<label>有效期：</label>
							<input type="text" name="lvCoupon.validityDate" class="required date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvCoupon.validityDate" format="yyyy-MM-dd HH:mm:ss"/>"/>
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