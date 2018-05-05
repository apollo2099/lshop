<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script type="text/javascript">
//添加商品
$(document).ready(function(){　　
　　　$("#but").click(function(){
           var product=$("#product").find("option:selected");
           if(product.val().length<=0){
              alertMsg.error("请先选择添加的商品！!");
              return false;
           }
           var productText= product.text();
           var productVal = product.val();
　　　　　　var $table=$("#tab tr");
　　　　　　var len=$table.length;
		   //判断添加的商品是否存在
		   var flag = true;
		   $("#tab tr").each(function(){
			   if($.trim($(this).find("td input:eq(0)").val())==productVal){
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
               $("#tab").append("<tr id="+(len)+">"+
                                "<td >"+len+"</td>"+
                                "<td style='display:none'><input type='hidden' name='lvProduct.packageList["+(len)+"].productCode' id=num"+(len)+" value="+productVal+"></td>"+
							    "<td >"+productText+"</td>"+
							    "<td ><input type='text' name='lvProduct.packageList["+(len)+"].pnum' id=num"+(len)+" value='1' class='required digits'></td>"+
							    "<td >USD</td>"+
							    "<td ><span><a href='#' onclick='deltr("+(len)+")'>删除</a></span></td>"+
							    "</tr>");      
                          
           }
　　　　　    })　
})

//删除对应表格行数据
function deltr(index)
{
　$table=$("#tab tr");
　{
　　$("tr[id='"+index+"']").remove();　
　}　
}

//添加自定义校验
function iframeCallbackForm(form, callback){
	var $form = $(form), $iframe = $("#callbackframe");
	if(!$form.valid()) {return false;}
    
    var mealId=$("#mealId").val();
    if(mealId!=null&&mealId==1){
       var $table=$("#tabDetails tr");
       var len=$table.length;
       if(len<=0){
	      alertMsg.error('选择套餐时，请添加套餐商品信息!');
	      return false;
       }
    }

    var flag=validateActivityOrLadder();
    if(!flag){
       alertMsg.error('活动和阶梯价不能同时启动!');
       return flag;
    }
    
    var pubProductCode=$("#pubProductCode").val();
    var pubPackageCode=$("#pubPackageCode").val();
    if(pubProductCode.length<=0&&pubPackageCode<=0){
    	alertMsg.error('请先选择商品库商品或者套餐!');
    	return false;
    }
    
	if ($iframe.size() == 0) {
		$iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
	}
	if(!form.ajax) {
		$form.append('<input type="hidden" name="ajax" value="1" />');
	}
	form.target = "callbackframe";
	
	_iframeResponse($iframe[0], callback || DWZ.ajaxDone);
}

//活动和阶梯价不能同时启动
function validateActivityOrLadder(){
  var activity=$("#activity").val();
  var ladder=$("#ladder").val(); 
  if(activity!=null&&ladder!=null){
     if(activity==1&&ladder==1){
       //alertMsg.error('活动和阶梯价不能同时启动!');
       $("#aInfo").html("<font color='red'>活动和阶梯价不能同时启动！</font>");
       $("#eInfo").html("<font color='red'>活动和阶梯价不能同时启动！</font>");
       return false;
     }else{
       $("#aInfo").html("<font color='red'></font>");
       $("#eInfo").html("<font color='red'></font>");
     }
  }
   return true;
}
</script>

<div class="page unitBox">
	<div class="pageContent">
	<form method="post"
			action="lvProductAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallbackForm(this, navTabAjaxDone)" enctype="multipart/form-data">
			<input type="hidden" name="lvProduct.id" value="${lvProduct.id }">
		    <input type="hidden" name="lvProduct.storeId" value="${lvProduct.storeId }">
		    <input type="hidden" name="lvProduct.code" value="${lvProduct.code }">
		    <input type="hidden" name="lvProduct.createTime" value="${lvProduct.createTime }">
		    <input type="hidden" name="lvProduct.status" value="${lvProduct.status }">
		    <input type="hidden" name="lvProduct.pimage" value="${lvProduct.pimage }">
		    <input type="hidden" name="lvProduct.pubPackageName" value="${lvProduct.pubPackageName }">
		    <input type="hidden" name="lvProduct.oldPubProductCode" value="${lvProduct.pubProductCode }">
		    <input type="hidden" name="lvProduct.oldProductName" value="${lvProduct.productName }">
		    <input type="hidden" name="lvProduct.oldPType" value="${lvProduct.ptype }">
		    <input type="hidden" name="lvProduct.priceRmb" value="${lvProduct.priceRmb }"/>
		    <input type="hidden" name="lvProduct.priceUsd" value="${lvProduct.priceUsd }"/>
		    <input type="hidden" name="lvProduct.isPreferences" value="${lvProduct.isPreferences }"/>
		    
  		<div class="pageFormContent" layoutH="56">


				<dl>
					<dt>所属店铺：</dt>
					<dd>
						<select name="lvProduct.storeId" disabled="disabled"> 
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvProduct.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						 </select>
					</dd>				  
				</dl>
				<dl>
					<dt>商品类别：</dt>
					<dd>
						<select  name="lvProduct.ptype">
						<option value=""></option>
						<s:iterator value="#request.typeList" id="t">
						<option value="${t.code }" <s:if test="#t.code==lvProduct.ptype">selected="selected"</s:if>>${t.typeName}</option> 
						</s:iterator>
						</select>
					</dd>				  
				</dl>
					<dl>
					<dt>商城商品分类：</dt>
					<dd>
						<select  name="lvProduct.shopProductType" class="required" >
						<option value="">==请选择==</option>
						<s:iterator value="#request.shopProductTypeList" id="t">
						<option value="${t.code }" <s:if test="#t.code==lvProduct.shopProductType">selected="selected"</s:if>>${t.typeName}</option> 
						</s:iterator>
						</select>
					</dd>				  
				</dl>
				<dl>
					<dt>是否为套餐(：</dt>
					<dd>
					<select name="lvProduct.isSetMeal" onchange="changeSetMeal()" id="mealId" class="required">
					      <option value="">==请选择==</option>
					      <option value="1" <c:if test="${lvProduct.isSetMeal==1 }">selected="selected"</c:if>>是</option>
					      <option value="0" <c:if test="${lvProduct.isSetMeal==0 }">selected="selected"</c:if>>否</option>
					    </select>
					</dd>				  
				</dl>
			    <script type="text/javascript">
							 function changeSetMeal(){
								var flag=$("#mealId").find("option:selected").val();
								if(flag!=null&&flag.length>0){
									if(flag==1){//套餐
                                       $("#selPubProduct").hide();
                                       $("#selPubPackage").show();
									}else if(flag==0){//非套餐
										$("#selPubProduct").show();
	                                    $("#selPubPackage").hide();
									}
								}
							 }
				</script>
				<!-- 判断为套餐动态显示套餐选择的商品列表 -->
				<div id="selPubProduct" style="display:none">
				<dl class="nowrap">
					<dt>
						选择商品：
					</dt>
					<dd>		
						<input type="hidden" name="lvProduct.pubProductCode" value="${lvProduct.pubProductCode }" id="pubProductCode">
						<input type="hidden" name="lvProduct.pcode" value="${ lvProduct.pcode}"> 
						<input type="hidden" name="lvProduct.pmodel" value="${lvProduct.pmodel }">
						<a target="dialog" href="lvPubProductAction!selectSingleProduct.action?lvPubProduct.pubProductCode=${lvProduct.pubProductCode }&json.navTabId=${json.navTabId}" lookupGroup="lvProduct" width="850" height="600" mask="true"><input type="button" value="选择商品"></a>
					</dd>
				</dl>		
				</div>
				
				<div id="selPubPackage" style="display:none">
				    <dl class="nowrap">
					<dt>
						选择套餐：
					</dt>
					<dd>		
						<input type="hidden" name="lvPubPackage.code" value="${lvProduct.pubProductCode }" id="pubPackageCode">
						<input type="text" name="lvPubPackage.packageName" readonly="readonly" value="${lvProduct.pubPackageName }">
						<a target="dialog" href="lvPubPackageAction!selectSinglePackage.action?lvPubPackage.pubPackageCode=${lvProduct.pubProductCode }&json.navTabId=${json.navTabId}" lookupGroup="lvPubPackage" width="850" height="600" mask="true"><input type="button" value="选择套餐"></a>
					</dd>
				</dl>			
				<hr width="100%"><br/>    
				<table id="tab" class="table" border="1" width="80%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="25%">商品名称</th>
						<th width="20%">商品型号</th>
						<th width="30%">商务对接code</th>
						<th width='20%'>商品数量</th>
				   </tr>
				   </thead>
				   <tbody id="tabDetails">
				   <c:foreach items="${lvProduct.packageList}" var="lp" varStatus="index">
				   <tr id="${index.index }">
                                <td >${index.index }</td>
                                <c:foreach items="${productList}" var="product">
								<c:if test="${product.code==lp.pubProductCode}">
							    <td >${product.productName }</td>
							    <td >${product.productModel }</td>
							    <td >${product.pcode }</td>
							    </c:if>
								</c:foreach>
							    <td >${lp.pnum }</td>
					</tr>
					</c:foreach>
				   
				   </tbody>
			   </table>
			   </div>
			   
			   <div class="divider"></div>
			   <dl class="nowrap">
					<dt>商品名称：</dt>
					<dd>
						<input name="lvProduct.productName" type="text" class="required" size="100" value="${ lvProduct.productName}" maxlength="64"/>
					</dd>				  
				</dl>
				
				<dl  class="nowrap">
					<dt>商品详情页面主商品图：</dt>
					<dd>
						<input name="img" type="file" size="20" class="" maxlength="128"/>
					</dd>				  
				</dl>
				<dl class="nowrap" >
					<dt>页面主商品图(广告)：</dt>
					<dd>
						<input name="imgAd" type="file" size="20" class="" maxlength="128"/>
					</dd>				  
				</dl>
				<dl >
					<dt>页面主商品图广告路径：</dt>
					<dd>
						<input name="lvProduct.pimageAd" type="text" value="${lvProduct.pimageAd }" size="30" maxlength="128"/>
					</dd>				  
				</dl>
				<dl>
					<dt>商品价格：</dt>
					<dd>
						<input name="lvProduct.price" type="text" size="30" class="required numberNew" maxlength="9" value="${lvProduct.price }"/>
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						<input name="lvProduct.orderId" type="text" size="30" class="required number"  maxlength="11" value="${lvProduct.orderId }"/>
					</dd>				  
				</dl>
				
					<dl>
					<dt>是否启动活动：</dt>
					<dd>
					    <select name="lvProduct.isActivity" onchange="validateActivityOrLadder()" id="activity">
					     <option value="">==请选择==</option>
					     <option value="1" <c:if test="${lvProduct.isActivity==1 }">selected="selected"</c:if>>是</option>
					     <option value="0" <c:if test="${lvProduct.isActivity==0 }">selected="selected"</c:if>>否</option>
					    </select><span id="eInfo"></span>
					</dd>				  
				</dl>
				<dl>
					<dt>上下架标示：</dt>
					<dd>
					<select name="lvProduct.isSupport" class="required">
					     <option value="">==请选择==</option>
					     <option value="1" <c:if test="${lvProduct.isSupport==1 }">selected="selected"</c:if>>上架</option>
					     <option value="0" <c:if test="${lvProduct.isSupport==0 }">selected="selected"</c:if>>下架</option>
					    </select>
					</dd>				  
				</dl>
				<dl>
					<dt>是否启用阶梯价：</dt>
					<dd>
					<select name="lvProduct.isLadder" onchange="validateActivityOrLadder()" id="ladder">
					     <option  value="">==请选择==</option>
					      <option value="1" <c:if test="${lvProduct.isLadder==1 }">selected="selected"</c:if>>是</option>
					      <option value="0" <c:if test="${lvProduct.isLadder==0 }">selected="selected"</c:if>>否</option>
					    </select><span id="aInfo"></span>
					</dd>				  
				</dl>
				<!-- 
				<dl>
					<dt>是否启用优惠券：</dt>
					<dd>
					<select name="lvProduct.isPreferences" >
					     <option value="">==请选择==</option>
					      <option value="1" <c:if test="${lvProduct.isPreferences==1 }">selected="selected"</c:if>>是</option>
					      <option value="0" <c:if test="${lvProduct.isPreferences==0 }">selected="selected"</c:if>>否</option>
					    </select>
					</dd>				  
				</dl>
				 -->
				<dl>
					<dt>是否设置为推荐商品：</dt>
					<dd>
					<select name="lvProduct.isCommend">
					     <option value="">==请选择==</option>
					      <option value="1" <c:if test="${lvProduct.isCommend==1 }">selected="selected"</c:if>>是</option>
					      <option value="0" <c:if test="${lvProduct.isCommend==0 }">selected="selected"</c:if>>否</option>
					    </select>
					</dd>				  
				</dl>

				
				<script type="text/javascript">
							 function showSetMeal(){
								var flag=$("#mealId").find("option:selected").val();
								if(flag!=null&&flag.length>0){
									if(flag==1){
									   $("#setMealFlag").show();
									   $("#mealId").attr("class","required");
									   $("#pcode").attr("class","");//套餐无商品pcode
									}else if(flag==0){
									   $("#setMealFlag").hide();
									   $("#mealId").attr("class","");
									   $("#pcode").attr("class","required");
									}
								}else{
								    $("#setMealFlag").hide();
								    $("#pcode").attr("class","required");
								}
								
							 }
				</script>
				
				
				
				<dl class="nowrap">
					<dt>商品描述：</dt>
					<dd>
						<textarea rows="5" cols="100" name="lvProduct.description">${lvProduct.description }</textarea>
					</dd>				  
				</dl>
				<div class="divider"></div>
				<dl class="nowrap">
					<dt>公共商品库code：</dt>
					<dd>
					    ${lvProduct.pubProductCode }
					</dd>				  
				</dl>
				<!-- 判断为套餐动态显示套餐选择的商品列表 -->
				<div id="setMealFlag" style="display:none">
				    <dl class="nowrap">
					<dt>
						选择套餐商品：
					</dt>
					<dd>
						<select id="product">
						 <option value="">==请选择==</option>
						 <c:foreach items="${productList}" var="product">
						  <option value="${product.code }">${product.productName }</option>
						 </c:foreach>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="添加商品" id="but">
					</dd>
				</dl>			
				<hr width="100%"><br/>    
				<table class="table" border="1" width="80%"  >
				   <thead>
				   <tr>
						<th  orderField="id" class="desc">ID</th>
						<th style="display:none">商品名称</th>
						<th >商品名称</th>
						<th >商品数量</th>
						<th >币种</th>
						<th >操作</th>
				   </tr>
				   </thead>
				   <tbody id="tab">
				   <c:foreach items="${lvProduct.packageList}" var="lp" varStatus="index">
				   <tr id="${index.index }">
                                <td >${index.index }</td>
                                <td style="display:none"><input type="hidden" name="lvProduct.packageList[${index.index }].productCode"  value="${lp.productCode }"></td>
							    <td >
								    <c:foreach items="${productList}" var="product">
								     <c:if test="${product.code==lp.productCode}">
								     ${product.productName }
								     </c:if>
								    </c:foreach>
							    </td>
							    <td ><input type="text" name="lvProduct.packageList[${index.index }].pnum"  value="${lp.pnum }" class="required digits"></td>
							    <td >USD</td>
							    <td ><span><a href='#' onclick="deltr(${index.index })")">删除</a></span></td>
					</tr>
					</c:foreach>
				   </tbody>
			   </table>
				</div>
		</div>
	
				
		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
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
<script type="text/javascript">
<!--
$(document).ready(function(){
	var type=$("#mealId").val();
    if(type==1){
    	$("#selPubProduct").hide();
        $("#selPubPackage").show();
	}else if(type==0){
		$("#selPubProduct").show();
        $("#selPubPackage").hide();
	}
})
//-->
</script>
