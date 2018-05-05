<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
function reutrnDate(code){
	var $table=$("#tabDetails tr");
    var len=$table.length;
    //解决因为删除引起的id重复问题
	$("#tabDetails tr").each(function(){
	  if(parseInt($.trim($(this).attr("id")))>=len){
	    len=parseInt($.trim($(this).attr("id")))+1;
	  }
	});
	
    $.ajax({
		  type: "POST",
		  url: "lvPubPackageAction!toPackageDetails.action",
		  data: "lvPubPackage.code="+code,
		  dataType:"json",
		  success: function(data){
			 $("#tabDetails tr").remove();
		     if(data!=null){
		        var listTmp=data.listTmp; 
		        $.bringBack({packageName:data.pubPackage.packageName,code:data.pubPackage.code});	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productModel = listTmp[k].productModel; 
                    var str='<tr id="'+len+'"><td width="5%">'+len+'<input type="hidden" name="lvPubPackage.detailsList['+len+'].code"  value="'+listTmp[k].code+'"/></td>'
           	     +'<td width="25%">'+listTmp[k].productName+'<input type="hidden" name="lvPubPackage.detailsList['+len+'].pubProductCode"  value="'+listTmp[k].pubProductCode+'"/></td>'
           	     +'<td width="20%">'+listTmp[k].productModel+'</td>'
           		 +'<td width="30%">'+listTmp[k].pcode+'</td>'
           		 +'<td width="20%">'+listTmp[k].productNum+'<input type="hidden" name="lvPubPackage.detailsList['+len+'].productNum" value="'+listTmp[k].productNum+'"/></td>';   
                     len++;
                 	$('#tabDetails').append(str);
                    
				 }
		     }
		  }
	});	
	
}
</script>

<form id="pagerForm" method="post" action="lvPubPackageAction!selectSinglePackage.action?lvPubPackage.pubPackageCode=${lvPubPackage.pubPackageCode }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvPubPackageAction!selectSinglePackage.action?lvPubPackage.pubPackageCode=${lvPubPackage.pubPackageCode }&json.navTabId=${json.navTabId}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>套餐名称</label></td><td><input name="lvPubPackage.packageName" type="text" size="20" value="${lvPubPackage.packageName}"/></td>
						</tr><tr> 
				</tr>
			</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="155" targetType="dialog" width="100%">
		<thead>
			<tr>
				   <!-- 生成表单 -->
				   <th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">ID</th>
				   <th width="45%">套餐名称</th>
				   <th width="45%">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="page.list" status="stat" id="item">
			<tr>
					<!-- 生成表单 -->
					<td>${item.id}</td>
					<td>${item.packageName}</td>
				<td>
				<a class="btnSelect" href="javascript:reutrnDate('${item.code }')" title="查找带回">选择</a>
				<a href="lvPubPackageAction!view.action?lvPubPackage.id=${item.id}" target="dialog" navTabId="lvPubPackage_1" rel="lvPubPackage_1" title="查看" width="850" height="600" mask="true">详情</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>

			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
</div>
