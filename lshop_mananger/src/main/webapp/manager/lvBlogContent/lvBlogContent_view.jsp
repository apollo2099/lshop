<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
		<div class="viewInfo" >
				<dl class="nowrap">
					<dt>博客标题：</dt>
					<dd>
						${lvBlogContent.title}
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>博客类别：</dt>
					<dd>
				     <s:iterator value="#request.typeList" id="t">
					     <c:if test="${lvBlogContent.type==t.id }">${t.type}</c:if>
					 </s:iterator>
					</dd>				  
				</dl>
				<dl class="nowrap"> 
					<dt>发布时间：</dt>
					<dd>
						<s:date name="lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>文章内容：</dt>
					<dd>
					<textarea  readonly="readonly" class="editor" upImgUrl="/manager/res/upload.action?dir=/images" upImgExt="jpg,jpeg,gif,png" name="lvBlogContent.content" cols="90" rows="15" >${lvBlogContent.content }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>简介：</dt>
					<dd>
					<textarea name="lvBlogContent.intor"  cols="103" rows="5" readonly="readonly">${lvBlogContent.intor }</textarea>
					</dd>				  
				</dl>
				<dl>
					<dt>公开范围：</dt>
					<dd>
					<s:if test="lvBlogContent.isPrivate==1">私有</s:if><s:else>公有</s:else>
					</dd>				  
				</dl>
				<dl >
					<dt>是否首页推荐：</dt>
					<dd>					
					<s:if test="lvBlogContent.isRecommend==1">是</s:if><s:else>否</s:else>
					</dd>				  
				</dl>
				<dl>
					<dt>排序值：</dt>
					<dd>
						${lvBlogContent.orderId}
					</dd>				  
				</dl>
					<dl>
					<dt>浏览数：</dt>
					<dd>
						${lvBlogContent.clickNum}
					</dd>				  
				</dl>
					<dl>
					<dt>博客标签：</dt>
					<dd>
						${lvBlogContent.keyword}
					</dd>				  
				</dl>
		</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>
