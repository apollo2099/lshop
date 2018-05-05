<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
					$("#selProvince").append("<input type='hidden' name='lvOrder.addressList["+(k)+"].provinceName' class='provinceDate' id=num"+(k)+" value='"+provinceName+"''>");
					$("#selProvince").append("<input type='hidden' name='lvOrder.addressList["+(k)+"].provinceId' class='provinceDate' id=num"+(k)+" value="+provinceId+">");
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
<jsp:param value="lvOrderAction!shopOrderList.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderAction!shopOrderList.action?json.navTabId=${json.navTabId}" method="post">
	<div class="searchBar">
	        <input type="hidden" name="lvOrder.isdelete" value="${lvOrder.isdelete }">
	        <input type="hidden" name="lvOrder.auditFlag" value="${lvOrder.auditFlag }">
	        <input type="hidden" name="lvOrder.orderFlag" value="${lvOrder.orderFlag }">
	        <input type="hidden" name="lvOrder.isFinanceAudit" value="${lvOrder.isFinanceAudit }">
	        <input type="hidden" name="lvOrder.isServiceAudit" value="${lvOrder.isServiceAudit }">
	        <c:if test="${lvOrder.flag==1 and lvOrder.statusFlag==0}">
	        <input type="hidden" name="lvOrder.flag" value="${lvOrder.flag }">
	        </c:if>
	        <input type="hidden" name="lvOrder.statusFlag" value="${lvOrder.statusFlag }">
	        <c:if test="${lvOrder.statusFlag!=1 && lvOrder.flag!=1 and lvOrder.orderFlag!=1}">
	          <input type="hidden" name="lvOrder.status" value="${lvOrder.status }">
	        </c:if>
	        <c:if test="${lvOrder.statusFlag!=1 && lvOrder.flag!=1}" >
	          <input type="hidden" name="lvOrder.payStatus" value="${lvOrder.payStatus }">
	        </c:if>
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>订单编号</label>
						<input type="text" name="lvOrder.oid" value="${lvOrder.oid}" />
						</td>
				        <td><label>用户Email</label>
						<input type="text" name="lvOrder.userEmail" value="${lvOrder.userEmail}" />
						</td>
						<td><label>下单时间</label><input type="text" name="lvOrder.startTime" value="${lvOrder.startTime}" class="date"/>
						-<input type="text" class="date" name="lvOrder.endTime" value="${lvOrder.endTime}"></td>
			            <td><label>优惠码</label><input type="text" name="couponCode"  value="${couponCode }"/></td>
	    
				</tr><tr>
				</tr><tr>
				        <td><label>商家名称</label>
				        <select name="lvOrder.storeId" >
							   <option value="">==请选择==</option>
							    <c:foreach items="${storeList}" var="store">
							   <option value="${store.storeFlag }" <c:if test="${lvOrder.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
							   </c:foreach>
							 </select>
				        </td>
				        <td><label>收货人姓名</label>
						<input type="text" name="relName"  value="${relName }"/>
						</td>
						<td><label>支付时间</label><input type="text" name="lvOrder.startOverTime" value="${lvOrder.startOverTime}" class="date"/>
						-<input type="text" class="date" name="lvOrder.endOverTime" value="${lvOrder.endOverTime}"></td>
				</tr><tr>
				</tr><tr>
					  <td><label>订单类型</label>
							 <select name="orderType" 
							 <c:if test="${lvOrder.flag==1 and lvOrder.statusFlag==0}">disabled="disabled"</c:if>
							 >
							   <option value="">==请选择==</option>
							   <option value="0" <c:if test="${lvOrder.flag==0 or orderType==0 }">selected="selected"</c:if>>用户下单</option>
							   <option value="1" <c:if test="${lvOrder.flag==1 or orderType==1 }">selected="selected"</c:if>>商家下单</option>
							 </select>
					  </td>
						<td><label>订单状态</label>
						<c:if test="${lvOrder.orderFlag!=1}">
						<select name="lvOrder.status" 
						<c:if test="${lvOrder.statusFlag!=1 && lvOrder.flag!=1 and lvOrder.orderFlag!=1}" >disabled="disabled"</c:if>
						>
						  <option value="">==请选择==</optoin>
						  <option value="0" <c:if test="${lvOrder.status==0 }">selected="selected"</c:if>>待发货</option>
						  <option value="1" <c:if test="${lvOrder.status==1 }">selected="selected"</c:if>>已发货，未确认收货</option>
						  <option value="2" <c:if test="${lvOrder.status==2 }">selected="selected"</c:if>>已收货 ,待评价</option>
						  <option value="3" <c:if test="${lvOrder.status==3 }">selected="selected"</c:if>>已退货</option>
						  <option value="4" <c:if test="${lvOrder.status==4 }">selected="selected"</c:if>>已完成</option>
						</select>
			            </c:if>
			            <c:if test="${lvOrder.orderFlag==1}">
			            <select name="lvOrder.status">
						  <option value="">==请选择==</optoin>
						  <option value="1" <c:if test="${lvOrder.status==1 }">selected="selected"</c:if>>已发货，未确认收货</option>
						  <option value="2" <c:if test="${lvOrder.status==2 }">selected="selected"</c:if>>已收货 ,待评价</option>
						</select>
			            </c:if>
						</td>
				        <td><label>支付状态</label>
				        <select name="lvOrder.payStatus"
				        <c:if test="${lvOrder.statusFlag!=1 && lvOrder.flag!=1}">disabled="disabled"</c:if>>
				           <option value="">==请选择==</optoin>
				           <option value="0" <c:if test="${lvOrder.payStatus==0 }">selected="selected"</c:if>>未支付</optoin>
				           <option value="1" <c:if test="${lvOrder.payStatus==1 }">selected="selected"</c:if>>已支付，已确认</optoin>
				           <option value="2" <c:if test="${lvOrder.payStatus==2 }">selected="selected"</c:if>>已支付，未确认</optoin>
				        </select>
				        </td>
				</tr><tr>
							 <td><label>国家</label><select name="countryId" id="countryId" onchange="changeCountry()">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${areaList}" var="area">
						   <option value="${area.id }" <c:if test="${countryId==area.id }">selected="selected"</c:if>>${area.namecn }</option>
						   </c:foreach>
						 </select></td>
				<td><label>洲/省</label>
				            <input type="text" name="lvOrderAddress.provinceName"  value="${lvOrderAddress.provinceName }" id="txtProvince" />
				            <select name="lvOrderAddress.provinceId" id="selProvince" class="required" style="display:none" >
							<option value="">==请选择==</option>
							 <c:foreach items="${lvOrder.addressList}" var="lp" varStatus="index">
							    <option value="${lp.provinceId}" <c:if test="${lp.provinceId == lvOrderAddress.provinceId }">selected="selected"</c:if>>${lp.provinceName}</option>
							 </c:foreach>
							</select>
							<c:foreach items="${lvOrder.addressList}" var="lp" varStatus="index">
							  <input type="hidden" name="lvOrder.addressList[${index.index }].provinceId" class="provinceDate" value="${lp.provinceId}">
							  <input type="hidden" name="lvOrder.addressList[${index.index }].provinceName" class="provinceDate"  value="${lp.provinceName}">	
							</c:foreach>
				</td>
				<td><label>支付方式</label>	
				 <select name="lvOrder.paymethod" style="width:300px;">
				          <option value="">==请选择==</option>
						 <s:iterator value="@com.lshop.common.util.Constants@PAY_METHODS" status="stat" id="item">
				             <option value="${item.key }" <c:if test="${lvOrder.paymethod==item.key}">selected="selected"</c:if>>${item.value }</option>    
				         </s:iterator>
				</select >
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
		    <sec:authorize url="exportOrderInfo">
			<li><a class="icon" href="lvOrderAction!exportOrder.action?json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			</sec:authorize>
			<li class="line">line</li>
			<sec:authorize url="ShopSendNotice">
			<c:if test="${lvOrder.payStatus==1}">
			<li><a class="add" href="lvOrderAction!doShopNotice.action?json.navTabId=${json.navTabId}" target="dwzSelectedTodo" title="确实要批量提醒这些记录吗?" rel="ids"><span>批量提醒发货</span></a></li>
			</c:if>
			</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="235">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="2%" >ID</th>
				<th width="5%">商家名称</th>
				<th width="10%">订单编号</th>
				<th width="5%">收货人姓名</th>
				<th width="5%">订单总金额</th>
				<th width="5%">优惠券</th>
				<th width="5%">币种</th>
				<th width="5%">订单类型</th>
				<th width="5%">支付方式</th>
				<th width="5%">支付状态</th>
				<th width="5%">订单状态</th>
				<th width="5%" orderField="createTime" class="${orderDirection}">下单时间</th>
				<th width="5%" orderField="overtime" class="${orderDirection}">支付时间</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
		
     <s:iterator value="page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.oid}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				<c:foreach items="${storeList}" var="store">
				<c:if test="${store.storeFlag==item.storeId}">
				<a style="color: blue" href="lvStoreMngAction!shopView.action?lvStore.storeFlag=${item.storeId}&json.navTabId=${json.navTabId }" target="dialog" title="查看店铺信息"  width="900" height="730" mask="true">
				${store.name}</a></c:if>
				</c:foreach>
				</td>
				<td>${item.oid }</td>
				<td>${item.relName }</td>
				<td>${item.totalPrice }</td>
				<td>${item.couponCodeList }</td>
				<td>${item.currency }</td>
				<td>
				<c:if test="${item.flag==0}">用户下单</c:if>
				<c:if test="${item.flag==1}">商家下单</c:if>
				</td>
				<td>
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(#item.paymethod)"/>
				</td>
				<td>
				<c:if test="${item.payStatus==0}">未支付</c:if>
				<c:if test="${item.payStatus==1}">已支付,已确认</c:if>
				<c:if test="${item.payStatus==2}">已支付未确认</c:if>
				</td>
				<td>
				<c:if test="${item.status==0}">待发货</c:if>
				<c:if test="${item.status==1}">已发货，未确认收货</c:if>
				<c:if test="${item.status==2}">已收货 ,待评价 </c:if>
				<c:if test="${item.status==3}">已退货</c:if>
				<c:if test="${item.status==4}">已完成</c:if>
				</td>
				<td>
				<s:date name="createTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:date name="overtime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
                <sec:authorize url="viewOrderDetails">
				    <a class="btnView" href="lvOrderAction!view.action?lvOrder.id=${item.id}&lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="900" height="730" mask="true">查看</a>
                </sec:authorize>
                <sec:authorize url="editOrderInfo">
				    <a class="btnEdit" href="lvOrderAction!befEdit.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  navTabId="lvProduct" rel="lvProduct" width="900" height="700" mask="true">编辑</a>
				    </sec:authorize>
				<sec:authorize url="deleteOrderInfo">
				    <c:if test="${item.isdelete!=-1}">
				    <a class="btnDel" href="lvOrderAction!del.action?lvOrder.oid=${item.oid}&lvOrder.storeId=${item.storeId }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
				    </c:if>
				</sec:authorize>
				    <sec:authorize url="noticeOrderInfo">   
				    <a  href="lvOrderAction!notice.action?lvOrder.id=${item.id }&lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }"  title="通知" target="dialog"  navTabId="lvOrder" rel="lvOrder" width="1020" height="600" mask="true">通知</a>
				    </sec:authorize>  				    
				    <c:if test="${(item.isServiceAudit!=1 or item.isFinanceAudit!=1) and item.payStatus==1 and item.isdelete!=-1 and lvOrder.auditFlag!=2}">
				       <sec:authorize url="auditOrderInfo">  
				       <a  href="lvOrderAction!befAudit.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="审核" target="dialog"  navTabId="lvProduct" rel="lvProduct"  width="900" height="700" mask="true">审核</a>
				       </sec:authorize>  
				       <sec:authorize url="orderSendRemark">  
				       <a  href="lvOrderAction!befSendRemark.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="发货备注" target="dialog"  navTabId="lvProduct" rel="lvProduct"  width="450" height="350" mask="true">发货备注</a>
				       </sec:authorize>  
				    </c:if>
				    
				    <c:if test="${item.status==0 and item.payStatus==1 and item.isServiceAudit==1 and item.isFinanceAudit==1 and item.isdelete!=-1}">
				    <sec:authorize url="sendOrderInfo">     
				       <a  href="lvOrderAction!befSendOrder.action?lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }"  title="订单发货" target="dialog"  navTabId="lvProduct" rel="lvProduct"  width="1020" height="700" mask="true">发货</a>
				    </sec:authorize>  
				    <sec:authorize url="synSendOrder">     
				       <a  href="lvOrderAction!synSendOrder.action?lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }"  title="同步物流" target="ajaxTodo"  navTabId="lvProduct" rel="lvProduct" mask="true">同步物流</a>
				    </sec:authorize>
				    </c:if>
				 
				    <c:if test="${item.status==1 and item.isdelete!=-1 and item.payStatus==1}">		
				       <sec:authorize url="confirmReceiving">     		      
				       <a  href="lvOrderAction!updateStatus.action?lvOrder.id=${item.id}&lvOrder.oid=${item.oid }&versionTime=${item.modifyTime }&lvOrder.status=2&lvOrder.storeId=${lvOrder.storeId }&json.navTabId=${json.navTabId }"  title="确认收货" target="ajaxTodo"   mask="true">确认收货</a>
				       </sec:authorize>  
				       <!-- 
				       <a  href="lvOrderAction!befSendRemark.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="发货备注" target="dialog"  navTabId="lvProduct" rel="lvProduct"  width="450" height="350" mask="true">发货备注</a>
				       -->
				       <sec:authorize url="orderBreakRemark">     
				       <a  href="lvOrderAction!befBreakRemark.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="退货" target="dialog"  navTabId="lvProduct" rel="lvProduct" width="450" height="350" mask="true">退货</a>
				       </sec:authorize>  
				    </c:if>
				<sec:authorize url="orderBreakRemark">        
				    <c:if test="${(item.status==2 or item.status==4) and item.isdelete!=-1 and item.payStatus==1}">
				       <a  href="lvOrderAction!befBreakRemark.action?lvOrder.id=${item.id}&json.navTabId=${json.navTabId }"  title="退货" target="dialog"  navTabId="lvProduct" rel="lvProduct" width="450" height="350" mask="true">退货</a>
				    </c:if>
				</sec:authorize>  
				<sec:authorize url="orderReceiving">    
				    <c:if test="${item.isdelete==-1}">
				       <a  href="lvOrderAction!receiving.action?lvOrder.id=${item.id}&lvOrder.oid=${item.oid }&lvOrder.storeId=${item.storeId }&versionTime=${item.modifyTime }&json.navTabId=${json.navTabId }"  title="恢复订单" target="ajaxTodo"   mask="true">恢复</a>
				    </c:if>
				</sec:authorize>  
				<sec:authorize url="updateOrderPrice">
				    <c:if test="${item.payStatus!=1 and item.isdelete!=-1}">
				        <a  href="lvOrderAction!befUpdatePrice.action?lvOrder.id=${item.id }&lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }"  title="修改订单总金额" target="dialog"  navTabId="lvOrder" rel="lvOrder" width="420" height="300" mask="true">修改金额</a>
				    </c:if>
				</sec:authorize>
				<sec:authorize url="updateOrderStatus">
				     <c:if test="${item.isdelete!=-1}">
				     <a  href="lvOrderAction!befUpdateStatus.action?lvOrder.id=${item.id }&lvOrder.oid=${item.oid}&json.navTabId=${json.navTabId }"  title="修改订单状态" target="dialog"  navTabId="lvOrder" rel="lvOrder"  width="420" height="300" mask="true">修改状态</a>
				     </c:if>
				</sec:authorize>
				<sec:authorize url="orderChangeStore">
				<c:if test="${item.payStatus==1 and item.isdelete!=-1}">
				<a  href="lvOrderAction!befChangeStore.action?lvOrder.oid=${item.oid}&lvOrder.storeId=${item.storeId }&lvStore.countryId=${item.contryId }&lvStore.status=1&json.navTabId=${json.navTabId }"  title="转单(请选择目标店铺)" target="dialog"  navTabId="lvOrder" rel="lvOrder"  width="800" height="600" mask="true">转单</a>
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

<script type="text/javascript">
$(document).ready(function(){
　 if(${empty lvOrder.addressList}){
    $("#txtProvince").show();
    $("#selProvince").hide();
  }else{
    $("#txtProvince").hide();
    $("#selProvince").show();
  }
});
</script>

