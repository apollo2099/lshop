<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<div class="page">
	<div class="pageContent">
		<form method="post" action="wxMenuMngAction!createMenusToWeixin.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<p>
							<label>公众号名称：</label>
							<select name="wxMenu.wxhConfigId" id="wxhConfigId"  class="required">
			                <option value="">==请选择==</option>
			                <c:foreach items="${wxhConfigList}" var="wxhConfig">
			                <option  value="${wxhConfig.id }">${wxhConfig.wxhName}</option>
		                    </c:foreach>
		                    </select>
						</p>
						
							<p>
						
						<font color="red">&nbsp;&nbsp;注释：最多允许3个父菜单，父菜单最多有5个子菜单 <br> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否则自定义微信菜单会失败</font>
						</p>
						
						
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:void(0)"><span>保存</span></a></li>-->
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">推送</button></div></div></li>
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>