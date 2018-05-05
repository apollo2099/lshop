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
						${wxNewsMaterialItem.id}
					</dd>				  
				</dl>
				<dl>
					<dt>公众号配置名称：</dt>
					<dd>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxNewsMaterialItem.wxhConfigId==wxhConfig.id}">${wxhConfig.wxhName}</c:if>
						</c:foreach>
					</dd>				  
				</dl>
				<dl>
					<dt>图文消息类型：</dt>
					<dd>
						<c:if test="${wxNewsMaterialItem.newsType == 1 }">
							单图文
							</c:if>
								<c:if test="${wxNewsMaterialItem.newsType == 2 }">
							多图文
							</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>关联图文素材名称：</dt>
					<dd>
						${newsMaterial.name }
					</dd>				  
				</dl>
				<dl>
					<dt>标题：</dt>
					<dd>
						${wxNewsMaterialItem.title}
					</dd>				  
				</dl>
				<dl>
					<dt>描述：</dt>
					<dd title="${wxNewsMaterialItem.description }">
					<c:if test="${fn:length(wxNewsMaterialItem.description)>15}">
						${fn:substring(wxNewsMaterialItem.description,0,15)}...
						</c:if>
					<c:if test="${fn:length(wxNewsMaterialItem.description)<=15}">
						${ wxNewsMaterialItem.description}
						</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>跳转链接：</dt>
					<dd title="${wxNewsMaterialItem.url}">
							<c:if test="${fn:length(wxNewsMaterialItem.url)>15}">
						${fn:substring(wxNewsMaterialItem.url,0,15)}...
						</c:if>
					<c:if test="${fn:length(wxNewsMaterialItem.url)<=15}">
						${ wxNewsMaterialItem.url}
						</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>图片链接：</dt>
					<dd>
					<c:if test="${wxNewsMaterialItem.picurl!=''}"><img src="${wxNewsMaterialItem.picurl}" width="60"  height="60"/></c:if>
					</dd>				  
				</dl>
				<dl>
				</dl>
					<dl>
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxNewsMaterialItem.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxNewsMaterialItem.modifyTimeString}
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
