<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvProductImageAction!add.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" name="lvProductImage.productCode" value="${lvProductImage.productCode }">
			<input type="hidden" name="lvProductImage.storeId" value="${lvProductImage.storeId }">
  		    <div class="pageFormContent" layoutH="56">
				<dl>
				<dt>产品图片：</dt>
					<dd>
                       <input name="img" type="file" size="20" class="required"/>
					</dd>				  
				</dl>
				<dl>
				<dt>排序值：</dt>
					<dd>
                       <input name="lvProductImage.sortId" type="text" size="20" maxlength="4" class="required digits" value="0"/>
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
