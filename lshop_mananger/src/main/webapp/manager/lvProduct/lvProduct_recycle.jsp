<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
//根据专卖店查询专卖店分类下面的店铺信息
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
	var storeIdVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvProductAction!toProductType.action",
		  data: "lvProduct.storeId="+storeIdVal,
		  dataType:"json",
		  success: function(data){
			 $(".provinceDate").remove();
			 $("#ptype option").remove();
			 $("#ptype").append("<option value=''>==请选择==</option>");
		     if(data!=null){
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var typeCode = listTmp[k].typeCode; 
					var typeName = listTmp[k].typeName; 
					$("#ptype").append("<input type='hidden' name='lvProduct.typeList["+(k)+"].typeName' class='provinceDate' id=num"+(k)+" value="+typeName+">");
					$("#ptype").append("<input type='hidden' name='lvProduct.typeList["+(k)+"].code' class='provinceDate' id=num"+(k)+" value="+typeCode+">");
					$("#ptype").append("<option value="+typeCode+">"+typeName+"</option>");
				 }
		     }
		  }
	});
}
</script>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!recycle.action?lvProduct.isSupport=${lvProduct.isSupport }" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductAction!recycle.action?lvProduct.isSupport=${lvProduct.isSupport }" method="post">
		<div class="searchBar">
		    <table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvProduct.storeId" style="width:130px;" id="storeId" onchange="changeStore()">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvProduct.storeId}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
						<td><label>商品名称</label><input name="lvProduct.productName" type="text" size="20" value="${lvProduct.productName}"/></td>
						<td><label>商城商品分类</label>
						<select name="lvProduct.shopProductType" style="width:130px;">
						<option value="">==请选择==</option>
						<c:foreach items="${shopProductTypeList}" var="spt">
						<option  value="${spt.code }" <c:if test="${lvProduct.shopProductType==spt.code}">selected="selected"</c:if>>${spt.typeName}</option>
					    </c:foreach>
					    </select>
						</td>
						<td><label>商品类别</label>
						<select name="lvProduct.ptype" style="width:130px;" id="ptype">
						<option value="">==请选择==</option>
						<c:foreach items="${lvProduct.typeList}" var="lp" varStatus="index">
						    <option value="${lp.code}" <c:if test="${lp.code ==lvProduct.ptype }">selected="selected"</c:if>>${lp.typeName}</option>
						</c:foreach>
					    </select>
					    <c:foreach items="${lvProduct.typeList}" var="lp" varStatus="index">
							  <input type="hidden" name="lvProduct.typeList[${index.index }].code" class="provinceDate" value="${lp.code}">
							  <input type="hidden" name="lvProduct.typeList[${index.index }].typeName" class="provinceDate"  value="${lp.typeName}">	
						</c:foreach>
						</td>
				</tr><tr>
				</tr><tr>
						<td><label>是否套餐</label></td>
						<td>
						<select name="lvProduct.isSetMeal" style="width:130px;">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvProduct.isSetMeal==1}">selected="selected"</c:if>>是</option>
						<option value="0" <c:if test="${lvProduct.isSetMeal==0}">selected="selected"</c:if>>否</option>
					    </select>
						</td>
						<!-- 
						<td><label>商务对接code</label><input name="lvProduct.pcode" type="text" size="20" value="${lvProduct.pcode}"/></td>
						<td><label>商品型号</label><input name="lvProduct.pmodel" type="text" size="20" value="${lvProduct.pmodel}"/></td>
						 -->
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
			<li><a class="delete"  href="lvProductAction!del.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="180">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="5%">所属关系</th>
				<th width="27%">商品名称</th>
				<!-- 
				<th width="15%">商务对接code</th>
				<th width="5%">商品型号</th>
				 -->
				<th width="5%">价格</th>
				<th width="5%">排序值</th>
				<th width="4%">是否套餐</th>
				<th width="10%" orderField="createTime" class="${orderDirection}">创建时间</th>
			    <th width="5%">商品图片</th>
				<th width="39%">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="page.list"  id="product">
			<tr>
				<td><input name="ids" value="${product.id}" type="checkbox"></td>
				<td>${product.id }</td>
				<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==product.storeId}">${store.name}</c:if>
					</c:foreach>
				</td>
				<td>${product.productName }</td>
				<!-- 
				<td>${product.pcode }</td>
				<td>${product.pmodel}</td>
				 -->
				<td>${product.price }</td>
				<td>${product.orderId }</td>
				<td>
				<s:if test="#product.isSetMeal==1">是</s:if>
				<s:if test="#product.isSetMeal==0">否</s:if>
				</td>
				<td><s:date name="createTime " format="yyyy-MM-dd HH:mm"/></td>
				<td>
					<c:if test="${product.pimage!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${product.pimage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${product.pimage}" width="20px" height="20px"/></a>
					</c:if>
					
				</td>
				<td>
				<sec:authorize url="userEdit">
				   	<a class="btnView" href="lvProductAction!unSupportDetails.action?lvProduct.id=${product.id}&json.navTabId=${json.navTabId }"  target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看产品详情" width="900" height="700" mask="true">查看</a>
				   	<a class="btnEdit" href="lvProductAction!befEdit.action?lvProduct.id=${product.id}&lvProduct.storeId=${product.storeId }&json.navTabId=${json.navTabId }"  title="编辑" target="navTab"  navTabId="lvProduct" rel="lvProduct"  mask="true">编辑</a>
				   	<a class="btnDel"  href="lvProductAction!del.action?ids=${product.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>	  
					<a href="lvProductAction!getProductProperty.action?lvProduct.code=${product.code}&lvProduct.storeId=${product.storeId }&json.navTabId=lvShopSubject_1"  rel="lvShopSubject_1" target="navTab">扩展页</a>
					<a href="lvProductAction!getProductImage.action?lvProduct.code=${product.code}&lvProduct.storeId=${product.storeId }&json.navTabId=lvShopSubject_2"  rel="lvShopSubject_2" target="navTab">效果图</a>
					<a href="lvProductAction!getProductImage.action?lvProduct.code=${product.code}&lvProduct.storeId=${product.storeId }&json.navTabId=lvShopSubject_3"  rel="lvShopSubject_3" target="navTab">阶梯价</a>
					<c:if test="${product.isSupport==0}">
					   <a href="lvProductAction!updateSupport.action?lvProduct.code=${product.code}&lvProduct.isSupport=1&json.navTabId=${json.navTabId }"  title="确实将改产品上架吗?" target="ajaxTodo">上架</a>
					</c:if>
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
