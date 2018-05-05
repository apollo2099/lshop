<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvNavigationMngAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" 
		onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			
			<input name="lvNavigation.id" type="hidden" value="${lvNavigation.id}"/>
			<input name="lvNavigation.navParentId" type="hidden" value="${lvNavigation.navParentId}"/>
			<input name="lvNavigation.navImage" type="hidden"  value="${lvNavigation.navImage}"/>
			<input name="lvNavigation.createTime" type="hidden" value="<s:date name="lvNavigation.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/> 
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				       <p>
							<label>所属关系：</label>
							<c:if test="${empty parentMenu}">
							<select name="lvNavigation.storeId" class="required">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvNavigation.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						    </select>
						    </c:if>
						    <c:if test="${not empty parentMenu}">
						    <input name="lvNavigation.storeId" type="hidden" value="${lvNavigation.storeId}"/>
						    <select name="lvNavigation.storeId" class="required" disabled="disabled">
						    <c:foreach items="${storeList}" var="store">
							<option value="${store.storeFlag }" <c:if test="${store.storeFlag==lvNavigation.storeId}">selected="selected"</c:if>>${store.name}</option>
						    </c:foreach>
						     </select>
						    <label style="color:red;">请到父导航菜单中修改</label>
						    </c:if>
						</p>  
						<p>
							<label>导航名称：</label>
							<input name="lvNavigation.navName" type="text" class="required" size="30" maxlength="32" value="${lvNavigation.navName}"/>
						</p>
						<p>
							<label>导航URL链接：</label>
							<input name="lvNavigation.navUrl" type="text" class="required" size="30" maxlength="128" value="${lvNavigation.navUrl}"/>
						</p>
						<p>
							<label>导航图片：</label>
							<input name="img" type="file" size="20" class="" maxlength="64"/>
						</p>
						<p>
							<label>着色标识：</label>
							<input name="lvNavigation.navFlag" type="text" size="30" maxlength="128" value="${lvNavigation.navFlag}" />
						</p>
						<p>
							<label>打开方式：</label>
							<s:select list="#{0:'本窗口打开',1:'新窗口打开'}" name="lvNavigation.openTarget"></s:select>
						</p>
						<p>
							<label>排序值：</label>
							<input name="lvNavigation.orderValue" type="text" size="7" maxlength="4" value="${lvNavigation.orderValue}" class="required digits" />
						</p>
						<c:if test="${not empty parentMenu}">
						<p>
							<label>父级菜单：</label>
							<input type="text" disabled="disabled"  value="${parentMenu.navName}"/>
						</p>
						</c:if>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>