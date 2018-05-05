<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxNewsMaterialMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>公众号：</label>
							<select name="wxNewsMaterial.wxhConfigId" class="required" style='width:196px;'>
							<option value="">==请选择==</option>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
								<option value="${wxhConfig.id }">${wxhConfig.wxhName }</option>
								</c:foreach>
							</select>
						</p>				
						<p>
							<label>素材名称：</label>
							<input name="wxNewsMaterial.name" type="text" size="30" maxlength="60" class="required"/>
						</p>
						<p>
							<label>图文消息类型：</label>
							<select name="wxNewsMaterial.newsType" class="required" style='width:196px;'>
							<option value="">==请选择==</option>
								<option value="1">单图文</option>
								<option value="2">多图文</option>
							</select>
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