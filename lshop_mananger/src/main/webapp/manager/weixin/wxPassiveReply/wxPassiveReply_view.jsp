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
						${wxPassiveReply.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号名称：</dt>
					<dd>
						${config.wxhName}
					</dd>				  
				</dl>
			
				<dl>
					<dt>对应素材名称：</dt>
					<dd>
					
					
						<c:foreach items="${textMaterials}" var="item">
						<c:if test="${item.id==wxPassiveReply.materialId && wxPassiveReply.materialType==1 }">${item.name}</c:if>
					    </c:foreach>
					    
					    <c:foreach items="${newsMaterials}" var="item1">
						<c:if test="${item1.id==wxPassiveReply.materialId && wxPassiveReply.materialType==6}">${item1.name}</c:if>
					    </c:foreach>
					
					</dd>				  
				</dl>
					<dl>
					<dt>素材类型：</dt>
					<dd>
					<c:if test="${wxPassiveReply.materialType==1 }">
					文本
					</c:if>
					<c:if test="${wxPassiveReply.materialType==6 }">
						图文
					</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>描述：</dt>
					<dd title="${wxPassiveReply.description}">
					<c:if test="${fn:length(wxPassiveReply.description)>15}">
						${fn:substring(wxPassiveReply.description,0,15)}... 
						</c:if>
						<c:if test="${fn:length(wxPassiveReply.description)<=15}">
						${wxPassiveReply.description}
						</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxPassiveReply.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxPassiveReply.modifyTimeString}
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
