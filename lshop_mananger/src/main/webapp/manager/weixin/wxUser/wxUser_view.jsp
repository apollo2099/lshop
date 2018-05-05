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
					<dt>公众号名称：</dt>
					<dd>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
					 <c:if test="${wxhConfig.id==wxUser.wxhConfigId}">${wxhConfig.wxhName}</c:if>
					    </c:foreach>
					</dd>				  
				</dl>
						
				<dl>
					<dt>用户标识：</dt>
					<dd title="${wxUser.openid }">
						<c:if test="${fn:length(wxUser.openid)>15}">
					${fn:substring(wxUser.openid,0,15)}...
					</c:if>
					<c:if test="${fn:length(wxUser.openid)<=15}">
					${wxUser.openid}
					</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>用户昵称：</dt>
					<dd>
						${wxUser.nickname}
					</dd>				  
				</dl>
				
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxUser.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxUser.modifyTimeString}
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
