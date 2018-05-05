<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCoupon.isActivate" type="hidden" value="0" />
			
				<!-- 生成表单 -->
					    <p>
							<label>优惠卷名称：</label>
							<input name="lvCoupon.couponName" type="text" size="30" maxlength="128" />
						</p>
						<p>
							<label>优惠码：</label>
							<input name="lvCoupon.couponCode" type="text" size="30" maxlength="30" class="required"/>
						</p>
						<p>
							<label>有效期：</label>
							<input name="lvCoupon.validityDate" class="required textInput date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" />
						</p>
						<p>
							<label>面值：</label>
							<input name="lvCoupon.faceValue" type="text" size="30" maxlength="8" class="required numberEqual"/>
						</p>
						<p>
							<label>币种：</label>
							<select name="lvCoupon.currency" class="required">
							<option value="">==请选择==</option>
							<option value="USD" selected="selected">USD</option>
						    </select>
						    <font color="red">*</font>
						</p>
						<p>
							<label>优惠卷类型：</label>
							<select name="lvCoupon.couponType" class="required">
							<option value="">==请选择==</option>
							<option value="1" selected="selected">特殊优惠卷</option>
							<option value="0" >普通优惠卷</option>
						    </select>
						    <font color="red">*</font> 
						</p>
						<p >
						   <font color="red" style="font-size:12px;">备注： 特殊优惠券可以使用多次，普通优惠券只能使用一次！</font>
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