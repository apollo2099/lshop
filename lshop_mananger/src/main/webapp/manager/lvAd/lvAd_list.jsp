<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
//判断选择是商家还是商城，对应的显示广告列表
function changeStore_t(){
    var storeId=$("#storeId_t").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvAdAction!toStore.action",
		  data: "lvAd.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
                if(data.parentCode=='0'){
                    $("#ad4").hide();
	                $("#ad5").hide();
	                $("#ad6").show();
	                $("#keyid4").attr("disabled",true);
	                $("#keyid5").attr("disabled",true);
	                $("#keyid6").attr("disabled",false);
                }else{
                	$("#ad4").hide();
	                $("#ad5").show();
	                $("#ad6").hide();
	                $("#keyid4").attr("disabled",true);
	                $("#keyid5").attr("disabled",false);
	                $("#keyid6").attr("disabled",true);
                }
		     }else{
                $("#ad4").show();
	            $("#ad5").hide();
	            $("#ad6").hide();
	            $("#keyid4").attr("disabled",false);
	            $("#keyid5").attr("disabled",true);
	            $("#keyid6").attr("disabled",true);
		     }
		  }
	});
}
</script>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvAdAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvAdAction!list.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>所属关系：</label>
			    <select name="lvAd.storeId" id="storeId_t" onchange="changeStore_t()">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }" <c:if test="${lvAd.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
				</select>
			</li>
			<li>
				<label>广告标题：</label>
				<!--  
				    <select name="lvAd.adKey"  >
					     <option value="">==请选择==</option>
					        <s:iterator value="@com.lshop.common.util.Constants@AD_LOCATIONS" status="stat" id="item">
				        <option value="${item.key}" <c:if test="${lvAd.adKey==item.key }">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
				         <s:iterator value="@com.lshop.common.util.Constants@AD_TVPAD" status="stat" id="item">
				             <option value="${item.key}" <c:if test="${lvAd.adKey==item.key }">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
					    </select>
					    -->
					      <div id="ad4">
					    <select name="lvAd.adKey" id="keyid4" >
					          <option value="">==请选择==</option>
					    </select>
				    </div >
				    <div id="ad5" style="display:none">
				        <select name="lvAd.adKey" id="keyid5" >
				          <option value="">==请选择==</option>
				         <s:iterator value="@com.lshop.common.util.Constants@AD_LOCATIONS" status="stat" id="item">
				             <option value="${item.key}" <c:if test="${lvAd.adKey==item.key }">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
				        </select>
				    </div>
				    <div id="ad6" style="display:none">
				        <select name="lvAd.adKey" id="keyid6"  >
				          <option value="">==请选择==</option>
				         <s:iterator value="@com.lshop.common.util.Constants@AD_TVPAD" status="stat" id="item">
				             <option value="${item.key}" <c:if test="${lvAd.adKey==item.key }">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
				        </select>
				    </div>
			</li>
			<li>
				<label>状态：</label>
				<select name="lvAd.status">
				          <option value="">==请选择==</option>
				          <option value="1" <c:if test="${lvAd.status==1 }">selected="selected"</c:if>>启用</option>
				          <option value="-1" <c:if test="${lvAd.status==-1 }">selected="selected"</c:if>>停用</option>
				</select>
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
		    <li><a class="add" href="lvAdAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600" title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvAdAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">所属关系</th>
				<th width="25%">广告标题</th>
				<th width="10%">广告描述</th>
				<th width="10%">投放时间</th>
				<th width="10%">状态</th>
				<th width="10%">创建时间</th>
				<th width="10%">修改时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				<c:foreach items="${storeList}" var="store">
						<c:if test="${item.storeId==store.storeFlag }">${store.name }</c:if>
				</c:foreach>
				</td>
				<td>${item.adTitle }</td>
				<td>${item.adDescription }</td>
				<td><s:date name="pickleTime" format="yyyy-MM-dd HH:mm"/></td>
				<td>
				<c:if test="${item.status==1 }">启用</c:if>
				<c:if test="${item.status==-1 }">停用</c:if>
				</td>
				<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvAdAction!view.action?lvAd.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="1020" height="700" mask="true">查看</a>
					<a class="btnEdit" href="lvAdAction!befEdit.action?lvAd.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="900" height="600" mask="true">编辑</a>
					<a class="btnDel"  href="lvAdAction!del.action?lvAd.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
<script>
$(document).ready(function(){
changeStore_t();
});
</script>
