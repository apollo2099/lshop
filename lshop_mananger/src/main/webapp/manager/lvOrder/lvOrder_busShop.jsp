<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<script type="text/javascript">
var resDomainArea = 'http://res.itvpad.com/www/res/area_en/';
//通过支付方式，判断币种
function changCurrency(flag){
  if(flag==3||flag==13||flag==17||flag==18){
      $('#currencyid').val('CNY');
	  $('.currencyInfoId').text('CNY');
  }else{
	 $('#currencyid').val('USD');
     $('.currencyInfoId').text('USD');
  }
}
//判断支付状态动态显示隐藏支付时间
function changeOvertime(){
	if($("#payStatusId").val()==1){
	  document.getElementById('overtimeId').disabled=""
		$("#overtimeId").attr("class","required date");
		$("#overtimeId").attr("disabled","");
	}else{
		$("#overtimeId").val('');
		$("#overtimeId").attr("class","date");
		$("#overtimeId").attr("disabled","disabled");
	}
}



//根据产品code查询产品信息(价格)
function changeProduct(){
var product=$("#product").find("option:selected")
var productVal = product.val();
    $.ajax({
		  type: "POST",
		  url: "lvProductAction!toProduct.action",
		  data: "lvProduct.code="+productVal,
		  dataType:"json",
		  success: function(data){
			 $("#priceUsd").val(data.price);
		  }
	});
}
//添加产品
$(document).ready(function(){
	
	initArea();

	
	$("#but").click(function(){
           var product=$("#product").find("option:selected");
           if(product.val().length<=0){
              alertMsg.error("请先选择添加的产品！!");
              return false;
           }
           var productText= product.text();
           var productVal = product.val();
           var $table=$("#tab tr");
           var len=$table.length;
           

		   //判断添加的产品是否存在
		   var flag = true;
		   $("#tab tr").each(function(){
			   if($.trim($(this).find("td:eq(1)").text())==productVal){
			      alertMsg.error("此项已经存在，须删除后才可重新添加！");
			      flag=false;
				  return false;
			   }
	       });
	       //解决因为删除引起的id重复问题
	       $("#tab tr").each(function(){
		       if($.trim($(this).attr("id"))>=len){
		       len=parseInt($.trim($(this).attr("id")))+1;
		       }
	       });
           if(flag){
        	   $("#tab").append("<tr id="+(len)+"><td width='5%'>"+len+"</td>"+
                            "<td style='display:none'>"+productVal+"</td>"+
                            "<td >"+productText+"<input type='hidden' name='lvOrder.list["+(len)+"].productCode' value="+productVal+"></td>"+
                            "<td width='25%'>"+$("#priceUsd").val()+"</td>"+
                            "<td width='35%'><input type='text' name='lvOrder.list["+(len)+"].pnum' id=num"+(len)+" value='1' class='digits'></td>"+
                            "<td width='10%'>"+$("#currency").val()+"</td>"+
                            "<td width='10%'><a href='#' onclick='deltr("+(len)+")'>删除</a></td>"+
                            "</tr>");
           }
           });
});
//删除对应表格行数据
function deltr(index)
{
	$table=$("#tab tr");
{
	$("tr[id='"+index+"']").remove();
}
}

//拼接订单产品信息，提交表单
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}

	//循环遍遍历表格中添加的产品信息
	var detailsListTmp="";
	$("#tab tr").each(function(){
		detailsListTmp += $.trim($(this).find("td:eq(1)").text()) + ","  + 
		               $.trim($(this).find("td input").val()) + ",";
       $("#detailsList").val(detailsListTmp);	
	});
	
	var detailsList=$("#detailsList").val();
	if(detailsList==null||detailsList.length<=0){
		alertMsg.error("请先添加购买的产品信息!");
		return false;
	}
	
	var payStyleTxt=$("#payStyle").text();
	if(payStyleTxt==""||$.trim(payStyleTxt).length<=0){
		alertMsg.error("请先添加店铺的付款方式!");
		return false;
	}
	
	//
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		   callback(json);
		},
		error: DWZ.ajaxError
	});
	return false;
}

//隐藏/显示优惠券填充数据
function showCouponCode(tmp){
  if(tmp!=null){
     if($(tmp).val()==1){
        $("#couponDiv").show();
     }else if($(tmp).val()==0){
        $("#couponDiv").hide();
     }
  }
}

