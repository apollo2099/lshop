<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
		<ul class="tree expand">			
          <c:foreach items="${areaList}" var="area">
			<li><a href="javascript:">${area.areaName }</a>
				<ul>
				<c:foreach items="${area.cityList}" var="country">
				<li><a href="javascript:">${country.areaName }</a>
				    <ul >
				    <c:foreach items="${country.cityList}" var="city">
				    <li><a href="javascript:" onclick="$.bringBack({countryId:'${country.areaId}', country:'${country.areaName}', city:'${city.areaName }',continentCode:'${area.code }',countryCode:'${country.code }',cityCode:'${city.code }' })">${city.areaName }</a></li>
				    </c:foreach>
				    </ul>
				</li>
				</c:foreach>
				</ul>
			</li>
		 </c:foreach>
		</ul>
	</div>	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>