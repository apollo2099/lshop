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
	
	
	<div class="application">
		   <div class="posit">
		      <h2 class="bt3" id="nbt">
			    <p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首页</a> > 注册开发者</span> </p>
			  </h2>
		   </div>  
		   <div class="apl_title" id="apl_title">
		      <div class="apptitle">请选择开发者账号类型</div>
		      <div class="app_detil"><span>账号类型开通后不可修改 请根据实际情况选择</span></div>
		   </div> 
			   <div class="apl_ruzu">
			    <a href="${storeDomain}/web/developer!toApply.action?type=0">
			      <div class="apl_ruzu_left"> 
			           <strong>个人开发者</strong>
			           <span>适用于个人或小团队 需要提供开发者个人资料</span> 
			      </div>
			    </a>
			    <a href="${storeDomain}/web/developer!toApply.action?type=1">
			      <div class="apl_ruzu_right">
			       <strong>企业开发者</strong>
			       <span>适用于企业、机构注册 需提供企业、机构信息</span>
			      </div>
			    </a>
			      <div class="clear"></div>
			   </div>
   
   </div>
	
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	</body>
</html>