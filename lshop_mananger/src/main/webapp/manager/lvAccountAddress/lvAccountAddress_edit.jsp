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
	<form method="post" action="lvAccountAddressAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, dialogAjaxDone);">
			<input type="hidden" name="lvAccountAddress.id" value="${lvAccountAddress.id }">
			<input type="hidden" name="lvAccountAddress.isDefault" value="${lvAccountAddress.isDefault }">
			<input type="hidden" name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }">
			<input type="hidden" name="lvAccountAddress.code" value="${lvAccountAddress.code }">
			<input type="hidden" value="${lvAccountAddress.provinceId }" id="provinceIdTmp">
			
  		<div class="pageFormContent" layoutH="56">
				<dl >
					<dt>联系人编号：</dt>
					<dd>
					    <input name="lvAccountAddress.relCode" type="text" size="30"
							maxlength="32" class="required " value="${lvAccountAddress.relCode}" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl >
					<dt>联系人名称：</dt>
					<dd>
                 <input name="lvAccountAddress.relName" type="text" size="30"
							maxlength="32" class="required" value="${lvAccountAddress.relName}" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl >
					<dt>邮政编码：</dt>
					<dd>
                 <input name="lvAccountAddress.postCode" type="text" size="30"
							maxlength="16" class="required " value="${lvAccountAddress.postCode}"/>
					</dd>				  
				</dl>
				<dl >
					<dt>电话：</dt>
					<dd>
                 <input name="lvAccountAddress.tel" type="text" size="30" maxlength="16" id="tel" onblur="checkMobile()"
							 value="${lvAccountAddress.tel}"/><span id="telInfo"></span>
					</dd>				  
				</dl>
				<dl >
					<dt>手机：</dt>
					<dd>
                 <input name="lvAccountAddress.mobile" type="text" size="30" maxlength="32" id="mobile" onblur="checkMobile()"
							 class="digits" value="${lvAccountAddress.mobile}"/><span id="mobileInfo"></span>
					</dd>				  
				</dl>
				    <dl class="nowrap">
				    <dt>联系地址：</dt>
				   <dd>
				   <label >
                   <input name="lvAccountAddress.adress" type="text" size="20" maxlength="128" class="required" value="${lvAccountAddress.adress }" alt="街道" />
                   </label>
                   <label >
                   <input name="lvAccountAddress.cityName" type="text" size="20" maxlength="32" class="required" value="${lvAccountAddress.cityName }" alt="县/市" />
                   </label>
                   <label >
                   <input id="txtProvinceEdit" name="lvAccountAddress.provinceName" type="text" size="20" maxlength="32" class="required" value="${lvAccountAddress.provinceName }" alt="洲/省" />
                   <select id="selProvinceEdit" name="lvAccountAddress.provinceId" onchange="changeProvince()"></select>
                   </label>
                    <label >
                   <select class="required"  name="lvAccountAddress.contryId" id="contryidEdit" onchange="changeCountry()">
						<option value="">==请选择==</option>
						<s:iterator value="#request.areaList"  id="c">
						  <option value="${c.id}"
						  <c:if test="${lvAccountAddress.contryId==c.id }">selected="selected"</c:if>
						  >${c.nameen}</option>
						</s:iterator>
						</select>
                        <input id="contrynameIdEdit" name="lvAccountAddress.contryName" type="hidden" size="20" maxlength="32" class="required" value="${lvAccountAddress.contryName }" alt="国家" />
                   </label>
                              
              
				   </dd>				  
			   </dl>

				<dl >
					<dt>创建时间：</dt>
					<dd>
                 <input name="lvAccountAddress.createTime" type="text" size="30"  format="yyyy-MM-dd HH:mm:ss"
						  value="<s:date name="lvAccountAddress.createTime" format="yyyy-MM-dd HH:mm:ss"/>" class="date" readonly="true"/>
					</dd>				  
				</dl>
				<dl >
					<dt>修改人：</dt>
					<dd>
                 <input name="lvAccountAddress.modifyUserName" type="text" size="30"
							value="${lvAccountAddress.modifyUserName}" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl >
					<dt>修改时间：</dt>
					<dd>
                <input name="lvAccountAddress.modifyTime" type="text" size="30"  format="yyyy-MM-dd HH:mm:ss"
						  value="<s:date name="lvAccountAddress.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>" class="date" readonly="true"/>
					</dd>				  
				</dl>
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

<script type="text/javascript">
$(document).ready(function(){　　
changeCountry();
})


//根据国家id查询省份信息
function changeCountry(){
	var countryVal = $("#contryidEdit").val();
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
