<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function reutrnDate(){
	var $table=$("#tabGiftDetails tr");
    var len=$table.length;
    //解决因为删除引起的id重复问题
	$("#tabGiftDetails tr").each(function(){
	  if(parseInt($.trim($(this).attr("id")))>=len){
	    len=parseInt($.trim($(this).attr("id")))+1;
	  }
	});
    
	$('[name="ids"]:checked').each(function () {
		//验证选择的商品是否重复，如果重复则不添加
		var data = eval("("+$(this).val()+")") ;	 
		var giftCode=data.giftCode;
		var flag = true;
		$("#tabGiftDetails tr").each(function(){
			   if($.trim($(this).find("td:eq(1) input:eq(0)").val())==giftCode){
			      flag=false;
			   }
	    });
		//数据组装
		if(flag){
			 var str='<tr id="zp'+len+'"><td width="5%">'+len+'</td>'
		     +'<td width="35%">'+data.giftName+'<input type="hidden" name="lvActivityAppointProduct.acGiftList['+len+'].giftCode"  value="'+data.giftCode+'"/><input type="hidden" name="lvActivityAppointProduct.acGiftList['+len+'].giftName"  value="'+data.giftName+'"/></td>'
		     +'<td width="20%"><input type="text" name="lvActivityAppointProduct.acGiftList['+len+'].giftEveryNum"  class="required digits" maxlength="5" value="1"/></td>'
			 +'<td width="15%"><input type="text" name="lvActivityAppointProduct.acGiftList['+len+'].giftTotalNum"  class="required digits" maxlength="5" value="0"/></td>'
			 +'<td width="15%"><input type="text" name="lvActivityAppointProduct.acGiftList['+len+'].orderValue"  class="digits" maxlength="5" value="0"/></td>'
			 +'<td width="10%"><a style="color:blue" class="a_flag" href="javascript:void(0)" onclick="delGiftDetails('+len+')">删除</a></td></tr>';   
	          len++;
	      	$('#tabGiftDetails').append(str);   
		}
	});	
	$.bringBack();	
}
</script>

<form id="pagerForm" method="post" action="lvPubGiftAction!selectMultipleGift.action?lvPubGift.status=${lvPubGift.status }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return dialogSearch(this);" action="lvPubGiftAction!selectMultipleGift.action?lvPubGift.status=${lvPubGift.status }&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>	
						<td><label>赠品中文名称</label></td><td><input name="lvPubGift.giftName" type="text" size="20" value="${lvPubGift.giftName}"/></td>
						<td><label>赠品英文名称</label></td><td><input name="lvPubGift.giftNameEn" type="text" size="20" value="${lvPubGift.giftNameEn}"/></td>
						</tr><tr>	
						<td><label>SAS对接code</label></td><td><input name="lvPubGift.pcode" type="text" size="20" value="${lvPubGift.pcode}"/></td>
						
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
		                    <th width="35%">赠品中文名称</th>
					        <th width="25%">赠品英文名称</th>
					        <th width="25%">SAS对接code</th>
					        <th width="10%">创建时间</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="{id:'${item.id}',giftCode:'${item.code}',giftName:'${item.giftName}'}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id}</td>
								<td>${item.giftName }</td>
								<td>${item.giftNameEn }</td>
								<td>${item.pcode }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
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