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
						${wxObtainNewsTpl.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号名称：</dt>
					<dd>
					${config.wxhName}
					</dd>				  
				</dl>
				<dl>
					<dt>图文素材名称 ：</dt>
					<dd>
					<c:foreach items="${newsMaterials}" var="newsMaterial">
						<c:if test="${newsMaterial.id==wxObtainNewsTpl.newsId }">${newsMaterial.name}</c:if>
					    </c:foreach>
					</dd>				  
				</dl>
				
				<dl>
					<dt>参加关键词：</dt>
					<dd>
						${wxObtainNewsTpl.pushKeyword}
					</dd>				  
				</dl>
				<dl>
					<dt>查询关键词：</dt>
					<dd>
						${wxObtainNewsTpl.queryKeyword}
					</dd>				  
				</dl>
				<dl>
					<dt>活动结束时间：</dt>
					<dd>
						${wxObtainNewsTpl.endTimeString}
					</dd>				  
				</dl>
				
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxObtainNewsTpl.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxObtainNewsTpl.modifyTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${wxObtainNewsTpl.modifyUserName}
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