//现在优惠码是否有效
function checkCouponCode(){
	var couponCode=$("#couponCode").val();
	if(couponCode==""){
		$("#couponMsg").html("<font color='red'>請輸入優惠碼！</font>");
	}else{
		$.ajax({
		   type: "POST",
		   url: "lvOrderAction!checkCouponCode.action",
		   data: "couponCode="+couponCode,
		   dataType: "html",
		   success: function(data){
		   	    var o=eval("("+data+")");
		   	    if(o.couponFlag==null){
		   	    	$("#couponMsg").html("<font color='red'>優惠券驗證通過！金額为：$<span id='price'>"+o.money+"</span>，有效日期為："+o.validatydate+"</font>");
		   	    }else if(o.couponFlag==1){
		   	 		$("#couponMsg").html("<font color='red'>優惠码不存在！</font>");
		   	 	}else if(o.couponFlag==2){
		   	 		$("#couponMsg").html("<font color='red'>優惠券不存在！</font>");
		   	 	}else if(o.couponFlag==3){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已停用！</font>");
		   	 	}else if(o.couponFlag==4){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已過期！</font>");
		   	 	}else if(o.couponFlag==5){
		   	 	    $("#couponMsg").html("<font color='red'>優惠码已使用！</font>");
		   	 	}
		   	 }
		});
	}
}

