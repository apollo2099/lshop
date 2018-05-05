<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvAppAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" name="lvApp.id" value="${lvApp.id }">
		    <input type="hidden" name="lvApp.storeId" value="${lvApp.storeId }">
		    <input type="hidden" name="lvApp.code" value="${lvApp.code }">
		    <input type="hidden" name="lvApp.appImage" value="${lvApp.appImage }">
		    <input type="hidden" name="lvApp.oldName" value="${lvApp.name }">
  		<div class="pageFormContent" layoutH="56">
			   <dl>
					<dt>应用类型：</dt>
					<dd>
						<select name="lvApp.typeCode" class="required">
						  <option value="">==请选择类型==</option>
						  <s:iterator value="#request.typeList" id="t">
						     <option value="${t.code }"
						     <c:if test="${lvApp.typeCode==t.code }">selected="selected"</c:if> 
						     >${t.typeName}</option> 
						  </s:iterator>
						</select>
					
					</dd>				  
				</dl>
			    <dl>
					<dt>应用名称：</dt>
					<dd>
						<input name="lvApp.name" type="text"  size="32" maxlength="32" class="required" value="${lvApp.name }"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>版本：</dt>
				    <dd>
				         <input name="lvApp.version" type="text" size="32" maxlength="16" class="required" value="${lvApp.version }"/>
				    </dd>
				</dl>
				<dl>
					<dt>适用机型：</dt>
					<dd>
						<input name="lvApp.applyModel" type="text" size="32" maxlength="32" value="${lvApp.applyModel }"/>
					</dd>				  
				</dl>
					<dl>
					<dt>适用版本：</dt>
					<dd>
                        <input name="lvApp.applyVersion" type="text" size="32" maxlength="16" value="${lvApp.applyVersion }"/>
					</dd>				  
				</dl>
				<dl  >
					<dt>应用语言：</dt>
					<dd>
						<input name="lvApp.language" type="text" size="32" maxlength="32" value="${lvApp.language }"/>
					</dd>				  
				</dl>
				<dl>
					<dt>第三方提供者：</dt>
					<dd>
						<input name="lvApp.thirdParty" type="text" size="32" maxlength="32" value="${lvApp.thirdParty }"/>
					</dd>				  
				</dl>
				<dl>
					<dt>评分：</dt>
					<dd>
						<input name="lvApp.grade" type="text" size="32" maxlength="4" class="digits" value="${lvApp.grade }"/>
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>下载地址：</dt>
					<dd>
					   <input name="lvApp.download" type="text" size="64" maxlength="128" value="${lvApp.download }"/>
					</dd>				  
				</dl>
					<dl class="nowrap">
					<dt>应用图片：</dt>
					<dd>
					      <input name="img" type="file" size="32" />
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>文件大小：</dt>
					<dd>
						 <input name="lvApp.appSize" type="text" size="32" maxlength="16" value="${lvApp.appSize }">
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>应用介绍：</dt>
					<dd>
					  <textarea  class="editor" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=app&storeFlag=${lvApp.storeId }" upImgExt="jpg,jpeg,gif,png"
				               name="lvApp.introduce" rows="15" cols="80">${lvApp.introduce }</textarea>
					</dd>				  
				</dl>
				<dl>
				<dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvApp.createTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvApp.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvApp.modifyUserName"  readonly="true"  value="${lvApp.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvApp.modifyTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvApp.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
		</div>
	
		<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									保存
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
</form>
</div>
</div>
