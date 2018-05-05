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

function mainCurrencyChange(){
	var mailCurrency=$("#mainCurrency").find("option:selected");
	$("#mainCurrencyName").val(mailCurrency.text());
	$("#currencyId option").remove();
	$("#currencyId").append("<option value=''>==请选择==</option>");
	if(mailCurrency.val()=='USD'){
		$("#currencyId").append("<option value='CNY'>CNY  人民币</option>");
		$("#currencyId").append("<option value='EUR'>EUR  欧元</option>");
		$("#currencyId").append("<option value='AUD'>AUD  澳元</option>");
		$("#currencyId").append("<option value='GBP'>GBP  英镑</option>");
		$("#currencyId").append("<option value='HKD'>HKD  港币</option>");
		$("#currencyId").append("<option value='JPY'>JPY  日元</option>");
		$("#currencyId").append("<option value='KER'>KER  韩元</option>");
		$("#currencyId").append("<option value='THB'>THB  泰铢</option>");
		$("#currencyId").append("<option value='TWD'>TWD  台币</option>");
	}else if(mailCurrency.val()=='CNY'){
		$("#currencyId").append("<option value='USD'>USD  美元</option>");
		$("#currencyId").append("<option value='EUR'>EUR  欧元</option>");
		$("#currencyId").append("<option value='AUD'>AUD  澳元</option>");
		$("#currencyId").append("<option value='GBP'>GBP  英镑</option>");
		$("#currencyId").append("<option value='HKD'>HKD  港币</option>");
		$("#currencyId").append("<option value='JPY'>JPY  日元</option>");
		$("#currencyId").append("<option value='KER'>KER  韩元</option>");
		$("#currencyId").append("<option value='THB'>THB  泰铢</option>");
		$("#currencyId").append("<option value='TWD'>TWD  台币</option>");
	}else if(mailCurrency.val()=='VB'){
		$("#currencyId").append("<option value='USD'>USD  美元</option>");
		$("#currencyId").append("<option value='CNY'>CNY  人民币</option>");
		$("#currencyId").append("<option value='EUR'>EUR  欧元</option>");
		$("#currencyId").append("<option value='AUD'>AUD  澳元</option>");
		$("#currencyId").append("<option value='GBP'>GBP  英镑</option>");
		$("#currencyId").append("<option value='HKD'>HKD  港币</option>");
		$("#currencyId").append("<option value='JPY'>JPY  日元</option>");
		$("#currencyId").append("<option value='KER'>KER  韩元</option>");
		$("#currencyId").append("<option value='THB'>THB  泰铢</option>");
		$("#currencyId").append("<option value='TWD'>TWD  台币</option>");
	}
	
	
	
}
</script>
<div class="page">
	<div class="pageContent">
	<form method="post" action="lvExchangeRateAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="lvExchangeRate.currencyName" id="currencyName">
		<input type="hidden" name="lvExchangeRate.mainCurrencyName" id="mainCurrencyName">		
		<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<p>
					<label>基准货币名称：</label>
					<select name="lvExchangeRate.mainCurrency" id="mainCurrency" onchange="mainCurrencyChange()" class="required">
					      <option value="">==请选择==</option>
						  <option value="USD">USD  美元</option>
						  <option value="CNY">CNY  人民币</option>
						  <option value="VB">VB V币</option>
					</select>
				</p>	
				<p>
				<label>兑换货币名称：</label>
				<select name="lvExchangeRate.currency" id="currencyId" onchange="currencyChange()" class="required">
						 <option value="">==请选择==</option>
				</select>
			</p>
			<p>
				<label>兑换汇率：</label>
				<input name="lvExchangeRate.exchangeRate" type="text" size="30" maxlength="11" class="required numberNew numberToThree"/>
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
