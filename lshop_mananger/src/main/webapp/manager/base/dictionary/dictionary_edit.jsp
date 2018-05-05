<%@ page language="java" pageEncoding="utf-8"%>
<div class="pageContent">
	<form method="post" action="baseDictionarys!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<input type="hidden" name="dictionarys.id" value="${dictionarys.id }" />
		<input type="hidden" name="dictionarys.version" value="${dictionarys.version }" />
		<input type="hidden" name="dictionarys.dictionaryCode" value="${dictionarys.dictionaryCode }" />
		<input type="hidden" name="dictionarys.parentCode" value="${dictionarys.parentCode }" />
		<input type="hidden" name="dictionarys.status" value="${dictionarys.status }" />
		<input type="hidden" name="dictionarys.levelNo" value="${dictionarys.levelNo }" />
		<input type="hidden" name="dictionarys.createdBy" value="${dictionarys.createdBy }" />
		<input type="hidden" name="dictionarys.createdDate" value="${dictionarys.createdDate }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>字典名称：</label>
				<input name="dictionarys.codeName"  class="required" type="text" size="30"  maxlength="255" alt="[数据字典名称]" value="${dictionarys.codeName}"/>
			</p>
			<p>
				<label>字典描述：</label>
				<input name="dictionarys.description" class="required" type="text" size="30" maxlength="255" alt="请输入数据字典描述" value="${dictionarys.description}"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
