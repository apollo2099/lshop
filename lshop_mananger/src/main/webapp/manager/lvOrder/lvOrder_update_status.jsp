<%@ page language="java" pageEncoding="utf-8" import="com.lshop.common.util.Constants"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="lvOrderAction!updateOrderStatus.action"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvOrder.oid" type="hidden" value="${lvOrder.oid}"/>
            <input type="hidden" name="json.navTabId" value="${json.navTabId}" />
            <input type="hidden" name="lvOrder.storeId" value="${lvOrder.storeId }">
            <input type="hidden" name="oldStatus" value="${lvOrder.status }">
            <input type="hidden" name="oldPayStatus" value="${lvOrder.payStatus }">
            <input type="hidden" name="versionTime" value="${lvOrder.modifyTime }">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
					    
						<p>
							<label>订单编号：</label>
							${lvOrder.oid}
						</p>
						<p>
						    <label>发货状态：</label>
						    <select name="lvOrder.status" class="required">
						      <option value="">==请选择==</option>
						      <option value="0" <c:if test="${lvOrder.status==0 }">selected="selected"</c:if>>待发货</option>
						      <option value="1" <c:if test="${lvOrder.status==1 }">selected="selected"</c:if>>已发货</option>
						      <option value="2" <c:if test="${lvOrder.status==2 }">selected="selected"</c:if>>已收货 ,待评价</option>
						      <option value="4" <c:if test="${lvOrder.status==4 }">selected="selected"</c:if>>已完成</option>
						    </select>
						</p>
						<p>
						    <label>支付状态：</label>
						     <select name="lvOrder.payStatus" class="required">
						      <option value="">==请选择==</option>
						      <option value="0" <c:if test="${lvOrder.payStatus==0 }">selected="selected"</c:if>>未支付</option>
						      <option value="1" <c:if test="${lvOrder.payStatus==1 }">selected="selected"</c:if>>已支付,已确认</option>
						      <option value="2" <c:if test="${lvOrder.payStatus==2 }">selected="selected"</c:if>>已支付,未确认</option>
						    </select>
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