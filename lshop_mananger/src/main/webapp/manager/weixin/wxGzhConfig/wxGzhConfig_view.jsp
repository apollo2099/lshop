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
						${wxGzhConfig.id}
					</dd>				  
				</dl>
				
				
				<dl>
					<dt>公众号名称：</dt>
					<dd>
						${wxGzhConfig.wxhName}
					</dd>				  
				</dl>
				
				<dl>
					<dt>所属商城：</dt>
					<dd>
					 <c:foreach items="${storeList}" var="store">
							    <c:if test="${wxGzhConfig.storeId==store.storeFlag }">${store.name }</c:if>
							   </c:foreach>
					</dd>				  
				</dl>
				<dl>
					<dt>URL(服务器地址)：</dt>
					<dd>
						${wxGzhConfig.preUrl}
					</dd>				  
				</dl>

				<dl>
					<dt>Token：</dt>
					<dd>${wxGzhConfig.token}
					</dd>				  
				</dl>
				<dl>
					<dt>应用ID：</dt>
					<dd>
						${wxGzhConfig.appId}
					</dd>				  
				</dl>

				<dl>
					<dt>应用密钥：</dt>
					<dd>
						${wxGzhConfig.appSecret}
					</dd>				  
				</dl>
				<dl>
					<dt>access_token：</dt>
					<dd title="${wxGzhConfig.accessToken}">
					<c:if test="${fn:length(wxGzhConfig.accessToken)>15}">
						${fn:substring(wxGzhConfig.accessToken,0,15)}...
						</c:if>
						<c:if test="${fn:length(wxGzhConfig.accessToken)<=15}">
						${wxGzhConfig.accessToken}
						</c:if>
					</dd>				  
				</dl>

				<dl>
					<dt>凭证失效时间(s)：</dt>
					<dd>
						${wxGzhConfig.accessTokenExpires}
					</dd>				  
				</dl>
				<dl>
					<dt>凭证获取时间：</dt>
					<dd>
						${wxGzhConfig.accessTokenTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>编号：</dt>
					<dd>
						${wxGzhConfig.code}
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${wxGzhConfig.createTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${wxGzhConfig.modifyTimeString}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人编号：</dt>
					<dd>
						${wxGzhConfig.modifyUserId}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${wxGzhConfig.modifyUserName}
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
