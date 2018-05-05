<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvBlogSubscribeAction!save.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			
  		<div class="pageFormContent" layoutH="56">
				<dl class="nowrap">
					<dt>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</dt>
					<dd>
					    <input name="lvBlogSubscribe.title" type="text" size="50" class="required " />
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 广告图片上传：</dt>
					<dd>
                         <input name="lvBlogSubscribe.adBanner" type="file" size="40" />
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 广告URL：</dt>
					<dd>
                        <input name="lvBlogSubscribe.adUrl" type="text" size="50" />
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 发送周期：</dt>
					<dd>
						<select name="lvBlogSubscribe.sendCycle" class="required">
						  <option>---</option>
						  <option value="0" >永不发送</option>
						  <option value="1" >每天</option>
						  <option value="2" >每周一</option>
						  <option value="3" >每周三</option>
						  <option value="4" >每个月</option>
						</select>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt> 发送时间：</dt>
					<dd>
                         <input name="lvBlogSubscribe.sendTime" type="text" size="50" class="required date"  format="HH:mm:ss" />(注意：时间格式 HH:mm:ss)
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
