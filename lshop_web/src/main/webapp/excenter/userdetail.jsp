<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<div class="diffusion03_nr">			
	   <ul>
	     <c:foreach items="${objList}" var="obj" varStatus="status">  
			<c:if test="${status.count==1}"><li><p class="No1">${obj[0]}</p> <p>收入：<span class="f_orange">$${obj[1]}</span></p> </li></c:if>
			<c:if test="${status.count==2}"><li><p class="No2">${obj[0]}</p> <p>收入：<span class="f_orange">$${obj[1]}</span></p> </li></c:if>
			<c:if test="${status.count==3}"><li><p class="No3">${obj[0]}</p> <p>收入：<span class="f_orange">$${obj[1]}</span></p> </li></c:if>
			<c:if test="${status.count==4}"><li><p class="No4">${obj[0]}</p> <p>收入：<span class="f_orange">$${obj[1]}</span></p> </li></c:if>
			<c:if test="${status.count==5}"><li><p class="No5">${obj[0]}</p> <p>收入：<span class="f_orange">$${obj[1]}</span></p> </li></c:if>
		</c:foreach>
         </ul>             
</div>