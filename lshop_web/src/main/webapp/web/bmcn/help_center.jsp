<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>banana商城帮助中心</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
	
		<div class="content_main">
			<div class="left_frame">
	    		<div class="cm_n_1"> 
		        	<h3>帮助中心</h3>
		        	<c:foreach items="${objList}" var="obj">
		        		<h4 <c:if test="${obj[0].id==id}">class="choose"</c:if>>${obj[0].name }</h4>
		        		<ul>
		        		<c:foreach items="${obj[1]}" var="lvHelp">
		        			<li><a href="/help${obj[0].id}.html#M_${lvHelp.id}">${lvHelp.name }</a></li>
		        		</c:foreach>
		        		</ul>
	   				</c:foreach>
	        	</div>
	        	
				<!--left_ad-->
				<ad:ad adkey="AD_TVPAD_LEFT"></ad:ad>
	    	</div> 
		 	<!--End left_Frame-->
		 	
			<div class="right_frame">
		 		<h1><img src="${resDomain}/bmcn/res/images/icon02.gif" /><font class="bfont"><a href="/index.html">首页</a>--> <a href="/web/helpCenter!getHelps.action">帮助中心</a></font> </h1>
	 	 		<div class="help_center_details">                  
					<c:foreach items="${lvHelps}" var="help" varStatus="status">
		   				<p id="M_${help.id}"><font class="redfont fontwz">${help.name}：</font><br />
							${help.content }
						</p>
		   			</c:foreach>
				</div>
				<!--End 幫助中心-->
			</div>
			<!--End right_Frame-->
			
		</div>
		<!--End content-->
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html>