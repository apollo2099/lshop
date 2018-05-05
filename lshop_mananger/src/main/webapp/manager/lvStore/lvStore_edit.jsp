<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
//动态刷新返回选择区域参数
function changeStore(){
	var storeVal=$("#storeId").find("option:selected").val();
	if(storeVal!=null&&storeVal.length>0){
	   $(".btnLook").attr("href",$("#tempLookHref").val()+"&parentCode="+storeVal);
	}else{
	   alertMsg.error('请先选择商城关系!');
	}
}

//初始化加载
$(document).ready(function(){
	changeStore();
});

</script>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreMngAction!edit.action?soureFlag=0&json.navTabId=${json.navTabId}" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		    <input type="hidden" name="lvStore.id" value="${lvStore.id}">
		    <input type="hidden" name="lvStore.isdel" value="${lvStore.isdel}">
		    <input type="hidden" name="lvStore.code" value="${lvStore.code}">
		    <input type="hidden" name="lvStore.templetId" value="${lvStore.templetId}">
		    <input type="hidden" name="lvStore.createUserId" value="${lvStore.createUserId}">
		    <input type="hidden" name="lvStore.createUserName" value="${lvStore.createUserName}">
		    <input type="hidden" name="lvStore.createTime" value="<fmt:formatDate value='${lvStore.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
		    <input type="hidden" name="lvStore.description" value="${lvStore.description }">
		    <input type="hidden" name="lvStore.affiche" value="${lvStore.affiche }">
		    <input type="hidden" name="lvStore.logo" value="${lvStore.logo }">
		    <input type="hidden" name="lvStore.thirdPartyShippingMark" value="${lvStore.thirdPartyShippingMark }">
		    <input type="hidden" name="lvStore.isPreferences" value="${lvStore.isPreferences }">
		    <input type="hidden" name="lvStore.number" value="${lvStore.number }">
		    <input type="hidden" name="lvStore.orderValue" value="${lvStore.orderValue }">
		    <input type="hidden" name="lvStore.isTemplet" value="${lvStore.isTemplet }">
		    <input type="hidden" name="lvStore.isHot"  value="${lvStore.isHot }">
		    <input type="hidden" name="lvStore.isCommend"  value="${lvStore.isCommend }">
		    <input type="hidden" name="lvStore.oldName" value="${lvStore.name }">
		    <input type="hidden" name="lvStore.oldStatus" value="${lvStore.status }">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				        <dl >
							<dt>所属商城：</dt>
							<dd>
							    <select name="lvStore.parentCode" class="required" id="storeId" onchange="changeStore()">
								<c:foreach items="${shopList}" var="shop">
								<option <c:if test="${lvStore.parentCode==shop.code}">selected="selected"</c:if> value="${shop.code }">${shop.name }</option>
								</c:foreach>
								</select>
						</dd>
						</dl>
						 <dl >
							<dt>城市：</dt>
							<dd>
						        <input name="lvStore.continentCode" type="hidden" size="30"  value="${lvStore.continentCode }"/>
							    <input name="lvStore.countryCode" type="hidden" size="30"  value="${lvStore.countryCode }"/>
							    <input name="lvStore.cityCode" type="hidden" size="30"  value="${lvStore.cityCode }"/>
							    <input name="lvStore.country" type="hidden" size="30"   readonly="readonly" value="${lvStore.country }"/>
							    <input name="lvStore.countryId" type="hidden" size="30"  class="required" value="${lvStore.countryId }"/>
								<input name="lvStore.city" type="text" size="30" maxlength="10" class="required" readonly="readonly" value="${lvStore.city }"/>
								<a class="btnLook" href="lvStoreAreaAction!selectArea.action?json.navTabId=lvStore_1" lookupGroup="lvStore">查找带回</a>
								<input type="hidden" id="tempLookHref" value="lvStoreAreaAction!selectArea.action?json.navTabId=lvStore_1" >	
							</dd>
					    </dl>
					   
					    <dl >
							<dt>店铺名称：</dt>
							<dd>
								<input name="lvStore.name" type="text" size="30" maxlength="20" class="required" value="${lvStore.name}"/>
							</dd>
					    </dl>
					    <dl >
							<dt>服务电话：</dt>
							<dd>
							<input name="lvStore.serviceTel" type="text" size="30" maxlength="50" class="phone" value="${lvStore.serviceTel }"/>
							</dd>
					    </dl>
					    <dl >
							<dt>店铺域名：</dt>
							<dd>
								<input id="domainName" name="lvStore.domainName" type="text" size="30" maxlength="160" value="${lvStore.domainName }" class="required" readonly="readonly"/>
							</dd>
					    </dl>
					     
					     <dl class="nowrap">
							<dt>商家LOGO：</dt>
							<dd>
								<table><tr><td><input name="img" type="file"  class="true"/> </td><td>	<img alt="" width="60" height="60" src="${lvStore.logo}"></td></tr></table>
							</dd>
					    </dl>
					    <dl class="nowrap">
						<dt>服务承诺：</dt>
						<dd><input name="lvStore.servicePromise" type="text" size="92" maxlength="200"  value="${lvStore.servicePromise }"/></dd>
						</dl>
						
					   <dl class="nowrap">
					    <dt>店铺地址：</dt>
					   <dd> <input name="lvStore.address" type="text" size="92" maxlength="200" value="${lvStore.address }"/></dd>
					   </dl>
					    
					    <dl >
							<dt>是否商务发货：</dt>
							<dd>
								<s:select list="#{'':'==请选择==',1:'是',0:'否'}" name="lvStore.thirdPartyShippingMark" cssClass="required" disabled="true"></s:select> 
							</dd>
					    </dl>
 						<dl >
							<dt>选择默认模板：</dt>
							<dd>
							<!-- 
								<select name="lvTplModelPublic.id" class="required">
								<option value="">==请选择==</option>
								<s:iterator  value="#request.modelPublicList" id="mode"><option value="${mode.id}" <c:if test="${lvTplModelPublic.id==mode.id }">selected="selected"</c:if>>${mode.modelName}</option></s:iterator>
								</select>
								 --> 
								<s:if test="#request.tplModel!=null">${requestScope.tplModel.modelName}</s:if>
							</dd>
					    </dl>
					    <dl >
							<dt>是否开启优惠券：</dt>
							<dd>
								<s:select list="#{1:'是',0:'否'}" name="lvStore.isPreferences" cssClass="required"  disabled="true"></s:select> 
							</dd>
					    </dl>
					    <dl >
							<dt>店铺状态：</dt>
							<dd>
								<s:select list="#{1:'启用',0:'停用'}" name="lvStore.status" cssClass="required"></s:select> 
							</dd>
					    </dl>
					    <dl class="nowrap">
							<dt>第三方统计代码：</dt>
							<dd>
								<textarea name="lvStore.statCode"  rows="5" cols="80" maxlength="1500">${lvStore.statCode }</textarea>
							</dd>
					    </dl>
					  	<dl >
							<dt>Email：</dt>
							<dd>
								<input name="lvStore.email" type="text" size="30" value="${lvStore.email }" maxlength="50" class="email"/>
							</dd>
					    </dl>
					     <dl >
							<dt>商家标志：</dt>
						   <dd><input type="text" name="lvStore.storeFlag" class="required lettersonly"  size="30" maxlength="8" value="${lvStore.storeFlag}" readonly="readonly"/></dd>
						</dl>
						 <dl >
							<dt>负责人：</dt>
						   <dd><input type="text" name="lvStore.principal" size="30"  maxlength="20" value="${lvStore.principal }"></dd>
						</dl>
						<dl >
							<dt>联系电话：</dt>
						   <dd><input name="lvStore.tel" type="text" size="30" maxlength="50" value="${lvStore.tel }" class="phone"/></dd>
						</dl>
						<dl >
							<dt>创建时间：</dt>
						   <dd><input name="lvStore.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvStore.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
							</dd>
						</dl>
						<dl >
							<dt>修改时间：</dt>
						   <dd><input name="lvStore.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvStore.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
							</dd>
						</dl>
						<dl >
							<dt>修改人名称：</dt>
						   <dd><input name="lvStore.modifyUserName" readonly="readonly" type="text" size="30" value="${lvStore.modifyUserName}"/></dd>
						</dl>
						
						<dl >
							<dt>是否样版店：</dt>
							<dd>
								<s:select list="#{'':'否',1:'是',0:'否'}" name="lvStore.isTemplet" cssClass="required" disabled="true"></s:select> 
							</dd>
					    </dl>
					    <dl >
							<dt>商城体系：</dt>
							<dd>
								<select name="lvStore.mallSystem" class="required">
								<c:foreach items="${mallSystemList}" var="m">
								<option value="${m.mallSystemFlag }" <c:if test="${lvStore.mallSystem==m.mallSystemFlag}">selected="selected"</c:if>>${m.mallSystemName }</option>
								</c:foreach>
								</select>
							</dd>
					    </dl>
					    <dl >
							<dt>币种：</dt>
							<dd>
							<input name="lvStore.currency" readonly="readonly" value="${lvStore.currency}">
							<%--
							    <select name="lvStore.currency" class="required">
							    <option value="USD" <c:if test="${lvStore.currency=='USD'}">selected="selected"</c:if>>USD</option>
							    <option value="CNY" <c:if test="${lvStore.currency=='CNY'}">selected="selected"</c:if>>CNY</option>
								</select>
							 --%>
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