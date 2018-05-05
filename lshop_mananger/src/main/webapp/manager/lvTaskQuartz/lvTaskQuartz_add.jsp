<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTaskQuartzMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>任务名称：</label>
							<input name="lvTaskQuartz.taskName" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>目标实例：</label>
							<input name="lvTaskQuartz.targetObject" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>目标方法：</label>
							<input name="lvTaskQuartz.targetMethod" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>定时时间：</label>
							<input name="lvTaskQuartz.targetTime" type="text" size="30" maxlength="64" class="required"/>
						</p>
						<p>
							<label>描述：</label>
							
							<input name="lvTaskQuartz.description" type="text" size="30" maxlength="225" class="true"/>
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