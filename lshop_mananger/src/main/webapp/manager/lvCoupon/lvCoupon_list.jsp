<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvCouponAction!list.action?lvCoupon.couponTypeCode=${lvCoupon.couponTypeCode}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvCouponAction!list.action?lvCoupon.couponTypeCode=${lvCoupon.couponTypeCode}&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
						</tr><tr>						
						<td><label>优惠券名称</label>
                        ${couponType.name }
						</tr><tr>
						<td><label>优惠码</label>
						<input name="lvCoupon.couponCode" type="text" size="20" value="${lvCoupon.couponCode}" maxlength="32"/>
						</td>
						<td><label>使用订单</label><input name="lvCoupon.orderId" type="text" size="20" value="${lvCoupon.orderId}" maxlength="32"/></td>
						<td><label>使用人</label><input name="lvCoupon.applyName" type="text" size="20" value="${lvCoupon.applyName}" maxlength="32"/></td>
						<td><label>优惠码状态</label>
						<select name="lvCoupon.couponStatus">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvCoupon.couponStatus==1 }">selected="selected"</c:if>>已领取</option>
						<option value="2" <c:if test="${lvCoupon.couponStatus==2 }">selected="selected"</c:if>>已使用</option>
					    </select>
						</td>
						</tr><tr>
                        <td><label>发放途径</label>
                        <select name="lvCoupon.grantWay" style="width:200px;">
						<option value="">==请选择==</option>
						<option value="0000" <c:if test="${lvCoupon.grantWay=='0000' }">selected="selected"</c:if>>管理员下载</option>
						<option value="0001" <c:if test="${lvCoupon.grantWay=='0001' }">selected="selected"</c:if>>推广获取优惠券</option>
						<c:foreach items="${activityList }" var="way">
						<option value="${way.code }" <c:if test="${lvCoupon.grantWay==way.code }">selected="selected"</c:if>>${way.activityTitle }</option>
						</c:foreach>
					    </select>
                        
                        </td>
						<td><label>获取时间</label><input name="lvCoupon.obtainStartTime" type="text" size="10" class="date" format="yyyy-MM-dd" value="${lvCoupon.obtainStartTime }"/>-<input name="lvCoupon.obtainEndTime" type="text" 
						size="10" class="date" format="yyyy-MM-dd" value="${lvCoupon.obtainEndTime }" format="yyyy-MM-dd"/></td>
						<td><label>操作人</label><input name="lvCoupon.operator" type="text" size="20" value="${lvCoupon.operator}"/></td>
						
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="icon" href="lvCouponAction!befExportCoupon.action?lvCoupon.couponTypeCode=${lvCoupon.couponTypeCode }&json.navTabId=${json.navTabId}"  target="dialog" navTabId="lvCoupon" rel="lvCoupon" width="600" height="400" mask="true"><span>导出Excel</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="200">
			<thead>
				<tr>
					<!-- 生成表单 -->
	                <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="4%" orderField="id" class="${orderDirection}">ID</th>
					<th width="15%">优惠码</th>
					<th width="10%">优惠码状态</th>
					<th width="5%">发放类型</th>
					<th width="10%">发放途径</th>
					<th width="10%">操作人</th>
					<th width="5%">获取人</th>
					<th width="10%">获取时间</th>
					<th width="5%">使用人</th>
					<th width="10%">使用时间</th>
					<th width="15%">使用订单</th>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.couponCode }</td>
								<td>
								<s:if test="#item.couponStatus==0">未领取</s:if>
								<s:if test="#item.couponStatus==1">已领取</s:if>
								<s:if test="#item.couponStatus==2">已使用</s:if>
								</td>
								<td>
								<s:if test="#item.grantType==1">线上</s:if>
								<s:if test="#item.grantType==2">线下</s:if>
								</td>
								<td>
									<s:if test="#item.grantType==1">
									<s:iterator value="#request.activityList" status="stat" id="way">
									 <c:if test="${item.grantWay eq way.code}">${way.activityTitle}</c:if>
									</s:iterator>
									</s:if>
									<s:if test="#item.grantType==2">
									<s:if test="#item.grantWay=='管理员下载'">管理员下载</s:if>
									<s:if test="#item.grantWay!=null">
									<s:iterator value="#request.activityList" status="stat" id="way">
									 <c:if test="${item.grantWay eq way.code}">${way.activityTitle}</c:if>
									</s:iterator>
									</s:if>
									</s:if>
									<s:if test="#item.grantWay=='推广获取优惠券'">推广获取优惠券</s:if>
								</td>
								<td>
								${item.operator }
								</td>
								<td>
								<c:if test="${couponType.reuse==1}">
								<a class="" href="lvCouponAction!listForObtain.action?couponCode=${item.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="listForObtain" rel="listForObtain" title="获取人" width="550" height="400" mask="true">${item.obtainName }</a>
								</c:if>
								<c:if test="${couponType.reuse==0}">${item.obtainName }</c:if>
								</td>
								<td>
								<c:if test="${couponType.reuse==1}">
								<a class="" href="lvCouponAction!listForObtain.action?couponCode=${item.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="listForObtain" rel="listForObtain" title="获取时间" width="550" height="400" mask="true"><s:date name="obtainTime" format="yyyy-MM-dd HH:mm"/></a>
								</c:if>
								<c:if test="${couponType.reuse==0}"><s:date name="obtainTime" format="yyyy-MM-dd HH:mm"/></c:if>
								</td>
								<td>
								<c:if test="${couponType.reuse==1}">
								<a class="" href="lvCouponAction!listForApply.action?couponCode=${item.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="listForObtain" rel="listForObtain" title="使用人" width="550" height="400" mask="true">${item.applyName }</a>
								</c:if>
								<c:if test="${couponType.reuse==0}">${item.applyName }</c:if>
								</td>
								<td>
								<c:if test="${couponType.reuse==1}">
								<a class="" href="lvCouponAction!listForApply.action?couponCode=${item.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="listForObtain" rel="listForObtain" title="使用时间" width="550" height="400" mask="true"><s:date name="applyTime" format="yyyy-MM-dd HH:mm"/></a>
								</c:if>
								<c:if test="${couponType.reuse==0}"><s:date name="applyTime" format="yyyy-MM-dd HH:mm"/></c:if>
								</td>
								<td>
								<c:if test="${couponType.reuse==1}">
								<a class="" href="lvCouponAction!listForApply.action?couponCode=${item.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="listForObtain" rel="listForObtain" title="使用订单" width="550" height="400" mask="true">${item.orderId }</a>
								</c:if>
								<c:if test="${couponType.reuse==0}">${item.orderId }</c:if>
								</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>