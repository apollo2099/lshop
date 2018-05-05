<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvHelpTypeMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>所属关系：</label>
							<select name="lvHelpType.storeId" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" >${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>类别名称：</label>
							<input name="lvHelpType.name" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvHelpType.orderValue" type="text" size="30" maxlength="5" value="0" class="required number"/>
						</p>
						<p>
							<label>语言：</label>
							<s:select list="#{'cn':'中文简体','tw':'中文繁体','en':'英文','kn':'韩文'}" name="lvHelpType.webLanguage"></s:select>
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