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
				<input name="lvActivityMac.id" type="hidden" size="30" value="${lvActivityMac.id}"/>
				<input name="lvActivityMac.activityCode" type="hidden" size="30" value="${lvActivityMac.activityCode}"/>
				<input name="lvActivityMac.code" type="hidden" size="30" value="${lvActivityMac.code}"/>
				<input name="lvActivityMac.createTime" type="hidden" size="30" value="${lvActivityMac.createTime}"/>

				
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
				    <dt>活动优惠金额：</dt>
				    <dd>
				     <input name="lvActivityMac.amount" class="required numberAPoint"  maxlength="5"  type="text" size="32" value="${lvActivityMac.amount }"/>
				    </dd>
				</dl>
				<dl>
				    <dt>Mac兑换次数：</dt>
				    <dd>
				     <input name="lvActivityMac.exchangeNum" class="required digitsNoZore"  maxlength="4" type="text" size="32" value="${lvActivityMac.exchangeNum }"/>
				    </dd>
				</dl>


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