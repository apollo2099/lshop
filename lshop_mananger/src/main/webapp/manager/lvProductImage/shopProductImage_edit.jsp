<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
	<form method="post"
			action="lvProductImageAction!edit.action?json.navTabId=${json.navTabId}"
			class="pageForm required-validate"
			onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
			<input type="hidden" name="lvProductImage.id" value="${lvProductImage.id }">
		    <input type="hidden" name="lvProductImage.storeId" value="${lvProductImage.storeId }">
		    <input type="hidden" name="lvProductImage.code" value="${lvProductImage.code }">
		    <input type="hidden" name="lvProductImage.productImage" value="${lvProductImage.productImage }">
		    <input type="hidden" name="lvProductImage.productCode" value="${lvProductImage.productCode }">
  		<div class="pageFormContent" layoutH="56">
				<dl >
				<dt>产品图片：</dt>
				    <dd>
				     <input name="img" type="file" size="20" class="" />
				    </dd>
				</dl>
				<dl>
				<dt>排序值：</dt>
				    <dd>
				  <input type="text" name="lvProductImage.sortId" value="${lvProductImage.sortId }" maxlength="4" class="required digits" size="32">
				    </dd>
				</dl>
				<dl>
	              <dt>创建时间：</dt>
					<dd>
                        <input type="text" name="lvProductImage.createTime"  format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvProductImage.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改人：</dt>
					<dd>
                         <input type="text" name="lvProductImage.modifyUserName"  readonly="true"  value="${lvProductImage.modifyUserName }" size="32"/>
					</dd>				  
				</dl>
				<dl>
				<dt>最后修改时间：</dt>
					<dd>
                       	<input type="text" name="lvProductImage.modifyTime"  format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvProductImage.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
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
