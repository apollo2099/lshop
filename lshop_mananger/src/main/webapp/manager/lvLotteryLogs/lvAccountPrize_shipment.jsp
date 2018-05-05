<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
    var isFlag=checkMobile();
    if(!isFlag){
      alertMsg.error('電話和手機必須填寫其中一個!');
      return false;
    }
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		   callback(json);
		},
		error: DWZ.ajaxError
	});
	return false;
}

function checkMobile(){
  var mobile=$("#mobile").val();
  var tel=$("#tel").val();
  if(mobile==''&&tel==''){
       /*
       $("#mobileInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
	   $("#telInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");*/
       return false;
  } 
  return true;
}

</script>
<div class="page">
	<div class="pageContent">
	<form method="post" action="lvLotteryLogsAction!shipment.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="lvAccountPhysicalTicket.id" value="${lvAccountPhysicalTicket.id }">
			<input type="hidden" name="lvAccountPhysicalTicket.accountPrizeCode" value="${lvAccountPhysicalTicket.accountPrizeCode }">
			<input type="hidden" value="${lvAccountPhysicalTicket.provinceId }" id="provinceIdTmp">
			<input type="hidden" name="lvAccountPhysicalTicket.relCode"  size="30" value="${lvAccountPhysicalTicket.relCode}"/>
			<input type="hidden" name="lvAccountPrize.code" value="${lvAccountPrize.code }">
			<input type="hidden" name="lvAccountPrize.id" value="${lvAccountPrize.id }">
			
  		<div class="pageFormContent" layoutH="56">
				<dl >
					<dt>联系人名称：</dt>
					<dd>
                 <input name="lvAccountPhysicalTicket.relName" type="text" size="30"
							maxlength="32" class="required" value="${lvAccountPhysicalTicket.relName}"/>
					</dd>				  
				</dl>
				<dl >
					<dt>邮政编码：</dt>
					<dd>
                 <input name="lvAccountPhysicalTicket.postCode" type="text" size="30"
							maxlength="16" class="required " value="${lvAccountPhysicalTicket.postCode}"/>
					</dd>				  
				</dl>
				<dl >
					<dt>电话：</dt>
					<dd>
                 <input name="lvAccountPhysicalTicket.tel" type="text" size="30" maxlength="16" id="tel" onblur="checkMobile()"
							 value="${lvAccountPhysicalTicket.tel}"/><span id="telInfo"></span>
					</dd>				  
				</dl>
				<dl >
					<dt>手机：</dt>
					<dd>
                 <input name="lvAccountPhysicalTicket.mobile" type="text" size="30" maxlength="32" id="mobile" onblur="checkMobile()"
							 class="digits" value="${lvAccountPhysicalTicket.mobile}"/><span id="mobileInfo"></span>
					</dd>				  
				</dl>
				    <dl class="nowrap">
				    <dt>联系地址：</dt>
				   <dd>
				   <label >
                   <input name="lvAccountPhysicalTicket.adress" type="text" size="20" maxlength="128" class="required" value="${lvAccountPhysicalTicket.adress }" alt="街道" />
                   </label>
                   <label >
                   <input name="lvAccountPhysicalTicket.cityName" type="text" size="20" maxlength="32" class="required" value="${lvAccountPhysicalTicket.cityName }" alt="县/市" />
                   </label>
                   <label >
                   <input id="txtProvinceEdit" name="lvAccountPhysicalTicket.provinceName" type="text" size="20" maxlength="32" class="required" value="${lvAccountPhysicalTicket.provinceName }" alt="洲/省" />
                   <select id="selProvinceEdit" name="lvAccountPhysicalTicket.provinceId" onchange="changeProvince()"></select>
                   </label>
                    <label >
                   <select class="required"  name="lvAccountPhysicalTicket.contryId" id="contryidEdit" onchange="changeCountry()">
						<option value="">==请选择==</option>
						<s:iterator value="#request.areaList"  id="c">
						  <option value="${c.id}"
						  <c:if test="${lvAccountPhysicalTicket.contryId==c.id }">selected="selected"</c:if>
						  >${c.nameen}</option>
						</s:iterator>
						</select>
                        <input id="contrynameIdEdit" name="lvAccountPhysicalTicket.contryName" type="hidden" size="20" maxlength="32" class="required" value="${lvAccountPhysicalTicket.contryName }" alt="国家" />
                   </label>
                              
              
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
</div>

<script type="text/javascript">
$(document).ready(function(){
changeCountry();
})


//根据国家id查询省份信息
function changeCountry(){
	var countryVal = $("#contryidEdit").find("option:selected").val();
	$("#contrynameIdEdit").val($("#contryidEdit").find("option:selected").text());
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toProvinceEn.action",
		  data: "countryId="+countryVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        var listTmp=data.listTmp;  	
		        $("#selProvinceEdit option").remove();
				var temp=$("#provinceIdTmp").val();
				for(var k=0;k<listTmp.length;k++){ 
					var provinceName = listTmp[k].provinceName; 
					var provinceId = listTmp[k].provinceId; 
					if(temp==provinceId){
						  $("#selProvinceEdit").append("<option value="+provinceId+" selected='selected'>"+provinceName+"</option>");
					}else{
						  $("#selProvinceEdit").append("<option value="+provinceId+" >"+provinceName+"</option>");
					}
					
				 }
				 $("#selProvinceEdit").show();
		         $("#txtProvinceEdit").hide();
		     }else{
		    	 $("#selProvinceEdit option").remove();
			     $("#selProvinceEdit").hide();
			     $("#txtProvinceEdit").show();
		     }
		  }
	});
}


//根据省份变化对省份名称赋值
function changeProvince(){
	$("#txtProvinceEdit").val($("#selProvinceEdit").find("option:selected").text());
}
</script>
