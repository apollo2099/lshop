<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvGroupBuyAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" name="lvGroupBuy.id" value="${lvGroupBuy.id }">
		    <input type="hidden" name="lvGroupBuy.storeId" value="${lvGroupBuy.storeId }">
		    <input type="hidden" name="lvGroupBuy.code" value="${lvGroupBuy.code }">
		    <input type="hidden" name="lvGroupBuy.gimage" value="${lvGroupBuy.gimage }">
  		    <div class="pageFormContent" layoutH="56">
    
			     <dl class="nowrap">
				 <dt>团购标题：</dt>
				    <dd>
				     <input name="lvGroupBuy.title" type="text" size="108" maxlength="128" class="required"  value="${lvGroupBuy.title }"/>
				    </dd>
				 </dl>
			     <dl>
					<dt>团购产品：</dt>
					<dd>
				     <select name="lvGroupBuy.productCode" class="required" style="width:200px;"> 
					<option value="">
						==请选择产品==
					</option>
				         <s:iterator value="#request.productList" id="product">
							<option value="${product.code}"
							 <c:if test="${lvGroupBuy.productCode==product.code }">selected="selected"</c:if>>
								${product.productName }
							</option>
						</s:iterator>
				  </select>
					</dd>				  
				</dl>
				
				
				<dl >
				<dt>原价：</dt>
					<dd>
					    <input name="lvGroupBuy.primeCost" type="text" size="32" maxlength="11"  class="required numberNew"  value="${lvGroupBuy.primeCost }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>折扣：</dt>
					<dd>
                    <input name="lvGroupBuy.discount" type="text" size="32" maxlength="3"  value="${lvGroupBuy.discount }" class="required digits" min="1" max="9"/>
					</dd>				  
				</dl>
				<dl>
				<dt>现价：</dt>
					<dd>
                    <input name="lvGroupBuy.presentPrice" type="text" size="32" maxlength="11"  class="required numberNew"  value="${lvGroupBuy.presentPrice }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最低成团人数：</dt>
					<dd>
                    <input name="lvGroupBuy.groupNum" type="text" size="32" maxlength="11"  class="required digits" min="1" value="${lvGroupBuy.groupNum }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>已购买人数：</dt>
					<dd>
                    <input name="lvGroupBuy.purchasedNum" type="text" size="32" maxlength="11"  class="digits"  value="${lvGroupBuy.purchasedNum }"/>
					</dd>				  
				</dl>
				<dl>
				<dt>活动开始时间：</dt>
					<dd>
                    <input name="lvGroupBuy.startTime" type="text" size="32" maxlength="32"  class="required date" format="yyyy-MM-dd HH:mm:ss" 
                    value="<s:date name="lvGroupBuy.startTime" format="yyyy-MM-dd HH:mm:ss"/>" />
					</dd>				  
				</dl>
				<dl>
				<dt>活动结束时间：</dt>
					<dd>
                    <input name="lvGroupBuy.endTime" type="text" size="32" maxlength="32"  class="required date" format="yyyy-MM-dd HH:mm:ss"
                    value="<s:date name="lvGroupBuy.endTime" format="yyyy-MM-dd HH:mm:ss"/>" />
					</dd>				  
				</dl>
				<dl style="height: 60px;">
				<dt>团购图片：</dt>
					<dd>
                    <input name="img" type="file" size="20" class=""/>
					</dd>				  
				</dl>
				<dl style="height: 60px;">
				<dt></dt>
					<dd>
                       	<c:if test="${lvGroupBuy.gimage!=''}"><img src="${lvGroupBuy.gimage}" width="60"  height="60"/></c:if>
					</dd>				  
				</dl>
				<dl>
				<dt>激活活动标志：</dt>
					<dd>
                    <select name="lvGroupBuy.status" class="required">
                     <option value="">==请选择==</option>
                     <option value="1"  <c:if test="${lvGroupBuy.status==1}">selected="selected"</c:if>>启用</option>
                     <option value="-1" <c:if test="${lvGroupBuy.status==-1}">selected="selected"</c:if>>停用</option>
                    </select>
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvGroupBuy.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvGroupBuy.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvGroupBuy.modifyUserName"  readonly="true" value="${lvGroupBuy.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvGroupBuy.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvGroupBuy.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
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
