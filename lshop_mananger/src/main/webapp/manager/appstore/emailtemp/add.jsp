<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
	
</script>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="/manager/emailMngAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<dl class="nowrap">
					<dt style="text-align: right;">
						店铺标识：
					</dt>
					<dd>
						<select name="templ.mallSign">
							<option value="bmcn">banana中文</option>
							<option value="bmen">banana英文</option>
							
							<option value="tvpadcn">华扬中文</option>
							<option value="tvpaden">华扬英文</option>
						</select>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt style="text-align: right;">
						模版分类：
					</dt>
					<dd>
						<select name="templ.ttype">
							<option value="1">开发者审核通过</option>
							<option value="2">开发者审核不通过</option>
							
							<option value="3">应用审核通过</option>
							<option value="4">应用审核不通过</option>
						</select>
					</dd>
				</dl>
				
				<dl>
					<dt style="text-align: right;">
						模版标题：
					</dt>
					<dd>
						<input name="templ.name" type="text"
							style="width: 400px;" maxlength="32" class="required" />
					</dd>
				</dl>
				<dl class="nowrap" >
					<dt style="text-align: right;">
						邮件标题：
					</dt>
					<dd>
						<input name="templ.title" type="text"
							style="width: 400px;" maxlength="32" class="required" />
					</dd>
				</dl>
			
				
				<dl class="nowrap">
					<dt style="text-align: right;">
						邮件内容：
					</dt>
					<dd>
						<textarea class="editor" name="templ.content" rows="12" maxlength="1500"
							cols="80"></textarea>
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