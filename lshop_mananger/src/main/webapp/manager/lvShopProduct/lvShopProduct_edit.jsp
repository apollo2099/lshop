<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopProductAction!edit.action?json.navTabId=${json.navTabId}" 
		 class="pageForm required-validate" 
		 onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
			<input name="lvShopProduct.id" type="hidden" size="30" value="${lvShopProduct.id}"/>
			<input name="lvShopProduct.productCode" type="hidden" size="30" value="${lvShopProduct.productCode}"/>
			<input name="lvShopProduct.subjectType" type="hidden" size="30" value="${lvShopProduct.subjectType}"/>
			<input name="lvShopProduct.storeId" type="hidden" size="30" value="${lvShopProduct.storeId}"/>
			<input name="lvShopProduct.storeFlag" type="hidden" size="30" value="${lvShopProduct.storeFlag}"/>
			<input name="lvShopProduct.code" type="hidden" size="30" value="${lvShopProduct.code}"/>
			<input name="lvShopProduct.modifyUserId" type="hidden" size="30" value="${lvShopProduct.modifyUserId}"/>
			<input name="lvShopProduct.pimage" type="hidden" size="30" value="${lvShopProduct.pimage}"/>
			<input name="lvShopProduct.pimageAd" type="hidden" size="30" value="${lvShopProduct.pimageAd}"/>
				<!-- 生成表单 -->
						<p>
							<label>商品名称：</label>
							<input name="lvShopProduct.productName" type="text" size="30" maxlength="150" value="<s:property escapeHtml="true" value="lvShopProduct.productName"/>" class="required"/>
						</p>
						<p>
							<label>价格：</label>
							<input type="text" size="30" value="${lvProduct.price }" readonly="readonly"/>
						</p>
						<p>
							<label>产品图片：</label>
							<input name="img" type="file"  class="true"/> 
						</p>
						<p>
							<label>产品广告图：</label>
							<input name="imgAd" type="file"  class="true"/> 
						</p>
						<p>
							<label>链接地址：</label>
							<input name="lvShopProduct.url" type="text" size="30" maxlength="150" value="${lvShopProduct.url}" class="required"/>
						</p>
	
						<p>
							<label>排序值：</label>
							<input name="lvShopProduct.orderValue" type="text" size="30" maxlength="10" value="${lvShopProduct.orderValue}" class="required digits"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvShopProduct.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopProduct.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvShopProduct.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopProduct.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>

						<p>
							<label>修改人名称：</label>
							<input name="lvShopProduct.modifyUserName" type="text" size="30" value="${lvShopProduct.modifyUserName}" readonly="readonly"/>
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