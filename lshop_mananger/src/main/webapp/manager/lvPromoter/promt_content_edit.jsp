<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvPromtPayMngAction!edit.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="promtContent.id" value="${promtContent.id}"/>
			<input type="hidden" name="promtContent.model" value="${promtContent.model}"/>
			<input type="hidden" name="promtContent.status" value="${promtContent.status}"/>
			<div class="pageFormContent" layoutH="56">
				<dl>
					<dt>推广类型模版：</dt>
					<dd>
						  <select name="promtContent.model" style="width: 160px" class="required" onchange="changeType(this.options[this.selectedIndex].value);" lang="推广类型模版" disabled="disabled">
						    <option value="">请选择</option>
						    <option value="1"  <c:if test="${promtContent.model==1}">selected</c:if>>邮件</option>
						    <option value="2" <c:if test="${promtContent.model==2}">selected</c:if>>论坛博客</option>
					      </select>	
				     </dd>
				</dl>
				<c:if test="${promtContent.model==1}">
					<div id="type1">
					    
						<dl>
						   <dt>方案名称：</dt>
						   <dd><input type="text" name="promtContent.formName" value="${promtContent.formName}" class="required"/></dd>
						</dl>
					    <dl class="nowrap">
				           <dt>HTML内容:：</dt>
				           <dd>
					         <textarea class="editor" name="promtContent.htmlContent"  rows="15" cols="20" upImgUrl="/manager/res/upload.action" upImgExt="jpg,jpeg,gif,png">${promtContent.htmlContent}</textarea>
				           </dd>				  
				        </dl>
				         <dl>
						   <dt>纯文本内容:</dt>
						   <dd><textarea rows="10" cols="40" name="promtContent.textContent">${promtContent.textContent}</textarea></dd>
						 </dl>
				        
					</div>
				</c:if>
				
				<c:if test="${promtContent.model==2}">
					<div id="type2">
						<dl>
						   <dt>方案名称：</dt>
						   <dd><input type="text" name="promtContent.formName" value="${promtContent.formName}"/></dd>
						</dl>
						<dl>
						   <dt>纯文本内容:</dt>
						   <dd><textarea rows="10" cols="40" name="promtContent.textContent">${promtContent.textContent}</textarea></dd>
						</dl>
					</div>
				</c:if>
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