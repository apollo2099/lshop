<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
<form method="post" action="lvOrderAction!sendOrder.action?json.navTabId=${json.navTabId }"
	 class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	 <input type="hidden" name="versionTime" value="${lvOrder.modifyTime }">
	 <input type="hidden" name="lvOrder.storeId" value="${lvOrder.storeId }">
		<div class="pageContent">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<p>
					<label>
						订单编号  ：
					</label>
					<input type="text" name="lvOrder.oid" value="${lvOrder.oid }" size="32" readonly="readonly">
				</p>
				<p>
					<label>
						用户Email：
					</label>
					<input type="text" name="lvOrder.userEmail" value="${lvOrder.userEmail }" size="32" readonly="readonly">
					
				</p>
				<p>
					<label>
						收货人姓名 ：
					</label>
					<input type="text" name="lvOrderAddress.relName" value="${lvOrderAddress.relName }" size="32" readonly="readonly">
				</p>
				<p>
					<label>
						物流公司 ：
					</label>
					<input type="text" name="lvOrder.expressCompany" size="32" class="required">
				</p>
				<p>
					<label>
						快递名称 ：
					</label>
					<input type="text" name="lvOrder.expressName" size="32" class="required">
				</p>
					<p>
					<label>
						快递单号 ：
					</label>
					<input type="text" name="lvOrder.expressNum" size="32" class="required">
				</p>
				<dl class="nowrap">
					<dd>
						<hr width="1000">
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						邮件标题：
					</dt>
					<dd>
						<input type="text" name="title" id="title" size="110"
							 class="required">
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						邮件内容：
					</dt>
					<dd>
						<textarea class="editor" name="content" id="content" rows="20" cols="90"></textarea>
					</dd>
				</dl>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								发货
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