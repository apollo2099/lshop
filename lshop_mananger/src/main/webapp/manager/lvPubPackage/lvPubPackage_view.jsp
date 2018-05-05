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
					<dt>套餐名称：</dt>
					<dd>
						${lvPubPackage.packageName}
					</dd>				  
				</dl>
				
				<div class="divider"></div>
					<div>
						<dl class="nowrap">
							<dt>商品信息：</dt>
							<dd>
							</dd>
						</dl>
						<table width="100%" border="1" class="table">
							<thead>
								<tr>
									<th width="5%">ID</th>
									<th width="25%">商品名称</th>
									<th width="20%">商品型号</th>
									<th width="30%">商品code</th>
									<th width='20%'>商品数量</th>
								</tr>
							</thead>
							<tbody id="tab">
							  <c:foreach items="${packageDetailsList }" var="item">
							   <tr>
							   		<td width="5%">${item.id } </td>
							   		<c:foreach items="${pubProductList }" var="product">
							   		  <c:if test="${item.pubProductCode==product.code }">
							   		     <td width="25%">${product.productName }</td>
									     <td width="20%">${product.productModel }</td>
									     <td width="30%">${product.pcode }</td>
							   		  </c:if>
							   		</c:foreach>
									<td width='20%'>${item.productNum }</td>
							   </tr>
							  </c:foreach>
							
							</tbody>
						</table>
					</div>

	            <div class="divider"></div>
				
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<s:date name="lvPubPackage.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						<s:date name="lvPubPackage.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvPubPackage.modifyUserName}
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
