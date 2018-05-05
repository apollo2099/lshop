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
					<dt>id：</dt>
					<dd>
						${wxObtain.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号名称：</dt>
					<dd>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id==wxObtain.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach>
					</dd>				  
				</dl>
				<dl>
					<dt>用户标识：</dt>
					<dd title="${wxObtain.openid }">
						<c:if test="${fn:length(wxObtain.openid)>15}">
					${fn:substring(wxObtain.openid,0,15)}...
					</c:if>
					<c:if test="${fn:length(wxObtain.openid)<=15}">
					${wxObtain.openid}
					</c:if>
					</dd>						  
				</dl>
				<dl>
					<dt>用户昵称：</dt>
					<dd>
						${wxObtain.nickname}
					</dd>				  
				</dl>
				<dl>
					<dt>领取类型：</dt>
					<dd>
					<c:if test="${wxObtain.obtainType == 1 }">
					自己领取
					</c:if>
						<c:if test="${wxObtain.obtainType == 2 }">
					好友支持
					</c:if>
					</dd>				  
				</dl>
			
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxObtain.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>IP地址：</dt>
					<dd>
						${wxObtain.ipAddress}
					</dd>				  
				</dl>
				
					<dl>
					<dt>好友标识：</dt>
					<dd title="${wxObtain.friendOpenId}">
						<c:if test="${fn:length(wxObtain.friendOpenId)>15}">
					${fn:substring(wxObtain.friendOpenId,0,15)}...
					</c:if>
					<c:if test="${fn:length(wxObtain.friendOpenId)<=15}">
					${wxObtain.friendOpenId}
					</c:if>
						
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
