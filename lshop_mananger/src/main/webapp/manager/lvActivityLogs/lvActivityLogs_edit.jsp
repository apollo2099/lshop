<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityLogsAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
						<p>
							<label>id：</label>
							<input name="lvActivityLogs.id" type="text" size="30" value="${lvActivityLogs.id}"/>
						</p>
						<p>
							<label>活动code：</label>
							<input name="lvActivityLogs.activityCode" type="text" size="30" value="${lvActivityLogs.activityCode}"/>
						</p>
						<p>
							<label>活动参与订单号：</label>
							<input name="lvActivityLogs.orderId" type="text" size="30" value="${lvActivityLogs.orderId}"/>
						</p>
						<p>
							<label>活动物品信息：</label>
							<input name="lvActivityLogs.productInfo" type="text" size="30" value="${lvActivityLogs.productInfo}"/>
						</p>
						<p>
							<label>活动参与人：</label>
							<input name="lvActivityLogs.actors" type="text" size="30" value="${lvActivityLogs.actors}"/>
						</p>
						<p>
							<label>活动参与人ip：</label>
							<input name="lvActivityLogs.actorsIp" type="text" size="30" value="${lvActivityLogs.actorsIp}"/>
						</p>
						<p>
							<label>活动参与时间：</label>
							<input name="lvActivityLogs.actorsTime" class="textInput date" readonly="readonly" pattern="yyyy-MM-dd HH:mm:ss" type="text" size="30" 
								   value=""/>
								 <s:date name="actorsTime" format="yyyy-MM-dd HH:mm"/>
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