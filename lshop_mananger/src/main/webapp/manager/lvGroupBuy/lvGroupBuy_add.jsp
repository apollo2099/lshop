<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script>
//根据店铺编号查询对应店铺的产品信息
function changeStore(){
	var storeId=$("#storeIdEdit").find("option:selected")
	var storeVal = storeId.val();
    $.ajax({
		  type: "POST",
		  url: "lvProductAction!toAllProduct.action",
		  data: "lvProduct.storeId="+storeVal,
		  dataType:"json",
		  success: function(data){
		     //删除添加列表中的产品信息
		     $("#tab tr").remove();
		     if(data!=null){
		        $("#productEdit option").remove();
		        $("#productEdit").append("<option value=''>==请选择产品==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productCode = listTmp[k].productCode; 
					$("#productEdit").append("<option value="+productCode+">"+productName+"</option>");
				 }
		     }else{
		        $("#productEdit option").remove();
		        $("#productEdit").append("<option value=''>==请选择产品==</option>");

		     }
		  }
	});
}
</script>


<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvGroupBuyAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
  		    <div class="pageFormContent" layoutH="56">
			     
			     <dl class="nowrap">
				 <dt>团购标题：</dt>
				    <dd>
				     <input name="lvGroupBuy.title" type="text" size="108" maxlength="128" class="required" />
				    </dd>
				 </dl>
				 <dl>
					<dt>所属店铺：</dt>
					<dd>
						<select name="lvGroupBuy.storeId" class="required" id="storeIdEdit" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						 </select>
					</dd>				  
				</dl>
			     <dl>
					<dt>团购产品：</dt>
					<dd>
				     <select name="lvGroupBuy.productCode" class="required" style="width:200px;" id="productEdit"> 
						<option value="">
							==请选择产品==
						</option>
				     </select>
					</dd>				  
				</dl>
				<dl >
				<dt>原价：</dt>
					<dd>
					    <input name="lvGroupBuy.primeCost" type="text" size="32" maxlength="11"  class="required numberNew"/>
					</dd>				  
				</dl>
				<dl>
				<dt>折扣：</dt>
					<dd>
                    <input name="lvGroupBuy.discount" type="text" size="32" maxlength="3"  class="required digits" min="1" max="9"/>
					</dd>				  
				</dl>
				<dl>
				<dt>现价：</dt>
					<dd>
                    <input name="lvGroupBuy.presentPrice" type="text" size="32" maxlength="11"  class="required numberNew"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最低成团人数：</dt>
					<dd>
                    <input name="lvGroupBuy.groupNum" type="text" size="32" maxlength="11"  class="required digits" min="1"/>
					</dd>				  
				</dl>
				<dl>
				<dt>已购买人数：</dt>
					<dd>
                    <input name="lvGroupBuy.purchasedNum" type="text" size="32" maxlength="11"  class="digits"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动开始时间：</dt>
					<dd>
                    <input name="lvGroupBuy.startTime" type="text" size="32" maxlength="32"  class="required date" format="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                    <input name="lvGroupBuy.endTime" type="text" size="32" maxlength="32"  class="required date" format="yyyy-MM-dd HH:mm:ss" />
					</dd>				  
				</dl>
				<dl >
				<dt>团购图片：</dt>
					<dd>
                    <input name="img" type="file" size="20" class=""/>
					</dd>				  
				</dl>
				<dl>
				<dt>激活活动标志：</dt>
					<dd>
                    <select name="lvGroupBuy.status" class="required">
                     <option value="">==请选择==</option>
                     <option value="1">启用</option>
                     <option value="-1">停用</option>
                    </select>
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
