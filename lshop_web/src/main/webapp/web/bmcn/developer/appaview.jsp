<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
	
	
	<div class="application1">
		   <div class="posit">
		      <h2 class="bt3" id="nbt">
			    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > 注册开发者</span> </p>
			  </h2>
		   </div>  
		   <div class="apl_title">  
		      <div class="app_detil">
                                                              您提交的注册申请未通过审核，审核不通过原因:${developer.reason}，如有疑问请与我们客服人员进行联系。
		         <br />
		         <a href="${storeDomain}/web/index!toIndex.action" id="return">返回首页</a>
		      </div>
		      
		   </div> 
   
   </div>
	
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html>