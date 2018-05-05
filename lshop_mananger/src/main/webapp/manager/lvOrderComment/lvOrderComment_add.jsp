<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>
//根据店铺编号查询对应店铺的产品信息
function changeStore(){
	var storeId=$("#storeId").find("option:selected")
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
		        $("#product option").remove();
		        $("#product").append("<option value=''>==请选择==</option>");
		        var listTmp=data.listTmp;  	
				for(var k=0;k<listTmp.length;k++){ 
					var productName = listTmp[k].productName; 
					var productCode = listTmp[k].productCode; 
					$("#product").append("<option value="+productCode+">"+productName+"</option>");
				 }
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
	<form method="post"
			action="lvOrderCommentAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" name="lvProductComment.isCheck" value="1">
  		    <div class="pageFormContent" layoutH="56">
				
				<dl>
				<dt>订单编号：</dt>
					<dd>
						<input name="lvProductComment.oid" type="text" size="32" maxlength="32" class="required" />
					</dd>				  
				</dl>
				<dl>
				<dt>用户昵称：</dt>
					<dd>
						<input name="lvProductComment.nickname" type="text" size="32" maxlength="32" class="required" />
					</dd>				  
				</dl>
				<dl >
					<dt>所属店铺：</dt>
					<dd>
						<select name="lvProductComment.storeId" class="required" id="storeId" onchange="changeStore()">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						 </select>
					</dd>				  
				</dl>
				<dl>
				<dt>产品名称：</dt>
					<dd>
                        <select id="product" name="lvProductComment.productCode">
						 <option value="">==请选择==</option>
						 <c:foreach items="${productList}" var="product">
						  <option value="${product.code }">${product.productName }</option>
						 </c:foreach>
						</select>
					</dd>				  
				</dl>
			
				<dl class="nowrap">
				<dt>评论内容：</dt>
					<dd>
                        <textarea  name="lvProductComment.content" rows="5" cols="80">${lvProductComment.content }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
				<dt>评论图片：</dt>
					<dd>
                       <input type="file" name="img">
					</dd>				  
				</dl>
				<dl>
				<dt>评分：</dt>
					<dd>
                        <input name="lvProductComment.score" type="text" size="32" class="required digits" max="5" min="1"  />
					</dd>				  
				</dl>
                <dl>
                 <dt>评论时间：</dt>
					<dd>
                        <input name="lvProductComment.createTime" type="text" size="32" class="required date" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
				<dt>是否审核：</dt>
					<dd>
                        <select name="lvProductComment.isCheck" disabled="disabled">
						<option value="1">已审核</option>
						</select>
					</dd>				  
				</dl>
				
				<dl>
				<dt>订单所在国家：</dt>
					<dd>
                        <select class="required"  name="lvProductComment.contryId" class="required">
						<option value="">请选择</option>
						<s:iterator value="#request.areaList"  id="c"><option value="${c.id}">${c.nametw}</option></s:iterator>
						</select>
					</dd>				  
				</dl>
				<dl>
				<dt>产品单价：</dt>
					<dd>
                        <input name="lvProductComment.oprice" type="text" size="32" maxlength="32"  />
					</dd>				  
				</dl>
				<dl>
				<dt>产品数量：</dt>
					<dd>
                        <input name="lvProductComment.pnum" type="text" size="32" maxlength="32"  class="required"/>
					</dd>				  
				</dl>
				<dl>
				<dt>币种：</dt>
					<dd>
                        <input name="lvProductComment.currency" type="text" size="32" maxlength="32"  />
					</dd>				  
				</dl>
				<dl>
				<dt>排序：</dt>
					<dd>
                        <input name="lvProductComment.sortId" type="text" size="32" maxlength="32"  />
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
