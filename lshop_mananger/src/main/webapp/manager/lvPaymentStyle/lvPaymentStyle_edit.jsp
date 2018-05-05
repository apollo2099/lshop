<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
function changePayChannel(){
	var payChannel=$("#payChannel").find("option:selected");
	if(parseInt(payChannel.val())==1){
		$("#noPaymentSystem").hide();
		$("#paymentSystem").show();
		$("#noPaymentSystem input").attr("class","");
		$("#paymentSystem input").attr("class","required");
	}else if(parseInt(payChannel.val())==0){
		$("#noPaymentSystem").show();
		$("#paymentSystem").hide();
		$("#noPaymentSystem input").attr("class","required");
		$("#paymentSystem input").attr("class","");
	}
}
</script>


<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPaymentStyleMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvPaymentStyle.id" type="hidden" value="${lvPaymentStyle.id}"/>
			<input name="lvPaymentStyle.payType" type="hidden" value="${lvPaymentStyle.payType}"/>
			<input name="lvPaymentStyle.payValue" type="hidden" value="${lvPaymentStyle.payValue}"/>
			<input name="lvPaymentStyle.storeFlag" type="hidden" value="${lvPaymentStyle.storeFlag}"/>
			<input name="lvPaymentStyle.code" type="hidden" value="${lvPaymentStyle.code}"/>
			<input name="lvPaymentStyle.params" type="hidden" value="<s:property escapeHtml="true" value="lvPaymentStyle.params"/>"/>
			<input name="lvPaymentStyle.paymentSystemParams" type="hidden" value="<s:property escapeHtml="true" value="lvPaymentStyle.paymentSystemParams"/>"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>支付类型：</label>
							<select disabled="disabled">
								<option <c:if test="${lvPaymentStyle.payType eq 0}">selected</c:if>>在线支付</option>
								<option <c:if test="${lvPaymentStyle.payType eq 1}">selected</c:if>>在线充值</option>
							</select>
						</p>
						<p>
							<label>支付名称：</label>
							<input name="lvPaymentStyle.payName" type="text" size="36" maxlength="640" class="required" value="<s:property escapeHtml="true" value="lvPaymentStyle.payName"/>" />
						</p>
						<p>
							<label>是否开启：</label>
							<s:select name="lvPaymentStyle.isActivity" list="#{1:'开启',0:'关闭'}"></s:select>
						</p>
						<p>
							<label>支付通道：</label>
							<s:select name="lvPaymentStyle.payChannel" list="#{0:'内置接口',1:'支付系统'}" cssClass="required" onchange="changePayChannel()" id="payChannel"></s:select>
						</p>
						<%--支付宝 --%>
						<div id="noPaymentSystem">
						<s:if test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_ALIPAY||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_PAYPALOUT_VISA||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_PAYPALOUT_MASTER||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_PAYPALOUT_JCB||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_OLD_PAYPALOUT_VISA||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_OLD_PAYPALOUT_MASTER||lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_OLD_PAYPALOUT_JCB">
						<dl class="nowrap">
							<label>商户号：</label>
							<input name="partner" type="text" size="64" maxlength="64" class="required" value="${partner}"/>
						</dl>
						<dl class="nowrap">
							<label>卖家支付宝帐号：</label>
							<input name="seller_email" type="text" size="64" maxlength="64" class="required" value="${seller_email}"/>
						</dl>
						<dl class="nowrap">
							<label>密钥key：</label>
							<input name="key" type="text" size="64" maxlength="128" class="required" value="${key}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="https://mapi.alipay.com/gateway.do?"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:if>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_95EPAY">
					    <%--双钱支付方式 --%>
					    <dl class="nowrap">
							<label>商户号：</label>
							<input name="merno" type="text" size="64" maxlength="64" class="required" value="${merno}"/>
						</dl>
						<dl class="nowrap">
							<label>密钥key：</label>
							<input name="key" type="text" size="64" maxlength="128" class="required" value="${key}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="https://www.95gateway.com/sslpayment"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:elseif>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_PAYPAL">
						<%--palpay支付 --%>
					    <dl class="nowrap">
							<label>papal账户：</label>
							<input name="business" type="text" size="64" maxlength="64" class="required" value="${business}"/>
						</dl>
						<dl class="nowrap">
							<label>密钥key：</label>
							<input name="key" type="text" size="64" maxlength="128" class="required" value="${key}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="http://www.paypal.com/cgi-bin/webscr"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:elseif>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_KQ">
						<%--快钱支付 --%>
					    <dl class="nowrap">
							<label>外卡账户：</label>
							<input name="business" type="text" size="64" maxlength="64" class="required" value="${business}"/>
						</dl>
						<dl class="nowrap">
							<label>终端编号：</label>
							<input name="termId" type="text" size="64" maxlength="128" class="required" value="${termId}"/>
						</dl>
						<dl class="nowrap">
							<label>密钥key：</label>
							<input name="key" type="text" size="64" maxlength="128" class="required" value="${key}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="https://cpay.99bill.com/fgw/payment/purchase.htm"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:elseif>
					
						<%--paydollar支付 --%>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_PAYDOLLAR">
						<dl class="nowrap">
							<label>商户ID：</label>
							<input name="merchantId" type="text" size="64" maxlength="64" class="required" value="${merchantId}"/>
						</dl>	
	                      <dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="https://test.paydollar.com/b2cDemo/eng/payment/payForm.jsp"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						<dl class="nowrap">
							<label>密钥：</label>
							<input name="key" type="text" size="64" maxlength="128" class="required" value="${key}"/>
						</dl>
						<dl class="nowrap">
							<label>支付成功页面：</label>
							<input name="successUrl" type="text" size="64" maxlength="128" class="required" value="${successUrl}"/>
						</dl>
						<dl class="nowrap">
							<label>支付失败页面：</label>
							<input name="failUrl" type="text" size="64" maxlength="128" class="required" value="${failUrl}"/>
						</dl>
						<dl class="nowrap">
							<label>取消交易页面：</label>
							<input name="cancelUrl" type="text" size="64" maxlength="128" class="required" value="${cancelUrl}"/>
						</dl>
						</s:elseif>
						
						<%--支付宝国际银行卡 --%>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_ALIPAY_INTL">
					    <dl class="nowrap">
							<label>商户编号(userId)：</label>
							<input name="userId" type="text" size="64" maxlength="64" class="required" value="${userId}"/>
						</dl>
						<dl class="nowrap">
							<label>商户密码(password)：</label>
							<input name="password" type="text" size="64" maxlength="128" class="required" value="${password}"/>
						</dl>
						<dl class="nowrap">
							<label>密钥(entityId)：</label>
							<input name="entityId" type="text" size="64" maxlength="128" class="required" value="${entityId}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="http://www.paypal.com/cgi-bin/webscr"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:elseif>
						<%--钱海支付 --%>
						<s:elseif test="lvPaymentStyle.payValue==@com.lshop.common.util.Constants@PAY_METHOD_QH">
					    <dl class="nowrap">
							<label>账户(account)：</label>
							<input name="account" type="text" size="64" maxlength="64" class="required" value="${account}"/>
						</dl>
						<dl class="nowrap">
							<label>终端号(terminal)：</label>
							<input name="terminal" type="text" size="64" maxlength="128" class="required" value="${terminal}"/>
						</dl>
						<dl class="nowrap">
							<label>secureCode：</label>
							<input name="secureCode" type="text" size="64" maxlength="128" class="required" value="${secureCode}"/>
						</dl>
						<dl class="nowrap">
						    <label>提交URL网关：</label>
						    <s:if test="lvPaymentStyle.url==null||lvPaymentStyle.url==''">
						    <input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="https://secure.oceanpayment.com/gateway/service/test"/>
						    </s:if>
						    <s:else>
							<input name="lvPaymentStyle.url" readonly="readonly" type="text" size="50" maxlength="128" class="required" value="${lvPaymentStyle.url}"/>
						    </s:else>
						</dl>
						</s:elseif>
						</div>
						
						<div id="paymentSystem">
							 <dl class="nowrap">
								<label>商户号：</label>
								<input name="paySysMerno" type="text" size="64" maxlength="64" class="required" value="${paySysMerno}"/>
							</dl>
							<dl class="nowrap">
								<label>密钥key：</label>
								<input name="paySysKey" type="text" size="64" maxlength="128" class="required" value="${paySysKey}"/>
							</dl>
							<dl class="nowrap">
							    <label>提交URL网关：</label>
								<input name="paySysUrl" type="text" size="64" maxlength="128" class="required" value="${paySysUrl}"/>
							</dl>
						</div>
						<p>
							<label>支付币种：</label>
							<select name="lvPaymentStyle.currency" class="required">
							<option value="">==请选择==</option>
							<option  value="USD" <c:if test="${lvPaymentStyle.currency=='USD'}">selected</c:if>>USD</option>
							<option  value="CNY" <c:if test="${lvPaymentStyle.currency=='CNY'}">selected</c:if>>CNY</option>
						    </select>
						</p>  
						<p>
							<label>排序值：</label>
							<input name="lvPaymentStyle.orderValue" type="text" size="7" maxlength="4" value="${lvPaymentStyle.orderValue}" class="required digits" />
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

<script type="text/javascript">
<!--
$(document).ready(function(){
	changePayChannel();
})
//-->
</script>