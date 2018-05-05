<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="lvOrderAction!befChangeStore.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&json.navTabId=${json.navTabId}"
		name="pagerFormAction" />
</jsp:include>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);"
		action="lvOrderAction!befChangeStore.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&json.navTabId=${json.navTabId}" method="post">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
				</tr><tr>
						</tr><tr>
						<td>国家</td><td>
						<select name="lvStore.countryId" disabled="disabled">
						<option value="">==请选择==</option>
						<c:foreach items="${areaList}" var="store">
						<option value="${store.id }" <c:if test="${lvStore.countryId==store.id }">selected="selected"</c:if>>${store.namecn }</option>
						</c:foreach>
						</select>
						</td>
						
						<td>所属城市</td><td>
						<select name="lvStore.cityCode" id="selCity" >
						<option value="">==请选择==</option>
						<c:foreach items="${productTypList}" var="type">
						<option value="${type.code }" <c:if test="${lvProduct.shopProductType==type.code }">selected="selected"</c:if>>${type.typeName }</option>
						</c:foreach>
						</select>
						</td>
						<td>店铺名称</td><td><input name="lvStore.name" type="text" size="20" value="${lvStore.name}"/></td>
						
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<form method="post" action="lvOrderAction!befChangeProduct.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="pageFormContent" style="padding: 0px">
			<table class="table" width="100%" layoutH="180">
				<thead>
					<tr>
						<th width="5%"><input type="hidden" group="ids" class="checkboxCtrl"></th>
						<th width="5%">ID</th>
						<th width="10%">店铺名称</th>
						<th width="10%">城市</th>
						<th width="60%">国家</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="item">
						<tr>
							<td><input name="ids" value="${item.storeFlag}" type="radio" onclick="subChange('${item.storeFlag}')"></td>
							<td>${item.id}</td>
							<td>${item.name}</td>
							<td>${item.city}</td>
							<td>${item.country }</td>
						</tr>
					</c:foreach>
				</tbody>
			</table>

			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
		</div>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent">
					<a id="nextHref" href="lvOrderAction!befChangeProduct.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&json.navTabId=${json.navTabId}"   
					  target="dialog" navTabId="lvOrder" rel="lvOrder"  width="800" height="600" mask="true" title="匹配目标店铺产品">
					<button type="submit">下一步</button>
					</a>
					</div></div>
					
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toStoreArea.action",
		  data: "countryId="+${lvStore.countryId},
		  dataType:"json",
		  success: function(data){
		  if(data!=null){
		  var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var provinceName = listTmp[k].provinceName; 
					var provinceCode = listTmp[k].provinceCode; 
					$("#selCity").append("<option value="+provinceCode+">"+provinceName+"</option>");
				 }
		  } 
		  }
	});
})


function subChange(targetFlag){
   var tmp="lvOrderAction!befChangeProduct.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&json.navTabId=${json.navTabId}&targetFlag="+targetFlag;
   $("#nextHref").attr("href",tmp);
}
</script>