//根据店铺编号查询对应店铺的产品信息
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvOrderAction!toBusShop.action",
		  data: "lvProduct.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     //删除添加列表中的产品信息
		     $("#tab tr").remove();
		     $("#payStyle div").remove();
		     if(data!=null){
		        $("#product option").remove();
		        $("#product").append("<option value=''>==请选择==</option>");
		        var listTmp=data.productList;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productCode = listTmp[k].productCode; 
					$("#product").append("<option value="+productCode+">"+productName+"</option>");
				}
				var listpayTmp=data.paymentStyleList;
				for(var num=0;num<listpayTmp.length;num++){
				    var payName=listpayTmp[num].payName; 
				    var payValue = listpayTmp[num].payValue; 
					var tmpHtml="<div><input name='lvOrder.paymethod' type='radio' class='required' value='"+payValue+"' onclick='changCurrency("+payValue+")'  />"+payName+"<br /></div>";
					$("#payStyle").append(tmpHtml)
				}
				//根据店铺查询币种
				$("#currency").val(data.currency);
				
		     }else{
		        $("#product option").remove();
		        $("#product").append("<option value=''>==请选择==</option>");

		     }
		  }
	});
}
</script>
<div class="page">
	<div class="pageContent">
		<form method="post" id="addressForm"
			action="lvOrderAction!doShop.action"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, navTabAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="lvOrder.currency" id="currencyid" />
			<input type="hidden" name="lvOrder.detailsList" id="detailsList"/>
			<input type="hidden" id="priceUsd"/>
			<input type="hidden" id="currency"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->	
				<dl class="nowrap">
					<dt>所属店铺：</dt>
					<dd>
						<select name="lvOrder.storeId" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						 </select>
					</dd>				  
				</dl>
                <dl class="nowrap">
					<dt>
						产品名称：
					</dt>
					<dd>
						<select id="product" onchange="changeProduct()" style="width:230px;">
						 <option value="">==请选择==</option> 
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="添加产品" id="but">
					</dd>
				</dl>			
				<hr width="100%"><br/>
				<table class="table" border="1" width="80%"  >
				   <thead>
				   <tr>
						<th width="5%" orderField="id" class="desc">ID</th>
						<th >产品名称</th>
						<th width="25%">产品单价</th>
						<th width="35%">产品数量</th>
						<th width="10%">币种</th>
						<th width="10%">操作</th>
				   </tr>
				   </thead>
				   <tbody id="tab">
				   </tbody>
			   </table>
			   <hr width="100%">
			   	<dl class="nowrap">
					<dt>
						第三方订单号：
					</dt>
					<dd>
						<input name="lvOrder.thirdOrderNum" type="text" size="30" maxlength="32"  class="required" alt="第三方订单号" />
					</dd>
				</dl>			   

				<dl class="nowrap">
					<dt>
						第三方订单来源：
					</dt>
					<dd>
						<gv:dictionary type="select" code="THIRD_ORDER_SOURCE"  name="lvOrder.thirdOrderSource"  style="required"/>
					</dd>
				</dl>
			   <dl class="nowrap">
					<dt>
						订单总金额：
					</dt>
					<dd>
						<input name="lvOrder.totalPrice" type="text" size="30"
							maxlength="11"  alt="订单总金额" />(注：可手动输入订单总金额数目)
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						用户邮箱地址：
					</dt>
					<dd>
						<input name="lvOrder.userEmail" type="text" size="30"
							maxlength="128" class="required email" alt="请输入电子邮件" />
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						收货人姓名：
					</dt>
					<dd>
						<input name="lvOrderAddress.relName" type="text" size="30"
							maxlength="32" class="required" alt="输入收货人姓名"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						手机号码：
					</dt>
					<dd>
						<input name="lvOrderAddress.mobile" id="mobileId" type="text"
							size="30" maxlength="16" alt="输入手机号码" class="phone" />
					</dd>
				</dl>



				<dl class="nowrap">
					<dt>
						固定电话：
					</dt>
					<dd>
						<input name="lvOrderAddress.tel" id="telId" type="text" size="30" maxlength="32"
							class="phone" alt="请输入电话号码" />
					</dd>
				</dl>

				<dl class="nowrap">
					<dt>
						收货地址：
					</dt>
					<dd>
					<input name="lvOrderAddress.adress" id="adress" type="text" style="float:left;"
										class="input2" value="街道詳細地址"
										onfocus="if(this.value=='街道詳細地址')this.value=''"
										onblur="if(this.value=='')this.value='街道詳細地址'" />
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="citySelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>	
									<input type="hidden" name="lvOrderAddress.cityId" id="cityId"/>
									<input type="hidden" name="lvOrderAddress.cityName" id="cityName"/>	
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="provinceSelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>
									<input type="hidden" name="lvOrderAddress.provinceId" id="provinceId"/>
									<input type="hidden" name="lvOrderAddress.provinceName" id="provinceName"/>								 
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="countrySelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>									
									<input type="hidden" name="lvOrderAddress.contryName"
										id="contryName" value="" />
									<input type="hidden" name="lvOrderAddress.contryId"
										id="contryId" value="" />	
										
										</dd>
										<span id="addressInfo" font="red"></span>									
									
				</dl>
				<dl class="nowrap">
					<dt>
						邮政编码：
					</dt>
					<dd>
						<input name="lvOrderAddress.postCode" type="text" size="30"
							maxlength="16" class="required" alt="请输入邮政编码"/>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						是否支付：
					</dt>
					<dd>
						<s:select list="#{1:'已支付',0:'未支付'}" id="payStatusId" name="lvOrder.payStatus" onchange="changeOvertime()" ></s:select>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						付款方式：
					</dt>
					<dd>
						 <div id="payStyle">
						 </div>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						第三方支付单号：
					</dt>
					<dd>
						<input name="lvOrder.thirdPartyOrder" type="text" size="30" maxlength="32"  alt="第三方支付单号" />
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						最佳收货时间：
					</dt>
					<dd>
					        <input type="radio" class="required" name="lvOrderAddress.bestDeliveryTime" value="1"  <s:if test="lvOrderAddress.bestDeliveryTime==1">checked="checked"</s:if>/>任何时间均可以送货<br/>
                            <input type="radio"  class="required" name="lvOrderAddress.bestDeliveryTime" value="2" <s:if test="lvOrderAddress.bestDeliveryTime==2">checked="checked"</s:if>/>周一至周五送货（周末没人无法签收）<br/>
                            <input type="radio"  class="required" name="lvOrderAddress.bestDeliveryTime" value="3" <s:if test="lvOrderAddress.bestDeliveryTime==3">checked="checked"</s:if>/>周末送货（工作日没人无法签收）<br/>
                            <input type="radio"  class="required" name="lvOrderAddress.bestDeliveryTime" value="4" <s:if test="lvOrderAddress.bestDeliveryTime==4">checked="checked"</s:if>/>晚上送货（白天没人无法签收）
                    </dd>
				</dl>
				<dl class="nowrap">
					<dt>
						到款日期：
					</dt>
					<dd>

						<input type="text" name="overtime" id="overtimeId"  class="required date" format="yyyy-MM-dd HH:mm:ss" readonly="readonly" size="32">

					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						订单备注：
					</dt>
					<dd>
						<textarea rows="6" cols="50" name="lvOrder.orderRemark" maxlength="315"></textarea>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>
						是否使用优惠券：
					</dt>
					<dd>
						<s:select list="#{'':'==请选择==',1:'是',0:'否'}"  id="isCouponCode" onchange="showCouponCode(this)"></s:select>  
					</dd>
				</dl>
				<div id="couponDiv" style="display:none">
				<dl>
					<dt>
						請輸入您的優惠碼:
					</dt>
					<dd>
						<input id="couponCode" name="couponCode" type="text" class="input2" value="" onblur="checkCouponCode();"/>
						<a href="javascript:checkCouponCode();"><img src="http://res.itvpad.com/www/res/images/buy_icon.gif" width="55" height="23" border="0" /></a>
						<!-- 
						<div class="button">
							<div class="buttonContent">
						    <button onclick="javascript:checkCouponCode();">使用</button>
						</div></div>
						 -->
					</dd>
					<dd></dd>
				</dl>
				<dl >
					<dd>
						<span id="couponMsg"></span>  
					</dd>
				</dl>
				</div>
				

			</div>

			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onClick="return validateAddress();">
									提交表单
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>