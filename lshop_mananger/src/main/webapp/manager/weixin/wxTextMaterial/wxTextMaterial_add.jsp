<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxTextMaterialMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
<p>
							<label>公众号名称：</label>
							<select name="wxTextMaterial.wxhConfigId" style='width:196px;'>
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" >${wxhConfig.wxhName}</option>
					    </c:foreach>
					    </select>
						</p>
						<p>
							<label>素材名称：</label>
							<input name="wxTextMaterial.name" type="text" size="30" maxlength="60" class="required"/>
						</p>
						<p style="height:130px;">
							<label>消息内容：</label>
							<textarea name="wxTextMaterial.content"  rows="7" cols="30" class="required" maxlength="412"></textarea>
						</p>
						<p><label>&nbsp;</label><span style="color:#777777;">如需要在微信中换行显示，请使用“{br}”字符。</span></p>
						
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>