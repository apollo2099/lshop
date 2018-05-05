<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
	<form method="post" action="lvOrderAction!edit.action?json.navTabId=${json.navTabId }"
	 class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="lvOrderAddress.id" value="${lvOrderAddress.id }">
		<input type="hidden" name="lvOrderAddress.orderId" value="${lvOrderAddress.orderId }">
		<input type="hidden" name="lvOrderAddress.relCode" value="${lvOrderAddress.relCode }">
		<input type="hidden" name="lvOrderAddress.storeId" value="${lvOrderAddress.storeId }">
		<input type="hidden" name="lvOrderAddress.code" value="${lvOrderAddress.code }">
		<input type="hidden" name="lvOrderAddress.createTime" value="${lvOrderAddress.createTime }">
		<input type="hidden" name="lvOrderAddress.contryId" value="${lvOrderAddress.contryId }" id="contryid">
		<div class="pageFormContent" layoutH="56">
		<div  layoutH="200">
		<!--取值 -->
		        <dl>
					<dt>用户Email：</dt>
					<dd>
						<input type="text" name="lvOrder.userEmail" value="${lvOrder.userEmail }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<dl>
					<dt>收货人姓名：</dt>
					<dd>
						<input type="text" name="lvOrderAddress.relName" value="${lvOrderAddress.relName }" maxlength="32" size="32">
					</dd>				  
				</dl>
				  <div class="divider"></div>
	
				<dl>
					<dt>订单编号：</dt>
					<dd>
						<input type="text" name="lvOrder.oid" value="${lvOrder.oid }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<dl>
					<dt>订单金额：</dt>
					<dd>
						<input type="text" name="lvOrder.totalPrice" value="${lvOrder.totalPrice}" size="32" readonly="readonly" />
					</dd>				  
				</dl>
				<dl>
					<dt>订单状态：</dt>
					<dd>
						<c:if test="${lvOrder.status==0}">待发货</c:if>
						<c:if test="${lvOrder.status==1}">已发货，未确认收货</c:if>
						<c:if test="${lvOrder.status==2}">已收货 ,待评价 </c:if>
						<c:if test="${lvOrder.status==3}">已退货</c:if>
						<c:if test="${lvOrder.status==4}">已完成</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>订单类型：</dt>
					<dd>
				<c:if test="${lvOrder.flag==0}">用户下单</c:if>
				<c:if test="${lvOrder.flag==1}">商家下单</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>下单时间：</dt>
					<dd>
                        <input name="lvOrder.createTime" type="text" size="30"  format="yyyy-MM-dd HH:mm:ss" size="32"
						  value="<s:date name="lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/>"  readonly="readonly"/>
					</dd>				  
				</dl>
				<dl>
					<dt>支付方式：</dt>
					<dd>
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(lvOrder.paymethod)"/>
					</dd>				  
				</dl>
				<dl>
					<dt>支付成功时间：</dt>
					<dd>
						  <input name="lvOrder.overtime" type="text" size="30"  format="yyyy-MM-dd HH:mm:ss" size="32"
						  value="<s:date name="lvOrder.overtime" format="yyyy-MM-dd HH:mm:ss"/>" class="date" readonly="readonly"/>
					</dd>				  
				</dl>
				<dl>
					<dt>第三方支付订单号：</dt>
					<dd>
						<input type="text" name="lvOrder.thirdPartyOrder" value="${lvOrder.thirdPartyOrder }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<c:if test="${lvOrder.flag==1 }">
				<dl>
					<dt>第三方订单号：</dt>
					<dd>
						${lvOrder.thirdOrderNum}
					</dd>				  
				</dl>
				
				<dl>
					<dt>第三方订单来源：</dt>
					<dd>
					<c:if test="${not empty  lvOrder.thirdOrderSource}">
						<gv:dictionary type="text" code="THIRD_ORDER_SOURCE"  name="lvOrder.thirdOrderSource" value="${lvOrder.thirdOrderSource }"/>
					</c:if>
					</dd>				  
				</dl>
				</c:if>
				
				<dl class="nowrap">
					<dt>订单备注：</dt>
					<dd>
						<textarea rows="3" cols="85" name="lvOrder.orderRemark" readonly="readonly">${lvOrder.orderRemark }</textarea>
					</dd>				  
				</dl>
			 <div class="divider"></div>
			    <dl class="nowrap">
				    <dt>收货地址：</dt>
				   <dd>
				   <label >
                   <input name="lvOrderAddress.adress" type="text" size="20" maxlength="128" class="required" value="${lvOrderAddress.adress }" alt="街道" />
                   </label>
                   <label >
                   <input name="lvOrderAddress.cityName" type="text" size="20" maxlength="32" class="required" value="${lvOrderAddress.cityName }" alt="县/市" />
                   </label>
                   <label >
                   <input id="txtProvinceEdit" name="lvOrderAddress.provinceName" type="text" size="20" maxlength="32" class="required" value="${lvOrderAddress.provinceName }" alt="洲/省" />
                   <select id="selProvinceEdit" name="lvOrderAddress.provinceName"></select>
                   </label>
                    <label >
                   <select class="required"  name="lvOrderAddress.contryId" id="contryidEdit" onchange="contryChange()" disabled="disabled">
						<option value="">==请选择==</option>
						<s:iterator value="#request.areaList"  id="c">
						  <option value="${c.id}"
						  <c:if test="${lvOrderAddress.contryId==c.id }">selected="selected"</c:if>
						  >${c.nameen}</option>
						</s:iterator>
						</select>
						<script type="text/javascript">
							 function contryChange(){
								$("#contrynameIdEdit").val($("#contryidEdit").find("option:selected").text());
							 }
						</script>
                        <input id="contrynameIdEdit" name="lvOrderAddress.contryName" type="hidden" size="20" maxlength="32" class="required" value="${lvOrderAddress.contryName }" alt="国家" />
                   </label>
                              
              
				   </dd>				  
			   </dl>
				<dl>
					<dt>邮编：</dt>
					<dd>
						<input type="text" name="lvOrderAddress.postCode" value="${lvOrderAddress.postCode }"  maxlength="16" size="32">
					</dd>				  
				</dl>
				<dl>
				    <dt>手机：</dt>
					<dd>
						<input type="text" name="lvOrderAddress.mobile" value="${lvOrderAddress.mobile }"  maxlength="16" size="32">
					</dd>				  
				</dl>
				<dl>
					<dt>联系电话：</dt>
					<dd>
						<input type="text" name="lvOrderAddress.tel" value="${lvOrderAddress.tel }" maxlength="16" size="32">
					</dd>				  
				</dl>
								
				<dl>
					<dt>物流公司：</dt>
					<dd>
						<input type="text" name="lvOrder.expressCompany" value="${lvOrder.expressCompany }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<dl>
					<dt>快递名称：</dt>
					<dd>
						<input type="text" name="lvOrder.expressName" value="${lvOrder.expressName }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<dl>
					<dt>快递单号：</dt>
					<dd>
						<input type="text" name="lvOrder.expressNum" value="${lvOrder.expressNum }" readonly="readonly" size="32">
					</dd>				  
				</dl>
				<dl style="height: 30px;">
					<dt>客服审核状态：</dt>
					<dd>
                 <c:if test="${lvOrder.isServiceAudit==0}">待审核</c:if>
				 <c:if test="${lvOrder.isServiceAudit==1}">审核通过</c:if>
				 <c:if test="${lvOrder.isServiceAudit==-1}">审核未通过</c:if>
					</dd>				  
				</dl>
				<dl style="height: 30px;">
					<dt>客服审核备注：</dt>
					<dd>
						<textarea rows="2" cols="30" name="lvOrder.serviceAuditContent" readonly="readonly">${lvOrder.serviceAuditContent }</textarea>
					</dd>				  
				</dl>
				<dl style="height: 30px;">
					<dt>财务审核状态：</dt>
					<dd>
                 <c:if test="${lvOrder.isFinanceAudit==0}">待审核</c:if>
				 <c:if test="${lvOrder.isFinanceAudit==1}">审核通过</c:if>
				 <c:if test="${lvOrder.isFinanceAudit==-1}">审核未通过</c:if>
					</dd>				  
				</dl>
				<dl style="height: 30px;">
					<dt>财务审核备注：</dt>
					<dd>
						<textarea rows="2" cols="30" name="lvOrder.financeAuditContent" readonly="readonly">${lvOrder.financeAuditContent }</textarea>
					</dd>				  
				</dl>
			  </div>	
			 <div class="divider"></div>
			  <div  > 
		<table class="table" width="100%">
		<thead>
			<tr>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="50%">产品名称</th>
				<th width="20%">产品单价</th>
				<th width="25%">产品数量</th>
		   </tr>
		</thead>
		<tbody>
		<c:foreach items="${orderDetailsList}" var="orderDetails">
			<tr>
				<td>${orderDetails.id }</td>
				<td>
				<c:foreach items="${productList}" var="product">
				  <c:if test="${product.code == orderDetails.productCode}">${product.productName}</c:if>
				</c:foreach>
				</td>
				<td>${orderDetails.currency } ${orderDetails.oprice}</td>
				<td>${orderDetails.pnum }</td>
			</tr>
		</c:foreach>
		<c:foreach items="${orderGiftList }" var="gift">
		<tr>
		        <td>${gift.id }</td>
				<td>${gift.giftName }</td>
				<td></td>
				<td>${gift.giftNum }</td>
		</tr>
		</c:foreach>
		</tbody>
	</table>

		</div>
		</div>
           <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript">
