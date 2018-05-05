<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
<h2 class="contentTitle">设置店铺基本资料：</h2>
	<div class="pageContent">
		<div class="tabs" currentIndex="${currentIndex }" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
				<c:foreach items="${shopList}" var="lvStore" >
				<li><a href="javascript:;"><span>${lvStore.name}</span></a></li>
				</c:foreach>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:650px;">
		<c:foreach items="${shopList}" var="lvStore" varStatus="stat">
			<div>
			<form method="post" action="lvStoreMngAction!edit.action?json.navTabId=${json.navTabId}&soureFlag=2" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
			<input type="hidden" name="currentIndex" value="${stat.index }">
		    <input type="hidden" name="lvStore.id" value="${lvStore.id}">
		    <input type="hidden" name="lvStore.isdel" value="${lvStore.isdel}">
		    <input type="hidden" name="lvStore.code" value="${lvStore.code}">
		    <input type="hidden" name="lvStore.templetId" value="${lvStore.templetId}">
		    <input type="hidden" name="lvStore.storeFlag" value="${lvStore.storeFlag}">
		    <input type="hidden" name="lvStore.createUserId" value="${lvStore.createUserId}">
		    <input type="hidden" name="lvStore.createUserName" value="${lvStore.createUserName}">
		    <input type="hidden" name="lvStore.createTime" value="<fmt:formatDate value='${lvStore.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
		    <input type="hidden" name="lvStore.parentCode" value="${lvStore.parentCode }">
		    <input type="hidden" name="lvStore.isPreferences" value="${lvStore.isPreferences }">
		    <input type="hidden" name="lvStore.thirdPartyShippingMark" value="${lvStore.thirdPartyShippingMark }">
		    <input type="hidden" name="lvStore.country"   value="${lvStore.country }"/>	
		    <input type="hidden" name="lvStore.countryId"   value="${lvStore.countryId }"/>	    
		    <input type="hidden" name="lvStore.orderValue" value="${lvStore.orderValue }">
		    <input type="hidden" name="lvStore.isTemplet" value="${lvStore.isTemplet }">
		    <input type="hidden" name="lvStore.city"    value="${lvStore.city }"/>
		    <input type="hidden" name="lvStore.serviceTel"  value="${lvStore.serviceTel }"/>
		    <input type="hidden" name="lvStore.urlFlag"  value="${lvStore.urlFlag }">
		    <input type="hidden" name="lvStore.status"  value="${lvStore.status }">
		    <input type="hidden" name="lvStore.isTemplet"  value="${lvStore.isTemplet }">
		    <input type="hidden" name="lvStore.principal"  value="${lvStore.principal }">
		    <input type="hidden" name="lvStore.isHot"  value="${lvStore.isHot }">
		    <input type="hidden" name="lvStore.orderValue"  value="${lvStore.orderValue }">
		    <input type="hidden" name="lvStore.isCommend"  value="${lvStore.isCommend }">
		    <input type="hidden" name="lvStore.oldName" value="${lvStore.name }">
		    <input name="lvStore.continentCode" type="hidden"  value="${lvStore.continentCode }"/>
			<input name="lvStore.countryCode" type="hidden"   value="${lvStore.countryCode }"/>
			<input name="lvStore.cityCode" type="hidden"  value="${lvStore.cityCode }"/>
		    <input type="hidden" name="lvStore.oldStatus" value="${lvStore.status }">
		    <input type="hidden" name="lvStore.mallSystem" value="${lvStore.mallSystem}">
		    <input type="hidden" name="lvStore.currency" value="${lvStore.currency}">
		    
			<div class="pageFormContent" layoutH="140">
						<p>
							<label>店铺编号：</label>
							<input name="lvStore.number" type="text" size="30" maxlength="32" value="${lvStore.number}" class="required" readonly="readonly"/>
						</p>
						<p>
							<label>店铺名称：</label>
							<input name="lvStore.name" type="text" size="30" maxlength="32" value="${lvStore.name}" class="required"/>
						</p>
						<p>
							<label>店铺域名：</label>
							<input name="lvStore.domainName" type="text" size="30" readonly="readonly" maxlength="150" value="${lvStore.domainName}" class="required"/>
						</p>
						
						<dl>
					    <dt>所选默认模板：</dt>
					   <dd>
						${lvStore.defaultTplModel }
					    </dd>				  
				        </dl>
						
						
						<p>
							<label>店铺标志：</label>
							${lvStore.storeFlag}
						</p>
						<dl class="nowrap">
							<dt>店铺LOGO：</dt>
							<dd >
							<table><tr><td><input id="importFile" name="img" type="file"  class="true"/></td><td>	<img alt="" width="60" height="60" src="${lvStore.logo}"></td></tr></table>
							</dd>
						</dl>
						<p>
							<label>联系电话：</label>
							<input name="lvStore.tel" type="text" size="30" maxlength="50"  class="phone"   value="${lvStore.tel}"/>
						</p>
						<p>
							<label>关键字：</label>
							<input name="lvStore.keyword" type="text" size="35" maxlength="150" value="${lvStore.keyword}" class="true"/>
						</p>
						<p>
							<label>是否商务发货：</label>
							  <select name="lvStore.thirdPartyShippingMark" disabled="disabled" class="required">
							 <option value="">==请选择==</option>
							 <option value="1" <c:if test="${lvStore.thirdPartyShippingMark==1 }">selected="selected"</c:if>>是</option>
							 <option value="0" <c:if test="${lvStore.thirdPartyShippingMark==0 }">selected="selected"</c:if>>否</option>
							</select> 
						</p>
						<p>
							<label>是否启用优惠券：</label>
							<select name="lvStore.isPreferences" disabled="disabled" class="required">
							 <option value="">==请选择==</option>
							 <option value="1" <c:if test="${lvStore.isPreferences==1 }">selected="selected"</c:if>>是</option>
							 <option value="0" <c:if test="${lvStore.isPreferences==0 }">selected="selected"</c:if>>否</option>
							</select>  
						</p>
						<p>
							<label>Email：</label>
							<input name="lvStore.email" type="text" size="35" maxlength="50" value="${lvStore.email}" class="email"/>
						</p>
						
						<dl class="nowrap">
						<dt>服务承诺：</dt>
						<dd><input name="lvStore.servicePromise" type="text" size="92" maxlength="200" value="${lvStore.servicePromise}" /></dd>
						</dl>
					   <dl class="nowrap">
					    <dt>店铺地址：</dt>
					   <dd> <input name="lvStore.address" type="text" size="92" maxlength="200" value="${lvStore.address }"/></dd>
					   </dl>
						<dl class="nowrap">
						<dt>页面描述：</dt>
						<dd><textarea name="lvStore.description"  rows="4" cols="50" maxlength="320">${lvStore.description}</textarea></dd>
						</dl>
						<dl class="nowrap">
						<dt>公告：</dt>
						<dd><textarea name="lvStore.affiche"  rows="4" cols="50" maxlength="150">${lvStore.affiche}</textarea></dd>
						</dl>
						<dl class="nowrap">
						<dt>底部版权信息：</dt>
						<dd><textarea name="lvStore.footerInfo"  rows="4" cols="50" maxlength="500">${lvStore.footerInfo}</textarea></dd>
						</dl>
						<dl class="nowrap">
							<dt>第三方统计代码：</dt>
							<dd>
								<textarea name="lvStore.statCode"  rows="4" cols="50" >${lvStore.statCode}</textarea>
							</dd>
						</dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				</ul>
			</div>
		</form>
			</div>
		</c:foreach>
		</div>	
	</div>
</div>