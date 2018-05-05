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
						${wxMenu.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号名称：</dt>
					<dd>
						${config.wxhName}
					</dd>				  
				</dl>
				<dl>
					<dt>父菜单名称：</dt>
					<dd>
					<c:if test="${ wxMenu.menuParent==0 }">无父菜单</c:if>
							<c:if test="${ wxMenu.menuParent!=0 && menu.id == wxMenu.menuParent}">${menu.name}</c:if>
					</dd>				  
				</dl>

				<dl>
					<dt>菜单名称：</dt>
					<dd>
						${wxMenu.name}
					</dd>				  
				</dl>
				
				<dl>
					<dt>排序值：</dt>
					<dd>
						${wxMenu.orderValue}
					</dd>				  
				</dl>
				<dl>
					<dt>菜单类型：</dt>
					<dd>
						<c:if test="${wxMenu.menuType == 'click'}">
								发送信息
								</c:if>
								<c:if test="${wxMenu.menuType == 'view'}">
								跳转到网页
								</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>对外标识：</dt>
					<dd>
						${wxMenu.menuKey}
					</dd>				  
				</dl>
				<dl>
					<dt>URL地址：</dt>
					<dd title="${wxMenu.menuUrl }">
					<c:if test="${fn:length(wxMenu.menuUrl)>20}">
					${fn:substring(wxMenu.menuUrl,0,20)}...
					</c:if>
					
						<c:if test="${fn:length(wxMenu.menuUrl)<=20}">
					${wxMenu.menuUrl }
					</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>素材类型：</dt>
					<dd>
								<c:if test="${wxMenu.materialType == 1}">
								文本
								</c:if>
								<c:if test="${wxMenu.materialType == 6}">
								图文
								</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>对应素材名称：</dt>
					<dd>
					
					<c:choose>
								<c:when test="${wxMenu.materialType == 1 }">
							<c:foreach items="${textMaterials}" var="textMaterial">
						   <c:if test="${textMaterial.id == wxMenu.materialId}">${textMaterial.name}</c:if>
					    </c:foreach>
								</c:when>
								
								<c:when test="${wxMenu.materialType == 6 }">
								<c:foreach items="${newsMaterials}" var="newsMaterial">
						<c:if test="${newsMaterial.id == wxMenu.materialId  }">${newsMaterial.name}</c:if>
					    </c:foreach>
								</c:when>
								</c:choose>
					</dd>				  
				</dl>

				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxMenu.createTimeString}
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
