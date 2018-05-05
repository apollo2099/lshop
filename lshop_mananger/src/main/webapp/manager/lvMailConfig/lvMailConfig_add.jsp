<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvMailConfigAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<dl>
					<dt>发送服务端HOST：</dt>
					<dd>
						<input name="lvMailConfig.sendSmtpHost" type="text" size="30" maxlength="32" class="required"/>
					</dd>				  
				</dl>
				<dl>
					<dt>发送端用户密码：</dt>
					<dd>
							<input name="lvMailConfig.sendPassword" type="text" size="30" maxlength="32" class="required"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>发送服务端用户列表：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvMailConfig.sendUserName" maxlength="255" class="required"></textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>发送服务端来源：</dt>
					<dd>
						<input name="lvMailConfig.mailFrom" type="text" size="30" maxlength="255" class="required"/>
					</dd>				  
				</dl>
				<dl>
					<dt>商城体系标示：</dt>
					<dd>
						<select name="lvMailConfig.mallSystem" class="required">
								<c:foreach items="${mallSystemList}" var="m">
								<option value="${m.mallSystemFlag }">${m.mallSystemName }</option>
								</c:foreach>
								</select>
					</dd>				  
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>