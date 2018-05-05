<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script type="text/javascript">
	
</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="/manager/sysConfigMngAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="config.createTime" value="<s:date name="config.createTime" format="yyyy-MM-dd HH:mm:ss"/>">
			<input type="hidden" name="config.status" value="${config.status}"/>
			<input type="hidden" name="config.id" value="${config.id}"/>
			<input type="hidden" name="config.code" value="${config.code}"/>
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt style="text-align: right;">
						名称：
					</dt>
					<dd>
					<input name="config.name" type="text" style="width: 500px;" 
						value='<s:property escapeHtml="true" value="#request.config.name"/>'
							style="" maxlength="128" class="required" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						配置值：
					</dt>
					<dd>
						<input name="config.val" type="text" style="width: 500px;" 
						value='<s:property escapeHtml="true" value="#request.config.val"/>'
						style="" maxlength="512" class="required" />	
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						描述：
					</dt>
					<dd>
						<input name="config.cdesc" type="text" style="width: 500px;" 
						value='<s:property escapeHtml="true" value="#request.config.cdesc"/>'
						style="" maxlength="128" class="required" />					
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						类型：
					</dt>
					<dd>
						<select name="config.type" onchange="" 
						style="width: 500px;" class="required">
							<option value="">请选择类型</option>
							<option  value="0" <c:if test="${config.type==0 }">selected</c:if>>公共配置</option>
							<option  value="1" <c:if test="${config.type==1 }">selected</c:if>>tvpad项目</option>
							<option  value="2" <c:if test="${config.type==2 }">selected</c:if>>banana项目</option>
						</select>
					</dd>
				</dl>	
				
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									修改
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>