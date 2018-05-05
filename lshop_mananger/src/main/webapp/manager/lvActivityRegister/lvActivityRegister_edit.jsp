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
				<input name="lvActivityRegister.id" type="hidden" size="30" value="${lvActivityRegister.id}"/>
				<input name="lvActivityRegister.activityCode" type="hidden" size="30" value="${lvActivityRegister.activityCode}"/>
				<input name="lvActivityRegister.code" type="hidden" size="30" value="${lvActivityRegister.code}"/>
				<input name="lvActivityRegister.createTime" type="hidden" size="30" value="${lvActivityRegister.createTime}"/>
				<input name="lvActivityRegister.grantTotal" type="hidden" size="30" value="${lvActivityRegister.grantTotal}"/>
				<input name="lvActivityRegister.uncollectedTotal" type="hidden" size="30" value="${lvActivityRegister.uncollectedTotal}"/>
				<input name="lvActivityRegister.givenTypeNum" type="hidden" size="30" value="${lvActivityRegister.givenTypeNum}"/>
				<input name="lvActivityRegister.givenProductCode" type="hidden" size="30" value="${lvActivityRegister.givenProductCode}"/>
				<input name="lvActivityRegister.storeId" type="hidden" value="${lvActivityRegister.storeId }">
				
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
				    <dt>活动体系：</dt>
				    <dd>
				    <select name="lvActivityRegister.storeId" style="width:200px;" disabled="disabled">
						<option value="">==请选择==</option>
						<c:foreach items="${mallList }" var="s">
						  <option value="${s.mallSystemFlag }" <c:if test="${s.mallSystemFlag==lvActivityRegister.storeId }">selected="selected"</c:if>>${s.mallSystemName }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <select disabled="disabled" name="lvActivityRegister.givenTypeName" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvActivityRegister.givenTypeNum==1 }">selected="selected"</c:if>>赠送优惠券</option>
						<option value="2" <c:if test="${lvActivityRegister.givenTypeNum==2 }">selected="selected"</c:if>>赠送抽奖机会</option>
					  </select>
				    </dd>
				</dl>
				<c:if test="${lvActivityRegister.givenTypeNum==1 }">
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				   
				   <input class="required" name="lvActivityRegister.givenTypeName" value="${lvActivityRegister.givenTypeName}" readonly="readonly"/>
			                 优惠券剩余${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber }张
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="" type="text"  maxlength="64" class="required" size="32" value="${lvActivityRegister.grantTotal+lvActivityRegister.uncollectedTotal}" readonly="readonly"/>
				    </dd>
				</dl>
				<dl>
				    <dt>追加优惠券数量：</dt>
				    <dd>
				     <input name="lvActivityRegister.addTotal" type="text"  maxlength="4" size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
				</c:if>
				<c:if test="${lvActivityRegister.givenTypeNum==2 }">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input class="required" name="lvActivityRegister.givenTypeName" type="text" readonly="readonly" value="${lvActivityRegister.givenTypeName }"/>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityRegister.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" value="${lvActivityRegister.lotteryTotal }"/>
						</dd>
					</dl>
				</c:if>
				

				
                 <div class="divider"></div>
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