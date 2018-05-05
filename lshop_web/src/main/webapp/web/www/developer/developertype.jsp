<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
	
	
	<div class="application">
		   <div class="posit">
		      <h2 class="bt3" id="nbt">
			    <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首頁</a> > 註冊開發者</span> </p>
			  </h2>
		   </div>  
		   <div class="apl_title" id="apl_title">
		      <div class="apptitle">請選擇開發者賬號類型</div>
		      <div class="app_detil"><span>賬號類型開通後不可修改請根據實際情況選擇</span></div>
		   </div> 
			   <div class="apl_ruzu">
			    <a href="${storeDomain}/web/developer!toApply.action?type=0">
			      <div class="apl_ruzu_left"> 
			           <strong>個人開發者</strong>
			           <span>適用於個人或小團隊需要提供開發者個人資料</span> 
			      </div>
			    </a>
			    <a href="${storeDomain}/web/developer!toApply.action?type=1">
			      <div class="apl_ruzu_right">
			       <strong>企業開發者</strong>
			       <span>適用於企業，機構註冊需提供企業，機構信息</span>
			      </div>
			    </a>
			      <div class="clear"></div>
			   </div>
   
   </div>
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	</body>
</html>