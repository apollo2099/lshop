<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
function delDetails(flag){
	$("#"+flag).remove();
}
</script>


<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPubPackageAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvPubPackage.id" type="hidden" size="30" value="${lvPubPackage.id}"/>
			<input name="lvPubPackage.modifyUserId" type="hidden" size="30" value="${lvPubPackage.modifyUserId}"/>
			<input name="lvPubPackage.code" type="hidden" size="30" value="${lvPubPackage.code}"/>
			<input name="lvPubPackage.orderValue" type="hidden" size="30" value="${lvPubPackage.orderValue}"/>
			<input name="lvPubPackage.status" type="hidden" size="30" value="${lvPubPackage.status}"/>
			<input name="lvPubPackage.oldPackageName" type="hidden" size="30" value="${lvPubPackage.packageName}"/>
			
				<!-- 生成表单 -->
						
						<p>
							<label>套餐名称：</label>
							<input name="lvPubPackage.packageName" type="text" size="30" maxlength="64" value="${lvPubPackage.packageName}" class="required"/>
						</p>
						<div class="divider"></div>
					<div>
						<dl class="nowrap">
							<dt>商品信息：</dt>
							<dd>
								<a href="lvPubProductAction!selectMultipleProduct.action?json.navTabId=${json.navTabId}"
									style="color: blue;" target="dialog" lookupGroup="fLookup"
									width="850" height="600" title="添加产品" mask="true"><input
									type="button" value="添加商品" id="but"></a>
							</dd>
						</dl>
						<table width="100%" border="1" class="table">
							<thead>
								<tr>
									<th width="5%">ID</th>
									<th width="25%">商品名称</th>
									<th width="10%">商品型号</th>
									<th width="30%">商务对接code</th>
									<th width='20%'>商品数量</th>
									<th width='10%'>操作</th>
								</tr>
							</thead>
							<tbody id="tab">
							    <c:foreach items="${packageDetailsList }" var="item" varStatus="index">
							    <tr id="${index.count }">
							   		<td width="5%">${item.id } <input type="hidden" name="lvPubPackage.detailsList[${index.count }].id" value="${item.id }"/></td>
							   		<c:foreach items="${pubProductList }" var="product">
							   		  <c:if test="${item.pubProductCode==product.code }">
							   		     <td width="25%">${product.productName }</td>
									     <td width="10%">${product.productModel }</td>
									     <td width="30%">${product.pcode }</td>
							   		  </c:if>
							   		</c:foreach>
									<td width='20%'><input type="text" name="lvPubPackage.detailsList[${index.count }].productNum" value="${item.productNum }" maxlength="10" size="10" min="1" class="required digits"></td>
									<td width='10%'><span style="cursor:pointer" onclick="delDetails(${index.count })">删除</span></td>
							   </tr>
							  </c:foreach>
							</tbody>
						</table>
					</div>

	            <div class="divider"></div>
				<p>
							<label>创建时间：</label>
								 <input type="text" name="lvPubPackage.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="30"
                        value="<s:date name="lvPubPackage.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
								 <input type="text" name="lvPubPackage.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="30"
                        value="<s:date name="lvPubPackage.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvPubPackage.modifyUserName" type="text" size="30" readonly="readonly" value="${lvPubPackage.modifyUserName}"/>
						</p>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>