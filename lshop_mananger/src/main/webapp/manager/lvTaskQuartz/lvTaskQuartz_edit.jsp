<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTaskQuartzMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				            <input name="lvTaskQuartz.status" type="hidden" size="30" value="${lvTaskQuartz.status}"/>
							<input name="lvTaskQuartz.id" type="hidden" size="30" value="${lvTaskQuartz.id}"/>
							<input name="lvTaskQuartz.createTime" type="hidden" value="${lvTaskQuartz.createTimeString}"/>
						<p>
							<label>任务名称：</label>
							<input name="lvTaskQuartz.taskName" type="text" class="required" size="30"  value="${lvTaskQuartz.taskName}"/>
						</p>
						<p>
							<label>目标实例：</label>
							<input name="lvTaskQuartz.targetObject" type="text" class="required"  size="30" value="${lvTaskQuartz.targetObject}"/>
						</p>
						<p>
							<label>目标方法：</label>
							<input name="lvTaskQuartz.targetMethod" type="text" class="required"  size="30" value="${lvTaskQuartz.targetMethod}"/>
						</p>
						<p>
							<label>定时时间：</label>
							<input name="lvTaskQuartz.targetTime" type="text" class="required" size="30" value="${lvTaskQuartz.targetTime}"/>
						</p>
						<p>
							<label>描述：</label>
							<input name="lvTaskQuartz.description" type="text" class="required"  size="30" value="${lvTaskQuartz.description}"/>
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