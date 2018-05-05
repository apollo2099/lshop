<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvProductAction!updateProduct.action?json.navTabId=${json.navTabId}" 
		class="pageForm required-validate" 
		onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="lvProduct.id" value="${lvProduct.id }">
		    <input type="hidden" name="lvProduct.storeId" value="${lvProduct.storeId }">
		    <input type="hidden" name="lvProduct.code" value="${lvProduct.code }">
		    <input type="hidden" name="lvProduct.createTime" value="${lvProduct.createTime }">
		    <input type="hidden" name="lvProduct.status" value="${lvProduct.status }">
		    <input type="hidden" name="lvProduct.pimage" value="${lvProduct.pimage }">
		    <input type="hidden" name="lvProduct.pimageAd" value="${lvProduct.pimageAd }">
		    <input type="hidden" name="lvProduct.oldProductName" value="${lvProduct.productName }">
		    <input type="hidden" name="lvProduct.isActivity" value="${lvProduct.isActivity }">
		    <input type="hidden" name="lvProduct.isSupport" value="${lvProduct.isSupport }">
		    <input type="hidden" name="lvProduct.isLadder" value="${lvProduct.isLadder }">
		    <input type="hidden" name="lvProduct.isPreferences" value="${lvProduct.isPreferences }">
		    <input type="hidden" name="lvProduct.isCommend" value="${lvProduct.isCommend }">
		    <input type="hidden" name="lvProduct.isSetMeal" value="${lvProduct.isSetMeal }">
            <input type="hidden" name="lvProduct.priceRmb" value="${lvProduct.priceRmb }">
		    <input type="hidden" name="lvProduct.oldPType" value="${lvProduct.ptype }">
		    <input type="hidden" name="lvProduct.orderId" value="${lvProduct.orderId }">	
		    <input type="hidden" name="lvProduct.pubPackageName" value="${lvProduct.pubPackageName }">	
		    <input type="hidden" name="lvProduct.pubProductCode" value="${lvProduct.pubProductCode }">
		    <!-- 生成表单 -->
						<p>
							<label>商品名称：</label>
							<input name="lvProduct.productName" type="text" size="30" maxlength="64" class="required" value="${lvProduct.productName}" />
						</p>
						<p>
							<label>商务对接code：</label>
							<input name="lvProduct.pcode" type="text" size="30" maxlength="64"  value="${lvProduct.pcode}"/>
						</p>
						<p>
							<label>商品型号：</label>
							<input name="lvProduct.pmodel" type="text" size="30" maxlength="50"  value="${lvProduct.pmodel}"/>
						</p>
						<p>
							<label>产品主产品图：</label>
							<input name="img" type="file" size="20" class="" />
						</p>
						<p>
							<label>产品图(广告)：</label>
							<input name="imgAd" type="file" size="20" class="" />
						</p>
						<p>
							<label>商品价格：</label>
							<input name="lvProduct.price" type="text" size="10" class="required numberNew" maxlength="9" value="${lvProduct.price }"/>
						</p>
						<p>
							<label>产品类别：</label>
						<select  name="lvProduct.ptype" class="required" style="width:200px;">
						<option value=""></option>
						<s:iterator value="#request.typeList" id="t">
						<option value="${t.code }" <s:if test="#t.code==lvProduct.ptype">selected="selected"</s:if>>${t.typeName}</option> 
						</s:iterator>
						</select>
						</p>
						<p>
							<label>商品分类：</label>
							<select name="lvProduct.shopProductType" class="required" style="width:200px;">
							<option value="">==请选择==</option>
							<c:foreach items="${productTypList}" var="type">
							<option value="${type.code }" <c:if test="${lvProduct.shopProductType==type.code }">selected="selected"</c:if>>${type.typeName }</option>
							</c:foreach>
							</select>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvProduct.createTime" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvProduct.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvProduct.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvProduct.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvProduct.modifyUserName" type="text" size="30" value="${lvProduct.modifyUserName}" readonly="readonly"/>
						</p>
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