<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxObtainMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxObtainMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>用户昵称</label></td><td><input name="wxObtain.nickname" type="text" size="20" value="${wxObtain.nickname}"/></td>
						<td><label>领取类型</label></td><td>
						<select name="wxObtain.obtainType">
						<option value="">
						==请选择==
						</option>
						<option value="1" <c:if test="${wxObtain.obtainType==1 }">selected="selected"</c:if>>
					    自己领取
						</option>
						<option value="2" <c:if test="${wxObtain.obtainType==2 }">selected="selected"</c:if>>
					  好友支持
						</option>
						</select></td>
						</tr><tr>
						<td><label>IP地址</label></td><td><input name="wxObtain.ipAddress" type="text" size="20" value="${wxObtain.ipAddress}"/></td>
						<td><label>公众号名称</label></td><td>
						<select name="wxObtain.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxObtain.wxhConfigId}">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
					    </c:foreach>
					    </select>
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
				<li></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="openid" class="${orderField eq 'openid' ? orderDirection : ''}">用户标识</th>
							<th orderField="nickname" class="${orderField eq 'nickname' ? orderDirection : ''}" width="200px">用户昵称</th>
							<th orderField="obtainType" class="${orderField eq 'obtainType' ? orderDirection : ''}">领取类型</th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th orderField="ipAddress" class="${orderField eq 'ipAddress' ? orderDirection : ''}">ip地址</th>
							<th orderField="friendOpenId" class="${orderField eq 'friendOpenId' ? orderDirection : ''}">好友标识</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.openid }</td>
								<td>${item.nickname }</td>
								<td>
								<c:if test="${item.obtainType == 1}">
								自己领取
								</c:if>
								<c:if test="${item.obtainType == 2}">
								好友支持
								</c:if></td>
								<td>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id==item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach></td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.ipAddress }</td>
								<td>${item.friendOpenId }</td>
								
					<td>
						<a class="btnView" href="wxObtainMngAction!view.action?wxObtain.id=${item.id}" target="dialog" navTabId="wxObtain" rel="wxObtain" title="查看" width="450" height="380">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>