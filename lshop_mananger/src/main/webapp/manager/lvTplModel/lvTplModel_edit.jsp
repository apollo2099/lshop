<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTplModelMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="lvTplModel.id" value="${lvTplModel.id}"/>
			<input type="hidden" name="lvTplModel.isDefault" value="${lvTplModel.isDefault}">
			<input type="hidden" name="lvTplModel.storeFlag" value="${lvTplModel.storeFlag}">
			<input name="lvTplModel.createTime" type="hidden"  value="<s:date name="lvTplModel.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
			<input name="lvTplModel.code" type="hidden"value="${lvTplModel.code}"/>
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>名称：</label>
							<input name="lvTplModel.modelName" type="text" size="30" class="required" value="${lvTplModel.modelName}"/>
						</p>
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