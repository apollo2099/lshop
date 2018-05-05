<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvMallSystemAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvMallSystem.id" type="hidden" size="32" value="${lvMallSystem.id}"/>
			<input name="lvMallSystem.code" type="hidden" size="32" value="${lvMallSystem.code}"/>
			<input name="lvMallSystem.modifyUserId" type="hidden" size="32" value="${lvMallSystem.modifyUserId}"/>
			<input name="lvMallSystem.oldMallSystemFlag" type="hidden" size="32" value="${lvMallSystem.mallSystemFlag}"/>
			
				<!-- 生成表单 -->
						<p>
							<label>商城体系名称：</label>
							<input name="lvMallSystem.mallSystemName" type="text" size="32" value="${lvMallSystem.mallSystemName}" maxlength="64" class="required"/>
						</p>
						<p>
							<label>商城体系标示：</label>
							<input name="lvMallSystem.mallSystemFlag" type="text" size="32" value="${lvMallSystem.mallSystemFlag}" maxlength="32" class="required"/>
						</p>
						<p>
							<label>商城体系域名后缀：</label>
							<input name="lvMallSystem.domainPostfix" type="text" size="32" value="${lvMallSystem.domainPostfix}" maxlength="32" class="required"/>
						</p>

						<p>
							<label>创建时间：</label>
								 	<input type="text" name="lvMallSystem.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvMallSystem.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改时间：</label>
								 	 	<input type="text" name="lvMallSystem.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true"  size="32"
                        value="<s:date name="lvMallSystem.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvMallSystem.modifyUserName" type="text" size="32" value="${lvMallSystem.modifyUserName}" readonly="readonly"/>
						</p>
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