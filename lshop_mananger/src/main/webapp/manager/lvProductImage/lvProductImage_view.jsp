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
					<dt>产品名称：</dt>
					<dd>
						 <s:iterator value="#request.productList" id="product">
							    <c:if test="${lvProductImage.productCode==product.code }">${product.productName }</c:if>
						</s:iterator>
					</dd>				  
				</dl>

				<dl  class="nowrap">
					<dt>产品图片：</dt>
					<dd>
						<c:if test="${lvProductImage.productImage!=''}"><img src="${lvProductImage.productImage}" width="60"  height="60"/></c:if>
					
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						${lvProductImage.sortId }
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvProductImage.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改人：</dt>
					<dd>
						${lvProductImage.modifyUserName }
					</dd>				  
				</dl>
					<dl>
					<dt>最后修改时间：</dt>
					<dd>
						<s:date name="lvProductImage.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
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
