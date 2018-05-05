<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvShopProductTypeAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvShopProductType.modifyUserId" type="hidden" size="30" value="${lvShopProductType.modifyUserId}"/>
			<input name="lvShopProductType.id" type="hidden" size="30" value="${lvShopProductType.id}"/>
			<input name="lvShopProductType.parentCode" type="hidden" size="30" value="${lvShopProductType.parentCode}"/>
			<input name="lvShopProductType.storeId" type="hidden" size="30" value="${lvShopProductType.storeId}"/>
			<input name="lvShopProductType.code" type="hidden" size="30" value="${lvShopProductType.code}"/>
			<input name="lvShopProductType.oldTypeName" type="hidden" value="${lvShopProductType.typeName }">
				<!-- 生成表单 -->
						<p>
							<label>分类名称：</label>
							<input name="lvShopProductType.typeName" type="text" size="30" maxlength="20" class="required" value="${lvShopProductType.typeName}"/>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvShopProductType.orderValue" type="text" size="30" maxlength="10" class="required digits" value="${lvShopProductType.orderValue}"/>
						</p>
						<p>
							<label>创建时间：</label>
							<input name="lvShopProductType.createTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopProductType.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>
						<p>
							<label>修改时间：</label>
							<input name="lvShopProductType.modifyTime"  readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value="<s:date name="lvShopProductType.modifyTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
								 
						</p>
						<p>
							<label>修改人名称：</label>
							<input name="lvShopProductType.modifyUserName" type="text" size="30" value="${lvShopProductType.modifyUserName}" readonly="readonly"/>
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