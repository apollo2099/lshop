<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<c:foreach items="${objList}" var="obj" varStatus="status">
<li>
	<div class="libox">
		<a href="${obj[0].url}">
		<div class="proimg">
			 <img src="${obj[0].pimage }" width="63%" />
		</div>
		<div class="imtitile">
			<h2>${obj[0].productName }</h2>
		</div>
		<div class="imgjia">
			<span></span> <span>${obj[1].currency} ${obj[0].productPrice}</span>
		</div>
		</a>
	</div>
</li>
</c:foreach>