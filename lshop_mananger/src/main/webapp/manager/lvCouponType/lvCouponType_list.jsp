<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<form id="pagerForm" method="post" action="lvCouponTypeAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvCouponTypeAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
		 <table class="searchContent" >
						<tr>
								</tr><tr>
								<td><label>优惠券名称</label>
								<input name="lvCouponType.name" type="text" size="20" value="${lvCouponType.name }" maxlength="127"/>
								</td>
								<td><label>是否启用</label><select name="lvCouponType.status">
								<option value="">==请选择==</option>
								<option value="1" <c:if test="${lvCouponType.status==1 }">selected="selected"</c:if>>启用</option>
								<option value="0" <c:if test="${lvCouponType.status==0 }">selected="selected"</c:if>>停用</option>
							    </select></td>
								<td><label>是否过期</label><select name="lvCouponType.overdue">
								<option value="">==请选择==</option>
								<option value="1" <c:if test="${lvCouponType.overdue==1 }">selected="selected"</c:if>>是</option>
								<option value="0" <c:if test="${lvCouponType.overdue==0 }">selected="selected"</c:if>>否</option>
							    </select></td>
								<td><label>优惠券金额</label><input name="lvCouponType.startAmount" type="text" size="10" maxlength="5" class="numberNew" value="${lvCouponType.startAmount }"/>-<input name="lvCouponType.endAmount" type="text" size="10" class="numberNew" maxlength="5" value="${lvCouponType.endAmount }"/></td>
								<td><label>重复使用</label><select name="lvCouponType.reuse">
								<option value="">==请选择==</option>
								<option value="1" <c:if test="${lvCouponType.reuse==1 }">selected="selected"</c:if>>是</option>
								<option value="0" <c:if test="${lvCouponType.reuse==0 }">selected="selected"</c:if>>否</option>
							    </select></td>
						</tr><tr>
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
		<sec:authorize url="userEdit">
		    <li><a class="add" href="lvCouponTypeAction!befSave.action?json.navTabId=${json.navTabId}" ref="mainList" target="dialog" navTabId="lvAccount" rel="lvAccount"  width="800" height="650" mask="true"  title="添加优惠券"><span>添加优惠券</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="lvCouponTypeAction!exportCouponTypeList.action?json.navTabId=${json.navTabId}"  target="dwzExport" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<th width="4%" orderField="id" class="${orderDirection}">ID</th>
					<th width="20%">优惠券名称</th>
					<th width="15%">有效时间</th>
	                <th width="5%">优惠券金额</th>
	                <th width="5%">重复使用</th>
					<th width="5%">总数</th>
					<th width="5%">未发放数</th>
					<th width="5%">已发放数</th>
					<th width="5%">已使用数</th>
					<th width="5%">是否启用</th>
					<th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.name }</td>
								<td><s:date name="startTime" format="yyyy-MM-dd"/>至<s:date name="endTime" format="yyyy-MM-dd"/></td>
								<td>${item.amount }</td>
								<td>
								   <s:if test="#item.reuse==1">是</s:if>
								   <s:if test="#item.reuse==0">否</s:if>
								</td>
								<td>${item.total }</td>
								<td>${item.noGrantNumber }</td>
								<td>${item.grantNumber }</td>
								<td>${item.usedNumber }</td>
								<td>
								   <s:if test="#item.status==1">启用</s:if>
								   <s:if test="#item.status==0">停用</s:if>
								</td>
					<td>
					<a class="btnView" href="lvCouponTypeAction!view.action?lvCouponType.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="900" height="650" mask="true">查看</a>
					<a class="btnEdit" href="lvCouponTypeAction!befEdit.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId}"  title="编辑" target="dialog" width="800" height="450" mask="true">编辑</a>
					<sec:authorize url="findCouponList">     
					<a href="lvCouponAction!list.action?lvCoupon.couponTypeCode=${item.code}&json.navTabId=${json.navTabId}"  rel="commendList_1" target="navTab">发放详情</a>
					</sec:authorize>
					<a href="lvCouponTypeAction!befDownload.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId}"  title="下载优惠码" target="dialog" title="下载优惠码" width="500" height="400" mask="true">下载优惠码</a>	
                    <a href="lvCouponTypeAction!befAddNumber.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" title="增加数量" width="500" height="400" mask="true">增加数量</a>
                    <a href="lvCouponTypeAction!befExtendDate.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" title="延长有效期" width="500" height="300" mask="true">延长有效期</a>
                    <s:if test="#item.status==1">
                    <a href="lvCouponTypeAction!befDisable.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId}"  title="停用优惠券!" target="dialog" width="500" height="300" mask="true">停用</a>
                    </s:if>
                    <s:if test="#item.status==0">
                    <a href="lvCouponTypeAction!updateStatus.action?lvCouponType.id=${item.id}&json.navTabId=${json.navTabId }"  title="确实要启用?该操作请谨慎!" target="ajaxTodo">启用</a>
                    </s:if>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>