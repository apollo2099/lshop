<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvUserPromotersMngAction!rankPromoterList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!rankPromoterList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar" >
				<ul class="searchContent">
				   <li>
						<label>昵称：</label>
						<input name="nickname" type="text" id="nickname" value="${nickname }" width="20"/>
				   </li>
				   <li>
						<label>邮箱：</label>
						<input name="email" type="text" id="email" value="${email}" width="20"/>
				   </li>
				 </ul>
				 <div class="subBar">
				         <ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent"><button type="submit">检索</button></div>
								</div>
							</li>
						</ul>
				</div>
			</div>
		</form>
		<span>统计：一级推广商 <font color="red">${rankFirstUserNum}</font> 位，二级推广商 <font color="red">${promoterUserNum }</font> 位，总推广台数 <font color="red">${totalSettlementNum }</font> 台，佣金累积 <font color="red">${totalSettlementAmount }</font> 美元</span>
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
				  <li><a class="add" href="lvUserPromotersMngAction!bfadd.action?json.navTabId=${json.navTabId}" target="dialog"  mask="true" title="添加一级推广商"><span>添加</span></a></li>
				  <li><a class="edit" href="lvUserPromotersMngAction!exportInfos.action?json.navTabId=${json.navTabId}" target="dwzExport" title="导出excel?" rel="ids"><span>导出Excel表格</span></a></li>
				</ul>
			</div>
			<table class="table" layouth="160" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl">全选</th>
						<th style="width:8%;">邮箱</th>
						<th style="width:6%;">昵称</th>
						<th style="width:6%;">已申请数量</th>
						<th style="width:6%;">申请数量</th>
						<th style="width:6%;">下级推广台数</th>
						<th style="width:6%;">佣金累积</th>
						<th style="width:6%;">已结算佣金</th>
						<th style="width:6%;">未结算佣金</th>
						<th style="width:10%;">创建时间</th>	
						<th style="width:10%;">最后登录时间</th>	
						<th style="width:12%;">操作</th>
						<th style="width:13%;">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="obj" varStatus="status">
					<tr>
						<td><input name="ids" value="${obj[11]}" type="checkbox" class='np'>${status.count}</td>
						<td>${obj[0]}</td>
						<td>${obj[1]}</td>
						<td>${obj[2]}</td>			
						<td>${obj[3]}</td>
						<td>${obj[4]}</td>	
						<td>${obj[5]}</td>	
						<td>${obj[6]}</td>	
						<td>${obj[7]}</td>		
						<td>${obj[8]}</td>
						<td>${obj[9]}</td>
						<td>
					      <a title="编辑" target="dialog" mask="true" href="lvUserPromotersMngAction!bfEditRankPromoter.action?id=${obj[11]}&json.navTabId=${json.navTabId}" class="btnEdit">编辑</a>
					      <a  href="lvUserPromotersMngAction!bfViewRankPromoter.action?id=${obj[11]}" target="dialog" title="资料" width="850" height="500">资料</a>
					      <a  href="lvUserPromotersMngAction!showGrade.action?myId=${obj[11]}&json.navTabId=${json.navTabId}" title="业绩" target="navTab" ref="lvUserPromoters">业绩</a>
						  <c:if test="${obj[3]>0}">
						    <a href="lvUserPromotersMngAction!befCheck.action?id=${obj[11]}&json.navTabId=${json.navTabId}" target="dialog" width="700" height="500" title="审核"  style="color:green">审核</a>
						  </c:if >
						</td>
						<td>${obj[10]}</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
