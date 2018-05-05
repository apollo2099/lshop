<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponTypeAction!updateDisable.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvCouponType.id" type="hidden" size="30" value="${lvCouponType.id}"/>
			<input name="lvCouponType.code" type="hidden" size="30" value="${lvCouponType.code}"/>
				<!-- 生成表单 -->
		          <p>
					     说明：停用优惠劵会让已发放的优惠劵无效
				  </p>
				  <p>
				   <label>停用原因</label>
						<textarea rows="5" cols="30" name="lvCouponType.disableReasons" maxlength="120" class="required"></textarea>
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