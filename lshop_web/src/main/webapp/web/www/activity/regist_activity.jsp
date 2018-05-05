<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<c:if test="${not empty activities && fn:length(activities) > 0}">
<ul>
<li class="title">註冊就送</li>
<c:foreach items="${activities}" var="regist">
<li>
<c:if test="${regist.giftType == 1}">
<img src="${resDomain}/www/res/images/zq.jpg" width="43" height="17" />
</c:if>
<c:if test="${regist.giftType == 2}">
<img src="${resDomain}/www/res/images/cj.jpg" width="43" height="17" />
</c:if>
 ${regist.activityTitle}</li>
</c:foreach>
</ul>
</c:if>