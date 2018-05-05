<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
//根据专卖店查询专卖店分类下面的店铺信息
function changeCountry(){
	var countryId=$("#countryId").find("option:selected")
	var countryVal = countryId.val();
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toProvince.action",
		  data: "countryId="+countryVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#txtProvince").hide();
		        $("#selProvince").show();
		        $(".provinceDate").remove();
		        $("#selProvince option").remove();
		        $("#selProvince").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var provinceName = listTmp[k].provinceName; 
					var provinceId = listTmp[k].provinceId; 
					$("#selProvince").append("<input type='hidden' name='lvAccountAddress.addressList["+(k)+"].provinceName' class='provinceDate' id=num"+(k)+" value="+provinceName+">");
					$("#selProvince").append("<input type='hidden' name='lvAccountAddress.addressList["+(k)+"].provinceId' class='provinceDate' id=num"+(k)+" value="+provinceId+">");
					$("#selProvince").append("<option value="+provinceId+">"+provinceName+"</option>");
				 }
		     }else{
		        $(".provinceDate").remove();
		        $("#selProvince option").remove();
		        $("#selProvince").append("<option value=''>==请选择==</option>");
		        $("#txtProvince").show();
		        $("#selProvince").hide();
		     }
		  }
	});
}
</script>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvAccountAddressAction!list.action?lvAccountAddress.relCode=${lvAccountAddress.relCode}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvAccountAddressAction!list.action?lvAccountAddress.relCode=${lvAccountAddress.relCode }" method="post">
		<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>联系人</label><input  name="lvAccountAddress.relName" type="text" size="20" value="${lvAccountAddress.relName}"/></td>
						<td><label>国家</label>
						<select name="lvAccountAddress.contryId" id="countryId" onchange="changeCountry()">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${areaList}" var="area">
						   <option value="${area.id }" <c:if test="${lvAccountAddress.contryId==area.id }">selected="selected"</c:if>>${area.namecn }</option>
						   </c:foreach>
						 </select>
						</td>
						<td><label>州/省</label>
						 <input type="text" name="lvAccountAddress.provinceName"  value="${lvAccountAddress.provinceName }" id="txtProvince" />
				            <select name="lvAccountAddress.provinceId" id="selProvince" class="required" style="display:none" >
							<option value="">==请选择==</option>
							 <c:foreach items="${lvAccountAddress.addressList}" var="lp" varStatus="index">
							    <option value="${lp.provinceId}" <c:if test="${lp.provinceId ==lvAccountAddress.provinceId }">selected="selected"</c:if>>${lp.provinceName}</option>
							 </c:foreach>
							</select>
							<c:foreach items="${lvAccountAddress.addressList}" var="lp" varStatus="index">
							  <input type="hidden" name="lvAccountAddress.addressList[${index.index }].provinceId" class="provinceDate" value="${lp.provinceId}">
							  <input type="hidden" name="lvAccountAddress.addressList[${index.index }].provinceName" class="provinceDate"  value="${lp.provinceName}">	
							</c:foreach>
						
						</td>
						<td><label>市</label><input  name="lvAccountAddress.cityName" type="text" size="20" value="${lvAccountAddress.cityName}"/></td>
						
				</tr><tr>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="lvAccountAddressAction!delList.action?json.navTabId=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="148">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%" >联系人</th>
				<th width="10%" >电话</th>
				<th width="10%" >手机</th>
				<th width="10%" >邮编</th>
				<th width="40%" >地址</th>
				<th width="10%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="address">
			<tr>
				<td><input name="ids" value="${address.id}" type="checkbox"></td>
				<td>${address.id }</td>
				<td>${address.relName}</td>
				<td>${address.tel}</td>
				<td>${address.mobile}</td>
				<td>${address.postCode }</td>
				<td>
				${address.contryName}
				${address.provinceName}
				${address.cityName}
				${address.adress}
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvAccountAddressAction!view.action?lvAccountAddress.id=${address.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="800" height="500" mask="true">查看</a>
					<a class="btnEdit" href="lvAccountAddressAction!befEdit.action?lvAccountAddress.id=${address.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog" width="800" height="500" mask="true">编辑</a>
					<a class="btnDel"  href="lvAccountAddressAction!del.action?lvAccountAddress.id=${address.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>

<script type="text/javascript">
$(document).ready(function(){
　 if(${empty lvAccountAddress.addressList}){
    $("#txtProvince").show();
    $("#selProvince").hide();
  }else{
    $("#txtProvince").hide();
    $("#selProvince").show();
  }
});
</script>
