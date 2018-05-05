<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:if test="${not empty activity || not empty activityLottery}">
<td height="70" colspan="7" bgcolor="#FFFFFF" style=" padding-left:20px; line-height:30px;"> <font style="font-weight:bold">You have joined the following promotions：</font><BR>
<c:if test="${not empty activity}">
<img src="${resDomain}/bmen/res/images/zq.jpg"  /> ${activity.activityTitle}<br />
</c:if>
<c:if test="${not empty activityLottery}">
<img src="${resDomain}/bmen/res/images/cj.jpg"  /> ${activityLottery.activityTitle}<br />
</c:if>
</td>
</c:if>