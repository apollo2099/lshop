<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvStoreAreaAction!saveCity.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvStoreArea.parentCode" type="hidden" size="30" maxlength="32" value="${lvStoreArea.parentCode }"/>
				<!-- 生成表单 -->
				        <p>
							<label>城市名称：</label>
                            <input name="lvStoreArea.areaName" type="text" size="30" maxlength="50" value="${lvStoreArea.areaName}" class="required"/>
						</p>
						<!-- 
						 <p>
							<label>英文名称：</label>
                            <input name="lvStoreArea.areaNameEn" type="text" size="30" maxlength="50" value="${lvStoreArea.areaNameEn}" class="required"/>
						</p>
						 -->
						<p>
							<label>排序值：</label>
							<input name="lvStoreArea.orderValue" type="text" size="10" maxlength="10" class="required digits" value="0"/>
						</p>
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