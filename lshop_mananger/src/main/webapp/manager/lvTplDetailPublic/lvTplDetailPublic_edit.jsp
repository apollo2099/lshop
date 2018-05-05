<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTplDetailPublicMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvTplDetailPublic.id" type="hidden"  value="${lvTplDetailPublic.id}"/>
			<input name="lvTplDetailPublic.createTime" type="hidden"  value="<s:date name="lvTplDetailPublic.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		    <input name="lvTplDetailPublic.tplModelCode" type="hidden" value="${lvTplDetailPublic.tplModelCode}"/>
		    <div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				        <p>
							<label>模板名称：</label>
							<input name="lvTplDetailPublic.name" type="text" size="30" class="required" value="${lvTplDetailPublic.name}"/>
						</p>
						
						<p>
							<label>模版文件路径：</label>
							<input name="lvTplDetailPublic.pagePath" type="text" readonly="readonly" size="30" value="${lvTplDetailPublic.pagePath}"/>
						</p>
						<dl class="nowrap">
							<dt>模板内容：</dt>
							<dd>
								<textarea name="lvTplDetailPublic.content"  rows="22" cols="90" class="required">${lvTplDetailPublic.content}</textarea>
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