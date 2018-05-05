<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="wxGzhConfigMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>id：</label>
							<input name="wxGzhConfig.id" type="text" size="30" readOnly="readOnly"  value="${wxGzhConfig.id}"/>
						</p>
						<p>
							<label>公众号名称：</label>
							<input name="wxGzhConfig.wxhName" type="text" size="30" maxlength="32" value="${wxGzhConfig.wxhName}" class="required"/>
						</p>
							<p>
						<label>所属店铺：</label>
						<select name="wxGzhConfig.storeId" class="required" id="storeId"  style='width:196px;'>
							<c:foreach items="${storeList}" var="store">
							<c:if test="${wxGzhConfig.storeId == store.storeFlag}">
							<option value="${store.storeFlag}" selected="selected">${store.name}</option>
							</c:if>
						    </c:foreach>
						 </select>
						 </p>
						<p>
							<label>URL(服务器地址)：</label>
							<input name="wxGzhConfig.preUrl" type="text" size="30" maxlength="255" value="${wxGzhConfig.preUrl}" class="required"/>
						</p>
						<p>
							<label>Token：</label>
							<input name="wxGzhConfig.token" type="text" size="30" maxlength="32" value="${wxGzhConfig.token}" class="required"/>
						</p>
						<p>
							<label>应用ID：</label>
							<input name="wxGzhConfig.appId" type="text" size="30" maxlength="32" readOnly="readOnly" value="${wxGzhConfig.appId}" class="required"/>
						</p>
						<p>
							<label>应用密钥：</label>
							<input name="wxGzhConfig.appSecret" type="text" size="30" maxlength="128" value="${wxGzhConfig.appSecret}" class="required"/>
						</p>
						<p>
							<label>access_token凭证：</label>
							<input name="wxGzhConfig.accessToken" type="text" size="30" value="${wxGzhConfig.accessToken}" readOnly="readOnly"/>
						</p>
						<p>
							<label>凭证失效时间(s)：</label>
							<input name="wxGzhConfig.accessTokenExpires" type="text" size="30" readonly="readonly" value="${wxGzhConfig.accessTokenExpires}"/>
						
						</p>
						<p>
							<label>凭证获取时间：</label>
						    <s:date name="wxGzhConfig.accessTokenTime" format="yyyy-MM-dd HH:mm:ss"/>
						    <input name="wxGzhConfig.accessTokenTimeString" type="hidden" value="${wxGzhConfig.accessTokenTimeString}"/>
						</p>
						<p>
							<label>编号：</label>
							<input name="wxGzhConfig.code" type="text" size="30" readOnly="readOnly" value="${wxGzhConfig.code}"/>
						</p>
						<p>
							<label>创建时间：</label>
							<s:date name="wxGzhConfig.createTime" format="yyyy-MM-dd HH:mm:ss"/>
							 <input name="wxGzhConfig.createTimeString" type="hidden" value="${wxGzhConfig.createTimeString}"/>
								 
						</p>
						<p>
							<label>修改时间：</label>
							<s:date name="wxGzhConfig.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>
						</p>
						<p>
							<label>修改人编号：</label>
							<input name="wxGzhConfig.modifyUserId" readonly="readonly" type="text" size="30" value="${wxGzhConfig.modifyUserId}"/>
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="wxGzhConfig.modifyUserName" readonly="readonly" type="text" size="30" value="${wxGzhConfig.modifyUserName}"/>
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