<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
			<div class="blog_right">
				<div class="blog_sort">
					<span class="blog_sort_ban">Blog 分类</span>
			      	<ul>
			      		<li><span>[${contentSum}]</span><a href="/blog.html">所有文章</a>  </li>
					  	<c:foreach items="${typeList}" var="t" varStatus="index" >
						   <c:foreach items="${typeCountList}" var="sum">
						    <c:if test="${t.id==sum.type}">
						       <li><span>[${sum.num}]</span><a href="/blog/blogType${t.id }.html">${t.type }</a></li>
						    </c:if>
						   </c:foreach>
					  	</c:foreach>
					</ul>
				</div>
				
				<div class="blog_label">
					<span class="blog_label_ban">热门标签</span>
						<ul>
							<li>
						      	<c:foreach items="${tagsList}" var="tags">
						      		<a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${tags.tagName }&lvBlogContent.type=">${tags.tagName }</a>
						      	</c:foreach>
						    </li>
						</ul>
						
				</div>	
				
				<div class="blog_hot">
					<span class="blog_hot_ban">热点博文</span>
			      	<ul>
			     		<s:iterator value="#request.contentList" status="sta" id="co">
			      			<li><a href="/blog/blogInfo${co.id }.html">
				      		 <s:if test="%{null!=#co.title&&#co.title.length()>16}">
			                        <s:property value="%{#co.title.substring(0, 16)}" />……
			                 </s:if>
			                 <s:else><s:property value="#co.title"/></s:else>
			      		</a></li>
			      		</s:iterator>
			      	</ul>
				</div>	
				
			</div>