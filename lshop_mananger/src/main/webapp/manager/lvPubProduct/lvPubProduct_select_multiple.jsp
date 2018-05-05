<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function reutrnDate(){
	var $table=$("#tab tr");
    var len=$table.length;
    //解决因为删除引起的id重复问题
	$("#tab tr").each(function(){
	  if(parseInt($.trim($(this).attr("id")))>=len){
	    len=parseInt($.trim($(this).attr("id")))+1;
	  }
	});
	$('[name="ids"]:checked').each(function () {
         var data = eval("("+$(this).val()+")") ;	 
		 var str='<tr id="'+len+'"><td width="5%">'+len+'</td>'
	     +'<td width="25%">'+data.productName+'<input type="hidden" name="lvPubPackage.detailsList['+len+'].pubProductCode"  value="'+data.productCode+'"/></td>'
	     +'<td width="10%">'+data.productModel+'</td>'
		 +'<td width="30%">'+data.pcode+'</td>'
		 +'<td width="20%"><input type="text" name="lvPubPackage.detailsList['+len+'].productNum" value="1" size="10" maxlength="10" class="required digits" min="1"></td>'
		 +'<td width="10%"><a style="color:blue" class="a_flag" href="javascript:void(0)" onclick="delDetails('+len+')">删除</a></td></tr>';   
          len++;
      	$('#tab').append(str);   
	});	
	$.bringBack();	
}
</script>
<form id="pagerForm" method="post" action="lvPubProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return dialogSearch(this);" action="lvPubProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>	
						<td><label>商品名称</label></td><td><input name="lvPubProduct.productName" type="text" size="20" value="${lvPubProduct.productName}" /></td>
						<td><label>商品型号</label></td><td><input name="lvPubProduct.productModel" type="text" size="20" value="${lvPubProduct.productModel }"/></td>
						<td><label>商务对接code</label></td><td><input name="lvPubProduct.pcode" type="text" size="20" value="${lvPubProduct.pcode}"/></td>
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
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">ID</th>
							<th width="45%">商品名称</th>
							<th width="15%">商品型号</th>
							<th width="35%">商务对接code</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="{id:'${item.id}',productCode:'${item.code}',productName:'${item.productName}',productModel:'${item.productModel}', pcode:'${item.pcode}'}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id}</td>
								<td>${item.productName}</td>
								<td>${item.productModel}</td>
								<td>${item.pcode }</td>
								
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
   
			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
		<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="input" onclick="reutrnDate();">添加</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
		</div>
	</div>
</div>