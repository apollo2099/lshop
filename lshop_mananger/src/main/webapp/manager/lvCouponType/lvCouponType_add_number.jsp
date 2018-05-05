<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponTypeAction!updateTotalNumber.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCouponType.id" type="hidden" size="30" value="${lvCouponType.id}"/>
			<input name="lvCouponType.total" type="hidden" size="30" value="${lvCouponType.total}"/>
				<!-- 生成表单 -->
					<p>
				<label>追加数量</label>
				<input type="text" name="lvCouponType.addNumber" size="32" maxlength="4" class="required digitsNoZore" >
				</p>
				<div class="divider"></div>
		            <p>
					1)优惠券总数${lvCouponType.total}张
					</p>
					 <p>
					2)未发放数目${lvCouponType.noGrantNumber}张
					</p>
			        <p>
					3)已发放数目${lvCouponType.grantNumber}张
					</p>
					<p>
					4)已使用数目${lvCouponType.usedNumber}张
					</p>
			        <p>
					5)可剩余分配库存${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber}张
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