<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvTplDetailMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="lvTplDetail.id" type="hidden"  value="${lvTplDetail.id}"/>
			<input name="lvTplDetail.createTime" type="hidden"  value="<s:date name="lvTplDetail.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
		    <input name="lvTplDetail.tplModelCode" type="hidden" value="${lvTplDetail.tplModelCode}"/>
		    <input name="lvTplDetail.storeFlag" type="hidden" value="${lvTplDetail.storeFlag}"/>
		    <input name="lvTplDetail.isdel" type="hidden" value="${lvTplDetail.isdel}"/>
		    <div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				        <p>
							<label>模板名称：</label>
							<input name="lvTplDetail.name" type="text" size="30" value="${lvTplDetail.name}"/>
						</p>
						
						<p>
							<label>模版文件路径：</label>
							<input name="lvTplDetail.pagePath" type="text" readonly="readonly" size="30" value="${lvTplDetail.pagePath}"/>
						</p>
						
						<dl class="nowrap">
							<dt>模板内容：</dt>
							<dd>
								<textarea name="lvTplDetail.content"  rows="22" cols="90"><s:property value="lvTplDetail.content" default="false"/></textarea>
							</dd>
					    </dl>
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