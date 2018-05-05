<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
//根据专卖店查询专卖店分类下面的店铺信息
function changeCountry(){
	var countryId=$("#countryId").find("option:selected")
	var countryVal = countryId.val();
    $.ajax({
		  type: "POST",
		  url: "lvStoreMngAction!toCity.action",
		  data: "countryId="+countryVal,
		  dataType:"json",
		  success: function(data){
		     $("#selProvince option").remove();
		     $("#selProvince").append("<option value=''>==请选择==</option>");
		     if(data!=null){
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var city = listTmp[k].city; 
					$("#selProvince").append("<input type='hidden' name='lvStore.cityList["+(k)+"].areaName' class='provinceDate' id=num"+(k)+" value="+city+">");
					$("#selProvince").append("<option value="+city+">"+city+"</option>");
				 }
		     }
		  }
	});
}
</script>



<form id="pagerForm" method="post" action="lvStoreMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvStoreMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="550">
				<tr>
						</tr><tr>
						<!-- 
						<td><label>国家名称：</label></td><td>
						<select name="lvStore.countryId" onchange="changeCountry()" id="countryId">
							<option value="">==请选择==</option>
							<c:foreach items="${storeAreaList}" var="area">
							<option value="${area.areaId }" <c:if test="${area.areaId==lvStore.countryId}">selected="selected"</c:if>>${area.areaName }</option>
							</c:foreach>
						</select></td>
						<td><label>城市名称：</label></td><td>
						<select name="lvStore.city"  id="selProvince">
						<option value="">==请选择==</option>
						 <c:foreach items="${lvStore.cityList}" var="lp" varStatus="index">
							    <option value="${lp.areaName}" <c:if test="${lp.areaName == lvStore.city }">selected="selected"</c:if>>${lp.areaName}</option>
							 </c:foreach>
							</select>
							<c:foreach items="${lvStore.cityList}" var="lp" varStatus="index">
							  <input type="hidden" name="lvStore.cityList[${index.index }].areaName" class="provinceDate"  value="${lp.areaName}">	
							</c:foreach>
						</td>
						 -->
						<td><label>城市名称：</label></td><td><input name="lvStore.city" type="text" size="20" maxlength="10" value="${lvStore.city}"/></td>
						<td><label>店铺名称：</label></td><td>
						<input name="lvStore.name" type="text" size="20" maxlength="10" value="${lvStore.name}"/>
						</td>
						<td><label>店铺状态：</label></td><td>
						<s:select list="#{'':'==请选择==','1':'启用','0':'停用'}" name="lvStore.status"></s:select>
						</td>
						</tr><tr>
						<td><label>是否热门店铺：</label></td><td>
						<s:select list="#{'':'==请选择==','1':'是','0':'否'}" name="lvStore.isHot"></s:select>
						</td>
						<td><label>是否推荐店铺：</label></td><td>
						<s:select list="#{'':'==请选择==','1':'是','0':'否'}" name="lvStore.isCommend"></s:select>
						</td>
						<td><label>所属商城：</label></td><td>
						<select name="lvStore.parentCode">
						<option value="">==请选择==</option>
						<c:foreach items="${shopList}" var="shop">
						<option <c:if test="${lvStore.parentCode==shop.code}">selected="selected"</c:if> value="${shop.code }">${shop.name }</option>
						</c:foreach>
						</select>
						</td>
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
				<li><a class="add" href="lvStoreMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvStore" rel="lvStore" width="850" height="650" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<!-- 
				<li><a class="delete" href="lvStoreMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
				<li class="line">line</li>
				 -->
				<li><a class="edit" href="lvStoreMngAction!updateDomain.action?json.navTabId=${json.navTabId}" target="ajaxTodo" title="确实要更新店铺域名"><span>更新所有商家域名</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="lvStoreMngAction!befStoreArea.action?json.navTabId=${json.navTabId}" target="dwzDialog"  title="确实要批量修改店铺区域" width="500" height="300" mask="true" ref="ids"><span>批量修改店铺区域</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="lvStoreMngAction!befStoreHot.action?json.navTabId=${json.navTabId}" target="dwzDialog" title="确实要设置热门店铺" width="500" height="300" mask="true" ref="ids"><span>设置热门店铺</span></a></li>
				<li class="line">line</li>
				<li><a class="edit" href="lvStoreMngAction!befStoreCommend.action?json.navTabId=${json.navTabId}" target="dwzDialog" title="确实要设置推荐店铺" width="500" height="300" mask="true" ref="ids"><span>设置推荐店铺</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="4%">编号</th>
							<th width="5%">店铺编号</th>
							<th width="10%">店铺名称</th>
							<th width="10%">店铺域名</th>
							<th width="5%">店铺状态</th>
							<th width="5%">城市名称</th>
							<th width="5%">排序值</th>
							<th width="5%">热门店铺</th>
							<th width="5%">推荐店铺</th>
							<th width="5%">所属关系</th>
							<th width="10%">创建时间</th>	
							<th width="10%">修改时间</th>							
					        <th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>${item.number }</td>
								<td>${item.name }</td>
								<td>${item.domainName }</td>
								<!-- 
								<td><c:if test="${!empty item.urlFlag}"> http://${lshopDemain}/${item.urlFlag }</c:if></td>
								 -->
								<td>
								<c:if test="${item.status==1}">启用</c:if>
								<c:if test="${item.status==0}">停用</c:if>
								</td>
								<td>${item.city}</td>
								<td>${item.orderValue}</td>
								<td>
								<c:if test="${item.isHot==1}">是</c:if>
								<c:if test="${item.isHot==0}">否</c:if>
								</td>
								<td>
								<c:if test="${item.isCommend==1}">是</c:if>
								<c:if test="${item.isCommend==0}">否</c:if>
								</td>
								<td>
								   <c:foreach items="${shopList}" var="shop">
								    <c:if test="${item.parentCode == shop.code}"> ${shop.name }</c:if>
								   </c:foreach>
								</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
					    <!-- 
						<a class="btnDel" href="lvStoreMngAction!del.action?lvStore.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						 -->
						<a class="btnEdit" href="lvStoreMngAction!befEdit.action?lvStore.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvStore" rel="lvStore" title="编辑" width="850" height="650" mask="true">编辑</a>
						<a class="btnView" href="lvStoreMngAction!view.action?lvStore.id=${item.id}" target="dialog" navTabId="lvStore" rel="lvStore" title="查看" width="900" height="700">查看</a>
						<c:if test="${item.status==1}">
						<a href="lvStoreMngAction!updateStoreStatus.action?lvStore.id=${item.id}&lvStore.status=0&json.navTabId=${json.navTabId}"  title="你确定要停用该店铺吗？请谨慎操作！" target="ajaxTodo"   mask="true">停用</a>
						</c:if>
						<c:if test="${empty item.status or item.status==0}">
						<a href="lvStoreMngAction!updateStoreStatus.action?lvStore.id=${item.id}&lvStore.status=1&json.navTabId=${json.navTabId}"  title="你确定要启用该店铺吗？请谨慎操作！" target="ajaxTodo"   mask="true">启用</a>
						</c:if>
						<a href="lvStoreMngAction!befOrderValue.action?lvStore.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog"  title="编辑" width="450" height="200" mask="true">排序</a>
						<c:if test="${item.isTemplet!=1}">
						<a  href="lvStoreMngAction!initStoreData.action?lvStore.id=${item.id}&lvStore.storeFlag=${item.storeFlag}&lvStore.parentCode=${item.parentCode }&json.navTabId=${json.navTabId}"  title="你确定要初始化店铺数据吗？请谨慎操作！" target="ajaxTodo"   mask="true">初始化店铺数据</a>
						</c:if>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>