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
				<input name="lvActivityAppointProduct.id" type="hidden" size="30" value="${lvActivityAppointProduct.id}"/>
				<input name="lvActivityAppointProduct.activityCode" type="hidden" size="30" value="${lvActivityAppointProduct.activityCode}"/>
				<input name="lvActivityAppointProduct.code" type="hidden" size="30" value="${lvActivityAppointProduct.code}"/>
				<input name="lvActivityAppointProduct.createTime" type="hidden" size="30" value="${lvActivityAppointProduct.createTime}"/>
				<input name="lvActivityAppointProduct.grantTotal" type="hidden" size="30" value="${lvActivityAppointProduct.grantTotal}"/>
				<input name="lvActivityAppointProduct.uncollectedTotal" type="hidden" size="30" value="${lvActivityAppointProduct.uncollectedTotal}"/>
				<input name="lvActivityAppointProduct.givenTypeNum" type="hidden" size="30" value="${lvActivityAppointProduct.givenTypeNum}"/>
				<input name="lvActivityAppointProduct.givenProductCode" type="hidden" size="30" value="${lvActivityAppointProduct.givenProductCode}"/>
				<input name="lvActivityAppointProduct.storeId" type="hidden" value="${lvActivityAppointProduct.storeId }">
				
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
					<dt>活动英文名称：</dt>
					<dd>
						<input name="lvActivity.activityTitleEn" type="text" size="64" maxlength="120" class="required" value="${lvActivity.activityTitleEn }"/>
					</dd>				  
				</dl>
				<dl>
				    <dt>活动类型：</dt>
				    <dd>
					  <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
				    </dd>
				</dl>
				<dl class="nowrap">
				<dt>指定商品：</dt>
				    <dd>
				    </dd>
				</dl>
				<table id="tab" class="table" width="80%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="20%">所属关系</th>
					    <th width="45%">商品名称</th>
						<th width="35%">商品价格</th>
				   </tr>
				   </thead>
				   <tbody id="tabDetails">
				   <c:foreach items="${acProductList }" var="item">
				   <tr>
				     <td>${item.id }</td>
				     <td>${item.storeName}</td>
				     <td>${item.productName }</td>
				     <td>${item.currency} ${item.price}</td>
				   </tr>
				   </c:foreach>
				   </tbody>
			   </table>
			   
                 <div class="divider"></div>
				<dl>
				    <dt>赠送活动类型：</dt>
				    <dd>
				     <select disabled="disabled" name="lvActivityAppointProduct.givenTypeName" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvActivityAppointProduct.givenTypeNum==1 }">selected="selected"</c:if>>赠送优惠券</option>
						<option value="2" <c:if test="${lvActivityAppointProduct.givenTypeNum==2 }">selected="selected"</c:if>>赠送抽奖机会</option>
						<option value="3" <c:if test="${lvActivityAppointProduct.givenTypeNum==3 }">selected="selected"</c:if>>赠送礼品</option>
					  </select>
				    </dd>
				</dl>
				<c:if test="${lvActivityAppointProduct.givenTypeNum==1 }">
				<dl>
				    <dt>优惠券：</dt>
				    <dd>
				   
				   <input class="required" name="lvActivityAppointProduct.givenTypeName" value="${lvActivityAppointProduct.givenTypeName}" readonly="readonly"/>
			                 优惠券剩余${lvCouponType.total-lvCouponType.noGrantNumber-lvCouponType.grantNumber-lvCouponType.usedNumber }张
				    </dd>
				</dl>
				<dl>
				    <dt>赠送总量：</dt>
				    <dd>
				     <input name="" type="text"  maxlength="64" class="required" size="32" value="${lvActivityAppointProduct.grantTotal+lvActivityAppointProduct.uncollectedTotal}" readonly="readonly"/>
				    </dd>
				</dl>
				<dl>
				    <dt>追加优惠券数量：</dt>
				    <dd>
				     <input name="lvActivityAppointProduct.addTotal" type="text"  maxlength="4" size="32" class="digitsNoZore"/>
				    </dd>
				</dl>
				</c:if>
				<c:if test="${lvActivityAppointProduct.givenTypeNum==2 }">
					<dl class="nowrap">
						<dt>抽奖活动：</dt>
						<dd>
				   <input class="required" name="lvActivityAppointProduct.givenTypeName" type="text" readonly="readonly" value="${lvActivityAppointProduct.givenTypeName }"/>	
						</dd>
					</dl>
					<dl>
						<dt>抽奖次数：</dt>
						<dd>
						 <input name="lvActivityAppointProduct.lotteryTotal" type="text"  maxlength="4" class="required digitsNoZore" size="32" value="${lvActivityAppointProduct.lotteryTotal }"/>
						</dd>
					</dl>
				</c:if>
			   <c:if test="${lvActivityAppointProduct.givenTypeNum==3 }">
				<dl class="nowrap">
						<dt>选择赠品：</dt>
						<dd>
						</dd>
					</dl>
				   <table id="tabGift" class="table" width="100%"  >
				   <thead>
				   <tr>
						<th width="5%">ID</th>
					    <th width="25%">赠品中文名称</th>
					    <th width="10%">赠品总数</th>
					    <th width="20%">每次赠送数量</th>
						<th width="20%">追加赠品数量</th>
						<th width="20%">排序</th>
				   </tr>
				   </thead>
				   <tbody id="tabGiftDetails">
				   <c:foreach items="${giftList }" var="item" varStatus="index">
				   <tr>
				     <td>${item.id }
				     <input type="hidden" name="lvActivityAppointProduct.acGiftList[${index.index }].id"  value="${item.id }"/>
				     <input type="hidden" name="lvActivityAppointProduct.acGiftList[${index.index }].giftTotalNum"  value="${item.giftTotalNum }"/>
				     <input type="hidden" name="lvActivityAppointProduct.acGiftList[${index.index }].giftHaveNum"  value="${item.giftHaveNum }"/>
				     <input type="hidden" name="lvActivityAppointProduct.acGiftList[${index.index }].giftSerplusNum"  value="${item.giftSerplusNum }"/>
				     </td>
				     <td>${item.giftName }</td>
				     <td>${item.giftTotalNum }</td>
				     <td><input type="text" name="lvActivityAppointProduct.acGiftList[${index.index }].giftEveryNum" class="required digits" maxlength="5" value="${item.giftEveryNum }"/></td>
				     <td><input type="text" name="lvActivityAppointProduct.acGiftList[${index.index }].giftAddNum" class="digits"  maxlength="5" value="0"/></td>
				     <td><input type="text" name="lvActivityAppointProduct.acGiftList[${index.index }].orderValue"  class="required digits" maxlength="5" value="${item.orderValue }"/></td>
				   </tr>
				   </c:foreach>
				   </tbody>
			      </table>
				</c:if>
                <div class="divider"></div>
                <dl>
				    <dt>活动是否允许使用优惠券：</dt>
				    <dd>
				     <input name="lvActivityAppointProduct.isUseCoupon" type="radio"  value="1" <c:if test="${lvActivityAppointProduct.isUseCoupon==1 }"> checked="checked"</c:if>/>是
				     <input name="lvActivityAppointProduct.isUseCoupon" type="radio"  value="0" disabled="disabled"/>否
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