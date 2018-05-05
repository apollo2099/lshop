<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function reutrnDate(){
	var $table=$("#tabDetails tr");
    var len=$table.length;
    //解决因为删除引起的id重复问题
	$("#tabDetails tr").each(function(){
	  if(parseInt($.trim($(this).attr("id")))>=len){
	    len=parseInt($.trim($(this).attr("id")))+1;
	  }
	});
	$('[name="ids"]:checked').each(function () {
		
		//验证选择的商品是否重复，如果重复则不添加
		var data = eval("("+$(this).val()+")") ;	 
		var productCode=data.productCode;
		var flag = true;
		$("#tabDetails tr").each(function(){
			   if($.trim($(this).find("td:eq(2) input:eq(0)").val())==productCode){
			      flag=false;
			   }
	    });
		
		if(flag){
			 var str='<tr id="sp'+len+'"><td width="5%">'+len+'</td>'
		     +'<td width="20%">'+data.storeName+'<input type="hidden" name="lvActivityAppointProduct.acProcuctList['+len+'].storeId"  value="'+data.storeId+'"/></td>'
		     +'<td width="45%">'+data.productName+'<input type="hidden" name="lvActivityAppointProduct.acProcuctList['+len+'].productCode"  value="'+data.productCode+'"/><input type="hidden" name="lvActivityAppointProduct.acProcuctList['+len+'].productName"  value="'+data.productName+'"/></td>'
			 +'<td width="25%">'+data.currency+data.price+'<input type="hidden" name="lvActivityAppointProduct.acProcuctList['+len+'].price"  value="'+data.price+'"/><input type="hidden" name="lvActivityAppointProduct.acProcuctList['+len+'].currency"  value="'+data.currency+'"/></td>'
			 +'<td width="10%"><a style="color:blue" class="a_flag" href="javascript:void(0)" onclick="delDetails('+len+')">删除</a></td></tr>';   
	          len++;
	      	$('#tabDetails').append(str);   
		}
	});	
	$.bringBack();	
}
</script>
<form id="pagerForm" method="post" action="lvProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return dialogSearch(this);" action="lvProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>	
						<td><label>所属关系</label></td><td>
						<select name="lvProduct.storeId" style="width:130px;" id="storeId" onchange="changeStore()">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvProduct.storeId}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
						<td><label>商品名称</label></td><td><input name="lvProduct.productName" type="text" size="20" value="${lvProduct.productName}" /></td>
						
				</tr>
			</table>
			<div class="subBar">
				<ul>
				    <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="reutrnDate();">选择带回</button></div></div></li>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layoutH="140">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">ID</th>
							<th width="15%">所属关系</th>
							<th width="40%">商品名称</th>
							<th width="20%">商品价格</th>
							<th width="20%">创建时间</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="{id:'${item.id}',productCode:'${item.code}',productName:'${item.productName}',price:'${item.price}',storeId:'${item.storeId}',storeName:'${item.storeName}',currency:'${item.currency}',createTime:'${item.createTime}'}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id}</td>
								<td>${item.storeName}</td>
								<td>${item.productName}</td>
								<td>${item.currency}&nbsp;${item.price}</td>
								<td>${item.createTime}</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
   
			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
	</div>
</div>