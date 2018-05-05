<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
	<form method="post" action="lvProductCommentAction!audit.action?json.navTabId=${json.navTabId }"
	 class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	 <input type="hidden" name="lvOrder.oid" value="${lvOrder.oid }">
		<div class="pageFormContent" layoutH="56">
	    <div class="viewInfo" layoutH="97">
		<!--取值 -->
				<dl>
				<dt>订单编号：</dt>
					<dd>
						${lvProductComment.oid }
					</dd>				  
				</dl>
				<dl>
				<dt>产品名称：</dt>
					<dd>
                         <c:foreach items="${productList}" var="product">
                           <c:if test="${lvProductComment.productCode==product.code}">${product.productName}</c:if>  
						 </c:foreach>
					</dd>				  
				</dl>
				<dl>
				<dt>用户昵称：</dt>
				    <dd>
				       ${lvProductComment.nickname }
				    </dd>
				</dl>
				<dl class="nowrap">
				<dt>评论内容：</dt>
					<dd>
                        <textarea  name="lvProductComment.content" rows="5" cols="80">${lvProductComment.content }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
				<dt>评论图片：</dt>
					<dd>
                    <c:if test="${lvProductComment.commentImages!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${lvProductComment.commentImages}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="900" height="600"><img src="${lvProductComment.commentImages}" width="60"  height="60"/></a>
					</c:if>
					</dd>				  
				</dl>
				<dl>
				<dt>评分：</dt>
					<dd>
                        ${lvProductComment.score }
					</dd>				  
				</dl>
				<dl>
				<dt>评分等级：</dt>
					<dd>
                        <c:if test="${lvProductComment.grade==1}">差评</c:if>
                        <c:if test="${lvProductComment.grade==2}">中评</c:if>
                        <c:if test="${lvProductComment.grade==3}">好评</c:if>
					</dd>				  
				</dl>
				<dl>
				<dt>评论时间：</dt>
					<dd>
                        <s:date name="lvProductComment.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</dd>				  
				</dl>
			    <dl>
				<dt>是否审核：</dt>
					<dd>
                        <c:if test="${lvProductComment.isCheck==0}">未审核</c:if>
                        <c:if test="${lvProductComment.isCheck==1}">已审核</c:if>
					</dd>				  
				</dl>
				<dl>
				<dt>排序：</dt>
					<dd>
                        ${lvProductComment.sortId }
					</dd>				  
				</dl>


		</div>
		</div>
           <div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>

