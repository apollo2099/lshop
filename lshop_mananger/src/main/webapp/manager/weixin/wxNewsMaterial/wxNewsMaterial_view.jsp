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
						${wxNewsMaterial.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号：</dt>
					<dd>
					<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id==wxNewsMaterial.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach>
					</dd>				  
				</dl>				
				<dl>
					<dt>素材名称：</dt>
					<dd  title= "${wxNewsMaterial.name }">
					<c:if test="${fn:length(wxNewsMaterial.name)>15}">
						${fn:substring(wxNewsMaterial.name,0,15)}...
						</c:if>
					<c:if test="${fn:length(wxNewsMaterial.name)<=15}">
						${ wxNewsMaterial.name}
						</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>图文消息类型：：</dt>
					<dd>
						<c:if test="${wxNewsMaterial.newsType == 1}">
								单图文
							</c:if>
							<c:if test="${wxNewsMaterial.newsType == 2}">
								多图文
						</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxNewsMaterial.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxNewsMaterial.modifyTimeString}
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
