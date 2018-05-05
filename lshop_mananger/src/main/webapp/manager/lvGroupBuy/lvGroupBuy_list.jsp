<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script>
//根据店铺编号查询对应店铺的产品信息
function changeStore(){
	var storeId=$("#storeIdList").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvProductAction!toAllProduct.action",
		  data: "lvProduct.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     //删除添加列表中的产品信息
		     $(".productData").remove();
		     if(data!=null){
		        $("#productList option").remove();
		        $("#productList").append("<option value=''>==请选择产品==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productCode = listTmp[k].productCode; 
					$("#productList").append("<input type='hidden' name='lvGroupBuy.productList["+(k)+"].productName' class='productData'  value='"+productName+"'>");
					$("#productList").append("<input type='hidden' name='lvGroupBuy.productList["+(k)+"].code' class='productData'  value='"+productCode+"'>");
					$("#productList").append("<option value="+productCode+">"+productName+"</option>");
				 }
		     }else{
		        $("#productList option").remove();
		        $("#productList").append("<option value=''>==请选择产品==</option>");

		     }
		  }
	});
}
</script>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvGroupBuyAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvGroupBuyAction!list.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
		    <li>
				<label>所属关系：</label>
				<select name="lvGroupBuy.storeId" class="required" id="storeIdList" onchange="changeStore()" style="width:200px;">
					<option value="">==请选择==</option>
					<c:foreach items="${storeList}" var="store">
					<option  value="${store.storeFlag }" <c:if test="${lvGroupBuy.storeId ==store.storeFlag}">selected="selected"</c:if>>${store.name}</option>
					</c:foreach>
				</select>
			</li>
			<li>
				<label>团购产品：</label>
				<select name="lvGroupBuy.productCode" style="width: 200px;"
					id="productList">
						<option value="">==请选择产品==</option>
						<c:foreach items="${lvGroupBuy.productList}" var="lp" varStatus="index">
							<option value="${lp.code}" <c:if test="${lp.code ==lvGroupBuy.productCode}">selected="selected"</c:if>>${lp.productName}</option>
						</c:foreach>
				</select> 
				<c:foreach items="${lvGroupBuy.productList}"
						var="lp" varStatus="index">
						<input type="hidden" name="lvGroupBuy.productList[${index.index }].code" class="productData" value="${lp.code}">
						<input type="hidden" name="lvGroupBuy.productList[${index.index }].productName" class="productData" value="${lp.productName}">
				</c:foreach>
				</li>
			<li>
				<label>团购标题：</label>
				<input type="text" name="lvGroupBuy.title" value="${lvGroupBuy.title}"/>
			</li>
		</ul>
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
		    <li><a class="add" href="lvGroupBuyAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600" title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvGroupBuyAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="25%">团购标题</th>
				<th width="15%">团购产品</th>
				<th width="10%">活动开始时间</th>
				<th width="10%">活动结束时间</th>
				<th width="5%">激活标志</th>
				<th width="10%">所属关系</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>${item.title }</td>
				<td>
				
				<c:foreach items="${productList}" var="product">
				    <c:if test="${product.code==item.productCode}">${product.productName }</c:if>
				</c:foreach>
				</td>
				<td><s:date name="startTime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:date name="endTime" format="yyyy-MM-dd HH:mm"/></td>
				<td>
				     <c:if test="${item.status==1}">启用</c:if>
                     <c:if test="${item.status==-1}">停用</c:if>
				</td>
				<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvGroupBuyAction!view.action?lvGroupBuy.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="900" height="600" mask="true">查看</a>
					<a class="btnEdit" href="lvGroupBuyAction!befEdit.action?lvGroupBuy.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="900" height="600" mask="true">编辑</a>
					<a class="btnDel"  href="lvGroupBuyAction!del.action?lvGroupBuy.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
					<a href="lvGroupPropertyAction!list.action?lvGroupProperty.groupCode=${item.code }&json.navTabId=lvGroupProperty_1"  rel="lvGroupProperty_1" target="navTab">扩展属性</a>
				</sec:authorize>	
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
