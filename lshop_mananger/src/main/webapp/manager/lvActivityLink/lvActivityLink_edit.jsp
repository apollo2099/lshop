<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<div class="pageFormContent" layoutH="56">
				<input name="lvActivityLink.activityHtml" type="hidden" size="30" value="${lvActivityLink.activityHtml}"/>
				<input name="lvActivityLink.id" type="hidden" size="30" value="${lvActivityLink.id}"/>
				<input name="lvActivityLink.activityCode" type="hidden" size="30" value="${lvActivityLink.activityCode}"/>
				<input name="lvActivityLink.createTime" type="hidden" size="30" value="${lvActivityLink.createTime}"/>
				<input name="lvActivityLink.givenProductCode" type="hidden" value="${lvActivityLink.givenProductCode }" />
				<input name="lvActivityLink.grantTotal" type="hidden" value="${lvActivityLink.grantTotal }" />
				<input name="lvActivityLink.uncollectedTotal" type="hidden" value="${lvActivityLink.uncollectedTotal}" />
				<input name="lvActivityLink.givenTypeNum" type="hidden" value="${lvActivityLink.givenTypeNum}" />
				<input name="lvActivityLink.code" type="hidden" value="${lvActivityLink.code}" />
				<input name="lvActivityLink.strategyType" type="hidden" value="${lvActivityLink.strategyType}" />
			
				<input name="lvActivity.id" type="hidden" value="${lvActivity.id }"/>
				<input name="lvActivity.code" type="hidden" value="${lvActivity.code }"/>
				<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
				<input name="lvActivity.status" type="hidden" value="${lvActivity.status}"/>
				<input name="lvActivity.createTime" type="hidden" value="${lvActivity.createTime }"/>	
				<!-- 生成表单 -->
				<dl>
					<dt>活动名称：</dt>
					<dd>
						<input name="lvActivity.activityTitle" type="text" size="64" maxlength="120" class="required" value="${lvActivity.activityTitle}"/>
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
				     <select disabled="disabled" name="lvActivityLink.givenTypeNum" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="1" selected="selected">赠送优惠券</option>
					  </select>
				    </dd>
				</dl>
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				   <input class="required" name="lvActivityLink.givenTypeName" value="${lvCouponType.name}"  readonly="readonly"/>
			                 优惠券剩余${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber }张
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="" type="text"  maxlength="64" class="required" size="32" value="${lvActivityLink.grantTotal+ lvActivityLink.uncollectedTotal}" readonly="readonly"/>
				    </dd>
				</dl>
				<dl>
				    <dt>领券策略：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_LINK_STRATEGYTYPE" name="lvActivityLink.strategyType" value="${lvActivityLink.strategyType }" disabled="disabled"/>
				    </dd>
				</dl>
				
				<dl>
				    <dt>追加优惠券数量：</dt>
				    <dd>
				     <input name="lvActivityLink.addTotal" type="text"  maxlength="4" size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>每个用户领取次数：</dt>
				    <dd>
				     <input name="lvActivityLink.limitNum" type="text" size="32" maxlength="4" class="required digits" value="${lvActivityLink.limitNum}"/>
				    </dd>
				</dl>
                </div>

				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				        <input type="text" name="lvActivity.startTime" class="required date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     	<input type="text" name="lvActivity.endTime" class="required date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.endTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动状态：</dt>
				    <dd>
				     	<gv:dictionary type="select" code="ACTIVITY_STATUS" name="lvActivity.status" value="${lvActivity.status }" disabled="disabled"/>
				    </dd>
				</dl>
				<dl>
					<dt>活动描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivity.remark" maxlength="200">${lvActivity.remark }</textarea>
					</dd>				  
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</div>