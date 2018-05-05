<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
  <td width="50%" height="30" align="left" valign="middle"><strong class="tstrong">购买数量</strong></td>
  <td width="50%" height="30" valign="middle"><strong class="tstrong">价格（每件）</strong></td>
</tr>
<c:if test="${not empty ladderList}">
<c:foreach items="${ladderList}" var="lader1">
<tr>
<c:choose>
	<c:when test="${empty lader1.upInterval and empty lader1.downInterval}">
		<td height="30" align="left" valign="middle">0</td>
	</c:when>
	<c:when test="${not empty lader1.upInterval and empty lader1.downInterval}">
		<td height="30" align="left" valign="middle">${lader1.upInterval }以上</td>
	</c:when>
	<c:when test="${empty lader1.upInterval and not empty lader1.downInterval}">
		<td height="30" align="left" valign="middle">${lader1.downInterval }以下</td>
	</c:when>
	<c:otherwise>
		<td height="30" align="left" valign="middle">${lader1.upInterval }-${lader1.downInterval }</td>
	</c:otherwise>
</c:choose>
<td height="30" valign="middle">${lvStore.currency} ${lader1.price }</td>
</tr>
</c:foreach>
</c:if>
</table>