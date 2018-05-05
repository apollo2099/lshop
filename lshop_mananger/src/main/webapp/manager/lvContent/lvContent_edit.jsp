<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
<script type="text/javascript">
   function selectContent(f){
     if(f.value==1){
        $('#contentid').show();
        }else{
        $('#contentid').hide();
        }
   }
   </script>
	<div class="pageContent">
		<form method="post" id="postFromId" action="lvContentMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvContent.isdel" type="hidden"  value="${lvContent.isdel}"/>
			<input name="lvContent.id" type="hidden"  value="${lvContent.id}"/>
			<input name="lvContent.code" type="hidden"  value="${lvContent.code}"/>
			<input name="lvContent.storeFlag" type="hidden" value="${lvContent.storeFlag }">
		   <input name="lvContent.createTime"   type="hidden"  value="<fmt:formatDate value='${lvContent.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
						
						
						<p>
							<label>所属关系：</label>
							<select name="lvContent.storeFlag" class="required" disabled="disabled">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvContent.storeFlag}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
						</p>  
						<p>
							<label>请选择模板：</label>
							<select name="lvContent.templatePath"  class="required" style="width:230px;">
							<option value="">请选择</option>
							<s:iterator value="detailList" id="d">
							<option title="${d.name}" value="${d.pagePath}"  <s:if test="lvContent.templatePath==#d.pagePath">selected="selected"</s:if> >${d.pagePath}</option>
							</s:iterator>
							</select>
							
						</p>
						<dl class="nowrap" >
							<dt>页面名称：</dt>
							<dd>
							<input name="lvContent.pageName" type="text" size="30" value="${lvContent.pageName}" maxlength="32" class="required"/>
						    </dd>
						</dl>
						<dl class="nowrap" >
							<dt>页面标题：</dt>
							<dd>
							<input name="lvContent.pageTitle" type="text" size="30"  value="${lvContent.pageTitle}" maxlength="32" />
						    </dd>
						</dl>
						
						<dl class="nowrap" >
					      <dt>页面关键字：</dt>
					      <dd>
					      <textarea rows="4" cols="80" name="lvContent.pageKeywords">${lvContent.pageKeywords}</textarea>
						  </dd>
					    </dl>
						<dl class="nowrap" >
							<dt>页面描述：</dt>
							<dd>
							<textarea rows="4" cols="80" name="lvContent.pageDescription">${lvContent.pageDescription}</textarea>
							</dd>
						</dl>
						<dl class="nowrap" >
							<dt>页面路径：</dt>
							<dd>
							<input name="lvContent.pagePath" type="text" size="30" maxlength="128" value="${lvContent.pagePath}" class="required"/>
						    </dd>
						</p>	
						<dl class="nowrap"> 
							<dt>是否有主体内容：</dt>
							<dd>
							<s:select name="lvContent.isHasContent" list="#{1:'有',0:'无'}"  onchange="selectContent(this)"></s:select>
						    </dd>
						</dl>
						<dl class="nowrap"   id="contentid"  <s:if test="lvContent.isHasContent!=1"> style="display:none;"</s:if> >
							<dt>页面内容：</dt>
							<dd>
								<textarea name="lvContent.pageContent"  rows="13" cols="80" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=content&storeFlag=${lvContent.storeFlag }" upImgExt="jpg,jpeg,gif,png"  class="editor {internalScript:true,inlineScript:true,linkTag:true}">${lvContent.pageContent}</textarea>
							</dd>
					    </dl>
			</div>
		</form>
		<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="$('#postFromId').submit();">保存</button></div></div></li>
						<li>
						 <div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
						</li>
					</ul>
		</div>
	</div>
</div>