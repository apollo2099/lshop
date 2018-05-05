<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvActivityAction!list.action?json.navTabId=${json.navTabId}" name="pagerFormAction"/>
</jsp:include>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvActivityAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<input type="hidden" name="lvActivity.typeKey" value="${lvActivity.typeKey}">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
						</tr><tr>
						<td><label>活动名称</label>
						<input name="lvActivity.activityTitle" type="text" size="20" maxlength="120" value="${lvActivity.activityTitle}"/>
						</td>
						<td><label>活动状态</label><select name="lvActivity.status">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvActivity.status==1 }">selected="selected"</c:if>>启用</option>
						<option value="0" <c:if test="${lvActivity.status==0 }">selected="selected"</c:if>>停用</option>
					    </select></td>
						<td><label>活动类型</label>
						<%--
						<select name="lvActivity.typeKey" disabled="disabled">
						<option value="">==请选择==</option>
						<option value="1" <c:if test="${lvActivity.typeKey==1 }">selected="selected"</c:if>>限时活动</option>
						<option value="2" <c:if test="${lvActivity.typeKey==2 }">selected="selected"</c:if>>限量活动</option>
						<option value="3" <c:if test="${lvActivity.typeKey==3 }">selected="selected"</c:if>>订单满就送活动</option>
     					<option value="4" <c:if test="${lvActivity.typeKey==4 }">selected="selected"</c:if>>点击连接就送活动</option>
     					<option value="5" <c:if test="${lvActivity.typeKey==5 }">selected="selected"</c:if>>注册就送活动</option>
     					<option value="6" <c:if test="${lvActivity.typeKey==6 }">selected="selected"</c:if>>分享就送活动</option>
     					<option value="7" <c:if test="${lvActivity.typeKey==7 }">selected="selected"</c:if>>抽奖活动</option>
     					<option value="8" <c:if test="${lvActivity.typeKey==8 }">selected="selected"</c:if>>购买指定商品就送活动</option>
					    </select>
					     --%>
					    <gv:dictionary type="select" code="ACTIVITY_TYPE" value="${lvActivity.typeKey}" disabled="disabled"/>
					    </td>
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
				<li><a class="add" href="lvActivityAction!befSave.action?lvActivity.typeKey=${lvActivity.typeKey }&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvActivity" rel="lvActivity" width="750" height="650" mask="true"><span>添加</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="145">
			<thead>
				<tr>
				<!-- 生成表单 -->
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="20%">活动名称</th>
				<th width="10%">活动类型</th>
				<th width="10%">活动开始时间</th>
				<th width="10%">活动结束时间</th>
				<th width="20%">描述</th>
				<th width="5%">活动状态</th>
				<th width="20%">操作</th>
                
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.activityTitle }</td>
								<td>
								<%--
							      <s:if test="#item.typeKey==1">限时活动</s:if>
								  <s:if test="#item.typeKey==2">限量活动</s:if>
								  <s:if test="#item.typeKey==3">订单满就送活动</s:if>
								  <s:if test="#item.typeKey==4">点击连接就送活动</s:if>
								  <s:if test="#item.typeKey==5">注册就送活动</s:if>
								  <s:if test="#item.typeKey==6">分享就送活动</s:if>
								  <s:if test="#item.typeKey==7">抽奖活动</s:if>
								  <s:if test="#item.typeKey==8">购买指定商品就送活动</s:if>
								   --%>
								  <gv:dictionary type="text" code="ACTIVITY_TYPE" value="${item.typeKey}"/>
								</td>
								<td><s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.remark }</td>
								<td>
								<gv:dictionary type="text" code="ACTIVITY_STATUS" value="${item.status}"/>
								</td>
					<td>
                        <a class="btnView" href="lvActivityAction!view.action?lvActivity.id=${item.id}" target="dialog" navTabId="lvActivity" rel="lvActivity" title="查看" width="850" height="700" mask="true">查看</a>
						<a class="btnEdit" href="lvActivityAction!befEdit.action?lvActivity.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvActivity" rel="lvActivity" title="编辑" width="750" height="650" mask="true">编辑</a>
						<s:if test="#item.status==0">
                    	<a href="lvActivityAction!updateStatus.action?lvActivity.id=${item.id}&lvActivity.typeKey=${item.typeKey }&lvActivity.code=${item.code }&lvActivity.status=1&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确实启用该活动吗?该操作请谨慎!">启用</a>
                    	</s:if>
                    	<s:if test="#item.status==1">
                    	<a href="lvActivityAction!updateStatus.action?lvActivity.id=${item.id}&lvActivity.typeKey=${item.typeKey }&lvActivity.code=${item.code }&lvActivity.status=0&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确实停用该活动吗?该操作请谨慎!">停用</a>
                    	</s:if>
                    	<s:if test="#item.typeKey==7">
                    	<a href="lvLotteryPrizeAction!list.action?lvLotteryPrize.activityCode=${item.code }&json.navTabId=lvLottery_1"  rel="lvLottery_1" target="navTab">奖项物品</a>
                    	<a href="lvLotteryLogsAction!list.action?lvLotteryLogs.activityCode=${item.code }&json.navTabId=lvLotteryLogs_1"  rel="lvLotteryLogs_1" target="navTab">中奖记录</a>
					    </s:if>
					</td>
					
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>