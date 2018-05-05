<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubProductAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvPubProduct.id" type="hidden" size="30" value="${lvPubProduct.id}"/>
			<input name="lvPubProduct.code" type="hidden" size="30" value="${lvPubProduct.code}"/>
			<input name="lvPubProduct.orderValue" type="hidden" size="30" value="${lvPubProduct.orderValue}"/>
			<input name="lvPubProduct.modifyUserId" type="hidden" size="30" value="${lvPubProduct.modifyUserId}"/>
			<input name="lvPubProduct.status" type="hidden" size="30" value="${lvPubProduct.status}"/>
			<input name="lvPubProduct.oldPcode" type="hidden" size="30" value="${lvPubProduct.pcode}"/>
			
				<!-- 生成表单 -->
						<p>
							<label>商品名称：</label>
							<input name="lvPubProduct.productName" type="text" size="30" maxlength="64" class="required" value="${lvPubProduct.productName}"/>
						</p>
						<p>
							<label>商品型号：</label>
							<input name="lvPubProduct.productModel" type="text" size="30" maxlength="32" value="${lvPubProduct.productModel}"/>
						</p>
						<p>
							<label>SAS对接code：</label>
							<input name="lvPubProduct.pcode" type="text" size="30" maxlength="32" class="required" value="${lvPubProduct.pcode}"/>
						</p>
						<p>
							<label>创建时间：</label>
						<input type="text" name="lvPubProduct.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="30"
                        value="<s:date name="lvPubProduct.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
	                    <input type="text" name="lvPubProduct.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="30"
                        value="<s:date name="lvPubProduct.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvPubProduct.modifyUserName" type="text" size="30" readonly="readonly" value="${lvPubProduct.modifyUserName}"/>
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