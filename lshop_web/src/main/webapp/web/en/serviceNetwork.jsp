<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad Overseas Chinese Live TV Streaming Player - Global Sales and Service Outlets</title>
		<meta name="description" content="TVpad sales and service outlets cover in more than 100 developed cities in more than 40 regions and countries, including the United States, Canada, Australia, the United Kingdom, France, Japan, Malaysia, Philippines, and Thailand." />
		<meta name="keywords" content="Worldwide sales and service outlets, sales channel, distributor, agent, reseller." />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","en_serviceNetwork"); %>
		<%@include file="/web/en/common/header.jsp" %>	

		<div class="content_main">
			<div class="posit">
      			<h2 class="bt3" style="background-color:#fff">
					<img src="${resDomain}/en/res/images/icon02.gif" /><a href="/index.html"> Home</a> >  Service Outlets </p>
	  			</h2>
   			</div>  
			<div class="Overall market">
				<div class="mp" >
					<!--<div class="Market_f">
					  <object  classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="960" height="512">
						<param name="movie" value="swf/map.swf" />
						<param name="quality" value="high" />				
						<param name="wmode" value="opaque" />-->
						<!--[if !IE]>--> 
						<!--<object width="960" height="512" type="application/x-shockwave-flash" data="swf/map.swf">-->
						<!--<![endif]-->
						<!--<param name="quality" value="high" />
						<param name="wmode" value="opaque" />
						<param name="expressinstall" value="swf/map.swf" />				
					 	 </object>
					  </object>
					</div>-->
					<div class="Market_Map">      </div>			
  				</div>
		        <div class="Market_join">
		        	<a href="http://business.mtvpad.com/agent/agentAction!toLogin.action"><img src="${resDomain}/en/res/images/Button_Market.gif"/></a>
		            <img src="${resDomain}/en/res/images/Market_01.gif"/>
		    	</div>
        		<table class="table01" cellpadding="0" cellspacing="0">
		          	<thead>
			            <tr>
			              <th style="text-align:center" scope="col" width="130"> Country </th>
			              <th style="text-align:center" scope="col" width="96"> City </th>
			              <th style="text-align:center" scope="col" width="221"> Channel Name </th>
			              <th style="text-align:center" scope="col" width="291"> Address </th>
			              <th style="text-align:center" scope="col" width="127"> Responsible Person </th>
			            </tr>
		          	</thead>
         			<tbody>
         				<c:foreach items="${networkList }" var="network">
	         				<tr class="t Australia">
				              <td style="PADDING-LEFT: 8px"><img alt="" src="${resDomain }/${network.storeId}${network.icon}" />${network.country } </td>
				              <td> ${network.city } </td>
				              <td> ${network.channelName } </td>
				              <td class="font02"> ${network.address }</td>
				              <td style="TEXT-ALIGN: center"> ${network.responsiblePerson } </td>
				            </tr>
         				</c:foreach>
          			</tbody>
        		</table>
        		<div class="Market_border"></div>
    		</div>
		</div>
		<!--End Content_Main-->

		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>
	</body>
</html>
