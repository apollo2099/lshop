<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
				     <gv:dictionary type="text" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}"/>
				    </dd>
				</dl>

				<dl>
				    <dt>活动体系：</dt>
				    <dd>
				    <select name="lvActivityLottery.storeId" style="width:200px;">
						<option value="">==请选择==</option>
						<c:foreach items="${mallList }" var="s">
						  <option value="${s.mallSystemFlag }">${s.mallSystemName }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>兑奖截止日期：</dt>
				    <dd>
				     <input name="lvActivityLottery.endTicketDate" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				     <input name="lvActivity.startTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     <input name="lvActivity.endTime" class="required date" readonly="readonly" format="yyyy-MM-dd HH:mm:ss" type="text" size="32"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" value="0"/>
				    </dd>
				</dl>
				<dl class="nowrap">
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200"></textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>活动规则：</dt>
					<dd>
				        <textarea class="editor {internalScript:true,inlineScript:true,linkTag:true}" id="contentId" name="lvActivityLottery.activityRule" upImgUrl="/manager/res/uploadImg.action?dir=/images&marking=ac" upImgExt="jpg,jpeg,gif,png" rows="10" cols="61"></textarea>
					</dd>				  
				</dl>
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