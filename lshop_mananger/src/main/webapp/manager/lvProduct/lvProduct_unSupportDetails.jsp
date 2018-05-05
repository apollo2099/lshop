<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		<div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
					<dt>产品id：</dt>
					<dd>
						${lvProduct.id}
					</dd>				  
				</dl>
				<dl>
					<dt>产品名称：</dt>
					<dd>
						${lvProduct.productName}
					</dd>				  
				</dl>
					<dl>
					<dt>产品型号：</dt>
					<dd>
						${lvProduct.pmodel}
					</dd>				  
				</dl>
					<dl>
					<dt>产品类别：</dt>
					<dd>
					<c:foreach items="${typeList}" var="t">
					  <c:if test="${t.code==lvProduct.ptype}">${t.typeName }</c:if>
					</c:foreach>
					</dd>				  
				</dl>
				

					<dl>
					<dt>上下架标示：</dt>
					<dd>
						<c:if test="${lvProduct.isSupport ==1}">上架</c:if>
						<c:if test="${lvProduct.isSupport ==0}">下架</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>商城商品分类：</dt>
					<dd>
						<c:foreach items="${shopProductTypeList}" var="t">
						  <c:if test="${t.code==lvProduct.shopProductType}">${t.typeName}</c:if>
						</c:foreach>
					</dd>				  
				</dl>
				<dl  style="height: 60px;">
					<dt>产品详情页面主产品图：</dt>
					<dd>
						<s:if test="lvProduct.pimage!=''"><img src="${lvProduct.pimage}" width="60"  height="60"/></s:if>
						<s:else>无</s:else>
					</dd>				  
				</dl>
				<dl  style="height: 60px;">
					<dt>页面主产品图(广告)：</dt>
					<dd>
						<s:if test="lvProduct.pimageAd!=''"><img src="${lvProduct.pimageAd}" width="60"  height="60"/></s:if>
						<s:else>无</s:else>
					</dd>				  
				</dl>
				<dl>
					<dt>产品价格(人民币)：</dt>
					<dd>
						${lvProduct.priceRmb}RMB
					</dd>				  
				</dl>
				<dl>
					<dt>产品价格(美元)：</dt>
					<dd>
						${lvProduct.priceUsd}USD
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>产品描述：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvProduct.description">${lvProduct.description }</textarea>
					</dd>				  
				</dl>

				<dl class="nowrap">
					<dt>下架说明：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvProduct.description">${lvProduct.unsupportRemark }</textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>下架时间：</dt>
					<dd>
						<s:date name="lvProduct.unsupportTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvProduct.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>修改人：</dt>
					<dd>
						${lvProduct.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>修改时间：</dt>
					<dd>
						<s:date name="lvProduct.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
