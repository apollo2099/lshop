<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="lvUserPromotersMngAction!accountManage.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!accountManage.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<div class="searchBar" >
				<ul class="searchContent">
				   <li>
						<label>真实姓名：</label>
						<input name="realName" type="text" id="realName" value="${realName }" width="20"/>
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
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<table class="table" layouth="160" width="100%">
				<thead>
					<tr>
					    <th width="width:4%;"><input type="checkbox" group="ids" class="checkboxCtrl">全选</th>
						<th style="width:12%;">真实姓名</th>
						<th style="width:12%;">邮箱</th>
						<th style="width:12%;">联系电话</th>
						<th style="width:12%;">账户类型</th>
						<th style="width:12%;">账户</th>
						<th style="width:12%;">可结算余额</th>
						<th style="width:12%;">可结算产品数</th>
						<th style="width:12%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="rankFirstInfo" varStatus="status">
					<tr>
						<td><input name="ids" type="checkbox" class='np'>${status.count}</td>
						<td>${rankFirstInfo.realName}</td>
						<td>${rankFirstInfo.email}</td>
						<td>${rankFirstInfo.tel}</td>	
						<td>
							<c:if test="${rankFirstInfo.accountTypes==1}">PayPal</c:if>
			        		<c:if test="${rankFirstInfo.accountTypes==2}">支付宝</c:if>
						</td>		
						<td>${rankFirstInfo.accountNumber}</td>	
						<td>${rankFirstInfo.settlementAmount}</td>	
						<td>${rankFirstInfo.settlementNum}</td>			
						<td>			
						  <c:if test="${rankFirstInfo.isFlag==false}">
					      <a title="生成结算清单?" target="ajaxTodo" href="lvUserPromotersMngAction!pay.action?uid=${rankFirstInfo.uid}&versionTime=${rankFirstInfo.modifyTime}&json.navTabId=${json.navTabId}"  class="btnSelect"><span>生成结算清单</span></a>
					      </c:if>
					      <a  title="结算清单" target="navTab" href="lvExtendBalanceAction!list.action?lvExtendBalance.uid=${rankFirstInfo.uid}&json.navTabId=rankFirstInfo_1" rel="rankFirstInfo_1" class="btnLook"><span>结算清单</span></a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
