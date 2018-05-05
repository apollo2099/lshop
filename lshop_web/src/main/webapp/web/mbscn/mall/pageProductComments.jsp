<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<c:if test="${not empty objList}">
     	<c:foreach items="${objList}" var="obj">
     		<div class="detailcoment-box">
		        <div class="comentname">
		          <strong>${obj[1].nickname }<img src="${resDomain }/mbscn${obj[0] }" /></strong>
		          <span><fmt:formatDate value="${obj[1].createTime }"  type="both" /></span>
		          <div class="clear"></div>
		        </div>
		        <div class="star">
		        	<c:foreach begin="1" end="${obj[1].score}">
              			 <span></span>
              		</c:foreach>
		          <div class="clear"></div>
		        </div>
		        <div class="comenttext">
		           ${obj[1].content }
		        </div>
		        <div class="shopcoouts">购买数量：<em>${obj[1].pnum }</em></div>  
		        <div id="henxian"></div>
		      </div>
     	</c:foreach>
     </c:if>