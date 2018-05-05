<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubProductAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->

						<p>
							<label>商品名称：</label>
							<input name="lvPubProduct.productName" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>商品型号：</label>
							<input name="lvPubProduct.productModel" type="text" size="30" maxlength="32"/>
						</p>
						<p>
							<label>SAS对接code：</label>
							<input name="lvPubProduct.pcode" type="text" size="30" maxlength="32" class="required"/>
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