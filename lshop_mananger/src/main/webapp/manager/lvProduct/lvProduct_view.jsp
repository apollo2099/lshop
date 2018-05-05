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
					<dt>商品id：</dt>
					<dd>
						${lvProduct.id}
					</dd>				  
				</dl>
				<dl>
					<dt>商品名称：</dt>
					<dd>
						${lvProduct.productName}
					</dd>				  
				</dl>
					<dl>
					<dt>商品型号：</dt>
					<dd>
						${lvProduct.pmodel}
					</dd>				  
				</dl>
					<dl>
					<dt>商品类别：</dt>
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
					<dt>商品详情页面主商品图：</dt>
					<dd>
						<s:if test="lvProduct.pimage!=''"><img src="${lvProduct.pimage}" width="60"  height="60"/></s:if>
						<s:else>无</s:else>
					</dd>				  
				</dl>
				<dl  style="height: 60px;">
					<dt>页面主商品图(广告)：</dt>
					<dd>
						<s:if test="lvProduct.pimageAd!=''"><img src="${lvProduct.pimageAd}" width="60"  height="60"/></s:if>
						<s:else>无</s:else>
					</dd>				  
				</dl>
				<dl>
					<dt>商品价格：</dt>
					<dd>
						${lvProduct.price}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>商品描述：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvProduct.description">${lvProduct.description }</textarea>
					</dd>				  
				</dl>

				
				<dl>
					<dt>是否启动活动：</dt>
					<dd>
						<c:if test="${lvProduct.isActivity ==1}">是</c:if>
						<c:if test="${lvProduct.isActivity ==0}">否</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>是否启用阶梯价：</dt>
					<dd>
						<c:if test="${lvProduct.isLadder ==1}">是</c:if>
						<c:if test="${lvProduct.isLadder ==0}">否</c:if>
					</dd>				  
				</dl>
				<!-- 
				<dl>
					<dt>是否启用优惠券：</dt>
					<dd>
						<c:if test="${lvProduct.isPreferences ==1}">是</c:if>
						<c:if test="${lvProduct.isPreferences ==0}">否</c:if>
					</dd>				  
				</dl>
				 -->
				<dl>
					<dt>是否为套餐(：</dt>
					<dd>
						<c:if test="${lvProduct.isSetMeal ==1}">是</c:if>
						<c:if test="${lvProduct.isSetMeal ==0}">否</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>是否设置为推荐商品：</dt>
					<dd>
						<c:if test="${lvProduct.isCommend ==1}">是</c:if>
						<c:if test="${lvProduct.isCommend ==0}">否</c:if>
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
				<dl>
					<dt>公共商品code：</dt>
					<dd>
					   ${lvProduct.pubProductCode }
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
