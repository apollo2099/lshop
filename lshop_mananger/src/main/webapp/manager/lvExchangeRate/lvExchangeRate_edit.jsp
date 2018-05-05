<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//币种信息
function currencyChange(){
	$("#currencyName").val($("#currencyId").find("option:selected").text());
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvExchangeRateAction!edit.action?json.navTabId=${json.navTabId}" 
		      class="pageForm required-validate" 
		      onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvExchangeRate.id" type="hidden" size="30" value="${lvExchangeRate.id}"/>
			<input name="lvExchangeRate.mainCurrency" type="hidden" size="30" value="${lvExchangeRate.mainCurrency}"/>
			<input name="lvExchangeRate.mainCurrencyName" type="hidden" size="30" value="${lvExchangeRate.mainCurrencyName}"/>
			<input name="lvExchangeRate.storeId" type="hidden" size="30" value="${lvExchangeRate.storeId}"/>
			<input name="lvExchangeRate.code" type="hidden" size="30" value="${lvExchangeRate.code}"/>
			<input name="lvExchangeRate.modifyUserId" type="hidden" size="30" value="${lvExchangeRate.modifyUserId}"/>
			<input type="hidden" name="lvExchangeRate.currencyName" id="currencyName" value="${lvExchangeRate.currencyName }">	
			<input type="hidden" name="lvExchangeRate.oldCurrency" value="${lvExchangeRate.currency }">
			<input type="hidden" name="lvExchangeRate.currency" value="${lvExchangeRate.currency }">
				<!-- 生成表单 -->
				<p>
					<label>基准货币名称：</label>
					<select name="lvExchangeRate.mainCurrency"  class="required" disabled="disabled">
							 <option value="">==请选择==</option>
							 <option value="CNY" <c:if test="${lvExchangeRate.mainCurrency=='CNY' }">selected="selected"</c:if>>CNY  人民币</option>
							 <option value="USD" <c:if test="${lvExchangeRate.mainCurrency=='USD' }">selected="selected"</c:if>>USD  美元</option>
							</select>
				</p>
				
	           <p>
					<label>兑换货币名称：</label>
					<select name="lvExchangeRate.currency"   class="required" disabled="disabled">
							 <option value="">==请选择==</option>
							 <option value="USD" <c:if test="${lvExchangeRate.currency=='USD' }">selected="selected"</c:if>>USD  美元</option>
							 <option value="CNY" <c:if test="${lvExchangeRate.currency=='CNY' }">selected="selected"</c:if>>CNY  人民币</option>
							 <option value="EUR" <c:if test="${lvExchangeRate.currency=='EUR' }">selected="selected"</c:if>>EUR  欧元</option>
							 <option value="AUD" <c:if test="${lvExchangeRate.currency=='AUD' }">selected="selected"</c:if>>AUD  澳元</option>
							 <option value="GBP" <c:if test="${lvExchangeRate.currency=='GBP' }">selected="selected"</c:if>>GBP  英镑</option>
							 <option value="HKD" <c:if test="${lvExchangeRate.currency=='HKD' }">selected="selected"</c:if>>HKD  港币</option>
							 <option value="JPY" <c:if test="${lvExchangeRate.currency=='JPY' }">selected="selected"</c:if>>JPY  日元</option>
							 <option value="KER" <c:if test="${lvExchangeRate.currency=='KER' }">selected="selected"</c:if>>KER  韩元</option>
							 <option value="THB" <c:if test="${lvExchangeRate.currency=='THB' }">selected="selected"</c:if>>THB  泰铢</option>
							 <option value="TWD" <c:if test="${lvExchangeRate.currency=='TWD' }">selected="selected"</c:if>>TWD  台币</option>
							</select>
				</p>
				<p>
					<label>兑换汇率：</label>
					<input name="lvExchangeRate.exchangeRate" type="text" size="30" maxlength="11"  value="${lvExchangeRate.exchangeRateStr}" class="required numberNew numberToThree"/>
				</p>
				<p>
					<label>创建时间：</label>
					<input name="lvExchangeRate.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
					 value="<s:date name="lvExchangeRate.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				</p>
				<p>
					<label>修改时间：</label>
					<input name="lvExchangeRate.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
					 value="<s:date name="lvExchangeRate.modifyTime" format="yyyy-MM-dd HH:mm:ss" />"  />			
				</p>
				<p>
					<label>修改人名称：</label>
					<input name="lvExchangeRate.modifyUserName" type="text" size="30" value="${lvExchangeRate.modifyUserName}" readonly="readonly"/>
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