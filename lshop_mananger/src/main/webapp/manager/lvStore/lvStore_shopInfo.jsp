<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
<h2 class="contentTitle">设置商城基本资料：</h2>
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
			<form method="post" action="lvStoreMngAction!edit.action?soureFlag=1" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		    <input type="hidden" name="json.navTabId" value="${json.navTabId}">
		    <input type="hidden" name="currentIndex" value="${stat.index }">
		    <input type="hidden" name="lvStore.id" value="${lvStore.id}">
		    <input type="hidden" name="lvStore.isdel" value="${lvStore.isdel}">
		    <input type="hidden" name="lvStore.code" value="${lvStore.code}">
		    <input type="hidden" name="lvStore.templetId" value="${lvStore.templetId}">
		    <input type="hidden" name="lvStore.storeFlag" value="${lvStore.storeFlag}">
		    <input type="hidden" name="lvStore.createUserId" value="${lvStore.createUserId}">
		    <input type="hidden" name="lvStore.createUserName" value="${lvStore.createUserName}">
		    <input type="hidden" name="lvStore.createTime" value="<fmt:formatDate value='${lvStore.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
		    <input type="hidden" name="lvStore.thirdPartyShippingMark" value="${lvStore.thirdPartyShippingMark }">
		    <input type="hidden" name="lvStore.description" value="${lvStore.description }">
		    <input type="hidden" name="lvStore.parentCode" value="${lvStore.parentCode }">
		    <input type="hidden" name="lvStore.number" value="${lvStore.number }">
		    <input type="hidden" name="lvStore.domainName" value="${lvStore.domainName }">
		    
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
		    <input type="hidden" name="lvStore.servicePromise"  value="${lvStore.servicePromise}" />
		    <input type="hidden" name="lvStore.address"  value="${lvStore.address }"/>
		    <input type="hidden" name="lvStore.isCommend"  value="${lvStore.isCommend }">
		    <input type="hidden" name="lvStore.oldName" value="${lvStore.name }">
		    <input type="hidden" name="lvStore.oldStatus" value="${lvStore.status }">
		    <input name="lvStore.continentCode" type="hidden"  value="${lvStore.continentCode }"/>
			<input name="lvStore.countryCode" type="hidden"   value="${lvStore.countryCode }"/>
			<input name="lvStore.cityCode" type="hidden"  value="${lvStore.cityCode }"/>
			<input type="hidden" name="lvStore.mallSystem" value="${lvStore.mallSystem}">
			<div class="pageFormContent" >
						<dl class="nowrap">
							<dt>商城名称：</dt>
							<dd><input name="lvStore.name" type="text" size="30" maxlength="20" value="${lvStore.name}" class="required"/></dd>
						</dl>
						<dl class="nowrap" style="height: 90px">
							<dt>商城LOGO：</dt>
							<dd >
							<table><tr><td><input name="img" type="file"  class="true"/></td></tr>
							      <tr><td><img alt="" width="60" height="60" src="${lvStore.logo}"></td></tr></table>
							</dd>
						</dl>
						<dl class="nowrap">
							<dt>联系电话：</dt>
							<dd><input name="lvStore.tel" type="text" size="30" maxlength="50" class="phone"  value="${lvStore.tel}"/></dd>
						</dl>
						<dl class="nowrap">
							<dt>Email：</dt>
							<dd><input name="lvStore.email" type="text" size="30" maxlength="50" value="${lvStore.email}" class="email"/></dd>
						</dl>
						<dl class="nowrap">
							<dt>底部版权信息：</dt>
						<dd><textarea name="lvStore.footerInfo"  rows="6" cols="50" maxlength="500">${lvStore.footerInfo}</textarea></dd>
						</dl>
						<dl class="nowrap">
							<dt>第三方统计代码：</dt>
							<dd><textarea name="lvStore.statCode"  rows="6" cols="50" maxlength="1500">${lvStore.statCode}</textarea></dd>
						</dl>
						<dl class="nowrap">
							<dt>最后修改人：</dt>
							<dd><input name="lvStore.modifyUserName" type="text" size="30"  value="${lvStore.modifyUserName}" readonly="readonly"/></dd>
						</dl>
						
						<dl class="nowrap">
							<dt>币种：</dt>
							<dd>
							
							<select name="lvStore.currency" class="required">
							<option value="USD" <c:if test="${lvStore.currency=='USD'}">selected="selected"</c:if>>USD</option>
							<option value="CNY" <c:if test="${lvStore.currency=='CNY'}">selected="selected"</c:if>>CNY</option>
							</select>
							</dd>
						</dl>
						<dl class="nowrap">
							<dt>最后修改时间：</dt>
							<dd>
							<input name="lvStore.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="${lvStore.modifyTime }"/>
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
</div>