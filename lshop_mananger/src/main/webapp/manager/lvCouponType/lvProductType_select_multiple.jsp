<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
function reutrnDate(){
	    var $table = $("#tab tr");
		var len = $table.length;



		//解决因为删除引起的id重复问题
		$("#tab tr").each(function() {
			if (parseInt($.trim($(this).attr("id"))) >= len) {
				len = parseInt($.trim($(this).attr("id"))) + 1;
			}
		});
		$('[name="orgId"]:checked').each(
						function() {
							var data = eval("(" + $(this).val() + ")");
							
							
							//判断添加的产品类型是否存在
							var flag = true;
							$("#tab tr").each(function() {
								if ($.trim($(this).find("td:eq(0) input").val()) == data.typeCode) {
									alertMsg.error("选择项存在重复项，须删除后才可重新添加！");
									flag = false;
									//return false;
								}
							});
							
							
							//添加产品类型拼接
							if(flag){
								var str = '<tr id="'+len+'">'
								+ '<td width="40%">'
								+ data.name
								+ '<input type="hidden" name="lvCouponType.couponProductlist['+len+'].relationCode"  value="'+data.typeCode+'"/></td>'
								+ '<td width="45%">'
								+ data.typeName
								+ '<input type="hidden" name="lvCouponType.couponProductlist['+len+'].storeId"  value="'+data.storeId+'"/></td>'
								+ '<td width="15%"><a style="color:blue" class="a_flag" href="javascript:void(0)" onclick="deltr('
								+ len + ')">删除</a></td></tr>';
						        len++;
						        $('#tab').append(str);
							}
						});
		$.bringBack();
	}
</script>

<form id="pagerForm" method="post" action="lvCouponTypeAction!selectMultipleProductType.action?currency=${currency }" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvCouponTypeAction!selectMultipleProductType.action?currency=${currency }" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>所属关系:</label>
				<select name="lvProductType.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList }" var="store">
						<option value="${store.storeFlag }" <c:if test="${store.storeFlag==lvProductType.storeId }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
				</select>
			</li>
			<li>
				<label>商品类型:</label>
				<input class="textInput" name="lvProductType.typeName" value="${lvProductType.typeName}" type="text">
			</li>	  
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="orgId" warn="请选择商品类型" onclick="reutrnDate()">选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="5%"><input type="checkbox" class="checkboxCtrl" group="orgId" /></th>
				<th width="40%" orderfield="orgName">所属关系</th>
				<th width="45%"orderfield="orgNum">商品类型</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input type="checkbox" name="orgId" value="{id:'${item.id }',name:'${item.name }', typeName:'${item.typeName }', typeCode:'${item.code }',storeId:'${item.storeId }'}"/></td>
				<td>${item.name }</td>
				<td>${item.typeName }</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>

			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
</div>