<%@ page language="java" pageEncoding="utf-8" import="com.lshop.common.util.Constants"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="lvOrderAction!updatePrice.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvOrder.oid" type="hidden" value="${lvOrder.oid}"/>
            <input type="hidden" name="json.navTabId" value="${json.navTabId}" />
            <input type="hidden" name="lvOrder.storeId" value="${lvOrder.storeId }">
            <input type="hidden" name="versionTime" value="${lvOrder.modifyTime }">
            <input type="hidden" name="lvOrder.createTime" value="${lvOrder.createTime}">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
					    
						<p>
							<label>订单编号：</label>
							${lvOrder.oid}
						</p>
						<p>
						    <label>订单总金额：</label>
						    <input type="text" name="lvOrder.totalPrice" value="${lvOrder.totalPrice}" size="32"  class="required numberNew"/>
						</p>
								
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
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