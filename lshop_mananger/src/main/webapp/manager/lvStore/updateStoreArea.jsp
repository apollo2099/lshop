<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreMngAction!updateStoreArea.action?json.navTabId=${json.navTabId}" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		     <input type="hidden" name="ids" value="${ids}">
		     
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				      <dl >
							<dt>城市：</dt>
							<dd>
							    <input name="lvStore.continentCode" type="hidden" size="30" />
							    <input name="lvStore.countryCode" type="hidden" size="30"  />
							    <input name="lvStore.cityCode" type="hidden" size="30"  />
							    <input name="lvStore.countryId" type="hidden" size="30"  class="required"/>
							    <input name="lvStore.country" type="hidden" />
								<input name="lvStore.city" type="text" size="30" maxlength="10" class="required" readonly="readonly"/>
								<a class="btnLook" href="lvStoreAreaAction!selectArea.action?json.navTabId=lvStore_1" target="_blank" lookupGroup="lvStore" width="500" height="300">查找带回</a>
							</dd>
					    </dl>
					    <!-- 
				       <dl >
							<dt>国家：</dt>
							<dd>
								<input name="lvStore.country" type="text" size="30" maxlength="32"  class="required" readonly="readonly"/>
							</dd>
					    </dl>
					     -->
					     
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