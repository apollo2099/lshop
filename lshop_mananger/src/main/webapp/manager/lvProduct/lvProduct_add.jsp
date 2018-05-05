<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
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

//根据店铺编号查询对应店铺的商品信息
function changeStore(){
	var storeVal = $("#storeIdEdit").find("option:selected").val();
    $.ajax({
		  type: "POST",
		  url: "lvProductAction!toProductByStoreId.action",
		  data: "lvProduct.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     if(data!=null){
		        $("#product option").remove();
		        $("#productType option").remove();
		        $("#shopType option").remove();
		        $("#product").append("<option value=''>==请选择==</option>");
		        $("#productType").append("<option value=''>==请选择==</option>");
		        $("#shopType").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productCode = listTmp[k].productCode; 
					$("#product").append("<option value="+productCode+">"+productName+"</option>");
				 }
				 var listTypeTmp=data.listTypeTmp;
				 for(var num=0;num<listTypeTmp.length;num++){ 
					var typeCode = listTypeTmp[num].typeCode; 
					var typeName = listTypeTmp[num].typeName; 
					$("#productType").append("<option value="+typeCode+">"+typeName+"</option>");
				 }
				 var shopProductTypeList=data.shopProductTypeList;
				 for(var n=0;n<shopProductTypeList.length;n++){ 
					var shopTypeCode = shopProductTypeList[n].shopTypeCode; 
					var shopTypeName = shopProductTypeList[n].shopTypeName; 
					$("#shopType").append("<option value="+shopTypeCode+">"+shopTypeName+"</option>");
				 }
		     }else{
		        $("#product option").remove();
		        $("#productType option").remove();
		        $("#shopType option").remove();
		        $("#product").append("<option value=''>==请选择==</option>");
		        $("#productType").append("<option value=''>==请选择==</option>");
		        $("#shopType").append("<option value=''>==请选择==</option>");

		     }
		  }
	});
}
</script>

<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvProductAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
		    onsubmit="return iframeCallbackForm(this, navTabAjaxDone)" enctype="multipart/form-data">
  		<div class="pageFormContent" layoutH="56">

				<dl>
					<dt>所属店铺：</dt>
					<dd>
						<select name="lvProduct.storeId" class="required" id="storeIdEdit" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						 </select>
					</dd>				  
				</dl>
					<dl>
					<dt>商品类别：</dt>
					<dd>
						<select  name="lvProduct.ptype" class="required" id="productType">
						<option value="">==请选择==</option>
						<s:iterator value="#request.typeList" id="t">
						<option value="${t.code }">${t.typeName}</option> 
						</s:iterator>
						</select>
					</dd>				  
				</dl>
					<dl>
					<dt>商城商品分类：</dt>
					<dd>
						<select  name="lvProduct.shopProductType" class="required" id="shopType">
						<option value="">==请选择==</option>
						<s:iterator value="#request.shopProductTypeList" id="t">
						<option value="${t.code }">${t.typeName}</option> 
						</s:iterator>
						</select>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>是否为套餐(：</dt>
					<dd>
					   <select name="lvProduct.isSetMeal" onchange="changeSetMeal()" id="mealId" class="required">
					      <option value="">==请选择==</option>
					      <option value="1">是</option>
					      <option value="0">否</option>
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
		    
						<input type="hidden" name="lvProduct.pubProductCode" id="pubProductCode">
						<input type="hidden" name="lvProduct.pcode" > 
						<input type="hidden" name="lvProduct.pmodel" >
						<a target="dialog" href="lvPubProductAction!selectSingleProduct.action?json.navTabId=${json.navTabId}" lookupGroup="lvProduct" width="850" height="600" mask="true"><input type="button" value="选择商品"></a>
					</dd>
				</dl>		
				</div>
				
				<div id="selPubPackage" style="display:none">
				    <dl class="nowrap">
					<dt>
						选择套餐：
					</dt>
					<dd>		
						<input type="hidden" name="lvPubPackage.code" id="pubPackageCode">
						<input type="text" name="lvPubPackage.packageName" readonly="readonly">
						<a target="dialog" href="lvPubPackageAction!selectSinglePackage.action?json.navTabId=${json.navTabId}" lookupGroup="lvPubPackage" width="850" height="600" mask="true"><input type="button" value="选择套餐"></a>
					</dd>
				</dl>			
				<hr width="100%"><br/>    
				<table id="tab" border="1" width="80%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="25%">商品名称</th>
						<th width="20%">商品型号</th>
						<th width="30%">商品code</th>
						<th width='20%'>商品数量</th>
				   </tr>
				   </thead>
				   <tbody id="tabDetails">
				   </tbody>
			   </table>
			   </div>
			   
			   <div class="divider"></div>
				<dl class="nowrap">
					<dt>商品名称：</dt>
					<dd>
						<input name="lvProduct.productName" type="text" maxlength="64" class="required" size="100" />
					</dd>				  
				</dl>
				<dl>
					<dt>商品价格：</dt>
					<dd>
						<input name="lvProduct.price" type="text" size="30" class="required numberNew"  maxlength="9"/>
					</dd>				  
				</dl>
				<dl  >
					<dt>商品详情页面主商品图：</dt>
					<dd>
						<input name="img" type="file" size="20" class="" maxlength="128"/>
					</dd>				  
				</dl>
				<dl  >
					<dt>页面主商品图(广告)：</dt>
					<dd>
						<input name="imgAd" type="file" size="20" class="" maxlength="128"/>
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						<input name="lvProduct.orderId" type="text" size="30" class="required number"  maxlength="11"/>
					</dd>				  
				</dl>

				<dl>
					<dt>是否启动活动：</dt>
					<dd>
					    <select name="lvProduct.isActivity" onchange="validateActivityOrLadder()" class="required" id="activity">
					     <option>==请选择==</option>
					     <option value="1">是</option>
					     <option value="0" selected="selected">否</option>
					    </select><span id="eInfo"></span>
					</dd>				  
				</dl>
				<dl>
					<dt>上下架标示：</dt>
					<dd>
					    <select name="lvProduct.isSupport" class="required">
					     <option value="">==请选择==</option>
					     <option value="1" selected="selected">上架</option>
					     <option value="0">下架</option>
					    </select>
					</dd>				  
				</dl>
				<dl>
					<dt>是否启用阶梯价：</dt>
					<dd>
					<select name="lvProduct.isLadder" onchange="validateActivityOrLadder()" class="required" id="ladder">
					      <option value="">==请选择==</option>
					      <option value="1">是</option>
					      <option value="0" selected="selected">否</option>
					    </select><span id="aInfo"></span>
					</dd>				  
				</dl>
				<dl>
					<dt>是否启用优惠券：</dt>
					<dd>
					<select name="lvProduct.isPreferences" class="required">
					      <option value="">==请选择==</option>
					      <option value="1">是</option>
					      <option value="0" selected="selected">否</option>
					    </select>
					</dd>				  
				</dl>
				<dl>
					<dt>是否设置为推荐商品：</dt>
					<dd>
					    <select name="lvProduct.isCommend" class="required">
					      <option value="">==请选择==</option>
					      <option value="1">是</option>
					      <option value="0" selected="selected">否</option>
					    </select>
					</dd>				  
				</dl>				
				<dl class="nowrap">
					<dt>商品描述：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvProduct.description"></textarea>
					</dd>				  
				</dl>
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
