<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreMngAction!updateStoreCommend.action?json.navTabId=${json.navTabId}" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		    <input type="hidden" name="ids" value="${ids}">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				
				       <dl >
							<dt>设置是否为推荐店铺：</dt>
							<dd>
								<s:select list="#{'':'==请选择==',1:'是',0:'否'}" name="lvStore.isCommend" cssClass="required"></s:select>
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