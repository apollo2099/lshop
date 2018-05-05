<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<style>
.viewInfo dl{
width: 700px;
margin-left: 50px;
}
.viewInfo dl dt{
width: 100px;
}
.viewInfo dl dd{
width: 580px;
}
</style>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" style="border-top-width: 0px;">
				<dl >
					<dt>账号：</dt>
					<dd>
						${app.email}
					</dd>				  
				</dl>
				<dl >
					<dt>是否同意协议：</dt>
					<dd>
						<c:if test="${app.isAgree==0 }">不同意</c:if>
						<c:if test="${app.isAgree==1 }">同意</c:if>
					</dd>				  
				</dl>
				<dl >
					<dt>应用名称：</dt>
					<dd>
				     ${app.name}
					</dd>				  
				</dl>
				<dl >
					<dt>应用语言：</dt>
					<dd>
				     ${app.lang}
					</dd>				  
				</dl>
				<dl >
					<dt>应用分类：</dt>
					<dd>
				     ${app.appType}
					</dd>				  
				</dl>
				<dl >
					<dt>关键字：</dt>
					<dd>
				     ${app.keyWord}
					</dd>				  
				</dl>
				<dl style="height: 50;">
					<dt>安装包地址：</dt>
					<dd style="border-bottom: 0px;">
				     <textarea name="" maxlength="" style="height: 50px;width: 580px;">${app.url}</textarea>
					</dd>				  
				</dl>
				<dl style="height: 50;">
					<dt>应用介绍：</dt>
					<dd style="border-bottom: 0px;">
				     <textarea name="" maxlength="" style="height: 50px;width: 580px;">${app.appDesc}</textarea>
					</dd>				  
				</dl>
				<dl >
					<dt>是否收费：</dt>
					<dd>
				     	<c:if test="${app.isCharge==1}">是</c:if>
				     	<c:if test="${app.isCharge==0}">否</c:if>
					</dd>				  
				</dl>
				<dl >
					<dt>是否有广告：</dt>
					<dd>
				     	<c:if test="${app.isAd==1}">是</c:if>
				     	<c:if test="${app.isAd==0}">否</c:if>
					</dd>				  
				</dl>
				
				<dl >
					<dt>状态：</dt>
					<dd>
						<c:if test="${app.reviewStatus==0}">待审核</c:if>
						<c:if test="${app.reviewStatus==1}">审核通过</c:if>
						<c:if test="${app.reviewStatus==2}">审核不通过</c:if>
						<c:if test="${app.reviewStatus==3}">待完善信息</c:if>
					</dd>				  
				</dl>
				<c:if test="${app.reviewStatus==2}">
				<dl style="height: 50;">
					<dt>不通过原因：</dt>
					<dd style="border-bottom: 0px;">
						<textarea name="app.reason" maxlength="200" style="height: 50px;width: 580px;">${app.reason}</textarea>
					</dd>				  
				</dl>
				</c:if>
				<dl >
					<dt>创建时间：</dt>
					<dd>
				     <fmt:formatDate value="${app.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				
				<dl >
					<dt>审核时间：</dt>
					<dd>
				     <fmt:formatDate value="${app.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				
				<dl style="height: ${size}px;">
					<dt>应用截图：</dt>
					<dd style="border-bottom: 0px;">
						<c:if test="${urlList != null }">
						<c:foreach var="url" items="${urlList }">
							<p style="height: 100px;">
							<a href="${url }"
								title="点击查看原图" target="_blank"> <img alt=""
								src="${url }"
								width="200px" height="100px">
							</a>
							</p>
						</c:foreach>
						</c:if>
					</dd>
				</dl>
				<!-- 
			    <dl style="height: 100px;margin-top: 20px;">
					<dt>协议截图：</dt>
					<dd style="border-bottom: 0px;">
						<a href="${app.proofUrl}"
							title="点击查看原图" target="_blank"> <img alt=""
							src="${app.proofUrl}"
							width="200px" height="100px">
						</a>
					</dd>
				</dl>
				 -->
			</div>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
