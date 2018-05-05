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
			action="/manager/sysConfigMngAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt style="text-align: right;">
						名称：
					</dt>
					<dd>
					<input name="config.name" type="text" style="width: 500px;"
							style="" maxlength="128" class="required" />
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						配置值：
					</dt>
					<dd>
						<input name="config.val" type="text" style="width: 500px;"
						style="" maxlength="512" class="required" />	
					</dd>
				</dl>
				<dl>
					<dt style="text-align: right;">
						描述：
					</dt>
					<dd>
						<input name="config.cdesc" type="text" style="width: 500px;"
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
							<option value="0">公共配置</option>
							<option value="1">tvpad项目</option>
							<option value="2">banana项目</option>
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
									保存
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