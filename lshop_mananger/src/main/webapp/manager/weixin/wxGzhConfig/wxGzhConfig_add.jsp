<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxGzhConfigMngAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						
						<p>
							<label>公众号名称：</label>
							<input name="wxGzhConfig.wxhName" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
						<label>所属店铺：</label>
				    <select name="wxGzhConfig.storeId" class="required" id="storeId" style='width:196px;'>
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option value="${store.storeFlag }">${store.name}</option>
						    </c:foreach>
						 </select>
						 </p>
						<p>
							<label>URL(服务器地址)：</label>
							<input name="wxGzhConfig.preUrl" type="text" size="30" maxlength="255" class="required"/>
						</p>
						<p>
							<label>Token：</label>
							<input name="wxGzhConfig.token" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>应用ID：</label>
							<input name="wxGzhConfig.appId" type="text" size="30" maxlength="32" class="required"/>
						</p>
						<p>
							<label>应用密钥：</label>
							<input name="wxGzhConfig.appSecret" type="text" size="30" maxlength="128" class="required"/>
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