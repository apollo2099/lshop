<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
		<form method="post" action="lvActivityAction!edit.action?json.navTabId=${json.navTabId}" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data">
			<div class="pageFormContent" layoutH="56">
				<!-- 生成表单 -->
				<input name="lvActivityShare.id" type="hidden" size="30" value="${lvActivityShare.id}"/>
				<input name="lvActivityShare.activityCode" type="hidden" size="30" value="${lvActivityShare.activityCode}"/>
				<input name="lvActivityShare.givenProductCode" type="hidden" size="30" value="${lvActivityShare.givenProductCode}"/>
				<input name="lvActivityShare.grantTotal" type="hidden" size="30" value="${lvActivityShare.grantTotal}"/>
				<input name="lvActivityShare.uncollectedTotal" type="hidden" size="30" value="${lvActivityShare.uncollectedTotal}"/>
				<input name="lvActivityShare.givenTypeNum" type="hidden" size="30" value="${lvActivityShare.givenTypeNum}"/>
				<input name="lvActivityShare.shareImage" type="hidden" size="30" value="${lvActivityShare.shareImage}"/>
				<input name="lvActivityShare.code" type="hidden" size="30" value="${lvActivityShare.code}"/>
				<input name="lvActivityShare.createTime" type="hidden" size="30" value="${lvActivityShare.createTime}"/>
				<input name="lvActivityShare.mallFlag" type="hidden" value="${lvActivityShare.mallFlag }">
				
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
				    <select name="lvActivityShare.mallFlag" style="width:200px;" disabled="disabled">
						<option value="">==请选择==</option>
						<c:foreach items="${mallList }" var="s">
						  <option value="${s.mallSystemFlag }" <c:if test="${s.mallSystemFlag==lvActivityShare.mallFlag }">selected="selected"</c:if>>${s.mallSystemName }</option>
						</c:foreach>
					  </select>
				    </dd>
				</dl>
                 <div class="divider"></div>
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <select disabled="disabled" name="lvActivityShare.givenTypeName" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvActivityShare.givenTypeNum==1 }">selected="selected"</c:if>>赠送优惠券</option>
						<option value="2" <c:if test="${lvActivityShare.givenTypeNum==2 }">selected="selected"</c:if>>赠送抽奖机会</option>
					  </select>
				    </dd>
				</dl>
				<c:if test="${lvActivityShare.givenTypeNum==1 }">
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				   
				   <input class="required" name="lvActivityShare.givenTypeName" value="${lvActivityShare.givenTypeName}" readonly="readonly"/>
			                 优惠券剩余${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber }张
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="" type="text"  maxlength="64" class="required" size="32" value="${lvActivityShare.grantTotal+lvActivityShare.uncollectedTotal}" readonly="true"/>
				    </dd>
				</dl>
				<dl>
				    <dt>追加优惠券数量：</dt>
				    <dd>
				     <input name="lvActivityShare.addTotal" type="text"  maxlength="4" size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
				</c:if>
				<c:if test="${lvActivityShare.givenTypeNum==2 }">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input class="required" name="lvActivityShare.givenTypeName" type="text" readonly="readonly" value="${lvActivityShare.givenTypeName }"/>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityShare.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" value="${lvActivityShare.lotteryTotal }"/>
						</dd>
					</dl>
					<dl>
						<dt>每日参与活动次数：</dt>
						<dd>
						 <input name="lvActivityShare.partakeNum" type="text" size="32" maxlength="4" class="required digitsNoZore" value="${lvActivityShare.partakeNum }"/>
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
				    <dt>微信分享连接：</dt>
				    <dd>
				      <input name="lvActivityShare.shareLink" type="text"  maxlength="128" class="required" size="32" value="${lvActivityShare.shareLink }"/>
				    </dd>
				</dl>
				 <dl>
				    <dt>微博分享图片：</dt>
				    <dd>
				      <input name="img" type="file"  maxlength="64" size="32"/>
				    </dd>
				</dl>
					<dl class="nowrap">
					<dt>微博分享描述：</dt>
					<dd>
				        <textarea rows="5" cols="60" name="lvActivityShare.shareDesc" maxlength="200" class="required">${lvActivityShare.shareDesc }</textarea>
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