//根据专卖店查询专卖店分类下面的店铺信息
function changeCountry(){
	var countryVal = $("#contryidEdit").val();
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toProvinceEn.action",
		  data: "countryId="+countryVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#selProvinceEdit option").remove();
		        var listTmp=data.listTmp;  	
				var temp=$("#txtProvinceEdit").val();
				for(var k=0;k<listTmp.length;k++){ 
					var provinceName = listTmp[k].provinceName; 
					var provinceId = listTmp[k].provinceId; 
					if(temp==provinceName){
					  $("<option></option>").val(provinceName).text(provinceName).attr("selected","selected").appendTo($("#selProvinceEdit"));
					}else{
					  $("<option></option>").val(provinceName).text(provinceName).appendTo($("#selProvinceEdit"));
					}
					
				 }
				 $("#selProvinceEdit").show();
		         $("#txtProvinceEdit").hide();
		         $("#selProvinceEdit").attr("disabled",false);
		         $("#txtProvinceEdit").attr("disabled",true);
		     }else{
		        $("#selProvinceEdit option").remove();
		        $("#selProvinceEdit").hide();
		        $("#txtProvinceEdit").show();
		        $("#selProvinceEdit").attr("disabled",true);
		        $("#txtProvinceEdit").attr("disabled",false);
		     }
		  }
	});
}

//页面初始化加载
$(document).ready(function(){　　
changeCountry();
})
</script>

