<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function onCheangePayValue(){
	$('#payNameId').val($('#payValueId option:selected').text());
	if($('#payValueId').val()!=''){
	$('#pvalueT').text('当前支付方式value值为：'+$('#payValueId').val());
	}
}

function changePayType(){
	var payType = $("#payType").find("option:selected").val();
    $.ajax({
		  type: "POST",
		  url: "lvPaymentStyleMngAction!toFindStoreByPayType.action",
		  data: "lvPaymentStyle.payType="+payType,
		  dataType:"json",
		  success: function(data){
		     $("#storeInfo option").remove();
		     $("#storeInfo").append("<option value=''>==请选择==</option>");
		     if(data!=null){
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var storeFlag = listTmp[k].storeFlag; 
					var storeName = listTmp[k].storeName; 
					$("#storeInfo").append("<option value='"+storeFlag+"'>"+storeName+"</option>");
				 }
		     }
		  }
	});
}


</script>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPaymentStyleMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvPaymentStyle.isActivity" type="hidden" value="0"/>
			<input name="lvPaymentStyle.orderValue" type="hidden" value="0"/>
			<input name="lvPaymentStyle.payName" id="payNameId" type="hidden"   size="30" maxlength="64" class="true"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
					  <p>
							<label>支付类型：</label>
							<select name="lvPaymentStyle.payType" id="payType" onchange="changePayType()" class="required">
								<option value="">==请选择==</option>
								<option value="0">在线支付</option>
								<option value="1">在线充值</option>
							</select>
						</p>
				       <p>
							<label>所属关系：</label>
							<select name="lvPaymentStyle.storeFlag" class="required" id="storeInfo">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>选择支付方式：</label>
							<select name="lvPaymentStyle.payValue" id="payValueId" onchange="onCheangePayValue()" class="required">
							<option value="">==请选择==</option>
							<s:iterator value="#request.payDataList" id="o">
							<option value="${o.payValue}">${o.payName}</option>
							</s:iterator>
							</select>
						</p>
						<p>
							<label>支付币种：</label>
							<select name="lvPaymentStyle.currency" class="required">
							<option value="">==请选择==</option>
							<option  value="USD" >USD</option>
							<option  value="CNY" >CNY</option>
						    </select>
						</p>  
						
						<p id="pvalueT"></p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>