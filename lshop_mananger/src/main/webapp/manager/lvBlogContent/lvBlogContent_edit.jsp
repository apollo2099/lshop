<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<script>
function validateCallbackFrom(form, callback){
  var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	var blogContent=$("#blogContent").val()	
	if(blogContent==null||blogContent.length<=0){
		alertMsg.error('请输入文章内容!');
		return false;
	}
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: function(json){
		   callback(json);
		},
		error: DWZ.ajaxError
	});
	return false;
}
</script>
<div class="page unitBox">
	<div class="pageContent">
	<form method="post"
			action="lvBlogContentAction!doUpdate.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallbackFrom(this, navTabAjaxDone); ">
			<input type="hidden" name="lvBlogContent.id" value="${lvBlogContent.id }">
			<input type="hidden" name="lvBlogContent.isTop" value="${lvBlogContent.isTop }">
			<input type="hidden" name="lvBlogContent.isRed" value="${lvBlogContent.isRed }">
			<input type="hidden" name="lvBlogContent.modifyTime" value="${lvBlogContent.modifyTime }">
			<input type="hidden" name="lvBlogContent.isDelete" value="${lvBlogContent.isDelete }">
			<input type="hidden" name="lvBlogContent.replyNum" value="${lvBlogContent.replyNum }">
			<input type="hidden" name="publishTime" value="${lvBlogContent.createTime }" />
			<input type="hidden" name="lvBlogContent.code" value="${lvBlogContent.code}">
			<input type="hidden" name="lvBlogContent.storeId" value="${lvBlogContent.storeId }">
  		<div class="pageFormContent" layoutH="56">
				<dl class="nowrap">
					<dt>博客标题：</dt>
					<dd>
					    <input name="lvBlogContent.title" type="text" size="100"  maxlength="300"
							 class="required " value="${lvBlogContent.title}"/>
					</dd>				  
				</dl>
				<dl >
					<dt>所属关系：</dt>
					<dd>
					<select name="lvBlogContent.storeId" class="required" disabled="disabled">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogContent.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
					</select>
					</dd>				  
				</dl>
				<dl >
					<dt>博客类别：</dt>
					<dd>
                 <select name="lvBlogContent.type" class="required"> 
					<option value="">
						请选择类别
					</option>
				         <s:iterator value="#request.typeList" id="t">
							<option
							    <c:if test="${lvBlogContent.type==t.id }">selected="selected"</c:if>
								value="${t.id}">
								${t.type}
							</option>
						</s:iterator>
				  </select>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>发布时间：</dt>
					<dd>
					     <input name="lvBlogContent.createTime" type="text" size="30"  format="yyyy-MM-dd HH:mm:ss"
						  value="<s:date name="lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/>" class="date" readonly="readonly"/>
						(如果没有指定发布时间，则采用系统默认为当前时间)
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>文章内容：</dt>
					<dd>
				    <textarea  id="blogContent" class="editor" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=blog&storeFlag=${lvBlogContent.storeId }" upImgExt="jpg,jpeg,gif,png"
				               name="lvBlogContent.content" cols="100" rows="15">${lvBlogContent.content }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>简介：</dt>
					<dd>
					<textarea name="lvBlogContent.intor"  cols="100" rows="5" maxlength="200" >${lvBlogContent.intor }</textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>公开范围：</dt>
					<dd>
					<select name="lvBlogContent.isPrivate" >
					<option <c:if test="${lvBlogContent.isPrivate==1 }">selected="selected"</c:if> value="1">私有</option>
					<option <c:if test="${lvBlogContent.isPrivate==0 }">selected="selected"</c:if> value="0">公有</option>
					</select>
					</dd>				  
				</dl>
				<dl >
					<dt>是否首页推荐：</dt>
					<dd>
					<select name="lvBlogContent.isRecommend" >
					<option <c:if test="${lvBlogContent.isRecommend==1 }">selected="selected"</c:if>value="1">是</option>
					<option <c:if test="${lvBlogContent.isRecommend==0 }">selected="selected"</c:if>value="0">否</option>
					</select>
					</dd>				  
				</dl>
				<dl >
					<dt>排序值：</dt>
					<dd>
					 <input name="lvBlogContent.orderId" type="text" size="30" class="digits"
							maxlength="4"  value="${lvBlogContent.orderId}"/>
						
					</dd>				  
				</dl >
					<dl>
					<dt>浏览数：</dt>
					<dd>
					<input name="lvBlogContent.clickNum" type="text" size="30" class="digits"
							maxlength="11"  value="${lvBlogContent.clickNum}"/>
						
					</dd>				  
				</dl>
				
				<dl>
					<dt>博客标签：</dt>
					<dd>
					<input name="lvBlogContent.keyword" type="text" size="30" value="${lvBlogContent.keyword}" readonly="readonly"/>
				    <a class="btnLook" target="dialog" href="lvBlogTagsAction!select.action?json.navTabId=lvBlogTags_1&lvBlogTags.storeId=${lvBlogContent.storeId }" lookupGroup="lvBlogContent" width="800" height="400" mask="true">查找带回</a>
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
