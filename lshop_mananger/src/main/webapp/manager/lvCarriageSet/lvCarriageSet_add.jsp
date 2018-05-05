<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script>
function changeDeliveryBYPID(soureId,targetId){
	var countryId=$("#"+soureId+"").find("option:selected");
	var countryVal = countryId.val();
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toProvince.action",
		  data: "countryId="+countryVal,
		  dataType:"json",
		  success: function(data){
		     $("#"+targetId+" option").remove();
		     $("#"+targetId+"").append("<option value=''>==请选择==</option>");
		     if(data!=null){
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var provinceName = listTmp[k].provinceName; 
					var provinceId = listTmp[k].provinceId;
					$("#"+targetId+"").append("<option value='"+provinceId+"'>"+provinceName+"</option>");
				 }
		     }
		  }
	});
}
</script>

<div class="pageContent">
	<form method="post" action="lvCarriageSetAction!add.action?json.navTabId=${json.navTabId }" 
	                    class="pageForm required-validate" 
	                    onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		  				<p>
							<label>所属关系：</label>
							<select name="lvCarriageSet.storeId" class="required" style="width:150px">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
		        <dl>
					<dt>运费配送国家：</dt>
					<dd>
				      <select name="lvCarriageSet.deliveryId" class="required" onchange="changeDeliveryBYPID('countryId','provinceId')" style="width:150px" id="countryId">
						 <option value="">==请选择==</option>
						 <option value="100000">运费默认值地区</option>
						 	<c:foreach items="${areaList}" var="area">
						 	<option value="${area.id }">${area.namecn}</option>
				             </c:foreach>
					  </select>
					</dd>				  
				</dl>
				<dl>
					<dt>运费配送省份：</dt>
					<dd>
				      <select name="provinceId"  onchange="changeDeliveryBYPID('provinceId','cityId')"; style="width:150px" id="provinceId">
						 <option value="">==请选择==</option>
					  </select>
					</dd>				  
				</dl>
				<dl>
					<dt>运费配送市/州：</dt>
					<dd>
				      <select name="cityId"  style="width:150px" id="cityId">
						 <option value="">==请选择==</option>
					  </select>
					</dd>				  
				</dl>
				<dl >
				<dt>运费：</dt>
					<dd>
                       <input name="lvCarriageSet.carriage" class="required numberNew" type="text" size="32" maxlength="5" alt="请输入运费"/>
					</dd>				  
				</dl>
				<!--
				<dl>
				 
				<dt>币种：</dt>
					<dd>
                     <s:select list="#{'':'==请选择==','USD':'USD','RMB':'RMB'}" name="lvCarriageSet.currency" cssClass="required"></s:select>  
					</dd>				  
				</dl>
			-->
				<dl>
				<dt>备注：</dt>
					<dd>
                       <textarea rows="5" cols="50" name="lvCarriageSet.remark" maxlength="160"></textarea>
					</dd>				  
				</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
