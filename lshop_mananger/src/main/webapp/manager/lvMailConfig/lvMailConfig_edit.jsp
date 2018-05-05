<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvMailConfigAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvMailConfig.id" type="hidden" size="32" value="${lvMailConfig.id}"/>
				<input name="lvMailConfig.code" type="hidden" size="32" value="${lvMailConfig.code}"/>
				<input name="lvMailConfig.modifyUserId" type="hidden" size="32" value="${lvMailConfig.modifyUserId}"/>
				<dl>
					<dt>发送服务端HOST：</dt>
					<dd>
						<input name="lvMailConfig.sendSmtpHost" type="text" size="32" maxlength="32" class="required" value="${lvMailConfig.sendSmtpHost }"/>
					</dd>				  
				</dl>
				<dl>
					<dt>发送端用户密码：</dt>
					<dd>
							<input name="lvMailConfig.sendPassword" type="text" size="32" maxlength="32" class="required" value="${lvMailConfig.sendPassword }"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>发送服务端用户列表：</dt>
					<dd>
						<textarea rows="5" cols="80" name="lvMailConfig.sendUserName" maxlength="255" class="required">${lvMailConfig.sendUserName }</textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>发送服务端来源：</dt>
					<dd>
						<input name="lvMailConfig.mailFrom" type="text" size="32" maxlength="255" class="required" value="${lvMailConfig.mailFrom }"/>
					</dd>				  
				</dl>
				<dl>
					<dt>商城体系标示：</dt>
					<dd>
						<select name="lvMailConfig.mallSystem" class="required">
								<c:foreach items="${mallSystemList}" var="m">
								<option value="${m.mallSystemFlag }" <c:if test="${lvMailConfig.mallSystem==m.mallSystemFlag}">selected="selected"</c:if>>${m.mallSystemName }</option>
								</c:foreach>
						</select>
					</dd>				  
				</dl>
				<dl>
					<dt>创建时间：</dt>
					<dd>
						<input type="text" name="lvMailConfig.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvMailConfig.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改时间：</dt>
					<dd>
						<input type="text" name="lvMailConfig.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvMailConfig.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
					<dt>修改人名称：</dt>
					<dd>
						<input name="lvMailConfig.modifyUserName" type="text" size="32" value="${lvMailConfig.modifyUserName}" readonly="readonly"/>
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