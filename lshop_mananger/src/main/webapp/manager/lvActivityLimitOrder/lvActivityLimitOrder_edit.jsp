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
				<!-- 生成表单 -->
				<input name="lvActivityLimitOrder.id" type="hidden" size="30" value="${lvActivityLimitOrder.id}"/>
				<input name="lvActivityLimitOrder.activityCode" type="hidden" size="30" value="${lvActivityLimitOrder.activityCode}"/>
				<input name="lvActivityLimitOrder.code" type="hidden" size="30" value="${lvActivityLimitOrder.code}"/>
				<input name="lvActivityLimitOrder.createTime" type="hidden" size="30" value="${lvActivityLimitOrder.createTime}"/>
				<input name="lvActivityLimitOrder.givenProductCode" type="hidden" value="${lvActivityLimitOrder.givenProductCode }" />
				<input name="lvActivityLimitOrder.grantTotal" type="hidden" value="${lvActivityLimitOrder.grantTotal }" />
				<input name="lvActivityLimitOrder.occupiedTotal" type="hidden" value="${lvActivityLimitOrder.occupiedTotal}" />
				<input name="lvActivityLimitOrder.uncollectedTotal" type="hidden" value="${lvActivityLimitOrder.uncollectedTotal }" />
				<input name="lvActivityLimitOrder.givenTypeNum" type="hidden" value="${lvActivityLimitOrder.givenTypeNum }">
				
				<input name="lvActivity.id" type="hidden" value="${lvActivity.id }"/>
				<input name="lvActivity.code" type="hidden" value="${lvActivity.code }"/>
				<input name="lvActivity.typeKey" type="hidden" value="${lvActivity.typeKey }"/>
				<input name="lvActivity.createTime" type="hidden" value="${lvActivity.createTime }"/>	
				<input name="lvActivity.status" type="hidden" value="${lvActivity.status }">
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
				<dl>
				    <dt>选择店铺：</dt>
				    <dd>
				     <select onchange="changeActivity()" name="lvActivityLimitOrder.storeId" style="width:200px;">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList }" var="s">
						  <option value="${s.storeFlag }" <c:if test="${lvActivityLimitOrder.storeId==s.storeFlag }">selected="selected"</c:if>>${s.name }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <gv:dictionary type="select" code="ACTIVITY_GIVEN_TYPE_NUM"  value="${lvActivityLimitOrder.givenTypeNum }" change="changeGivenType()" id="givenTypeSel" disabled="disabled"/>
				    </dd>
				</dl>
				<c:if test="${lvActivityLimitOrder.givenTypeNum==1 }">
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				   
				   <input class="required" name="lvActivityLimitOrder.givenTypeName" value="${lvCouponType.name}" readonly="readonly"/>
			                 优惠券剩余${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber }张
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="" type="text"  maxlength="64" class="required" size="32" value="${lvActivityLimitOrder.grantTotal+ lvActivityLimitOrder.occupiedTotal+lvActivityLimitOrder.uncollectedTotal}" readonly="true"/>
				    </dd>
				</dl>
				<dl>
				    <dt>追加优惠券数量：</dt>
				    <dd>
				     <input name="lvActivityLimitOrder.addTotal" type="text"  maxlength="4" size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
				</c:if>
				<c:if test="${lvActivityLimitOrder.givenTypeNum==2 }">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input class="required" name="lvActivityLimitOrder.givenTypeName" type="text" readonly="readonly" value="${lvActivityLimitOrder.givenTypeName }"/>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityLimitOrder.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" value="${lvActivityLimitOrder.lotteryTotal }"/>
						</dd>
					</dl>
				</c:if>
				
                 <div class="divider"></div>
				<dl>
				    <dt>订单满额送：</dt>
				    <dd>
				     <input name="lvActivityLimitOrder.limitTotalPrice" type="text" size="32" maxlength="5" class="required numberAPoint" value="${lvActivityLimitOrder.limitTotalPrice}"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动开始时间：</dt>
				    <dd>
				        <input type="text" name="lvActivity.startTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
                        value="<s:date name="lvActivity.startTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
				    </dd>
				</dl>
				<dl>
				    <dt>活动结束时间：</dt>
				    <dd>
				     	<input type="text" name="lvActivity.endTime" class="date" format="yyyy-MM-dd HH:mm:ss" readonly="true" size="32"
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
					<li>
						<div class="button"><div class="buttonContent"><button class="close">取消</button></div></div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>