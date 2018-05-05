<%@ page language="java" pageEncoding="utf-8"%>
<div class="pageContent">
	<form method="post" action="baseDictionarys!add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="dictionarys.parentCode" value="${param.dictionaryCode }" />
		<input type="hidden" name="dictionarys.levelNo" value="${param.level+1 }" />
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="pageFormContent nowrap" layoutH="56">
			<p>
				<label>编码：</label>
				<input name="dictionarys.dictionaryCode" class="required alphanumeric" type="text" size="30"  maxlength="32" alt="请输入数据字典编码" />
			</p>
			<p>
				<label>名称：</label>
				<input name="dictionarys.codeName" class="required" type="text" size="30" maxlength="64" alt="请输入唯一的字典标识"/>
			</p>
			<p>
				<label>描述：</label>
				<input name="dictionarys.description" type="text" size="30" maxlength="200" alt="请输入字典描述信息"/>
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
