<%@ page language="java" pageEncoding="utf-8" import="com.lshop.common.util.Constants"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post"
			action="lvOrderAction!comment.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="ids" type=hidden value="${ids}"/>
			<input type="text" name="versionTime" value="${versionTime }">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>评分：</label>
							<input name="lvOrderComment.score" type="text" class="required digits" min="1" max="5"  />
						</p>
						<!-- 
						<p>
							<label>评分等级：</label>
							<select name="lvOrderComment.grade" class="required">
							 <option value="">==</option>
							 <option value="1">差评</option>
							 <option value="2">中评</option>
							 <option value="3">好评</option>
							</select>
						</p>
						 -->
						<p>
						    <label>评论内容：</label>
						    <textarea rows="8" cols="50" name="lvOrderComment.content" maxlength="200" class="required"></textarea>
						<p>
								
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									评论
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