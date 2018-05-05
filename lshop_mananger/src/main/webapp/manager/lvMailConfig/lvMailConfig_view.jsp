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
					<dt>发送服务端HOST：</dt>
					<dd>
						${lvMailConfig.sendSmtpHost}
					</dd>				  
				</dl>
				<dl>
					<dt>发送端用户密码：</dt>
					<dd>
						${lvMailConfig.sendPassword}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>发送服务端用户列表：</dt>
					<dd>
					<textarea rows="5" cols="80" name="lvMailConfig.sendUserName" maxlength="255">${lvMailConfig.sendUserName}</textarea>
					</dd>				  
				</dl>
				
				<dl>
					<dt>发送服务端来源：</dt>
					<dd>
						${lvMailConfig.mailFrom}
					</dd>				  
				</dl>
				<dl>
					<dt>商城体系标示：</dt>
					<dd>
					<c:foreach items="${mallSystemList}" var="m">
						<c:if test="${lvMailConfig.mallSystem==m.mallSystemFlag}">${m.mallSystemName }</c:if>
				    </c:foreach>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						${lvMailConfig.createTime}
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						${lvMailConfig.modifyTime}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人编号：</dt>
					<dd>
						${lvMailConfig.modifyUserId}
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						${lvMailConfig.modifyUserName}
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
