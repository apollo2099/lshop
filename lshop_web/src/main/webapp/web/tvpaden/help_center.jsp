<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Details of TVpad purchasing, operation, Apps and others-HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/layout.css" rel="stylesheet" type="text/css" />
<script src="${resDomain}/tvpaden/res/js/yu.js" type="text/javascript"></script>
<script src="${resDomain}/tvpaden/res/js/tb.js" type="text/javascript"></script>	
</head>
<body>
<%@include file="/web/tvpaden/common/top.jsp" %>

<!--ad-->
<ad:ad adkey="AD_LOCATION_APP"></ad:ad>

<!--End focus-->

<div class="content_main">

		<div class="left_frame">
    	<div class="cm_n_1"> 
        	<h3>Help Center</h3>
        	<c:foreach items="${lvHelpTypes}" var="lvHelpType">
        		<h4>${lvHelpType.name }</h4>
        		<ul>
        		<c:foreach items="${lvHelps}" var="lvHelp">
        			<c:if test="${lvHelp.helpId==lvHelpType.id}">
        				<li id="aa"><a href="/help${lvHelpType.id}.html#M_${lvHelp.id}">${lvHelp.name }</a></li>
        			</c:if>
        		</c:foreach>
        		</ul>
        	</c:foreach>	
        </div>
       <div class="cm_ad_left"><img src="${resDomain}/tvpaden/res/images/pic01.gif" width="200" height="250" /></div>
    </div> 
 	<!--End left_Frame-->
 	
	 
	 <div class="right_frame">
	 		<h1><img src="${resDomain}/tvpaden/res/images/icon02.gif" /><font class="bfont"><a href="/index.html">Home</a>--> <a href="/web/helpCenter!getHelps.action">Help Center</a>--></font>Shopping Process</h1>
	 	 		<div class="help_center_details">                  
                    <c:foreach items="${lvHelps}" var="help">
                    <p id="M_${help.id}"><font class="redfont fontwz">${help.name}</font><br />
					${help.content }
					</p>
					</c:foreach>
				</div>
				<!--End 幫助中心-->
	 </div>
	 <!--End right_Frame-->
</div>
<!--End content-->

<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 