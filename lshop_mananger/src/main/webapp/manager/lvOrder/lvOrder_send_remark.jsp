<%@ page language="java" pageEncoding="utf-8" import="com.lshop.common.util.Constants"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="lvOrderAction!editSendRemark.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
					    <input name="lvOrder.oid" type="hidden" value="${lvOrder.oid}"/>
					    <input type="hidden" name="versionTime" value="${lvOrder.modifyTime }">
						<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
						<p>
							<label>订单编号：</label>
							${lvOrder.oid}
						</p>
						<p>
						    <label>发货备注：</label>
						    <textarea rows="8" cols="50" name="lvOrder.sendRemark" maxlength="315">${lvOrder.sendRemark}</textarea>
						<p>
								
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