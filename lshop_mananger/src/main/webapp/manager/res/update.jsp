<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
     	<form method="post" action="resManagerAction!update.action" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="filePath" value="${filePath}"/>
			<div class="pageFormContent" layoutH="56">
						<p>
							<label>文件名：</label>
							${tpl.name}
						</p>
						<p></p>
						<p>
							<label>内容</label>
							 <textarea rows="15" cols="70" id="contentId" name="content"><s:property value="tpl.source"/></textarea>
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