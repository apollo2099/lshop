<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<script>
function selectCounponProduct(){
	var cProduct=$("#cProduct").find("option:selected");
	if(parseInt(cProduct.val())==1){
       $("#productType").show();
	   $("#productTypeList").show();
	   $("#product").hide();
	   $("#productList").hide();
	}else if(parseInt(cProduct.val())==2){
       $("#productType").hide();
	   $("#productTypeList").hide();
	   $("#product").show();
	   $("#productList").show();
	}
}
//根据币种变更，修改查询返回连接
function changeSelectURL(){
	var currency=$("#currency").find("option:selected");
	$("#selProductType").attr("href",$("#txtProductType").val()+"?currency="+currency.val());
	$("#selProduct").attr("href",$("#txtProduct").val()+"?currency="+currency.val());
}

function changeReuse(){
	var reuseVal=$("#reuseSel").find("option:selected").val();
	if(reuseVal==1){
		$("#reuseNumDiv").show();
	}else if(reuseVal==0){
		$("#reuseNumDiv").hide();
	}
}

//删除对应表格行数据
function deltr(index)
{
  $("tr[id='"+index+"']").remove();
}


</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvCouponTypeAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input id="txtProductType" type="hidden" value="lvCouponTypeAction!selectMultipleProductType.action"/>
			<input id="txtProduct" type="hidden" value="lvCouponTypeAction!selectMultipleProduct.action"/>
				<!-- 生成表单 -->
				<dl class="nowrap">
					<dt>优惠券名称：</dt>
					<dd>
						<input name="lvCouponType.name" type="text" size="30" maxlength="128" class="required"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
				    <dt>优惠券金额：</dt>
				    <dd>
				     <input name="lvCouponType.amount" type="text" size="30" maxlength="5" class="required numberAPoint"/>
				    </dd>
				</dl>
				
				<dl class="nowrap">
					<dt>生成数量：</dt>
					<dd>
						<input name="lvCouponType.total" type="text" size="30" maxlength="4" class="required digitsNoZore"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>使用有效期：</dt>
					<dd>
                       <input name="lvCouponType.startTime" class="required textInput date" readonly="readonly" format="yyyy-MM-dd" type="text" size="30"/>至
					</dd>
				</dl>	
				<dl class="nowrap">
					<dt></dt>
					<dd>
                    <input name="lvCouponType.endTime" class="required textInput date" readonly="readonly" format="yyyy-MM-dd" type="text" size="30"/>
					</dd>	
				</dl >
				<dl class="nowrap">
					<dt>指定币种：</dt>
					<dd>
						<select name="lvCouponType.currency" class="required" onchange="changeSelectURL()" id="currency">
					      <option value="">==请选择==</option>
					      <option value="USD">USD</option>
					      <option value="CNY">CNY</option>
					    </select>
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>指定商品或者类型：</dt>
					<dd>
						<select name="lvCouponType.relationType" class="required" onchange="selectCounponProduct()" id="cProduct">
					      <option value="">==请选择==</option>
					      <option value="1">选择商品类别</option>
					      <option value="2">选择特定商品</option>
					    </select>
                <div style="display:none" id="productType">
			    <input name="org3.id" value="" type="hidden">
				<a class="btnLook" id="selProductType" href="lvCouponTypeAction!selectMultipleProductType.action" lookupGroup="org3" width="800" height="650">查找带回</a>
				</div>
                <div style="display:none" id="product">
				<a class="btnLook" id="selProduct" href="lvCouponTypeAction!selectMultipleProduct.action" lookupGroup="org4" width="800" height="650">查找带回</a>
				</div>
					</dd>				  
				</dl >

				 <div id="productList" style="display:none">
                 <dl class="nowrap">
					<dt>指定商品：</dt>
					<dd>
                        <table border="1"  style="width: 80%">
                        <thead>
						<tr>
						<th>所属关系</th>
						<th>商品信息</th>
						<th>商品价格</th>
						<th>创建时间</th>
						<th>操作</th>
						<tr>
						</thead>
						<tbody id="ptab">
						<tr>
						<tr>
						</tbody>
						</table>
					</dd>				  
				</dl>
				</div>

				 <div id="productTypeList" style="display:none">
				<dl class="nowrap">
									<dt>指定商品类别：</dt>
									<dd>
										<table border="1" style="width: 80%">
										<thead>
										<tr>
										<th>所属关系</th>
										<th>商品类别</th>
										<th>操作</th>
										</tr>
										</thead>
										<tbody id="tab">
				
										</tbody>
										</table>
									</dd>				  
								</dl>
				</div>

                <dl class="nowrap">
				    <dt>指定金额：</dt>
				    <dd>
				     <input name="lvCouponType.limitAmount" type="text" size="30" maxlength="5" class="required numberAPoint"/>
				    </dd>
				</dl>
					<dl class="nowrap">
					<dt>重复使用：</dt>
					<dd>
						<select name="lvCouponType.reuse" class="required" id="reuseSel" onchange="changeReuse()">
					      <option value="">==请选择==</option>
					      <option value="1">是</option>
					      <option value="0">否</option>
					    </select>
					</dd>				  
				</dl>
				<dl class="nowrap" id="reuseNumDiv" style="display:none">
					<dt>重复使用次数：</dt>
					<dd>
						<input name="lvCouponType.reuseNum" type="text" size="30" maxlength="4" class="digits"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>是否启用：</dt>
					<dd>
						<select name="lvCouponType.status" class="required">
					      <option value="">==请选择==</option>
					      <option value="1" selected="selected">启用</option>
					      <option value="0">停用</option>
					    </select>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>描述信息：</dt>
					<dd>
				        <textarea name="lvCouponType.remark" rows="5" cols="60" maxlength="200"></textarea>
					</dd>				  
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>