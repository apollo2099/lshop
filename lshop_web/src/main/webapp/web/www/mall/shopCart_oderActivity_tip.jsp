<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:if test="${not empty activity || not empty activityLottery || not empty activityAppontList}">
<td height="70" colspan="7" bgcolor="#FFFFFF" style=" padding-left:20px; line-height:30px;"> <font style="font-weight:bold">消費提醒：</font><BR>
下訂單並支付成功的用戶可以參加以下促銷 <br />
<c:if test="${not empty activity}">
<img src="${resDomain}/www/res/images/zq.jpg"  /> ${activity.activityTitle}<br />
</c:if>
<c:if test="${not empty activityLottery}">
<img src="${resDomain}/www/res/images/cj.jpg"  /> ${activityLottery.activityTitle}<br />
</c:if>

<c:foreach items="${activityAppontList }" var="ac">
	  <c:if test="${ac.givenTypeNum==1 }">
	   <c:if test="${ac.uncollectedTotal>0 }">
	   <img src="${resDomain}/www/res/images/zq.jpg"  /> ${ac.givenTypeName}<br />
	   </c:if>
	  </c:if>
	  <c:if test="${ac.givenTypeNum==2 }">
	  <img src="${resDomain}/www/res/images/cj.jpg"  /> ${ac.givenTypeName}<br />
	  </c:if>
	  <c:if test="${not empty ac.activityGift }">
		<img src="${resDomain}/www/res/images/zp.jpg"  /> 
		<c:foreach items="${ ac.activityGift}" var="g">
			${g.giftName }<font class="redfont"> ×${g.giftEveryNum }</font>&nbsp;&nbsp;&nbsp;
		</c:foreach><br />
	  </c:if>
</c:foreach>

</td>
</c:if>