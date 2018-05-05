<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:if test="${not empty activity}">
<img src="${resDomain}/mtmcn/res/images/zq.jpg"  /> <span>${activity.activityTitle}</span><br />
</c:if>
<c:if test="${not empty activityLottery}">
<img src="${resDomain}/mtmcn/res/images/cj.jpg"  /> <span>${activityLottery.activityTitle}</span><br />
</c:if>

<c:foreach items="${activityAppontList }" var="ac">
	  <c:if test="${ac.givenTypeNum==1 }">
	     <c:if test="${ac.uncollectedTotal>0 }">
	       <img src="${resDomain}/mtmcn/res/images/zq.jpg" />${ac.givenTypeName}
	     </c:if>
	  </c:if>
	  <c:if test="${ac.givenTypeNum==2 }">
	      <img src="${resDomain}/mtmcn/res/images/cjjh.jpg" />${ac.givenTypeName}
	  </c:if>
	  <c:if test="${not empty ac.activityGift }">
			 <img src="${resDomain}/mtmcn/res/images/zp1.jpg" />
			 	<c:foreach items="${ ac.activityGift}" var="g">
			       <span>${g.giftName }<i class="usd">x${g.giftEveryNum }</i></span>&nbsp;&nbsp;&nbsp;
			 	</c:foreach>
	  </c:if>
</c:foreach>