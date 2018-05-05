<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!save.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
			<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
				<!-- 生成表单 -->
				
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
				    <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
				    </dd>
				</dl>
                <div class="divider"></div>
                <div style="display:block" id="givenType">
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <select name="lvActivityLink.givenTypeNum" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="1" selected="selected">赠送优惠券</option>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				<input id="inputOrg1" name="lvActivityLink.givenProductCode" value="" type="hidden"/>
				   <input class="required" name="lvActivityLink.givenTypeName" type="text" readonly="readonly" postField="keyword" lookupGroup="lvActivityLink"/>
				<a class="btnLook" href="lvCouponTypeAction!selectSimpleCouponType.action" lookupGroup="lvActivityLink">查找带回</a>	
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				    <input name="lvActivityLink.uncollectedTotal" type="text" size="32" maxlength="4" class="required digitsNoZore"/>
				    </dd>
				</dl>
                <div class="divider"></div>
                
                <dl>
				    <dt>领券策略：</dt>
				    <dd>
				     <gv:dictionary type="select" code="ACTIVITY_LINK_STRATEGYTYPE" name="lvActivityLink.strategyType" value="1"/>
				    </dd>
				    
				    
				</dl>
				<dl>
				    <dt>每用户最大领取次数：</dt>
				    <dd>
				     <input name="lvActivityLink.limitNum" type="text" size="32" maxlength="4" class="required digits"/>
				    </dd>
				</dl>
                </div>

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
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" />
				    </dd>
				</dl>
				<dl>
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200"></textarea>
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
