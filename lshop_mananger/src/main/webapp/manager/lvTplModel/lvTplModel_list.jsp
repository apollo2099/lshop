<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvTplModelMngAction!list.action?storeFlag=${storeFlag}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvTplModelMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="720">
				<tr>
						</tr><tr>
						<td><label>名称</label></td><td><input name="lvTplModel.modelName" type="text" size="20" value="${lvTplModel.modelName}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvTplModel.storeFlag">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvTplModel.storeFlag}">selected="selected"</c:if>>${store.name}</option>
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
				<li><a class="add" href="lvTplModelMngAction!befSave.action?storeFlag=${storeFlag}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplModelPublic" rel="lvTplModelPublic" width="400" height="200" mask="true"><span>添加</span></a></li>
			    <li><a class="add" href="/manager/lvTplModel/import_excel.jsp" target="dialog" navTabId="lvTplModelPublic_import" rel="lvTplModelPublic_import" width="500" height="300" mask="true"><span>导入</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="180">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}" width="50">编号</th>
							<th width="10%">所属关系</th>
							<th width="30%" orderField="modelName" class="${orderField eq 'modelName' ? orderDirection : ''}">名称</th>
							<th width="5%" orderField="isDefault" class="${orderField eq 'isDefault' ? orderDirection : ''}">是否默认</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}" width="200">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${modifyTime eq 'modifyTime' ? orderDirection : ''}" width="200">修改时间</th>
					        <th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeFlag}">${store.name}</c:if>
									</c:foreach>
								</td>
								<td>${item.modelName }</td>
								<td><s:if test="#item.isDefault==1">是</s:if><s:else>否</s:else></td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
					   <s:if test="#item.isDefault!=1">
						<a class="btnDel" href="lvTplModelMngAction!del.action?lvTplModel.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					   </s:if>
						<a class="btnEdit" href="lvTplModelMngAction!befEdit.action?lvTplModel.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplModel" rel="lvTplModel" title="编辑" width="400" height="200" mask="true">编辑</a>
					    <a  class="btnLook" href="lvTplDetailMngAction!list.action?lvTplDetail.tplModelCode=${item.code}&lvTplModel.storeFlag=${item.storeFlag}&json.navTabId=lvTplDetail" target="navTab" navTabId="lvTplDetail" rel="lvTplDetail" title="明细管理" width="850" height="500" mask="true">明细管理</a>
					    &nbsp;
					   <s:if test="#item.isDefault!=1">
					   <a  href="lvTplModelMngAction!doDefault.action?&lvTplModel.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="设置默认模板">设置默认</a>
					   </s:if>
					   <s:else>
					    <a  href="lvTplModelMngAction!doBuild.action?lvTplModel.id=${item.id}&lvTplModel.storeFlag=${item.storeFlag}" target="ajaxTodo" title="生成模板页面">生成模板页面</a>
					   </s:else>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>