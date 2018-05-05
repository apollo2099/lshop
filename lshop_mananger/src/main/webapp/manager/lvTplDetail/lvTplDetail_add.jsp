<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTplDetailMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvTplDetail.tplModelCode"  value="${lvTplDetail.tplModelCode}"/>
			<input type="hidden" name="lvTplModel.storeFlag" value="${lvTplModel.storeFlag }">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>模板名称：</label>
							<input name="lvTplDetail.name" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>模版文件路径：</label>
							<input name="lvTplDetail.pagePath" type="text" size="30" maxlength="128" class="required"/>
						</p>
						<dl class="nowrap">
							<dt>模板内容：</dt>
							<dd>
								<textarea name="lvTplDetail.content"  rows="22" cols="90" class="required"></textarea>
							</dd>
					    </dl>
						
						
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