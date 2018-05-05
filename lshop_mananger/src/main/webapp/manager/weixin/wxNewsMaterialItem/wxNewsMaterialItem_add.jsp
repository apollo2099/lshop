<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxNewsMaterialItemMngAction!save.action?json.navTabId=${json.navTabId}"
		 class="pageForm required-validate"
		 onsubmit="return iframeCallback(this, dialogAjaxDone);" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<p>
							<label>公众号名称：</label>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxNewsMaterialItem.wxhConfigId == wxhConfig.id}">${wxhConfig.wxhName}</c:if>
						</c:foreach>
							<input name="wxNewsMaterialItem.wxhConfigId" class="required" type="hidden" value="${wxNewsMaterialItem.wxhConfigId }" size="30" maxlength="10" class="false"/>
						</p>	
						
						<p>
							<label>关联图文素材名称：</label>
							${newsMaterial.name }
							<input name="wxNewsMaterialItem.newsId" type="hidden" value="${wxNewsMaterialItem.newsId }" size="30"/>
						</p>
						<p>
							<label>图文消息类型</label>
							
							<c:if test="${wxNewsMaterialItem.newsType == 1 }">
							单图文
							</c:if>
							<c:if test="${wxNewsMaterialItem.newsType == 2 }">
							多图文
							</c:if>
							<input name="wxNewsMaterialItem.newsType" type="hidden" value="${wxNewsMaterialItem.newsType }" size="30" maxlength="10"  />
						</p>
						
						<p>
							<label>标题：</label>
							<input name="wxNewsMaterialItem.title" type="text" size="30" maxlength="64" class="required"/>
						</p>
						
						<p>
							<label>点击后跳转链接：</label>
							<input name="wxNewsMaterialItem.url" type="text" size="30" maxlength="256" class="required"/>
						</p>
						<p>
							<label>图片链接：</label>
							<input name="img" type="file" size="17" class="required"/>
							
						</p>
						<p>
							<label>描述：</label>
							<textarea  name="wxNewsMaterialItem.description" rows="7" cols="30" class="required" maxlength="412"></textarea>
							
						</p>
						
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